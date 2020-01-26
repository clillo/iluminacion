package com.clillo.dmx.programas.pasos;

import java.util.ArrayList;

import com.clillo.dmx.configuracion.programas.GrupoTO;
import com.clillo.dmx.configuracion.programas.MovimientoTO;
import com.clillo.dmx.configuracion.programas.PasoTO;
import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.core.fixtures.FixtureRobotica;
import com.clillo.dmx.programas.pasos.roboticas.PosicionPadre;

public class EjecutorPasosMovingHead implements EjecutorPasos {
		
	public int velocidadActual = 0;

	@Override
	public void ejecutaPaso(ProgramaTO programa){
		//System.out.println("Ejecutando paso moving head");

		PasoTO paso = programa.siguientePaso();
				
		ejecutaListaMovimientos(programa, paso);
		ejecutaListaGrupos(programa, paso);
	}
	
	private void ejecutaListaMovimientos(ProgramaTO programa, PasoTO paso){
		ArrayList<MovimientoTO> listaMovimientoTO = paso.getListaMovimientos();
		if (listaMovimientoTO != null){
		//	System.out.println("Ejecutando lista movimientos");
			for (MovimientoTO movimiento: listaMovimientoTO){
				FixtureRobotica fixture = movimiento.getRobotica();
				fixture.setVelocidadActual(programa.getVelocidadParaMovimientoRoboticos());
				
				fixture.freeze();
				fixture.moverA(movimiento.getPan(), movimiento.getTilt());
				programa.setEjecutandoMovimiento(true);
				fixture.setProgramaMovimientoEnEjecucion(programa);
			}	
		}
	}
	
	private void ejecutaListaGrupos(ProgramaTO programa, PasoTO paso){
		
		ArrayList<GrupoTO> listaGrupos = paso.getListaGrupos();
		if (listaGrupos != null)
			for (GrupoTO grupo : listaGrupos){
				if (grupo.getTipoAleatorioRoboticas()!=null){
					PosicionPadre posicion = grupo.getTipoAleatorioRoboticas().getPosicion();
					if (posicion!=null){
						posicion.setGrupo(grupo);
						posicion.setPrograma(programa);
						
						if (programa.getParametros().isPrimeraEjecucion()){
							programa.getParametros().setPrimeraEjecucion(false);
							posicion.inicia();
						}
						
						posicion.mueve();
					}else
						System.out.println("ERROR: tipo no definido en el Factory "+grupo.getTipoAleatorioRoboticas());

				}
			}
	}		
}
