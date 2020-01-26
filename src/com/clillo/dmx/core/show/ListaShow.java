package com.clillo.dmx.core.show;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

public class ListaShow {

	private static ArrayList<EscenaAutomatico> lista;
	
	public static ArrayList<EscenaAutomatico> getLista() {
		return lista;
	}

	public static EscenaAutomatico[] obtieneLista(){
		EscenaAutomatico[] s = new EscenaAutomatico[lista.size()];
		
		int i=0;
		for (EscenaAutomatico ss: lista)
			s[i++] = ss;
		return s;
	}
	
	static{
		File archivos[] = new File("conf/show").listFiles();
		
		lista = new ArrayList<EscenaAutomatico>();

		for (File archivo : archivos)
			lista.add(creaShow(archivo.getAbsolutePath()));
	}
	
	private static EscenaAutomatico creaShow(String nombreArchivo){
		EscenaAutomatico s = new EscenaAutomatico();
		try {
			File archivo = new File(nombreArchivo);
			XMLConfiguration configuracion = new XMLConfiguration(archivo);
			configuracion.setSchemaValidation(false);
			
			String nombre = configuracion.getString("generales.nombre");
			
			s.setNombre(nombre);
			
			List<HierarchicalConfiguration> apagar = configuracion.configurationsAt("apagar.canal");
			
			ShowDetalle detalleApagar = new ShowDetalle();

			ArrayList<ShowParValor> lista = new ArrayList<ShowParValor>();
			for (HierarchicalConfiguration par : apagar)
				lista.add(new ShowParValor(par.getString("[@id]"), par.getInt("")));
			
			detalleApagar.setNombre("apagar");
			detalleApagar.setLista(lista);
			s.setApagar(detalleApagar);

			List<HierarchicalConfiguration> pasos = configuracion.configurationsAt("pasos.paso");
			
			ArrayList<ShowDetalle> listaDetalle = new ArrayList<ShowDetalle>();
			s.setLista(listaDetalle);
			
			for (HierarchicalConfiguration paso : pasos){
				long tiempo = paso.getInt("tiempo");
				
				ShowDetalle detalle = new ShowDetalle();
				ArrayList<ShowPrograma> listaProgramas = new ArrayList<ShowPrograma>();
				detalle.setSiguienteEjecucion(tiempo);
				lista = new ArrayList<ShowParValor>();

				detalle.setLista(lista);
				detalle.setListaProgramas(listaProgramas);

				List<HierarchicalConfiguration> canales = paso.configurationsAt("canales.canal");
				
				for (HierarchicalConfiguration canal : canales)
					lista.add(new ShowParValor(canal.getString("[@id]"), canal.getInt("")));
				
				List<HierarchicalConfiguration> programas = paso.configurationsAt("programas.programa");
				for (HierarchicalConfiguration programa : programas)
					listaProgramas.add(new ShowPrograma(programa.getString("[@tipo]"), programa.getString("[@id]")));

				listaDetalle.add(detalle);
			}

		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return s;
	}

}
