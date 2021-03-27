package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas;

import java.awt.Color;
import java.awt.Graphics;

public class PuntoArchivo {

	private static final int factor = 2;
	private static int color = 0;

	private int x;
	private int y;
	
	
	
	public PuntoArchivo(int x, int y, PanelCanvas panelCanvas, FixtureRobotica entidad) {
		super();
	//	this.x = x;
	//	this.y = y;
		this.x = (int)panelCanvas.inverseX(x, entidad);
		this.y = (int)panelCanvas.inverseY(y, entidad);
	}

	public PuntoArchivo(String str, PanelCanvas panelCanvas, FixtureRobotica entidad){
		String [] pp = str.split(";");
		
        int vPan = 0;
        int vPanFine = 0;

        int vTilt = 0;
        int vTiltFine =0;
        
		for (String str1: pp){
			
			if (str1.startsWith("101")){
				String [] pp1 = str1.split(",");
				vPan = Integer.parseInt(pp1[1]);
			}
			
			if (str1.startsWith("109")){
				String [] pp1 = str1.split(",");
				vPanFine = Integer.parseInt(pp1[1]);
			}
			if (str1.startsWith("102")){
				String [] pp1 = str1.split(",");
				vTilt = Integer.parseInt(pp1[1]);
			}
			if (str1.startsWith("110")){
				String [] pp1 = str1.split(",");
				vTiltFine = Integer.parseInt(pp1[1]);
			}	
		}
		
		x = (int)panelCanvas.inverseX(vPan*256 + vPanFine, entidad);
		y = (int)panelCanvas.inverseY(vTilt*256 + vTiltFine, entidad);
		System.out.println(x+"\t"+y);
		

	}
	
	public void paint(Graphics g) {
		switch (color) {
		case 0:
			g.setColor(Color.green);
			break;
		case 1:
			g.setColor(Color.blue);
			break;
		case 2:
			g.setColor(Color.red);
			break;
		case 3:
			g.setColor(Color.yellow);
			break;

		default:
			break;
		}
	
		color= (color+1)%4;
		g.drawOval(x - 4, y - 4, 8, 8);
	}
}
