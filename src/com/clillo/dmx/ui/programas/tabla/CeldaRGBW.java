package com.clillo.dmx.ui.programas.tabla;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.clillo.dmx.ui.dialogos.EligeColor;

public class CeldaRGBW extends JPanel implements MouseListener {

	private FilaRGBW fila;

	private static final long serialVersionUID = 1L;
	private JLabel lblFila = new JLabel();
	private JLabel lblColor1 = new JLabel();
	private JLabel lblColor2 = new JLabel();
	private JLabel lblColor3 = new JLabel();
	private JLabel lblColor4 = new JLabel();

	public CeldaRGBW() {
		setLayout(null);
		lblColor1.setBackground(Color.BLACK);
		lblColor1.setHorizontalAlignment(SwingConstants.CENTER);
		lblColor1.setOpaque(true);
		lblColor2.setBackground(Color.BLACK);
		lblColor2.setHorizontalAlignment(SwingConstants.CENTER);
		lblColor2.setOpaque(true);
		lblColor3.setBackground(Color.BLACK);
		lblColor3.setHorizontalAlignment(SwingConstants.CENTER);
		lblColor3.setOpaque(true);
		lblColor4.setBackground(Color.BLACK);
		lblColor4.setHorizontalAlignment(SwingConstants.CENTER);
		lblColor4.setOpaque(true);
		lblFila.setBounds(0, 0, 46, 23);
		lblColor1.setBounds(56, 0, 70, 23);
		lblColor2.setBounds(136, 0, 70, 23);
		lblColor3.setBounds(216, 0, 70, 23);
		lblColor4.setBounds(296, 0, 70, 23);

		add(lblFila);
		add(lblColor1);
		add(lblColor2);
		add(lblColor3);
		add(lblColor4);

		lblColor1.addMouseListener(this);
		lblColor2.addMouseListener(this);
		lblColor3.addMouseListener(this);
		lblColor4.addMouseListener(this);
		this.setBounds(0, 0, 459, 20);
	}

	public void setFila(FilaRGBW fila) {
		this.fila = fila;
		lblFila.setText(fila.getNroFila() + "");

		lblColor1.setBackground(fila.getColor1());
		lblColor2.setBackground(fila.getColor2());
		lblColor3.setBackground(fila.getColor3());
		lblColor4.setBackground(fila.getColor4());

		lblColor1.setText(fila.getColor1().getRed()+","+fila.getColor1().getGreen()+","+fila.getColor1().getBlue());
		lblColor2.setText(fila.getColor2().getRed()+","+fila.getColor2().getGreen()+","+fila.getColor2().getBlue());
		lblColor3.setText(fila.getColor3().getRed()+","+fila.getColor3().getGreen()+","+fila.getColor3().getBlue());
		lblColor4.setText(fila.getColor4().getRed()+","+fila.getColor4().getGreen()+","+fila.getColor4().getBlue());
		repaint();
	}

	public FilaRGBW getFila() {
		return fila;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource().equals(lblColor1)){
			EligeColor.setColorInicial(fila.getColor1());
			EligeColor.createAndShowGUI(true);
			
			if (EligeColor.isAceptar()){
				fila.setColor1(new Color(EligeColor.getR(), EligeColor.getG(), EligeColor.getB()));
				setFila(fila);
				fila.actualizar();
			}
		}

		if (e.getSource().equals(lblColor2)){
			EligeColor.setColorInicial(fila.getColor2());
			EligeColor.createAndShowGUI(true);
			
			if (EligeColor.isAceptar()){
				fila.setColor2(new Color(EligeColor.getR(), EligeColor.getG(), EligeColor.getB()));
				setFila(fila);
				fila.actualizar();
			}

		}

		if (e.getSource().equals(lblColor3)){
			EligeColor.setColorInicial(fila.getColor3());
			EligeColor.createAndShowGUI(true);
			
			if (EligeColor.isAceptar()){
				fila.setColor3(new Color(EligeColor.getR(), EligeColor.getG(), EligeColor.getB()));
				setFila(fila);
				fila.actualizar();
			}

		}

		if (e.getSource().equals(lblColor4)){
			EligeColor.setColorInicial(fila.getColor4());
			EligeColor.createAndShowGUI(true);
			
			if (EligeColor.isAceptar()){
				fila.setColor4(new Color(EligeColor.getR(), EligeColor.getG(), EligeColor.getB()));
				setFila(fila);
				fila.actualizar();
			}

		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
