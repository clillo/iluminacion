package com.clillo.dmx.ui.jpanels.fixtures;

import java.awt.Color;

public class PanelObservadorPinSpot extends PanelObservadorRGBWPadre {
	
	private static final long serialVersionUID = -5766991298707309249L;

	public PanelObservadorPinSpot() {
		super();
	}
	
	public void setId(String id){
		super.setId(id);
		canales = new int[]{canalBase, canalBase+1, canalBase+2, canalBase+3};
	}

	@Override
	protected void inicializaArreglosAuxiliares(Color[] colorDimmer, int[] nivelDimmer) {
		for (int i=0; i<127; i++){
			int nivel =  nivel2Porcentaje(i); 
			colorDimmer[i] = new Color(nivel, nivel, nivel);
			nivelDimmer[i] = 100*i/127;
		}

		for (int i=127; i<256; i++){
			colorDimmer[i] = Color.black;
			nivelDimmer[i] = 0;
		}		
	}

	@Override
	protected int nivel2Porcentaje(int nivel) {
		return  100*nivel/127; 
	}

	@Override
	protected int porcentaje2Nivel(int porcentaje) {
		return (127*porcentaje/100);
	}

	@Override
	protected int getCanalDimmer() {
		return canalBase;
	}

	@Override
	protected int getCanalR() {
		return canalBase + 1;
	}

	@Override
	protected int getCanalG() {
		return canalBase + 2;
	}

	@Override
	protected int getCanalB() {
		return canalBase + 3;
	}

	@Override
	protected void actualizaEnvioDmxOtros(int canal, int valor) {
	//	System.out.println("Error al tratar de manejar el mensaje al canal "+canal+" desde el observador pinspot");
	}
}
