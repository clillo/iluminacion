package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.Actualizable;
import javax.swing.JComboBox;

public class PanelCanalesMovingHead extends JPanel implements ChangeListener {

	private static final long serialVersionUID = -5869553409971473557L;

	private JTextField txtMvhd;
	
	private JTextField txtPan;
	private JTextField txtTilt;
	private JSpinner spiPan1;
	private JSpinner spiPan2;
	private JSpinner spiTilt1;
	private JSpinner spiTilt2;
	private JSpinner spDimmer;

	private JLabel label;
	private JLabel label_1;
	
	private NodoEscena nodoEscena;
	private Actualizable actualizable;
	private JLabel lblSpeed;
	private JLabel lblColor;
	private JLabel lblGobo;
	private JLabel lblGRotation;
	private JLabel lblStrobo;
	private JSpinner spinner_3;
	private JLabel lblPrisma;
	private JSpinner spinner_4;
	private JLabel lblFocus;
	private JComboBox comboBox_2;
	private JLabel lblGoboCristal;
	private JSpinner spinner_5;
	private JLabel lblGoboSh;
	
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
		
		spDimmer = new JSpinner();
		spDimmer.setBounds(357, 27, 40, 20);
		add(spDimmer);
		
		JLabel lblDimmer = new JLabel("Dimmer");
		lblDimmer.setBounds(357, 11, 46, 14);
		add(lblDimmer);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(407, 27, 60, 20);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(473, 27, 60, 20);
		add(comboBox_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(79, 75, 40, 20);
		add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(146, 75, 40, 20);
		add(spinner_1);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(219, 75, 40, 20);
		add(spinner_2);
		
		lblSpeed = new JLabel("Speed");
		lblSpeed.setBounds(81, 58, 46, 14);
		add(lblSpeed);
		
		lblColor = new JLabel("Color");
		lblColor.setBounds(407, 11, 46, 14);
		add(lblColor);
		
		lblGobo = new JLabel("Gobo");
		lblGobo.setBounds(474, 11, 46, 14);
		add(lblGobo);
		
		lblGRotation = new JLabel("G Rotation");
		lblGRotation.setBounds(146, 58, 73, 14);
		add(lblGRotation);
		
		lblStrobo = new JLabel("Strobo");
		lblStrobo.setBounds(219, 58, 46, 14);
		add(lblStrobo);
		
		spinner_3 = new JSpinner();
		spinner_3.setBounds(274, 75, 40, 20);
		add(spinner_3);
		
		lblPrisma = new JLabel("Prisma");
		lblPrisma.setBounds(274, 58, 46, 14);
		add(lblPrisma);
		
		spinner_4 = new JSpinner();
		spinner_4.setBounds(331, 75, 40, 20);
		add(spinner_4);
		
		lblFocus = new JLabel("Focus");
		lblFocus.setBounds(331, 58, 46, 14);
		add(lblFocus);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(472, 74, 60, 20);
		add(comboBox_2);
		
		lblGoboCristal = new JLabel("Gobo Cristal");
		lblGoboCristal.setBounds(473, 58, 73, 14);
		add(lblGoboCristal);
		
		spinner_5 = new JSpinner();
		spinner_5.setBounds(384, 75, 40, 20);
		add(spinner_5);
		
		lblGoboSh = new JLabel("Gobo Sh");
		lblGoboSh.setBounds(384, 58, 46, 14);
		add(lblGoboSh);
				
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
		
		spDimmer.removeChangeListener(this);
		spDimmer.setValue(nodoEscena.getDimmer());
		spDimmer.addChangeListener(this);
		//this.setBackground(nodoEscena.isSeleccionado()?Color.red: Color.lightGray);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource().equals(spDimmer)){
			nodoEscena.setDimmer((Integer)spDimmer.getValue());
		}
		if (e.getSource().equals(spiPan1)){
			nodoEscena.setPan1((Integer)spiPan1.getValue());
			nodoEscena.setPan(nodoEscena.getPan1()*256 + nodoEscena.getPan2());

			double ancho = nodoEscena.getEntidad().getVentanaMaxX()-nodoEscena.getEntidad().getVentanaMinX();			
			int x = (int) ((nodoEscena.getPan() - nodoEscena.getEntidad().getVentanaMinX())*800.0/ancho);
			
			nodoEscena.setX(x);
		}

		if (e.getSource().equals(spiPan2)){
			nodoEscena.setPan2((Integer)spiPan2.getValue());
			nodoEscena.setPan(nodoEscena.getPan1()*256 + nodoEscena.getPan2());
			
			double ancho = nodoEscena.getEntidad().getVentanaMaxX()-nodoEscena.getEntidad().getVentanaMinX();
			int x = (int) ((nodoEscena.getPan() - nodoEscena.getEntidad().getVentanaMinX())*800.0/ancho);

			nodoEscena.setX(x);
		}

		if (e.getSource().equals(spiTilt1)){
			nodoEscena.setTilt1((Integer)spiTilt1.getValue());
			nodoEscena.setTilt(nodoEscena.getTilt1()*256 + nodoEscena.getTilt2());
			
			double alto = nodoEscena.getEntidad().getVentanaMaxY()-nodoEscena.getEntidad().getVentanaMinY();
			
			int y = (int) ((nodoEscena.getTilt() - nodoEscena.getEntidad().getVentanaMinY())*800.0/alto);
			nodoEscena.setY(y);
		}

		if (e.getSource().equals(spiTilt2)){
			nodoEscena.setTilt2((Integer)spiTilt2.getValue());
			nodoEscena.setTilt(nodoEscena.getTilt1()*256 + nodoEscena.getTilt2());

			double alto = nodoEscena.getEntidad().getVentanaMaxY()-nodoEscena.getEntidad().getVentanaMinY();
			int y = (int) ((nodoEscena.getTilt() - nodoEscena.getEntidad().getVentanaMinY())*800.0/alto);
			nodoEscena.setY(y);
		}
		
		FixtureToDmx.actualizaNodoToDmx(nodoEscena);
		actualiza();
		actualizable.actualizaValores();
	}

	public void setActualizable(Actualizable actualizable) {
		this.actualizable = actualizable;
	}
}
