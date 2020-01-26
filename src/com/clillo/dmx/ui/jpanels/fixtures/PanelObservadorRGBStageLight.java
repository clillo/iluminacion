package com.clillo.dmx.ui.jpanels.fixtures;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.core.fixtures.FixtureRGBStageLight;
import com.clillo.dmx.ui.jpanels.fixtures.crystalMagic.IterfazNotificadorCambio;
import com.clillo.dmx.ui.jpanels.fixtures.crystalMagic.PanelCrystalMagicCanal;

/**
 * 1.- Dimmer global  0 - 255
 * 2.- Rojo Naranja   0 - 255
 * 3.- Verde Violeta  0 - 255
 * 4.- Azul Blanco    0 - 255
 * 5.- Verde Amarillo 0 - 255
 * 6.- Strobo         0 - 255
 * 7.- Rotar          0 - 128  128 - 255
 * 8.- Programas automáticos 0 - 149 - 169 - 210 - 255 
 * 
 * @author carlos
 *
 */
public class PanelObservadorRGBStageLight extends PanelObservador implements ActionListener, IterfazNotificadorCambio{

	private static final long serialVersionUID = -3664280122489375394L;

	private PanelCrystalMagicCanal pnlVeVio;
	private PanelCrystalMagicCanal pnlAzBl;
	private PanelCrystalMagicCanal pnlVeAm;
	private PanelCrystalMagicCanal pnlDimmer;
	private PanelCrystalMagicCanal pnlRoNa;
	private PanelCrystalMagicCanal pnlStrobo;
	private PanelCrystalMagicCanal pnlRotar;
	private PanelCrystalMagicCanal pnlAuto;
	
	private JButton btnEncender;
	private JButton btnApagar;
	
	private FixtureRGBStageLight fc;
	
	public PanelObservadorRGBStageLight() {
		setLayout(null);
		
		pnlVeVio = new PanelCrystalMagicCanal();
		pnlVeVio.setBounds(4, 63, 227, 20);
		pnlVeVio.setNotificador(this);
		add(pnlVeVio);

		pnlAzBl = new PanelCrystalMagicCanal();
		pnlAzBl.setBounds(4, 83, 227, 20);
		pnlAzBl.setNotificador(this);
		add(pnlAzBl);

		pnlVeAm = new PanelCrystalMagicCanal();
		pnlVeAm.setBounds(4, 103, 227, 20);
		pnlVeAm.setNotificador(this);
		add(pnlVeAm);
		
		btnEncender = new JButton("Encender");
		btnEncender.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEncender.setBounds(47, 214, 71, 20);
		btnEncender.addActionListener(this);
		add(btnEncender);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnApagar.setBounds(121, 214, 71, 20);
		btnApagar.addActionListener(this);
		add(btnApagar);
		
		pnlDimmer = new PanelCrystalMagicCanal();
		pnlDimmer.setBounds(4, 11, 227, 20);
		pnlDimmer.setNotificador(this);
		add(pnlDimmer);
		
		pnlRoNa = new PanelCrystalMagicCanal();
		pnlRoNa.setBounds(4, 42, 227, 20);
		pnlRoNa.setNotificador(this);
		add(pnlRoNa);
		
		pnlStrobo = new PanelCrystalMagicCanal();
		pnlStrobo.setBounds(4, 135, 227, 20);
		pnlStrobo.setNotificador(this);
		add(pnlStrobo);
		
		pnlRotar = new PanelCrystalMagicCanal();
		pnlRotar.setBounds(4, 155, 227, 20);
		pnlRotar.setNotificador(this);
		add(pnlRotar);
		
		pnlAuto = new PanelCrystalMagicCanal();
		pnlAuto.setBounds(4, 183, 227, 20);
		pnlAuto.setNotificador(this);
		add(pnlAuto);
	}

	@Override
	public void setId(String id) {
		super.setId(id);
		canales = new int[]{canalBase, canalBase+1, canalBase+2, canalBase+3, canalBase+4, canalBase+5, canalBase+6, canalBase+7};

		fc = (FixtureRGBStageLight)fixture;
		
		pnlDimmer.setNivel(fc.getDimmer());
		
		pnlRoNa.setNivel(fc.getRojoNaranja());
		pnlVeVio.setNivel(fc.getVerdeVioleta());
		pnlAzBl.setNivel(fc.getAzulBlanco());
		pnlVeAm.setNivel(fc.getVerdeAmarillo());
		
		pnlStrobo.setNivel(fc.getStobo());
		pnlRotar.setNivel(fc.getRotar());
		pnlAuto.setNivel(fc.getAutomatico());
	}
	
	@Override
	public void actualizaEnvioDmx(int canal, int valor) {
		if (canal==canalBase )
			pnlDimmer.setNivel(valor);
		else
			if (canal==canalBase + 1)
				pnlRoNa.setNivel(valor);
			else
				if (canal==canalBase + 2)
					pnlVeVio.setNivel(valor);
				else
					if (canal==canalBase + 3)
						pnlAzBl.setNivel(valor);
					else
						if (canal==canalBase + 4)
							pnlVeAm.setNivel(valor);
						else
							if (canal==canalBase + 5)
								pnlStrobo.setNivel(valor);
							else
								if (canal==canalBase + 6)
									pnlRotar.setNivel(valor);
								else
									if (canal==canalBase + 7)
										pnlAuto.setNivel(valor);
	}

	@Override
	public void apagar() {
		Dmx.enviar(canalBase, 0);
		Dmx.enviar(canalBase + 7, 0);
	}

	@Override
	public void encender(){
		Dmx.enviar(canalBase, 255);
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
		Dmx.enviar(canalBase, pnlDimmer.getNivel());
		
		Dmx.enviar(canalBase + 1, pnlRoNa.getNivel());
		Dmx.enviar(canalBase + 2, pnlVeVio.getNivel());
		Dmx.enviar(canalBase + 3, pnlAzBl.getNivel());
		Dmx.enviar(canalBase + 4, pnlVeAm.getNivel());
		
		Dmx.enviar(canalBase + 5, pnlStrobo.getNivel());
		Dmx.enviar(canalBase + 6, pnlRotar.getNivel());
		Dmx.enviar(canalBase + 7, pnlAuto.getNivel());
		//encender();
		//fc.grabar();
	}
}
