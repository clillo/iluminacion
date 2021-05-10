package com.clillo.dmx.ui.jpanels.opcionesMenu.ejecucionAutomatica;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.clillo.dmx.comm.serial.FactoryRS232;
import com.clillo.dmx.comm.serial.Rs232Listener;
import com.clillo.dmx.configuracion.GeneralesCfg;
import com.clillo.dmx.core.ejecucion.EjecutorAutomatico;
import com.clillo.dmx.core.ejecucion.EjecutorAutomaticoSingleton;
import com.clillo.dmx.core.ejecucion.EscenaAutomatico;
import com.clillo.dmx.core.ejecucion.ListaEjecutorAutomatico;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelEjecutorProgramas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMenuGenerico;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos.InterfacePanelEjecutorProgramas;

public class PanelEjecucionAutomatica extends PanelMenuGenerico implements ActionListener, ListSelectionListener, Rs232Listener{

	private static final long serialVersionUID = -5869553409971473557L;
		
	private JButton btnTodos;
	private JButton btnNinguno;
  	private JButton btnModificar;
	private JButton btnAgregar;

	private	JList<EscenaAutomatico> lstEscenas;
	
	private EjecutorAutomatico ejecutor;
	private EscenaAutomatico escenaActual;
	
	private JCheckBox chkSpider;
	private JCheckBox chkLaserMultiPunto; 
	private JCheckBox chkLaserIlda;
	private JCheckBox chkDerby;
	private JCheckBox chkBall;
	private JCheckBox chkPinSpot;
	private JCheckBox chkRgbw;
	private JCheckBox chkRgbwConjuntas;
	private JCheckBox chkMovingHeads;
	private JCheckBox chkScanners;
	
	private JCheckBox chkAutomatico;
	private JLabel lblProximoEn;
	private JButton btnCambiaprobabilidad;

	private JCheckBox chckbxEstHbilitadoEl;
	
	private long tiempoAnterior;

