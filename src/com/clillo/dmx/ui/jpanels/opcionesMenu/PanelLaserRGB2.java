package com.clillo.dmx.ui.jpanels.opcionesMenu;

import javax.swing.border.TitledBorder;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorLaserRGB2;

public class PanelLaserRGB2 extends PanelMenuGenerico{

	private static final long serialVersionUID = -5869553409971473557L;

	public PanelLaserRGB2() {
	    this.configura(270, 300, "Laser RGB2");
	  	this.setLayout(null);
		
		PanelObservadorLaserRGB2 pnlRGBStageLigth = new PanelObservadorLaserRGB2();
		pnlRGBStageLigth.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlRGBStageLigth.setBounds(10, 11, 234, 240);
		pnlRGBStageLigth.setId("laserrgb2");
		add(pnlRGBStageLigth);
		Dmx.registraEnviable(pnlRGBStageLigth);
	}
}
