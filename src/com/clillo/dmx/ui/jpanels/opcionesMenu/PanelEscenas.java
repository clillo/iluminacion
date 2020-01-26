package com.clillo.dmx.ui.jpanels.opcionesMenu;

import javax.swing.border.TitledBorder;

import com.clillo.dmx.ui.jpanels.opcionesMenu.escenas.PanelEjecutorEscenas;

public class PanelEscenas extends PanelMenuGenerico{

	private static final long serialVersionUID = -5869553409971473557L;
	private PanelEjecutorEscenas pnlEjecutor;
	
	public PanelEscenas() {
	    this.configura(800, 330, "Escenas");
	  	setLayout(null);
	  	
	  	pnlEjecutor = new PanelEjecutorEscenas();
	  	pnlEjecutor.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	pnlEjecutor.setBounds(10, 11, 766, 278);
	  	add(pnlEjecutor);
	  		  		  		  	
	  	pnlEjecutor.setup(); 
	}
}
