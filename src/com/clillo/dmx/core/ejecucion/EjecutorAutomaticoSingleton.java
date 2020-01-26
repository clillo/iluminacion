package com.clillo.dmx.core.ejecucion;

import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecucionAutomatica.PanelEjecucionAutomatica;

public class EjecutorAutomaticoSingleton {

	private static EjecutorAutomatico ejecutor;
	
	
	public static EjecutorAutomatico getEjecutor(PanelEjecucionAutomatica panel){
		if (ejecutor==null){
			ejecutor = new EjecutorAutomatico(panel);
			ejecutor.setName("Thread Ejecutor Automático Fixtures");
			ejecutor.start();
		}
		return ejecutor;
	}
}
