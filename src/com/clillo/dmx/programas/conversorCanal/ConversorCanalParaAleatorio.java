package com.clillo.dmx.programas.conversorCanal;

import java.util.Random;

public class ConversorCanalParaAleatorio extends ConversorCanal{

	private static final int minimo=10;
	private static final int maximo=149;
	
	private static Random random = new Random();
	
	@Override
	public int getValorCanal(int valor) {
		if (valor>=0)
			return valor;
		
	 	return random.nextInt(maximo-minimo) + minimo;
	}
}
