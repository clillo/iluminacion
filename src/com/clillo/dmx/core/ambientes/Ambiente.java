package com.clillo.dmx.core.ambientes;

import java.util.ArrayList;

public class Ambiente {

	private String nombre;
	private ArrayList<AmbienteDetalle> lista;
	private boolean enEjecucion;

	public Ambiente(){
	}
	
	public void envia(){
	}
	
	public void apaga(){
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<AmbienteDetalle> getLista() {
		return lista;
	}

	public void setLista(ArrayList<AmbienteDetalle> lista) {
		this.lista = lista;
	}
	
	public boolean isEnEjecucion() {
		return enEjecucion;
	}

	public void setEnEjecucion(boolean enEjecucion) {
		this.enEjecucion = enEjecucion;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
