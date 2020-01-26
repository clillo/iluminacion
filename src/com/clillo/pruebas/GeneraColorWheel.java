package com.clillo.pruebas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.clillo.dmx.catalogo.ColorTO;
import com.clillo.dmx.configuracion.ColoresCfg;

public class GeneraColorWheel {
	
	public static void agregaValor(BufferedWriter writer, String canal, int valor) throws IOException{
		writer.write("<canal id=\""+canal+"\">");
		writer.write(""+valor);
		writer.write("</canal>");
	}

	public static void main(String[] args) throws IOException {
		ArrayList<ColorTO> coloresSuavizado = ColoresCfg.getColoresSuavizado();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter("conf/programas/colorWheel.xml"));
		writer.write("<programas>\n");

		writer.write("\t<programa id=\"1\">");
		writer.write("\n");

		int n=1;
		for (ColorTO color: coloresSuavizado){
			writer.write("\t\t<paso id=\""+n+"\">\n\t\t\t");
			agregaValor(writer, "rgbw1.r", color.getR());
			agregaValor(writer, "rgbw1.g", color.getG());
			agregaValor(writer, "rgbw1.b", color.getB());
			agregaValor(writer, "rgbw1.w", 0);
			writer.write("\n\t\t\t");
			
			agregaValor(writer, "rgbw2.r", color.getR());
			agregaValor(writer, "rgbw2.g", color.getG());
			agregaValor(writer, "rgbw2.b", color.getB());
			agregaValor(writer, "rgbw2.w", 0);
			writer.write("\n\t\t\t");
			
			agregaValor(writer, "rgbw3.r", color.getR());
			agregaValor(writer, "rgbw3.g", color.getG());
			agregaValor(writer, "rgbw3.b", color.getB());
			agregaValor(writer, "rgbw3.w", 0);
			writer.write("\n\t\t\t");
			
			agregaValor(writer, "rgbw4.r", color.getR());
			agregaValor(writer, "rgbw4.g", color.getG());
			agregaValor(writer, "rgbw4.b", color.getB());
			agregaValor(writer, "rgbw4.w", 0);
			writer.write("\n");
			
			writer.write("\t\t</paso>\n");
			n++;
		}

		writer.write("\t</programa>\n"); 

		writer.write("\t<programa id=\"2\">");
		writer.write("\n");

		n=1;
		for (int i=0; i<coloresSuavizado.size(); i++){
			writer.write("\t\t<paso id=\""+n+"\">\n\t\t\t");
			ColorTO color = coloresSuavizado.get(i);
			
			agregaValor(writer, "rgbw1.r", color.getR());
			agregaValor(writer, "rgbw1.g", color.getG());
			agregaValor(writer, "rgbw1.b", color.getB());
			agregaValor(writer, "rgbw1.w", 0);
			writer.write("\n\t\t\t");
			
			color = coloresSuavizado.get((i+10)%coloresSuavizado.size());

			agregaValor(writer, "rgbw2.r", color.getR());
			agregaValor(writer, "rgbw2.g", color.getG());
			agregaValor(writer, "rgbw2.b", color.getB());
			agregaValor(writer, "rgbw2.w", 0);
			writer.write("\n\t\t\t");
			
			color = coloresSuavizado.get((i+20)%coloresSuavizado.size());

			agregaValor(writer, "rgbw3.r", color.getR());
			agregaValor(writer, "rgbw3.g", color.getG());
			agregaValor(writer, "rgbw3.b", color.getB());
			agregaValor(writer, "rgbw3.w", 0);
			writer.write("\n\t\t\t");
			
			color = coloresSuavizado.get((i+30)%coloresSuavizado.size());

			agregaValor(writer, "rgbw4.r", color.getR());
			agregaValor(writer, "rgbw4.g", color.getG());
			agregaValor(writer, "rgbw4.b", color.getB());
			agregaValor(writer, "rgbw4.w", 0);
			writer.write("\n");
			
			writer.write("\t\t</paso>\n");
			n++;
		}

		writer.write("\t</programa>\n"); 

		writer.write("\t<programa id=\"3\">");
		writer.write("\n");

