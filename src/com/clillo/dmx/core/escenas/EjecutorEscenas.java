package com.clillo.dmx.core.escenas;

import com.clillo.utiles.Log;

public class EjecutorEscenas extends Thread {

	private Escena escena;
	
	protected EjecutorEscenas() {
	}
	
	public synchronized void ejecuta(boolean enEjecucion, Escena escena){		
		if (enEjecucion){
			this.escena = escena;
			Log.debug("Ejecutando Escena " + escena.getTitulo(), this.getClass());
			escena.setSiguienteEjecucion(System.currentTimeMillis());		
		}
		else{
			Log.debug("Parando Escena " + escena.getTitulo(), this.getClass());
			this.escena = null;
		}
	}

	private synchronized void tic(){
		long actual = System.currentTimeMillis();
		
		if (escena!=null){
			
			if (escena.getSiguienteEjecucion()<actual)
				escena.setSiguienteEjecucion();			
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
}
