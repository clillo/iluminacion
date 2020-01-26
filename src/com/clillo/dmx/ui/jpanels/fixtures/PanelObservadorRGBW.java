package com.clillo.dmx.ui.jpanels.fixtures;

import java.awt.Color;

/**
 * Representa un dmx con leds rgb y blanco
 * El canal 1 es el control de brillo. 0-15 apagado, 16-255 desde apagado hasta encendido total
 * El canal 2 es el valor para r
 * El canal 3 es el valor para g
 * El canal 4 es el valor para b
 * El canal 5 es el valor para w (blanco)
 * El canal 6 es el valor para strobo. 0-15 apagado, 16-255 velocidad desde lento hasta rápido
 * El canal 7 es el control para el cambio de colores automático. 0-15 apagado, 16-255 velocidad del cambio

 * Strobo: rápido 190
 */
public class PanelObservadorRGBW extends PanelObservadorRGBWPadre {
	
	private static final long serialVersionUID = -5766991298707309249L;

	public PanelObservadorRGBW() {
		super();
	}
	
	public void setId(String id){
		super.setId(id);    //brillo    r           g             b           w            strobo       color mix
		canales = new int[]{canalBase, canalBase+1, canalBase+2, canalBase+3, canalBase+4, canalBase+5, canalBase+6};
		actualizaColor(255, 0, 0);
	}

	@Override
	protected void inicializaArreglosAuxiliares(Color[] colorDimmer, int[] nivelDimmer) {
		for (int i=0; i<16; i++){
			colorDimmer[i] = Color.black;
			nivelDimmer[i] = 0;
		}
		
		for (int i=16; i<256; i++){
			int nivel =  nivel2Porcentaje(i); 
			colorDimmer[i] = new Color(nivel, nivel, nivel);
			nivelDimmer[i] = 100*(i-16)/239;
		}
		
	//	for (int i=0;i<256; i++)
	//		System.out.println(i+"\t"+nivelDimmer[i]);
	}

	@Override
	protected int nivel2Porcentaje(int nivel) {
		return 100*(nivel-16)/239; 
	}

	@Override
	protected int porcentaje2Nivel(int porcentaje) {
		return (255*porcentaje/100);
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
		//System.out.println("Error al tratar de manejar el mensaje al canal "+canal+" desde el observador rgb");
	}
}
