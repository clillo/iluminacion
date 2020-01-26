package com.clillo.dmx.configuracion.programas;

public class CanalValorTO {

	private String canalId;
	private int canalDMX;
	private int valorDMX;
	
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
