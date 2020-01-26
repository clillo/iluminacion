package com.clillo.dmx.programas.pasos;

import com.clillo.dmx.configuracion.programas.ProgramaTO;

public class EjecutorPasosEscena implements EjecutorPasos {

	@Override
	public void ejecutaPaso(ProgramaTO programa){
		//System.out.println("Ejecutando paso "+programa);
		programa.getEscena().setSiguienteEjecucion();
	}
}
