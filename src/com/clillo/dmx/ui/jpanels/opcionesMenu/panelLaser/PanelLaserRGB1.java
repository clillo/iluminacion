package com.clillo.dmx.ui.jpanels.opcionesMenu.panelLaser;

import javax.swing.border.TitledBorder;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.jpanels.fixtures.laser.PanelObservadorLaserRGB1;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMenuGenerico;

public class PanelLaserRGB1 extends PanelMenuGenerico{

	private static final long serialVersionUID = -5869553409971473557L;

	public PanelLaserRGB1() {
	    this.configura(270, 300, "Laser RGB 1");
	  	this.setLayout(null);
		
	  	PanelObservadorLaserRGB1 pnlRGBStageLigth = new PanelObservadorLaserRGB1();
		pnlRGBStageLigth.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlRGBStageLigth.setBounds(10, 11, 234, 240);
		pnlRGBStageLigth.setId("laserrgb1");
		add(pnlRGBStageLigth);
		Dmx.registraEnviable(pnlRGBStageLigth);
	}
}
