package com.clillo.dmx.core.fixtures;

import java.io.File;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class FixtureCrystalBall1 extends Fixture{

	private static final File ARCHIVO = new File("conf/fixtures/crystalBall1.xml");
	private static XMLConfiguration configuracion;

	private int r;
	private int g;
	private int b;
	private int rotar;
	
	public FixtureCrystalBall1(String nombre) {
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
		rotar = configuracion.getInt("rotar");
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

	public int getRotar() {
		return rotar;
	}

	public void setRotar(int rotar) {
		this.rotar = rotar;
	}
	
	public void grabar(){
		try {
			configuracion.setProperty("actualR", r);
			configuracion.setProperty("actualG", g);
			configuracion.setProperty("actualB", b);
			configuracion.setProperty("rotar", rotar);

			configuracion.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
}
