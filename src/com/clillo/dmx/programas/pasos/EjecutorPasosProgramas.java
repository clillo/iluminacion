package com.clillo.dmx.programas.pasos;

import java.util.ArrayList;
import java.util.HashMap;

import com.clillo.dmx.configuracion.programas.PasoTO;
import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.configuracion.programas.to.PasoProgramaTO;
import com.clillo.dmx.configuracion.programas.to.PasoProgramaTO.TipoRandom;

public class EjecutorPasosProgramas implements EjecutorPasos {
	
	private HashMap<Integer, Integer> hashIds = new HashMap<>();

	@Override
	public void ejecutaPaso(ProgramaTO programa){
		//System.out.println("Ejecutando paso "+programa);
		PasoTO paso = programa.siguientePaso();
		boolean primerPaso = false;
		
		if (programa.isEjecutaPrimerPaso()){
			primerPaso = true;
			programa.setEjecutaPrimerPaso(false);
		}
			
		ArrayList<PasoProgramaTO> listaProgramas = paso.getListaProgramas();
		if (listaProgramas!=null)
			for (PasoProgramaTO pasoPrograma: listaProgramas)
				if (pasoPrograma.isNingunProgramaSeleccionado())
					pasoPrograma.getPanelCambio().seleccionaNingunPrograma();
				else{
				//	System.out.println("Nuleando gatillador programa "+prg.getPrograma());
					if (pasoPrograma.getListaProgramas()!=null){
						int idProgramaEjecutando = -1;
						
						if(hashIds.containsKey(pasoPrograma.getIdInterno()))
							idProgramaEjecutando = hashIds.get(pasoPrograma.getIdInterno());
						
//						System.out.println(prg.getListaProgramas().get(0));

						if (pasoPrograma.getTipoRandom() == TipoRandom.PrimerPaso){
							if (idProgramaEjecutando == -1 || primerPaso)
								idProgramaEjecutando = (int)(Math.random()*pasoPrograma.getListaProgramas().size());
						}
						
						if (pasoPrograma.getTipoRandom() == TipoRandom.PasoAPaso)
							idProgramaEjecutando = (int)(Math.random()*pasoPrograma.getListaProgramas().size());
						
						hashIds.put(pasoPrograma.getIdInterno(), idProgramaEjecutando);
						
						pasoPrograma.getListaProgramas().get(idProgramaEjecutando).setTipoGatillador(null);
						pasoPrograma.getPanelCambio().seleccionaProgramaDesdeEjecutorDePasos(pasoPrograma.getListaProgramas().get(idProgramaEjecutando));
					}else {
						pasoPrograma.getPrograma().setTipoGatillador(null);
						pasoPrograma.getPanelCambio().seleccionaProgramaDesdeEjecutorDePasos(pasoPrograma.getPrograma());
					}
				}
	}
}
