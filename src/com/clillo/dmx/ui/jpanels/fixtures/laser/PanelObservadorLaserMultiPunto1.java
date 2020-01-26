package com.clillo.dmx.ui.jpanels.fixtures.laser;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import com.clillo.dmx.comm.tcp.HTTP;
import com.clillo.dmx.comm.tcp.HTTPEnviable;
import com.clillo.dmx.core.fixtures.laser.FixtureLaserMultipunto1;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservador;

public class PanelObservadorLaserMultiPunto1 extends PanelObservador implements ActionListener, HTTPEnviable{

	private static final long serialVersionUID = -3664280122489375394L;
	
	private JButton btnEncender;
	private JButton btnApagar;
	private JCheckBox tglbtnRojoVerde;
	private JCheckBox tglbtnAzul;
	
	private FixtureLaserMultipunto1 fix;
	
	public PanelObservadorLaserMultiPunto1() {
		setLayout(null);
		
		btnEncender = new JButton("Encender");
		btnEncender.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEncender.setBounds(122, 49, 71, 20);
		btnEncender.addActionListener(this);
		add(btnEncender);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnApagar.setBounds(28, 49, 71, 20);
		btnApagar.addActionListener(this);
		add(btnApagar);
		
		tglbtnRojoVerde = new JCheckBox("Rojo - Verde");
		tglbtnRojoVerde.setBounds(102, 7, 93, 23);
		tglbtnRojoVerde.addActionListener(this);
		add(tglbtnRojoVerde);
		
		tglbtnAzul = new JCheckBox("Azul");
		tglbtnAzul.setBounds(28, 7, 60, 23);
		tglbtnAzul.addActionListener(this);
		add(tglbtnAzul);

	}

	@Override
	public void setId(String id) {
		super.setId(id);
		fix = (FixtureLaserMultipunto1) this.fixture;
		fix.setPanel(this);
		
		HTTP.registraEnviable(this, fix.getIp());
	}
	
	@Override
	public void actualizaEnvioDmx(int canal, int valor) {
		//if (canal==canalBase)
		//	System.out.println(valor);	
	}

	@Override
	public void apagar() {
		fixture.apagar();
	}

	@Override
	public void encender(){
		fixture.encender();
	}
	
	public void actualizarEstado(){
		tglbtnAzul.removeActionListener(this);
		tglbtnRojoVerde.removeActionListener(this);
		tglbtnAzul.setSelected(fix.isAzul());
		tglbtnRojoVerde.setSelected(fix.isRojoVerde());
		tglbtnRojoVerde.addActionListener(this);
		tglbtnAzul.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnApagar))
			fixture.apagar();

		if (e.getSource().equals(btnEncender))
			fixture.encender();
		
		if (e.getSource().equals(tglbtnAzul)){
			fix.setAzul(tglbtnAzul.isSelected());
			fix.actualizarEstado();
		}

		if (e.getSource().equals(tglbtnRojoVerde)){
			fix.setRojoVerde(tglbtnRojoVerde.isSelected());
			fix.actualizarEstado();
		}
	}

	@Override
	public void actualizaEnvioHTTP(String mensaje) {
		if (mensaje.equals("laserAzul?apagar=1")){
			tglbtnAzul.setSelected(false);
			fix.setAzul(false);
		}
		
		if (mensaje.equals("laserRojo?apagar=1")){
			tglbtnRojoVerde.setSelected(false);
			fix.setRojoVerde(false);
		}

		if (mensaje.equals("laserAzul?encender=1")){
			tglbtnAzul.setSelected(true);
			fix.setAzul(true);
		}

		if (mensaje.equals("laserRojo?encender=1")){
			tglbtnRojoVerde.setSelected(true);
			fix.setRojoVerde(true);
		}
	}
}
