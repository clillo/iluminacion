package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.clillo.dmx.tiempo.NotificableReloj;
import com.clillo.dmx.tiempo.RelojTO;
import com.clillo.dmx.tiempo.RelojUniversal;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMenuGenerico;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.ArchivoEncendidoYaml;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.ArchivoPosicionesYaml;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.Escena;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.FixtureToDmx;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.LectorPasosConjuntoYaml;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.NodoEscena;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.PasoElement;
import javax.swing.JSpinner;

public class PanelRoboticasConjuntas extends PanelMenuGenerico implements ActionListener, InformaCambiosUsuario, ChangeListener, Actualizable, ListSelectionListener, NotificableReloj, ItemListener {

	private static final long serialVersionUID = -5869553409971473557L;
	private JPanel pnl1;
	
	private List<Escena> listaPosiciones = new ArrayList<Escena>();
	private List<Escena> listaEncendidos = new ArrayList<Escena>();

	private JComboBox<Escena> lstEscenasPosiciones;
	private JComboBox<Escena> lstEscenasEncendido;
	private JList<PasoElement> lstPasosConjunto;
	private final Action action = new SwingAction();
	
	private static RelojTO reloj;
	private JTextField txtReloj;
	private JSpinner spnReloj;
	
	private int indice;
	
	public PanelRoboticasConjuntas() {
	    this.configura(500, 400, "Mantiene Figuras Robóticas");
	  	this.setLayout(null);

		pnl1 = new JPanel();
		pnl1.setLayout(null);
		pnl1.setBounds(0, 0, 553, 340);
		add(pnl1);
		try {
			
			listaPosiciones = new ArchivoPosicionesYaml().leeListaPosicionesEscenas();
			listaEncendidos = new ArchivoEncendidoYaml().leeListaPosicionesEscenas();

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(26, 183, 137, 95);
		pnl1.add(scrollPane_2);
		
		JList<Escena> list_1 = new JList<Escena>();
		scrollPane_2.setViewportView(list_1);
		
		JLabel lblPosicin = new JLabel("Posición");
		lblPosicin.setBounds(10, 12, 46, 14);
		pnl1.add(lblPosicin);
		
		JLabel lblEncendido = new JLabel("Encendido");
		lblEncendido.setBounds(92, 12, 61, 14);
		pnl1.add(lblEncendido);
		
		JLabel lblConjuntas = new JLabel("Conjuntas");
		lblConjuntas.setBounds(26, 158, 95, 14);
		pnl1.add(lblConjuntas);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(352, 37, 191, 292);
		pnl1.add(scrollPane_3);
		
		lstPasosConjunto = new JList<PasoElement>();
		scrollPane_3.setViewportView(lstPasosConjunto);
		
		JLabel lblPasos = new JLabel("Pasos");
		lblPasos.setBounds(352, 12, 61, 14);
		pnl1.add(lblPasos);
		
		JButton btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.setAction(action);
		btnEjecutar.setBounds(253, 291, 89, 23);
		pnl1.add(btnEjecutar);
		
		txtReloj = new JTextField();
		txtReloj.setBounds(253, 258, 86, 20);
		pnl1.add(txtReloj);
		txtReloj.setColumns(10);
		
		lstPasosConjunto.setListData(LectorPasosConjuntoYaml.leer().getPasos());
		
	  	reloj = new RelojTO();
	  	reloj.setActivo(false);
	  	reloj.setGatillable(this);
	  	reloj.setTxtInterfaz(txtReloj);
	  	
	  	lstEscenasPosiciones = new JComboBox<Escena>();
	  	lstEscenasPosiciones.setBounds(10, 35, 74, 23);
	  	pnl1.add(lstEscenasPosiciones);
	  	
	  	lstEscenasEncendido = new JComboBox<Escena>();
	  	lstEscenasEncendido.setBounds(92, 37, 71, 23);
	  	pnl1.add(lstEscenasEncendido);
	  	
	  	spnReloj = new JSpinner();
	  	spnReloj.setBounds(183, 36, 40, 20);
	  	pnl1.add(spnReloj);
	  	
	  	JLabel lblReloj = new JLabel("Reloj");
	  	lblReloj.setBounds(183, 12, 61, 14);
	  	pnl1.add(lblReloj);
	  	
	  	JSpinner spinner_1 = new JSpinner();
	  	spinner_1.setBounds(236, 36, 40, 20);
	  	pnl1.add(spinner_1);
	  	
	  	JLabel lblSpeed = new JLabel("Speed");
	  	lblSpeed.setBounds(236, 12, 61, 14);
	  	pnl1.add(lblSpeed);
	  	
		for (Escena e: obtieneListaPosiciones())
			lstEscenasPosiciones.addItem(e);
		
		for (Escena e: obtieneListaEncendidos())
			lstEscenasEncendido.addItem(e);
	  			
		lstEscenasEncendido.addItemListener(this);
	  	lstEscenasPosiciones.addItemListener(this);
	  	
	  	reloj.setIdReloj("roboticasConjunta");
	 
	  	RelojUniversal.agregaReloj(reloj);
	}
	
	public Escena[] obtieneListaPosiciones(){
		Escena[] s = new Escena[listaPosiciones.size()];
		
		int i=0;
		for (Escena ss: listaPosiciones)
			s[i++] = ss;
		return s;
	}
	
	public Escena[] obtieneListaEncendidos(){
		Escena[] s = new Escena[listaEncendidos.size()];
		
		int i=0;
		for (Escena ss: listaEncendidos)
			s[i++] = ss;
		return s;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void cambioVentana(FixtureRobotica entidad) {}

	@Override
	public void cambioPosicionCursor(int x, int y, double pan, double tilt, NodoEscena nodoActual) {}
	


	@Override
	public void stateChanged(ChangeEvent e) {}
	

	@Override
	public void cambioPosicionCursor(int x, int y, double pan, double tilt) {}
	


	@Override
	public void actualizaValores() {}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e!=null && e.getValueIsAdjusting())
			return;


		
	}
		
	private class SwingAction extends AbstractAction {

		private static final long serialVersionUID = -8667164890667312726L;
		public SwingAction() {
			putValue(NAME, "Ejecutar");
		
		}
		public void actionPerformed(ActionEvent e) {
			indice=0;
			reloj.setActivo(!reloj.isActivo());
		  	
		}
	}

	@Override
	public void tic(String nombre) {
		lstPasosConjunto.setSelectedIndex(indice);
		indice = (indice+1)%lstPasosConjunto.getModel().getSize();
		PasoElement pasoElement = lstPasosConjunto.getSelectedValue();
		lstEscenasPosiciones.setSelectedIndex(pasoElement.getPaso().getPosicion());
		lstEscenasEncendido.setSelectedIndex(pasoElement.getPaso().getEncendido());
				
		spnReloj.setValue(pasoElement.getPaso().getReloj());
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Escena selPosicion = (Escena)lstEscenasPosiciones.getSelectedItem();
		
		if (selPosicion==null){
			return;
		}
		
		Escena selEncendido = (Escena)lstEscenasEncendido.getSelectedItem();
		
		if (selEncendido==null){
			return;
		}
		

		
		FixtureToDmx.actualizaEncendidoNodoToDmx(selEncendido);
		FixtureToDmx.actualizaNodoToDmx(selPosicion);
		
		reloj.setSiguienteEjecucion(System.currentTimeMillis() + Long.parseLong(spnReloj.getValue().toString()));

	}
}
