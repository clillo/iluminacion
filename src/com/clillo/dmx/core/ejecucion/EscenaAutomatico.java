package com.clillo.dmx.core.ejecucion;

import com.clillo.utiles.Archivos;

public class EscenaAutomatico {

	private String nombre;
	private String nombreArchivo;

	private int tiempoMaximo;
	private int tiempoMinimo;
	
	private int probabilidad;
	private int probabilidadGlobalNormalizada;
	
	private boolean laserMultiPunto;
	private boolean laserIlda;
	private boolean derby;
	private boolean ball;
	private boolean pinSpot;
	private boolean rGBW;
	private boolean rGBWConjuntas;
	private boolean movingHeads;	
	private boolean scanners;
	private boolean spider;
		
	public EscenaAutomatico(){
		
	}
	
	public void grabar(){
		StringBuilder sb = new StringBuilder();
		
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
		sb.append("<show>\n");
		sb.append("\t<generales>\n");
		sb.append("\t\t<nombre>");
		sb.append(nombre);
		sb.append("</nombre>\n");
		sb.append("\t\t<tiempoMaximo>");
		sb.append(tiempoMaximo);
		sb.append("</tiempoMaximo>\n");
		sb.append("\t\t<tiempoMinimo>");
		sb.append(tiempoMinimo);
		sb.append("</tiempoMinimo>\n");
		sb.append("\t\t<probabilidad>");
		sb.append(probabilidad);
		sb.append("</probabilidad>\n");
		sb.append("\t</generales>\n");
		sb.append("\t<fixtures>\n");
		
		if (scanners)			sb.append("\t\t<fixture>scanners</fixture>\n");
		if (movingHeads) 		sb.append("\t\t<fixture>movingHeads</fixture>\n");
		if (rGBW)				sb.append("\t\t<fixture>rgbw</fixture>\n");
		if (pinSpot)			sb.append("\t\t<fixture>pinspot</fixture>\n");
		if (derby)				sb.append("\t\t<fixture>derby1</fixture>\n");
		if (ball)				sb.append("\t\t<fixture>crystalBall</fixture>\n");
		if (laserMultiPunto)	sb.append("\t\t<fixture>laser1</fixture>\n");
		if (laserIlda)			sb.append("\t\t<fixture>laser2</fixture>\n");
		if (spider)			    sb.append("\t\t<fixture>spider1</fixture>\n");

		sb.append("\t</fixtures>\n");
		sb.append("</show>");
		
		Archivos.grabaArchivo(sb.toString(), nombreArchivo);
	}
		
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isScanners() {
		return scanners;
	}

	public void setScanners(boolean scanners) {
		this.scanners = scanners;
	}

	public boolean isMovingHeads() {
		return movingHeads;
	}

	public void setMovingHeads(boolean movingHeads) {
		this.movingHeads = movingHeads;
	}

	public boolean isrGBW() {
		return rGBW;
	}

	public void setrGBW(boolean rGBW) {
		this.rGBW = rGBW;
	}

	public boolean isrGBWConjuntas() {
		return rGBWConjuntas;
	}

	public void setrGBWConjuntas(boolean rGBWConjuntas) {
		this.rGBWConjuntas = rGBWConjuntas;
	}

	public boolean isPinSpot() {
		return pinSpot;
	}

	public void setPinSpot(boolean pinSpot) {
		this.pinSpot = pinSpot;
	}

	public boolean isDerby() {
		return derby;
	}

	public void setDerby(boolean derby) {
		this.derby = derby;
	}

	public boolean isBall() {
		return ball;
	}

	public void setBall(boolean ball) {
		this.ball = ball;
	}

	public boolean isLaserMultiPunto() {
		return laserMultiPunto;
	}

	public void setLaserMultiPunto(boolean laserMultiPunto) {
		this.laserMultiPunto = laserMultiPunto;
	}

	public boolean isLaserIlda() {
		return laserIlda;
	}

	public void setLaserIlda(boolean laserIlda) {
		this.laserIlda = laserIlda;
	}
	
	public int getTiempoMaximo() {
		return tiempoMaximo;
	}

	public void setTiempoMaximo(int tiempoMaximo) {
		this.tiempoMaximo = tiempoMaximo;
	}

	public int getTiempoMinimo() {
		return tiempoMinimo;
	}

	public void setTiempoMinimo(int tiempoMinimo) {
		this.tiempoMinimo = tiempoMinimo;
	}

	public int getProbabilidad() {
		return probabilidad;
	}

	public void setProbabilidad(int probabilidad) {
		this.probabilidad = probabilidad;
	}

	public int getProbabilidadGlobalNormalizada() {
		return probabilidadGlobalNormalizada;
	}

	public void setProbabilidadGlobalNormalizada(int probabilidadGlobalNormalizada) {
		this.probabilidadGlobalNormalizada = probabilidadGlobalNormalizada;
	}

	public boolean isSpider() {
		return spider;
	}

	public void setSpider(boolean spider) {
		this.spider = spider;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		if (scanners)			sb.append("Scanners + ");
		if (movingHeads) 		sb.append("MovingHeads + ");
		if (rGBW)				sb.append("Rgbw + ");
		if (pinSpot)			sb.append("Pinspot + ");
		if (derby)				sb.append("Derby + ");
		if (ball)				sb.append("Ball + ");
		if (laserMultiPunto)	sb.append("MultiPunto + ");
		if (laserIlda)			sb.append("ILDA + ");
		if (spider)			    sb.append("Spider + ");
		
		if (sb.length()>2)
			sb.delete(sb.length() - 2, sb.length());
		
		sb.append('[').append(probabilidadGlobalNormalizada).append(']');
		
		return sb.toString();
	}
}
