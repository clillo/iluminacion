package com.clillo.dmx.configuracion.programas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import com.clillo.dmx.configuracion.programas.to.AccionHTTPTO;
import com.clillo.dmx.configuracion.programas.to.AleatorioTO;
import com.clillo.dmx.configuracion.programas.to.CanalValorTO;
import com.clillo.dmx.configuracion.programas.to.PasoProgramaTO;

public class PasoTO implements Serializable{

	private static final long serialVersionUID = -2099400564239843273L;

	private int id;
	private ArrayList<CanalValorTO> listaValores;
	private ArrayList<MovimientoTO> listaMovimientos;
	private ArrayList<GrupoTO> listaGrupos;
	private ArrayList<EfectoRoboticaTO> listaEfectosRobotica;
	private ArrayList<AccionHTTPTO> listaAcciones;
	private ArrayList<PasoProgramaTO> listaProgramas;
	private ArrayList<AleatorioTO> listaAleatorios;
	
	private boolean maximoBrilloBlanco;
	
	public HashMap<Integer, Integer> getHashValores(){
		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
		
		for (CanalValorTO valor: listaValores)
			hash.put(valor.getCanalDMX(), valor.getValorDMX());
		
		return hash;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<CanalValorTO> getListaValores() {
		return listaValores;
	}
	public void setListaValores(ArrayList<CanalValorTO> listaValores) {
		this.listaValores = listaValores;
	}
	public ArrayList<MovimientoTO> getListaMovimientos() {
		return listaMovimientos;
	}
	public void setListaMovimientos(ArrayList<MovimientoTO> listaMovimientos) {
		this.listaMovimientos = listaMovimientos;
	}
	public ArrayList<GrupoTO> getListaGrupos() {
		return listaGrupos;
	}
	public void setListaGrupos(ArrayList<GrupoTO> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}
	public ArrayList<EfectoRoboticaTO> getListaEfectosRobotica() {
		return listaEfectosRobotica;
	}
	public void setListaEfectosRobotica(ArrayList<EfectoRoboticaTO> listaEfectosRobotica) {
		this.listaEfectosRobotica = listaEfectosRobotica;
	}

	public ArrayList<AccionHTTPTO> getListaAcciones() {
		return listaAcciones;
	}

	public void setListaAcciones(ArrayList<AccionHTTPTO> listaAcciones) {
		this.listaAcciones = listaAcciones;
	}

	public ArrayList<PasoProgramaTO> getListaProgramas() {
		return listaProgramas;
	}

	public void setListaProgramas(ArrayList<PasoProgramaTO> listaProgramas) {
		this.listaProgramas = listaProgramas;
	}

	public boolean isMaximoBrilloBlanco() {
		return maximoBrilloBlanco;
	}

	public void setMaximoBrilloBlanco(boolean maximoBrilloBlanco) {
		this.maximoBrilloBlanco = maximoBrilloBlanco;
	}

	public ArrayList<AleatorioTO> getListaAleatorios() {
		return listaAleatorios;
	}

	public void setListaAleatorios(ArrayList<AleatorioTO> listaAleatorios) {
		this.listaAleatorios = listaAleatorios;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PasoTO [id=");
		builder.append(id);
		builder.append(", listaValores=");
		builder.append(listaValores);
		builder.append(", listaMovimientos=");
		builder.append(listaMovimientos);
		builder.append(", listaGrupos=");
		builder.append(listaGrupos);
		builder.append(", listaEfectosRobotica=");
		builder.append(listaEfectosRobotica);
		builder.append("]");
		return builder.toString();
	}
}
