package com.clillo.dmx.ui.jpanels.opcionesMenu;

import com.clillo.dmx.ui.jpanels.editaProgramas.PanelEdicionFiguras;

public class PanelCreaProgramasRobotizados extends PanelMenuGenerico{

	private static final long serialVersionUID = -5869553409971473557L;

	public PanelCreaProgramasRobotizados() {
	    this.configura(500, 400, "Generación de Programas Robotizados");
	  	this.setLayout(null);
		
	  	PanelEdicionFiguras pnlMovingHead1 = new PanelEdicionFiguras();
		pnlMovingHead1.setBounds(0, 0, 331, 270);
		add(pnlMovingHead1);
	}
}
