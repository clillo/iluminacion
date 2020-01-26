package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.clillo.dmx.core.fixtures.FixtureRobotica;

public class PanelCanvasLimites extends JPanel{

	private static final long serialVersionUID = 1410165324836742914L;

	private JTextField txtPosicion;
	private JTextField txtFine;
	private FixtureRobotica entidad;
	private MovimientosConMouse movimientosConMouse;

//	private ArrayList<Punto> listaPuntos;
	
	private static final int maxX = 255;
	private static final int maxY = 255;

	public PanelCanvasLimites() {
		movimientosConMouse = new MovimientosConMouse();
	//	movimientosConMouse.setPanelCanvas(this);
		
		this.addMouseListener(movimientosConMouse);
		this.addMouseMotionListener(movimientosConMouse);
	}
	
	public void paint(Graphics g){
		super.paint(g);
		if (entidad==null)
			return;
		
//		if(listaPuntos!=null){
//			g.setColor(Color.red);
//			for(Punto p: listaPuntos){
//				g.drawOval(convierteRealPantallaX(p.getX())-1, convierteRealPantallaY(p.getY())-1, 6, 6);
//				g.drawChars(p.getNombre().toCharArray(), 0, p.getNombre().length(), convierteRealPantallaX(p.getX()), convierteRealPantallaY(p.getY()));
//				//System.out.println((convierteRealPantallaX(p.getX()))+"\t"+(convierteRealPantallaY(p.getY())));
//			}
//				
//		}
		
		// Cruz en medio
		g.setColor(Color.gray);
		g.drawLine(getWidth()/2, 0,             getWidth()/2, getHeight());
		g.drawLine(0,            getHeight()/2, getWidth(),   getHeight()/2);
		
		if (txtPosicion!=null)
			txtPosicion.setText(entidad.getPosX() + "," + entidad.getPosY());

		if (txtFine!=null)
			txtFine.setText(entidad.getPosXFine() + "," + entidad.getPosYFine());

		g.setColor(Color.white);
		g.drawOval(convierteRealPantallaX(entidad.getPosX())-1, convierteRealPantallaY(entidad.getPosY())-1, 4, 4);
		
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
	
//	public void setListaPuntos(ArrayList<Punto> listaPuntos) {
//		this.listaPuntos = listaPuntos;
//	}

	public void setTxtPosicion(JTextField txtPosicion) {
		this.txtPosicion = txtPosicion;
	}
	
	public void setTxtFine(JTextField txtFine) {
		this.txtFine = txtFine;
	}

	public void setEntidad(FixtureRobotica entidad) {
		this.entidad = entidad;
		movimientosConMouse.setEntidad(entidad);
		movimientosConMouse.setMaxX(maxX);
		movimientosConMouse.setMaxY(maxY);
		movimientosConMouse.setCanvasMaxX(this.getWidth());
		movimientosConMouse.setCanvasMaxY(this.getHeight());
	}
	
	protected int conviertePantallaRealX(int pantallaX){
		return (maxX * pantallaX)/this.getWidth();
	}

	protected int conviertePantallaRealY(int pantallaY){
		return (maxY * pantallaY)/this.getHeight();
	}

	protected int convierteRealPantallaX(int realX){
		return realX * this.getWidth() / maxX;
	}

	protected int convierteRealPantallaY(int realY){
		return realY * this.getHeight() / maxY;
	}
}
