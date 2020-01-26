package com.clillo.dmx.aleatorios;

import java.util.Random;

import com.clillo.utiles.Log;

public class AleatorioPrioridad {

	private Random random = new Random();
	private int[] probabilidades;
	private int sumatoriaProbabilidades;
	private long histograma[];
	private String nombre;
	private int ocurrencias;

	public AleatorioPrioridad(int[] probabilidades) {
		this.probabilidades = probabilidades;
		init();
	}
	
	public AleatorioPrioridad(int[] probabilidades, String nombre) {
		this.probabilidades = probabilidades;
		this.nombre = nombre;
		init();
	}
	
	private void init(){
		sumatoriaProbabilidades = 0;
		
		for (int i=0; i<probabilidades.length; i++)
			sumatoriaProbabilidades += probabilidades[i];

		histograma = new long[probabilidades.length];
		for (int i=0; i<probabilidades.length; i++)
			histograma[i]=0;
		
		ocurrencias = 0;
		
	//	Log.debug(nombre+ " ----------------------------------------------------------------", AleatorioPrioridad.class);
	//	for (int i=0; i<probabilidades.length; i++)
	//		Log.debug(i+"\t"+probabilidades[i]+"\t"+probabilidades[i]/(sumatoriaProbabilidades*1.0)*100, AleatorioPrioridad.class);
	}
	
	public double[] getPorcentajes(){
		double[] salida = new double[probabilidades.length];
		for (int i=0; i<probabilidades.length; i++)
			salida[i] = probabilidades[i]/(sumatoriaProbabilidades*1.0)*100.0;
		return salida;
	}

	public int siguiente(){
		if (nombre!=null){
			ocurrencias++;
			if (ocurrencias>100){
				imprimeHistograma();
				ocurrencias=0;
			}
		}

		int dado = random.nextInt(sumatoriaProbabilidades);
		int suma = 0;
		for (int i=0; i<probabilidades.length; i++){
			suma += probabilidades[i];
			if (dado<suma){
				histograma[i]++;
				return i;
			}
		}
		histograma[0]++;
		return 0;
	}
	
	public void imprimeHistograma(){
		int suma = 0;
		
		for (int i=0; i<probabilidades.length; i++)
			suma += histograma[i];

		Log.debug("----------------------------------------------------------------", AleatorioPrioridad.class);
		Log.debug(nombre+" Porcentajes:", AleatorioPrioridad.class);
		
		for (int i=0; i<probabilidades.length; i++)
			Log.debug(i+"\t"+histograma[i]/(suma*1.0)*100, AleatorioPrioridad.class);

		Log.debug("----------------------------------------------------------------", AleatorioPrioridad.class);
	}

	public static void main(String[] args) {
		int[] probabilidades = new int[]{1, 1, 5, 0};
		
		AleatorioPrioridad a = new AleatorioPrioridad(probabilidades);

		for (int i=0; i<99999999; i++)
			a.siguiente();
		
		a.imprimeHistograma();
	}
}
