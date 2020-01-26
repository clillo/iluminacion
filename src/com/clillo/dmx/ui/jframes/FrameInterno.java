package com.clillo.dmx.ui.jframes;

import javax.swing.JInternalFrame;

import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMenuGenerico;

public class FrameInterno extends JInternalFrame{

	private static final long serialVersionUID = -3544256699487889303L;

	public FrameInterno(PanelMenuGenerico pGenerica) {
		//         titulo           resizable  closeable, maximizable, iconificable
		super(pGenerica.getTitle(), true, true, false, false);
	    setSize(pGenerica.getWidth(), pGenerica.getHeight());
	    setVisible(true);
	    getContentPane().add(pGenerica);    
	}
	
	
	public void dispose() {
		//super.dispose();
	}
}
