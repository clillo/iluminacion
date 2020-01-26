package com.clillo.dmx.comm.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestTCP {

	public static void main(String[] args) throws Exception {
		for (int i=0;i< 100; i++){
			Socket socket = new Socket("192.168.100.103", 1000);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader (new InputStreamReader (socket.getInputStream ()));
		
			out.print("Hello from JAVA TCP Client");
			out.flush();
			
		    String fromServer = in.readLine ();
		     System.out.println ("Server: " + fromServer);
		
			in.close();
			out.close();
		    socket.close();
		//    Thread.sleep(2000);
		}
	}
}
