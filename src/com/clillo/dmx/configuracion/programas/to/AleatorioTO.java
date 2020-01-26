package com.clillo.dmx.configuracion.programas.to;

import java.util.ArrayList;
import java.util.Random;

public class AleatorioTO {
	
	private String id;
	ArrayList<Integer> listaValores;
	private Random random = new Random();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<Integer> getListaValores() {
		return listaValores;
	}
	public void setListaValores(ArrayList<Integer> listaValores) {
		this.listaValores = listaValores;
	}
	public int siguienteIndice(){
		if (listaValores==null)
			return -1;
		return random.nextInt(listaValores.size());
	}
	public int siguienteValor(){
		int indice = siguienteIndice();
		if (indice<0)
			return -1;
		return listaValores.get(indice);
	}
}
