package com.clillo.dmx.ui.jpanels.opcionesMenu;

import javax.swing.border.TitledBorder;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorRGBStageLight;

public class PanelRGBStageLight extends PanelMenuGenerico{

	private static final long serialVersionUID = -5869553409971473557L;

	public PanelRGBStageLight() {
	    this.configura(270, 300, "RGB Stage Ligth");
	  	this.setLayout(null);
		
		PanelObservadorRGBStageLight pnlRGBStageLigth = new PanelObservadorRGBStageLight();
		pnlRGBStageLigth.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlRGBStageLigth.setBounds(10, 11, 234, 240);
		pnlRGBStageLigth.setId("rgbstagelight");
		add(pnlRGBStageLigth);
		Dmx.registraEnviable(pnlRGBStageLigth);
	}
}
