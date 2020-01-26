package com.clillo.dmx.aleatorios;

import java.util.Random;

/**
 * Clase que permite entregar números aleatorios que cumplen la distribución normal
 * @author clillo
 *
 */
public class AleatorioNormal {
	
	private int media;
	private int desviacionEstandar;
	
	private Random random = new Random();

	public AleatorioNormal(int media, int desviacionEstandar) {
		super();
		this.media = media;
		this.desviacionEstandar = desviacionEstandar;
	}
	
	public int siguiente(){
		double d = random.nextGaussian() * desviacionEstandar + media;
		if (d<0)
			d=0;

		return (int)d;
	}
	
	public static void main(String[] args) {
		AleatorioNormal a = new AleatorioNormal(30, 10);
		for (int i=0; i<10; i++)
			System.out.println(a.siguiente());
	}
}
