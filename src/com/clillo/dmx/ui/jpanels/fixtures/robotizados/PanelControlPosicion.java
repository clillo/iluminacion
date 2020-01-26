package com.clillo.dmx.ui.jpanels.fixtures.robotizados;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.clillo.dmx.core.fixtures.FixtureRobotica;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.ListenerMovimientos.TipoMovimiento;

public class PanelControlPosicion extends JPanel implements ActionListener{

	private static final long serialVersionUID = 5133024732217606553L;
	
	private JButton btnDerecha = null;
	private JButton btnInzquerda = null;
	private JButton btnArriba = null;
	private JButton btnAbajo = null;

	private FixtureRobotica entidad;
	
	private boolean esFine;
	private ListenerMovimientos listener;
	private TipoMovimiento tipo;

	public PanelControlPosicion() {
		setLayout(null);

		btnAbajo = new JButton();
		btnAbajo.setBounds(new Rectangle(17, 34, 20, 20));
		btnAbajo.addActionListener(this);
		this.add(btnAbajo);  

		btnArriba = new JButton();
		btnArriba.setBounds(new Rectangle(17, 0, 20, 20));
		btnArriba.addActionListener(this);
		this.add(btnArriba);
		
		btnDerecha = new JButton();
		btnDerecha.setBounds(new Rectangle(34, 17, 20, 20));
		btnDerecha.addActionListener(this);
		this.add(btnDerecha);
		
		btnInzquerda = new JButton();
		btnInzquerda.setBounds(new Rectangle(0, 17, 20, 20));
		btnInzquerda.addActionListener(this);
	    this.add(btnInzquerda);
	}
	
	public void setEntidad(FixtureRobotica entidad) {
		this.entidad = entidad;
	}

	public void setEsFine(boolean esFine) {
		this.esFine = esFine;
	}
	
	public void setListener(ListenerMovimientos listener) {
		this.listener = listener;
	}

	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (listener!=null){
			if (e.getSource().equals(btnDerecha)) 
				listener.mueveDerecha(tipo);
			
			if (e.getSource().equals(btnInzquerda)) 
				listener.mueveIzquerda(tipo);
	
			if (e.getSource().equals(btnArriba))
				listener.mueveArriba(tipo);
	
			if (e.getSource().equals(btnAbajo))
				listener.mueveAbajo(tipo);
			
			return;
		}
		if (esFine){
			if (e.getSource().equals(btnDerecha)) 
				entidad.derecha(true);
			
			if (e.getSource().equals(btnInzquerda)) 
				entidad.izquerda(true);
	
			if (e.getSource().equals(btnArriba))
				entidad.arriba(true);
	
			if (e.getSource().equals(btnAbajo))
				entidad.abajo(true);
		}else{
			if (e.getSource().equals(btnDerecha)) 
				entidad.derecha();
			
			if (e.getSource().equals(btnInzquerda)) 
				entidad.izquerda();
	
			if (e.getSource().equals(btnArriba))
				entidad.arriba();
	
			if (e.getSource().equals(btnAbajo))
				entidad.abajo();
		}
	}
}
