package com.clillo.dmx.core.show;

import java.util.ArrayList;

import com.clillo.utiles.Log;

public class EjecutorShow extends Thread {
	
	private ArrayList<EscenaAutomatico> lista;
	
	public EjecutorShow() {
		lista = ListaShow.getLista();
	}
	
	public synchronized void ejecuta(boolean enEjecucion, EscenaAutomatico show){		
		if (enEjecucion)
			Log.debug("Ejecutando Show "+show.getNombre(), this.getClass());
		else{
			Log.debug("Parando Show "+show.getNombre(), this.getClass());
			show.apaga();
		}
		show.setSiguienteEjecucion(System.currentTimeMillis());		
		show.setEnEjecucion(enEjecucion);
	}

	private synchronized void tic(){
		long actual = System.currentTimeMillis();
		
		for (int n=0; n<lista.size(); n++){
			EscenaAutomatico show = lista.get(n);
			
			if (show.isEnEjecucion()){
				if (show.getSiguienteEjecucion()<actual){
					show.envia();
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
}
