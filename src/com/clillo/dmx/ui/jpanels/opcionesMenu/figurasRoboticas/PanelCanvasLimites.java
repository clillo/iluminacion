package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanelCanvasLimites extends JPanel{

	private static final long serialVersionUID = 1410165324836742914L;

	private FixtureRobotica entidad;
	private MovimientosConMouse movimientosConMouse;

	public PanelCanvasLimites() {
		movimientosConMouse = new MovimientosConMouse();
		movimientosConMouse.setPanelCanvas(this);
		
		this.addMouseListener(movimientosConMouse);
		this.addMouseMotionListener(movimientosConMouse);
	}
	
	public void setRedimensiono(InformaCambiosUsuario redimensiono) {
		movimientosConMouse.setRedimensiono(redimensiono);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		
		if (entidad==null)
			return;
		
		// Cruz en medio
		g.setColor(Color.gray);
		g.drawLine(getWidth()/2, 0,             getWidth()/2, getHeight());
		g.drawLine(0,            getHeight()/2, getWidth(),   getHeight()/2);
		
		// Ventana para movimientos aleatorios
		int mnx = convierteRealPantallaX(entidad.getVentanaMinX());
		int mxx = convierteRealPantallaX(entidad.getVentanaMaxX());
		int mny = convierteRealPantallaY(entidad.getVentanaMinY());
		int mxy = convierteRealPantallaY(entidad.getVentanaMaxY());
		
		g.setColor(Color.cyan);
		g.drawLine( mnx, mny, mnx, mxy);
		g.drawLine( mnx, mny, mxx, mny);
		g.drawLine( mxx, mxy, mnx, mxy);
		g.drawLine( mxx, mny, mxx, mxy);
	}

	public void setEntidad(FixtureRobotica entidad) {
		this.entidad = entidad;
		movimientosConMouse.setEntidad(this.entidad);
	}
	
	protected double conviertePantallaRealX(int pantallaX){
		return (FixtureRobotica.maxX * pantallaX)/this.getWidth();
	}

	protected double conviertePantallaRealY(int pantallaY){
		return (FixtureRobotica.maxY * pantallaY)/this.getHeight();
	}

	protected int convierteRealPantallaX(double realX){
		return (int)Math.round(realX * this.getWidth() / FixtureRobotica.maxX);
	}

	protected int convierteRealPantallaY(double realY){
		return (int)Math.round(realY * this.getHeight() / FixtureRobotica.maxY);
	}
}
