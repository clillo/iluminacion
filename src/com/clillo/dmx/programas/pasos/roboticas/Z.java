package com.clillo.dmx.programas.pasos.roboticas;

import com.clillo.dmx.core.fixtures.FixtureRobotica;

public class Z extends PosicionPadre{

	public enum TipoZ{tipo1, tipo2, tipo1Simetrico, tipo2Simetrico}

	private	int tiempo = 0;
	private double posRelativax = 0;
	private double posRelativay = 0;
	private double escala = 60;
		
	private TipoZ tipo;
	
	public Z(TipoZ tipo) {
		this.tipo = tipo;
	}

	@Override
	public void mueve(){
		tiempo += 1;
		boolean simetrico= false;
		switch (tipo) {
			case tipo1:
				tipo1();
				break;

			case tipo2:
				tipo2();
				break;
				
			case tipo1Simetrico:
				tipo1();
				simetrico=true;
				break;

			case tipo2Simetrico:
				tipo2();
				simetrico=true;
				break;
		}

		if (simetrico){
			boolean par = false;
			for (FixtureRobotica fixture: grupo.getListaFixtures())	{
				if (par)
					mueveEntidadRelativa(posRelativax, posRelativay, fixture);
				else
					mueveEntidadRelativa(100.0 - posRelativax, posRelativay, fixture);
				par = !par;
			}
		}else{
			for (FixtureRobotica fixture: grupo.getListaFixtures())	    
				mueveEntidadRelativa(posRelativax, posRelativay, fixture);

		}

	}
	
	private void tipo1(){
		switch (tiempo) {
		case 1:
			 posRelativax = 100 - escala;
			 posRelativay = 100 - escala;
		break;

		case 2:
			 posRelativax = escala;
			 posRelativay = 100 - escala;
		break;

		case 3:
			 posRelativax = 100 - escala;
			 posRelativay = escala;
		break;
		
		case 4:
			 posRelativax = escala;
			 posRelativay = escala;
			 tiempo = 0;
		break;		
		}
	}
	
	private void tipo2(){
		switch (tiempo) {
		case 1:
			 posRelativax = 100 - escala;
			 posRelativay = 100 - escala;
		break;

		case 2:
			 posRelativax = escala;
			 posRelativay = 100 - escala;
		break;

		case 3:
			 posRelativax = 100 - escala;
			 posRelativay = escala;
		break;
		
		case 4:
			 posRelativax = escala;
			 posRelativay = escala;			
		break;		
		
		case 5:
			 posRelativax = 100 - escala;
			 posRelativay = escala;
		break;		
		
		case 6:
			 posRelativax = escala;
			 posRelativay = 100 - escala;
			 tiempo = 0;
		break;		
		}
	}

	@Override
	public void inicia() {
		tiempo = 0;
		escala = Math.random()*40 + 60;
	}
}
