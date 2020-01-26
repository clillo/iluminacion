package com.clillo.dmx.ui.dialogos;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.clillo.dmx.ui.Principal;
import com.clillo.utiles.HoraUtil;

public class EligeTiempo extends JPanel implements ChangeListener,	ActionListener {

	private static final long serialVersionUID = 2066941021336318125L;

	private JSlider sldrNivel;

	private JLabel banner;
	private JButton btnAceptar;
	private static JDialog dialog;

	private static int milisegundos = 0;
	private static int milisegundosMaximo = 30*60*1000; // máximo 30 minutos
	private static boolean aceptar;

	public EligeTiempo() {
		setLayout(null);
		setPreferredSize(new Dimension(600, 176));

		banner = new JLabel(HoraUtil.toStringElige(milisegundos), JLabel.CENTER);
		banner.setFont(new Font("Tahoma", Font.PLAIN, 22));

		JPanel bannerPanel = new JPanel(new BorderLayout());
		bannerPanel.add(banner, BorderLayout.CENTER);
		bannerPanel.setBorder(BorderFactory.createTitledBorder("Tiempo"));
		bannerPanel.setBounds(10, 11, 580, 78);
		add(bannerPanel);

		sldrNivel = new JSlider(JSlider.HORIZONTAL, milisegundosMaximo, milisegundos); 
		sldrNivel.setBounds(10, 89, 580, 37);
		sldrNivel.addChangeListener(this);
		add(sldrNivel);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(321, 137, 89, 23);
		btnAceptar.addActionListener(this);
		add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(190, 137, 89, 23);
		btnCancelar.addActionListener(this);
		add(btnCancelar);
		aceptar = false;
	}

	public static boolean isAceptar() {
		return aceptar;
	}

	public static int getMilisegundos() {
		return milisegundos;
	}

	public static void setMilisegundos(int milisegundos) {
		EligeTiempo.milisegundos = milisegundos;
	}
	
	public static void setMilisegundosMaximo(int milisegundos) {
		EligeTiempo.milisegundosMaximo = milisegundos;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		milisegundos = sldrNivel.getValue();
		banner.setText(HoraUtil.toStringElige(milisegundos));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		aceptar = false;

		if (e.getSource().equals(btnAceptar)) {
			milisegundos = sldrNivel.getValue();
			aceptar = true;
		}

		dialog.dispose();
	}

	public static void createAndShowGUI() {
		JComponent newContentPane = new EligeTiempo();
		newContentPane.setOpaque(true);

		dialog = new JDialog(Principal.getFrame(), Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setTitle("Elegir Tiempo");
		dialog.setContentPane(newContentPane);
		dialog.setSize(new Dimension(620, 210));
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.dispose();
	}
}