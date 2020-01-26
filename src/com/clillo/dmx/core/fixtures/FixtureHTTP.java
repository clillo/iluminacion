package com.clillo.dmx.core.fixtures;

import java.util.ArrayList;

import com.clillo.dmx.comm.tcp.HTTP;
import com.clillo.dmx.configuracion.programas.to.AccionHTTPTO;

public abstract class FixtureHTTP extends Fixture{

	public FixtureHTTP(String nombre) {
		super(nombre);
	}

	private ArrayList<AccionHTTPTO> listaAcciones;
	private String ip;
	private int puerto;
	
	public ArrayList<AccionHTTPTO> getListaAcciones() {
		return listaAcciones;
	}

	public void setListaAcciones(ArrayList<AccionHTTPTO> listaAcciones) {
		this.listaAcciones = listaAcciones;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}
	
	public void enviarMensajeHHTP(String mensaje){
		HTTP.enviarMensajeHHTP(mensaje, ip, puerto);
	}
}
