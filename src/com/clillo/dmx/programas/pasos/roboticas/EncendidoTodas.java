package com.clillo.dmx.programas.pasos.roboticas;

import com.clillo.dmx.core.fixtures.FixtureRobotica;

public class EncendidoTodas extends PosicionPadre{
	
	private boolean encendido;

	public EncendidoTodas(boolean encendido) {
		this.encendido = encendido;
	}

	@Override
	public void mueve(){
		for (FixtureRobotica fixture: grupo.getListaFixtures())
			if (encendido)
				fixture.encender();
			else
				fixture.apagar();
	}

	@Override
	public void inicia() {
		
	}
}
