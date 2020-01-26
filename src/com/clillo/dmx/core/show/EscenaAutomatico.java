package com.clillo.dmx.core.show;

import java.util.ArrayList;

public class EscenaAutomatico {

	private String nombre;
	private ArrayList<ShowDetalle> lista;
	private int pasoActual;
	private long siguienteEjecucion;
	private boolean enEjecucion;
	private ShowDetalle apagar;

	public EscenaAutomatico(){
		pasoActual=0;
	}
	
	public void envia(){
		ShowDetalle sd = lista.get(pasoActual);
		sd.envia();
		siguienteEjecucion = System.currentTimeMillis() + sd.getSiguienteEjecucion();
		pasoActual = (pasoActual+1)%lista.size();
	}
	
	public void apaga(){
		apagar.envia();
		pasoActual=0;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<ShowDetalle> getLista() {
		return lista;
	}

	public void setLista(ArrayList<ShowDetalle> lista) {
		this.lista = lista;
	}

	public long getSiguienteEjecucion() {
		return siguienteEjecucion;
	}

	public void setSiguienteEjecucion(long siguienteEjecucion) {
		this.siguienteEjecucion = siguienteEjecucion;
	}
	
	public boolean isEnEjecucion() {
		return enEjecucion;
	}

	public void setEnEjecucion(boolean enEjecucion) {
		this.enEjecucion = enEjecucion;
	}

	public ShowDetalle getApagar() {
		return apagar;
	}

	public void setApagar(ShowDetalle apagar) {
		this.apagar = apagar;
	}

	@Override
	public String toString() {
		return nombre;
	}
}
