package com.clillo.dmx.tiempo;

import java.awt.Color;

import javax.swing.JTextField;

import com.clillo.dmx.aleatorios.AleatorioNormal;
import com.clillo.utiles.HoraUtil;
import com.clillo.utiles.Log;

public class RelojEncendidoApagado extends Thread{

	private NotificableEncendidoApagado notificable;
	private int segundos;
	private long miliSegundos;
	private JTextField txtTiempo;
	private String nombre;

	private boolean encendido;
	private boolean activo;

	private AleatorioNormal aleatorioEncendido;
	private AleatorioNormal aleatorioApagado;

	public RelojEncendidoApagado(int mediaEncendido, int desvEstandarEncendido, int mediaApagado, int desvEstandarApagado) {
		activo = false;
		
		aleatorioEncendido = new AleatorioNormal(mediaEncendido, desvEstandarEncendido);
		aleatorioApagado = new AleatorioNormal(mediaApagado, desvEstandarApagado);
		this.setName("Reloj Encendido Apagado");
	}
	
	public void setNotificable(NotificableEncendidoApagado notificable) {
		this.notificable = notificable;
	}

	public void setTxtTiempo(JTextField txtTiempo) {
		this.txtTiempo = txtTiempo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	private void calculaNuevoApagado(){
		segundos = aleatorioApagado.siguiente();
		miliSegundos = segundos * 1000;
		Log.debug(nombre+" apagar ["+HoraUtil.toString(miliSegundos)+"]", RelojEncendidoApagado.class);
	}

	private void calculaNuevoEncendido(){
		segundos = aleatorioEncendido.siguiente();
		miliSegundos = segundos * 1000;
		Log.debug(nombre + " encender ["+HoraUtil.toString(miliSegundos)+"]", RelojEncendidoApagado.class);
	}
	
	public synchronized void setActivo(boolean activo) {
		this.activo = activo;
		notify();
	}
	
	private void tic(){
		if (encendido){
			calculaNuevoApagado();
			txtTiempo.setBackground(Color.black);
			txtTiempo.setForeground(Color.lightGray);
			txtTiempo.setText(HoraUtil.toString(miliSegundos));
			encendido = false;
			notificable.apagar();
		}else{
			calculaNuevoEncendido();
			txtTiempo.setBackground(Color.white);
			txtTiempo.setForeground(Color.black);
			txtTiempo.setText(HoraUtil.toString(miliSegundos));
			encendido = true;
			notificable.encender();
		}
	}
	
	public void run() {		
		calculaNuevoApagado();

		txtTiempo.setBackground(Color.black);
		txtTiempo.setForeground(Color.lightGray);
		txtTiempo.setText(HoraUtil.toString(miliSegundos));
		encendido = false;
		notificable.apagar();

		while (true) {
			long tiempoInicial = System.currentTimeMillis();
			
			if (miliSegundos<=0){
				tic();
				continue;
			}
				
			try {
				sleep(1000);
			} catch (InterruptedException e) {
			}
			
			String str = HoraUtil.toString(miliSegundos);
			
			txtTiempo.setText(str);
			txtTiempo.setToolTipText("Faltan "+str+" para el encendido/apagado");

			miliSegundos = miliSegundos - (System.currentTimeMillis()-tiempoInicial);

			synchronized (this) {
				while (!activo) {
					try {
						wait();
					} catch (InterruptedException e) {
					}
					
				}
			}
		}
	}

}
