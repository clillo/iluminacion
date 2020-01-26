package com.clillo.dmx.ui.jpanels.opcionesMenu;

import javax.swing.border.TitledBorder;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorCrystalBall1;

public class PanelCristalBall1 extends PanelMenuGenerico{

	private static final long serialVersionUID = -5869553409971473557L;

	public PanelCristalBall1() {
	    this.configura(270, 140, "Crystal Ball 1");
	  	this.setLayout(null);
		
		PanelObservadorCrystalBall1 pnlCrystalMagic = new PanelObservadorCrystalBall1();
		pnlCrystalMagic.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCrystalMagic.setBounds(10, 11, 234, 98);
		pnlCrystalMagic.setId("crystalBall1");
		add(pnlCrystalMagic);

		Dmx.registraEnviable(pnlCrystalMagic);
	}
}
