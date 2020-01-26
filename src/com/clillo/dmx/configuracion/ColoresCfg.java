package com.clillo.dmx.configuracion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import com.clillo.dmx.catalogo.ColorTO;

public class ColoresCfg {

	private static final File ARCHIVO_SUAVIZADO = new File("conf/colores/coloresSuavizado.xml");
	private static XMLConfiguration configuracionSuavizado;
	
	private static ArrayList<ColorTO> coloresSuavizado;

	static {
		try{
			leeColoresSuavizado();
		} catch (Exception ce) {
			ce.printStackTrace();
		}
	}

	private static void leeColoresSuavizado() throws ConfigurationException{
		coloresSuavizado = new ArrayList<ColorTO>();
		configuracionSuavizado = new XMLConfiguration(ARCHIVO_SUAVIZADO);
		configuracionSuavizado.setSchemaValidation(false);
				
		List<HierarchicalConfiguration> colores = configuracionSuavizado.configurationsAt("color");
		for (HierarchicalConfiguration color : colores){
			int id = color.getInt("[@id]");
			String hue = color.getString("hue");
			int r = color.getInt("r");
			int g = color.getInt("g");
			int b = color.getInt("b");
		//	System.out.println(id+"\t"+hue+"\t"+r+"\t"+g+"\t"+b);
			ColorTO c = new ColorTO(id, r, g, b);
			c.setNombre(hue);
			coloresSuavizado.add(c);
		}
	}
		
	public static ArrayList<ColorTO> getColoresSuavizado() {
		return coloresSuavizado;
	}

	public static void main(String[] args) {
		
	}
}
