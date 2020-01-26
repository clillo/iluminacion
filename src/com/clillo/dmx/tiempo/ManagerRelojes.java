package com.clillo.dmx.tiempo;

import java.util.ArrayList;

import com.clillo.utiles.HoraUtil;

public class ManagerRelojes implements Runnable{

	private ArrayList<RelojTO> listaRelojes;
	
	public ManagerRelojes() {
		listaRelojes = new ArrayList<RelojTO>();
		Thread th = new Thread(this);
		th.start();
	}
	
	public synchronized void agregaReloj(RelojTO reloj){
		reloj.setSiguienteEjecucion(System.currentTimeMillis());	
		listaRelojes.add(reloj);
	}
	
	private synchronized void tic(){
		long actual = System.currentTimeMillis();
		
		for (RelojTO reloj: listaRelojes)
			if (reloj.isActivo()){
				long falta = reloj.getSiguienteEjecucion() - actual;

				String str = HoraUtil.toString(falta+1000);
				
				//reloj.getGatillable().actualizaTiempo(str);
				reloj.getTxtInterfaz().setText(str);
				//reloj.getTxtInterfaz().setToolTipText("Faltan "+str);

				if (falta<=0){
					long siguienteEjecucion = actual + reloj.getMiliSegundos();
					reloj.setSiguienteEjecucion(siguienteEjecucion);	
					reloj.getGatillable().tic(null);
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
