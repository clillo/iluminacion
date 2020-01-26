package com.clillo.dmx.core.ejecucion;

import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecucionAutomatica.PanelEjecucionAutomatica;
import com.clillo.utiles.Log;

public class EjecutorAutomatico extends Thread {
	
	private long siguienteEjecucion;
	private boolean enEjecucion;
	private PanelEjecucionAutomatica panel;
	
	public EjecutorAutomatico(PanelEjecucionAutomatica panel) {
		this.panel = panel;
	}
	
	public synchronized void ejecuta(boolean enEjecucion){		
		if (enEjecucion)
			Log.debug("Ejecutando Escenas Automáticas", this.getClass());
		else{
			Log.debug("Parando Escenas Automáticas", this.getClass());
		}
		if (panel.getEscenaActual()!=null){
			int deltaT = panel.getEscenaActual().getTiempoMaximo() - panel.getEscenaActual().getTiempoMinimo();
			siguienteEjecucion = System.currentTimeMillis() + panel.getEscenaActual().getTiempoMinimo() + (int)(Math.random() * deltaT);
		}
		this.enEjecucion = enEjecucion;
	}

	private synchronized void tic(){
		long actual = System.currentTimeMillis();

		if (enEjecucion){
			panel.getLblProximoEn().setText("Proximo en " + (-(actual-siguienteEjecucion)/1000 + 1) +" seg.");

			if (siguienteEjecucion<actual){
				panel.seleccionaAleatorio();
				int deltaT = panel.getEscenaActual().getTiempoMaximo() - panel.getEscenaActual().getTiempoMinimo();
				siguienteEjecucion = System.currentTimeMillis() + panel.getEscenaActual().getTiempoMinimo() + (int)(Math.random() * deltaT);	
			}
		}
	}
	
	@Override
	public void run() {
		while (true) {
			
			tic();
				
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
			}
		}
	}
}
