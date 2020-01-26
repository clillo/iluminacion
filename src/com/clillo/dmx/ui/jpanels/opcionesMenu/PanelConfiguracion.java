package com.clillo.dmx.ui.jpanels.opcionesMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTextField;

import com.clillo.dmx.configuracion.GeneralesCfg;
import com.clillo.dmx.programas.conversorCanal.ConversorCanalDimmerRGBW;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class PanelConfiguracion extends PanelMenuGenerico implements  ChangeListener, ActionListener{

	private static final long serialVersionUID = -5869553409971473557L;

	private JSlider sldrDimmerRGBW;
	private JTextField txtDimmerRGBW;
	private JButton btnGrabar;
	private JCheckBox chckbxEstHbilitadoEl;
	
	public PanelConfiguracion() {
	    this.configura(420, 150, "Configuración");
	  	this.setLayout(null);
				
		JPanel pnlControlGlobal = new JPanel();
		pnlControlGlobal.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlControlGlobal.setBounds(10, 11, 379, 83);
		add(pnlControlGlobal);
		pnlControlGlobal.setLayout(null);

		txtDimmerRGBW = new JTextField();
		txtDimmerRGBW.setBounds(217, 12, 58, 20);
		pnlControlGlobal.add(txtDimmerRGBW);

		sldrDimmerRGBW = new JSlider();
		sldrDimmerRGBW.setBounds(10, 12, 200, 23);
		sldrDimmerRGBW.setValue((int)(ConversorCanalDimmerRGBW.getFactorDimmer()*100.0));

		txtDimmerRGBW.setText(sldrDimmerRGBW.getValue() + " %");
		pnlControlGlobal.add(sldrDimmerRGBW);
		
		btnGrabar = new JButton("Grabar");
		btnGrabar.setBounds(285, 11, 70, 23);
		pnlControlGlobal.add(btnGrabar);
		
		chckbxEstHbilitadoEl = new JCheckBox("Está habilitado el plugin del Virtual DJ?");
		chckbxEstHbilitadoEl.setBounds(23, 53, 213, 23);
		chckbxEstHbilitadoEl.addActionListener(this);
		chckbxEstHbilitadoEl.setSelected(GeneralesCfg.isHabilitadoVDJ());
		pnlControlGlobal.add(chckbxEstHbilitadoEl);
		
		btnGrabar.addActionListener(this);
		sldrDimmerRGBW.addChangeListener(this);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		txtDimmerRGBW.setText(sldrDimmerRGBW.getValue() + " %");
		ConversorCanalDimmerRGBW.setFactorDimmer(sldrDimmerRGBW.getValue()/100.0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnGrabar)){
			GeneralesCfg.grabaFactorDimmerRGBW(sldrDimmerRGBW.getValue());
			GeneralesCfg.grabaHabilitadoVDJ(chckbxEstHbilitadoEl.isSelected());
		}
	}
}
