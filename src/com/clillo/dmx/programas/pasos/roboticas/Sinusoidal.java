package com.clillo.dmx.programas.pasos.roboticas;

import com.clillo.dmx.core.fixtures.FixtureRobotica;

public class Sinusoidal extends PosicionPadre{

	private static final double pi = Math.PI/180.0;

	private	int iTiempo = 0;
	private int maxOffset = 5;
	private double deltaPosX;
	
	private long tiempoAnterior;
	
	public Sinusoidal() {
		
	}

	@Override
	public void mueve(){
		if ((System.currentTimeMillis() - tiempoAnterior)>30000)
			inicia();
		
		int offset=0;
		iTiempo += 10;
		if (iTiempo>=360)
		  	iTiempo=0;

		for (FixtureRobotica fixture: grupo.getListaFixtures()){				
			int alto = fixture.getAltoVentana()/2;
			double posx = fixture.getPosicionVentanaMediaPan() + deltaPosX * fixture.getAnchoVentana();
			double posy = (Math.cos((iTiempo+offset)*pi)*alto) + fixture.getPosicionVentanaMediaTilt();
		    
			mueveEntidad((int)posx, (int)posy, fixture);
		    offset+=maxOffset;
		}
	}

	@Override
	public void inicia() {
		maxOffset = (int)(Math.random()*80)+5;
		deltaPosX = (1.0 - Math.random()*2.0)/2.0;
		tiempoAnterior = System.currentTimeMillis();
	}
}
