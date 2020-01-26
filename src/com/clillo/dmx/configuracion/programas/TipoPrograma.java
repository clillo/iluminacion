package com.clillo.dmx.configuracion.programas;

import com.clillo.dmx.programas.conversorCanal.ConversorCanal;
import com.clillo.dmx.programas.conversorCanal.ConversorCanalDimmerMovingHeads;
import com.clillo.dmx.programas.conversorCanal.ConversorCanalDimmerPinSpot;
import com.clillo.dmx.programas.conversorCanal.ConversorCanalDimmerRGBW;
import com.clillo.dmx.programas.conversorCanal.ConversorCanalParaAleatorio;
import com.clillo.dmx.programas.pasos.EjecutorPasos;
import com.clillo.dmx.programas.pasos.EjecutorPasosAcciones;
import com.clillo.dmx.programas.pasos.EjecutorPasosConversor;
import com.clillo.dmx.programas.pasos.EjecutorPasosEscena;
import com.clillo.dmx.programas.pasos.EjecutorPasosGenerico;
import com.clillo.dmx.programas.pasos.EjecutorPasosMovingHead;
import com.clillo.dmx.programas.pasos.EjecutorPasosProgramas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelSubMasterLista;

public enum TipoPrograma {

	RGBWColor         ("rgbwcolor",          new EjecutorPasosGenerico(),   null), 
    RGBWDimmer        ("rgbwdimmer",         new EjecutorPasosConversor(),  new ConversorCanalDimmerRGBW()), 
    RGBWStrobo        ("rgbwstrobo",         new EjecutorPasosGenerico(),   null), 
	RGBWConjunto      ("rgbwconjunto",       new EjecutorPasosProgramas(),   null), 
	PinSpotColor      ("pinspotcolor",       new EjecutorPasosGenerico(),   null), 
	PinSpotDimmer     ("pinspotdimmer",      new EjecutorPasosConversor(),  new ConversorCanalDimmerPinSpot()), 
	
	MovingHeadPosicion("movingheadposicion", new EjecutorPasosMovingHead(), null), 
	MovingHeadColor   ("movingheadcolor",    new EjecutorPasosGenerico(),   null), 
	MovingHeadDimmer  ("movingheaddimmer",   new EjecutorPasosConversor(),  new ConversorCanalDimmerMovingHeads()),
	MovingHeadGobos   ("movingheadgobos",    new EjecutorPasosGenerico(),   null),
	MovingHeadStrobo  ("movingheadstrobo",   new EjecutorPasosGenerico(),   null),

	MovingHeadBPPosicion("movingheadbpposicion", new EjecutorPasosMovingHead(), null), 
	MovingHeadBPDimmer  ("movingheadbpdimmer",   new EjecutorPasosConversor(),  new ConversorCanalDimmerMovingHeads()),
	MovingHeadBPColor   ("movingheadbpcolor",    new EjecutorPasosGenerico(),   null), 
	MovingHeadBPGobos   ("movingheadbpgobos",    new EjecutorPasosGenerico(),   null),
	MovingHeadBPStrobo  ("movingheadbpstrobo",   new EjecutorPasosGenerico(),   null),

	ScannerPosicion   ("scannerposicion",    new EjecutorPasosMovingHead(), null), 
	ScannerColor      ("scannercolor",       new EjecutorPasosGenerico(),   null), 
	ScannerOnOff      ("scanneronoff",       new EjecutorPasosMovingHead(), null), 
	ScannerGobos      ("scannergobos",       new EjecutorPasosGenerico(),   null),

	RoboticasPosicion ("roboticasposicion",  new EjecutorPasosMovingHead(), null), 
	RoboticasColor    ("roboticascolor",     new EjecutorPasosGenerico(),   null), 
	RoboticasDimmer   ("roboticasdimmer",    new EjecutorPasosGenerico(),   null), 
	RoboticasStrobo   ("roboticasstrobo",    new EjecutorPasosGenerico(),   null), 
	RoboticasGobos    ("roboticasgobos",     new EjecutorPasosGenerico(),   null), 

	LaserRGBPatron    ("laserrgb2patron",    new EjecutorPasosGenerico(),   null),
	LaserRGBEfectos   ("laserrgb2efectos",    new EjecutorPasosGenerico(),   null),
	LaserRGBColor     ("laserrgb2color",     new EjecutorPasosGenerico(),   null),
	
	RGBStageLight     ("rgbstageligth",      new EjecutorPasosConversor(),  new ConversorCanalParaAleatorio()),
	
	LaserMultiPunto1  ("laserMultiPunto1",   new EjecutorPasosAcciones(),   null),
	LaserIlda1   	  ("laserIlda1",         new EjecutorPasosGenerico(),   null),

	CrystalBall1      ("crystalball1",       new EjecutorPasosGenerico(),   null),

	Derby2            ("derby2",             new EjecutorPasosGenerico(),   null),

	Spider1           ("spider1",             new EjecutorPasosGenerico(),   null),
	Escenas           ("escenas",    		 new EjecutorPasosEscena(),   null);
	

	private String nombre;
	private EjecutorPasos ejecutorPasos;
	private ConversorCanal conversorCanal;
	private PanelSubMasterLista panelSubMasterLista;

	private TipoPrograma(String nombreEnCarpeta, EjecutorPasos ejecutorPasos, ConversorCanal conversorCanal) {
		this.nombre = nombreEnCarpeta;
		this.ejecutorPasos = ejecutorPasos;
		this.conversorCanal = conversorCanal;
	}

	public static TipoPrograma getTipoPorNombre(String nombre) {
		for (TipoPrograma tipo : values())
			if (tipo.nombre.equals(nombre))
				return tipo;

		return null;
	}

	public EjecutorPasos getEjecutorPasos() {
		return ejecutorPasos;
	}

	public ConversorCanal getConversorCanal() {
		return conversorCanal;
	}

	public String getNombre() {
		return nombre;
	}

	public PanelSubMasterLista getPanelSubMasterLista() {
		return panelSubMasterLista;
	}

	public void setPanelSubMasterLista(PanelSubMasterLista panelSubMasterLista) {
		this.panelSubMasterLista = panelSubMasterLista;
	}
}
