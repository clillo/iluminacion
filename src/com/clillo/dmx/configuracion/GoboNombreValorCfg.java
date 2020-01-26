package com.clillo.dmx.configuracion;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import com.clillo.dmx.core.fixtures.robotizados.GoboNombreValor;

public class GoboNombreValorCfg {

	private static HashMap<String, ArrayList<GoboNombreValor>> lista;
	private static final File ARCHIVO = new File("conf/fixtures/valoresEfectoRoboticas.xml");

	private static XMLConfiguration configuracion;

	static{
		lista = new HashMap<String, ArrayList<GoboNombreValor>>();
		try{
			configuracion = new XMLConfiguration(ARCHIVO);
			configuracion.setSchemaValidation(false);
			
			List<HierarchicalConfiguration> categorias = configuracion.configurationsAt("categoria");
			for (HierarchicalConfiguration categoria : categorias){
				String cat = categoria.getString("[@id]");

				ArrayList<GoboNombreValor> tmp = new ArrayList<GoboNombreValor>();
				lista.put(cat, tmp);

				ArrayList<String> listaCanales = new ArrayList<String>();
				List<HierarchicalConfiguration> canales = categoria.configurationsAt("canal");
				for (HierarchicalConfiguration canal : canales)
					listaCanales.add(canal.getString("[@id]"));

				List<HierarchicalConfiguration> valores = categoria.configurationsAt("valor");
				for (HierarchicalConfiguration valor : valores){
					String id = valor.getString("[@id]");
				
					GoboNombreValor gnv = new GoboNombreValor();
					gnv.setId(id);
					gnv.setNombre(valor.getString("descripcion"));
					gnv.setValor(valor.getInt("valorDMX"));
					gnv.setListaCanales(listaCanales);
					tmp.add(gnv);
				}
			}
							
		} catch (Exception ce) {
			ce.printStackTrace();
		}
	}
	
	public static ArrayList<GoboNombreValor> getListaGoboNombreValor(String categoria){
		return lista.get(categoria);
	}

	public static HashMap<String, ArrayList<GoboNombreValor>> getLista() {
		return lista;
	}
}
