package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.NodoEscena.FixMovingHead;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.mhpositions.MHPositionNode;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.mhpositions.MHPositionNodeElement;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.mhpositions.MHPositionsConfig;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.mhpositions.MHPositionsList;

public class ArchivoPosicionesYaml {

	public List<Escena> leeListaPosicionesEscenas() throws Exception{
		List<Escena> listaEscenas = new ArrayList<>();
		List<MHPositionsList> positionsList = new MHPositionsConfig().getMHPositionsLists();
		
		for (MHPositionsList pos: positionsList){
			List<NodoEscena> listaPuntos = new ArrayList<>();

			Escena e1 = new Escena();
			e1.setId(pos.getId());
			e1.setListaNodos(listaPuntos);
			listaEscenas.add(e1);
			for(MHPositionNodeElement list: pos.getPositions()){
				MHPositionNode position = list.getPosition();
				
				NodoEscena nodoEscena = new NodoEscena();
				
				if (position.getFixture().equals("mh-90-1"))
					nodoEscena.setMovingHead(FixMovingHead.mh_90_1);
				if (position.getFixture().equals("mh-90-2"))
					nodoEscena.setMovingHead(FixMovingHead.mh_90_2);
				if (position.getFixture().equals("mh-60-1"))
					nodoEscena.setMovingHead(FixMovingHead.mh_60_1);
				if (position.getFixture().equals("mh-60-2"))
					nodoEscena.setMovingHead(FixMovingHead.mh_60_2);
				
				nodoEscena.setPan1(position.getPan());
				nodoEscena.setPan2(position.getPanFine());
				
				nodoEscena.setTilt1(position.getTilt());
				nodoEscena.setTilt2(position.getTiltFine());

				listaPuntos.add(nodoEscena);
			}
		}
		
		return listaEscenas;
	}
	
	
	public void grabaListaPosicionesEscenas(List<Escena> lista) throws Exception{
		for (Escena e: lista)
			grabaLista(e.getListaNodos(), "conf/escenas/spring/pos-"+(e.getId())+".yml");
	}
	
	
	private void grabaLista(List<NodoEscena> lista, String archivo) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("positions:\n");
		for (NodoEscena p: lista){
			sb.append("  - position:\n");
			sb.append("      fixture: "+p.getMovingHead().toString().replace('_', '-')+"\n");	
			sb.append("      pan: ").append(p.getPan1()).append("\n");
			sb.append("      panFine: ").append(p.getPan2()).append("\n");
			sb.append("      tilt: ").append(p.getTilt1()).append("\n");
			sb.append("      tiltFine: ").append(p.getTilt2()).append("\n");
		}
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
	    writer.write(sb.toString());
	    
	    writer.close();
	}

}
