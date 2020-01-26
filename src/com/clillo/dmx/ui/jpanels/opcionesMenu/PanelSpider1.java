package com.clillo.dmx.ui.jpanels.opcionesMenu;

import javax.swing.border.TitledBorder;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorSpider1;

public class PanelSpider1 extends PanelMenuGenerico{

	private static final long serialVersionUID = -5869553409971473557L;

	public PanelSpider1() {
	    this.configura(270, 300, "Spider 1");
	  	this.setLayout(null);
		
	  	PanelObservadorSpider1 pnlLaser = new PanelObservadorSpider1();
		pnlLaser.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlLaser.setBounds(10, 11, 234, 240);
		pnlLaser.setId("spider1");
		add(pnlLaser);
		Dmx.registraEnviable(pnlLaser);
	}
}
