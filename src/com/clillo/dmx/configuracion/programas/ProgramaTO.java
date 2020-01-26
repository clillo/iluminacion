package com.clillo.dmx.configuracion.programas;

import java.util.ArrayList;

import com.clillo.dmx.core.escenas.Escena;
import com.clillo.dmx.programas.NotificableEjecutorPasos;
import com.clillo.dmx.programas.conversorCanal.ConversorCanal;
import com.clillo.dmx.programas.pasos.EjecutorPasos;
import com.clillo.dmx.programas.pasos.EjecutorPasosMovingHead;

public class ProgramaTO implements Comparable<ProgramaTO>{

	private static int ultimoId=0;

	private int idInterno;
	private int correlativo;
	private String id;
	private String idNumerico;
	private TipoPrograma tipo;
	private String nombre;
	private int velocidad;
	private int velocidadMovimiento; // usado sólo en el caso de movimientos robóticos
	
	private int porcentajeVelocidadActual;
	private int porcentajeVelocidadMaximo;
	private int porcentajeVelocidadMinimo;
	
	private ArrayList<PasoTO> listaPasos;
	private boolean enEjecucion;
	private int pasoActual;
	private long siguienteEjecucion;
	private ConversorCanal conversorCanal;
	private EjecutorPasos ejecutorPasos;
	
	// Sirve para coordinar los movimientos. Significa que el tiempo de espera antes de la ejecución del próximo paso se deshabilitará
	private boolean ejecutandoMovimiento;
	
	private ParametrosEjecucionProgramaTO parametros;
	
	private NotificableEjecutorPasos notificableEjecutorPasos;
	
	// ¿Hubo algún cambio en las probabilidades de ejecución? ¿se debe persistir este cambio?
	private boolean grabar;

	private boolean esOnOff;

	private Escena escena;

	private boolean esVdjAble;
	private TipoGatillador tipoGatillador;

	// Lista de programas que se deben ejecutar junto con este programa, cuando el tipo de gatillador es reloj externo
	private ArrayList<ProgramaTO> programasDelegados;
	private boolean ningunProgramaSeleccionado;
	
	private boolean ejecutaPrimerPaso;
	
	public ProgramaTO() {
		idInterno = ultimoId++;
		porcentajeVelocidadActual = 100;
		parametros = new ParametrosEjecucionProgramaTO();
		pasoActual=-1;
	}

	public PasoTO siguientePaso(){
		pasoActual++;
		if (pasoActual >= listaPasos.size())
			pasoActual = 0;

		return listaPasos.get(pasoActual);
	}
	
	public void setSiguienteEjecucion() {
		if (listaPasos.size()>1 || ejecutorPasos instanceof EjecutorPasosMovingHead || (tipoGatillador == TipoGatillador.RelojExterno && !ningunProgramaSeleccionado )) {
			//System.out.println(velocidad+"\t"+porcentajeVelocidadActual + "\t" + nombre);
			siguienteEjecucion = System.currentTimeMillis() + velocidad*porcentajeVelocidadActual/100;
		}
		else
			siguienteEjecucion = System.currentTimeMillis() + 60000;		
	}
	
	public int getVelocidadParaMovimientoRoboticos() {
		return velocidadMovimiento * porcentajeVelocidadActual/100;
	}

