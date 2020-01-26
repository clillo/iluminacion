package com.clillo.dmx.programas.conversorCanal;

import com.clillo.dmx.configuracion.GeneralesCfg;

public class ConversorCanalDimmerMovingHeads extends ConversorCanal{

	private static final int minimo=0;
	private static final int maximo=255;
	
	private static double factorDimmer=1;
	
	static{
		GeneralesCfg.carga();
	}
	
	@Override
	public int getValorCanal(int valor) {
		int maximoNivel = maximo - minimo;
		int convertido = ( (int)(valor * factorDimmer) * maximoNivel)/100 + minimo; 
		//System.out.println(valor + "\t" + convertido);
	 	return convertido;
	}

	public static double getFactorDimmer() {
		return factorDimmer;
	}

	public static void setFactorDimmer(double factorDimmer) {
		ConversorCanalDimmerMovingHeads.factorDimmer = factorDimmer;
	}

}
