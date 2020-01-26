package com.clillo.dmx.ui.jpanels.fixtures;

import java.awt.Color;

import javax.swing.JPanel;

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
public class PanelObservadorRGBWSimple extends PanelObservador {
	
	private static final long serialVersionUID = -5766991298707309249L;

	private JPanel pnlColor;
	private int r;
	private int g;
	private int b;
	private int dimmer;
	
	private float h;
	private float s;
	private float l;
	
	private Color[] colorDimmer = new Color[256];
	private int[] nivelDimmer = new int[256];
	
	public PanelObservadorRGBWSimple() {
		super();
		setLayout(null);
		
		pnlColor = new JPanel();
		pnlColor.setBounds(0, 0, 60, 60);

		add(pnlColor);
		
		for (int i=0; i<16; i++){
			colorDimmer[i] = Color.black;
			nivelDimmer[i] = 0;
		}
		
		for (int i=16; i<256; i++){
			int nivel =  nivel2Porcentaje(i); 
			colorDimmer[i] = new Color(nivel, nivel, nivel);
			nivelDimmer[i] = 100*(i-16)/239;
		}
	}
	
	public void setId(String id){
		super.setId(id);    //brillo    r           g             b           w            strobo       color mix
		canales = new int[]{canalBase, canalBase+1, canalBase+2, canalBase+3, canalBase+4, canalBase+5, canalBase+6};
	}

	private int nivel2Porcentaje(int nivel) {
		return 100*(nivel-16)/239; 
	}

	private int getCanalDimmer() {
		return canalBase;
	}

	private int getCanalR() {
		return canalBase + 1;
	}

	private int getCanalG() {
		return canalBase + 2;
	}

	private int getCanalB() {
		return canalBase + 3;
	}

	@Override
	public void actualizaEnvioDmx(int canal, int valor) {
		boolean actualizarColor = false;
		if (canal == getCanalDimmer()){
			dimmer = valor;
			actualizarColor = true;
		}else
			if (canal == getCanalR()){
				r = valor;
				actualizarColor = true;
			}
			else
				if (canal == getCanalG()){
					g = valor;
					actualizarColor = true;
				}				
				else
					if (canal == getCanalB()){
						b = valor;
						actualizarColor = true;
					}

		if (actualizarColor)
			try {
				
				
				float[] hsbvals = new float[3];
				Color.RGBtoHSB(r, g, b, hsbvals);
				h = hsbvals[0];
				s = hsbvals[1];
				if (nivelDimmer[dimmer]>0)
					l = 1;
				else
					l = 0;

				pnlColor.setBackground(new Color(Color.HSBtoRGB(h, s, l)));
				
			} catch (Exception e) {
				System.out.println("ERROR al actualizar colores: "+r+","+g+","+b);
			}		
	}

	@Override
	public void apagar() {
		
	}

	@Override
	public void encender() {
		
	}

}
