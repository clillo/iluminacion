package com.clillo.dmx.programas.pasos;

import java.util.ArrayList;
import java.util.HashMap;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.configuracion.programas.EfectoRoboticaTO;
import com.clillo.dmx.configuracion.programas.PasoTO;
import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.configuracion.programas.to.AleatorioTO;
import com.clillo.dmx.configuracion.programas.to.CanalValorTO;

public class EjecutorPasosGenerico implements EjecutorPasos {

	@Override
	public void ejecutaPaso(ProgramaTO programa){
		int valorAleatorio = -1;
		PasoTO paso = programa.siguientePaso();
		
		HashMap<String, Integer> hashAleatorios = new HashMap<>();
		ArrayList<AleatorioTO> listaAleatorios = paso.getListaAleatorios();
		if(listaAleatorios!=null){
			for (AleatorioTO to: listaAleatorios)
				hashAleatorios.put(to.getId(), to.siguienteValor());
		}

		ArrayList<CanalValorTO> listaValores = paso.getListaValores();
		if (listaValores!=null)
			for (CanalValorTO valor: listaValores)
				if (valor.isAleatorio()){
					Dmx.enviar(valor.getCanalDMX(), hashAleatorios.get(valor.getIdAleatorio()));
				}
				else
					Dmx.enviar(valor.getCanalDMX(), valor.getValorDMX());
		
		ArrayList<EfectoRoboticaTO> listaEfectosRobotica = paso.getListaEfectosRobotica();
		if (listaEfectosRobotica!=null)
			for (EfectoRoboticaTO efecto: listaEfectosRobotica){
				int valorDMX = efecto.getValorDMX();
				
				if (efecto.isAleatorio()){
					if (valorAleatorio<0)
						valorAleatorio = efecto.getValorAleatorio();
					valorDMX = valorAleatorio;
				}
				Dmx.enviar(efecto.getCanalDMX(), valorDMX);
			}		
	}
}
