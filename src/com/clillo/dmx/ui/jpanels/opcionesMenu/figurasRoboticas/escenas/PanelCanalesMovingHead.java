package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.Actualizable;

public class PanelCanalesMovingHead extends JPanel implements ChangeListener {

	private static final long serialVersionUID = -5869553409971473557L;

	private JTextField txtMvhd;
	
	private JTextField txtPan;
	private JTextField txtTilt;
	private JSpinner spiPan1;
	private JSpinner spiPan2;
	private JSpinner spiTilt1;
	private JSpinner spiTilt2;

	private JLabel label;
	private JLabel label_1;
	
	private NodoEscena nodoEscena;
	private Actualizable actualizable;
	
	public PanelCanalesMovingHead() {
		
		this.setLayout(null);
		this.setBounds(0, 4, 1423, 1080);
		
		txtPan = new JTextField();
		txtPan.setBounds(81, 27, 40, 20);
		this.add(txtPan);
		
		spiPan1 = new JSpinner();
		spiPan1.setBounds(126, 27, 40, 20);
		this.add(spiPan1);
		
		spiPan2 = new JSpinner();
		spiPan2.setBounds(169, 27, 40, 20);
		spiPan2.addChangeListener(this);
		this.add(spiPan2);

		txtTilt = new JTextField();
		txtTilt.setBounds(219, 27, 40, 20);
		this.add(txtTilt);	

		spiTilt1 = new JSpinner();
		spiTilt1.setBounds(264, 27, 40, 20);

		this.add(spiTilt1);
		
		spiTilt2 = new JSpinner();
		spiTilt2.setBounds(307, 27, 40, 20);
		this.add(spiTilt2);
		
		label = new JLabel("Pan");
		label.setBounds(81, 11, 46, 14);
		this.add(label);
		
		label_1 = new JLabel("Tilt");
		label_1.setBounds(219, 11, 46, 14);
		this.add(label_1);
		
		txtMvhd = new JTextField();
		txtMvhd.setBounds(10, 27, 60, 20);
		this.add(txtMvhd);
		
		JLabel lblMovingHead = new JLabel("Moving Head");
		lblMovingHead.setBounds(10, 11, 68, 14);
		add(lblMovingHead);
				
	}

	public NodoEscena getNodoEscena() {
		return nodoEscena;
	}

	public void setNodoEscena(NodoEscena nodoEscena) {
		this.nodoEscena = nodoEscena;
	}
	
	public void actualiza(){
		if (nodoEscena==null)
			return;

		actualizaPan(nodoEscena);
		actualizaTilt(nodoEscena);
		
		txtMvhd.setText(nodoEscena.getMovingHead().toString());
		txtPan.setText(String.valueOf(Math.round(nodoEscena.getPan())));
		txtTilt.setText(String.valueOf(Math.round(nodoEscena.getTilt())));

		spiPan1.removeChangeListener(this);
		spiPan1.setValue(nodoEscena.getPan1());
		spiPan1.addChangeListener(this);
		
		spiPan2.removeChangeListener(this);
		spiPan2.setValue(nodoEscena.getPan2());
		spiPan2.addChangeListener(this);
		
		spiTilt1.removeChangeListener(this);
		spiTilt1.setValue(nodoEscena.getTilt1());
		spiTilt1.addChangeListener(this);
		
		spiTilt2.removeChangeListener(this);
		spiTilt2.setValue(nodoEscena.getTilt2());
		spiTilt2.addChangeListener(this);

	}
	
	private void actualizaPan(final NodoEscena nodo){
		nodo.setPan(nodo.getPan1()*256 + nodo.getPan2());

		double ancho = nodo.getEntidad().getVentanaMaxX()-nodo.getEntidad().getVentanaMinX();			
		int x = (int) ((nodo.getPan() - nodo.getEntidad().getVentanaMinX())*800.0/ancho);
		
		nodo.setX(x);
	}
	
	private void actualizaTilt(final NodoEscena nodo){
		nodo.setTilt(nodo.getTilt1()*256 + nodo.getTilt2());
		
		double alto = nodo.getEntidad().getVentanaMaxY()-nodo.getEntidad().getVentanaMinY();
		
		int y = (int) ((nodo.getTilt() - nodo.getEntidad().getVentanaMinY())*800.0/alto);
		nodo.setY(y);
	}

	@Override
	public void stateChanged(ChangeEvent e) {

		if (e.getSource().equals(spiPan1)){
			nodoEscena.setPan1((Integer)spiPan1.getValue());
			actualizaPan(nodoEscena);
		}

		if (e.getSource().equals(spiPan2)){
			nodoEscena.setPan2((Integer)spiPan2.getValue());
			actualizaPan(nodoEscena);
		}

		if (e.getSource().equals(spiTilt1)){
			nodoEscena.setTilt1((Integer)spiTilt1.getValue());

		}

		if (e.getSource().equals(spiTilt2)){
			nodoEscena.setTilt2((Integer)spiTilt2.getValue());
			actualizaTilt(nodoEscena);
		}
		
		FixtureToDmx.actualizaPosicionNodoToDmx(nodoEscena);
		actualiza();
		actualizable.actualizaValores();
	}

	public void setActualizable(Actualizable actualizable) {
		this.actualizable = actualizable;
	}
}
