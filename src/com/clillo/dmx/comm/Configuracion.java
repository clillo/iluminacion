package com.clillo.dmx.comm;

import java.io.File;

public class Configuracion {
	
	static{
		comunicacionHabilitada = !(new File("sincomm.txt").exists());
	}
	
	private static boolean comunicacionHabilitada;

	public static boolean estaComunicacionHabilitada(){
		return comunicacionHabilitada;
	}
}
