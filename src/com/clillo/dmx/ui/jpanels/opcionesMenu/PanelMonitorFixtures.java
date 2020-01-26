package com.clillo.dmx.ui.jpanels.opcionesMenu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorPinSpot;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorPinSpotAli;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorRGBW;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservadorRGBW4;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos.InterfacePanelEjecutorProgramas;

public class PanelMonitorFixtures extends PanelMenuGenerico implements ActionListener {

	private static final long serialVersionUID = -5869553409971473557L;

	private JCheckBox chkAutomaticoPinSpot;
	private JButton btnApagarPinSpot;
	
	private JCheckBox chkAutomaticoRGB;
	private JButton btnApagarRGB;
	
	private PanelObservadorPinSpot pnlPinSpot1;
	private PanelObservadorPinSpot pnlPinSpot2;
	private PanelObservadorPinSpotAli pnlPinSpot3;
	private PanelObservadorPinSpotAli pnlPinSpot4;

	private PanelObservadorRGBW pnlRGBW1;
	private PanelObservadorRGBW pnlRGBW2;
	private PanelObservadorRGBW pnlRGBW3;
	private PanelObservadorRGBW pnlRGBW4;

	private PanelObservadorRGBW4 pnlRGBWTodas;
	
	public PanelMonitorFixtures() {
		this.configura(560, 150, "Monitor Fixtures RGB");
		this.setLayout(null);

		panelRGB();
		panelPinSpot();
	}	
	
	private void panelPinSpot(){
		JPanel pnlPinSpot = new JPanel();
		pnlPinSpot.setLayout(null);
		pnlPinSpot.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlPinSpot.setBounds(10, 58, 461, 36);
		add(pnlPinSpot);

		pnlPinSpot1 = new PanelObservadorPinSpot();
		pnlPinSpot1.setBounds(151, 11, 60, 20);
		pnlPinSpot.add(pnlPinSpot1);
		pnlPinSpot1.setId("pinSpot1");

		pnlPinSpot2 = new PanelObservadorPinSpot();
		pnlPinSpot2.setBounds(219, 11, 60, 20);
		pnlPinSpot.add(pnlPinSpot2);
		pnlPinSpot2.setId("pinSpot2");

		pnlPinSpot3 = new PanelObservadorPinSpotAli();
		pnlPinSpot3.setBounds(10, 11, 60, 20);
		pnlPinSpot.add(pnlPinSpot3);
		pnlPinSpot3.setId("pinSpot3");

		pnlPinSpot4 = new PanelObservadorPinSpotAli();
		pnlPinSpot4.setBounds(80, 11, 60, 20);
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
		
		Dmx.registraEnviable(pnlPinSpot1);
		Dmx.registraEnviable(pnlPinSpot2);
		Dmx.registraEnviable(pnlPinSpot3);
		Dmx.registraEnviable(pnlPinSpot4);
	}
		
	private void panelRGB() {
		JPanel pnlRGB = new JPanel();
		pnlRGB.setLayout(null);
		pnlRGB.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlRGB.setBounds(10, 11, 535, 36);
		add(pnlRGB);

		pnlRGBW1 = new PanelObservadorRGBW();
		pnlRGBW1.setBounds(10, 11, 60, 20);
		pnlRGB.add(pnlRGBW1);
		pnlRGBW1.setId("rgbw1");

		pnlRGBW2 = new PanelObservadorRGBW();
		pnlRGBW2.setBounds(80, 11, 60, 20);
		pnlRGB.add(pnlRGBW2);
		pnlRGBW2.setId("rgbw2");

		pnlRGBW3 = new PanelObservadorRGBW();
		pnlRGBW3.setBounds(150, 11, 60, 20);
		pnlRGB.add(pnlRGBW3);
		pnlRGBW3.setId("rgbw3");

		pnlRGBW4 = new PanelObservadorRGBW();
		pnlRGBW4.setBounds(220, 11, 60, 20);
		pnlRGB.add(pnlRGBW4);
		pnlRGBW4.setId("rgbw4");
		
		pnlRGBWTodas = new PanelObservadorRGBW4();
		pnlRGBWTodas.setBounds(465, 11, 60, 20);
		pnlRGBWTodas.setId(pnlRGBW1, pnlRGBW2, pnlRGBW3, pnlRGBW4);
		pnlRGB.add(pnlRGBWTodas);

		chkAutomaticoRGB = new JCheckBox("Autom\u00E1tico");
		chkAutomaticoRGB.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chkAutomaticoRGB.setBounds(297, 9, 79, 20);
		chkAutomaticoRGB.addActionListener(this);
		pnlRGB.add(chkAutomaticoRGB);

		btnApagarRGB = new JButton("Apagar");
		btnApagarRGB.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnApagarRGB.setBounds(382, 8, 62, 20);
		btnApagarRGB.addActionListener(this);
		pnlRGB.add(btnApagarRGB);

		Dmx.registraEnviable(pnlRGBW1);
		Dmx.registraEnviable(pnlRGBW2);
		Dmx.registraEnviable(pnlRGBW3);
		Dmx.registraEnviable(pnlRGBW4);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		InterfacePanelEjecutorProgramas pnlPinSpot = PanelEjecutorProgramas.getPnlPinSpot();
		InterfacePanelEjecutorProgramas pnlRGB = PanelEjecutorProgramas.getPnlRGBW();
		
		if(e.getSource().equals(chkAutomaticoPinSpot))
			pnlPinSpot.setCambioAutomatico(chkAutomaticoPinSpot.isSelected());

		if(e.getSource().equals(chkAutomaticoRGB))
			pnlRGB.setCambioAutomatico(chkAutomaticoRGB.isSelected());

		if(e.getSource().equals(btnApagarPinSpot)){
			pnlPinSpot.apagar();
			pnlPinSpot1.apagar();
			pnlPinSpot2.apagar();
			pnlPinSpot3.apagar();
			pnlPinSpot4.apagar();
			chkAutomaticoPinSpot.setSelected(false);
		}
		
		if(e.getSource().equals(btnApagarRGB)){
			pnlRGB.apagar();
			pnlRGBW1.apagar();
			pnlRGBW2.apagar();
			pnlRGBW3.apagar();
			pnlRGBW4.apagar();
			chkAutomaticoRGB.setSelected(false);
		}
	}
}
