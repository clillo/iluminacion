package com.clillo.dmx.programas;

public class EjecutorMultiplesProgramasSingleton {

	private static EjecutorMultiplesProgramas ejecutor;
	
	static{
		ejecutor = new EjecutorMultiplesProgramas();
		ejecutor.setName("Thread Ejecutor Multiples Programas");
		ejecutor.start();
	}
	
	public static EjecutorMultiplesProgramas getEjecutor(){
		return ejecutor;
	}
}
