package com.clillo.vdj;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JToggleButton;
import javax.swing.JLabel;

public class VUMeter extends JFrame implements VUListener, ActionListener{

	private static final long serialVersionUID = 1823403452881818081L;
	
	private static final int MAX = 50;
	private static final int MAXPeak = 20;
	
	private JScrollBar scrollBar;
	private JPanel jPanel;
	private JPanel paneles[] = new JPanel[MAX];
	private JPanel panelesPeak[] = new JPanel[MAXPeak];
	private JTextField txtPosicion;

	private double rotation;
	private JTextField txtBeats;
	private JTextField txtBeatCounter;
	private JTextField txtNombre;
	private boolean grabando = false;
	private FileOutputStream fos;

	private ArrayList<Integer> wave = new ArrayList<Integer>();
	
	public VUMeter() {
		enableEvents(64L);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		initialize();
	}
	
	private void initialize() {
		setSize(new Dimension(1280, 600));
		setContentPane(getJPanel());
	}
	
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == 201){
			dispose();
			System.exit(0);
		}
		super.processWindowEvent(e);
	}

	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			
			scrollBar = new JScrollBar();
			scrollBar.setOrientation(JScrollBar.HORIZONTAL);
			scrollBar.setBounds(10, 51, 1244, 53);
			jPanel.add(scrollBar);
			scrollBar.setMaximum(10000);
			scrollBar.setMinimum(0);
			
			txtPosicion = new JTextField();
			txtPosicion.setBounds(99, 127, 86, 20);
			jPanel.add(txtPosicion);
			
			txtBeats = new JTextField();
			txtBeats.setBounds(99, 165, 86, 20);
			jPanel.add(txtBeats);
			
			txtBeatCounter = new JTextField();
			txtBeatCounter.setBounds(99, 196, 86, 20);
			jPanel.add(txtBeatCounter);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(10, 227, 252, 20);
			jPanel.add(txtNombre);
			
			JToggleButton tglbtnGrabar = new JToggleButton("Grabar");
			tglbtnGrabar.setBounds(416, 164, 93, 23);
			jPanel.add(tglbtnGrabar);
			
			JLabel lblPosicin = new JLabel("Posici\u00F3n");
			lblPosicin.setBounds(10, 130, 46, 14);
			jPanel.add(lblPosicin);
			
			JLabel lblSongPosBeat = new JLabel("Song Pos Beat");
			lblSongPosBeat.setBounds(10, 168, 86, 14);
			jPanel.add(lblSongPosBeat);
			
			JLabel lblBeatCounter = new JLabel("Beat Counter");
			lblBeatCounter.setBounds(10, 199, 86, 14);
			jPanel.add(lblBeatCounter);
			tglbtnGrabar.addActionListener(this);

			for (int i=0; i<MAX; i++){
				paneles[i] = new JPanel();
				paneles[i].setBounds(i*25, 26, 20, 20);
				jPanel.add(paneles[i]);

			}
			for (int i=0; i<MAXPeak; i++){
				panelesPeak[i] = new JPanel();
				panelesPeak[i].setBounds(600, 120 + i*12, 40, 10);
				jPanel.add(panelesPeak[i]);
			}
			
		}
		return jPanel;
	}
		
	@Override
	public void cambiar(double vuMeter, double rotation, double posicion, double songPosBeats, double beatCounter, String nombre) {	
		if (fos!=null)
			try {
				fos.write(((int)(vuMeter*100000)+","+(int)(posicion*100000)+","+(int)(beatCounter*100000)+"\n").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		
		txtNombre.setText(nombre);

		//txtBeatCounter.setText((int)(beatCounter*16)+"");
		int pp = 0;
		if (beatCounter<0.25)
		   pp = 1;
		else
			if (beatCounter<0.5)
				   pp = 2;
			else
				if (beatCounter<0.75)
					   pp = 3;
				else
					pp = 2;
		txtBeatCounter.setText(((int)(songPosBeats%16)+1)+" - "+pp);
		
		double v = 0.0;
		double inc = 1 / (MAX * 1.0);
		
		for (int i=0; i<MAX; i++){
			if (v<vuMeter)
				if (i<MAX*0.8)
					paneles[i].setBackground(Color.green);
				else
					if(i<MAX*0.9)
						paneles[i].setBackground(Color.orange);
					else
						paneles[i].setBackground(Color.red);
			else
				paneles[i].setBackground(Color.black);

			v = v + inc;
		}
		
		txtBeats.setText((int)songPosBeats+"");

		songPosBeats = Math.abs(songPosBeats - ((int)songPosBeats));
		
		v = 0.1;
		inc = 1 / (MAXPeak * 1.0);
		for (int i=0; i<MAXPeak; i++){
			if (v<=beatCounter)
				panelesPeak[i].setBackground(Color.black);
			else
				panelesPeak[i].setBackground(Color.red);
			v = v + inc;
		}
		
		int ipos = (int)(posicion*10000);
		scrollBar.setValue(ipos);
		txtPosicion.setText(ipos/100.0+"%");
		
	//	if (ipos%1 ==0)
	//		wave.add(200 - (int)(beatCounter*200));
			//wave.add(200 - (int)(vuMeter*200));

		
		this.rotation = rotation;
		
		this.repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.drawLine(400, 250, 400+ (int)(100 * Math.sin(-rotation*Math.PI*2)), 250+(int)( 100 * Math.cos(-rotation*Math.PI*2)) );
		g.drawOval(300, 150, 200, 200);
		
	//	for (int x=1; x<1200*4; x++)
	//		if (x<wave.size())
		//		g.drawLine((x-1)/4, wave.get(x-1)+340, x/4, wave.get(x)+340);
		for (int x=1; x<1200*4; x++)
				if (x<wave.size() &&  Math.abs(wave.get(x)-200)<4)
					g.drawOval(x/10, wave.get(x)+340,4,4);
	}
	
	public static void main(String[] args) {
		VUMeter vp = new VUMeter();
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
		Receptor r = new Receptor(vp);
		r.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (grabando){
			try {
				fos.flush();
				fos.close();
				fos=null;
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}else{
			try {
				fos = new FileOutputStream("C:\\tmp\\salida.txt");
			} catch (FileNotFoundException e1) {

				e1.printStackTrace();
			}	
		}
		grabando = !grabando;
		
	}
}
