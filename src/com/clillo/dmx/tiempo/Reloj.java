package com.clillo.dmx.tiempo;

public class Reloj extends Thread {

	private String nombre;
	private int milisegundos;
	private NotificableReloj gatillable;
	private boolean activo;

	public Reloj(String nombre, int milisegundos, NotificableReloj gatillable) {
		this.nombre = nombre;
		this.gatillable = null;
		this.setName(nombre);
		this.milisegundos = milisegundos;
		this.gatillable = gatillable;
		this.setName("Reloj "+nombre);
	}

	public void setMilisegundos(int milisegundos) {
		this.milisegundos = milisegundos;
	}

	public synchronized void setActivo(boolean activo) {
		this.activo = activo;
		notify();
	}

	public void run() {
		while (true) {
			try {
				sleep(milisegundos);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (activo)
				gatillable.tic(nombre);

			synchronized (this) {
				while (!activo) {
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
