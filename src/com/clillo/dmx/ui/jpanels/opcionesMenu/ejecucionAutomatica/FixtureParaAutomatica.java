package com.clillo.dmx.ui.jpanels.opcionesMenu.ejecucionAutomatica;

import javax.swing.JCheckBox;

import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos.InterfacePanelEjecutorProgramas;

public class FixtureParaAutomatica {
	
	private JCheckBox chk;
	private InterfacePanelEjecutorProgramas panel;
	private int idSecundario;
	
	public FixtureParaAutomatica(JCheckBox chk, InterfacePanelEjecutorProgramas panel) {
		super();
		this.chk = chk;
		this.panel = panel;
	}
	
	public FixtureParaAutomatica(JCheckBox chk, InterfacePanelEjecutorProgramas panel,	int idSecundario) {
		super();
		this.chk = chk;
		this.panel = panel;
		this.idSecundario = idSecundario;
	}

	public JCheckBox getChk() {
		return chk;
	}
	public void setChk(JCheckBox chk) {
		this.chk = chk;
	}
	public InterfacePanelEjecutorProgramas getPanel() {
		return panel;
	}
	public void setPanel(InterfacePanelEjecutorProgramas panel) {
		this.panel = panel;
	}
	public int getIdSecundario() {
		return idSecundario;
	}
	public void setIdSecundario(int idSecundario) {
		this.idSecundario = idSecundario;
	}	
}
