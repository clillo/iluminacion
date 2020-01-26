package com.clillo.pruebas;

import com.clillo.dmx.comm.dmx.Dmx;

public class PruebaComm {

	public static void main(String[] args) throws InterruptedException {
	//	for (int i=12; i<512; i++)
	//		Dmx.enviar(i, 150);
		for (int i=0; i<4; i++){
			Dmx.enviar(1, 10);
			Thread.sleep(1000);
			Dmx.enviar(1, 40);
			Thread.sleep(1000);
		}
		Dmx.stop();
	}
}
