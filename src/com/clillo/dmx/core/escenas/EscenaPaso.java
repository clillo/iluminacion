package com.clillo.dmx.core.escenas;

import java.util.ArrayList;

public class EscenaPaso {

	private int id;
	private ArrayList<EscenaCanal> listaCanales;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<EscenaCanal> getListaCanales() {
		return listaCanales;
	}
	public void setListaCanales(ArrayList<EscenaCanal> listaCanales) {
		this.listaCanales = listaCanales;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (EscenaCanal ec: listaCanales)
			sb.append(ec.toString()).append("\t");
		return sb.toString();
	}
}
