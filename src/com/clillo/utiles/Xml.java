package com.clillo.utiles;

import java.util.ArrayList;

public class Xml {
	
	public static String getValorTAG(String xml, String inicio, String fin, int desde){
		int pos1 = xml.indexOf(inicio, desde);
		if (pos1<0)
			return "";
		int pos2 = xml.indexOf(fin, pos1+inicio.length());
		if (pos2<0)
			return "";
		return xml.substring(pos1+inicio.length(),pos2).trim();
	}

	public static String getValorTAG(String xml, String inicio, String fin){
		return getValorTAG(xml, inicio, fin, 0);
	}
	
	public static String getValorTAG(String xml, String tag){
		return getValorTAG(xml, '<'+tag+'>', "</"+tag+'>', 0);
	}

	public static int getValorTAGEntero(String xml, String tag){
		return Integer.parseInt(getValorTAG(xml, tag));
	}

	public static double getValorTAGDouble(String xml, String tag){
		return Double.parseDouble(getValorTAG(xml, tag));
	}

	public static String getValorAtributo(String xml, String atributo){
		return getValorTAG(xml, atributo+"=\"", "\"");
	}
	
	public static int getValorAtributoEntero(String xml, String atributo){
		return Integer.parseInt(getValorAtributo(xml, atributo));
	}

	public static ArrayList<String> obtieneListaTAG(String xml, String tag){
		ArrayList<String> lista = new ArrayList<String>();
		String inicio = '<'+tag+' ';
		String fin = "</"+tag+">";
		int pos = xml.indexOf(inicio);
		
		while (pos>=0) {
			String linea = getValorTAG(xml, inicio, fin, pos);
			lista.add(linea);
			pos = xml.indexOf(inicio, pos+1);
		}
		return lista;
	}
}