		n=1;
		for (int i=0; i<coloresSuavizado.size(); i++){
			writer.write("\t\t<paso id=\""+n+"\">\n\t\t\t");
			ColorTO color = coloresSuavizado.get((i+30)%coloresSuavizado.size());
				
			agregaValor(writer, "rgbw1.r", color.getR());
			agregaValor(writer, "rgbw1.g", color.getG());
			agregaValor(writer, "rgbw1.b", color.getB());
			agregaValor(writer, "rgbw1.w", 0);
			writer.write("\n\t\t\t");
			
			color = coloresSuavizado.get((i+20)%coloresSuavizado.size());

			agregaValor(writer, "rgbw2.r", color.getR());
			agregaValor(writer, "rgbw2.g", color.getG());
			agregaValor(writer, "rgbw2.b", color.getB());
			agregaValor(writer, "rgbw2.w", 0);
			writer.write("\n\t\t\t");
			color = coloresSuavizado.get((i+10)%coloresSuavizado.size());

			agregaValor(writer, "rgbw3.r", color.getR());
			agregaValor(writer, "rgbw3.g", color.getG());
			agregaValor(writer, "rgbw3.b", color.getB());
			agregaValor(writer, "rgbw3.w", 0);
			writer.write("\n\t\t\t");
			color = coloresSuavizado.get(i);

			agregaValor(writer, "rgbw4.r", color.getR());
			agregaValor(writer, "rgbw4.g", color.getG());
			agregaValor(writer, "rgbw4.b", color.getB());
			agregaValor(writer, "rgbw4.w", 0);
			writer.write("\n");
			writer.write("\t\t</paso>\n");
			n++;
		}
		
		writer.write("\t</programa>\n"); 

		writer.write("\t<programa id=\"4\">");
		writer.write("\n");

		n=1;
		for (int i=0; i<coloresSuavizado.size(); i++){
			writer.write("\t\t<paso id=\""+n+"\">\n\t\t\t");
			ColorTO color = coloresSuavizado.get(i);
			
			agregaValor(writer, "rgbw1.r", color.getR());
			agregaValor(writer, "rgbw1.g", color.getG());
			agregaValor(writer, "rgbw1.b", color.getB());
			agregaValor(writer, "rgbw1.w", 0);
			writer.write("\n\t\t\t");

			agregaValor(writer, "rgbw4.r", color.getR());
			agregaValor(writer, "rgbw4.g", color.getG());
			agregaValor(writer, "rgbw4.b", color.getB());
			agregaValor(writer, "rgbw4.w", 0);
			writer.write("\n\t\t\t");
			color = coloresSuavizado.get(coloresSuavizado.size()-i-1);

			agregaValor(writer, "rgbw3.r", color.getR());
			agregaValor(writer, "rgbw3.g", color.getG());
			agregaValor(writer, "rgbw3.b", color.getB());
			agregaValor(writer, "rgbw3.w", 0);
			writer.write("\n\t\t\t");

			agregaValor(writer, "rgbw2.r", color.getR());
			agregaValor(writer, "rgbw2.g", color.getG());
			agregaValor(writer, "rgbw2.b", color.getB());
			agregaValor(writer, "rgbw2.w", 0);
			writer.write("\n");
			writer.write("\t\t</paso>\n");
			n++;
		}

		writer.write("\t</programa>\n"); 

		writer.write("\t<programa id=\"5\">");
		writer.write("\n");

		n=1;
		for (int i=0; i<coloresSuavizado.size(); i++){
			writer.write("\t\t<paso id=\""+n+"\">\n\t\t\t");
			ColorTO color = coloresSuavizado.get(i);
			
			agregaValor(writer, "rgbw1.r", color.getR());
			agregaValor(writer, "rgbw1.g", color.getG());
			agregaValor(writer, "rgbw1.b", color.getB());
			agregaValor(writer, "rgbw1.w", 0);
			writer.write("\n\t\t\t");

			agregaValor(writer, "rgbw3.r", color.getR());
			agregaValor(writer, "rgbw3.g", color.getG());
			agregaValor(writer, "rgbw3.b", color.getB());
			agregaValor(writer, "rgbw3.w", 0);
			writer.write("\n\t\t\t");

			color = coloresSuavizado.get(coloresSuavizado.size()-i-1);

			agregaValor(writer, "rgbw4.r", color.getR());
			agregaValor(writer, "rgbw4.g", color.getG());
			agregaValor(writer, "rgbw4.b", color.getB());
			agregaValor(writer, "rgbw4.w", 0);
			writer.write("\n\t\t\t");

			agregaValor(writer, "rgbw2.r", color.getR());
			agregaValor(writer, "rgbw2.g", color.getG());
			agregaValor(writer, "rgbw2.b", color.getB());
			agregaValor(writer, "rgbw2.w", 0);
			writer.write("\n");
			writer.write("\t\t</paso>\n");
			n++;
		}

		writer.write("\t</programa>\n"); 
		writer.write("</programas>");
		writer.close();

	}
}
