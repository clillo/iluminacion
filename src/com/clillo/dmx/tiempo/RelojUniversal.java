package com.clillo.dmx.tiempo;

public class RelojUniversal {

	private static final ManagerRelojes manager = new ManagerRelojes();
	
	public static void agregaReloj(RelojTO reloj){
		manager.agregaReloj(reloj);
	}
	
}
