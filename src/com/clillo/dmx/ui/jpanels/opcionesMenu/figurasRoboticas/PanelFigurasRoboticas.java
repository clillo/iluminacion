package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.Punto;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMenuGenerico;
import javax.swing.JLabel;
import javax.swing.JSpinner;

public class PanelFigurasRoboticas extends PanelMenuGenerico implements ActionListener, InformaCambiosUsuario, ChangeListener {

	private static final long serialVersionUID = -5869553409971473557L;

	private FixtureRobotica entidad;
	
	private PanelCanvas pnlCanvas;
	private PanelCanvasLimites pnlCanvasLimites;
	private JButton btnEditar;
	private JTextField txtPunto;
	private JPanel pnl1;
	
	private ArrayList<Punto> listaPuntos = new ArrayList<Punto>();
	private JTextField txtPan;
	private JTextField txtTilt;
	private JTextField txtPanActual;
	private JTextField txtTiltActual;
	private JLabel label;
	private JLabel label_1;
	
	private JSpinner spiPan1;
	private JSpinner spiPan2;
	private JSpinner spiTilt1;
	private JSpinner spiTilt2;

	private JTextField txtMinPan;
	private JTextField txtMaxPan;
	private JTextField txtMinTilt;
	private JTextField txtMaxTilt;
	private JTextField txtX;
	private JTextField txtY;
	
	private Punto puntoActual;
	
