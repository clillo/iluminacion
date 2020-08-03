package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlNode;
import com.amihaiemil.eoyaml.YamlSequence;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.NodoEscena.FixMovingHead;

public class ArchivoYaml {
	
	public List<Escena> leeListaEscenas() throws Exception{
		List<Escena> listaEscenas = new ArrayList<>();
		
		File listaArchivos[] = new File("conf/escenas/").listFiles();
			
		int i=0;
		for (File f: listaArchivos)
			if (f.getName().endsWith(".yml")){
				Escena e1 = new Escena();
				e1.setId(i);
				List<NodoEscena> listaTmp = leeLista(f.getAbsolutePath());
				e1.setListaNodos(listaTmp);
				listaEscenas.add(e1);
				i++;
			}

		return listaEscenas;
	}
	
	public void grabaListaEscenas(List<Escena> lista) throws Exception{
		for (Escena e: lista)
			grabaLista(e.getListaNodos(), "conf/escenas/posiciones"+(e.getId())+".yml");
	}
	
	
	public List<NodoEscena> leeLista(String archivo) throws Exception{
		YamlMapping team = Yaml.createYamlInput(
			    new File(archivo)
			).readYamlMapping();

		List<NodoEscena> listaPuntos = new ArrayList<>();
		YamlSequence lista = team.yamlSequence("puntos");

		for (YamlNode puntos: lista){
			YamlMapping punto = puntos.asMapping();
			for (YamlNode llave: punto.values()){
				YamlMapping datos = llave.asMapping();
				NodoEscena p = new NodoEscena();
				if (datos.string("movingHead").equals("MvHd1_90"))
					p.setMovingHead(FixMovingHead.MvHd1_90);
				if (datos.string("movingHead").equals("MvHd2_90"))
					p.setMovingHead(FixMovingHead.MvHd2_90);
				if (datos.string("movingHead").equals("MvHd1_60"))
					p.setMovingHead(FixMovingHead.MvHd1_60);
				if (datos.string("movingHead").equals("MvHd2_60"))
					p.setMovingHead(FixMovingHead.MvHd2_60);
				p.setNombre(datos.string("nombre"));
				p.setX(datos.integer("x"));
				p.setY(datos.integer("y"));
				p.setPan(datos.doubleNumber("pan"));
				p.setTilt(datos.doubleNumber("tilt"));
				p.setPan1(datos.integer("pan1"));
				p.setPan2(datos.integer("pan2"));
				p.setTilt1(datos.integer("tilt1"));
				p.setTilt2(datos.integer("tilt2"));
				p.setDimmer(datos.integer("dimmer"));
				listaPuntos.add(p);
			}
		}
		return listaPuntos;
	}
	
	public void grabaLista(List<NodoEscena> lista, String archivo) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("puntos:\n");
		for (NodoEscena p: lista){
			sb.append("  - punto:\n");
			sb.append("      movingHead: ").append(p.getMovingHead()).append("\n");
			sb.append("      nombre: ").append(p.getNombre()).append("\n");
			sb.append("      x: ").append(p.getX()).append("\n");
			sb.append("      y: ").append(p.getY()).append("\n");
			sb.append("      pan: ").append(p.getPan()).append("\n");
			sb.append("      pan1: ").append(p.getPan1()).append("\n");
			sb.append("      pan2: ").append(p.getPan2()).append("\n");
			sb.append("      tilt: ").append(p.getTilt()).append("\n");
			sb.append("      tilt1: ").append(p.getTilt1()).append("\n");
			sb.append("      tilt2: ").append(p.getTilt2()).append("\n");
			sb.append("      dimmer: ").append(p.getDimmer()).append("\n");
		}
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
	    writer.write(sb.toString());
	    
	    writer.close();
	}

	public static void main(String[] args) throws Exception {
		//List<NodoEscena> listaTmp = new ArchivoYaml().leeLista("");
		//new ArchivoYaml().grabaLista(listaTmp);
	}
}
