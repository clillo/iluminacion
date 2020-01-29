package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MovimientosConMouse implements MouseListener, MouseMotionListener {

	private FixtureRobotica entidad;
	private PanelCanvasLimites panelCanvas;
	private InformaCambiosUsuario redimensiono;
	
	// Indica si se está agrandado o achicando el área de movimiento.
	private boolean correBordeArriba;
	private boolean correBordeAbajo;
	private boolean correBordeDerecha;
	private boolean correBordeIzquerda;
	
	private static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
	private static final Cursor W_RESIZE_CURSOR = new Cursor(Cursor.W_RESIZE_CURSOR);
	private static final Cursor S_RESIZE_CURSOR = new Cursor(Cursor.S_RESIZE_CURSOR);
	private static final Cursor SE_RESIZE_CURSOR = new Cursor(Cursor.SE_RESIZE_CURSOR);
	private static final Cursor SW_RESIZE_CURSOR = new Cursor(Cursor.SW_RESIZE_CURSOR);
	private static final Cursor NE_RESIZE_CURSOR = new Cursor(Cursor.NE_RESIZE_CURSOR);
	private static final Cursor NW_RESIZE_CURSOR = new Cursor(Cursor.NW_RESIZE_CURSOR);

	public void setPanelCanvas(PanelCanvasLimites panelCanvas) {
		this.panelCanvas = panelCanvas;
	}

	public void setEntidad(FixtureRobotica entidad) {
		this.entidad = entidad;
	}
		
	public void setRedimensiono(InformaCambiosUsuario redimensiono) {
		this.redimensiono = redimensiono;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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

	@Override
	public void mouseDragged(MouseEvent e) {
		boolean cambio = false;
		int x = e.getX();
		int y = e.getY();
		
		if (x<0 || x>255)
			return;
		
		if (y<0 || y>255)
			return;

	    if (correBordeArriba){
			double posy =  panelCanvas.conviertePantallaRealY(y);
			if (posy<0 || posy>FixtureRobotica.maxY)
				return;

			entidad.setVentanaMinY(posy);
			panelCanvas.repaint();
			cambio = true;
		}
		if (correBordeAbajo){
			double posy =  panelCanvas.conviertePantallaRealY(y);
			if (posy<0 || posy>FixtureRobotica.maxY)
				return;
			entidad.setVentanaMaxY(posy);
			panelCanvas.repaint();
			cambio = true;
		}
		if (correBordeDerecha){
			double posx =  panelCanvas.conviertePantallaRealX(x);
			if (posx<0 || posx>FixtureRobotica.maxX)
				return;
			entidad.setVentanaMaxX(posx);
			panelCanvas.repaint();
			cambio = true;
		}
		if (correBordeIzquerda){
			double posx =  panelCanvas.conviertePantallaRealX(x);
			if (posx<0 || posx>FixtureRobotica.maxX)
				return;
			entidad.setVentanaMinX(posx);
			panelCanvas.repaint();
			cambio = true;
		}
		
		if (cambio)
			redimensiono.cambioVentana();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		panelCanvas.setCursor(DEFAULT_CURSOR);

		correBordeArriba = false;
		correBordeAbajo = false;
		correBordeDerecha = false;
		correBordeIzquerda = false;
		
		int redimBordeMinX = panelCanvas.convierteRealPantallaX(entidad.getVentanaMinX());
		int redimBordeMaxX = panelCanvas.convierteRealPantallaX(entidad.getVentanaMaxX());
		int redimBordeMinY = panelCanvas.convierteRealPantallaY(entidad.getVentanaMinY());
		int redimBordeMaxY = panelCanvas.convierteRealPantallaY(entidad.getVentanaMaxY());
		
		boolean dentroDelArea = e.getY()<=redimBordeMaxY && e.getY()>=redimBordeMinY && e.getX()<=redimBordeMaxX && e.getX()>=redimBordeMinX;

		if (e.getModifiersEx()==128 && dentroDelArea){

			if (Math.abs(e.getX()-redimBordeMaxX)<3){
				panelCanvas.setCursor(W_RESIZE_CURSOR);
				correBordeDerecha=true;
			}
		
			if (Math.abs(e.getX()-redimBordeMinX)<3){
				panelCanvas.setCursor(W_RESIZE_CURSOR);
				correBordeIzquerda=true;
			}
		
			if (Math.abs(e.getY()-redimBordeMaxY)<3){
				panelCanvas.setCursor(S_RESIZE_CURSOR);
				correBordeAbajo=true;
			}
		
			if (Math.abs(e.getY()-redimBordeMinY)<3){
				panelCanvas.setCursor(S_RESIZE_CURSOR);
				correBordeArriba=true;
			}
		
			if (correBordeAbajo && correBordeDerecha)
				panelCanvas.setCursor(SE_RESIZE_CURSOR);

			if (correBordeAbajo && correBordeIzquerda)
				panelCanvas.setCursor(SW_RESIZE_CURSOR);

			if (correBordeArriba && correBordeDerecha)
				panelCanvas.setCursor(NE_RESIZE_CURSOR);

			if (correBordeArriba && correBordeIzquerda)
				panelCanvas.setCursor(NW_RESIZE_CURSOR);
		}
	}
}
