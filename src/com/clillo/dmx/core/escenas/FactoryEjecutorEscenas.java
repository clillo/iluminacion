package com.clillo.dmx.core.escenas;

public class FactoryEjecutorEscenas {

	private static EjecutorEscenas ejecutor;
	
	static{
		ejecutor = new EjecutorEscenas();
		ejecutor.setName("Thread Ejecutor Escenas");
		ejecutor.start();
	}
	
	public static EjecutorEscenas getEjecutor(){
		return ejecutor;
	}
}
