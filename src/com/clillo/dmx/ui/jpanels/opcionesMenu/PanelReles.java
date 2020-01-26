package com.clillo.dmx.ui.jpanels.opcionesMenu;

import javax.swing.border.TitledBorder;

import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorRele;

public class PanelReles extends PanelMenuGenerico{

	private static final long serialVersionUID = -5869553409971473557L;

	public PanelReles() {
	    this.configura(270, 300, "Relés");
	  	this.setLayout(null);
		
	  	PanelObservadorRele pnlCanalA = new PanelObservadorRele();
		pnlCanalA.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCanalA.setBounds(10, 11, 339, 41);
		pnlCanalA.setId("canalA");
		add(pnlCanalA);
		
		PanelObservadorRele pnlCanalB = new PanelObservadorRele();
		pnlCanalB.setId("canalB");
		pnlCanalB.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCanalB.setBounds(10, 63, 339, 41);
		add(pnlCanalB);
		
		PanelObservadorRele pnlCanalC = new PanelObservadorRele();
		pnlCanalC.setId("canalC");
		pnlCanalC.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCanalC.setBounds(10, 115, 339, 41);
		add(pnlCanalC);
		
		PanelObservadorRele pnlCanalD = new PanelObservadorRele();
		pnlCanalD.setId("canalD");
		pnlCanalD.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCanalD.setBounds(10, 172, 339, 41);
		add(pnlCanalD);
	}
}
