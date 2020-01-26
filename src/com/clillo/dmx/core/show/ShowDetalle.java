package com.clillo.dmx.core.show;

import java.util.ArrayList;

public class ShowDetalle {

	private String nombre;
	private ArrayList<ShowParValor> lista;
	private ArrayList<ShowPrograma> listaProgramas;
	private long siguienteEjecucion;
	
	public void envia(){
		if (lista!=null)
			for (ShowParValor spv: lista)
				spv.envia();
		
		if (listaProgramas!=null)
			for (ShowPrograma programa: listaProgramas)
				programa.ejecuta();
	}
	
	public void apaga(){
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<ShowParValor> getLista() {
		return lista;
	}

	public void setLista(ArrayList<ShowParValor> lista) {
		this.lista = lista;
	}

	public ArrayList<ShowPrograma> getListaProgramas() {
		return listaProgramas;
	}

	public void setListaProgramas(ArrayList<ShowPrograma> listaProgramas) {
		this.listaProgramas = listaProgramas;
	}

	public long getSiguienteEjecucion() {
		if (siguienteEjecucion==0)
			return 1000;
		return siguienteEjecucion;
	}

	public void setSiguienteEjecucion(long siguienteEjecucion) {
		this.siguienteEjecucion = siguienteEjecucion;
	}
	
}
