package com.clillo.dmx.programas.pasos.roboticas;

import java.util.Random;

import com.clillo.dmx.core.fixtures.FixtureRobotica;

public class Rebotando extends PosicionPadre{

	private enum PosicionRelativaVentana {Arriba, Abajo, Izquierda, Derecha};
	
	private Random rndPan = new Random();
	private Random rndTilt = new Random();
	
	private boolean esPrimeraVez;
	
	@Override
	public void inicia() {
		esPrimeraVez = true;
	}

	@Override
	public void mueve(){
		for (FixtureRobotica fixture: grupo.getListaFixtures()){
			
			int pan = fixture.getPosX();
			int tilt = fixture.getPosY();

			PosicionRelativaVentana posicion = null;
			
			if (pan <= fixture.getVentanaMinX())
				posicion = PosicionRelativaVentana.Izquierda;

			if (pan >= fixture.getVentanaMaxX())
				posicion = PosicionRelativaVentana.Derecha;

			if (tilt <= fixture.getVentanaMinY())
				posicion = PosicionRelativaVentana.Arriba;

			if (tilt >= fixture.getVentanaMaxY())
				posicion = PosicionRelativaVentana.Abajo;
			
			if (posicion == null && !esPrimeraVez)
				continue;
			
			if (posicion == null  || posicion == PosicionRelativaVentana.Arriba){
				pan = fixture.getVentanaMinX();
				tilt = fixture.getPosicionAleatoriaTilt(rndTilt);
			}

			if (posicion == PosicionRelativaVentana.Izquierda){
				pan = fixture.getPosicionAleatoriaPan(rndPan);
				tilt = fixture.getVentanaMaxY();	
			}

			if (posicion == PosicionRelativaVentana.Derecha){
				pan = fixture.getPosicionAleatoriaPan(rndPan);
				tilt = fixture.getVentanaMinY();	
			}

			if (posicion == PosicionRelativaVentana.Abajo){
				pan = fixture.getVentanaMaxX();
				tilt = fixture.getPosicionAleatoriaTilt(rndTilt);
			}

			fixture.setPanFine(pan);
			fixture.setTiltFine(tilt);
			mueveEntidad(pan, tilt, fixture);
		}
		
		esPrimeraVez = false;
	}

}
