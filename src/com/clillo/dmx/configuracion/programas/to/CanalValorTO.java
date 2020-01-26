package com.clillo.dmx.configuracion.programas.to;

import java.io.Serializable;

public class CanalValorTO implements Serializable{

	private static final long serialVersionUID = 7654710161674475472L;
	
	private String canalId;
	private int canalDMX;
	private int valorDMX;
	private String idAleatorio;
	private boolean aleatorio;
	
	public String getCanalId() {
		return canalId;
	}
	public void setCanalId(String canalId) {
		this.canalId = canalId;
	}
	public int getCanalDMX() {
		return canalDMX;
	}
	public void setCanalDMX(int canalDMX) {
		this.canalDMX = canalDMX;
	}
	public int getValorDMX() {
		return valorDMX;
	}
	public void setValorDMX(int valorDMX) {
		this.valorDMX = valorDMX;
	}
	
	public String getIdAleatorio() {
		return idAleatorio;
	}
	public void setIdAleatorio(String idAleatorio) {
		this.idAleatorio = idAleatorio;
	}
	public boolean isAleatorio() {
		return aleatorio;
	}
	public void setAleatorio(boolean aleatorio) {
		this.aleatorio = aleatorio;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CanalValorTO [canalId=");
		builder.append(canalId);
		builder.append(", canalDMX=");
		builder.append(canalDMX);
		builder.append(", valorDMX=");
		builder.append(valorDMX);
		builder.append("]");
		return builder.toString();
	}
}
