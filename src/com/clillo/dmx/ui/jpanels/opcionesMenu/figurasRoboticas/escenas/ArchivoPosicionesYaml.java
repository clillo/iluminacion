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
		
		int i=0;
		for (MHPositionsList pos: positionsList){
			List<NodoEscena> listaPuntos = new ArrayList<>();

			Escena e1 = new Escena();
			e1.setId(i);
			e1.setListaNodos(listaPuntos);
			listaEscenas.add(e1);
			i++;
			for(MHPositionNodeElement list: pos.getPositions()){
				MHPositionNode position = list.getPosition();
				
				NodoEscena nodoEscena = new NodoEscena();
				
				if (position.getFixture().equals("mh-90-1"))
					nodoEscena.setMovingHead(FixMovingHead.MvHd1_90);
				if (position.getFixture().equals("mh-90-2"))
					nodoEscena.setMovingHead(FixMovingHead.MvHd2_90);
				if (position.getFixture().equals("mh-60-1"))
					nodoEscena.setMovingHead(FixMovingHead.MvHd1_60);
				if (position.getFixture().equals("mh-60-2"))
					nodoEscena.setMovingHead(FixMovingHead.MvHd2_60);
				
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
			grabaLista(e.getListaNodos(), "conf/escenas/spring/pos"+(e.getId())+".yml");
	}
	
	
	private void grabaLista(List<NodoEscena> lista, String archivo) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("positions:\n");
		for (NodoEscena p: lista){
			sb.append("  - position:\n");
			switch (p.getMovingHead()) {
			case MvHd1_60:
				sb.append("      fixture: mh-60-1\n");	
				break;
			case MvHd2_60:
				sb.append("      fixture: mh-60-2\n");	
				break;
				
			case MvHd1_90:
				sb.append("      fixture: mh-90-1\n");	
				break;
			case MvHd2_90:
				sb.append("      fixture: mh-90-2\n");	
				break;
			default:
				break;
			}

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
