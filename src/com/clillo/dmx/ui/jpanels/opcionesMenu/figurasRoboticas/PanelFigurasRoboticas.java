package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.clillo.dmx.ui.jpanels.fixtures.robotizados.Punto;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMenuGenerico;
import javax.swing.JLabel;
import javax.swing.JSpinner;

public class PanelFigurasRoboticas extends PanelMenuGenerico implements ActionListener {

	private static final long serialVersionUID = -5869553409971473557L;

	private PanelCanvas pnlCanvas;

	private JButton btnEditar;
	
	private JTextField txtPosicion;
	private JTextField txtPunto;
	private JPanel pnl1;
	
	private ArrayList<Punto> listaPuntos = new ArrayList<Punto>();
	private JTextField txtPan;
	private JTextField txtTilt;
	private JTextField txtPanActual;
	private JTextField txtTiltActual;
	private JLabel label;
	private JLabel label_1;
	
	private JSpinner spiPan1;
	private JSpinner spiPan2;

	public PanelFigurasRoboticas() {
	    this.configura(1100, 800, "Mantiene Figuras Robóticas");
	  	this.setLayout(null);
	  	
		pnl1 = new JPanel();
		pnl1.setLayout(null);
		pnl1.setBounds(4, 4, 1010, 810);
		add(pnl1);
		
		spiPan1 = new JSpinner();
		spiPan1.setBounds(829, 249, 46, 20);
		pnl1.add(spiPan1);
		
		spiPan2 = new JSpinner();
		spiPan2.setBounds(885, 249, 46, 20);
		pnl1.add(spiPan2);
		
		txtPosicion = new JTextField();
		txtPosicion.setBounds(837, 87, 46, 20);
		pnl1.add(txtPosicion);
		
		txtPan = new JTextField();
		txtPan.setBounds(837, 43, 46, 20);
		pnl1.add(txtPan);
		
		txtTilt = new JTextField();
		txtTilt.setBounds(893, 43, 46, 20);
		pnl1.add(txtTilt);
		
		txtPunto = new JTextField();
		txtPunto.setBounds(829, 118, 46, 20);
		pnl1.add(txtPunto);

		txtPanActual = new JTextField();
		txtPanActual.setBounds(829, 206, 46, 20);
		pnl1.add(txtPanActual);
		
		txtTiltActual = new JTextField();
		txtTiltActual.setBounds(829, 320, 46, 20);
		pnl1.add(txtTiltActual);	
		
		pnlCanvas = new PanelCanvas();
		pnlCanvas.setBackground(Color.BLACK);
		pnlCanvas.setBounds(4, 4, 800, 800);
		pnlCanvas.setListaPuntos(listaPuntos);
		pnlCanvas.setTxtPosicion(txtPosicion);
		pnlCanvas.setTxtPunto(txtPunto);
		pnlCanvas.setTxtPan(txtPan);
		pnlCanvas.setTxtTilt(txtTilt);
		pnlCanvas.setTxtPanActual(txtPanActual);
		pnlCanvas.setTxtTiltActual(txtTiltActual);
		pnlCanvas.setSpiPan1(spiPan1);
		pnlCanvas.setSpiPan2(spiPan2);
		pnl1.add(pnlCanvas);
				
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(648, 402, 68, 23);
		btnEditar.addActionListener(this);
		pnl1.add(btnEditar);
		
		JLabel lblPan = new JLabel("Pan");
		lblPan.setBounds(837, 27, 46, 14);
		pnl1.add(lblPan);
		
		JLabel lblTilt = new JLabel("Tilt");
		lblTilt.setBounds(893, 27, 46, 14);
		pnl1.add(lblTilt);
			
		label = new JLabel("Pan");
		label.setBounds(829, 190, 46, 14);
		pnl1.add(label);
		
		label_1 = new JLabel("Tilt");
		label_1.setBounds(829, 304, 46, 14);
		pnl1.add(label_1);
				
		Punto p = new Punto();
		p.setNombre("1");
		p.setX(50); p.setY(50);
		listaPuntos.add(p);

		p = new Punto();
		p.setNombre("2");
		p.setX(300); p.setY(50);
		listaPuntos.add(p);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnEditar)){
		}
	}
}
