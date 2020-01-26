package com.clillo.dmx.comm.tcp;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class HTTP {

	private static THHttp thHttp = new THHttp();
	private static ListaNotificables listaNotificables;

	static {
		thHttp.start();
    	listaNotificables = new ListaNotificables();
	}

	public static void enviarMensajeHHTP(String mensaje, String ip, int puerto) {
		if(true)
			return ;
		MensajeHHTP msg = new MensajeHHTP();
		msg.setIp(ip);
		msg.setPuerto(puerto);
		msg.setMensaje(mensaje);
		thHttp.encolaMensaje(msg);
		
		listaNotificables.enviar(ip, mensaje);
	}
	
	public static void registraEnviable(HTTPEnviable enviable, String ip){
		listaNotificables.registraEnviable(enviable, ip);
	}

	public static void main(String[] args) throws Exception {
		InetAddress ip;
		try {

			ip = InetAddress.getLocalHost();
			System.out.println("Current IP address : " + ip.getHostAddress());
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();

			System.out.print("Current MAC address : ");
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
			}
			System.out.println(sb.toString());

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}
