package com.clillo.pruebas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.clillo.dmx.catalogo.ColorTO;
import com.clillo.dmx.configuracion.ColoresCfg;
import com.clillo.utiles.ColorUtil;

public class MuestraColoresSuavizado extends JFrame implements MouseListener{

	private static final long serialVersionUID = 1823403452881818081L;
	private JPanel jPanel;
	private ArrayList<ColorTO> coloresSuavizado = ColoresCfg.getColoresSuavizado();
	private JPanel pnlColor;
	private int indice=0;
	
	public MuestraColoresSuavizado() {
		enableEvents(64L);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialize();
		ThreadMula tm = new ThreadMula();
		tm.start();
	}
	
	private void initialize() {
		setSize(new Dimension(1280, 850));
		setContentPane(getJPanel());
	}
	
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING){
			dispose();
			System.exit(0);
		}
		super.processWindowEvent(e);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			
			pnlColor = new JPanel();
			pnlColor.setBounds(1189, 724, 27, 29);
			jPanel.add(pnlColor);
			
			int y = 10;
			int x = 20;
			for (ColorTO color: coloresSuavizado){
				
				JLabel lbl = new JLabel();
				if (color.getNombre().length()>5)
					lbl.setText(color.getNombre().substring(0,5));
				else
					lbl.setText(color.getNombre());
				lbl.setBounds(new Rectangle(x, y, 70, 20));
				
				JPanel pnl = new JPanel();
				pnl.setBounds(new Rectangle(x+40, y, 10, 10));
				pnl.setBackground(new Color(color.getR(), color.getG(), color.getB()));
				pnl.addMouseListener(this);
				
				jPanel.add(lbl);
				jPanel.add(pnl);
				
				y+=25;
				if (y > 760){
					y=10;
					x+=120;
				}
			}
		}
		return jPanel;
	}
	
	public static void generaArchivo() throws Exception{
		BufferedWriter writer = new BufferedWriter(new FileWriter("conf/colores/coloresSuavizado.xml"));
		writer.write("<colores>\n");
		writer.write(imprimeVentana(0.0f, 0.15f, 0.004f));   // rojo
		writer.write(imprimeVentana(0.15f, 0.17f, 0.002f));  // amarillo
		writer.write(imprimeVentana(0.17f, 0.18f, 0.001f));  // amarillo
		writer.write(imprimeVentana(0.18f, 0.24f, 0.004f));  // verde fosf.
		writer.write(imprimeVentana(0.24f, 0.36f, 0.006f));  // verde
		writer.write(imprimeVentana(0.36f, 0.5f, 0.004f));   // celeste
		writer.write(imprimeVentana(0.5f, 0.492f, 0.001f));  // celeste
		writer.write(imprimeVentana(0.492f, 0.508f, 0.002f));// celeste
		writer.write(imprimeVentana(0.508f, 0.7f, 0.006f));  // azul
		writer.write(imprimeVentana(0.7f, 0.84f, 0.004f));   // cyan
		writer.write(imprimeVentana(0.84f, 0.87f, 0.002f));  // cyan
		writer.write(imprimeVentana(0.87f, 1f, 0.004f));     // rojo
		writer.write("</colores>");
		writer.close();
	}
	
	public static String imprimeVentana(float inicio, float fin, float incremeto) throws Exception{
		StringBuilder sb = new StringBuilder();
		float hue = inicio;
		int n=1;
		while (hue<=fin){
			int[] aColor = ColorUtil.HSLtoRGB(hue, 1f, 0.5f, null);
			sb.append("<color id=\""+n+"\">");
			sb.append("<hue>"+hue+"</hue>");
			sb.append("<r>"+aColor[0]+"</r>");
			sb.append("<g>"+aColor[1]+"</g>");
			sb.append("<b>"+aColor[2]+"</b>");
			sb.append("</color>\n");
			n++;
			hue += incremeto;
		}
		return sb.toString();
	}
	
	public void tic(){
		ColorTO color = coloresSuavizado.get(indice);
		
		pnlColor.setBackground(color.getColor());
		
		indice++;
		if (indice>=coloresSuavizado.size())
			indice = 0;
	}
		
	public static void main(String[] args) throws Exception {
		//imprimeVentana(0.15f, 0.18f);
		generaArchivo();
		MuestraColoresSuavizado vp = new MuestraColoresSuavizado();
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
	
	class ThreadMula extends Thread{
		
		@Override
		public void run() {
			super.run();
			while (true){
				tic();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}



