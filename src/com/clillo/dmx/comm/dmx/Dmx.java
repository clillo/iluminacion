package com.clillo.dmx.comm.dmx;

import com.clillo.dmx.comm.Configuracion;

public class Dmx {
	
	private static ListaNotificables listaNotificables;

    private static ComunicacionHttpDMX dmx;
    
    static{
    	listaNotificables = new ListaNotificables();

		if (Configuracion.estaComunicacionHabilitada())
			dmx = new ComunicacionHttpDMX();
	}

	public static void stop(){
		if (dmx!=null)
			dmx.stop();
	}
	
	public static void enviar(int canal, int dato){	
		listaNotificables.enviar(canal, dato);
			
		if (dmx==null)
			return;
		
		dmx.enviar(canal, dato);		
	}	
	
	public static void registraEnviable(DmxEnviable enviable){
		listaNotificables.registraEnviable(enviable);
	}
	
	public static int obtieneValorActualCanal(int canal){
		return listaNotificables.obtieneValorActualCanal(canal);
	}
}
