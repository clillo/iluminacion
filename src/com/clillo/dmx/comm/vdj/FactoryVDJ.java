package com.clillo.dmx.comm.vdj;

public class FactoryVDJ {

	private static VJReceptor ejecutor;
	
	static{
		ejecutor = new VJReceptor();
		ejecutor.setName("Thread Receptor Virtual DJ");
		ejecutor.start();
	}
	
	public static void init(){}
	
	public static void agregaListener(VJListener listener){
		ejecutor.agregaListener(listener);
	}
	
	public static void eliminaListener(VJListener listener){
		ejecutor.eliminaListener(listener);
	}
}
