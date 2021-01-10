package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMenuGenerico;

import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.ArchivoEncendidoYaml;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.Escena;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.FixtureToDmx;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.NodoEscena;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.PanelCanalesEncendidoMovingHead;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.NodoEscena.FixMovingHead;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

public class PanelEncendidoRoboticas extends PanelMenuGenerico implements ActionListener, InformaCambiosUsuario, ChangeListener, Actualizable, ListSelectionListener {

	private static final long serialVersionUID = -5869553409971473557L;
	
	private PanelCanvas pnlCanvas;
	private PanelCanvasLimites pnlCanvasLimites90;
	private PanelCanvasLimites pnlCanvasLimites60;
	private JPanel pnl1;
	
	private List<Escena> listaEscenas = new ArrayList<Escena>();
	private Escena escenaActual;
	private JTextField txtPan;
	private JTextField txtTilt;

	
	private JTextField txtMinPan;
	private JTextField txtMaxPan;
	private JTextField txtMinTilt;
	private JTextField txtMaxTilt;
	private JTextField txtX;
	private JTextField txtY;
	
	private final Action action = new SwingAction();

	
	private PanelCanalesEncendidoMovingHead pnlMv90_1;
	private PanelCanalesEncendidoMovingHead pnlMv90_2;
	private PanelCanalesEncendidoMovingHead pnlMv60_1;
	private PanelCanalesEncendidoMovingHead pnlMv60_2;
	
	private List<PanelCanalesEncendidoMovingHead> listaPaneles;
	private JList<Escena> lstEscenas;

  	private FixtureRobotica entidad90;
  	private FixtureRobotica entidad60;
  	
