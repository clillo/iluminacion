package com.clillo.dmx.ui.jpanels.opcionesMenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.clillo.dmx.configuracion.escenas.PuntosRoboticasCfg;
import com.clillo.dmx.core.ListaFixtures;
import com.clillo.dmx.core.fixtures.FixtureMovingHead;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.ListenerCambioPosicion;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.ListenerMovimientos;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.PanelCanvas;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.PanelControlPosicion;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.Punto;
import com.clillo.dmx.ui.jpanels.opcionesMenu.escenas.PuntoRoboticas;

import javax.swing.JSpinner;
import javax.swing.JScrollBar;

public class PanelMantienePuntosMovingHeads extends PanelMenuGenerico implements ActionListener, ListenerCambioPosicion, ListSelectionListener, ListenerMovimientos, AdjustmentListener{

	private static final long serialVersionUID = -5869553409971473557L;

	private PanelCanvas pnlCanvas;

	private JButton btnEditar;
	
	private PanelControlPosicion pnlControlPosicion;
	private PanelControlPosicion pnlControlPosicionFine;
	private PanelControlPosicion pnlTraslada; 
	private PanelControlPosicion pnlEscala;
	
	private JList<PuntoRoboticas> lstPuntos;
	private DefaultListModel<PuntoRoboticas> modeloLista;

	private JScrollBar scrlX;
	private JScrollBar scrlY;
	
	private FixtureMovingHead entidad;
	private JTextField txtPosicion;
	private JTextField txtFine;
	private JPanel pnl1;
	
	private String id;
	
	public PanelMantienePuntosMovingHeads() {
	    this.configura(530, 490, "Mantiene Puntos Moving Heads");
	  	this.setLayout(null);
	  	
		pnl1 = new JPanel();
		pnl1.setLayout(null);
		pnl1.setBounds(4, 4, 536, 484);
		add(pnl1);
		
		txtPosicion = new JTextField();
		txtPosicion.setBounds(436, 24, 39, 20);
		pnl1.add(txtPosicion);

		txtFine = new JTextField();
		txtFine.setBounds(475, 24, 39, 20);
		pnl1.add(txtFine);

		pnlCanvas = new PanelCanvas();
		pnlCanvas.setBackground(Color.BLACK);
		pnlCanvas.setBounds(34, 22, 400, 400);
		pnlCanvas.setTxtPosicion(txtPosicion);
		pnlCanvas.setTxtFine(txtFine);
		pnl1.add(pnlCanvas);
		
		pnlControlPosicion = new PanelControlPosicion();
		pnlControlPosicion.setBounds(451, 55, 60, 54);
		pnl1.add(pnlControlPosicion);
		
		pnlControlPosicionFine = new PanelControlPosicion();
		pnlControlPosicionFine.setBounds(451, 120, 60, 54);
		pnl1.add(pnlControlPosicionFine);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(448, 185, 63, 75);
		pnl1.add(scrollPane);
		
		modeloLista = new DefaultListModel<PuntoRoboticas>();

		lstPuntos = new JList<PuntoRoboticas>(modeloLista);
		lstPuntos.addListSelectionListener(this);
		scrollPane.setViewportView(lstPuntos);
				
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(446, 271, 68, 23);
		btnEditar.addActionListener(this);
		pnl1.add(btnEditar);
		
		pnlTraslada = new PanelControlPosicion();
		pnlTraslada.setBounds(454, 305, 60, 54);
		pnl1.add(pnlTraslada);
		
		pnlEscala = new PanelControlPosicion();
		pnlEscala.setBounds(438, 370, 60, 54);
		pnl1.add(pnlEscala);
		
		scrlX = new JScrollBar();
		scrlX.setMaximum(255);
		scrlX.setOrientation(JScrollBar.HORIZONTAL);
		scrlX.setBounds(10, 433, 490, 20);
		scrlX.addAdjustmentListener(this);
		pnl1.add(scrlX);
		
		scrlY = new JScrollBar();
		scrlY.setMaximum(255);
		scrlY.setBounds(1, 25, 23, 397);
		scrlY.addAdjustmentListener(this);
		pnl1.add(scrlY);
	}
	
	private void actualizaPuntos(){
		lstPuntos.removeListSelectionListener(this);
		modeloLista.removeAllElements();
		lstPuntos.addListSelectionListener(this);

		ArrayList<Punto> listaPuntos = new ArrayList<Punto>();
		for (PuntoRoboticas pr: PuntosRoboticasCfg.getListaPuntos()){
			modeloLista.addElement(pr);
			Punto p = new Punto();
			p.setNombre(pr.getId());
			if ("movingHead1".equals(this.id)){
				p.setX(pr.getMovingHead1Pan());
				p.setY(pr.getMovingHead1Tilt());
			}else{
				p.setX(pr.getMovingHead2Pan());
				p.setY(pr.getMovingHead2Tilt());				
			}
			//System.out.println(System.identityHashCode(pr)+"\t"+ pr.getId() +"\t"+pr.getMovingHead1Pan()+"\t"+pr.getMovingHead1Tilt());
			listaPuntos.add(p);
		}
		
		pnlCanvas.setListaPuntos(listaPuntos);
	}

