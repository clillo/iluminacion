package com.clillo.dmx.ui.jpanels.opcionesMenu;

import javax.swing.border.TitledBorder;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorDerbyRGBW2;

public class PanelDerbyRGBW2 extends PanelMenuGenerico{

	private static final long serialVersionUID = -5869553409971473557L;

	public PanelDerbyRGBW2() {
	    this.configura(320, 280, "Derby RGBW 2");
	  	this.setLayout(null);
		
	  	PanelObservadorDerbyRGBW2 pnlDerby = new PanelObservadorDerbyRGBW2();
		pnlDerby.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDerby.setBounds(10, 11, 310, 255);
		pnlDerby.setId("derbyrgbw2");
		add(pnlDerby);

		Dmx.registraEnviable(pnlDerby);
	}
}
