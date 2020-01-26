package com.clillo.dmx.ui.jpanels.fixtures;

import javax.swing.JPanel;

import com.clillo.dmx.comm.dmx.DmxEnviable;
import com.clillo.dmx.core.ListaFixtures;
import com.clillo.dmx.core.fixtures.Fixture;
import com.clillo.dmx.core.fixtures.ListenerApagadoEncendido;

public abstract class PanelObservador extends JPanel implements DmxEnviable, ListenerApagadoEncendido{

	private static final long serialVersionUID = 5779733250034260220L;

	protected Fixture fixture;
	protected int canalBase;
	protected int canales[];
	
	public void setId(String id){
		if (id!=null)
			fixture = ListaFixtures.getFixture(id);
		
		if (fixture==null){
			System.out.println("FATAL, no se encuentra el fixture: "+id);
			System.exit(0);
		}
		
		this.canalBase = fixture.getCanalDMXInicial();
		fixture.setListener(this);
	}
	
	@Override
	public int[] getCanales() {
		return canales;
	}
	
}
