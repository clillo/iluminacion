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

public class ArchivoEncendidoYaml {
	
	public List<Escena> leeListaPosicionesEscenas() throws Exception{
		List<Escena> listaEscenas = new ArrayList<>();
		
		File listaArchivos[] = new File("conf/escenas/").listFiles();
			
		int i=0;
		for (File f: listaArchivos)
			if (f.getName().startsWith("encendido") && f.getName().endsWith(".yml")){
				Escena e1 = new Escena();
				e1.setId(i+"");
				List<NodoEscena> listaTmp = leeLista(f.getAbsolutePath());
				e1.setListaNodos(listaTmp);
				listaEscenas.add(e1);
				i++;
			}

		return listaEscenas;
	}
	
	public void grabaListaEncendidoEscenas(List<Escena> lista) throws Exception{
		for (Escena e: lista)
			grabaLista(e.getListaNodos(), "conf/escenas/encendido"+(e.getId())+".yml");
	}
	
	
	private List<NodoEscena> leeLista(String archivo) throws Exception{
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
				p.setDimmer(datos.integer("dimmer"));
				p.setColor(datos.integer("color"));
				p.setGobo(datos.integer("gobo"));
				p.setGoboCristal(datos.integer("goboCristal"));
				p.setGoboRotation(datos.integer("goboRotation"));
				p.setGoboShake(datos.integer("goboShake"));
				p.setStrobo(datos.integer("strobo"));
				p.setPrisma(datos.integer("prisma"));
				p.setFocus(datos.integer("focus"));

				listaPuntos.add(p);
			}
		}
		return listaPuntos;
	}
	
	private void grabaLista(List<NodoEscena> lista, String archivo) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("puntos:\n");
		for (NodoEscena p: lista){
			sb.append("  - punto:\n");
			sb.append("      movingHead: ").append(p.getMovingHead()).append("\n");
			sb.append("      nombre: ").append(p.getNombre()).append("\n");
			sb.append("      dimmer: ").append(p.getDimmer()).append("\n");
			sb.append("      color: ").append(p.getColor()).append("\n");
			sb.append("      gobo: ").append(p.getGobo()).append("\n");
			sb.append("      goboCristal: ").append(p.getGoboCristal()).append("\n");
			sb.append("      goboRotation: ").append(p.getGoboRotation()).append("\n");
			sb.append("      goboShake: ").append(p.getGoboShake()).append("\n");
			sb.append("      strobo: ").append(p.getStrobo()).append("\n");
			sb.append("      prisma: ").append(p.getPrisma()).append("\n");
			sb.append("      focus: ").append(p.getFocus()).append("\n");
		}
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
	    writer.write(sb.toString());
	    
	    writer.close();
	}

}
