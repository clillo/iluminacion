package com.clillo.dmx.comm.dmx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ComunicacionHttpDMX {

    protected ComunicacionHttpDMX() {}
    
    protected void stop(){
	}
	
	public void enviar(int canal, int dato){
		
		if (canal<0 || canal>512 || dato<0 || dato>255)
			return;
		
    	try {

  	      StringBuilder result = new StringBuilder();
  	  //    URL url = new URL("http://localhost:8080/dmx?canal="+String.valueOf(canal)+"&valor="+String.valueOf(dato));
  	    //  System.out.println(url);
  	   //   URL url = new URL("http://192.168.100.3:8082/dmx?canal="+String.valueOf(canal)+"&valor="+String.valueOf(dato));
  	    URL url = new URL("http://192.168.100.13:8082/dmx?canal="+String.valueOf(canal)+"&valor="+String.valueOf(dato));
  	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
  	      conn.setRequestMethod("GET");
  	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
  	      String line;
  	      while ((line = rd.readLine()) != null) {
  	         result.append(line);
  	      }
  	      rd.close();
  	 	      
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}	
}