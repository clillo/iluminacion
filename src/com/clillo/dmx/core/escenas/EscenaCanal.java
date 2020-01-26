package com.clillo.dmx.core.escenas;

public class EscenaCanal {

	private int id;
	private String canalStr;
	private int idDMX;
	private int valor;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCanalStr() {
		return canalStr;
	}
	
	public void setCanalStr(String canalStr) {
		this.canalStr = canalStr;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getIdDMX() {
		return idDMX;
	}

	public void setIdDMX(int idDMX) {
		this.idDMX = idDMX;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EscenaCanal [id=");
		builder.append(id);
		builder.append(", canalStr=");
		builder.append(canalStr);
		builder.append(", idDMX=");
		builder.append(idDMX);
		builder.append(", valor=");
		builder.append(valor);
		builder.append("]");
		return builder.toString();
	}
}
