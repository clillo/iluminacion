package com.clillo.vdj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Receptor extends Thread{

	private ServerSocket serverSocket = null;
	VUListener listener;
	
	public Receptor(VUListener listener) {
		try {
			serverSocket = new ServerSocket(80);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.listener = listener;
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
			
			PrintWriter out = null;
			BufferedReader in = null;
			try {
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	
				String inputLine;
					            
				while ((inputLine = in.readLine()) != null && !inputLine.equals("")) {
					//System.out.println("["+inputLine+"]");
					

						double vuMeter = Double.parseDouble(inputLine.substring(0, 6));
					//	double rotation = Double.parseDouble(st.nextToken());
						double rotation = 0;
						double posicion = Double.parseDouble(inputLine.substring(6, 11));
						double songPosBeats = Integer.parseInt(inputLine.substring(11, 15).trim());
						double beatCounter = Double.parseDouble(inputLine.substring(15, 19));
				//		double beatCounter = 0;
						String nombre = "";
						
						 nombre = inputLine.substring(19);
						
//		System.out.println(vuMeter+"\t"+rotation+"\t"+posicion+"\t"+songPosBeats);
						 if(listener!=null)
							 listener.cambiar(vuMeter, rotation, posicion, songPosBeats, beatCounter, nombre);
					out.println();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if (in!=null)
					try {
						in.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				if (out!=null)
					try {
						out.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		}
	}
	
	public static void main(String[] args) {
		Receptor r = new Receptor(null);
		r.start();
	}
}
