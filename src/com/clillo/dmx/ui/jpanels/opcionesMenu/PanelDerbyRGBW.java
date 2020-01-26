package com.clillo.dmx.ui.jpanels.opcionesMenu;

import javax.swing.border.TitledBorder;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorDerbyRGBW;

public class PanelDerbyRGBW extends PanelMenuGenerico{

	private static final long serialVersionUID = -5869553409971473557L;

	public PanelDerbyRGBW() {
	    this.configura(320, 280, "Derby RGBW 1");
	  	this.setLayout(null);
		
	  	PanelObservadorDerbyRGBW pnlDerby = new PanelObservadorDerbyRGBW();
		pnlDerby.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDerby.setBounds(10, 11, 310, 255);
		pnlDerby.setId("derbyrgbw");
		add(pnlDerby);

		Dmx.registraEnviable(pnlDerby);
	}
}
