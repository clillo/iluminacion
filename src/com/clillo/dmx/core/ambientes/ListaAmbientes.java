package com.clillo.dmx.core.ambientes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

public class ListaAmbientes {

	private static final File ARCHIVO = new File("conf/ambientes/lista.xml");

	private static ArrayList<Ambiente> lista;
	
	public static ArrayList<Ambiente> getLista() {
		return lista;
	}

	public static Ambiente[] obtieneLista(){
		Ambiente[] s = new Ambiente[lista.size()];
		
		int i=0;
		for (Ambiente ss: lista)
			s[i++] = ss;
		return s;
	}
	
	static{
		try {
			XMLConfiguration configuracion;
			configuracion = new XMLConfiguration(ARCHIVO);
			configuracion.setSchemaValidation(false);
			
			List<HierarchicalConfiguration> archivos = configuracion.configurationsAt("archivo");

			lista = new ArrayList<Ambiente>();

			for (HierarchicalConfiguration archivo : archivos)
				lista.add(creaAmbiente(archivo.getString("")));

		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	private static Ambiente creaAmbiente(String nombreArchivo){
		Ambiente s = new Ambiente();
		try {
			File archivo = new File("conf/ambientes/"+nombreArchivo+".xml");
			XMLConfiguration configuracion = new XMLConfiguration(archivo);
			configuracion.setSchemaValidation(false);
			
			String nombre = configuracion.getString("generales.nombre");
			
			s.setNombre(nombre);
					
			List<HierarchicalConfiguration> fixtures = configuracion.configurationsAt("fixtures.fixture");
					
			ArrayList<AmbienteDetalle> listaDetalle = new ArrayList<AmbienteDetalle>();
			s.setLista(listaDetalle);
			
			for (HierarchicalConfiguration fixture : fixtures){
				String idFixture = fixture.getString("[@id]");
				String efectoFixture = fixture.getString("");
				
				AmbienteDetalle detalle = new AmbienteDetalle();
				detalle.setIdFixture(idFixture);
				detalle.setEfectoFixture(efectoFixture);
				
				listaDetalle.add(detalle);
			}

		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return s;
	}

}
