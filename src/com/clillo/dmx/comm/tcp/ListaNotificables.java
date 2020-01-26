package com.clillo.dmx.comm.tcp;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaNotificables {

	private HashMap<String, ArrayList<HTTPEnviable>> enviables;
 
	public ListaNotificables() {
		enviables = new HashMap<String, ArrayList<HTTPEnviable>>();
	}

	public void enviar(String ip, String mensaje) {
		ArrayList<HTTPEnviable> lista = enviables.get(ip);
		
		if (lista == null)
			return;
		
		for(HTTPEnviable e: lista)
			e.actualizaEnvioHTTP(mensaje);
	}
	
	public void registraEnviable(HTTPEnviable enviable, String ip){
		ArrayList<HTTPEnviable> lista = enviables.get(ip);
		
		if (lista == null){
			lista = new ArrayList<>();
			enviables.put(ip, lista);	
		}
		
		lista.add(enviable);
	}

}
