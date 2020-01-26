package com.clillo.dmx.programas.conversorCanal;

import com.clillo.dmx.configuracion.GeneralesCfg;

public class ConversorCanalDimmerRGBW extends ConversorCanal{

	private static final int minimo=0;
	private static final int maximo=255;
	
	private static double factorDimmer=1;
	
	static{
		GeneralesCfg.carga();
	}
	
	@Override
	public int getValorCanal(int valor) {
		int maximoNivel = maximo - minimo;
	 	return ( (int)(valor * factorDimmer) * maximoNivel)/100 + minimo;
	}

	public static double getFactorDimmer() {
		return factorDimmer;
	}

	public static void setFactorDimmer(double factorDimmer) {
		ConversorCanalDimmerRGBW.factorDimmer = factorDimmer;
	}

}