	public PanelEncendidoRoboticas() {
	    this.configura(1400, 1080, "Mantiene Encendido Robóticas");
	  	this.setLayout(null);
	  	
	  	
	  	FixtureRobotica entidad90 = new FixtureRobotica("mv90") {
		};

		entidad90.setVentanaMinX(0);
		entidad90.setVentanaMaxX(23000);
		entidad90.setVentanaMinY(0);
		entidad90.setVentanaMaxY(31000);
		
		FixtureRobotica entidad60=new FixtureRobotica("mv60") {
		};
		
		this.entidad60 = entidad60;
		this.entidad90 = entidad90;

		entidad60.setVentanaMinX(12000);
		entidad60.setVentanaMaxX(33000);
		entidad60.setVentanaMinY(0);
		entidad60.setVentanaMaxY(35000);
		
		pnl1 = new JPanel();
		pnl1.setLayout(null);
		pnl1.setBounds(0, 4, 1268, 1080);
		add(pnl1);
		
		txtMinPan = new JTextField();
		txtMinPan.setBounds(826, 525, 46, 20);
	//	pnl1.add(txtMinPan);
		
		txtMaxTilt = new JTextField();
		txtMaxTilt.setBounds(994, 525, 46, 20);
	//	pnl1.add(txtMaxTilt);

		txtMinTilt = new JTextField();
		txtMinTilt.setBounds(938, 525, 46, 20);
	//	pnl1.add(txtMinTilt);
		
		txtMaxPan = new JTextField();
		txtMaxPan.setBounds(882, 525, 46, 20);
	//	pnl1.add(txtMaxPan);
		
		txtPan = new JTextField();
		txtPan.setBounds(814, 27, 61, 20);
		pnl1.add(txtPan);
		
		txtTilt = new JTextField();
		txtTilt.setBounds(885, 27, 61, 20);
		pnl1.add(txtTilt);
		
		pnlCanvas = new PanelCanvas();
		pnlCanvas.setBackground(Color.BLACK);
		pnlCanvas.setBounds(809, 146, 205, 88);
		pnlCanvas.setCambios(this);
		
		pnl1.add(pnlCanvas);
		
		pnlCanvasLimites90 = new PanelCanvasLimites();
		pnlCanvasLimites90.setBackground(Color.BLACK);
		pnlCanvasLimites90.setBounds(814, 258, 200, 200);
		pnlCanvasLimites90.setEntidad(entidad90);
		pnlCanvasLimites90.setRedimensiono(this);
		pnl1.add(pnlCanvasLimites90);
		
		pnlCanvasLimites60 = new PanelCanvasLimites();
		pnlCanvasLimites60.setBackground(Color.BLACK);
		pnlCanvasLimites60.setBounds(1214, 640, 200, 200);
		pnlCanvasLimites60.setEntidad(entidad60);
		pnlCanvasLimites60.setRedimensiono(this);
		pnl1.add(pnlCanvasLimites60);
		
		JLabel lblPan = new JLabel("Pan");
		lblPan.setBounds(814, 11, 46, 14);
		pnl1.add(lblPan);
		
		JLabel lblTilt = new JLabel("Tilt");
		lblTilt.setBounds(885, 11, 46, 14);
		pnl1.add(lblTilt);
					
		txtX = new JTextField();
		txtX.setBounds(958, 27, 46, 20);
		pnl1.add(txtX);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(958, 11, 46, 14);
		pnl1.add(lblX);
		
		txtY = new JTextField();
		txtY.setBounds(1014, 27, 46, 20);
		pnl1.add(txtY);
		
		JLabel lblY = new JLabel("Y");
		lblY.setBounds(1014, 11, 46, 14);
		pnl1.add(lblY);
		
		pnlMv90_1 = new PanelCanalesEncendidoMovingHead();
		pnlMv90_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlMv90_1.setBounds(23, 132, 402, 102);
		pnl1.add(pnlMv90_1);
				
		pnlMv60_2 = new PanelCanalesEncendidoMovingHead();
		pnlMv60_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlMv60_2.setBounds(23, 243, 402, 102);
		pnl1.add(pnlMv60_2);
		
		pnlMv60_1 = new PanelCanalesEncendidoMovingHead();
		pnlMv60_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlMv60_1.setBounds(23, 356, 402, 102);
		pnl1.add(pnlMv60_1);
		
		pnlMv90_2 = new PanelCanalesEncendidoMovingHead();
		pnlMv90_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlMv90_2.setBounds(23, 469, 402, 102);
		pnl1.add(pnlMv90_2);
		
		listaPaneles = new ArrayList<>();
		listaPaneles.add(pnlMv60_1);
		listaPaneles.add(pnlMv60_2);
		listaPaneles.add(pnlMv90_1);
		listaPaneles.add(pnlMv90_2);
				
		try {
			
			listaEscenas = new ArchivoEncendidoYaml().leeListaPosicionesEscenas();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 9, 137, 95);
		pnl1.add(scrollPane);
		
		lstEscenas = new JList<Escena>();
		scrollPane.setViewportView(lstEscenas);
		
		JButton btnGrabar = new JButton("Grabar");
		btnGrabar.setBounds(187, 26, 102, 23);
		btnGrabar.setAction(action);
		pnl1.add(btnGrabar);

		lstEscenas.setListData(obtieneLista());
		lstEscenas.addListSelectionListener(this);

		cambioVentana(entidad90);
		
		actualizaPosicionTexto();
		for (PanelCanalesEncendidoMovingHead pnl : listaPaneles)
			pnl.setActualizable(this);

	}
	
	public Escena[] obtieneLista(){
		Escena[] s = new Escena[listaEscenas.size()];
		
		int i=0;
		for (Escena ss: listaEscenas)
			s[i++] = ss;
		return s;
	}