	public PanelEjecucionAutomatica() {
	    this.configura(375, 530, "Ejecución Automática de Fixtures");
	  	setLayout(null);
	  	
	  	JPanel pnlPanel1 = new JPanel();
	  	pnlPanel1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	pnlPanel1.setBounds(10, 11, 350, 263);
	  	add(pnlPanel1);
	  	pnlPanel1.setLayout(null);
	  		  	
	  	chkScanners = new JCheckBox("Scanners");
	  	chkScanners.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  	chkScanners.setBounds(6, 198, 97, 23);
	  	pnlPanel1.add(chkScanners);
	  	
	  	chkMovingHeads = new JCheckBox("Moving Heads");
	  	chkMovingHeads.setFont(new Font("Tahoma", Font.PLAIN, 16));

	  	chkMovingHeads.setBounds(6, 224, 141, 23);
	  	pnlPanel1.add(chkMovingHeads);
	  	
	  	chkRgbw = new JCheckBox("RGBW");
	  	chkRgbw.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  	chkRgbw.setBounds(6, 99, 86, 17);
	  	chkRgbw.addActionListener(this);
	  	pnlPanel1.add(chkRgbw);

	  	chkRgbwConjuntas = new JCheckBox("RGBW Conjuntas");
	  	chkRgbwConjuntas.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  	chkRgbwConjuntas.setBounds(6, 119, 159, 17);
	  	chkRgbwConjuntas.addActionListener(this);
	  	pnlPanel1.add(chkRgbwConjuntas);
	  	
	  	chkPinSpot = new JCheckBox("PinSpot");
	  	chkPinSpot.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  	chkPinSpot.setBounds(6, 59, 97, 17);
	  	chkPinSpot.addActionListener(this);
	  	pnlPanel1.add(chkPinSpot);
	  		  	
	  	btnTodos = new JButton("Todos");
	  	btnTodos.setBounds(221, 48, 97, 42);
	  	btnTodos.addActionListener(this);
	  	pnlPanel1.add(btnTodos);
	  	
	  	btnNinguno = new JButton("Ninguno");
	  	btnNinguno.setBounds(221, 109, 97, 44);
	  	btnNinguno.addActionListener(this);
	  	pnlPanel1.add(btnNinguno);
	  		  	
	  	chkDerby = new JCheckBox("Derby");
	  	chkDerby.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  	chkDerby.setBounds(6, 79, 163, 17);
	  	pnlPanel1.add(chkDerby);
	  	
	  	chkBall = new JCheckBox("Crystal Ball");
	  	chkBall.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  	chkBall.setBounds(6, 39, 121, 17);
	  	pnlPanel1.add(chkBall);
	  	
	  	chkLaserMultiPunto = new JCheckBox("Láser Multipunto");
	  	chkLaserMultiPunto.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  	chkLaserMultiPunto.setBounds(6, 138, 159, 17);
	  	pnlPanel1.add(chkLaserMultiPunto);

	  	chkLaserIlda = new JCheckBox("Láser Ilda");
	  	chkLaserIlda.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  	chkLaserIlda.setBounds(6, 158, 115, 17);
	  	pnlPanel1.add(chkLaserIlda);

	  	chkSpider = new JCheckBox("Spider");
	  	chkSpider.setFont(new Font("Tahoma", Font.PLAIN, 16));
	  	chkSpider.setBounds(6, 178, 159, 17);
	  	pnlPanel1.add(chkSpider);
	  	
	  	ListaFixturesParaAutomatica.agrega(chkScanners, PanelEjecutorProgramas.getPnlScanners());
	  	ListaFixturesParaAutomatica.agrega(chkMovingHeads, PanelEjecutorProgramas.getPnlMovingHeads());
	  	ListaFixturesParaAutomatica.agrega(chkRgbw, PanelEjecutorProgramas.getPnlRGBW());
	  	ListaFixturesParaAutomatica.agrega(chkRgbwConjuntas, PanelEjecutorProgramas.getPnlRGBWConjuntas());
	  	ListaFixturesParaAutomatica.agrega(chkPinSpot, PanelEjecutorProgramas.getPnlPinSpot());
	  	
	  	ListaFixturesParaAutomatica.agrega(chkSpider, PanelEjecutorProgramas.getPnlLaserIndividual(), 3);
	  	ListaFixturesParaAutomatica.agrega(chkLaserMultiPunto, PanelEjecutorProgramas.getPnlLaserIndividual(), 1);
	  	ListaFixturesParaAutomatica.agrega(chkLaserIlda, PanelEjecutorProgramas.getPnlLaserIndividual(), 2);
	  	ListaFixturesParaAutomatica.agrega(chkDerby, PanelEjecutorProgramas.getPnlDerby());
	  	
	  	chkAutomatico = new JCheckBox("Automático");
	  	chkAutomatico.setBounds(221, 166, 97, 23);
	  	pnlPanel1.add(chkAutomatico);
	  	
	  	lblProximoEn = new JLabel("Proximo en ...");
	  	lblProximoEn.setFont(new Font("Tahoma", Font.PLAIN, 18));
	  	lblProximoEn.setBounds(181, 224, 159, 31);
	  	pnlPanel1.add(lblProximoEn);
	  	chkAutomatico.addActionListener(this);
	  	
	  	JPanel panel = new JPanel();
	  	panel.setBounds(10, 285, 351, 199);
	  	panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	add(panel);
	  	panel.setLayout(null);
	  	
	  	JScrollPane scrollPane = new JScrollPane();
	  	scrollPane.setBounds(10, 11, 331, 155);
	  	panel.add(scrollPane);
	  	
	  	lstEscenas = new JList<EscenaAutomatico>();
	  	scrollPane.setViewportView(lstEscenas);
	  	
	  	btnAgregar = new JButton("Agregar");
	  	btnAgregar.addActionListener(this);
	  	btnAgregar.setBounds(10, 171, 71, 23);
	  	panel.add(btnAgregar);
	  	
	  	btnModificar = new JButton("Modificar");
	  	btnModificar.addActionListener(this);
	  	btnModificar.setBounds(259, 171, 82, 23);
	  	panel.add(btnModificar);
	  	
	  	btnCambiaprobabilidad = new JButton("CambiaProbabilidad");
	  	btnCambiaprobabilidad.addActionListener(this);
	  	btnCambiaprobabilidad.setBounds(124, 171, 125, 23);
	  	panel.add(btnCambiaprobabilidad);
	  	
		for (FixtureParaAutomatica chk: ListaFixturesParaAutomatica.getListaFixtures())
			chk.getChk().addActionListener(this);
		
		lstEscenas.setListData(ListaEjecutorAutomatico.obtieneLista());
		lstEscenas.addListSelectionListener(this);
		ejecutor = EjecutorAutomaticoSingleton.getEjecutor(this);
		lblProximoEn.setText("");
		
		chckbxEstHbilitadoEl = new JCheckBox("Está habilitado el plugin del Virtual DJ?");
		chckbxEstHbilitadoEl.setBounds(6, 7, 212, 23);
		chckbxEstHbilitadoEl.addActionListener(this);
		chckbxEstHbilitadoEl.setSelected(GeneralesCfg.isHabilitadoVDJ());
		pnlPanel1.add(chckbxEstHbilitadoEl);
		
		FactoryRS232.getEjecutor().agregaListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(chckbxEstHbilitadoEl)){
			GeneralesCfg.grabaHabilitadoVDJ(chckbxEstHbilitadoEl.isSelected());

			for (FixtureParaAutomatica chk: ListaFixturesParaAutomatica.getListaFixtures()){
				InterfacePanelEjecutorProgramas panel = chk.getPanel();
				panel.activaVDJ();
			}
			
			return ;
		}
		
