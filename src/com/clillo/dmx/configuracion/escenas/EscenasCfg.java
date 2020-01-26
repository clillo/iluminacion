package com.clillo.dmx.configuracion.escenas;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import com.clillo.dmx.core.escenas.Escena;

public class EscenasCfg {

	private static HashMap<String, ArchivoEscenasCfg> listaArchivos = new HashMap<String, ArchivoEscenasCfg>();
	private static ArrayList<Escena> listaEscenas = new ArrayList<Escena>(); 
	
	static {
		procesaDirectorio("conf\\escenas\\");
	}
	
	public static void procesaDirectorio(String directorio){
		File f = new File(directorio);
		File lista[] = f.listFiles();
		
		if (lista==null)
			System.out.println("No se encuentra el directorio de trabajo: "+directorio);
		
		for (File archivo: lista){
			String nombreSecuencia = archivo.getName();
			int pos = nombreSecuencia.indexOf('.');
			if (nombreSecuencia.endsWith(".xml")){
				nombreSecuencia = nombreSecuencia.substring(0, pos);
			//	System.out.println(nombreSecuencia);
				ArchivoEscenasCfg a = new ArchivoEscenasCfg(nombreSecuencia);
				listaArchivos.put(nombreSecuencia, a);
				listaEscenas.add(a.getEscena());
			}
		}	
	}
	
	public static ArrayList<Escena> getListaEscenas(){		
		return listaEscenas;
	}

	public static void grabaCambios(Escena e){
		ArchivoEscenasCfg archivo = listaArchivos.get(e.getNombreArchivo());
		archivo.grabar();
	}
}
