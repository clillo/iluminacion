package com.clillo.dmx.ui.jpanels.fixtures;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.core.fixtures.FixtureCrystalBall2;
import com.clillo.dmx.ui.jpanels.fixtures.crystalMagic.IterfazNotificadorCambio;
import com.clillo.dmx.ui.jpanels.fixtures.crystalMagic.PanelCrystalMagicCanal;

public class PanelObservadorCrystalBall2 extends PanelObservador implements ActionListener, IterfazNotificadorCambio{

	private static final long serialVersionUID = -3664280122489375394L;

	private PanelCrystalMagicCanal pnlR;
	private PanelCrystalMagicCanal pnlG;
	private PanelCrystalMagicCanal pnlB;
	
	private JCheckBox chkRotar;
	private JButton btnEncender;
	private JButton btnApagar;
	
	private FixtureCrystalBall2 fc;
	
	public PanelObservadorCrystalBall2() {
		setLayout(null);
		
		pnlR = new PanelCrystalMagicCanal();
		pnlR.setBounds(4, 4, 227, 20);
		pnlR.setNotificador(this);
		add(pnlR);

		pnlG = new PanelCrystalMagicCanal();
		pnlG.setBounds(4, 24, 227, 20);
		pnlG.setNotificador(this);
		add(pnlG);

		pnlB = new PanelCrystalMagicCanal();
		pnlB.setBounds(4, 44, 227, 20);
		pnlB.setNotificador(this);
		add(pnlB);
		
		chkRotar = new JCheckBox("Rotar");
		chkRotar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chkRotar.setBounds(4, 71, 47, 23);
		chkRotar.addActionListener(this);
		add(chkRotar);
		
		btnEncender = new JButton("Encender");
		btnEncender.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEncender.setBounds(65, 71, 71, 20);
		btnEncender.addActionListener(this);
		add(btnEncender);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnApagar.setBounds(139, 71, 71, 20);
		btnApagar.addActionListener(this);
		add(btnApagar);
	}

	@Override
	public void setId(String id) {
		super.setId(id);
		canales = new int[]{canalBase, canalBase+1, canalBase+2, canalBase+3, canalBase+4};

		fc = (FixtureCrystalBall2)fixture;
		
		pnlR.setNivel(fc.getR());
		pnlG.setNivel(fc.getG());
		pnlB.setNivel(fc.getB());
	}
	
	@Override
	public void actualizaEnvioDmx(int canal, int valor) {
		if (canal==canalBase + 1)
			pnlR.setNivel(valor);
		else
			if (canal==canalBase + 2)
				pnlG.setNivel(valor);
			else
				if (canal==canalBase + 3)
					pnlB.setNivel(valor);
				else
					if (canal==canalBase + 4)
						if (valor==0)
							chkRotar.setSelected(false);
						else
							chkRotar.setSelected(true);
	}

	@Override
	public void apagar() {
		Dmx.enviar(canalBase + 1, 0);
		Dmx.enviar(canalBase + 2, 0);
		Dmx.enviar(canalBase + 3, 0);
	}

	@Override
	public void encender(){
		Dmx.enviar(canalBase + 1, fc.getR());
		Dmx.enviar(canalBase + 2, fc.getG());
		Dmx.enviar(canalBase + 3, fc.getB());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnApagar))
			fixture.apagar();

		if (e.getSource().equals(btnEncender))
			fixture.encender();
		
		if (e.getSource().equals(chkRotar))
			if (chkRotar.isSelected())
				Dmx.enviar(canalBase + 4, fc.getRotar());
			else
				Dmx.enviar(canalBase + 4, 0);
	}

	@Override
	public void cambio() {
		fc.setR(pnlR.getNivel());
		fc.setG(pnlG.getNivel());
		fc.setB(pnlB.getNivel());
		encender();
		fc.grabar();
	}
}
