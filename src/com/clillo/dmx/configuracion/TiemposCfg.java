package com.clillo.dmx.configuracion;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

public class TiemposCfg {

	private static XMLConfiguration configuracion;
	
	static {
		try{
			configuracion = new XMLConfiguration("conf/generales/tiempos.xml");
			configuracion.setSchemaValidation(false);					
		} catch (Exception ce) {
			ce.printStackTrace();
		}
	}

	public static int getTiempoCambioAutomatico(String id){
		//System.out.println(id);
		List<HierarchicalConfiguration> relojes = configuracion.configurationsAt("cambiosAutomaticos.reloj");
		for (HierarchicalConfiguration reloj : relojes) 
			if (reloj.getString("[@id]").equals(id))
				return reloj.getInt("tiempo");
			
		return 0;
	}
	
	public static void setTiempoCambioAutomatico(String id, int tiempo){
		List<HierarchicalConfiguration> relojes = configuracion.configurationsAt("cambiosAutomaticos.reloj");
		for (HierarchicalConfiguration reloj : relojes) 
			if (reloj.getString("[@id]").equals(id))
				reloj.setProperty("tiempo", tiempo);
		
		try {
			configuracion.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
}
