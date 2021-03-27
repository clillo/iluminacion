package com.clillo.dmx.ui.jpanels.opcionesMenu.escenas;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.core.ListaFixtures;

public class PuntoRoboticas {

	private String id;
	
	private int movingHead1Pan;
	private int movingHead1Tilt;
	private int movingHead1PanFine;
	private int movingHead1TiltFine;
	private int movingHead2Pan;
	private int movingHead2Tilt;
	private int movingHead2PanFine;
	private int movingHead2TiltFine;

	private int scanner1Pan;
	private int scanner1Tilt;
	private int scanner2Pan;
	private int scanner2Tilt;
	private int scanner3Pan;
	private int scanner3Tilt;
	private int scanner4Pan;
	private int scanner4Tilt;
	
	public void mueveA(){
		Dmx.enviar(ListaFixtures.obtieneCanalDMX("movingHead1.pan"), movingHead1Pan);
		Dmx.enviar(ListaFixtures.obtieneCanalDMX("movingHead1.tilt"), movingHead1Tilt);
		Dmx.enviar(ListaFixtures.obtieneCanalDMX("movingHead1.panfine"), movingHead1PanFine);
		Dmx.enviar(ListaFixtures.obtieneCanalDMX("movingHead1.tiltfine"), movingHead1TiltFine);
		Dmx.enviar(ListaFixtures.obtieneCanalDMX("movingHead2.pan"), movingHead2Pan);
		Dmx.enviar(ListaFixtures.obtieneCanalDMX("movingHead2.tilt"), movingHead2Tilt);
		Dmx.enviar(ListaFixtures.obtieneCanalDMX("movingHead2.panfine"), movingHead2PanFine);
		Dmx.enviar(ListaFixtures.obtieneCanalDMX("movingHead2.tiltfine"), movingHead2TiltFine);


		Dmx.enviar(ListaFixtures.obtieneCanalDMX("scanner3.pan"), scanner3Pan);
		Dmx.enviar(ListaFixtures.obtieneCanalDMX("scanner3.tilt"), scanner3Tilt);
		Dmx.enviar(ListaFixtures.obtieneCanalDMX("scanner4.pan"), scanner4Pan);
		Dmx.enviar(ListaFixtures.obtieneCanalDMX("scanner4.tilt"), scanner4Tilt);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMovingHead1Pan() {
		return movingHead1Pan;
	}
	public void setMovingHead1Pan(int movingHead1Pan) {
		this.movingHead1Pan = movingHead1Pan;
	}
	public int getMovingHead1Tilt() {
		return movingHead1Tilt;
	}
	public void setMovingHead1Tilt(int movingHead1Tilt) {
		this.movingHead1Tilt = movingHead1Tilt;
	}
	public int getMovingHead2Pan() {
		return movingHead2Pan;
	}
	public void setMovingHead2Pan(int movingHead2Pan) {
		this.movingHead2Pan = movingHead2Pan;
	}
	public int getMovingHead2Tilt() {
		return movingHead2Tilt;
	}
	public void setMovingHead2Tilt(int movingHead2Tilt) {
		this.movingHead2Tilt = movingHead2Tilt;
	}
	public int getScanner1Pan() {
		return scanner1Pan;
	}
	public void setScanner1Pan(int scanner1Pan) {
		this.scanner1Pan = scanner1Pan;
	}
	public int getScanner1Tilt() {
		return scanner1Tilt;
	}
	public void setScanner1Tilt(int scanner1Tilt) {
		this.scanner1Tilt = scanner1Tilt;
	}
	public int getScanner2Pan() {
		return scanner2Pan;
	}
	public void setScanner2Pan(int scanner2Pan) {
		this.scanner2Pan = scanner2Pan;
	}
	public int getScanner2Tilt() {
		return scanner2Tilt;
	}
	public void setScanner2Tilt(int scanner2Tilt) {
		this.scanner2Tilt = scanner2Tilt;
	}
	public int getScanner3Pan() {
		return scanner3Pan;
	}
	public void setScanner3Pan(int scanner3Pan) {
		this.scanner3Pan = scanner3Pan;
	}
	public int getScanner3Tilt() {
		return scanner3Tilt;
	}
	public void setScanner3Tilt(int scanner3Tilt) {
		this.scanner3Tilt = scanner3Tilt;
	}
	public int getScanner4Pan() {
		return scanner4Pan;
	}
	public void setScanner4Pan(int scanner4Pan) {
		this.scanner4Pan = scanner4Pan;
	}
	public int getScanner4Tilt() {
		return scanner4Tilt;
	}
	public void setScanner4Tilt(int scanner4Tilt) {
		this.scanner4Tilt = scanner4Tilt;
	}

	public int getMovingHead1PanFine() {
		return movingHead1PanFine;
	}

	public void setMovingHead1PanFine(int movingHead1PanFine) {
		this.movingHead1PanFine = movingHead1PanFine;
	}

	public int getMovingHead1TiltFine() {
		return movingHead1TiltFine;
	}

	public void setMovingHead1TiltFine(int movingHead1TiltFine) {
		this.movingHead1TiltFine = movingHead1TiltFine;
	}

	public int getMovingHead2PanFine() {
		return movingHead2PanFine;
	}

	public void setMovingHead2PanFine(int movingHead2PanFine) {
		this.movingHead2PanFine = movingHead2PanFine;
	}

	public int getMovingHead2TiltFine() {
		return movingHead2TiltFine;
	}

	public void setMovingHead2TiltFine(int movingHead2TiltFine) {
		this.movingHead2TiltFine = movingHead2TiltFine;
	}

	@Override
	public String toString() {
		return id;
	}
}
