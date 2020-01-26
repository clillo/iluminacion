package com.clillo.dmx.programas;

import java.util.ArrayList;

import com.clillo.dmx.comm.vdj.VJListener;
import com.clillo.dmx.configuracion.ProgramasCfg;
import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.configuracion.programas.TipoGatillador;
import com.clillo.dmx.configuracion.programas.TipoPrograma;
import com.clillo.dmx.programas.pasos.EjecutorPasos;
import com.clillo.utiles.Log;

public class EjecutorMultiplesProgramas extends Thread implements VJListener{

	private ArrayList<ProgramaTO> listaProgramas;
	
	protected EjecutorMultiplesProgramas() {
		listaProgramas = ProgramasCfg.getListaProgramas();
	}
	
	public ArrayList<ProgramaTO> getListaProgramas(){		
		return listaProgramas;
	}
	
	public ArrayList<ProgramaTO> getListaProgramas(TipoPrograma tipoPrograma){
		ArrayList<ProgramaTO> lista = new ArrayList<ProgramaTO>();
		for (ProgramaTO programa: listaProgramas)
			if (programa.getTipo()==tipoPrograma)
				lista.add(programa);
		
		return lista;
	}
	
	public synchronized void ejecuta(boolean enEjecucion, ProgramaTO programa){		
		if (enEjecucion)
			Log.debug("Ejecutando Programa "+programa.getId(), this.getClass());
		else
			Log.debug("Parando Programa "+programa.getId(), this.getClass());
		
		programa.setSiguienteEjecucion(System.currentTimeMillis());		
		programa.setEnEjecucion(enEjecucion);
	}
	
	private void siguientePaso(ProgramaTO programa){
		EjecutorPasos ejecutor = programa.getEjecutorPasos();
		ejecutor.ejecutaPaso(programa);
		
		programa.setSiguienteEjecucion();	
		programa.getNotificableEjecutorPasos().pasoEjecutado();
	}

	private synchronized void tic() {
		long actual = System.currentTimeMillis();

		for (ProgramaTO programa : listaProgramas)
			if (programa.getTipoGatillador() == TipoGatillador.RelojInterno) {

				// if (programa.isEnEjecucion()){
				// if (programa.getSiguienteEjecucion()<actual)
				// siguientePaso(programa);
				//
				// continue;
				// }

				// el programa debe cumplir con las siguientes condiciones:
				// 1.- el programa debe estar habilitado para ejecutarse
				// 2.- el programa no debe estar en espera de la terminación del
				// movimiento
				// 3.- el programa cumplió con la espera necesaria entre
				// ejecuciones
				if (programa.isEnEjecucion() && !programa.isEjecutandoMovimiento() && programa.getSiguienteEjecucion() < actual) {
					siguientePaso(programa);
				}
			}else
				if (programa.getTipoGatillador() == TipoGatillador.RelojExterno) {
					if (programa.isEnEjecucion() && !programa.isEjecutandoMovimiento() && programa.getSiguienteEjecucion() < actual) {

						siguientePaso(programa);
						if(programa.getProgramasDelegados() != null)
							for (ProgramaTO prg: programa.getProgramasDelegados()){
								if (prg==null)
									System.err.println("OJO delegado en null: "+ programa);
								else
									if (prg.isEnEjecucion())
										siguientePaso(prg);
							}
					}
				}
	}

	@Override
	public void run() {
		while (true) {
			
			tic();
				
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
			}
		}
	}

	@Override
	public void cambiar(double vuMeter, double posicion, double songPosBeats, double beatCounter, String nombre) {
	}

	@Override
	public void beat() {
		for (ProgramaTO programa : listaProgramas)
			if (programa.getTipoGatillador() == TipoGatillador.VDJBeat && programa.isEnEjecucion())
				siguientePaso(programa);

	}

	@Override
	public void cuartoDeBeat(int cual) {
		for (ProgramaTO programa : listaProgramas)
			if (programa.getTipoGatillador() == TipoGatillador.VDJCuartoBeat && programa.isEnEjecucion())
				siguientePaso(programa);

	}

	@Override
	public void medioBeat(int cual) {
		for (ProgramaTO programa : listaProgramas)
			if (programa.getTipoGatillador() == TipoGatillador.VDJMedioBeat && programa.isEnEjecucion())
				siguientePaso(programa);
	}
}
