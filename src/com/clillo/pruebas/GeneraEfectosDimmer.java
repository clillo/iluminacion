package com.clillo.pruebas;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GeneraEfectosDimmer {
	
	public static void agregaValor(BufferedWriter writer, String canal, int valor) throws IOException{
		writer.write("<canal id=\""+canal+"\">");
		writer.write(""+valor);
		writer.write("</canal>\n");
	}

	public static void main(String[] args) throws IOException {
	
		BufferedWriter writer = new BufferedWriter(new FileWriter("conf/programas/dimmer.xml"));


		for (int n=0; n<100; n+=1){
			writer.write("<paso id=\""+n+"\">");
			agregaValor(writer, "movingHead1.dim", n);
			agregaValor(writer, "movingHead2.dim", n);

			writer.write("</paso>\n");
		}

		for (int n=100; n>=0; n-=1){
			writer.write("<paso id=\""+n+"\">");
			agregaValor(writer, "movingHead1.dim", n);
			agregaValor(writer, "movingHead2.dim", n);

			writer.write("</paso>\n");
		}

		writer.write("</programa>\n"); 
		writer.close();

	}
}
