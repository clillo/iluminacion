package com.clillo.dmx.ui.jpanels.opcionesMenu.fixturesrgb;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorPinSpotAli;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorRGBWSimple;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelEjecutorProgramas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos.InterfacePanelEjecutorProgramas;

public class PanelRealista extends JPanel implements ActionListener{

	private static final long serialVersionUID = -49764234619705052L;
	
	private PanelObservadorRGBWSimple pnlRGBW1;
	private PanelObservadorRGBWSimple pnlRGBW2;
	private PanelObservadorRGBWSimple pnlRGBW3;
	private PanelObservadorRGBWSimple pnlRGBW4;

	private JCheckBox chkAutomaticoPinSpot;
	private JButton btnApagarPinSpot;
		
	private PanelObservadorPinSpotAli pnlPinSpot3;
	private PanelObservadorPinSpotAli pnlPinSpot4;
	
	public PanelRealista() {
		super();
		this.setLayout(null);

		panelRGB();
		panelPinSpot();	
	}

	
	private void panelPinSpot(){
		JPanel pnlPinSpot = new JPanel();
		pnlPinSpot.setLayout(null);
		pnlPinSpot.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlPinSpot.setBounds(10, 97, 461, 36);
		add(pnlPinSpot);

		pnlPinSpot3 = new PanelObservadorPinSpotAli();
		pnlPinSpot3.setBounds(10, 11, 90, 20);
		pnlPinSpot.add(pnlPinSpot3);
		pnlPinSpot3.setId("pinSpot3");

		pnlPinSpot4 = new PanelObservadorPinSpotAli();
		pnlPinSpot4.setBounds(80, 11, 90, 20);
		pnlPinSpot.add(pnlPinSpot4);
		pnlPinSpot4.setId("pinSpot4");

		
		chkAutomaticoPinSpot = new JCheckBox("Autom\u00E1tico");
		chkAutomaticoPinSpot.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chkAutomaticoPinSpot.setBounds(297, 9, 79, 20);
		chkAutomaticoPinSpot.addActionListener(this);
		pnlPinSpot.add(chkAutomaticoPinSpot);

		btnApagarPinSpot = new JButton("Apagar");
		btnApagarPinSpot.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnApagarPinSpot.setBounds(382, 8, 62, 20);
		btnApagarPinSpot.addActionListener(this);
		pnlPinSpot.add(btnApagarPinSpot);
		
		Dmx.registraEnviable(pnlPinSpot3);
		Dmx.registraEnviable(pnlPinSpot4);
	}
		
	private void panelRGB() {
		JPanel pnlRGB = new JPanel();
		pnlRGB.setLayout(null);
		pnlRGB.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlRGB.setBounds(10, 11, 430, 65);
		add(pnlRGB);

		pnlRGBW1 = new PanelObservadorRGBWSimple();
		pnlRGBW1.setBounds(10, 11, 40, 40);
		pnlRGB.add(pnlRGBW1);
		pnlRGBW1.setId("rgbw1");

		pnlRGBW2 = new PanelObservadorRGBWSimple();
		pnlRGBW2.setBounds(60, 11, 40, 40);
		pnlRGB.add(pnlRGBW2);
		pnlRGBW2.setId("rgbw2");

		pnlRGBW3 = new PanelObservadorRGBWSimple();
		pnlRGBW3.setBounds(110, 11, 40, 40);
		pnlRGB.add(pnlRGBW3);
		pnlRGBW3.setId("rgbw3");

		pnlRGBW4 = new PanelObservadorRGBWSimple();
		pnlRGBW4.setBounds(160, 11, 40, 40);
		pnlRGB.add(pnlRGBW4);
		pnlRGBW4.setId("rgbw4");

		Dmx.registraEnviable(pnlRGBW1);
		Dmx.registraEnviable(pnlRGBW2);
		Dmx.registraEnviable(pnlRGBW3);
		Dmx.registraEnviable(pnlRGBW4);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		InterfacePanelEjecutorProgramas pnlPinSpot = PanelEjecutorProgramas.getPnlPinSpot();
		
		if(e.getSource().equals(chkAutomaticoPinSpot))
			pnlPinSpot.setCambioAutomatico(chkAutomaticoPinSpot.isSelected());

		if(e.getSource().equals(btnApagarPinSpot)){
			pnlPinSpot.apagar();
			pnlPinSpot3.apagar();
			pnlPinSpot4.apagar();
			chkAutomaticoPinSpot.setSelected(false);
		}
	}

}