		if (e.getSource().equals(chkAutomatico)){
			if (!chkAutomatico.isSelected()){
				lblProximoEn.setText("");
				lstEscenas.clearSelection();
				valueChanged(null);
				escenaActual = null;
			}
			else
				seleccionaAleatorio();

			ejecutor.ejecuta(chkAutomatico.isSelected());

			for (FixtureParaAutomatica chk: ListaFixturesParaAutomatica.getListaFixtures()){
				InterfacePanelEjecutorProgramas panel = chk.getPanel();
					
				if (chk.getIdSecundario()==0)
					panel.setCambioAutomatico(chk.getChk().isSelected());
				else
					panel.setCambioAutomatico(chk.getChk().isSelected(), chk.getIdSecundario());
			}
		}
		
		
		for (FixtureParaAutomatica chk: ListaFixturesParaAutomatica.getListaFixtures()){
			if (e.getSource().equals(chk.getChk())){
				InterfacePanelEjecutorProgramas panel = chk.getPanel();
				
				if (chk.getIdSecundario()==0)
					panel.setCambioAutomatico(chk.getChk().isSelected());
				else
					panel.setCambioAutomatico(chk.getChk().isSelected(), chk.getIdSecundario());
				
				return ;
			}
		}
		
		if (e.getSource().equals(btnTodos) || e.getSource().equals(btnNinguno)){
			boolean valor = e.getSource().equals(btnTodos);
			
			for (FixtureParaAutomatica chk: ListaFixturesParaAutomatica.getListaFixtures()){
				ActionEvent etmp = new ActionEvent(chk.getChk(), 1, null); 
				chk.getChk().setSelected(valor);
				actionPerformed(etmp);
			}
			
			if (e.getSource().equals(btnNinguno))
				lstEscenas.clearSelection();
		}
		
		if (e.getSource().equals(btnCambiaprobabilidad))
			cambiaProbabilidad();
		
		if (e.getSource().equals(btnModificar))
			modificar();
		
