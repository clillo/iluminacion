package com.clillo.dmx.core.fixtures.robotizados;

import java.util.LinkedList;

import com.clillo.dmx.core.fixtures.FixtureRobotica;

public class ColaPosiciones implements Runnable  {

	private LinkedList<Punto> lista;
	private FixtureRobotica estacion;
	private ListenerFinMovimiento listenerFinMovimiento;
	
	public ColaPosiciones(FixtureRobotica estacion) {
		this.estacion = estacion;
		lista = new LinkedList<Punto>();
	}

	public int getIdEstacion(){
		return estacion.getCanalDMXInicial();
	}
	
	public synchronized boolean estaMoviendose(){
		return !lista.isEmpty();
	}
	
	public synchronized void agregar() {
		lista.addFirst(new Punto());
		notifyAll();
	}
	
	public synchronized void agregar(Punto punto) {
		lista.addLast(punto);
		notifyAll();
	}
	
	public synchronized void freeze() {
		while (!lista.isEmpty())
			lista.remove();
		notifyAll();
	}

	private synchronized Punto obtener() {
		while (lista.isEmpty()){
			try {
				estacion.setMoviendose(false);
				wait();
			} catch (InterruptedException e) {
			}
		}
		
		Punto ins = lista.getFirst();
		lista.removeFirst();
		
		if (lista.isEmpty()){
			estacion.setMoviendose(false);
			if (listenerFinMovimiento!=null)
				listenerFinMovimiento.finalizaMovimiento();
		}
		return ins;
	}

	public void run() {
		Punto instruccion;
		while (true) {
			instruccion = obtener();
			if (!instruccion.isFake()){
				estacion.saltarA(instruccion.getPosX(), instruccion.getPosY());
				estacion.setMoviendose(true);
			}
			
			try {
				Thread.sleep(instruccion.getVelocidad());
			} catch (InterruptedException e1) {
			}
		}
	}

	public void setListenerFinMovimiento(ListenerFinMovimiento listenerFinMovimiento) {
		this.listenerFinMovimiento = listenerFinMovimiento;
	}
}
