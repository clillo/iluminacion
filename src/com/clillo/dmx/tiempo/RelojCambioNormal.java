package com.clillo.dmx.tiempo;

import java.awt.Color;

import javax.swing.JTextField;

import com.clillo.dmx.aleatorios.AleatorioNormal;
import com.clillo.utiles.HoraUtil;
import com.clillo.utiles.Log;

public class RelojCambioNormal extends Thread{

	private NotificableCambio notificable;
	private int segundos;
	private long miliSegundos;
	private JTextField txtTiempo;
	private String nombre;
	
	private int minimoValorTiempo;

	private boolean activo;

	private AleatorioNormal aleatorio;

	public RelojCambioNormal(int media, int desvEstandar) {
		activo = false;
		aleatorio = new AleatorioNormal(media, desvEstandar);
		this.setName("Rejol Cambio Normal");
	}
	
	public void setNotificable(NotificableCambio notificable) {
		this.notificable = notificable;
	}

	public void setTxtTiempo(JTextField txtTiempo) {
		this.txtTiempo = txtTiempo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setMinimoValorTiempo(int minimoValorTiempo) {
		this.minimoValorTiempo = minimoValorTiempo;
	}

	private void calculaNuevoCambio(){
		segundos = aleatorio.siguiente();
		if (segundos<minimoValorTiempo)
			segundos = minimoValorTiempo;
		miliSegundos = segundos * 1000;
		Log.debug(nombre + " cambio ["+HoraUtil.toString(miliSegundos)+"]", RelojCambioNormal.class);
	}
	
	private void setColorTexto(){
		if (activo){
			txtTiempo.setBackground(Color.white);
			txtTiempo.setForeground(Color.black);
		}else{
			txtTiempo.setBackground(Color.black);
			txtTiempo.setForeground(Color.gray);
		}
	}
	
	public synchronized void setActivo(boolean activo) {
		this.activo = activo;
		setColorTexto();
		notify();
	}
	
	private void tic(){
		calculaNuevoCambio();
		txtTiempo.setText(HoraUtil.toString(miliSegundos));
		notificable.cambio(this.nombre);
	}
	
	public void run() {		
		calculaNuevoCambio();

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
			txtTiempo.setToolTipText("Faltan "+str+" para el cambio");

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
