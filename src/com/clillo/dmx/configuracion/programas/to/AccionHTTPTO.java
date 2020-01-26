package com.clillo.dmx.configuracion.programas.to;

import java.io.Serializable;

public class AccionHTTPTO implements Serializable {

	private static final long serialVersionUID = -8765316355465888640L;

	private String id;
	private String fixture;
	private String accion;
	private String ip;
	private int puerto;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
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
	public String getFixture() {
		return fixture;
	}
	public void setFixture(String fixture) {
		this.fixture = fixture;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AccionHTTPTO [id=");
		builder.append(id);
		builder.append(", fixture=");
		builder.append(fixture);
		builder.append(", accion=");
		builder.append(accion);
		builder.append(", ip=");
		builder.append(ip);
		builder.append(", puerto=");
		builder.append(puerto);
		builder.append("]");
		return builder.toString();
	}

}
