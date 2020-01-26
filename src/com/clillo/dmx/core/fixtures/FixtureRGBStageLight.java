package com.clillo.dmx.core.fixtures;


public class FixtureRGBStageLight extends Fixture{

	//private static final File ARCHIVO = new File("conf/fixtures/crystalMagic.xml");
	//private static XMLConfiguration configuracion;

	private int dimmer;
	private int rojoNaranja;
	private int verdeVioleta;
	private int azulBlanco;
	private int verdeAmarillo;
	private int stobo;
	private int rotar;
	private int automatico;


	
	public FixtureRGBStageLight(int id, String nombre) {
		super(id, nombre);
/*		try {
			configuracion = new XMLConfiguration(ARCHIVO);
			configuracion.setSchemaValidation(false);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		r = configuracion.getInt("actualR");
		g = configuracion.getInt("actualG");
		b = configuracion.getInt("actualB");
		rotar = configuracion.getInt("rotar");
*/
	}

	
	public int getDimmer() {
		return dimmer;
	}


	public void setDimmer(int dimmer) {
		this.dimmer = dimmer;
	}


	public int getRojoNaranja() {
		return rojoNaranja;
	}


	public void setRojoNaranja(int rojoNaranja) {
		this.rojoNaranja = rojoNaranja;
	}


	public int getVerdeVioleta() {
		return verdeVioleta;
	}


	public void setVerdeVioleta(int verdeVioleta) {
		this.verdeVioleta = verdeVioleta;
	}


	public int getAzulBlanco() {
		return azulBlanco;
	}


	public void setAzulBlanco(int azulBlanco) {
		this.azulBlanco = azulBlanco;
	}


	public int getVerdeAmarillo() {
		return verdeAmarillo;
	}


	public void setVerdeAmarillo(int verdeAmarillo) {
		this.verdeAmarillo = verdeAmarillo;
	}


	public int getStobo() {
		return stobo;
	}


	public void setStobo(int stobo) {
		this.stobo = stobo;
	}


	public int getRotar() {
		return rotar;
	}


	public void setRotar(int rotar) {
		this.rotar = rotar;
	}


	public int getAutomatico() {
		return automatico;
	}


	public void setAutomatico(int automatico) {
		this.automatico = automatico;
	}


	public void grabar(){
	/*	try {
			configuracion.setProperty("actualR", r);
			configuracion.setProperty("actualG", g);
			configuracion.setProperty("actualB", b);
			configuracion.setProperty("rotar", rotar);

			configuracion.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}*/
	}
}
