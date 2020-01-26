package com.clillo.dmx.core.show;

public class EjecutorShowSingleton {

	private static EjecutorShow ejecutor;
	
	static{
		ejecutor = new EjecutorShow();
		ejecutor.setName("Thread Ejecutor Show");
		ejecutor.start();
	}
	
	public static EjecutorShow getEjecutor(){
		return ejecutor;
	}
}