		if (e.getSource().equals(btnAgregar))
			agregar();
		

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e!=null && e.getValueIsAdjusting())
			return;

		EscenaAutomatico sel = lstEscenas.getSelectedValue();
		
		if (sel==null){
	/*		chkLaserIguales.setSelected(false);
			chkLaser1.setSelected(false);
			chkLaser2.setSelected(false);
			chkDerby.setSelected(false);
			chkDerby2.setSelected(false);
			chkBall.setSelected(false);
			chkPinSpot.setSelected(false);
			chkRgbw.setSelected(false);
			chkMovingHeads.setSelected(false);
			chkScanners.setSelected(false);*/
			return;
		}

		chkLaserMultiPunto.setSelected(sel.isLaserMultiPunto());
		chkLaserIlda.setSelected(sel.isLaserIlda());
		chkDerby.setSelected(sel.isDerby());
		chkBall.setSelected(sel.isBall());
		chkPinSpot.setSelected(sel.isPinSpot());
		chkRgbw.setSelected(sel.isrGBW());
		chkRgbwConjuntas.setSelected(sel.isrGBWConjuntas());
		chkMovingHeads.setSelected(sel.isMovingHeads());
		chkScanners.setSelected(sel.isScanners());
		chkSpider.setSelected(sel.isSpider());
		
		for (FixtureParaAutomatica chk: ListaFixturesParaAutomatica.getListaFixtures()){
			InterfacePanelEjecutorProgramas panel = chk.getPanel();
				
			if (chk.getIdSecundario()==0)
				panel.setCambioAutomatico(chk.getChk().isSelected());
			else
				panel.setCambioAutomatico(chk.getChk().isSelected(), chk.getIdSecundario());
		}
	}
	
	private void cambiaProbabilidad(){
		EscenaAutomatico sel = lstEscenas.getSelectedValue();
		
		if (sel==null)
			return;

		String strPrb = JOptionPane.showInputDialog(null, "Probabilidad Escena: "+sel.getNombre(), ""+sel.getProbabilidad());

		if (strPrb==null)
			return;

		sel.setProbabilidad(Integer.parseInt(strPrb));
		
		sel.grabar();
		ListaEjecutorAutomatico.refrescaProbabilidades();
		lstEscenas.setListData(ListaEjecutorAutomatico.obtieneLista());

		JOptionPane.showMessageDialog(null, "Cambios Grabados");
	}
	
	private void modificar(){
		EscenaAutomatico sel = lstEscenas.getSelectedValue();
		
		if (sel==null)
			return;
		
		sel.setBall(chkBall.isSelected());
		sel.setLaserMultiPunto(chkLaserMultiPunto.isSelected());
		sel.setLaserIlda(chkLaserIlda.isSelected());
		sel.setDerby(chkDerby.isSelected());
		sel.setPinSpot(chkPinSpot.isSelected());
		sel.setrGBW(chkRgbw.isSelected());
		sel.setrGBWConjuntas(chkRgbwConjuntas.isSelected());
		sel.setMovingHeads(chkMovingHeads.isSelected());
		sel.setScanners(chkScanners.isSelected());
		sel.setSpider(chkSpider.isSelected());
		
		sel.grabar();

		JOptionPane.showMessageDialog(null, "Cambios Grabados");
	}
	
	private void agregar(){
		String nombre = JOptionPane.showInputDialog(null, "Nombre de la escena");

		if (nombre==null)
			return;
		
		EscenaAutomatico sel = new EscenaAutomatico();
		sel.setNombre(nombre);
		sel.setNombreArchivo(ListaEjecutorAutomatico.obtieneNuevoNombre());
		
		sel.setBall(chkBall.isSelected());
		sel.setLaserMultiPunto(chkLaserMultiPunto.isSelected());
		sel.setLaserIlda(chkLaserIlda.isSelected());
		sel.setDerby(chkDerby.isSelected());
		sel.setPinSpot(chkPinSpot.isSelected());
		sel.setrGBW(chkRgbw.isSelected());
		sel.setMovingHeads(chkMovingHeads.isSelected());
		sel.setScanners(chkScanners.isSelected());
		sel.setSpider(chkSpider.isSelected());
		sel.grabar();
		
		ListaEjecutorAutomatico.refrescaLista();
		lstEscenas.setListData(ListaEjecutorAutomatico.obtieneLista());
	}
	
	public void seleccionaAleatorio(){
		int prox = ListaEjecutorAutomatico.siguienteAleatorio();
		lstEscenas.setSelectedIndex(prox);
		escenaActual = lstEscenas.getSelectedValue();
		valueChanged(null);
	}

	public JLabel getLblProximoEn() {
		return lblProximoEn;
	}

	public EscenaAutomatico getEscenaActual() {
		return escenaActual;
	}

	@Override
	public void actualizaEnvio(char valor) {
		long tiempoActual = System.currentTimeMillis();
		if ((tiempoActual - tiempoAnterior) < 1000)
			return;
		
		tiempoAnterior = tiempoActual;

		if (valor=='A' || valor=='B'){
			if (lstEscenas.getSelectedIndex()<0)
				lstEscenas.setSelectedIndex(0);
			else
				if (lstEscenas.getSelectedIndex()==0)
					lstEscenas.setSelectedIndex(ListaEjecutorAutomatico.obtieneLista().length -1);
				else
					lstEscenas.setSelectedIndex( (lstEscenas.getSelectedIndex()-1)% ListaEjecutorAutomatico.obtieneLista().length );
		}
		
		if (valor=='C' || valor=='D')
			lstEscenas.setSelectedIndex( (lstEscenas.getSelectedIndex()+1)% ListaEjecutorAutomatico.obtieneLista().length );

	}	
}
