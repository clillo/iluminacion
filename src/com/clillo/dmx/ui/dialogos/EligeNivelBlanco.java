package com.clillo.dmx.ui.dialogos;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
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
import com.clillo.utiles.ColorUtil;

public class EligeNivelBlanco extends JPanel implements ChangeListener,	ActionListener {

	private static final long serialVersionUID = 2066941021336318125L;

	private JSlider sldrNivel;

	private JLabel banner;
	private JButton btnAceptar;
	private static JDialog dialog;

	private static int nivelInicial = 0;
	private static int nivel = 0;
	private static boolean aceptar;

	public EligeNivelBlanco() {
		setLayout(null);
		setPreferredSize(new Dimension(600, 176));

		banner = new JLabel(nivel+"", JLabel.CENTER);
		banner.setBackground(ColorUtil.obtieneColorFondoGris(nivel));
		banner.setForeground(ColorUtil.obtieneColorFuenteGris(nivel));
		banner.setOpaque(true);

		JPanel bannerPanel = new JPanel(new BorderLayout());
		bannerPanel.add(banner, BorderLayout.CENTER);
		bannerPanel.setBorder(BorderFactory.createTitledBorder("Ejemplo de Blanco"));
		bannerPanel.setBounds(10, 11, 580, 78);
		add(bannerPanel);

		sldrNivel = new JSlider(JSlider.HORIZONTAL, 0, 255, nivel);
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

	public static int getNivelInicial() {
		return nivelInicial;
	}

	public static boolean isAceptar() {
		return aceptar;
	}

	public static void setNivel(int nivel) {
		EligeNivelBlanco.nivel = nivel;
		EligeNivelBlanco.nivelInicial = nivel;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		nivel = sldrNivel.getValue();
		banner.setBackground(ColorUtil.obtieneColorFondoGris(nivel));
		banner.setForeground(ColorUtil.obtieneColorFuenteGris(nivel));
		banner.setText(nivel+"");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		aceptar = false;

		if (e.getSource().equals(btnAceptar)) {
			nivel = sldrNivel.getValue();
			aceptar = true;
		}

		dialog.dispose();
	}

	public static void createAndShowGUI() {
		JComponent newContentPane = new EligeNivelBlanco();
		newContentPane.setOpaque(true);

		dialog = new JDialog(Principal.getFrame(), Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setTitle("Elegir Nivel de Canal Blanco");
		dialog.setContentPane(newContentPane);
		dialog.setSize(new Dimension(620, 210));
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.dispose();
	}
}