	public String getId() {
		parametros.setId(id);
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isEnEjecucion() {
		return enEjecucion;
	}
	public boolean isEsOnOff() {
		return esOnOff;
	}
	public void setEsOnOff(boolean esOnOff) {
		this.esOnOff = esOnOff;
	}

	public void setEnEjecucion(boolean enEjecucion) {
		ejecutandoMovimiento = false;
		if (enEjecucion)
			parametros.setCantidadEjecuciones(parametros.getCantidadEjecuciones()+1);

		if (this.enEjecucion && !enEjecucion)
			parametros.termina();

		if (!this.enEjecucion && enEjecucion){
			parametros.comienza();
			ejecutaPrimerPaso = true;
		}

		this.enEjecucion = enEjecucion;
	}
	public int getPasoActual() {
		if (pasoActual<0)
			return 0;
		return pasoActual;
	}
	public void setPasoActual(int pasoActual) {
		this.pasoActual = pasoActual;
	}
	public TipoPrograma getTipo() {
		return tipo;
	}
	public void setTipo(TipoPrograma tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<PasoTO> getListaPasos() {
		return listaPasos;
	}
	public void setListaPasos(ArrayList<PasoTO> listaPasos) {
		this.listaPasos = listaPasos;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public long getSiguienteEjecucion() {
		return siguienteEjecucion;
	}
	public void setSiguienteEjecucion(long siguienteEjecucion) {
		this.siguienteEjecucion = siguienteEjecucion;
	}
	public int getPorcentajeVelocidadActual() {
		return porcentajeVelocidadActual;
	}
	public void setPorcentajeVelocidadActual(int porcentajeVelocidadActual) {
		this.porcentajeVelocidadActual = porcentajeVelocidadActual;
	}
	public int getPorcentajeVelocidadMaximo() {
		return porcentajeVelocidadMaximo;
	}
	public void setPorcentajeVelocidadMaximo(int porcentajeVelocidadMaximo) {
		this.porcentajeVelocidadMaximo = porcentajeVelocidadMaximo;
	}
	public int getPorcentajeVelocidadMinimo() {
		return porcentajeVelocidadMinimo;
	}
	public void setPorcentajeVelocidadMinimo(int porcentajeVelocidadMinimo) {
		this.porcentajeVelocidadMinimo = porcentajeVelocidadMinimo;
	}
	public ConversorCanal getConversorCanal() {
		return conversorCanal;
	}
	public void setConversorCanal(ConversorCanal conversorCanal) {
		this.conversorCanal = conversorCanal;
	}
	public EjecutorPasos getEjecutorPasos() {
		return ejecutorPasos;
	}
	public void setEjecutorPasos(EjecutorPasos ejecutorPasos) {
		this.ejecutorPasos = ejecutorPasos;
	}
	public boolean isEjecutandoMovimiento() {
		return ejecutandoMovimiento;
	}
	public void setEjecutandoMovimiento(boolean ejecutandoMovimiento) {
		this.ejecutandoMovimiento = ejecutandoMovimiento;
	}
	public int getVelocidadMovimiento() {
		return velocidadMovimiento;
	}
	public void setVelocidadMovimiento(int velocidadMovimiento) {
		this.velocidadMovimiento = velocidadMovimiento;
	}
	public NotificableEjecutorPasos getNotificableEjecutorPasos() {
		return notificableEjecutorPasos;
	}
	public void setNotificableEjecutorPasos(NotificableEjecutorPasos notificableEjecutorPasos) {
		this.notificableEjecutorPasos = notificableEjecutorPasos;
	}
	public int getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(int correlativo) {
		this.correlativo = correlativo;
	}
	public ParametrosEjecucionProgramaTO getParametros() {
		return parametros;
	}
	public void setParametros(ParametrosEjecucionProgramaTO parametros) {
		this.parametros = parametros;
	}
	public boolean isGrabar() {
		return grabar;
	}
	public void setGrabar(boolean grabar) {
		this.grabar = grabar;
	}

	public String getIdNumerico() {
		return idNumerico;
	}

	public void setIdNumerico(String idNumerico) {
		this.idNumerico = idNumerico;
	}

	public boolean isEsVdjAble() {
		return esVdjAble;
	}

	public void setEsVdjAble(boolean esVdjAble) {
		this.esVdjAble = esVdjAble;
	}

	public TipoGatillador getTipoGatillador() {
		return tipoGatillador;
	}

	public void setTipoGatillador(TipoGatillador tipoGatillador) {
		this.tipoGatillador = tipoGatillador;
	}

	public int getIdInterno() {
		return idInterno;
	}
	
	public ArrayList<ProgramaTO> getProgramasDelegados() {
		return programasDelegados;
	}

	public void setProgramasDelegados(ArrayList<ProgramaTO> programasDelegados) {
		this.programasDelegados = programasDelegados;
	}

	public boolean isNingunProgramaSeleccionado() {
		return ningunProgramaSeleccionado;
	}

	public void setNingunProgramaSeleccionado(boolean ningunProgramaSeleccionado) {
		this.ningunProgramaSeleccionado = ningunProgramaSeleccionado;
	}

	public boolean isEjecutaPrimerPaso() {
		return ejecutaPrimerPaso;
	}

	public void setEjecutaPrimerPaso(boolean ejecutaPrimerPaso) {
		this.ejecutaPrimerPaso = ejecutaPrimerPaso;
	}

	@Override
	public boolean equals(Object obj) {
		return ((ProgramaTO)obj).idInterno == idInterno;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(tipo);
		builder.append('.');
		builder.append(nombre);
		return builder.toString();
	}
	
	@Override
	public int compareTo(ProgramaTO o) {
		return correlativo-o.getCorrelativo();
	}
	
	public Escena getEscena() {
		return escena;
	}
}
