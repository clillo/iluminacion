package com.clillo.dmx.ui.jpanels.fixtures;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.dialogos.EligeColor;
import com.clillo.dmx.ui.dialogos.EligeColorListener;

public class PanelObservadorPinSpotAli extends PanelObservador implements ActionListener, MouseListener, EligeColorListener {
	
	private static final long serialVersionUID = -5766991298707309249L;

	private JPanel pnlColor;
	
	private JToggleButton btnOnOff;
	
	private int r;
	private int g;
	private int b;
	
	private boolean oOff;

	public PanelObservadorPinSpotAli() {
		super();
		setLayout(null);
		
		pnlColor = new JPanel();
		pnlColor.setBounds(0, 0, 16, 16);
		pnlColor.addMouseListener(this);
		add(pnlColor);
			
		pnlColor.setBackground(Color.black);
		
		btnOnOff = new JToggleButton();
		btnOnOff.setBounds(20, 0, 18, 18);
		add(btnOnOff);
		btnOnOff.addActionListener(this);
		
		r = g = b = 0;
		oOff = false;
	}
	
	public void setId(String id){
		super.setId(id);
		canales = new int[]{canalBase, canalBase+1, canalBase+2, canalBase+3};
	}

	public void actualizaColor(int r, int g, int b) {
		Dmx.enviar(canalBase + 1,  r);
		Dmx.enviar(canalBase + 2,  g);
		Dmx.enviar(canalBase + 3,  b);
	}

	@Override
	public void actualizaEnvioDmx(int canal, int valor) {
		boolean actualizarColor = false;
		if (canal == canalBase){
			if (valor==0)
				oOff = false;
			else
				oOff = true;
			
			btnOnOff.setSelected(oOff);
			if (btnOnOff.isSelected()){
				
				btnOnOff.setText("X");
			}
			else{

				btnOnOff.setText("");
			}
		}

		if (canal == canalBase + 1){
			r = valor;
			actualizarColor = true;
		}
		else
			if (canal == canalBase + 2){
				g = valor;
				actualizarColor = true;
			}				
			else
				if (canal == canalBase + 3){
					b = valor;
					actualizarColor = true;
				}

		if (actualizarColor)
			try {
				pnlColor.setBackground(new Color(r, g, b));
			} catch (Exception e) {
				System.out.println("ERROR al actualizar colores: "+r+","+g+","+b);
			}
	}

	@Override
	public void apagar() {
		oOff = false;
		Dmx.enviar(canalBase, 0);
		btnOnOff.setSelected(oOff);
	 
	}

	@Override
	public void encender() {
		oOff = true;
		Dmx.enviar(canalBase, 255);
		btnOnOff.setSelected(oOff);
	 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (btnOnOff.isSelected()){
			encender();
			btnOnOff.setText("X");
		}
		else{
			apagar();
			btnOnOff.setText("");
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(pnlColor)){
			int anteriorR = r;
			int anteriorG = g;
			int anteriorB = b;
			
			EligeColor.setListener(this);
			EligeColor.setColorInicial(new Color(r, g, b));
			EligeColor.createAndShowGUI();
			
			if (!EligeColor.isAceptar())
				 actualizaColor(anteriorR, anteriorG, anteriorB);
						
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
