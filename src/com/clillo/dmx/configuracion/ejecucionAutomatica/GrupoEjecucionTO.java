package com.clillo.dmx.configuracion.ejecucionAutomatica;

import java.util.ArrayList;

import com.clillo.dmx.core.fixtures.Fixture;

public class GrupoEjecucionTO {

	private String id;
	private ArrayList<Fixture> listaFixtures;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArrayList<Fixture> getListaFixtures() {
		return listaFixtures;
	}
	public void setListaFixtures(ArrayList<Fixture> listaFixtures) {
		this.listaFixtures = listaFixtures;
	}	
}
