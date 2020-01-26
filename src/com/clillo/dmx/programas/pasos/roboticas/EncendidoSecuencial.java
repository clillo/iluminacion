package com.clillo.dmx.programas.pasos.roboticas;

import com.clillo.dmx.core.fixtures.FixtureRobotica;

public class EncendidoSecuencial extends PosicionPadre{
	
	private FixtureRobotica fixtures[] = new FixtureRobotica[4];
	
	public enum TipoSecuencial{SimpleSentido1, SimpleSentido2, SimpleSentido3, SimpleSentido4, SimpleSentido5, SimpleSentido6};

	private TipoSecuencial tipo;
	private int actual = 1;

	private byte[] nivelesSecuencial1[] = { {1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
	private byte[] nivelesSecuencial2[] = { {0, 0, 0, 1}, {0, 0, 1, 0}, {0, 1, 0, 0}, {1, 0, 0, 0}};
	private byte[] nivelesSecuencial3[] = { {1, 1, 0, 0}, {1, 0, 0, 1}, {0, 0, 1, 1}, {0, 1, 1, 0}};
	private byte[] nivelesSecuencial4[] = { {1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 1, 1}, {1, 0, 0, 1}};
	private byte[] nivelesSecuencial5[] = { {1, 0, 0, 1}, {0, 1, 1, 0}};
	private byte[] nivelesSecuencial6[] = { {0, 1, 0, 1}, {1, 0, 1, 0}};

	public EncendidoSecuencial(TipoSecuencial tipo) {
		this.tipo = tipo;
	}

	@Override
	public void mueve(){
		switch (tipo) {
		case SimpleSentido1:
			secuencial(nivelesSecuencial1);
			break;
		case SimpleSentido2:
			secuencial(nivelesSecuencial2);
			break;
		case SimpleSentido3:
			secuencial(nivelesSecuencial3);
			break;
		case SimpleSentido4:
			secuencial(nivelesSecuencial4);
			break;
		case SimpleSentido5:
			secuencial(nivelesSecuencial5);
			break;
		case SimpleSentido6:
			secuencial(nivelesSecuencial6);
			break;
		default:
			break;
		}
	}
	
	private void secuencial( byte[] niveles[]){
		enciende(niveles[actual-1]);
		
		actual++;
		
		if (actual>niveles.length)
			actual=1;
	}


	private void enciende(byte[]niveles){
		for (int i=0; i<4; i++){
			if (niveles[i]==1)
				fixtures[i].encender();
			else
				fixtures[i].apagar();
		}		
	}

	@Override
	public void inicia() {
		fixtures[0] = grupo.getListaFixtures().get(2);//3
		fixtures[1] = grupo.getListaFixtures().get(0);//1
		fixtures[2] = grupo.getListaFixtures().get(1);//2
		fixtures[3] = grupo.getListaFixtures().get(3);//4
	}
}
