package com.clillo.dmx.catalogo;

import java.util.ArrayList;
import java.util.Random;

import com.clillo.utiles.Archivos;
import com.clillo.utiles.UtilException;
import com.clillo.utiles.Xml;

public class Colores {

	private static ArrayList<ColorTO> listaColoresExtendida;
	private static ArrayList<ColorTO> listaColoresReducidos;
	private static ArrayList<ColorTO> listaColoresBasicos;

	private static Random randomColoresBasicos = new Random();
	private static Random randomColoresReducidos = new Random();

	static{
		listaColoresExtendida = new ArrayList<ColorTO>();
		listaColoresReducidos = new ArrayList<ColorTO>();
		listaColoresBasicos = new ArrayList<ColorTO>();

		try {
			String coloresXML = Archivos.getTexto("conf/colores.xml");
			
			ArrayList<String> lista = Xml.obtieneListaTAG(coloresXML, "color");
			for (String str: lista)
				listaColoresExtendida.add(new ColorTO(str));
			
			coloresXML = Archivos.getTexto("conf/coloresReducidos.xml");
			
			lista = Xml.obtieneListaTAG(coloresXML, "color");
			for (String str: lista)
				listaColoresReducidos.add(new ColorTO(str));

			coloresXML = Archivos.getTexto("conf/coloresBasicos.xml");
			
			lista = Xml.obtieneListaTAG(coloresXML, "color");
			for (String str: lista)
				listaColoresBasicos.add(new ColorTO(str));
			
		} catch (UtilException e) {
			e.printStackTrace();
		}	
	}
		
	public static ArrayList<ColorTO> getListaColoresBasicos() {
		return listaColoresBasicos;
	}
	
	public static ColorTO getColorBasicoRandom() {
		return listaColoresBasicos.get(randomColoresBasicos.nextInt(listaColoresBasicos.size()));
	}


	public static ArrayList<ColorTO> getListaColoresExtendida() {
		return listaColoresExtendida;
	}
	
	public static ArrayList<ColorTO> getListaColoresReducidos() {
		return listaColoresReducidos;
	}
	
	public static ColorTO getColorReducidoRandom() {
		return listaColoresReducidos.get(randomColoresReducidos.nextInt(listaColoresReducidos.size()));
	}

	public static void guardaListaColoresExtendida(){
		int n=1;
		for (ColorTO to: listaColoresExtendida){
			to.setId(n++);
			System.out.println(to.toXML());
		}
	}

	public static void guardaListaColoresReducidos(){
		int n=1;
		for (ColorTO to: listaColoresBasicos){
			to.setId(n++);
			System.out.println(to.toXML());
		}
	}
	
	public static void guardaListaColoresBasicos(){
		int n=1;
		for (ColorTO to: listaColoresBasicos){
			to.setId(n++);
			System.out.println(to.toXML());
		}
	}

	public static void main(String[] args) {
		//guardaListaColoresExtendida();
		guardaListaColoresReducidos();
	//	guardaListaColoresBasicos();
	}
}
