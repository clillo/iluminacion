package com.clillo.dmx.comm.vdj;

public interface VJListener {

	public void cambiar(double vuMeter,	double posicion, double songPosBeats, double beatCounter, String nombre);
	
	public void beat();
	
	public void cuartoDeBeat(int cual);

	public void medioBeat(int cual);

}
