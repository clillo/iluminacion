package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.Escena;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.NodoEscena;

public class PanelCanvas extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = -5092101412027888450L;


	private static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
	private static final Cursor CURSOR_MOVER = new Cursor(Cursor.MOVE_CURSOR);

	private Escena escena;

	private NodoEscena nodoActual;
	
	private InformaCambiosUsuario cambios;
	
  	private List<PuntoArchivo> listaPuntoArchivo = new ArrayList<>();
		
	public PanelCanvas() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public void setCambios(InformaCambiosUsuario cambios) {
		this.cambios = cambios;
	}

	public void setEscena(Escena escena) {
		this.escena = escena;
	}
	
	public List<PuntoArchivo> getListaPuntoArchivo() {
		return listaPuntoArchivo;
	}

	public double inverseX(int pan, FixtureRobotica entidad) {
		double ancho = entidad.getVentanaMaxX()-entidad.getVentanaMinX();
		return (pan - entidad.getVentanaMinX())*800.0/ ancho;
		
	}
	
	public double inverseY(int tilt, FixtureRobotica entidad) {
		double alto = entidad.getVentanaMaxY()-entidad.getVentanaMinY();
		return (tilt - entidad.getVentanaMinY())*800.0/ alto;

	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for (PuntoArchivo pa: listaPuntoArchivo){
			pa.paint(g);
		}
		
		if (escena==null)
			return;
		

		
		for (NodoEscena p: escena.getListaNodos()){
			String txt = p.getMovingHead().toString();
			if (nodoActual == p){
				p.setSeleccionado(true);
				g.setColor(Color.red);
			}
			else{
				p.setSeleccionado(false);
				g.setColor(Color.white);
			}

			g.drawOval(p.getX() - 4, p.getY() - 4, 8, 8);
			g.drawChars(txt.toCharArray(), 0, txt.length(), p.getX() - 4, p.getY() - 4);

		}
	}
	

	@Override
	public void mouseDragged(MouseEvent e) {
		
		muestraValores(e);

		this.setCursor(CURSOR_MOVER);
		this.repaint();
		return;
	}
	
	private void muestraValores(MouseEvent e){
		
		if (nodoActual==null)
			return;
		double ancho = nodoActual.getEntidad().getVentanaMaxX()-nodoActual.getEntidad().getVentanaMinX();
		double alto = nodoActual.getEntidad().getVentanaMaxY()-nodoActual.getEntidad().getVentanaMinY();
		double pan = nodoActual.getEntidad().getVentanaMinX() + ancho*e.getX()/800.0;
		double tilt = nodoActual.getEntidad().getVentanaMinY() + alto*e.getY()/800.0;
		
		cambios.cambioPosicionCursor(e.getX(), e.getY(), pan, tilt, nodoActual);
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (nodoActual==null)
			return;
		double ancho = nodoActual.getEntidad().getVentanaMaxX()-nodoActual.getEntidad().getVentanaMinX();
		double alto = nodoActual.getEntidad().getVentanaMaxY()-nodoActual.getEntidad().getVentanaMinY();
		double pan = nodoActual.getEntidad().getVentanaMinX() + ancho*e.getX()/800.0;
		double tilt = nodoActual.getEntidad().getVentanaMinY() + alto*e.getY()/800.0;
		
		cambios.cambioPosicionCursor(e.getX(), e.getY(), pan, tilt);

		this.setCursor(DEFAULT_CURSOR);

		for (NodoEscena p: escena.getListaNodos()){
			if (Math.abs(e.getX() - p.getX())<2.0 && Math.abs(e.getY() - p.getY())<2.0){
				this.setCursor(CURSOR_MOVER);
				return;
			}
		}
	}

	public void setNodoActual(NodoEscena nodoActual) {
		this.nodoActual = nodoActual;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {

		nodoActual = null;
		for (NodoEscena p: escena.getListaNodos()){
			if (Math.abs(e.getX() - p.getX())<2.0 && Math.abs(e.getY() - p.getY())<2.0){

				this.setCursor(CURSOR_MOVER);
				nodoActual = p;
				p.setSeleccionado(true);
				this.repaint();
				muestraValores(e);
			}else
				p.setSeleccionado(false);
		}
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
