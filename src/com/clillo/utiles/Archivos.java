package com.clillo.utiles;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Archivos {

	public static String getTexto(String nombreArchivo) throws UtilException{
		StringBuffer sb = new StringBuffer();
		try {
			String buf;
			BufferedReader in = new BufferedReader(new FileReader(nombreArchivo));

			if ((buf = in.readLine()) == null) {
				in.close();
				throw new UtilException("Archivo no encontrado: "+nombreArchivo);
			}
			
			while (buf != null) {
				sb.append(buf);
				sb.append('\n');
				buf = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			throw new UtilException(e);
		}

		return sb.toString();
	}

	public static boolean grabaArchivo(String texto, String archivoSalida){
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(archivoSalida);		
			fos.write(texto.getBytes("UTF-8"));
			fos.flush();
			fos.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
