package com.clillo.dmx.ui.jpanels.opcionesMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import com.clillo.dmx.ui.jpanels.opcionesMenu.fixturesrgb.PanelRealista;
import com.clillo.dmx.ui.jpanels.opcionesMenu.fixturesrgb.PanelTradicional;

public class PanelMonitorRGBW extends PanelMenuGenerico implements ActionListener {

	private static final long serialVersionUID = -5869553409971473557L;

	public PanelMonitorRGBW() {
		this.configura(580, 250, "Monitor Fixtures RGB");
		this.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 552, 179);
		add(tabbedPane);

	  	tabbedPane.addTab("Vista Simplificada", null, new PanelRealista(), null);
	  	tabbedPane.addTab("Vista Tradicional", null, new PanelTradicional(), null);
	}	

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}
