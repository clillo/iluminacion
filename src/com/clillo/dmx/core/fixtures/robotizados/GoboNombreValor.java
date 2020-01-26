package com.clillo.dmx.core.fixtures.robotizados;

import java.util.ArrayList;

public class GoboNombreValor {

	private String id;
	private String nombre;
	private int valor;
	private ArrayList<String> listaCanales;
	
	public GoboNombreValor() {
		super();
	}

	public GoboNombreValor(String nombre, int valor) {
		super();
		this.nombre = nombre;
		this.valor = valor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public ArrayList<String> getListaCanales() {
		return listaCanales;
	}

	public void setListaCanales(ArrayList<String> listaCanales) {
		this.listaCanales = listaCanales;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
