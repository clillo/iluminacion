package com.clillo.dmx.programas.pasos.roboticas;

import java.util.Random;

import com.clillo.dmx.core.fixtures.FixtureRobotica;

public class PosicionAleatoriaGlobalSimetricoTilt extends PosicionPadre{

	private Random rndPan = new Random();
	private Random rndTilt = new Random();
	
	@Override
	public void mueve(){
		int pan = -1;
		int tilt = -1;

		int n=0;
		for (FixtureRobotica fixture: grupo.getListaFixtures()){
			if(pan==-1)
				pan = fixture.getPosicionAleatoriaPan(rndPan);
			if(tilt==-1)
				tilt = fixture.getPosicionAleatoriaTilt(rndTilt);
			
			if ((n++%2)==0)
				mueveEntidad(pan, tilt, fixture);
			else
				mueveEntidad(pan, fixture.getPosicionInversaTilt(tilt), fixture);
		}
	}

	@Override
	public void inicia() {
	}
}
