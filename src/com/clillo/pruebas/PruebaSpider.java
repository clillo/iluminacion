package com.clillo.pruebas;

import com.clillo.dmx.comm.dmx.Dmx;

public class PruebaSpider {
	
	private static final int offsetA = 0;
	private static final int offsetB= 50;
	
	private static void enciendeA1(int r, int g, int b, int w){
		Dmx.enviar(offsetA + 10, r);  Dmx.enviar(offsetA + 11, g);  Dmx.enviar(offsetA + 12, b);  Dmx.enviar(offsetA + 13, w);
	}

	private static void enciendeA2(int r, int g, int b, int w){
		Dmx.enviar(offsetA + 14, r);  Dmx.enviar(offsetA + 15, g);  Dmx.enviar(offsetA + 16, b);  Dmx.enviar(offsetA + 17, w);
	}
	
	private static void enciendeA3(int r, int g, int b, int w){
		Dmx.enviar(offsetA + 18, r);  Dmx.enviar(offsetA + 19, g);  Dmx.enviar(offsetA + 20, b);  Dmx.enviar(offsetA + 21, w);
	}

	private static void enciendeA4(int r, int g, int b, int w){
		Dmx.enviar(offsetA + 22, r);  Dmx.enviar(offsetA + 23, g);  Dmx.enviar(offsetA + 24, b);  Dmx.enviar(offsetA + 25, w);
	}

	private static void enciendeA5(int r, int g, int b, int w){
		Dmx.enviar(offsetA + 26, r);  Dmx.enviar(offsetA + 27, g);  Dmx.enviar(offsetA + 28, b);  Dmx.enviar(offsetA + 29, w);
	}

	private static void enciendeA6(int r, int g, int b, int w){
		Dmx.enviar(offsetA + 30, r);  Dmx.enviar(offsetA + 31, g);  Dmx.enviar(offsetA + 32, b);  Dmx.enviar(offsetA + 33, w);
	}
	
	private static void enciendeA7(int r, int g, int b, int w){
		Dmx.enviar(offsetA + 34, r);  Dmx.enviar(offsetA + 35, g);  Dmx.enviar(offsetA + 36, b);  Dmx.enviar(offsetA + 37, w);
	}

	private static void enciendeA8(int r, int g, int b, int w){
		Dmx.enviar(offsetA + 38, r);  Dmx.enviar(offsetA + 39, g);  Dmx.enviar(offsetA + 40, b);  Dmx.enviar(offsetA + 41, w);
	}

	
	private static void enciendeB1(int r, int g, int b, int w){
		Dmx.enviar(offsetB + 4, r);  Dmx.enviar(offsetB + 5, g);  Dmx.enviar(offsetB + 6, b);  Dmx.enviar(offsetB + 7, w);
	}

	private static void enciendeB2(int r, int g, int b, int w){
		Dmx.enviar(offsetB + 8, r);  Dmx.enviar(offsetB + 9, g);  Dmx.enviar(offsetB + 10, b);  Dmx.enviar(offsetB + 11, w);
	}
	
	private static void enciendeB3(int r, int g, int b, int w){
		Dmx.enviar(offsetB + 12, r);  Dmx.enviar(offsetB + 13, g);  Dmx.enviar(offsetB + 14, b);  Dmx.enviar(offsetB + 15, w);
	}

	private static void enciendeB4(int r, int g, int b, int w){
		Dmx.enviar(offsetB + 16, r);  Dmx.enviar(offsetB + 17, g);  Dmx.enviar(offsetB + 18, b);  Dmx.enviar(offsetB + 19, w);
	}

	private static void enciendeB8(int r, int g, int b, int w){
		Dmx.enviar(offsetB + 20, r);  Dmx.enviar(offsetB + 21, g);  Dmx.enviar(offsetB + 22, b);  Dmx.enviar(offsetB + 23, w);
	}

	private static void enciendeB7(int r, int g, int b, int w){
		Dmx.enviar(offsetB + 24, r);  Dmx.enviar(offsetB + 25, g);  Dmx.enviar(offsetB + 26, b);  Dmx.enviar(offsetB + 27, w);
	}
	
	private static void enciendeB6(int r, int g, int b, int w){
		Dmx.enviar(offsetB + 28, r);  Dmx.enviar(offsetB + 29, g);  Dmx.enviar(offsetB + 30, b);  Dmx.enviar(offsetB + 31, w);
	}

	private static void enciendeB5(int r, int g, int b, int w){
		Dmx.enviar(offsetB + 32, r);  Dmx.enviar(offsetB + 33, g);  Dmx.enviar(offsetB + 34, b);  Dmx.enviar(offsetB + 35, w);
	}
	
