package com.clillo.dmx.comm.dmx;

public interface DmxEnviable {

	public int[] getCanales();
	
	public void actualizaEnvioDmx(int canal, int valor);
}