	private void actualizaPosicionTexto(){
		for (PanelCanalesEncendidoMovingHead pnl : listaPaneles){
			pnl.actualiza();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void cambioVentana(FixtureRobotica entidad) {
		txtMaxPan.setText((int)(entidad.getVentanaMaxX())+"");
		txtMinPan.setText((int)(entidad.getVentanaMinX())+"");
		txtMaxTilt.setText((int)(entidad.getVentanaMaxY())+"");
		txtMinTilt.setText((int)(entidad.getVentanaMinY())+"");
	}

	@Override
	public void cambioPosicionCursor(int x, int y, double pan, double tilt, NodoEscena nodoActual) {
		txtX.setText(String.valueOf(x));
		txtY.setText(String.valueOf(y));
						
		txtPan.setText(String.valueOf(pan));
		txtTilt.setText(String.valueOf(tilt));	

		if (nodoActual!=null){
		//	this.nodoActual = nodoActual;
			
			if (nodoActual.getX()!=x || nodoActual.getY()!=y){
				nodoActual.setX(x);
				nodoActual.setY(y);
				
				int pan1 = (int)(pan / 256);
				int pan2 = (int)(pan % 256);

				int tilt1 = (int)(tilt/ 256);
				int tilt2 = (int)(tilt % 256);
				
				nodoActual.setPan(pan);
				nodoActual.setTilt(tilt);
				
				nodoActual.setPan1(pan1);
				nodoActual.setPan2(pan2);
				
				nodoActual.setTilt1(tilt1);
				nodoActual.setTilt2(tilt2);
			}
			
		}
		
		actualizaPosicionTexto();
		FixtureToDmx.actualizaNodoToDmx(escenaActual);
	}
	


	@Override
	public void stateChanged(ChangeEvent e) {
	/*	if (nodoActual==null)
			return;

		pnlCanvas.repaint();
		
		FixtureToDmx.actualizaNodoToDmx(nodoActual);
		
		actualizaPosicionTexto();*/
	}
	
	private void refrescaDmx(){
		for (Escena escena: listaEscenas)
			for (NodoEscena nodo: escena.getListaNodos())
				FixtureToDmx.actualizaPosicionNodoToDmx(nodo);
		
	}

	@Override
	public void cambioPosicionCursor(int x, int y, double pan, double tilt) {
		
		txtX.setText(String.valueOf(x));
		txtY.setText(String.valueOf(y));
						
		txtPan.setText(String.valueOf(pan));
		txtTilt.setText(String.valueOf(tilt));	
	}
	
	
	private class SwingAction extends AbstractAction {

		private static final long serialVersionUID = -782782725977607215L;
		public SwingAction() {
			putValue(NAME, "Graba Escena");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				new ArchivoEncendidoYaml().grabaListaEncendidoEscenas(listaEscenas);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}


	@Override
	public void actualizaValores() {
		pnlCanvas.repaint();
		
	//	FixtureToDmx.actualizaNodoToDmx(escenaActual);
		
		actualizaPosicionTexto();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e!=null && e.getValueIsAdjusting())
			return;

		Escena sel = lstEscenas.getSelectedValue();
		
		if (sel==null){
			return;
		}
		
		escenaActual = sel;
	
		int n90 = 0;
		int n60 = 0;
		for (NodoEscena nodo: escenaActual.getListaNodos()){
			if (nodo.getMovingHead()==FixMovingHead.mh_90_1){
				nodo.setEntidad(entidad90);
				pnlMv90_1.setNodoEscena(nodo);
			}
			if (nodo.getMovingHead()==FixMovingHead.mh_90_2){
				nodo.setEntidad(entidad90);
				pnlMv90_2.setNodoEscena(nodo);
			}				
			if (nodo.getMovingHead()==FixMovingHead.mh_60_1){
				nodo.setEntidad(entidad60);
				pnlMv60_1.setNodoEscena(nodo);
			}
			if (nodo.getMovingHead()==FixMovingHead.mh_60_2){
				nodo.setEntidad(entidad60);
				pnlMv60_2.setNodoEscena(nodo);
				
			}			
		}
		
		pnlCanvas.setEscena(escenaActual);
		actualizaValores();
		FixtureToDmx.actualizaEncendidoNodoToDmx(escenaActual);
	}
}
