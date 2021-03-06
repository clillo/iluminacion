package com.clillo.dmx.ui.jpanels.editaProgramas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.clillo.dmx.comm.dmx.Dmx;

public class PanelEdicionFiguras extends JPanel implements MouseMotionListener, MouseListener{

	private static final long serialVersionUID = -5869553409971473557L;
	private JTextField txtPosicion;
	private JPanel pnlDibujo;
	private JTextArea txtListaPuntos;
	
	private static final int maxX = 255;
	private static final int maxY = 255;
	
	private ArrayList<Punto> listaPuntos = new ArrayList<Punto>();
	
	public PanelEdicionFiguras() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(217, 11, 123, 189);
		add(scrollPane);

		txtListaPuntos = new JTextArea();
		txtListaPuntos.setFont(new Font("Monospaced", Font.PLAIN, 9));
		scrollPane.setViewportView(txtListaPuntos);
	
		pnlDibujo = new JPanel(){
			private static final long serialVersionUID = 9056031188937687827L;

			public void paint(Graphics g){
				super.paint(g);
				g.setColor(Color.gray);
				g.drawLine(pnlDibujo.getWidth()/2, 0, pnlDibujo.getWidth()/2, pnlDibujo.getHeight());
				g.drawLine(0, pnlDibujo.getHeight()/2, pnlDibujo.getWidth(), pnlDibujo.getHeight()/2);
				
				txtListaPuntos.setText("");
				for (Punto p: listaPuntos){
					g.drawOval(p.getX(), p.getY(), 4, 4);
					txtListaPuntos.append(p.getX()+","+ p.getY()+"\n");
				}
			}
		};
		
		pnlDibujo.setBackground(Color.BLACK);
		pnlDibujo.setBounds(10, 0, 200, 200);
		pnlDibujo.addMouseMotionListener(this);
		pnlDibujo.addMouseListener(this);
		add(pnlDibujo);
				
		txtPosicion = new JTextField();
		txtPosicion.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtPosicion.setBounds(10, 206, 55, 20);
		add(txtPosicion);
		txtPosicion.setColumns(10);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		txtPosicion.setText(conviertePantallaRealX(e.getX())+","+ conviertePantallaRealY(e.getY()));
    	Dmx.enviar(401, conviertePantallaRealX(e.getX()));
    	Dmx.enviar(403, conviertePantallaRealY(e.getY()));

	}
	
	private int conviertePantallaRealX(int pantallaX){
		return (maxX * pantallaX)/pnlDibujo.getWidth();
	}

	private int conviertePantallaRealY(int pantallaY){
		return (maxY * pantallaY)/pnlDibujo.getHeight();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Punto p = new Punto();
		p.setX(e.getX());
		p.setY(e.getY());
		listaPuntos.add(p);
		pnlDibujo.repaint();
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
