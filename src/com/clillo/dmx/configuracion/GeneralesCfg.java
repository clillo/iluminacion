package com.clillo.dmx.configuracion;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

import com.clillo.dmx.programas.conversorCanal.ConversorCanalDimmerRGBW;
import com.clillo.dmx.ui.jpanels.fixtures.laser.PanelObservadorLaserIlda1;

public class GeneralesCfg {

	private static final File ARCHIVO = new File("conf/generales/general.xml");

	private static XMLConfiguration configuracion;
	private static boolean habilitadoVDJ;
	
	static {
		try{
			configuracion = new XMLConfiguration(ARCHIVO);
			configuracion.setSchemaValidation(false);
			
			int porcentaje = configuracion.getInt("valorGeneralDimmerRGBW");
			int sensibilidadLaserIlda = configuracion.getInt("sensibilidadLaserIlda");
			habilitadoVDJ = configuracion.getBoolean("habilitadoVDJ");
			
			PanelObservadorLaserIlda1.sesibilidadLaserIlda = sensibilidadLaserIlda;
			ConversorCanalDimmerRGBW.setFactorDimmer(porcentaje/100.0);
			
		} catch (Exception ce) {
			ce.printStackTrace();
		}
	}
	
	public static void carga(){
		
	}

	public static void grabaFactorDimmerRGBW(int valor){
		configuracion.setProperty("valorGeneralDimmerRGBW", valor);
		
		try {
			configuracion.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void grabaSensibilidadLaserIlda(int valor){
		configuracion.setProperty("sensibilidadLaserIlda", valor);
		
		try {
			configuracion.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void grabaHabilitadoVDJ(boolean valor){
		habilitadoVDJ = valor;
		configuracion.setProperty("habilitadoVDJ", valor);
		
		try {
			configuracion.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isHabilitadoVDJ() {
		return habilitadoVDJ;
	}

	public static void main(String[] args) {
		
	}
}
