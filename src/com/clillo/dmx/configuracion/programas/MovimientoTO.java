package com.clillo.dmx.configuracion.programas;

import java.io.Serializable;

import com.clillo.dmx.core.ListaFixtures;
import com.clillo.dmx.core.fixtures.FixtureRobotica;

public class MovimientoTO implements Serializable{

	private static final long serialVersionUID = 8627928703994404250L;

	private String fixture;
	private int pan;
	private int tilt;
	
	private FixtureRobotica robotica;
	
	public String getFixture() {
		return fixture;
	}
	public void setFixture(String id) {
		this.fixture = id;
		robotica = (FixtureRobotica)ListaFixtures.getFixture(id);
	}
	public int getPan() {
		return pan;
	}
	public void setPan(int pan) {
		this.pan = pan;
	}
	public int getTilt() {
		return tilt;
	}
	public void setTilt(int tilt) {
		this.tilt = tilt;
	}
	
	public FixtureRobotica getRobotica() {
		return robotica;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MovimientoTO [fixture=");
		builder.append(fixture);
		builder.append(", pan=");
		builder.append(pan);
		builder.append(", tilt=");
		builder.append(tilt);
		builder.append("]");
		return builder.toString();
	}
}
