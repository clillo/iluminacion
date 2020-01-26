package com.clillo.ilda;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.clillo.utiles.Archivos;

public class VisualizaTxt extends JFrame{

	private static final long serialVersionUID = 5911260315118665343L;
	private static ArrayList<Punto> listaPuntos = new ArrayList<Punto>();
	private JPanel jPanel;
	
	private int frameActual;
	private static int framesTotal;

	public VisualizaTxt() {
		enableEvents(64L);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		jPanel = new JPanel(){
			private static final long serialVersionUID = -6829867106559111907L;

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				
				double factorX = this.getWidth()/255.0;
				double factorY = this.getHeight()/255.0;
				
				Punto pa = null;
				for (Punto p: listaPuntos)
					if (p.frame==frameActual){
						
						if (p.laser==1)
							g.setColor(Color.blue);
						else{
							g.setColor(Color.white);
							pa = null;
						}
						g.fillOval((int)(p.x*factorX), (int)(p.y*factorY), 2, 2);
						if (pa!=null)
							g.drawLine((int)(pa.x*factorX), (int)(pa.y*factorY), (int)(p.x*factorX), (int)(p.y*factorY));
						
						pa = p;
					}
				
				frameActual++;
				if (frameActual>framesTotal)
					frameActual = 0;
			}
		};
		jPanel.setLayout(null);
		jPanel.setBackground(Color.black);
		
	    setSize(new Dimension(512, 512));
	    setContentPane(jPanel);
	}
	
	private static void refinaPuntoMedio(){
		ArrayList<Punto> listaPuntosNueva = new ArrayList<Punto>();

		Punto pa = null;
		for (Punto p: listaPuntos){
			if (pa==null)
				listaPuntosNueva.add(p);
			else{
				Punto nuevo = new Punto();
				nuevo.x = (pa.x + p.x)/2;
				nuevo.y = (pa.y + p.y)/2;
				nuevo.frame = p.frame;
				nuevo.laser = p.laser;
				listaPuntosNueva.add(nuevo);
				listaPuntosNueva.add(p);
			}
			
			pa = p;
		}
		listaPuntos = listaPuntosNueva;
    	StringBuilder sb = new StringBuilder();

    	for (Punto p: listaPuntos){
	        sb.append(p.x).append(' ');
	        sb.append(255 - p.y).append(' ');
	        sb.append(p.laser).append(' ');
	        sb.append(p.frame).append('\n');
    	}
        Archivos.grabaArchivo(sb.toString(), "test.txt");
	}
	
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == 201){
			dispose();
			System.exit(0);
		}
		super.processWindowEvent(e);
	}
	
	public static void main(String[] args) throws IOException {
	   	String archivoSalida = "idls/test.txt";
	    
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(archivoSalida));
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return ;
		}

	    String linea;
	    framesTotal = 0;
    	linea = in.readLine();
		while(linea != null){
			String partes[] = linea.split(" ");
			//System.out.println(partes[0]+"\t"+partes[1]+"\t"+partes[2]);
			Punto p = new Punto();
			p.x = Integer.parseInt(partes[0]);
			p.y = 255 - Integer.parseInt(partes[1]);
			p.laser = Integer.parseInt(partes[2]);
			p.frame = Integer.parseInt(partes[3]);
			listaPuntos.add(p);
			
			if (p.frame>framesTotal)
				framesTotal= p.frame;
			linea = in.readLine();
		}
		
		in.close();
		
	//	refinaPuntoMedio();
		
		VisualizaTxt vp = new VisualizaTxt();
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

class Punto{
	public int x;
	public int y;
	public int laser;
	public int frame;
}