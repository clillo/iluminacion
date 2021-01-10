package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas;

import java.util.List;

public class Escena {
	
	private String id;
	
	private List<NodoEscena> listaNodos;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<NodoEscena> getListaNodos() {
		return listaNodos;
	}
	public void setListaNodos(List<NodoEscena> listaNodos) {
		this.listaNodos = listaNodos;
	}
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
