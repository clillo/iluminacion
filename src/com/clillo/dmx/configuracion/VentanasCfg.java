package com.clillo.dmx.configuracion;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import com.clillo.dmx.ui.jframes.FrameInterno;

public class VentanasCfg {

	private static final String ARCHIVO ="conf/generales/ventanas.xml";
	private static XMLConfiguration configuracion;
	
	static {
		try{
			configuracion = new XMLConfiguration(ARCHIVO);
			configuracion.setSchemaValidation(false);
		} catch (Exception ce) {
			ce.printStackTrace();
		}
	}
	
	
	public static boolean getPanelVisibilidad(String panelId){
		List<HierarchicalConfiguration> paneles = configuracion.configurationsAt("paneles.panel");
		for (HierarchicalConfiguration panel : paneles) 
			if (panel.getString("[@id]").equals(panelId))
				return  panel.getBoolean("visible");
		
		return false;
	}

	
	public static void getPanelPos(String panelId, FrameInterno mit){
		List<HierarchicalConfiguration> paneles = configuracion.configurationsAt("paneles.panel");
		for (HierarchicalConfiguration panel : paneles) 
			if (panel.getString("[@id]").equals(panelId)){
				int x = panel.getInt("posX");
				int y = panel.getInt("posY");
				mit.setLocation(x, y);
			}
	}

	public static void setPanelPos(String panelId, FrameInterno mit){
		if (mit==null)
			return;
	
		List<HierarchicalConfiguration> paneles = configuracion.configurationsAt("paneles.panel");
		for (HierarchicalConfiguration panel : paneles) 
			if (panel.getString("[@id]").equals(panelId)){
				panel.setProperty("posX", (int)mit.getLocation().getX());
				panel.setProperty("posY", (int)mit.getLocation().getY());
				panel.setProperty("visible", mit.isVisible());
			}
		try {
			configuracion.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
}