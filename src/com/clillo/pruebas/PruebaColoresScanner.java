package com.clillo.pruebas;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.clillo.dmx.comm.dmx.Dmx;

public class PruebaColoresScanner extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1823403452881818081L;
	private JPanel jPanel;
	private JTextField textField;
	
	private JButton btnEnviar;
	private JButton btnFade1;
	private JButton btnFade2;

	public PruebaColoresScanner() {
		enableEvents(64L);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialize();
	}
	
	private void initialize() {
		setSize(new Dimension(400, 200));
		setContentPane(getJPanel());
	}
	
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == 201){
			dispose();
			Dmx.stop();
			System.exit(0);
		}
		super.processWindowEvent(e);
	}
	
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			
			textField = new JTextField();
			textField.setBounds(26, 48, 86, 20);
			jPanel.add(textField);
			textField.setColumns(10);
			
			btnEnviar = new JButton("Enviar");
			btnEnviar.setBounds(144, 47, 89, 23);
			jPanel.add(btnEnviar);
			
			btnFade1 = new JButton("Fade 1");
			btnFade1.setBounds(26, 97, 89, 23);
			btnFade1.addActionListener(this);
			jPanel.add(btnFade1);
			
			btnFade2 = new JButton("Fade 2");
			btnFade2.setBounds(144, 97, 89, 23);
			jPanel.add(btnFade2);
			btnFade2.addActionListener(this);
			btnEnviar.addActionListener(this);		
		}
		return jPanel;
	}
		
	public static void main(String[] args) {
		PruebaColoresScanner vp = new PruebaColoresScanner();
		vp.validate();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = vp.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		vp.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		vp.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnEnviar)){
	    	int valor = Integer.parseInt(textField.getText());
		    enviar(valor);
		}
		
		if (e.getSource().equals(btnFade1)){
	    	int valor = Integer.parseInt(textField.getText());
	    	
	    	for (int i=valor+1; i<=10; i++){
	    		enviar(i);
		        try {
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
				}
		        textField.setText(i+"");
	    	}
		}
		
		if (e.getSource().equals(btnFade2)){
	    	int valor = Integer.parseInt(textField.getText());
	    	
	    	for (int i=valor+1; i>=0; i--){
	    		enviar(i);
		        try {
					Thread.sleep(1500);
				} catch (InterruptedException e1) {
				}
		        textField.setText(i+"");
	    	}
		}
	}
	
	private void enviar(int valor){
		Dmx.enviar(10, 120);
		Dmx.enviar(11, 70);
		Dmx.enviar(34, 128);
		
		if (valor==0)
			Dmx.enviar(33, 0);
		
		if (valor==1)
			Dmx.enviar(33, 21);
		
		if (valor==2)
			Dmx.enviar(33, 41);
		
		if (valor==3)
			Dmx.enviar(33, 61);
		
		if (valor==4)
			Dmx.enviar(33, 81);
		
		if (valor==5)
			Dmx.enviar(33, 101);
		
		if (valor==6)
			Dmx.enviar(33, 121);
		
		if (valor==7)
			Dmx.enviar(33, 141);
		
		if (valor==8)
			Dmx.enviar(33, 161);
		if (valor==9)
			Dmx.enviar(33, 181);
		if (valor==10)
			Dmx.enviar(33, 201);
	}
}