	public static void enciende(char letra, int cual, MiColor color){
		int r=0;
		int g=0; 
		int b=0;
		int w=0;
		if (color == MiColor.ROJO)
			r=255;
		else
			if(color == MiColor.VERDE)
				g=255;
			else
				if(color == MiColor.AZUL)
					b=255;
				else
					if(color == MiColor.BLANCO)
						w=255;
		
		if(letra=='A' && cual==1) enciendeA1(r, g, b, w);
		if(letra=='A' && cual==2) enciendeA2(r, g, b, w);
		if(letra=='A' && cual==3) enciendeA3(r, g, b, w);
		if(letra=='A' && cual==4) enciendeA4(r, g, b, w);
		if(letra=='A' && cual==5) enciendeA5(r, g, b, w);
		if(letra=='A' && cual==6) enciendeA6(r, g, b, w);
		if(letra=='A' && cual==7) enciendeA7(r, g, b, w);
		if(letra=='A' && cual==8) enciendeA8(r, g, b, w);
		
		if(letra=='B' && cual==1) enciendeB1(r, g, b, w);
		if(letra=='B' && cual==2) enciendeB2(r, g, b, w);
		if(letra=='B' && cual==3) enciendeB3(r, g, b, w);
		if(letra=='B' && cual==4) enciendeB4(r, g, b, w);
		if(letra=='B' && cual==5) enciendeB5(r, g, b, w);
		if(letra=='B' && cual==6) enciendeB6(r, g, b, w);
		if(letra=='B' && cual==7) enciendeB7(r, g, b, w);
		if(letra=='B' && cual==8) enciendeB8(r, g, b, w);
	}

	public static void apaga(char letra, int cual){
		int r=0;
		int g=0; 
		int b=0;
		int w=0;
		
		if(letra=='A' && cual==1) enciendeA1(r, g, b, w);
		if(letra=='A' && cual==2) enciendeA2(r, g, b, w);
		if(letra=='A' && cual==3) enciendeA3(r, g, b, w);
		if(letra=='A' && cual==4) enciendeA4(r, g, b, w);
		if(letra=='A' && cual==5) enciendeA5(r, g, b, w);
		if(letra=='A' && cual==6) enciendeA6(r, g, b, w);
		if(letra=='A' && cual==7) enciendeA7(r, g, b, w);
		if(letra=='A' && cual==8) enciendeA8(r, g, b, w);
		
		if(letra=='B' && cual==1) enciendeB1(r, g, b, w);
		if(letra=='B' && cual==2) enciendeB2(r, g, b, w);
		if(letra=='B' && cual==3) enciendeB3(r, g, b, w);
		if(letra=='B' && cual==4) enciendeB4(r, g, b, w);
		if(letra=='B' && cual==5) enciendeB5(r, g, b, w);
		if(letra=='B' && cual==6) enciendeB6(r, g, b, w);
		if(letra=='B' && cual==7) enciendeB7(r, g, b, w);
		if(letra=='B' && cual==8) enciendeB8(r, g, b, w);
	}

	public static void escena1() throws InterruptedException{
		Dmx.enviar(offsetA + 5, 120);
		Dmx.enviar(offsetA + 6, 120);

		Dmx.enviar(offsetA + 8, 255); // dimmer
		
		Dmx.enviar(offsetB + 0, 120);
		Dmx.enviar(offsetB + 1, 120);

		Dmx.enviar(offsetB + 2, 255); // dimmer
		
		for(int i=1; i<=8; i++){
			enciende('A', i, MiColor.ROJO);
			enciende('B', i, MiColor.ROJO);
			Thread.sleep(500);
			apaga('A', i);
			apaga('B', i);
		}
	}

	public static void escena2() throws InterruptedException{
		Dmx.enviar(offsetA + 8, 255); // dimmer
		Dmx.enviar(offsetB + 2, 255); // dimmer

		enciende('A', 5, MiColor.ROJO);
		enciende('A', 6, MiColor.ROJO);
		enciende('A', 7, MiColor.ROJO);
		enciende('A', 8, MiColor.ROJO);
		Thread.sleep(1000);
		apaga('A', 5);
		apaga('A', 6);
		apaga('A', 7);
		apaga('A', 8);
	}
	
	public static void escena3() throws InterruptedException{
		int a=255;
		int w = a;
		int g = a;
		int b = a;
		int r = a;
		
		Dmx.enviar(offsetA + 8, 255); // dimmer
		
		enciendeA1(r, g, b, w);
		enciendeA2(r, g, b, w);
		enciendeA3(r, g, b, w);
		enciendeA4(r, g, b, w);
		
		
		enciendeA5(r, g, b, w);
		enciendeA6(r, g, b, w);
		enciendeA7(r, g, b, w);
		enciendeA8(r, g, b, w);
		
		Thread.sleep(10 * 1000);
		
		Dmx.enviar(offsetA + 8, 0); // dimmer

	}

	public static void main(String[] args) throws InterruptedException {
		for (int i=0; i<2; i++){
			escena1();
			Thread.sleep(1000);
		escena2();
			Thread.sleep(1000);
		}

		
		Dmx.stop();
	}
}
