package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.Actualizable;
import javax.swing.JComboBox;

public class PanelCanalesEncendidoMovingHead extends JPanel implements ChangeListener {

	private static final long serialVersionUID = -5869553409971473557L;
	private JSpinner spDimmer;
	
	private NodoEscena nodoEscena;
	private Actualizable actualizable;
	private JLabel lblSpeed;
	private JLabel lblColor;
	private JLabel lblGobo;
	private JLabel lblGRotation;
	private JLabel lblStrobo;
	private JSpinner spPrisma;
	private JLabel lblPrisma;
	private JSpinner spFocus;
	private JLabel lblFocus;
	private JComboBox comboBox_2;
	private JLabel lblGoboCristal;
	private JSpinner spGoboShake;
	private JLabel lblGoboSh;
	private JTextField txtMvhd;
	private JSpinner spStrobo;
	private JSpinner spGoboRotation;
	private JSpinner spColor;
	private JSpinner spGobo;
	private JSpinner spGoboCristal;
	
	public PanelCanalesEncendidoMovingHead() {
		
		this.setLayout(null);
		this.setBounds(0, 4, 1423, 1080);
		
		spDimmer = new JSpinner();
		spDimmer.setBounds(88, 27, 40, 20);
		add(spDimmer);
		
		JLabel lblDimmer = new JLabel("Dimmer");
		lblDimmer.setBounds(88, 11, 46, 14);
		add(lblDimmer);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(35, 133, 60, 20);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(117, 133, 60, 20);
		add(comboBox_1);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(10, 75, 40, 20);
		add(spinner);
		
		spGoboRotation = new JSpinner();
		spGoboRotation.setBounds(77, 75, 40, 20);
		add(spGoboRotation);
		
		spStrobo = new JSpinner();
		spStrobo.setBounds(150, 75, 40, 20);
		add(spStrobo);
		
		lblSpeed = new JLabel("Speed");
		lblSpeed.setBounds(12, 58, 46, 14);
		add(lblSpeed);
		
		lblColor = new JLabel("Color");
		lblColor.setBounds(150, 11, 46, 14);
		add(lblColor);
		
		lblGobo = new JLabel("Gobo");
		lblGobo.setBounds(206, 11, 46, 14);
		add(lblGobo);
		
		lblGRotation = new JLabel("G Rotation");
		lblGRotation.setBounds(77, 58, 73, 14);
		add(lblGRotation);
		
		lblStrobo = new JLabel("Strobo");
		lblStrobo.setBounds(150, 58, 46, 14);
		add(lblStrobo);
		
		spPrisma = new JSpinner();
		spPrisma.setBounds(205, 75, 40, 20);
		add(spPrisma);
		
		lblPrisma = new JLabel("Prisma");
		lblPrisma.setBounds(205, 58, 46, 14);
		add(lblPrisma);
		
		spFocus = new JSpinner();
		spFocus.setBounds(262, 75, 40, 20);
		add(spFocus);
		
		lblFocus = new JLabel("Focus");
		lblFocus.setBounds(262, 58, 46, 14);
		add(lblFocus);
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(192, 133, 60, 20);
		add(comboBox_2);
		
		lblGoboCristal = new JLabel("Gobo Cristal");
		lblGoboCristal.setBounds(262, 11, 73, 14);
		add(lblGoboCristal);
		
		spGoboShake = new JSpinner();
		spGoboShake.setBounds(315, 75, 40, 20);
		add(spGoboShake);
		
		lblGoboSh = new JLabel("Gobo Sh");
		lblGoboSh.setBounds(315, 58, 46, 14);
		add(lblGoboSh);
		
		JLabel label = new JLabel("Moving Head");
		label.setBounds(10, 11, 68, 14);
		add(label);
		
		txtMvhd = new JTextField();
		txtMvhd.setBounds(10, 27, 60, 20);
		add(txtMvhd);
		
		spColor = new JSpinner();
		spColor.setBounds(150, 27, 40, 20);
		add(spColor);
		
		spGobo = new JSpinner();
		spGobo.setBounds(205, 27, 40, 20);
		add(spGobo);
		
		spGoboCristal = new JSpinner();
		spGoboCristal.setBounds(262, 27, 40, 20);
		add(spGoboCristal);
				
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

		spStrobo.removeChangeListener(this);
		spStrobo.setValue(nodoEscena.getStrobo());
		spStrobo.addChangeListener(this);
		
		spGoboShake.removeChangeListener(this);
		spGoboShake.setValue(nodoEscena.getGoboShake());
		spGoboShake.addChangeListener(this);
		
		spGoboRotation.removeChangeListener(this);
		spGoboRotation.setValue(nodoEscena.getGoboRotation());
		spGoboRotation.addChangeListener(this);
		
		spPrisma.removeChangeListener(this);
		spPrisma.setValue(nodoEscena.getPrisma());
		spPrisma.addChangeListener(this);

		spFocus.removeChangeListener(this);
		spFocus.setValue(nodoEscena.getFocus());
		spFocus.addChangeListener(this);

		spDimmer.removeChangeListener(this);
		spDimmer.setValue(nodoEscena.getDimmer());
		spDimmer.addChangeListener(this);
		
		spColor.removeChangeListener(this);
		spColor.setValue(nodoEscena.getColor());
		spColor.addChangeListener(this);
		
		spGobo.removeChangeListener(this);
		spGobo.setValue(nodoEscena.getGobo());
		spGobo.addChangeListener(this);
		
		spGoboCristal.removeChangeListener(this);
		spGoboCristal.setValue(nodoEscena.getGoboCristal());
		spGoboCristal.addChangeListener(this);

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource().equals(spDimmer)){
			nodoEscena.setDimmer((Integer)spDimmer.getValue());
		}

		if (e.getSource().equals(spGoboShake)){
			nodoEscena.setGoboShake((Integer)spGoboShake.getValue());
		}

		if (e.getSource().equals(spStrobo)){
			nodoEscena.setStrobo((Integer)spStrobo.getValue());
		}
		
		if (e.getSource().equals(spGoboRotation)){
			nodoEscena.setGoboRotation((Integer)spGoboRotation.getValue());
		}
		
		if (e.getSource().equals(spPrisma)){
			nodoEscena.setPrisma((Integer)spPrisma.getValue());
		}
		
		if (e.getSource().equals(spFocus)){
			nodoEscena.setFocus((Integer)spFocus.getValue());
		}
		if (e.getSource().equals(spColor)){
			nodoEscena.setColor((Integer)spColor.getValue());
		}
		if (e.getSource().equals(spGobo)){
			nodoEscena.setGobo((Integer)spGobo.getValue());
		}
		if (e.getSource().equals(spGoboCristal)){
			nodoEscena.setGoboCristal((Integer)spGoboCristal.getValue());
		}
		FixtureToDmx.actualizaEncendidoNodoToDmx(nodoEscena);
		actualiza();
		actualizable.actualizaValores();
	}

	public void setActualizable(Actualizable actualizable) {
		this.actualizable = actualizable;
	}
}
