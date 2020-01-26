package com.clillo.dmx.ui.jpanels.opcionesMenu;

import javax.swing.border.TitledBorder;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorStageLight;

public class PanelStageLight extends PanelMenuGenerico{

	private static final long serialVersionUID = -5869553409971473557L;

	public PanelStageLight() {
	    this.configura(270, 140, "Stage Ligth");
	  	this.setLayout(null);
		
	  	PanelObservadorStageLight pnlStageLigth = new PanelObservadorStageLight();
		pnlStageLigth.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlStageLigth.setBounds(10, 11, 234, 98);
		pnlStageLigth.setId("stagelight");
		add(pnlStageLigth);
		
		Dmx.registraEnviable(pnlStageLigth);
	}
}
