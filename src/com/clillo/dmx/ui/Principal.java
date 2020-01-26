package com.clillo.dmx.ui;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.UIManager;

import com.clillo.dmx.comm.serial.FactoryRS232;
import com.clillo.dmx.comm.vdj.FactoryVDJ;
import com.clillo.dmx.ui.jframes.FramePrincipal;

public class Principal {

	private static JFrame frame;

	static {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		frame = new FramePrincipal();
		frame.validate();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		if (screenSize.getWidth()<1920)
			frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		else 			
			frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		FactoryVDJ.init();
		FactoryRS232.init();
	}
	
	public static JFrame getFrame() {
		return frame;
	}

	public static void main(String[] args) throws Exception {
			
	}
}
