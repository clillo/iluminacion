package com.clillo.dmx.core.fixtures;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class FixtureDerbyRGBW extends Fixture{
	
	private static final File ARCHIVO = new File("conf/fixtures/derbyRGBW2.xml");
	private static XMLConfiguration configuracion;

	private int r;
	private int g;
	private int b;
	private int w;
	private int dimmer;
	private int strobo;
	private int rotacion;
	private int auto;
	
	public FixtureDerbyRGBW(String nombre) {
		super(nombre);
		try {
			configuracion = new XMLConfiguration(ARCHIVO);
			configuracion.setSchemaValidation(false);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		r = configuracion.getInt("actualR");
		g = configuracion.getInt("actualG");
		b = configuracion.getInt("actualB");
		w = configuracion.getInt("actualW");
		
		dimmer = configuracion.getInt("dimmer");
		strobo = configuracion.getInt("strobo");
		rotacion = configuracion.getInt("rotacion");
		auto = configuracion.getInt("auto");
	}
		
	public void grabar(){
		try {
			configuracion.setProperty("actualR", r);
			configuracion.setProperty("actualG", g);
			configuracion.setProperty("actualB", b);
			configuracion.setProperty("actualW", w);
			configuracion.setProperty("dimmer", dimmer);
			configuracion.setProperty("strobo", strobo);
			configuracion.setProperty("rotacion", rotacion);
			configuracion.setProperty("auto", auto);
	
			configuracion.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getW() {
		return w;
	}

	public void setW(int w) {
		this.w = w;
	}

	public int getDimmer() {
		return dimmer;
	}

	public void setDimmer(int dimmer) {
		this.dimmer = dimmer;
	}

	public int getStrobo() {
		return strobo;
	}

	public void setStrobo(int strobo) {
		this.strobo = strobo;
	}

	public int getRotacion() {
		return rotacion;
	}

	public void setRotacion(int rotacion) {
		this.rotacion = rotacion;
	}

	public int getAuto() {
		return auto;
	}

	public void setAuto(int auto) {
		this.auto = auto;
	}
}
