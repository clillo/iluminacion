package com.clillo.pruebas;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMonitorRGBW;

public class PruebaMonitorFixtures extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1823403452881818081L;
	
	public PruebaMonitorFixtures() {
		enableEvents(64L);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialize();
	}
	
	private void initialize() {
		setSize(new Dimension(343, 187));
		setContentPane(new PanelMonitorRGBW());
	}
	
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == 201){
			dispose();
			System.exit(0);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}

	public static void main(String[] args) {
		PruebaMonitorFixtures vp = new PruebaMonitorFixtures();
		vp.validate();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = vp.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		vp.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		vp.setVisible(true);
		
	}
}