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
			
		//	boolean moviendose = fixture.getPanFinal() != pan && fixture.getTiltFinal() !=tilt;

		//	if(moviendose)
		//		continue;
			
			//System.out.println(fixture.getFixId()+ "\tActual: "+ pan+","+tilt + "\t"+ fixture.getVentanaMinX()+","+fixture.getVentanaMaxX()+"\t"+ fixture.getVentanaMinY()+","+fixture.getVentanaMaxY()+"\t");

			PosicionRelativaVentana posicion = null;
			
			if (pan <= fixture.getVentanaMinX()) //if (Math.abs(pan - fixture.getVentanaMinX())<2)
				posicion = PosicionRelativaVentana.Izquierda;

			if (pan >= fixture.getVentanaMaxX()) //if (Math.abs(pan - fixture.getVentanaMaxX())<2)
				posicion = PosicionRelativaVentana.Derecha;

			if (tilt <= fixture.getVentanaMinY()) //if (Math.abs(tilt - fixture.getVentanaMinY())<2) 
				posicion = PosicionRelativaVentana.Arriba;

			if (tilt >= fixture.getVentanaMaxY()) //if (Math.abs(tilt - fixture.getVentanaMaxY())<2)
				posicion = PosicionRelativaVentana.Abajo;
			

			//System.out.println(fixture.getFixId()+"\t"+posicion+"\t"+fixture.isMoviendose());
			
		//	System.out.println(esPrimeraVez);

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
						
		//	System.out.println(fixture.getFixId()+"\tMover a: "+ pan+","+tilt);
			fixture.setPanFine(pan);
			fixture.setTiltFine(tilt);
			mueveEntidad(pan, tilt, fixture);
		}
		
		esPrimeraVez = false;
	}

}
