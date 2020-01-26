package com.clillo.dmx.ui.jpanels.opcionesMenu.panelLaser;

import javax.swing.border.TitledBorder;

import com.clillo.dmx.ui.jpanels.fixtures.laser.PanelObservadorLaserMultiPunto1;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMenuGenerico;

public class PanelLaserMultiPunto1 extends PanelMenuGenerico{

	private static final long serialVersionUID = -5869553409971473557L;

	public PanelLaserMultiPunto1() {
	    this.configura(270, 140, "Laser MultiPunto 1");
	  	this.setLayout(null);
		
	  	PanelObservadorLaserMultiPunto1 pnlLaser = new PanelObservadorLaserMultiPunto1();
		pnlLaser.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLaser.setBounds(10, 11, 234, 89);
		pnlLaser.setId("laserMulipunto1");
		add(pnlLaser);
		
	}
}
