package com.clillo.ilda;

import java.util.StringTokenizer;

import com.clillo.utiles.Archivos;

public class ConversorCSV {

    
    private static String getBinario(double d){
    	return (int )d+"";
    }

    public static void main(String args[]) throws Exception {
    	String nombreArchivo = "3dframe.csv";
    	String contenido = Archivos.getTexto(nombreArchivo);
    	
    	StringTokenizer st = new StringTokenizer(contenido, "\n");
    	StringBuilder sb = new StringBuilder();
    	int n=0;
    	while (st.hasMoreTokens()){
    		String linea = st.nextToken();
    		n++;
    		if (n>2){
	    		//System.out.println(linea);
	    		String cc[] = linea.split(",");
	        	int x = (int) (IldaFormat.MAX_WIDTH/2 + Integer.parseInt(cc[1])) ;
	          	int y = (int) (IldaFormat.MAX_HEIGHT/2 + Integer.parseInt(cc[2])) ;
	            sb.append(getBinario(x / IldaFormat.MAX_WIDTH * 255)).append(' ' );
	            sb.append(getBinario(y /IldaFormat.MAX_HEIGHT*255)).append(' ' );
	            sb.append((Integer.parseInt(cc[0])!=0?1:0)).append('\n');
    		}
    	}
    	
    	System.out.println("Lineas: "+n);
    	Archivos.grabaArchivo(sb.toString(), "test.txt");
    }
}
