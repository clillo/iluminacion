package com.clillo.dmx.ui.jpanels.opcionesMenu.ejecucionAutomatica;

import java.util.ArrayList;

import javax.swing.JCheckBox;

import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos.InterfacePanelEjecutorProgramas;

public class ListaFixturesParaAutomatica {

	private static ArrayList<FixtureParaAutomatica> listaFixtures = new ArrayList<FixtureParaAutomatica>(); 

	public static void agrega(JCheckBox chk, InterfacePanelEjecutorProgramas panel){
		listaFixtures.add(new FixtureParaAutomatica(chk, panel));
	}
	
	public static void agrega(JCheckBox chk, InterfacePanelEjecutorProgramas panel,	int idSecundario){
		listaFixtures.add(new FixtureParaAutomatica(chk, panel,	idSecundario));
	}

	public static ArrayList<FixtureParaAutomatica> getListaFixtures() {
		return listaFixtures;
	}	
}
