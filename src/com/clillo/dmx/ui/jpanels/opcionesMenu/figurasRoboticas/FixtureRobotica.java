package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas;

import com.clillo.dmx.core.fixtures.Fixture;

public abstract class FixtureRobotica extends Fixture {

	public static final double maxX = 65536;
	public static final double maxY = 65536;
	
	private double pan;
	private double tilt;
	
	private int pan1;
	private int pan2;
	
	private int tilt1;
	private int tilt2;

	private double ventanaMinX = 0000;
	private double ventanaMaxX = 23000;
	private double ventanaMinY = 0;
	private double ventanaMaxY = 31000;

	public FixtureRobotica(String nombre) {
		super(nombre);
	}

	public double getPan() {
		return pan;
	}

	public void setPan(double pan) {
		this.pan = pan;
	}

	public double getTilt() {
		return tilt;
	}

	public void setTilt(double tilt) {
		this.tilt = tilt;
	}

	public int getPan1() {
		return pan1;
	}

	public void setPan1(int pan1) {
		this.pan1 = pan1;
	}

	public int getPan2() {
		return pan2;
	}

	public void setPan2(int pan2) {
		this.pan2 = pan2;
	}

	public int getTilt1() {
		return tilt1;
	}

	public void setTilt1(int tilt1) {
		this.tilt1 = tilt1;
	}

	public int getTilt2() {
		return tilt2;
	}

	public void setTilt2(int tilt2) {
		this.tilt2 = tilt2;
	}

	public double getVentanaMinX() {
		return ventanaMinX;
	}

	public void setVentanaMinX(double ventanaMinX) {
		this.ventanaMinX = ventanaMinX;
	}

	public double getVentanaMaxX() {
		return ventanaMaxX;
	}

	public void setVentanaMaxX(double ventanaMaxX) {
		this.ventanaMaxX = ventanaMaxX;
	}

	public double getVentanaMinY() {
		return ventanaMinY;
	}

	public void setVentanaMinY(double ventanaMinY) {
		this.ventanaMinY = ventanaMinY;
	}

	public double getVentanaMaxY() {
		return ventanaMaxY;
	}

	public void setVentanaMaxY(double ventanaMaxY) {
		this.ventanaMaxY = ventanaMaxY;
	}
}
