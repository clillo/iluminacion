package com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.clillo.dmx.aleatorios.AleatorioPrioridad;
import com.clillo.dmx.configuracion.GeneralesCfg;
import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.configuracion.programas.TipoGatillador;
import com.clillo.dmx.programas.ConjuntoProgramas;
import com.clillo.dmx.tiempo.NotificableReloj;
import com.clillo.dmx.tiempo.RelojTO;
import com.clillo.dmx.tiempo.RelojUniversal;
import com.clillo.utiles.Log;
import javax.swing.JComboBox;

public class PanelTipoCambioAutomatico extends JPanel implements ActionListener, NotificableReloj{

	private static final long serialVersionUID = -5869553409971473557L;
	
	private JTextField txtTiempoCambio;
	private ButtonGroup grupoBotones;

	private JRadioButton rdbtnSinEfecto;
	private JRadioButton rdbtnAutomticoAleatorio;
	private JButton btnEfectosOff;
	private JComboBox<TipoGatillador> cbxTipo;
	
	private PanelSubMasterLista listener;
	
	private RelojTO reloj;
	private boolean primerProgramaApagado;
	
	private TipoGatillador tipoGatilladorSeleccionado;
	private ConjuntoProgramas conjuntoProgramas;

	private AleatorioPrioridad prioridadCambioTipoGatillador;
	
	public PanelTipoCambioAutomatico() {
	  	setLayout(null);
	  	
	  	rdbtnSinEfecto = new JRadioButton("Sin Efecto");
	  	rdbtnSinEfecto.setBounds(6, 7, 70, 15);
	  	rdbtnSinEfecto.setFont(new Font("Tahoma", Font.PLAIN, 9));
	  	rdbtnSinEfecto.addActionListener(this);
	  	add(rdbtnSinEfecto);
	  	
	  	rdbtnAutomticoAleatorio = new JRadioButton("Autom\u00E1tico Aleatorio");
	  	rdbtnAutomticoAleatorio.setBounds(178, 7, 115, 15);
	  	rdbtnAutomticoAleatorio.setFont(new Font("Tahoma", Font.PLAIN, 9));
	  	rdbtnAutomticoAleatorio.addActionListener(this);
	  	add(rdbtnAutomticoAleatorio);
	  	
	  	txtTiempoCambio = new JTextField();
	  	txtTiempoCambio.setBounds(299, 4, 55, 20);
	  	add(txtTiempoCambio);
	  	
	  	grupoBotones = new ButtonGroup();
	  	grupoBotones.add(rdbtnAutomticoAleatorio);
	  	grupoBotones.add(rdbtnSinEfecto);
	  	
	  	rdbtnSinEfecto.setSelected(true);
	  	
	  	btnEfectosOff = new JButton("Efectos Off");
	  	btnEfectosOff.setFont(new Font("Tahoma", Font.PLAIN, 9));
	  	btnEfectosOff.setBounds(364, 3, 86, 23);
	  	add(btnEfectosOff);
	  	
	  	btnEfectosOff.addActionListener(this);
	}

	public void setup(PanelSubMasterLista listener, ConjuntoProgramas conjuntoProgramas) {
		this.listener = listener;
		this.conjuntoProgramas = conjuntoProgramas;
		
	  	reloj = new RelojTO();
	  	reloj.setActivo(false);
	  	reloj.setGatillable(this);
	  	reloj.setTxtInterfaz(txtTiempoCambio);
	  	reloj.setIdReloj(conjuntoProgramas.getTipoPrograma().getNombre());
	  	conjuntoProgramas.setReloj(reloj);
		Log.debug("Agregando reloj al reloj universal: "+reloj, this.getClass());

	  	RelojUniversal.agregaReloj(reloj);
	  	
		Log.debug("Setup para el panel "+conjuntoProgramas.getTipoPrograma().getNombre(), this.getClass());
		
		activaVDJ();
	}
	
	public void procesaProbabilidades(){
		int[] probabilidades = new int[cbxTipo.getItemCount()];
		
		if (probabilidades.length==2){
			probabilidades[0] = 100;
			probabilidades[1] = 0;
		}else{
			probabilidades[0] = 40;
			probabilidades[1] = 30;
			probabilidades[2] = 10;
			probabilidades[3] = 20;
			probabilidades[4] = 0;			
		}

		prioridadCambioTipoGatillador = new AleatorioPrioridad(probabilidades);
	}
	
	/**
	 * Indica si hay algún programa que se puede gatillar con Virtual DJ
	 * @return
	 */
	private boolean tieneAlgunVJDeable(){
		for(ProgramaTO prg: conjuntoProgramas.getListaProgramas())
			if (prg.isEsVdjAble())
				return true;
		
		return false;
	}
	
	public void activaVDJ(){
		if (cbxTipo !=null)
			this.remove(cbxTipo);
		
		if (tieneAlgunVJDeable() && GeneralesCfg.isHabilitadoVDJ())
			cbxTipo = new JComboBox<TipoGatillador>(TipoGatillador.values());
		else{
			cbxTipo = new JComboBox<TipoGatillador>(new TipoGatillador[] {TipoGatillador.RelojInterno, TipoGatillador.RelojExterno});
		}
			
	  	cbxTipo.setFont(new Font("Tahoma", Font.PLAIN, 9));
	  	cbxTipo.setBounds(82, 4, 86, 20);
	  	cbxTipo.addActionListener(this);
	  	add(cbxTipo);
	  	
	  	procesaProbabilidades();	
		atenderCambiosCombo();
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e!=null && e.getSource().equals(btnEfectosOff)){
			apagaEfectos();
			return;
		}
		
		if (e!=null && e.getSource().equals(cbxTipo)){
			atenderCambiosCombo();
			return ;
		}
		
		if (rdbtnAutomticoAleatorio.isSelected())
		  	reloj.setActivo(true);
				
		if (rdbtnSinEfecto.isSelected())
		  	reloj.setActivo(false);
	}

	@Override
	public void tic(String nombre) {
		if (rdbtnAutomticoAleatorio.isSelected()){
			cbxTipo.setSelectedIndex(prioridadCambioTipoGatillador.siguiente());
			listener.cambiaAleatorio();
		}
	}
	
	public void apagaEfectos(){
		reloj.setActivo(false);
		if (primerProgramaApagado)
			listener.seleccionaPrimerPrograma();
		else
			listener.seleccionaNingunPrograma();
		rdbtnSinEfecto.setSelected(true);
	}
	
	public void enciendeAutomatico(){
		rdbtnAutomticoAleatorio.setSelected(true);
		actionPerformed(null);
		tic(null);
	}

	public boolean isPrimerProgramaApagado() {
		return primerProgramaApagado;
	}

	public void setPrimerProgramaApagado(boolean primerProgramaApagado) {
		this.primerProgramaApagado = primerProgramaApagado;
	}
	
	private void atenderCambiosCombo(){
		tipoGatilladorSeleccionado = (TipoGatillador) cbxTipo.getSelectedItem();
		
		for(ProgramaTO prg: conjuntoProgramas.getListaProgramas()){
			if (prg.isEsVdjAble())
				prg.setTipoGatillador(tipoGatilladorSeleccionado);
			
			if (prg.getTipoGatillador()==null)
				prg.setTipoGatillador(TipoGatillador.RelojInterno);
		}
	}
}