	public PanelFigurasRoboticas() {
	    this.configura(1100, 800, "Mantiene Figuras Robóticas");
	  	this.setLayout(null);
	  	
	  	this.entidad=new FixtureRobotica("a") {
		};
	  	
		pnl1 = new JPanel();
		pnl1.setLayout(null);
		pnl1.setBounds(4, 4, 1068, 810);
		add(pnl1);
		
		spiPan1 = new JSpinner();
		spiPan1.setBounds(894, 206, 46, 20);
		spiPan1.addChangeListener(this);
		pnl1.add(spiPan1);
		
		spiPan2 = new JSpinner();
		spiPan2.setBounds(950, 206, 46, 20);
		spiPan2.addChangeListener(this);
		pnl1.add(spiPan2);
		
		txtMinPan = new JTextField();
		txtMinPan.setBounds(814, 374, 46, 20);
		pnl1.add(txtMinPan);
		
		txtMaxTilt = new JTextField();
		txtMaxTilt.setBounds(982, 374, 46, 20);
		pnl1.add(txtMaxTilt);

		txtMinTilt = new JTextField();
		txtMinTilt.setBounds(926, 374, 46, 20);
		pnl1.add(txtMinTilt);
		
		txtMaxPan = new JTextField();
		txtMaxPan.setBounds(870, 374, 46, 20);
		pnl1.add(txtMaxPan);
		
		txtPan = new JTextField();
		txtPan.setBounds(814, 27, 61, 20);
		pnl1.add(txtPan);
		
		txtTilt = new JTextField();
		txtTilt.setBounds(885, 27, 61, 20);
		pnl1.add(txtTilt);
		
		txtPunto = new JTextField();
		txtPunto.setBounds(814, 146, 46, 20);
		pnl1.add(txtPunto);

		txtPanActual = new JTextField();
		txtPanActual.setBounds(814, 206, 68, 20);
		pnl1.add(txtPanActual);
		
		txtTiltActual = new JTextField();
		txtTiltActual.setBounds(814, 253, 68, 20);
		pnl1.add(txtTiltActual);	
		
		pnlCanvas = new PanelCanvas();
		pnlCanvas.setBackground(Color.BLACK);
		pnlCanvas.setBounds(4, 4, 800, 800);
		pnlCanvas.setListaPuntos(listaPuntos);
		pnlCanvas.setCambios(this);
		pnlCanvas.setEntidad(entidad);
		pnl1.add(pnlCanvas);
		
		pnlCanvasLimites = new PanelCanvasLimites();
		pnlCanvasLimites.setBackground(Color.BLACK);
		pnlCanvasLimites.setBounds(814, 404, 200, 200);
		pnlCanvasLimites.setEntidad(entidad);
		pnlCanvasLimites.setRedimensiono(this);
		pnl1.add(pnlCanvasLimites);
				
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(648, 402, 68, 23);
		btnEditar.addActionListener(this);
		pnl1.add(btnEditar);
		
		JLabel lblPan = new JLabel("Pan");
		lblPan.setBounds(814, 11, 46, 14);
		pnl1.add(lblPan);
		
		JLabel lblTilt = new JLabel("Tilt");
		lblTilt.setBounds(885, 11, 46, 14);
		pnl1.add(lblTilt);
			
		label = new JLabel("Pan");
		label.setBounds(814, 190, 46, 14);
		pnl1.add(label);
		
		label_1 = new JLabel("Tilt");
		label_1.setBounds(814, 237, 46, 14);
		pnl1.add(label_1);
		
		txtX = new JTextField();
		txtX.setBounds(814, 74, 46, 20);
		pnl1.add(txtX);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(814, 58, 46, 14);
		pnl1.add(lblX);
		
		txtY = new JTextField();
		txtY.setBounds(870, 74, 46, 20);
		pnl1.add(txtY);
		
		JLabel lblY = new JLabel("Y");
		lblY.setBounds(870, 58, 46, 14);
		pnl1.add(lblY);
		
		spiTilt1 = new JSpinner();
		spiTilt1.setBounds(894, 253, 46, 20);
		spiTilt1.addChangeListener(this);
		pnl1.add(spiTilt1);
		
		spiTilt2 = new JSpinner();
		spiTilt2.setBounds(950, 253, 46, 20);
		spiTilt2.addChangeListener(this);
		pnl1.add(spiTilt2);
				
		Punto p = new Punto();
		p.setNombre("1");
		p.setX(84); p.setY(39);
		p.setPan(13604.0);
		p.setTilt(18634.0);

		p.setPan1(53);
		p.setPan2(36);

		p.setTilt1(72);
		p.setTilt2(202);
		
		listaPuntos.add(p);

		p = new Punto();
		p.setNombre("2");
		p.setX(257); p.setY(51);
		p.setPan(20004.0);
		p.setTilt(19146.0);

		p.setPan1(78);
		p.setPan2(36);

		p.setTilt1(74);
		p.setTilt2(202);

		listaPuntos.add(p);
		cambioVentana();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnEditar)){
		}
	}

	@Override
	public void cambioVentana() {
		txtMaxPan.setText((int)(entidad.getVentanaMaxX())+"");
		txtMinPan.setText((int)(entidad.getVentanaMinX())+"");
		txtMaxTilt.setText((int)(entidad.getVentanaMaxY())+"");
		txtMinTilt.setText((int)(entidad.getVentanaMinY())+"");
	}

	@Override
	public void cambioPosicionCursor(int x, int y, double pan, double tilt, Punto puntoActual) {
		txtX.setText(String.valueOf(x));
		txtY.setText(String.valueOf(y));
						
		txtPan.setText(String.valueOf(pan));
		txtTilt.setText(String.valueOf(tilt));	

		if (puntoActual!=null){
			this.puntoActual = puntoActual;
			
			if (puntoActual.getX()!=x || puntoActual.getY()!=y){
				puntoActual.setX(x);
				puntoActual.setY(y);
				
				int pan1 = (int)(pan / 256);
				int pan2 = (int)(pan % 256);

				int tilt1 = (int)(tilt/ 256);
				int tilt2 = (int)(tilt % 256);
				
				puntoActual.setPan(pan);
				puntoActual.setTilt(tilt);
				
				puntoActual.setPan1(pan1);
				puntoActual.setPan2(pan2);
				
				puntoActual.setTilt1(tilt1);
				puntoActual.setTilt2(tilt2);
			}
			txtPanActual.setText(String.valueOf(puntoActual.getPan()));
			txtTiltActual.setText(String.valueOf(puntoActual.getTilt()));	

			spiPan1.setValue(puntoActual.getPan1());
			spiPan2.setValue(puntoActual.getPan2());

			spiTilt1.setValue(puntoActual.getTilt1());
			spiTilt2.setValue(puntoActual.getTilt2());

			txtPunto.setText(puntoActual.getNombre());
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (puntoActual==null)
			return;
		
		if (e.getSource().equals(spiPan1)){
			puntoActual.setPan1((Integer)spiPan1.getValue());
			puntoActual.setPan(puntoActual.getPan1()*256 + puntoActual.getPan2());
			txtPanActual.setText(String.valueOf(puntoActual.getPan()));

			double ancho = entidad.getVentanaMaxX()-entidad.getVentanaMinX();			
			int x = (int) ((puntoActual.getPan() - entidad.getVentanaMinX())*800.0/ancho);
			
			puntoActual.setX(x);
		}

		if (e.getSource().equals(spiPan2)){
			puntoActual.setPan2((Integer)spiPan2.getValue());
			puntoActual.setPan(puntoActual.getPan1()*256 + puntoActual.getPan2());
			txtPanActual.setText(String.valueOf(puntoActual.getPan()));
			
			double ancho = entidad.getVentanaMaxX()-entidad.getVentanaMinX();
			int x = (int) ((puntoActual.getPan() - entidad.getVentanaMinX())*800.0/ancho);

			puntoActual.setX(x);
		}

		if (e.getSource().equals(spiTilt1)){
			puntoActual.setTilt1((Integer)spiTilt1.getValue());
			puntoActual.setTilt(puntoActual.getTilt1()*256 + puntoActual.getTilt2());
			txtTiltActual.setText(String.valueOf(puntoActual.getTilt()));	
			
			double alto = entidad.getVentanaMaxY()-entidad.getVentanaMinY();
			
			int y = (int) ((puntoActual.getTilt() - entidad.getVentanaMinY())*800.0/alto);
			puntoActual.setY(y);
		}

		if (e.getSource().equals(spiTilt2)){
			puntoActual.setTilt2((Integer)spiTilt2.getValue());
			puntoActual.setTilt(puntoActual.getTilt1()*256 + puntoActual.getTilt2());
			txtTiltActual.setText(String.valueOf(puntoActual.getTilt()));	

			double alto = entidad.getVentanaMaxY()-entidad.getVentanaMinY();
			int y = (int) ((puntoActual.getTilt() - entidad.getVentanaMinY())*800.0/alto);
			puntoActual.setY(y);
		}
		
		pnlCanvas.repaint();
		
		Dmx.enviar(260, puntoActual.getPan1());
		Dmx.enviar(273, puntoActual.getPan2());
		Dmx.enviar(261, puntoActual.getTilt1());
		Dmx.enviar(274, puntoActual.getTilt2());

	}

	@Override
	public void cambioPosicionCursor(int x, int y, double pan, double tilt) {
		txtX.setText(String.valueOf(x));
		txtY.setText(String.valueOf(y));
						
		txtPan.setText(String.valueOf(pan));
		txtTilt.setText(String.valueOf(tilt));	
	}
}
