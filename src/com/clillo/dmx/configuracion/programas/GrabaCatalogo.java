package com.clillo.dmx.configuracion.programas;

import java.util.ArrayList;
import java.util.HashMap;

import com.clillo.dmx.configuracion.ProgramasCfg;
import com.clillo.utiles.Archivos;

public class GrabaCatalogo {
	
	private static void procesaArchivo(String ruta, ArchivoProgramasCfg archivo){
		
		ArrayList<ProgramaTO> listaProgramas = archivo.getListaProgramas();
		
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
		sb.append("<programas>\n");
		
		boolean grabar = false;
		
		for (ProgramaTO programa: listaProgramas){
			grabar |= programa.isGrabar();
			sb.append("\t<programa id=\"").append(programa.getCorrelativo()).append("\">\n");
			sb.append("\t\t<nombre>").append(programa.getNombre()).append("</nombre>\n");
			sb.append("\t\t<velocidad>").append(programa.getVelocidad()).append("</velocidad>\n");
			sb.append("\t\t<velocidadMovimiento>").append(programa.getVelocidadMovimiento()).append("</velocidadMovimiento>\n");

			sb.append("\t\t<porcentajeVelocidad>\n");
			sb.append("\t\t\t<actual>").append(programa.getPorcentajeVelocidadActual()).append("</actual>\n");
			sb.append("\t\t\t<maximo>").append(programa.getPorcentajeVelocidadMaximo()).append("</maximo>\n");
			sb.append("\t\t\t<minimo>").append(programa.getPorcentajeVelocidadMinimo()).append("</minimo>\n");
	
			sb.append("\t\t</porcentajeVelocidad>\n");

			sb.append("\t\t<probabilidadGlobal>").append(programa.getParametros().getProbabilidadGlobal()).append("</probabilidadGlobal>\n");
			sb.append("\t\t<tiempoEncendidoMedia>").append(programa.getParametros().getTiempoEncendidoMedia()).append("</tiempoEncendidoMedia>\n");
			sb.append("\t\t<tiempoEncendidoDevSta>").append(programa.getParametros().getTiempoEncendidoDevSta()).append("</tiempoEncendidoDevSta>\n");
			sb.append("\t</programa>\n");			
		}
		
		sb.append("</programas>\n");
		
		if (grabar){
			System.out.println("Grabando "+ruta);
			Archivos.grabaArchivo(sb.toString(), ruta);
		}
		
	}

	public static void grabaTodos(){
		HashMap<String, ArchivoProgramasCfg> listaArchivos = ProgramasCfg.getListaArchivos();
		
		for (String archivo: listaArchivos.keySet()){
			//System.out.println(archivo+"\t"+listaArchivos.get(archivo));
			procesaArchivo("conf\\programas\\"+archivo+".catalogo.xml", listaArchivos.get(archivo));
		}
	}
	
	public static void main(String[] args) {
		grabaTodos();
		System.exit(0);
	}
}
