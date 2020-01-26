package com.clillo.dmx.configuracion.programas;

import com.clillo.dmx.aleatorios.AleatorioNormal;
import com.clillo.dmx.tiempo.RelojTO;
import com.clillo.utiles.Log;

public class ParametrosEjecucionProgramaTO {

	private String id;
	
	private int probabilidadGlobal;
	private int probabilidadGlobalNormalizada;

	private int tiempoEncendidoMedia;
	private int tiempoEncendidoDevSta;

	private int cantidadEjecuciones;
	private long sumaTiempos;
	
	private long horaComienzoPrograma;
	private RelojTO reloj;
	
	private AleatorioNormal aleatorioSiguiente;
	
	private boolean primeraEjecucion;
	
	public void comienza(){
		if (aleatorioSiguiente==null)
			aleatorioSiguiente = new AleatorioNormal(tiempoEncendidoMedia, tiempoEncendidoDevSta);
		
		long tiempoEjecucion = aleatorioSiguiente.siguiente();
		
		Log.debug("Comenzando programa " +  id + " se ejecutara por "+tiempoEjecucion+" ms.", this.getClass());
		horaComienzoPrograma = System.currentTimeMillis();
		long actual = System.currentTimeMillis();
		reloj.setSiguienteEjecucion(actual+tiempoEjecucion);
		primeraEjecucion = true;
	}
	
	public void termina(){
		long tiempo = System.currentTimeMillis() - horaComienzoPrograma;
		horaComienzoPrograma = -1;
		sumaTiempos+=tiempo;
		Log.debug("Terminando programa "+id+" ejecutado por "+tiempo+" ms. total: "+sumaTiempos+" ejecuciones: "+cantidadEjecuciones, this.getClass());
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public int getProbabilidadGlobal() {
		return probabilidadGlobal;
	}
	public void setProbabilidadGlobal(int probabilidadGlobal) {
		this.probabilidadGlobal = probabilidadGlobal;
	}
	public int getTiempoEncendidoMedia() {
		return tiempoEncendidoMedia;
	}
	public void setTiempoEncendidoMedia(int tiempoEncendidoMedia) {
		this.tiempoEncendidoMedia = tiempoEncendidoMedia;
	}
	public int getTiempoEncendidoDevSta() {
		return tiempoEncendidoDevSta;
	}
	public void setTiempoEncendidoDevSta(int tiempoEncendidoDevSta) {
		this.tiempoEncendidoDevSta = tiempoEncendidoDevSta;
	}
	public int getProbabilidadGlobalNormalizada() {
		return probabilidadGlobalNormalizada;
	}
	public void setProbabilidadGlobalNormalizada(int probabilidadGlobalNormalizada) {
		this.probabilidadGlobalNormalizada = probabilidadGlobalNormalizada;
	}
	public int getCantidadEjecuciones() {
		return cantidadEjecuciones;
	}
	public void setCantidadEjecuciones(int cantidadEjecuciones) {
		this.cantidadEjecuciones = cantidadEjecuciones;
	}
	public long getSumaTiempos() {
		return sumaTiempos;
	}
	public void setSumaTiempos(long sumaTiempos) {
		this.sumaTiempos = sumaTiempos;
	}
	public RelojTO getReloj() {
		return reloj;
	}
	public void setReloj(RelojTO reloj) {
		this.reloj = reloj;
	}
	public boolean isPrimeraEjecucion() {
		return primeraEjecucion;
	}
	public void setPrimeraEjecucion(boolean primeraEjecucion) {
		this.primeraEjecucion = primeraEjecucion;
	}
}
