package com.clillo.dmx.ui.dialogos;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.ui.Principal;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class EligeProbabilidades extends JPanel implements ActionListener {

	private static final long serialVersionUID = 2066941021336318125L;
	private JButton btnAceptar;
	private static JDialog dialog;

	private static boolean aceptar;
	private static JTextField txtProbabilidad;
	private JLabel lblProbabilidad;
	private static JTextField txtTiempoMedioEncendido;
	private JLabel lblTiempoEncendidoseg;
	private static JTextField txtTiempoEncendidoDevSta;
	private JLabel lblMediaEncendidoseg;

	private static ProgramaTO programa;
	
	public EligeProbabilidades() {
		setLayout(null);
		setPreferredSize(new Dimension(340, 120));
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(170, 57, 89, 23);
		btnAceptar.addActionListener(this);
		add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(41, 57, 89, 23);
		btnCancelar.addActionListener(this);
		add(btnCancelar);
		
		txtProbabilidad = new JTextField();
		txtProbabilidad.setBounds(10, 26, 47, 20);
		add(txtProbabilidad);
		
		lblProbabilidad = new JLabel("Probabilidad");
		lblProbabilidad.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblProbabilidad.setBounds(10, 11, 74, 14);
		add(lblProbabilidad);
		
		txtTiempoMedioEncendido = new JTextField();
		txtTiempoMedioEncendido.setBounds(82, 26, 47, 20);
		add(txtTiempoMedioEncendido);
		
		lblTiempoEncendidoseg = new JLabel("Tiempo Encendido (seg)");
		lblTiempoEncendidoseg.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblTiempoEncendidoseg.setBounds(82, 11, 133, 14);
		add(lblTiempoEncendidoseg);
		
		txtTiempoEncendidoDevSta = new JTextField();
		txtTiempoEncendidoDevSta.setBounds(211, 26, 47, 20);
		add(txtTiempoEncendidoDevSta);
		
		lblMediaEncendidoseg = new JLabel("Media Encendido (seg)");
		lblMediaEncendidoseg.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblMediaEncendidoseg.setBounds(211, 11, 133, 14);
		add(lblMediaEncendidoseg);

		txtProbabilidad.setText(programa.getParametros().getProbabilidadGlobal()+"");
		txtTiempoMedioEncendido.setText(programa.getParametros().getTiempoEncendidoMedia()/1000+"");
		txtTiempoEncendidoDevSta.setText(programa.getParametros().getTiempoEncendidoDevSta()/1000+"");

		aceptar = false;
	}
	
	public static void setup(ProgramaTO programa){
		EligeProbabilidades.programa = programa;		
	}
	
	public static int getProbabilidad(){
		return Integer.parseInt(txtProbabilidad.getText());
	}

	public static int getTiempoMedioEncendido(){
		return Integer.parseInt(txtTiempoMedioEncendido.getText())*1000;
	}

	public static int getTiempoEncendidoDevSta(){
		return Integer.parseInt(txtTiempoEncendidoDevSta.getText())*1000;
	}

	public static boolean isAceptar() {
		return aceptar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		aceptar = false;

		if (e.getSource().equals(btnAceptar)) {
			aceptar = true;
		}

		dialog.dispose();
	}

	public static void createAndShowGUI() {
		JComponent newContentPane = new EligeProbabilidades();
		newContentPane.setOpaque(true);

		dialog = new JDialog(Principal.getFrame(), Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setTitle("Elegir Datos de Probabilidad");
		dialog.setContentPane(newContentPane);
		dialog.setSize(new Dimension(360, 140));
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.dispose();
	}
}