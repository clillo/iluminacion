package com.clillo.dmx.comm.vdj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.clillo.dmx.programas.EjecutorMultiplesProgramas;

public class VJReceptor extends Thread{

	private ServerSocket serverSocket = null;
	private ArrayList<VJListener> listaDeListeners = new ArrayList<VJListener>();
	private int indiceCuartoBeat;
	private int indiceMedioBeat;
	private int beatAnterior;
	
	public VJReceptor() {
		try {
			serverSocket = new ServerSocket(80);
		} catch (IOException e) {
			e.printStackTrace();
		}
		beatAnterior = 1;
		indiceCuartoBeat = 1;
	}
	
	public void agregaListener(VJListener listener){
		for (VJListener l: listaDeListeners)
			if (l==listener)
				return;
		listaDeListeners.add(listener);
	}
	
	public void eliminaListener(VJListener l){
		listaDeListeners.remove(l);
	}
	
	@Override
	public void run() {
		while (true){
			Socket clientSocket = null;
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e2) {
				e2.printStackTrace();
			}


			try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
	
				String inputLine;
					            
				while ((inputLine = in.readLine()) != null && !inputLine.equals("")) {
					double vuMeter = Double.parseDouble(inputLine.substring(0, 6));
					double posicion = Double.parseDouble(inputLine.substring(6, 11));
					double songPosBeats = Integer.parseInt(inputLine.substring(11, 15).trim());
					double beatCounter = Double.parseDouble(inputLine.substring(15, 19));
					String nombre = inputLine.substring(19);				

					for (VJListener l: listaDeListeners)
						l.cambiar(vuMeter, posicion, songPosBeats, beatCounter, nombre);
					
					int pp = 0;
					if (beatCounter<0.25)
					   pp = 1;
					else
						if (beatCounter<0.5)
							pp = 2;
						else
							if (beatCounter<0.75)
								pp = 3;
							else
								pp = 4;
					
					if (pp!=indiceCuartoBeat){
						indiceCuartoBeat = pp;
						for (VJListener l: listaDeListeners){
							//if (l instanceof EjecutorMultiplesProgramas)
							//	System.out.println(l.hashCode()+"\t"+indiceCuartoBeat);
							l.cuartoDeBeat(indiceCuartoBeat);
						}
					}
					
					pp = 0;
					if (beatCounter<0.5)
						pp = 1;
					else
						pp = 2;
					
					if (pp!=indiceMedioBeat){
						indiceMedioBeat = pp;
						for (VJListener l: listaDeListeners){
							//if (l instanceof EjecutorMultiplesProgramas)
							//	System.out.println(l.hashCode()+"\t"+indiceMedioBeat);
							l.medioBeat(indiceMedioBeat);
						}
						beatAnterior = 1;
					}
				
					if (beatCounter>0.9 && beatAnterior==1){
						for (VJListener l: listaDeListeners){
							//if (l instanceof EjecutorMultiplesProgramas)
							//	System.out.println(l.hashCode()+"\t"+indiceCuartoBeat);
							l.beat();
						}
						beatAnterior = 0;
					}
			
					out.println();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
