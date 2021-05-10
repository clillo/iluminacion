package com.clillo.dmx.ui.jpanels.opcionesMenu;

import javax.swing.JTabbedPane;

import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos.ObjetosCompartidosRGBW;

import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos.PanelEjecutorProgramasDerby;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos.PanelEjecutorProgramasLaserIndividual;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos.PanelEjecutorProgramasMovingHeads;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos.PanelEjecutorProgramasPinSpot;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos.PanelEjecutorProgramasRGBW;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos.PanelEjecutorProgramasRGBWConjuntos;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos.PanelEjecutorProgramasRoboticas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos.PanelEjecutorProgramasScanner;

public class PanelEjecutorProgramas extends PanelMenuGenerico{

	private static final long serialVersionUID = -5869553409971473557L;
	
	private static PanelEjecutorProgramasPinSpot pnlPinSpot = null;
	private static PanelEjecutorProgramasRGBW pnlRGBW = null; 
	private static PanelEjecutorProgramasRGBWConjuntos pnlRGBWConjuntas = null; 

	private static PanelEjecutorProgramasDerby pnlDerby = null;
	private static PanelEjecutorProgramasScanner pnlScanners = null; 
	private static PanelEjecutorProgramasMovingHeads pnlMovingHeads = null;
	private static PanelEjecutorProgramasRoboticas pnlRoboticas = null;

	private static PanelEjecutorProgramasLaserIndividual pnlLaserIndividual = null;
	
	private static ObjetosCompartidosRGBW objCompartidosRGBW = null;

	public PanelEjecutorProgramas() {
	    this.configura(970, 600, "Ejecutor de Programas");
	  	setLayout(null);
	  	
	  	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	  	tabbedPane.setBounds(2, 2, 950, 600);
	  	add(tabbedPane);

	  	if (pnlLaserIndividual==null)
	  		pnlLaserIndividual = new PanelEjecutorProgramasLaserIndividual();
	  	tabbedPane.addTab("Lasers Individual", null, pnlLaserIndividual, null);

	  	if (pnlDerby==null)
	  		pnlDerby = new PanelEjecutorProgramasDerby();
	  	tabbedPane.addTab("Derby", null, pnlDerby, null);

	  	if (pnlRoboticas==null)
	  		pnlRoboticas = new PanelEjecutorProgramasRoboticas();
	  	tabbedPane.addTab("Robóticas Conjuntas", null, pnlRoboticas, null);

	  	if (pnlScanners==null)
	  		pnlScanners = new PanelEjecutorProgramasScanner();
	  	tabbedPane.addTab("Scanners", null, pnlScanners, null);

	  	if (pnlMovingHeads==null)
	  		pnlMovingHeads = new PanelEjecutorProgramasMovingHeads();
	  	tabbedPane.addTab("Moving Heads", null, pnlMovingHeads, null);
  	
	  	if (pnlPinSpot==null)
	  		pnlPinSpot = new PanelEjecutorProgramasPinSpot();
	  	tabbedPane.addTab("PinSpot", null, pnlPinSpot, null);

	  	if (objCompartidosRGBW==null)
	  		objCompartidosRGBW = new ObjetosCompartidosRGBW();
	  	
	  	if (pnlRGBW==null)
	  		pnlRGBW = new PanelEjecutorProgramasRGBW(objCompartidosRGBW);
	  	tabbedPane.addTab("RWGB", null, pnlRGBW, null);
	  	
	  	if (pnlRGBWConjuntas==null)
	  		pnlRGBWConjuntas = new PanelEjecutorProgramasRGBWConjuntos(objCompartidosRGBW);
	  	tabbedPane.addTab("RWGB - C", null, pnlRGBWConjuntas, null);
	}

	public static PanelEjecutorProgramasPinSpot getPnlPinSpot() {
		return pnlPinSpot;
	}

	public static PanelEjecutorProgramasRGBW getPnlRGBW() {
		return pnlRGBW;
	}

	public static PanelEjecutorProgramasRGBWConjuntos getPnlRGBWConjuntas() {
		return pnlRGBWConjuntas;
	}
	
	public static PanelEjecutorProgramasScanner getPnlScanners() {
		return pnlScanners;
	}

	public static PanelEjecutorProgramasMovingHeads getPnlMovingHeads() {
		return pnlMovingHeads;
	}
	
	public static PanelEjecutorProgramasRoboticas getPnlRoboticas() {
		return pnlRoboticas;
	}

	public static PanelEjecutorProgramasLaserIndividual getPnlLaserIndividual() {
		return pnlLaserIndividual;
	}

	public static PanelEjecutorProgramasDerby getPnlDerby() {
		return pnlDerby;
	}

	
}
