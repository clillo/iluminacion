package com.clillo.dmx.ui.jpanels.fixtures;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.clillo.dmx.comm.dmx.Dmx;

/**
 * 1.- Control Mode - 0 - 63 off 64-127 sound  128-191 auto 192-255 manual 
 * 2.- Pattern        0 - 39 static   40-255 dinamic
 * 3.- Strobe         0-30 no 31-255 strobe
 * 4.- Reservado
 * 5.- Horizontal Move 0-160 manual   161-220 unilateralismo 221-255 auto 
 * 6.- Vertical Move  0-160 manual   161-220 unilateralismo 221-255 auto
 * 7.- zoom            0-2  3-90  91-146   147-200   201-255
 * 8.- color          0-99 blue/green  100-199 blue   200-255 green
 * 9.- reset          200 reset
 * 10.- Y-axis        0-150  manual   151-255 auto
 * 11.- X-axis        0-150  manual   151-255 auto
 * 12.- Z-axis        0-150  manual   151-255 auto
 * 
 *
 */
public class PanelObservadorLaserRGB1 extends PanelObservador implements ActionListener{

	private static final long serialVersionUID = -3664280122489375394L;
	
	private JButton btnEncender;
	private JButton btnApagar;
	
	public PanelObservadorLaserRGB1() {
		setLayout(null);
		
		btnEncender = new JButton("Encender");
		btnEncender.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEncender.setBounds(37, 21, 71, 20);
		btnEncender.addActionListener(this);
		add(btnEncender);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnApagar.setBounds(111, 21, 71, 20);
		btnApagar.addActionListener(this);
		add(btnApagar);
	}

	@Override
	public void setId(String id) {
		super.setId(id);
		canales = new int[]{canalBase, canalBase+1, canalBase+2, canalBase+3, canalBase+4, canalBase+5, canalBase+6, canalBase+7, canalBase+8, canalBase+9, canalBase+10, canalBase+11};
	}
	
	@Override
	public void actualizaEnvioDmx(int canal, int valor) {
		//if (canal==canalBase)
		//	System.out.println(valor);	
	}

	@Override
	public void apagar() {
		Dmx.enviar(canalBase, 0);
	}

	@Override
	public void encender(){
		Dmx.enviar(canalBase, 220);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnApagar))
			fixture.apagar();

		if (e.getSource().equals(btnEncender))
			fixture.encender();
	}
}
