package com.clillo.dmx.ui.jpanels.opcionesMenu.escenas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;

import com.clillo.dmx.comm.vdj.FactoryVDJ;
import com.clillo.dmx.configuracion.escenas.EscenasCfg;
import com.clillo.dmx.core.escenas.Escena;
import com.clillo.dmx.core.escenas.FactoryEjecutorEscenas;
import com.clillo.dmx.core.escenas.ListenerAvancePasosEscena;

public class PanelEjecutorEscenas extends JPanel implements ListSelectionListener, AdjustmentListener, ActionListener, ListenerAvancePasosEscena  {

	private static final long serialVersionUID = -5064098610578217030L;
	
	private JList<Escena> lstEscenas;
	private JTable tblPasos;
	private DefaultListModel<Escena> modeloLista;

	private JToggleButton btnNormal; 
	private JTextField txtVelocidad;
	private JScrollBar scrlFactor;
	private JTextField txtMinimo;
	private JTextField txtMaximo;
	private JToggleButton btnVDJ;
	
	private JButton btnEditarPasoActual;
	private JButton btnGuardarCambios;
	private JButton btnAgregarPaso;

	public PanelEjecutorEscenas() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 120, 256);
		add(scrollPane);
		
		modeloLista = new DefaultListModel<Escena>();

		lstEscenas = new JList<Escena>(modeloLista);
		lstEscenas.addListSelectionListener(this);
		scrollPane.setViewportView(lstEscenas);
			
		JScrollPane scrollPane2 = new JScrollPane();
	  	scrollPane2.setBounds(139, 11, 453, 256);
	  	add(scrollPane2);
	  	
	  	tblPasos = new JTable();
	  	tblPasos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	  	scrollPane2.setViewportView(tblPasos);
	  	
	  	JLabel lblNewLabel = new JLabel("T\u00EDtulo de la Tabla");
	  	scrollPane2.setColumnHeaderView(lblNewLabel);
	  	
	  	txtVelocidad = new JTextField();
	  	txtVelocidad.setText("");
	  	txtVelocidad.setBounds(602, 11, 44, 20);
	  	add(txtVelocidad);
	  	
	  	txtMinimo = new JTextField();
	  	txtMinimo.setText("");
	  	txtMinimo.setBounds(656, 11, 44, 20);
	  	add(txtMinimo);
	  	
	  	txtMaximo = new JTextField();
	  	txtMaximo.setText("");
	  	txtMaximo.setBounds(710, 11, 44, 20);
	  	add(txtMaximo);
	  	
	  	scrlFactor = new JScrollBar();
	  	scrlFactor.setOrientation(JScrollBar.HORIZONTAL);
	  	scrlFactor.setBounds(602, 42, 152, 16);
	  	add(scrlFactor);
	
	  	scrlFactor.addAdjustmentListener(this);
	  	
	  	btnNormal = new JToggleButton("Ejecuci\u00F3n Normal");
	  	btnNormal.addActionListener(this);
	  	btnNormal.setBounds(602, 66, 152, 23);
	  	add(btnNormal);
	  	
	  	btnVDJ = new JToggleButton("Ejecuci\u00F3n Virtual DJ");
	  	btnVDJ.setBounds(602, 100, 152, 23);
	  	btnVDJ.addActionListener(this);
	  	add(btnVDJ);
	  	
	  	btnEditarPasoActual = new JButton("Editar Paso Actual");
	  	btnEditarPasoActual.setBounds(602, 214, 152, 23);
	  	btnEditarPasoActual.addActionListener(this);
	  	add(btnEditarPasoActual);
	  	
	  	btnGuardarCambios = new JButton("Guardar Cambios");
	  	btnGuardarCambios.setBounds(602, 244, 152, 23);
	  	btnGuardarCambios.addActionListener(this);
	  	add(btnGuardarCambios);
	  	
	  	btnAgregarPaso = new JButton("Agregar Paso");
	  	btnAgregarPaso.setBounds(602, 185, 152, 23);
	  	btnAgregarPaso.addActionListener(this);
	  	add(btnAgregarPaso);
	}
	
	public void setup(){
		ArrayList<Escena> listaEscenas = EscenasCfg.getListaEscenas();
		for (Escena e: listaEscenas)
			modeloLista.addElement(e);
	}
	
	private void seleccionaEscena(Escena escenaActual){
		tblPasos.setModel(new ModeloTablaPasos(escenaActual));
		
		EditorRendererPasos editorRenderer = new EditorRendererPasos(escenaActual.getTipoCanales());
		tblPasos.setDefaultRenderer(Object.class, editorRenderer);
		tblPasos.setDefaultEditor(Object.class, editorRenderer);
        
		TableColumnModel tcm = tblPasos.getColumnModel();
		
		tcm.getColumn(0).setPreferredWidth(27);
		
		for (int i=0; i<tblPasos.getColumnModel().getColumnCount()-1; i++)
			tcm.getColumn(i+1).setPreferredWidth(100);
	  	
		txtVelocidad.setText(escenaActual.getVelocidad()+"");
		txtMinimo.setText(escenaActual.getMinimo()+"");
		txtMaximo.setText(escenaActual.getMaximo()+"");

		scrlFactor.removeAdjustmentListener(this );
	  	scrlFactor.setMinimum(escenaActual.getMinimo());
	  	scrlFactor.setMaximum(escenaActual.getMaximo());
		scrlFactor.setValue(escenaActual.getFactor());
		scrlFactor.addAdjustmentListener(this);
		
		//System.out.println(escenaActual.getFactor());
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getSource().equals(lstEscenas) && !arg0.getValueIsAdjusting()){
			seleccionaEscena(lstEscenas.getSelectedValue());
		}
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		if (arg0.getValueIsAdjusting())
			return;
		ajusta();	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnNormal))
			ajusta();
		
		if(e.getSource().equals(btnVDJ)){
			Escena es = lstEscenas.getSelectedValue();
			if (es!=null){
				es.setListenerAvance(this);
				if (btnVDJ.isSelected())
					FactoryVDJ.agregaListener(es);
				else
					FactoryVDJ.eliminaListener(es);
			}
		}
		
		if (e.getSource().equals(btnEditarPasoActual)){
			Escena es = lstEscenas.getSelectedValue();
			if (es!=null){
				int paso = tblPasos.getSelectionModel().getMinSelectionIndex();
				if (paso>=0){
					int dialogResult = JOptionPane.showConfirmDialog(null, "¿Estás seguro de editar este paso?", "Advertencia", JOptionPane.YES_NO_OPTION);

		            if(dialogResult == JOptionPane.YES_OPTION){
		            	es.capturaValoresActuales(paso);
		            	seleccionaEscena(es);
		            }
		            
				}
			}
		}
		
		if (e.getSource().equals(btnGuardarCambios)){
			Escena es = lstEscenas.getSelectedValue();
			if (es!=null)
				EscenasCfg.grabaCambios(es);
		}
			
		if (e.getSource().equals(btnAgregarPaso)){
			Escena es = lstEscenas.getSelectedValue();
			if (es!=null){
				es.agregaPaso();
				seleccionaEscena(es);
			}
		}

	}
	
	private void ajusta(){
		Escena es = lstEscenas.getSelectedValue();
		if (es!=null){
			es.setFactor(scrlFactor.getValue());
			es.setVelocidad(Integer.parseInt(txtVelocidad.getText()));
			es.setListenerAvance(this);
			FactoryEjecutorEscenas.getEjecutor().ejecuta(btnNormal.isSelected(), es);
		}
	}

	@Override
	public void avanzaPaso(int cual) {
		tblPasos.getSelectionModel().setSelectionInterval(cual, cual);
	}
}
