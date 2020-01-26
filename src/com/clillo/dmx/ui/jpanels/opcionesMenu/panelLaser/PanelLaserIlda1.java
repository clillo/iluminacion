package com.clillo.dmx.ui.jpanels.opcionesMenu.panelLaser;

import javax.swing.border.TitledBorder;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.jpanels.fixtures.laser.PanelObservadorLaserIlda1;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMenuGenerico;

public class PanelLaserIlda1 extends PanelMenuGenerico{

	private static final long serialVersionUID = -5869553409971473557L;

	public PanelLaserIlda1() {
	    this.configura(270, 300, "Laser Ilda 1");
	  	this.setLayout(null);
		
	  	PanelObservadorLaserIlda1 pnlLaser = new PanelObservadorLaserIlda1();
		pnlLaser.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLaser.setBounds(10, 11, 234, 240);
		pnlLaser.setId("laserIlda1");
		add(pnlLaser);
		Dmx.registraEnviable(pnlLaser);
	}
}
