package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas;

import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.FixtureRobotica;

public class NodoEscena {

	public enum FixMovingHead {MvHd1_90, MvHd2_90, MvHd1_60, MvHd2_60 };
	
	private FixMovingHead movingHead;
	
	private FixtureRobotica entidad;
	
	private String nombre;
	private int x;
	private int y;
	
	private double pan;
	private double tilt;
	
	private int pan1;
	private int pan2;
	
	private int tilt1;
	private int tilt2;
	
	private JTextField txtPan;
	private JTextField txtTilt;
	private JSpinner spiPan;
	private JSpinner spiPanFine;
	private JSpinner spiTilt;
	private JSpinner spiTiltFine;
	
	private boolean seleccionado;
	
	private int dimmer;
	
	public int getDimmer() {
		return dimmer;
	}
	public void setDimmer(int dimmer) {
		this.dimmer = dimmer;
	}
	public boolean isSeleccionado() {
		return seleccionado;
	}
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	public FixtureRobotica getEntidad() {
		return entidad;
	}
	public void setEntidad(FixtureRobotica entidad) {
		this.entidad = entidad;
	}
	public FixMovingHead getMovingHead() {
		return movingHead;
	}
	public void setMovingHead(FixMovingHead movingHead) {
		this.movingHead = movingHead;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getPan() {
		return pan;
	}
	public void setPan(double pan) {
		this.pan = pan;
	}
	public double getTilt() {
		return tilt;
	}
	public void setTilt(double tilt) {
		this.tilt = tilt;
	}
	public int getPan1() {
		return pan1;
	}
	public void setPan1(int pan1) {
		this.pan1 = pan1;
	}
	public int getPan2() {
		return pan2;
	}
	public void setPan2(int pan2) {
		this.pan2 = pan2;
	}
	public int getTilt1() {
		return tilt1;
	}
	public void setTilt1(int tilt1) {
		this.tilt1 = tilt1;
	}
	public int getTilt2() {
		return tilt2;
	}
	public void setTilt2(int tilt2) {
		this.tilt2 = tilt2;
	}
	public JTextField getTxtPan() {
		return txtPan;
	}
	public void setTxtPan(JTextField txtPan) {
		this.txtPan = txtPan;
	}
	public JTextField getTxtTilt() {
		return txtTilt;
	}
	public void setTxtTilt(JTextField txtTilt) {
		this.txtTilt = txtTilt;
	}
	public JSpinner getSpiPan() {
		return spiPan;
	}
	public void setSpiPan(JSpinner spiPan) {
		this.spiPan = spiPan;
	}
	public JSpinner getSpiPanFine() {
		return spiPanFine;
	}
	public void setSpiPanFine(JSpinner spiPanFine) {
		this.spiPanFine = spiPanFine;
	}
	public JSpinner getSpiTilt() {
		return spiTilt;
	}
	public void setSpiTilt(JSpinner spiTilt) {
		this.spiTilt = spiTilt;
	}
	public JSpinner getSpiTiltFine() {
		return spiTiltFine;
	}
	public void setSpiTiltFine(JSpinner spiTiltFine) {
		this.spiTiltFine = spiTiltFine;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NodoEscena [movingHead=");
		builder.append(movingHead);
		builder.append(", entidad=");
		builder.append(entidad);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", x=");
		builder.append(x);
		builder.append(", y=");
		builder.append(y);
		builder.append(", pan=");
		builder.append(pan);
		builder.append(", tilt=");
		builder.append(tilt);
		builder.append(", pan1=");
		builder.append(pan1);
		builder.append(", pan2=");
		builder.append(pan2);
		builder.append(", tilt1=");
		builder.append(tilt1);
		builder.append(", tilt2=");
		builder.append(tilt2);
		builder.append(", txtPan=");
		builder.append(txtPan);
		builder.append(", txtTilt=");
		builder.append(txtTilt);
		builder.append(", spiPan=");
		builder.append(spiPan);
		builder.append(", spiPanFine=");
		builder.append(spiPanFine);
		builder.append(", spiTilt=");
		builder.append(spiTilt);
		builder.append(", spiTiltFine=");
		builder.append(spiTiltFine);
		builder.append(", seleccionado=");
		builder.append(seleccionado);
		builder.append(", dimmer=");
		builder.append(dimmer);
		builder.append("]");
		return builder.toString();
	}
	
}
