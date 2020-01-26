package com.clillo.dmx.comm.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.LinkedList;

public class THHttp extends Thread {
	
	private LinkedList<MensajeHHTP> fifo = new LinkedList<MensajeHHTP>();

	private void enviarMensajeHHTP(MensajeHHTP mensaje){
		try {
			//System.out.println("Enviando: "+mensaje);
		    StringBuilder result = new StringBuilder();
		    URL url = new URL("http://"+mensaje.getIp()+"/"+mensaje.getMensaje());
		   
		      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		      conn.setConnectTimeout(5000);
		      conn.setReadTimeout(10000);
		      conn.setRequestMethod("GET");
		      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		      String line;
		      while ((line = rd.readLine()) != null) {
		         result.append(line);
		      }
		      rd.close();
		    // System.out.println(result.toString());
		} catch (UnknownHostException e) {
			System.err.println("ERROR al enviar mensaje HTTP: ["+mensaje.getMensaje()+"] a ["+mensaje.getIp()+"] "+e.getMessage());
		} catch (IOException e) {
			System.err.println("ERROR al enviar mensaje HTTP: ["+mensaje.getMensaje()+"] a ["+mensaje.getIp()+"] "+e.getMessage());
		}
	}
	
	public void encolaMensaje(MensajeHHTP msg){
		fifo.add(msg);
	}
	
	@Override
	public void run() {
		super.run();
		while (true){
			while (!fifo.isEmpty()){
				enviarMensajeHHTP(fifo.removeFirst());
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
			}
		}
	}
}
