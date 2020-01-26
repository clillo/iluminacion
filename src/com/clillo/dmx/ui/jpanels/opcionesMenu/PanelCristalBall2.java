package com.clillo.dmx.ui.jpanels.opcionesMenu;

import javax.swing.border.TitledBorder;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorCrystalBall2;

public class PanelCristalBall2 extends PanelMenuGenerico{

	private static final long serialVersionUID = -5869553409971473557L;

	public PanelCristalBall2() {
	    this.configura(270, 140, "Crystal Ball 2");
	  	this.setLayout(null);
		
		PanelObservadorCrystalBall2 pnlCrystalBall = new PanelObservadorCrystalBall2();
		pnlCrystalBall.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCrystalBall.setBounds(10, 11, 234, 98);
		pnlCrystalBall.setId("crystalBall2");
		add(pnlCrystalBall);

		Dmx.registraEnviable(pnlCrystalBall);
	}
}
