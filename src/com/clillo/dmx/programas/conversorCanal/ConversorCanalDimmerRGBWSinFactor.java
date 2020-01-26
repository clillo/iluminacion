package com.clillo.dmx.programas.conversorCanal;

public class ConversorCanalDimmerRGBWSinFactor extends ConversorCanal{

	private static final int minimo=0;
	private static final int maximo=255;
	
	@Override
	public int getValorCanal(int valor) {
		int maximoNivel = maximo - minimo;
	 	return ( (int)(valor) * maximoNivel)/100 + minimo;
	}

}