	public void setId(String id){
		entidad = (FixtureMovingHead)ListaFixtures.getFixture(id);
		pnlCanvas.setEntidad(entidad);
		entidad.setListenerCambioPosicion(this);
		pnlControlPosicion.setEntidad(entidad);
		pnlControlPosicionFine.setEntidad(entidad);
		pnlControlPosicionFine.setEsFine(true);
		
		pnlTraslada.setListener(this);
		pnlTraslada.setTipo(TipoMovimiento.TRASLADA);
		pnlEscala.setListener(this);
		pnlEscala.setTipo(TipoMovimiento.ESCALA);
		
		if (id.contains("1"))
			pnl1.setBorder(new TitledBorder(null, "Moving Head 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		else
			pnl1.setBorder(new TitledBorder(null, "Moving Head 2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		this.id = id;
		actualizaPuntos();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnEditar)){
			if (lstPuntos.getSelectedValue()!=null){
				PuntoRoboticas pr = lstPuntos.getSelectedValue();
				if ("movingHead1".equals(this.id)){
					pr.setMovingHead1Pan(entidad.getPosX());
					pr.setMovingHead1Tilt(entidad.getPosY());
					pr.setMovingHead1PanFine(entidad.getPanFine());
					pr.setMovingHead1TiltFine(entidad.getTiltFine());
				}else{
					pr.setMovingHead2Pan(entidad.getPosX());
					pr.setMovingHead2Tilt(entidad.getPosY());
					pr.setMovingHead2PanFine(entidad.getPanFine());
					pr.setMovingHead2TiltFine(entidad.getTiltFine());				
				}
				//System.out.println(System.identityHashCode(pr)+"\t"+ pr.getId() +"\t"+pr.getMovingHead1Pan()+"\t"+pr.getMovingHead1Tilt());
				actualizaPuntos();
				pnlCanvas.repaint();
				PuntosRoboticasCfg.grabar();
			}
		}
	}

	@Override
	public void moverHasta(int x, int y, boolean fine) {
		if (fine){
			scrlX.setValue(x);
			scrlY.setValue(y);
		}
		pnlCanvas.repaint();
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getSource().equals(lstPuntos) && !arg0.getValueIsAdjusting()){
			if (lstPuntos.getSelectedValue()!=null)
				lstPuntos.getSelectedValue().mueveA();
			
			pnlCanvas.repaint();
		}
	}

	@Override
	public void mueveArriba(TipoMovimiento tipo) {
		for (PuntoRoboticas pr: PuntosRoboticasCfg.getListaPuntos()){		
			if ("movingHead1".equals(this.id)){
				if (tipo == TipoMovimiento.TRASLADA)
					pr.setMovingHead1Tilt(pr.getMovingHead1Tilt()-1);
				else
					pr.setMovingHead1Tilt((int)(pr.getMovingHead1Tilt()/1.025));
			}else{
				if (tipo == TipoMovimiento.TRASLADA)
					pr.setMovingHead2Tilt(pr.getMovingHead2Tilt()-1);
				else
					pr.setMovingHead2Tilt((int)(pr.getMovingHead2Tilt()/1.025));
		}
		}
		actualizaPuntos();
		pnlCanvas.repaint();
		PuntosRoboticasCfg.grabar();	
	}

	@Override
	public void mueveAbajo(TipoMovimiento tipo) {
		for (PuntoRoboticas pr: PuntosRoboticasCfg.getListaPuntos()){		
			if ("movingHead1".equals(this.id)){
				if (tipo == TipoMovimiento.TRASLADA)
					pr.setMovingHead1Tilt(pr.getMovingHead1Tilt()+1);
				else
					pr.setMovingHead1Tilt((int)(pr.getMovingHead1Tilt()*1.025));
			}else{
				if (tipo == TipoMovimiento.TRASLADA)
					pr.setMovingHead2Tilt(pr.getMovingHead2Tilt()+1);
				else
					pr.setMovingHead2Tilt((int)(pr.getMovingHead2Tilt()*1.025));
			}
		}
		actualizaPuntos();
		pnlCanvas.repaint();
		PuntosRoboticasCfg.grabar();			
	}

	@Override
	public void mueveIzquerda(TipoMovimiento tipo) {
		for (PuntoRoboticas pr: PuntosRoboticasCfg.getListaPuntos()){		
			if ("movingHead1".equals(this.id)){
				if (tipo == TipoMovimiento.TRASLADA)
					pr.setMovingHead1Pan(pr.getMovingHead1Pan()-1);
				else
					pr.setMovingHead1Pan((int)(pr.getMovingHead1Pan()/1.025));
			}else{
				if (tipo == TipoMovimiento.TRASLADA)
					pr.setMovingHead2Pan(pr.getMovingHead2Pan()-1);
				else
					pr.setMovingHead2Pan((int)(pr.getMovingHead2Pan()/1.025));
			}
		}
		actualizaPuntos();
		pnlCanvas.repaint();
		PuntosRoboticasCfg.grabar();		
	}

	@Override
	public void mueveDerecha(TipoMovimiento tipo) {
		for (PuntoRoboticas pr: PuntosRoboticasCfg.getListaPuntos()){		
			if ("movingHead1".equals(this.id)){
				if (tipo == TipoMovimiento.TRASLADA)
					pr.setMovingHead1Pan(pr.getMovingHead1Pan()+1);
				else
					pr.setMovingHead1Pan((int)(pr.getMovingHead1Pan()*1.025));
			}else{
				if (tipo == TipoMovimiento.TRASLADA)
					pr.setMovingHead2Pan(pr.getMovingHead2Pan()+1);
				else
					pr.setMovingHead2Pan((int)(pr.getMovingHead2Pan()*1.025));
			}
		}
		actualizaPuntos();
		pnlCanvas.repaint();
		PuntosRoboticasCfg.grabar();			
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		entidad.setPanFine(scrlX.getValue());
		entidad.setTiltFine(scrlY.getValue());
		
		entidad.saltarAFine(scrlX.getValue(), scrlY.getValue());
		pnlCanvas.repaint();
	}
}
