package com.clillo.dmx.ui.dialogos;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.Principal;

public class EligeNivelSniffer extends JPanel implements ChangeListener, ActionListener, AdjustmentListener {

	private static final long serialVersionUID = 2066941021336318125L;

	private final static int max = 11;

	private JButton btnAceptar;
	private JButton btnAnterior; 
	private JButton btnSiguiente;
	
	private static JDialog dialog;

	private JTextField txtNivel[];
	private JScrollBar scrlNivel[];
	private JPanel bannerPanel[]; 
	
	private static HashMap<Integer, String> canalesStr;
	private static int nivelInicial;
	
	public EligeNivelSniffer() {
		setLayout(null);
		setPreferredSize(new Dimension(802, 400));

		scrlNivel = new JScrollBar[max];
		txtNivel = new JTextField[max];
		bannerPanel = new JPanel[max];
		
		creaNiveles();
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(386, 366, 89, 23);
		btnAceptar.addActionListener(this);
		add(btnAceptar);
		
		btnAnterior = new JButton("Anterior");
		btnAnterior.setBounds(10, 366, 89, 23);
		btnAnterior.addActionListener(this);
		add(btnAnterior);
		
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setBounds(689, 366, 89, 23);
		btnSiguiente.addActionListener(this);
		add(btnSiguiente);
			
	}

	public static void setNivelInicial(int nivelInicial) {
		EligeNivelSniffer.nivelInicial = nivelInicial;
	}
	
	public static void setCanalesStr(HashMap<Integer, String> canalesStr) {
		EligeNivelSniffer.canalesStr = canalesStr;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
	}
	
	private void creaNiveles(){
		for (int i=0; i<max; i++){
			bannerPanel[i] = new JPanel();
			bannerPanel[i].setLayout(null);
			bannerPanel[i].setBorder(BorderFactory.createTitledBorder(null, canalesStr.get(nivelInicial+i), TitledBorder.CENTER, TitledBorder.LEFT, new Font("times new roman",Font.PLAIN,8), Color.blue));
			bannerPanel[i].setBounds(10+70*i, 11, 64, 334);
			add(bannerPanel[i]);
			
			scrlNivel[i] = new JScrollBar();
			scrlNivel[i].setBounds(22, 23, 16, 269);
			scrlNivel[i].setMinimum(0);
			scrlNivel[i].setMaximum(265);
			scrlNivel[i].setValue(255 - Dmx.obtieneValorActualCanal(nivelInicial+i));
			scrlNivel[i].addAdjustmentListener(this);
			
			bannerPanel[i].add(scrlNivel[i]);
			
			txtNivel[i] = new JTextField(Dmx.obtieneValorActualCanal(nivelInicial+i)+"");
			txtNivel[i].setBounds(10, 303, 44, 20);
			txtNivel[i].setHorizontalAlignment(SwingConstants.CENTER);
			bannerPanel[i].add(txtNivel[i]);
			
			txtNivel[i].addActionListener(this);
		}
	}

	private void eliminaNiveles(){
		for (int i=0; i<max; i++){
			bannerPanel[i].remove(scrlNivel[i]);
			bannerPanel[i].remove(txtNivel[i]);
			remove(bannerPanel[i]);		
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i=0; i<max; i++)
			if (e.getSource().equals(txtNivel[i])){
				int nivel = Integer.parseInt(txtNivel[i].getText());
				
				Dmx.enviar(nivelInicial+i, nivel);
				scrlNivel[i].setValue(255 - nivel);
				return;
			}
		
		if (e.getSource().equals(btnAceptar))
			dialog.dispose();

		if (e.getSource().equals(btnAnterior)){
			nivelInicial-=10;
			if (nivelInicial<0)
				nivelInicial=0;
			eliminaNiveles();
			creaNiveles();
			repaint();
		}

		if (e.getSource().equals(btnSiguiente)){
			nivelInicial+=10;
			if (nivelInicial>512)
				nivelInicial = 512;
			eliminaNiveles();
			creaNiveles();
			repaint();
		}

	}

	public static void createAndShowGUI() {
		JComponent newContentPane = new EligeNivelSniffer();
		newContentPane.setOpaque(true);

		dialog = new JDialog(Principal.getFrame(), Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setTitle("Elegir Niveles DMX");
		dialog.setContentPane(newContentPane);
		dialog.setSize(new Dimension(820, 430));
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.dispose();
	}
	
	public static void main(String[] args) {
		createAndShowGUI();
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent arg0) {
		for (int i=0; i<max; i++)
			if (arg0.getSource().equals(scrlNivel[i])){
			 	txtNivel[i].setText((255-scrlNivel[i].getValue())+"");
			 	Dmx.enviar(nivelInicial+i, 255-scrlNivel[i].getValue());
			}
		
	}
}