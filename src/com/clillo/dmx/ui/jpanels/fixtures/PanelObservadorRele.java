package com.clillo.dmx.ui.jpanels.fixtures;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

public class PanelObservadorRele extends PanelObservador implements ActionListener{

	private static final long serialVersionUID = -3664280122489375394L;
	
	private JCheckBox chkEncendido;
	private JButton btnEncender;
	private JButton btnApagar;
	
	public PanelObservadorRele() {
		setLayout(null);
		
		chkEncendido = new JCheckBox("Encendido");
		chkEncendido.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chkEncendido.setBounds(172, 10, 79, 23);
		chkEncendido.addActionListener(this);
		add(chkEncendido);
		
		btnEncender = new JButton("Encender");
		btnEncender.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEncender.setBounds(10, 11, 71, 20);
		btnEncender.addActionListener(this);
		add(btnEncender);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnApagar.setBounds(84, 11, 71, 20);
		btnApagar.addActionListener(this);
		add(btnApagar);
	}

	@Override
	public void setId(String id) {
		super.setId(id);
	}
	
	@Override
	public void actualizaEnvioDmx(int canal, int valor) {
		System.out.println(canal+"\t"+valor);
	}

	@Override
	public void encender() {		
		System.out.println("Encender "+this.fixture);
	}

	@Override
	public void apagar() {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnApagar)){
			fixture.apagar();
		}

		if (e.getSource().equals(btnEncender)){
			fixture.encender();
		}
	}
}
