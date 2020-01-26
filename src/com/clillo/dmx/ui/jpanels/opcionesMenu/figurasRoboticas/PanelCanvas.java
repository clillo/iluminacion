package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.clillo.dmx.ui.jpanels.fixtures.robotizados.Punto;

public class PanelCanvas extends JPanel implements MouseListener, MouseMotionListener{

	private static final long serialVersionUID = -5092101412027888450L;
	
	private static final int MAX = 65536;

	private static final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
	private static final Cursor CURSOR_MOVER = new Cursor(Cursor.MOVE_CURSOR);

	private ArrayList<Punto> listaPuntos;
	private JTextField txtPosicion;
	private JTextField txtPunto;
	private JTextField txtPan;
	private JTextField txtTilt;
	private JTextField txtPanActual;
	private JTextField txtTiltActual;
	private JSpinner spiPan1;
	private JSpinner spiPan2;
	
	private Punto puntoActual;
		
	public PanelCanvas() {
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	public void setListaPuntos(ArrayList<Punto> listaPuntos) {
		this.listaPuntos = listaPuntos;
	}
	
	public void setTxtPosicion(JTextField txtPosicion) {
		this.txtPosicion = txtPosicion;
	}

	public void setTxtPunto(JTextField txtPunto) {
		this.txtPunto = txtPunto;
	}

	public void setTxtPan(JTextField txtPan) {
		this.txtPan = txtPan;
	}

	public void setTxtTilt(JTextField txtTilt) {
		this.txtTilt = txtTilt;
	}

	public void setTxtPanActual(JTextField txtPanActual) {
		this.txtPanActual = txtPanActual;
	}

	public void setTxtTiltActual(JTextField txtTiltActual) {
		this.txtTiltActual = txtTiltActual;
	}

	public void setSpiPan1(JSpinner spiPan1) {
		this.spiPan1 = spiPan1;
	}

	public void setSpiPan2(JSpinner spiPan2) {
		this.spiPan2 = spiPan2;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		for (Punto p: listaPuntos){
			
			if (puntoActual == p){
				g.setColor(Color.red);
				g.fillOval(p.getX() - 2, p.getY() - 2, 4, 4);
				txtPanActual.setText(String.valueOf(puntoActual.getPan1())+","+String.valueOf(puntoActual.getPan2()));
				spiPan1.setValue(puntoActual.getPan1());
				spiPan2.setValue(puntoActual.getPan2());
			}
			else{
				g.setColor(Color.white);
				g.drawOval(p.getX() - 2, p.getY() - 2, 4, 4);
			}
		}
	}
	

	@Override
	public void mouseDragged(MouseEvent e) {
		if (puntoActual==null)
			return;
		
		muestraValores(e);

		puntoActual.setX(e.getX());
		puntoActual.setY(e.getY());
		this.setCursor(CURSOR_MOVER);
		this.repaint();
		return;
	}
	
	private void muestraValores(MouseEvent e){
		txtPosicion.setText(e.getX()+","+e.getY());
		double pan = (MAX*e.getX())/800.0;
		double tilt = (MAX*e.getY())/800.0;
		
		int pan1 = (int)(pan / 256);
		int pan2 = (int)(pan % 256);

		int tilt1 = (int)(tilt/ 256);
		int tilt2 = (int)(tilt % 256);

		txtPan.setText(String.valueOf(pan1)+","+String.valueOf(pan2));
		txtTilt.setText(String.valueOf(tilt1)+","+String.valueOf(tilt2));	
		
		if (puntoActual!=null){
			txtPunto.setText(puntoActual.getNombre());
			puntoActual.setPan(pan);
			puntoActual.setTilt(tilt);
			
			puntoActual.setPan1(pan1);
			puntoActual.setPan2(pan2);
			
	/*		pan1 = (int)(pan / 256);
			pan2 = (int)(pan % 256);

			tilt1 = (int)(tilt/ 256);
			tilt2 = (int)(tilt % 256);

			txtPan.setText(String.valueOf(pan1)+","+String.valueOf(pan2));
			txtTilt.setText(String.valueOf(tilt1)+","+String.valueOf(tilt2));	
*/
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		muestraValores(e);

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
		txtPunto.setText("");
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
