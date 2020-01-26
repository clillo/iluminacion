package com.clillo.dmx.ui.jpanels.fixtures.crystalMagic;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.SwingConstants;

public class PanelCrystalMagicCanal extends JPanel implements	ActionListener, AdjustmentListener {

	private static final long serialVersionUID = -3664280122489375394L;

	private JTextField txtNivel;
	private JButton btnEncender;
	private JButton btnApagar;
	private JScrollBar scrlValores;

	private int nivel;
	private IterfazNotificadorCambio notificador;

	public PanelCrystalMagicCanal() {
		setLayout(null);
		
		scrlValores = new JScrollBar();
		scrlValores.setOrientation(JScrollBar.HORIZONTAL);
		scrlValores.setBounds(79, 0, 140, 17);
		scrlValores.setMaximum(255);
		scrlValores.setMinimum(0);
		scrlValores.addAdjustmentListener(this);
		add(scrlValores);

		txtNivel = new JTextField();
		txtNivel.setHorizontalAlignment(SwingConstants.CENTER);
		txtNivel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtNivel.setBounds(20, 0, 28, 16);
		add(txtNivel);

		btnEncender = new JButton("");
		btnEncender.setBounds(53, 1, 16, 16);
		btnEncender.addActionListener(this);
		add(btnEncender);

		btnApagar = new JButton("");
		btnApagar.setBounds(0, 0, 16, 16);
		btnApagar.addActionListener(this);
		add(btnApagar);
		nivel = 0;
		
		txtNivel.setText(nivel+"");
		scrlValores.setValue(nivel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnEncender))
			nivel = 255;
		
		if (e.getSource().equals(btnApagar))
			nivel = 0;
		
		setNivel(nivel);
		if (notificador!=null)
			notificador.cambio();
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		setNivel(e.getValue());
		if (notificador!=null)
			notificador.cambio();
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		scrlValores.removeAdjustmentListener(this);
		this.nivel = nivel;
		txtNivel.setText(nivel+"");
		scrlValores.setValue(nivel);
		scrlValores.addAdjustmentListener(this);
	}

	public void setNotificador(IterfazNotificadorCambio notificador) {
		this.notificador = notificador;
	}
}
