package com.clillo.dmx.core.fixtures.robotizados;

public class Punto {
	
	private int posX;
	private int posY;
	private int velocidad;
	private boolean fake;
	
	public Punto() {
		fake = true;
	}
	
	public Punto(int posX, int posY, int velocidad){
		this.posX = posX;
		this.posY = posY;	
		this.velocidad = velocidad;
		fake = false;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public boolean isFake() {
		return fake;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Punto [posX=");
		builder.append(posX);
		builder.append(", posY=");
		builder.append(posY);
		builder.append(", velocidad=");
		builder.append(velocidad);
		builder.append(", fake=");
		builder.append(fake);
		builder.append("]");
		return builder.toString();
	}
}
