package com.clillo.dmx.programas.pasos.roboticas;

import java.util.HashMap;

import com.clillo.dmx.core.fixtures.FixtureRobotica;

public class Espiral extends PosicionPadre{

	private HashMap<String, Integer> radioEspiral;
	private HashMap<String, Integer> tiempo;
	
	public Espiral() {
		radioEspiral = new HashMap<String, Integer>();
		tiempo = new HashMap<String, Integer>();		
	}

	@Override
	public void mueve(){
		for (FixtureRobotica fixture: grupo.getListaFixtures()){
			int iradio = 0;
			if (radioEspiral.containsKey(fixture.getFixId()))
					iradio = radioEspiral.get(fixture.getFixId());

			int iTiempo = 0;
			if (tiempo.containsKey(fixture.getFixId()))
				iTiempo = tiempo.get(fixture.getFixId());
			
			if (iradio<20)
				iradio=20;
			
			int ancho = fixture.getAnchoVentana()/2;
			int alto = fixture.getAltoVentana()/2;

			double posx = (Math.sin(iTiempo*Math.PI/180)*(ancho*Math.sin(iradio*Math.PI/180))) + fixture.getPosicionVentanaMediaPan();
			double posy = (Math.cos(iTiempo*Math.PI/180)*(alto*Math.sin(iradio*Math.PI/180))) + fixture.getPosicionVentanaMediaTilt();
		    
			mueveEntidad((int)posx, (int)posy, fixture);

		    if (iTiempo>=360){
		    	iTiempo=0;
		    	iradio+=5;
		    	
		    	 if (iradio>=170){
		    		 iradio=0;
		    	 }
		    }
		    
		    iTiempo += 10;
		    radioEspiral.put(fixture.getFixId(), iradio);
		    tiempo.put(fixture.getFixId(), iTiempo);
		}
	}

	@Override
	public void inicia() {
		
	}
}
