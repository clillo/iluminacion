package com.clillo.pruebas;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.clillo.dmx.catalogo.ColorTO;
import com.clillo.dmx.catalogo.Colores;
import com.clillo.dmx.comm.dmx.Dmx;

public class MuestraColores extends JFrame implements MouseListener{

	private static final long serialVersionUID = 1823403452881818081L;
	private JPanel jPanel;
	
	private ArrayList<ColorTO> listaColores = Colores.getListaColoresExtendida();
//	private ArrayList<ColorTO> listaColores = Colores.getListaColoresReducidos();
//	private ArrayList<ColorTO> listaColores = Colores.getListaColoresBasicos();
//	private DmxRgbw estacion = new DmxRgbw(100, "");

	public MuestraColores() {
		enableEvents(64L);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialize();
	}
	
	private void initialize() {
		setSize(new Dimension(1280, 850));
		setContentPane(getJPanel());
	}
	
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == 201){
			dispose();
			Dmx.stop();
			System.exit(0);
		}
		super.processWindowEvent(e);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//JPanel pnl = (JPanel)e.getSource();
		//estacion.enviar(pnl.getBackground());
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			
			int y = 10;
			int x = 20;
			for (ColorTO color: listaColores){
				JLabel lbl = new JLabel();
				lbl.setText(color.getId()+"");
				lbl.setBounds(new Rectangle(x, y, 50, 20));
				
				JLabel lbl2 = new JLabel();
				lbl2.setText(color.getNombre());
				lbl2.setBounds(new Rectangle(x+50, y, 120, 20));

				JPanel pnl = new JPanel();
				pnl.setBounds(new Rectangle(x+20, y, 20, 20));
				pnl.setBackground(color.getColor());
				pnl.addMouseListener(this);
				
				jPanel.add(lbl);
				jPanel.add(lbl2);
				jPanel.add(pnl);
				
				y+=30;
				if (y > 760){
					y=10;
					x+=200;
				}
			}
		}
		return jPanel;
	}
		
	public static void main(String[] args) {
		MuestraColores vp = new MuestraColores();
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

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
