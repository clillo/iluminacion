package com.clillo.dmx.configuracion.fixtures;

import java.io.File;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import com.clillo.dmx.core.ListaFixtures;
import com.clillo.dmx.core.fixtures.FixtureRobotica;

public class RoboticasConfig {

	private static final File ARCHIVO = new File("conf/fixtures/roboticas.xml");
	private static XMLConfiguration configuracion;

	static{
		try {
			configuracion = new XMLConfiguration(ARCHIVO);
			configuracion.setSchemaValidation(false);
				
			List<HierarchicalConfiguration> listaFixtures = configuracion.configurationsAt("fixture");
			for (HierarchicalConfiguration fixture : listaFixtures) {
				String id = fixture.getString("[@id]");
				FixtureRobotica fix = (FixtureRobotica)ListaFixtures.getFixture(id);
				fix.setVentanaMinX(fixture.getInt("ventanaMinX"));
				fix.setVentanaMaxX(fixture.getInt("ventanaMaxX"));
				fix.setVentanaMinY(fixture.getInt("ventanaMinY"));
				fix.setVentanaMaxY(fixture.getInt("ventanaMaxY"));
			}

		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * No borrar este método, sirve para ejecutar el bloque estático que inicializa la clase
	 */
	public static void carga(){
		
	}
	
	public static void graba(){
		List<HierarchicalConfiguration> listaFixtures = configuracion.configurationsAt("fixture");
		for (HierarchicalConfiguration fixture : listaFixtures) {
			String id = fixture.getString("[@id]");
			FixtureRobotica fix = (FixtureRobotica)ListaFixtures.getFixture(id);
			fixture.setProperty("ventanaMinX", fix.getVentanaMinX());
			fixture.setProperty("ventanaMaxX", fix.getVentanaMaxX());
			fixture.setProperty("ventanaMinY", fix.getVentanaMinY());
			fixture.setProperty("ventanaMaxY", fix.getVentanaMaxY());
		}

		try {
			configuracion.save();
		} catch (ConfigurationException e) {
		}	
	}

	public static void main(String[] args) {
		
	}
}
