package com.clillo.dmx.core;

import java.util.ArrayList;
import java.util.HashMap;

import com.clillo.dmx.configuracion.FixturesCfg;
import com.clillo.dmx.configuracion.fixtures.RoboticasConfig;
import com.clillo.dmx.configuracion.programas.to.CanalValorTO;
import com.clillo.dmx.core.fixtures.Fixture;

public class ListaFixtures {

	private static HashMap<String, Fixture> lista;
	private static HashMap<String, Integer> listaCanales;

	static{
		lista = new HashMap<String, Fixture>();
		listaCanales = new HashMap<String, Integer>();
		FixturesCfg.carga();
		RoboticasConfig.carga();
	}
	
	public static void addCanal(String nombreCanal, int idDMX){
		listaCanales.put(nombreCanal, idDMX);
	}
	
	public static void addFixture(Fixture fix){
		lista.put(fix.getFixId(), fix);
	}
	
	public static Fixture getFixture(String id){
		return lista.get(id);
	}
	
	public static ArrayList<Fixture> obtieneListadoFixtures(){
		 ArrayList<Fixture> listaF = new  ArrayList<Fixture>();
		 for (Fixture fix: lista.values())
			 listaF.add(fix);
		 
		 return listaF;
	}
	
	public static Fixture obtieneFixtureDadoCanal(int canalDMX){
		 for (Fixture fix: lista.values())
			for(CanalValorTO canal: fix.getListaCanales())
				if(canal.getCanalDMX()==canalDMX)
					return fix;
		 
		return null;
	}
	
	public static int obtieneCanalDMX(String nombreCanal){
		if (!listaCanales.containsKey(nombreCanal))
			System.err.println("Cueck: Canal no existe "+nombreCanal);
		return listaCanales.get(nombreCanal);
	}
}
