package com.clillo.dmx.catalogo;

import java.awt.Color;
import java.io.Serializable;

import com.clillo.utiles.Xml;

public class ColorTO implements Serializable {

	private static final long serialVersionUID = 6731782698805669694L;

	private int id;
	private int r;
	private int g;
	private int b;
	private Color color;
	private String nombre;
	
	public ColorTO(int id, int r, int g, int b) {
		super();
		this.id = id;
		this.r = r;
		this.g = g;
		this.b = b;
		color = new Color(r, g, b);
	}
	
	public ColorTO(String xml){
		id = Xml.getValorAtributoEntero(xml, "id");
		nombre = Xml.getValorTAG(xml, "nombre");
		r = Xml.getValorTAGEntero(xml, "r");
		g = Xml.getValorTAGEntero(xml, "g");
		b = Xml.getValorTAGEntero(xml, "b");
		color = new Color(r, g, b);
	}

	public Color getColor(){
		return color;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String toXML() {
		StringBuilder builder = new StringBuilder();
		builder.append("<color id=\"");
		builder.append(id);
		builder.append("\"><nombre>");
		builder.append(nombre);
		builder.append("</nombre><r>");
		builder.append(r);
		builder.append("</r><g>");
		builder.append(g);
		builder.append("</g><b>");
		builder.append(b);
		builder.append("</b></color>");
		return builder.toString();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ColorTO [id=");
		builder.append(id);
		builder.append(", r=");
		builder.append(r);
		builder.append(", g=");
		builder.append(g);
		builder.append(", b=");
		builder.append(b);
		builder.append(", color=");
		builder.append(color);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + b;
		result = prime * result + g;
		result = prime * result + r;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColorTO other = (ColorTO) obj;
		if (b != other.b)
			return false;
		if (g != other.g)
			return false;
		if (r != other.r)
			return false;
		return true;
	}
}
