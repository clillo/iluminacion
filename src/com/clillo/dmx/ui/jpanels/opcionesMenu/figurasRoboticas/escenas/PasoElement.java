package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas;

public class PasoElement {

	private Paso paso;

	public Paso getPaso() {
		return paso;
	}

	public void setPaso(Paso value) {
		this.paso = value;
	}
	
	@Override
	public String toString() {
		
		return paso.getNombre();
	}

}
