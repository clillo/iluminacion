package com.clillo.dmx.configuracion.escenas;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import com.clillo.dmx.ui.jpanels.opcionesMenu.escenas.PuntoRoboticas;

public class PuntosRoboticasCfg {

	private static XMLConfiguration conf;
	private static ArrayList<PuntoRoboticas> listaPuntos;

	static {
		try{
			String strArchivo = "conf/fixtures/puntosRoboticas.xml";
			
			conf = new XMLConfiguration(strArchivo);
			conf.setSchemaValidation(false);
				
			listaPuntos = new ArrayList<PuntoRoboticas>();
			
			List<HierarchicalConfiguration> puntos = conf.configurationsAt("punto");
			for (HierarchicalConfiguration punto : puntos){
				String id = punto.getString("[@id]");
				
				PuntoRoboticas ec = new PuntoRoboticas();
				ec.setId(id);
				
				ec.setMovingHead1Pan(punto.getInt("movingHead1Pan"));
				ec.setMovingHead1Tilt(punto.getInt("movingHead1Tilt"));
				ec.setMovingHead1PanFine(punto.getInt("movingHead1PanFine"));
				ec.setMovingHead1TiltFine(punto.getInt("movingHead1TiltFine"));
				ec.setMovingHead2Pan(punto.getInt("movingHead2Pan"));
				ec.setMovingHead2Tilt(punto.getInt("movingHead2Tilt"));
				ec.setMovingHead2PanFine(punto.getInt("movingHead2PanFine"));
				ec.setMovingHead2TiltFine(punto.getInt("movingHead2TiltFine"));
				ec.setScanner1Pan(punto.getInt("scanner1Pan"));
				ec.setScanner1Tilt(punto.getInt("scanner1Tilt"));
				ec.setScanner2Pan(punto.getInt("scanner2Pan"));
				ec.setScanner2Tilt(punto.getInt("scanner2Tilt"));
				ec.setScanner3Pan(punto.getInt("scanner3Pan"));
				ec.setScanner3Tilt(punto.getInt("scanner3Tilt"));
				ec.setScanner4Pan(punto.getInt("scanner4Pan"));
				ec.setScanner4Tilt(punto.getInt("scanner4Tilt"));
				
				//System.out.println(ec);
				listaPuntos.add(ec);
			}
		} catch (Exception ce) {
			ce.printStackTrace();
		}
	}
				
	public static ArrayList<PuntoRoboticas> getListaPuntos() {
		return listaPuntos;
	}

	public static void grabar(){	
		List<HierarchicalConfiguration> puntos = conf.configurationsAt("punto");
		for (HierarchicalConfiguration punto : puntos){
			String id = punto.getString("[@id]");
			for(PuntoRoboticas ec: listaPuntos)
				if (ec.getId().equals(id)){
					punto.setProperty("movingHead1Pan", ec.getMovingHead1Pan());
					punto.setProperty("movingHead1Tilt", ec.getMovingHead1Tilt());
					punto.setProperty("movingHead1PanFine", ec.getMovingHead1PanFine());
					punto.setProperty("movingHead1TiltFine", ec.getMovingHead1TiltFine());

					punto.setProperty("movingHead2Pan", ec.getMovingHead2Pan());
					punto.setProperty("movingHead2Tilt", ec.getMovingHead2Tilt());
					punto.setProperty("movingHead2PanFine", ec.getMovingHead2PanFine());
					punto.setProperty("movingHead2TiltFine", ec.getMovingHead2TiltFine());

					punto.setProperty("scanner1Pan", ec.getScanner1Pan());
					punto.setProperty("scanner1Tilt", ec.getScanner1Tilt());
					punto.setProperty("scanner2Pan", ec.getScanner2Pan());
					punto.setProperty("scanner2Tilt", ec.getScanner2Tilt());
					punto.setProperty("scanner3Pan", ec.getScanner3Pan());
					punto.setProperty("scanner3Tilt", ec.getScanner3Tilt());
					punto.setProperty("scanner4Pan", ec.getScanner4Pan());
					punto.setProperty("scanner4Tilt", ec.getScanner4Tilt());
					continue;
				}
		}

		try {
			conf.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
	}
}
