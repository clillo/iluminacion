package com.clillo.dmx.configuracion.escenas;

import java.util.ArrayList;
import java.util.HashMap;

import com.clillo.dmx.core.escenas.Escena;
import com.clillo.dmx.core.escenas.EscenaCanal;
import com.clillo.dmx.core.escenas.EscenaCanalPosicionRoboticas;
import com.clillo.dmx.core.escenas.EscenaRobotica;

public class ConversorRoboticasCanales {

	private HashMap<Integer, ArrayList<String>> hashCanales = new HashMap<Integer, ArrayList<String>>();

	public void convierte(Escena e){
		if (e.getListaRoboticas().size()==0)
			return;
		
		ArrayList<EscenaCanal> listaCanales = new ArrayList<EscenaCanal>();

		for (EscenaRobotica er: e.getListaRoboticas()){
			ArrayList<String> canalesPorId = new ArrayList<String>();
			EscenaCanal ec = agregaCanal(er.getId(), er.getNombre()+".pan");
			listaCanales.add(ec);
			canalesPorId.add(ec.getCanalStr());
			
			ec = agregaCanal(er.getId(), er.getNombre()+".tilt");
			listaCanales.add(ec);
			canalesPorId.add(ec.getCanalStr());

			if (er.getNombre().startsWith("moving")){				
				ec = agregaCanal(er.getId(), er.getNombre()+".panfine");
				listaCanales.add(ec);
				canalesPorId.add(ec.getCanalStr());
				ec = agregaCanal(er.getId(), er.getNombre()+".tiltfine");
				listaCanales.add(ec);
				canalesPorId.add(ec.getCanalStr());
			}
			
			hashCanales.put(er.getId(), canalesPorId);
		}
		
		e.setListaCanales(listaCanales);
		//for(EscenaCanal ec: e.getListaCanales()){
		//	System.out.println(ec);
		//}
	}
	
	private EscenaCanal agregaCanal(int correlativo, String valor){
		EscenaCanal ec = new EscenaCanal();
		ec.setId(correlativo);
		ec.setCanalStr(valor);
		return ec;
	}
	
	public ArrayList<EscenaCanal> getCanales(int canal, String valorStr){
		
		ArrayList<EscenaCanal> listaCanales = new ArrayList<>();
		ArrayList<String> canalesPorId = hashCanales.get(canal);
		//System.out.println(canal+"\t"+canalesPorId);
		for (String str: canalesPorId)
			listaCanales.add(new EscenaCanalPosicionRoboticas(str, valorStr));
		
		return listaCanales;
	}
}
