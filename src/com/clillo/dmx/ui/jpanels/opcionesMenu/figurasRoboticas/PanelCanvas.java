package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.clillo.dmx.ui.jpanels.fixtures.robotizados.Punto;

public class PanelCanvas extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = -5092101412027888450L;
	
	private FixtureRobotica entidad;

	private static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
	private static final Cursor CURSOR_MOVER = new Cursor(Cursor.MOVE_CURSOR);

	private ArrayList<Punto> listaPuntos;

	private Punto puntoActual;
	
	private InformaCambiosUsuario cambios;
		
	public PanelCanvas() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	public void setEntidad(FixtureRobotica entidad) {
		this.entidad = entidad;
	}

	public void setCambios(InformaCambiosUsuario cambios) {
		this.cambios = cambios;
	}

	public void setListaPuntos(ArrayList<Punto> listaPuntos) {
		this.listaPuntos = listaPuntos;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for (Punto p: listaPuntos){
			
			if (puntoActual == p){
				g.setColor(Color.red);
				g.drawOval(p.getX() - 4, p.getY() - 4, 8, 8);
			}
			else{
				g.setColor(Color.white);
				g.drawOval(p.getX() - 4, p.getY() - 4, 8, 8);
			}
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
		double ancho = entidad.getVentanaMaxX()-entidad.getVentanaMinX();
		double alto = entidad.getVentanaMaxY()-entidad.getVentanaMinY();
		double pan = entidad.getVentanaMinX() + ancho*e.getX()/800.0;
		double tilt = entidad.getVentanaMinY() + alto*e.getY()/800.0;
		
		cambios.cambioPosicionCursor(e.getX(), e.getY(), pan, tilt, puntoActual);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		double ancho = entidad.getVentanaMaxX()-entidad.getVentanaMinX();
		double alto = entidad.getVentanaMaxY()-entidad.getVentanaMinY();
		double pan = entidad.getVentanaMinX() + ancho*e.getX()/800.0;
		double tilt = entidad.getVentanaMinY() + alto*e.getY()/800.0;
		
		cambios.cambioPosicionCursor(e.getX(), e.getY(), pan, tilt);

		this.setCursor(DEFAULT_CURSOR);

		for (Punto p: listaPuntos){
			if (Math.abs(e.getX() - p.getX())<2.0 && Math.abs(e.getY() - p.getY())<2.0){
				this.setCursor(CURSOR_MOVER);
				return;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		puntoActual = null;
		for (Punto p: listaPuntos){
			if (Math.abs(e.getX() - p.getX())<2.0 && Math.abs(e.getY() - p.getY())<2.0){

				this.setCursor(CURSOR_MOVER);
				puntoActual = p;
				muestraValores(e);
				this.repaint();
			}
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
