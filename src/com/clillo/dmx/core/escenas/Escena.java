package com.clillo.dmx.core.escenas;

import java.util.ArrayList;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.comm.vdj.VJListener;
import com.clillo.dmx.configuracion.escenas.EscenasCfg;

public class Escena implements VJListener{

	private ListenerAvancePasosEscena listenerAvance;
	
	private String id;
	private String nombreArchivo;
	private String titulo;
	private String[] titulosPasos;
	private Object[][] datosTabla;
	
	private ArrayList<EscenaCanal> listaCanales;
	private ArrayList<EscenaPaso> listaPasos;
	private ArrayList<EscenaRobotica> listaRoboticas;
	
	private EscenaCanal tipoCanales; 

	private long siguienteEjecucion;
	private int indiceEjecucion;
	
	private int factor;
	private int velocidad;
	private int minimo;
	private int maximo;
	
	private boolean ejecutandoPorVDJ;
	
	public Escena() {
		indiceEjecucion = 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String[] getTitulosPasos() {
		return titulosPasos;
	}

	public Object[][] getDatosTabla() {
		return datosTabla;
	}
	
	public ArrayList<EscenaCanal> getListaCanales() {
		return listaCanales;
	}

	public long getSiguienteEjecucion() {
		return siguienteEjecucion;
	}

	public void setSiguienteEjecucion(long siguienteEjecucion) {
		this.siguienteEjecucion = siguienteEjecucion;
	}
	
	public void setFactor(int factor) {
		this.factor = factor;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public int getMaximo() {
		return maximo;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}

	public int getFactor() {
		return factor;
	}

	public int getVelocidad() {
		return velocidad;
	}
	
	public void setListenerAvance(ListenerAvancePasosEscena listenerAvance) {
		this.listenerAvance = listenerAvance;
	}
		
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
		
	public EscenaCanal getTipoCanales() {
		return tipoCanales;
	}

	public void setTipoCanales(EscenaCanal tipoCanales) {
		this.tipoCanales = tipoCanales;
	}

	public ArrayList<EscenaRobotica> getListaRoboticas() {
		return listaRoboticas;
	}

	public void setListaRoboticas(ArrayList<EscenaRobotica> listaRoboticas) {
		this.listaRoboticas = listaRoboticas;
	}
	
	public boolean isEjecutandoPorVDJ() {
		return ejecutandoPorVDJ;
	}

	public void setEjecutandoPorVDJ(boolean ejecutandoPorVDJ) {
		this.ejecutandoPorVDJ = ejecutandoPorVDJ;
	}

	public void agregaPaso(){
		EscenaPaso ep = new EscenaPaso();
		ep.setId(listaPasos.size()+1);
		ArrayList<EscenaCanal> lc = new ArrayList<EscenaCanal>();
		ep.setListaCanales(lc);
		
		for (EscenaCanal ap: listaCanales){
			EscenaCanal ec = new EscenaCanal();
			ec.setId(ap.getId());
			ec.setCanalStr(ap.getCanalStr());
			ec.setIdDMX(ap.getIdDMX());
			ec.setValor(0);
			
			lc.add(ec);
		}
		listaPasos.add(ep);
		setListaPasos(listaPasos);
		
		EscenasCfg.grabaCambios(this);
	}

	public void capturaValoresActuales(int nroPaso){
		for (EscenaCanal ec: listaPasos.get(nroPaso).getListaCanales()){
			int nuevoValor = Dmx.obtieneValorActualCanal(ec.getIdDMX());
	//		if (nuevoValor!=ec.getValor())
		//		System.out.println("Paso: "+nroPaso + " Nuevo: "+nuevoValor+" antiguo: "+ec.getValor());
			ec.setValor(nuevoValor);
		}
		
		setListaPasos(listaPasos);
		EscenasCfg.grabaCambios(this);
	}

	public void setSiguienteEjecucion() {
		if (listenerAvance!=null)
			listenerAvance.avanzaPaso(indiceEjecucion);
		
		for (EscenaCanal ec: listaPasos.get(indiceEjecucion).getListaCanales())
			Dmx.enviar(ec.getIdDMX(),ec.getValor());
		
		siguienteEjecucion = System.currentTimeMillis() + velocidad*factor/100;
		indiceEjecucion++;
		if (indiceEjecucion>=listaPasos.size())
			indiceEjecucion=0;
	}
	
	public int getIndiceEjecucion() {
		return indiceEjecucion+1;
	}

	public void setListaCanales(ArrayList<EscenaCanal> listaCanales) {
		this.listaCanales = listaCanales;
		int fixtures = listaCanales.size() + 1;
		
		titulosPasos = new String[fixtures];
		for (int i=0; i<fixtures; i++)
			if (i==0)
				titulosPasos[i] = "";
			else
				titulosPasos[i] = listaCanales.get(i-1).getCanalStr();
	}

	public ArrayList<EscenaPaso> getListaPasos() {
		return listaPasos;
	}

	public void setListaPasos(ArrayList<EscenaPaso> listaPasos) {
		int fixtures = listaCanales.size() + 1;
		this.listaPasos = listaPasos;
		int pasos = listaPasos.size();
		
		datosTabla = new Object[pasos][fixtures];
		
		for (int j=0; j<pasos; j++)
			for (int i=0; i<fixtures; i++)
				if (i==0)
					datosTabla[j][i] = listaPasos.get(j).getId();
				else
					datosTabla[j][i] = "";
		
		for (int j=0; j<pasos; j++)
			for (EscenaCanal ec: listaPasos.get(j).getListaCanales())
					datosTabla[j][ec.getId()] = ec;
	}

	@Override
	public String toString() {
		return titulo;
	}

	private double rotacionAnterior = 0;
	private double beatCounterAnterior = 0;
	private boolean sentido = false;
	
	@Override
	public void cambiar(double vuMeter,	double posicion, double songPosBeats, double beatCounter, String nombre){
		//System.out.println(Math.round(rotation*10) == rotation*10 );
	//	System.out.println(rotation +"\t"+ rotacionAnterior  +"\t" + (rotation - rotacionAnterior));
	/*	if (rotation - rotacionAnterior>0.2 || rotation < rotacionAnterior){
			System.out.println("tic");
			rotacionAnterior=rotation;
			setSiguienteEjecucion();
		}
*/
	//	System.out.println(beatCounter +"\t"+ beatCounterAnterior  +"\t" + (beatCounter - beatCounterAnterior));
		if (beatCounter>0.9 && !sentido ){
			sentido = true;
			//System.out.println("tic");
			setSiguienteEjecucion();
		}
		if (beatCounter<0.9)
			sentido = false;
	}

	@Override
	public void beat() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cuartoDeBeat(int cual) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void medioBeat(int cual) {
		// TODO Auto-generated method stub
		
	}
}
