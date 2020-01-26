package com.clillo.bpm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.io.IOException;
import java.net.URLDecoder;

import org.apache.commons.lang.StringEscapeUtils;

import com.clillo.utiles.Xml;

public class ExtraeBpmVirtualDJ {

	public static boolean comienzaConNumero(String nombre){	
		int pos = nombre.indexOf('.');
		if (pos<0)
			return false;
		
		try{
			Integer.parseInt(nombre.substring(0,pos-1).trim());
			return true;
		}catch(Exception e){
		}
		
		return false;
	}
	
	private static String getArchivo(String nombreArchivo) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(nombreArchivo));

		StringBuilder sb = new StringBuilder();
	    String linea;
	    linea = in.readLine();
		while(linea != null){
			sb.append(linea);
			linea = in.readLine();
		}
		
	    in.close();
	    return sb.toString();
	}
	
	private static void procesaArchivo(String contenido){
	    int pos = contenido.indexOf("<Song");
	    while (pos>=0){
	    	String song = Xml.getValorTAG(contenido, "<Song", "</Song>", pos);
	    	procesaCancion(song);
	    	pos = contenido.indexOf("<Song", pos+1);
	    }
	}
	
	private static String getBpm(String bpm){
		if (bpm==null)
			return "";
		
		if (!bpm.contains("."))
			bpm = bpm+".00";
		
		String tmp[] = bpm.split("\\.");
		
		int num = Integer.parseInt(tmp[0]);
		
		bpm = "";
		if (num<100)
			bpm = "0" + num;
		else
			bpm = "" + num;
		
		return bpm;
	}
	
	private static void procesaCancion(String song){

    	String path = Xml.getValorTAG(song, "FilePath=\"", "\"");
    	if (!path.startsWith("C:\\JDownloader\\"))
    		return;
    	
    	String bpm = Xml.getValorTAG(song, "Bpm=\"", "\"");
    	if(bpm.equals(""))
    		return ;
       	double dBpm = Double.parseDouble(bpm);

       	String strBpm = Math.round(((int)((60/dBpm)*100))/100.0 ) + "";
      
        path = StringEscapeUtils.unescapeXml(path);
     
       	int pos = path.lastIndexOf('\\');
        String nombreArchivo = path.substring(pos+1);
        String nombreDirectorio = path.substring(0, pos+1);
        String nuevoNombre =  nombreDirectorio + getBpm(strBpm)+" - " + nombreArchivo;

        if (existe(path))
        //	if (!comienzaConNumero(nombreArchivo))
        		System.out.println(getBpm(strBpm) + "\t"+mueve(path, nuevoNombre)+"\t"+path+"\t"+nuevoNombre);
        //	else
        	//	System.out.println("YA TIENE BPM\t" + path);
        else
        	System.out.println("NO EXISTE\t" + path);
	}
	
	private static boolean existe(String path){
		return new File(path).exists();
	}

	private static boolean mueve(String path, String nuevoNombre){
		return false;
	  //return new File(path).renameTo(new File(nuevoNombre));
	}
	
	public static void main(String[] args) throws IOException {
		String nombreArchivo = "C:\\Users\\carlos\\Documents\\VirtualDJ\\database.xml";
		String contenido = getArchivo(nombreArchivo);
		procesaArchivo(contenido);
	}
}
