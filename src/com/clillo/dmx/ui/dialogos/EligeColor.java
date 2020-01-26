package com.clillo.dmx.ui.dialogos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.clillo.dmx.catalogo.ColorTO;
import com.clillo.dmx.catalogo.Colores;
import com.clillo.dmx.ui.Principal;

public class EligeColor extends JPanel implements ChangeListener, ActionListener, MouseListener {

	private static final long serialVersionUID = 2066941021336318125L;

	private JColorChooser colorChooser;
	private JLabel banner;
	private JButton btnAceptar;
	private JButton btnElijeColor;

	private static JDialog dialog;

	private static int r;
	private static int g;
	private static int b;

	private static boolean aceptar;
	private static Color colorInicial;

	private static EligeColorListener listener;
	private JTextField txtR;
	private JTextField txtG;
	private JTextField txtB;
	
	public EligeColor() {
		setLayout(null);
		setPreferredSize(new Dimension(600, 400));

		banner = new JLabel("", JLabel.CENTER);
		banner.setBounds(6, 16, 295, 22);
		banner.setBackground(colorInicial);
		banner.setOpaque(true);

		JPanel bannerPanel = new JPanel();
		bannerPanel.setLayout(null);
		bannerPanel.add(banner);
		bannerPanel.setBorder(BorderFactory.createTitledBorder("Ejemplo del Color"));
		bannerPanel.setBounds(10, 11, 580, 45);
		add(bannerPanel);
		
		txtR = new JTextField();
		txtR.setBounds(311, 16, 50, 20);
		bannerPanel.add(txtR);
		txtR.setColumns(10);
		
		txtG = new JTextField();
		txtG.setBounds(371, 16, 50, 20);
		bannerPanel.add(txtG);
		txtG.setColumns(10);
		
		txtB = new JTextField();
		txtB.setBounds(431, 16, 50, 20);
		bannerPanel.add(txtB);
		txtB.setColumns(10);
		
		btnElijeColor = new JButton("Elije Color");
		btnElijeColor.setBounds(491, 16, 79, 23);
		bannerPanel.add(btnElijeColor);
		btnElijeColor.addActionListener(this);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(313, 360, 89, 23);
		btnAceptar.addActionListener(this);
		add(btnAceptar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(182, 360, 89, 23);
		btnCancelar.addActionListener(this);
		add(btnCancelar);
				
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 67, 580, 275);
		add(tabbedPane);
		
		JPanel pnlColorChooser = new JPanel();
		pnlColorChooser.setLayout(new BorderLayout(0, 0));
				
		colorChooser = new JColorChooser(banner.getBackground());
		pnlColorChooser.add(colorChooser);
		colorChooser.getSelectionModel().addChangeListener(this);
		colorChooser.setBorder(BorderFactory.createTitledBorder("Elija el Color"));
		colorChooser.setPreviewPanel(new JPanel());
		
		JPanel pnlColoresBasicos = new JPanel();
		tabbedPane.addTab("Colores Básicos", null, pnlColoresBasicos, null);
		tabbedPane.addTab("Full Colores", null, pnlColorChooser, null);
		pnlColoresBasicos.setLayout(null);
		
		llenaDatos(pnlColoresBasicos);
		r = g = b = -1;
		aceptar = false;
		if (colorInicial!=null){
			txtR.setText(colorInicial.getRed()+"");
			txtG.setText(colorInicial.getGreen()+"");
			txtB.setText(colorInicial.getBlue()+"");
		}

	}

	public static int getR() {
		return r;
	}

	public static int getG() {
		return g;
	}

	public static int getB() {
		return b;
	}

	public static boolean isAceptar() {
		return aceptar;
	}

	public static void setColorInicial(Color colorInicial) {
		EligeColor.colorInicial = colorInicial;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		seleccionaColor(colorChooser.getColor());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnElijeColor)){
			Color newColor = new Color(Integer.parseInt(txtR.getText()), Integer.parseInt(txtG.getText()),Integer.parseInt(txtB.getText()));
			seleccionaColor(newColor);
			return;
		}
			
		aceptar = false;

		if (e.getSource().equals(btnAceptar)) {
			r = banner.getBackground().getRed();
			g = banner.getBackground().getGreen();
			b = banner.getBackground().getBlue();
			aceptar = true;
		}

		dialog.dispose();
	}

	public static void createAndShowGUI() {
		JComponent newContentPane = new EligeColor();
		newContentPane.setOpaque(true);

		dialog = new JDialog(Principal.getFrame(), Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setTitle("Elegir Color");
		dialog.setContentPane(newContentPane);
		dialog.setSize(new Dimension(620, 430));
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.dispose();
	}
	
	public static void createAndShowGUI(boolean simple) {
		JComponent newContentPane = new EligeColor();
		newContentPane.setOpaque(true);

		dialog = new JDialog(null, Dialog.ModalityType.APPLICATION_MODAL);
		dialog.setTitle("Elegir Color");
		dialog.setContentPane(newContentPane);
		dialog.setSize(new Dimension(620, 430));
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.dispose();
	}
	
	private void llenaDatos(JPanel pnlColoresBasicos){
		int y = 10;
		int x = 20;
		 ArrayList<ColorTO> listaColores = Colores.getListaColoresBasicos();
		for (ColorTO color: listaColores){
			JLabel lbl = new JLabel();
			lbl.setText(color.getId()+"");
			lbl.setBounds(new Rectangle(x, y, 50, 15));
			
			JLabel lbl2 = new JLabel();
			lbl2.setText(color.getNombre());
			lbl2.setBounds(new Rectangle(x+50, y, 120, 15));

			JPanel pnl = new JPanel();
			pnl.setBounds(new Rectangle(x+20, y, 15, 15));
			pnl.setBackground(color.getColor());
			pnl.addMouseListener(this);
			
			pnlColoresBasicos.add(lbl);
			pnlColoresBasicos.add(lbl2);
			pnlColoresBasicos.add(pnl);
			
			y+=25;
			if (y > 200){
				y=10;
				x+=200;
			}
		}
	}
	
	private void seleccionaColor(Color newColor){
		banner.setBackground(newColor);
		//banner.setText(newColor.getRed() + "," + newColor.getGreen() + "," + newColor.getBlue());
		txtR.setText(newColor.getRed()+"");
		txtG.setText(newColor.getGreen()+"");
		txtB.setText(newColor.getBlue()+"");

		if (listener!=null)
			listener.actualizaColor(newColor.getRed(), newColor.getGreen(), newColor.getBlue());
	}

	public static void main(String[] args) {
		createAndShowGUI(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JPanel pnl = (JPanel)e.getSource();
		seleccionaColor(pnl.getBackground());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
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

	public static void setListener(EligeColorListener listener) {
		EligeColor.listener = listener;
	}
	
}