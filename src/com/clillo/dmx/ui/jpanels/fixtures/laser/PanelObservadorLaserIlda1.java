package com.clillo.dmx.ui.jpanels.fixtures.laser;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.configuracion.GeneralesCfg;
import com.clillo.dmx.configuracion.GoboNombreValorCfg;
import com.clillo.dmx.core.fixtures.robotizados.GoboNombreValor;
import com.clillo.dmx.ui.jpanels.fixtures.PanelObservador;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * 1.- Control Mode - 	0 off 
 * 						1-40 SD view
 * 						41-80 SD view sound
 * 			 			81-100 SD playlist
 * 						101-120 SD play sound
 * 						121-160 preset effect
 * 						161-200 preset effect sound
 * 						201-240 DMX prog
 * 						241-255 DMX prog sound
 * 2.- Intensidad - 	0 N/A 
 * 						1-40 N/A
 * 						41-80 sound intensity
 * 			 			81-100 N/A
 * 						101-120 sound intensity
 * 						121-160 N/A
 * 						161-200 sound intensity
 * 						201-240 N/A
 * 						241-255 sound intensity
 * 3.- Design selection mode DMX
 * 4.- SD Card Folder select
 * 5.- SD Card file select
 *
 */
public class PanelObservadorLaserIlda1 extends PanelObservador implements ActionListener, ListSelectionListener, ChangeListener, AdjustmentListener{

	private static final long serialVersionUID = -3664280122489375394L;
	
	private JButton btnEncender;
	private JButton btnApagar;
	private JList<GoboNombreValor> lstEfectos;
	private JCheckBox chckbxCambioActivadoPor;
	private JScrollBar scrollBar;
	private JLabel lblSensibilidad;
	
	private int valorEncendido;
	public static int sesibilidadLaserIlda;
	private boolean encendido;
	
	public PanelObservadorLaserIlda1() {
		setLayout(null);
		
		btnEncender = new JButton("Encender");
		btnEncender.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEncender.setBounds(37, 210, 71, 20);
		btnEncender.addActionListener(this);
		add(btnEncender);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnApagar.setBounds(111, 210, 71, 20);
		btnApagar.addActionListener(this);
		add(btnApagar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 189, 162);
		add(scrollPane);
		
		ArrayList<GoboNombreValor> lista = GoboNombreValorCfg.getListaGoboNombreValor("laserIlda1.file");
		GoboNombreValor listaTmp[] = new GoboNombreValor[lista.size()] ;
		int i = 0;
		for (GoboNombreValor par:lista)
			listaTmp[i ++] = par;

		lblSensibilidad = new JLabel("255");
		lblSensibilidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblSensibilidad.setBounds(199, 213, 28, 14);
		add(lblSensibilidad);		
		
		lstEfectos = new JList<GoboNombreValor>(listaTmp);
		scrollPane.setViewportView(lstEfectos);
		lstEfectos.setSelectedIndex(0);
		lstEfectos.addListSelectionListener(this);
		
		scrollBar = new JScrollBar();
		scrollBar.setMaximum(255);
		scrollBar.setMinimum(0);
		scrollBar.setBounds(207, 11, 17, 188);
		
		scrollBar.addAdjustmentListener(this);
		add(scrollBar);
		
		chckbxCambioActivadoPor = new JCheckBox("Cambio Activado Por Sonido");
		chckbxCambioActivadoPor.setBounds(6, 11, 166, 23);
		chckbxCambioActivadoPor.addChangeListener(this);
		add(chckbxCambioActivadoPor);
				
		valorEncendido = 1;
	}

	@Override
	public void setId(String id) {
		super.setId(id);
		canales = new int[]{canalBase, canalBase+1, canalBase+2, canalBase+3, canalBase+4};
		scrollBar.setValue(sesibilidadLaserIlda);
		adjustmentValueChanged(null); 
		encendido = false;
	}
	
	@Override
	public void actualizaEnvioDmx(int canal, int valor) {
		if (canal == canalBase){
			encendido = valor!=0;
			chckbxCambioActivadoPor.setSelected(valor==85);
		}else
			if (canal == canalBase + 4){
				lstEfectos.setSelectedIndex(valor);
				lstEfectos.ensureIndexIsVisible(lstEfectos.getSelectedIndex());
			}
	}

	@Override
	public void apagar() {
		Dmx.enviar(canalBase, 0);
		encendido = false;
	}

	@Override
	public void encender(){
		Dmx.enviar(canalBase, valorEncendido);
		encendido = true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnApagar))
			fixture.apagar();

		if (e.getSource().equals(btnEncender))
			fixture.encender();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		Dmx.enviar(canalBase + 4, lstEfectos.getSelectedValue().getValor());
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		valorEncendido = (chckbxCambioActivadoPor.isSelected()?85:1);
		if (encendido)
			encender();
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		Dmx.enviar(canalBase + 1, scrollBar.getValue());
		lblSensibilidad.setText(scrollBar.getValue()+"");
		GeneralesCfg.grabaSensibilidadLaserIlda(scrollBar.getValue());
	}
}
