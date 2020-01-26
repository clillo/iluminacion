package com.clillo.dmx.programas.pasos.roboticas;

import java.util.Random;

import com.clillo.dmx.core.fixtures.FixtureRobotica;

public class PosicionAleatoriaIndividual extends PosicionPadre{

	private Random rndPan = new Random();
	private Random rndTilt = new Random();
	
	@Override
	public void mueve(){
		for (FixtureRobotica fixture: grupo.getListaFixtures()){
			int pan = fixture.getPosicionAleatoriaPan(rndPan);
			int tilt = fixture.getPosicionAleatoriaTilt(rndTilt);
			mueveEntidad(pan, tilt, fixture);
		}
	}

	@Override
	public void inicia() {
	}
}
