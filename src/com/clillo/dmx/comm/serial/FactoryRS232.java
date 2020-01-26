package com.clillo.dmx.comm.serial;


public class FactoryRS232 {

	private static Rs232 ejecutor;
	
	static{
		try {
			ejecutor = new Rs232();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void init(){}

	public static Rs232 getEjecutor() {
		return ejecutor;
	}
}
