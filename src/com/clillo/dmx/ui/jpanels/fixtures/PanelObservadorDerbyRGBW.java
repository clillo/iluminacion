package com.clillo.dmx.ui.jpanels.fixtures;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.core.fixtures.FixtureDerbyRGBW;
import com.clillo.dmx.ui.jpanels.fixtures.crystalMagic.IterfazNotificadorCambio;
import com.clillo.dmx.ui.jpanels.fixtures.crystalMagic.PanelCrystalMagicCanal;

public class PanelObservadorDerbyRGBW extends PanelObservador implements ActionListener, IterfazNotificadorCambio{

	private static final long serialVersionUID = -3664280122489375394L;

	private PanelCrystalMagicCanal pnlR;
	private PanelCrystalMagicCanal pnlG;
	private PanelCrystalMagicCanal pnlB;
	private PanelCrystalMagicCanal pnlW;
	private PanelCrystalMagicCanal pnlDimmer;
	private PanelCrystalMagicCanal pnlStrobo;
	private PanelCrystalMagicCanal pnlRotacion;
	private PanelCrystalMagicCanal pnlAuto;
	
	private JButton btnEncender;
	private JButton btnApagar;
	
	private FixtureDerbyRGBW fc;
	
	public PanelObservadorDerbyRGBW() {
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
		
		pnlW = new PanelCrystalMagicCanal();
		pnlW.setBounds(4, 64, 227, 20);
		pnlW.setNotificador(this);
		add(pnlW);
		
		btnEncender = new JButton("Encender");
		btnEncender.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEncender.setBounds(44, 214, 71, 20);
		btnEncender.addActionListener(this);
		add(btnEncender);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnApagar.setBounds(135, 214, 71, 20);
		btnApagar.addActionListener(this);
		add(btnApagar);
		
		pnlDimmer = new PanelCrystalMagicCanal();
		pnlDimmer.setBounds(4, 107, 227, 20);
		pnlDimmer.setNotificador(this);
		add(pnlDimmer);
		
		pnlStrobo = new PanelCrystalMagicCanal();
		pnlStrobo.setBounds(4, 131, 227, 20);
		pnlStrobo.setNotificador(this);
		add(pnlStrobo);
		
		pnlRotacion = new PanelCrystalMagicCanal();
		pnlRotacion.setBounds(4, 157, 227, 20);
		pnlRotacion.setNotificador(this);
		add(pnlRotacion);
		
		pnlAuto = new PanelCrystalMagicCanal();
		pnlAuto.setBounds(4, 183, 227, 20);
		pnlAuto.setNotificador(this);
		add(pnlAuto);
		
		JLabel lblNewLabel = new JLabel("R");
		lblNewLabel.setBounds(241, 4, 20, 14);
		add(lblNewLabel);
		
		JLabel lblG = new JLabel("G");
		lblG.setBounds(241, 24, 20, 14);
		add(lblG);
		
		JLabel lblW = new JLabel("B");
		lblW.setBounds(241, 44, 20, 14);
		add(lblW);
		
		JLabel lblW_1 = new JLabel("W");
		lblW_1.setBounds(241, 64, 20, 14);
		add(lblW_1);
		
		JLabel lblDimmer = new JLabel("Dimmer");
		lblDimmer.setBounds(241, 107, 46, 14);
		add(lblDimmer);
		
		JLabel lblStrobo = new JLabel("Strobo");
		lblStrobo.setBounds(241, 131, 46, 14);
		add(lblStrobo);
		
		JLabel lblRotacin = new JLabel("Rotaci\u00F3n");
		lblRotacin.setBounds(241, 157, 58, 14);
		add(lblRotacin);
		
		JLabel lblAuto = new JLabel("Auto");
		lblAuto.setBounds(241, 183, 46, 14);
		add(lblAuto);
	}

	@Override
	public void setId(String id) {
		super.setId(id);
		canales = new int[]{canalBase, canalBase+1, canalBase+2, canalBase+3, canalBase+4, canalBase+5, canalBase+6, canalBase+7};

		fc = (FixtureDerbyRGBW)fixture;
		
		pnlR.setNivel(fc.getR());
		pnlG.setNivel(fc.getG());
		pnlB.setNivel(fc.getB());
		pnlW.setNivel(fc.getW());
	}
	
	@Override
	public void actualizaEnvioDmx(int canal, int valor) {
		if (canal==canalBase)
			pnlDimmer.setNivel(valor);
		else
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
							pnlW.setNivel(valor);
						else
							if (canal==canalBase + 5)
								pnlStrobo.setNivel(valor);
							else
								if (canal==canalBase + 6)
									pnlRotacion.setNivel(valor);
								else
									if (canal==canalBase + 7)
										pnlAuto.setNivel(valor);
	}

	@Override
	public void apagar() {
		Dmx.enviar(canalBase, 0);
		Dmx.enviar(canalBase + 1, 0);
		Dmx.enviar(canalBase + 2, 0);
		Dmx.enviar(canalBase + 3, 0);
		Dmx.enviar(canalBase + 4, 0);
		Dmx.enviar(canalBase + 5, 0);
		Dmx.enviar(canalBase + 6, 0);
		Dmx.enviar(canalBase + 7, 0);
	}

	@Override
	public void encender(){
		Dmx.enviar(canalBase, fc.getDimmer());
		Dmx.enviar(canalBase + 1, fc.getR());
		Dmx.enviar(canalBase + 2, fc.getG());
		Dmx.enviar(canalBase + 3, fc.getB());
		Dmx.enviar(canalBase + 4, fc.getW());
		
		Dmx.enviar(canalBase + 5, fc.getStrobo());
		Dmx.enviar(canalBase + 6, fc.getRotacion());
		Dmx.enviar(canalBase + 7, fc.getAuto());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnApagar))
			fixture.apagar();

		if (e.getSource().equals(btnEncender))
			fixture.encender();
	}

	@Override
	public void cambio() {
		fc.setR(pnlR.getNivel());
		fc.setG(pnlG.getNivel());
		fc.setB(pnlB.getNivel());
		fc.setW(pnlW.getNivel());
		
		fc.setDimmer(pnlDimmer.getNivel());
		fc.setStrobo(pnlStrobo.getNivel());
		fc.setRotacion(pnlRotacion.getNivel());
		fc.setAuto(pnlAuto.getNivel());
		
		encender();
		fc.grabar();
	}
}
