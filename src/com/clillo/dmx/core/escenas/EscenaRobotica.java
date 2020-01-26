package com.clillo.dmx.core.escenas;

public class EscenaRobotica {

	private int id;
	private String nombre;
	
	private int canalPan;
	private int canalTilt;
	private int canalPanFine;
	private int canalTiltFine;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCanalPan() {
		return canalPan;
	}

	public void setCanalPan(int canalPan) {
		this.canalPan = canalPan;
	}

	public int getCanalTilt() {
		return canalTilt;
	}

	public void setCanalTilt(int canalTilt) {
		this.canalTilt = canalTilt;
	}

	public int getCanalPanFine() {
		return canalPanFine;
	}

	public void setCanalPanFine(int canalPanFine) {
		this.canalPanFine = canalPanFine;
	}

	public int getCanalTiltFine() {
		return canalTiltFine;
	}

	public void setCanalTiltFine(int canalTiltFine) {
		this.canalTiltFine = canalTiltFine;
	}
}
