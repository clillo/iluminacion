package com.clillo.dmx.core.escenas;

public class EscenaCanalOnOff extends EscenaCanal {

	private boolean encendido;
	private int valorOn;
	private int valorOff;
	
	public EscenaCanalOnOff(boolean encendido, int valorOn, int valorOff) {
		super();
		this.valorOn = valorOn;
		this.valorOff = valorOff;
		setEncendido(encendido);
	}

	public boolean isEncendido() {
		return encendido;
	}

	public void setEncendido(boolean encendido) {
		this.encendido = encendido;
		if (encendido)
			this.setValor(valorOn);
		else
			this.setValor(valorOff);

//		System.out.println(encendido+"\t"+getValor());
	}
}
