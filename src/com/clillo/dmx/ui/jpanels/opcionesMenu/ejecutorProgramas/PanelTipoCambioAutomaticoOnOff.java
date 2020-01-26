package com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.clillo.dmx.programas.ConjuntoProgramas;
import com.clillo.dmx.tiempo.NotificableReloj;
import com.clillo.dmx.tiempo.RelojTO;
import com.clillo.dmx.tiempo.RelojUniversal;
import com.clillo.utiles.Log;

public class PanelTipoCambioAutomaticoOnOff extends JPanel implements ActionListener, NotificableReloj{

	private static final long serialVersionUID = -5869553409971473557L;
	private JTextField txtTiempoCambio;
	private ButtonGroup grupoBotones;

	private JRadioButton rdbtnSinEfecto;
	private JRadioButton rdbtnAutomticoSecuencial;
	private JButton btnEfectosOff;

	private PanelSubMasterLista listener;
	
	private RelojTO reloj;
	
	public PanelTipoCambioAutomaticoOnOff() {
	  	setLayout(null);
	  	
	  	rdbtnSinEfecto = new JRadioButton("Sin Efecto");
	  	rdbtnSinEfecto.setBounds(6, 7, 80, 15);
	  	rdbtnSinEfecto.setFont(new Font("Tahoma", Font.PLAIN, 9));
	  	rdbtnSinEfecto.addActionListener(this);
	  	add(rdbtnSinEfecto);

	  	rdbtnAutomticoSecuencial = new JRadioButton("Autom\u00E1tico Secuencial");
	  	rdbtnAutomticoSecuencial.setBounds(88, 7, 124, 15);
	  	rdbtnAutomticoSecuencial.setFont(new Font("Tahoma", Font.PLAIN, 9));
	  	rdbtnAutomticoSecuencial.addActionListener(this);
	  	add(rdbtnAutomticoSecuencial);
	  	
	  	txtTiempoCambio = new JTextField();
	  	txtTiempoCambio.setBounds(218, 4, 63, 20);
	  	add(txtTiempoCambio);
	  	
	  	grupoBotones = new ButtonGroup();
	  	grupoBotones.add(rdbtnAutomticoSecuencial);
	  	grupoBotones.add(rdbtnSinEfecto);
	  	
	  	rdbtnSinEfecto.setSelected(true);
	  	
	  	btnEfectosOff = new JButton("Efectos Off");
	  	btnEfectosOff.setFont(new Font("Tahoma", Font.PLAIN, 9));
	  	btnEfectosOff.setBounds(303, 3, 86, 23);
	  	add(btnEfectosOff);
	  	
	  	btnEfectosOff.addActionListener(this);
	}

	public void setup(PanelSubMasterLista listener, ConjuntoProgramas conjuntoProgramas) {
		this.listener = listener;
		
	  	reloj = new RelojTO();
	  	reloj.setActivo(false);
	  	reloj.setGatillable(this);
	  	reloj.setTxtInterfaz(txtTiempoCambio);
	  	reloj.setIdReloj(conjuntoProgramas.getTipoPrograma().getNombre());
	  	conjuntoProgramas.setReloj(reloj);
		Log.debug("Agregando reloj al reloj universal: "+reloj, this.getClass());

	  	RelojUniversal.agregaReloj(reloj);
	  	
		Log.debug("Setup para el panel "+conjuntoProgramas.getTipoPrograma().getNombre(), this.getClass());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e!=null && e.getSource().equals(btnEfectosOff)){
			apagaEfectos();
			return;
		}
		
		if (rdbtnAutomticoSecuencial.isSelected())
		  	reloj.setActivo(true);

		if (rdbtnSinEfecto.isSelected())
		  	reloj.setActivo(false);
	}

	@Override
	public void tic(String nombre) {
		if (rdbtnAutomticoSecuencial.isSelected())
			listener.cambiaSecuencial();
	}
	
	public void apagaEfectos(){
		reloj.setActivo(false);
		listener.seleccionaNingunPrograma();
		rdbtnSinEfecto.setSelected(true);
	}
	
	public void enciendeAutomatico(){
		rdbtnAutomticoSecuencial.setSelected(true);
		actionPerformed(null);
	}

}
