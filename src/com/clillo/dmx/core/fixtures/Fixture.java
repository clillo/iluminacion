package com.clillo.dmx.core.fixtures;

import java.awt.Color;
import java.util.ArrayList;

import com.clillo.dmx.configuracion.programas.to.AccionHTTPTO;
import com.clillo.dmx.configuracion.programas.to.CanalValorTO;
import com.clillo.utiles.Log;

public abstract class Fixture {

	protected boolean encendido;
	private int canalDMXInicial;
	private int nCanales;
	private Color color;
	private String nombre;
	private String fixId;
	
	protected int nivelR;
	protected int nivelG;
	protected int nivelB;
	protected int nivelBlanco;
	protected int nivelDimmer;
	
	private ArrayList<CanalValorTO> listaCanales;
	
	private ListenerApagadoEncendido listener;
	
	public Fixture(int canalDMXInicial, String nombre) {
		this.canalDMXInicial = canalDMXInicial;
		this.nombre = nombre;
	}

	public Fixture(String nombre) {
		this.nombre = nombre;
	}
	
	public ArrayList<CanalValorTO> getListaCanales() {
		return listaCanales;
	}

	public void setListaCanales(ArrayList<CanalValorTO> listaCanales) {
		this.listaCanales = listaCanales;
	}
	
	public void encender(){
		if (listener!=null){
			Log.debug("Encendiendo "+this.getClass().getName(), this.getClass());
			listener.encender();
		}
		
	}

	public void apagar(){
		if (listener!=null){
			Log.debug("Apagando "+this.getClass().getName(), this.getClass());
			listener.apagar();
		}
	}

	public boolean isEncendido() {
		return encendido;
	}

	public int getCanalDMXInicial() {
		return canalDMXInicial;
	}

	public void setCanalDMXInicial(int canalDMXInicial) {
		this.canalDMXInicial = canalDMXInicial;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String getFixId() {
		return fixId;
	}

	public void setFixId(String fixId) {
		this.fixId = fixId;
	}

	public int getnCanales() {
		return nCanales;
	}

	public void setnCanales(int nCanales) {
		this.nCanales = nCanales;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ListenerApagadoEncendido getListener() {
		return listener;
	}

	public void setListener(ListenerApagadoEncendido listener) {
		this.listener = listener;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fixture [canalDMXInicial=");
		builder.append(canalDMXInicial);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", fixId=");
		builder.append(fixId);
		builder.append("]");
		return builder.toString();
	}
}
