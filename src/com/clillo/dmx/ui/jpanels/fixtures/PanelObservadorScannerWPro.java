package com.clillo.dmx.ui.jpanels.fixtures;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.configuracion.GoboNombreValorCfg;
import com.clillo.dmx.core.fixtures.FixtureRobotica;
import com.clillo.dmx.core.fixtures.FixtureScanner;
import com.clillo.dmx.core.fixtures.robotizados.GoboNombreValor;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.ListenerCambioPosicion;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.PanelCanvas;

/**
* +00: 01: Pan                   (0 stop)
* +01: 02: Tilt                  (0 stop)
* +02: 03: Color                 (000 - 014 white)
* +03: 04: On/Off                (000 - 014 white)
*/
public class PanelObservadorScannerWPro extends PanelObservador implements ListenerCambioPosicion, ActionListener {
	
	private static final long serialVersionUID = -5766991298707309249L;
	
	private int canalPan;
	private int canalTilt;
	private int canalColor;
	private int canalOnOff;

	private PanelCanvas pnlCanvas;
	private JTextField txtPosicion;
	private FixtureRobotica entidad;

	private JComboBox<GoboNombreValor> cbxColores;
	
	private JButton btnEncender;
	private JButton btnApagar;
	
	private JPanel pnlEncendido;

	public PanelObservadorScannerWPro() {
		super(); 
		setLayout(null);

		txtPosicion = new JTextField();
		txtPosicion.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtPosicion.setBounds(0, 102, 40, 16);
		add(txtPosicion);

		pnlCanvas = new PanelCanvas();
		pnlCanvas.setBackground(Color.BLACK);
		pnlCanvas.setBounds(0, 0, 100, 100);
		pnlCanvas.setTxtPosicion(txtPosicion);
		add(pnlCanvas);
		
		cbxColores = new JComboBox<GoboNombreValor>();
		ArrayList<GoboNombreValor> lista = GoboNombreValorCfg.getListaGoboNombreValor("scanner.wpro.color");
		for (GoboNombreValor par:lista)
			cbxColores.addItem(par);
		cbxColores.setFont(new Font("Tahoma", Font.PLAIN, 9));
		cbxColores.setBounds(0, 122, 138, 16);
		cbxColores.addActionListener(this);
		add(cbxColores);
				
		btnEncender = new JButton("Encender");
		btnEncender.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEncender.setBounds(103, 0, 70, 20);
		btnEncender.addActionListener(this);
		add(btnEncender);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnApagar.setBounds(103, 22, 70, 20);
		btnApagar.addActionListener(this);
		add(btnApagar);
		
		pnlEncendido = new JPanel();
		pnlEncendido.setBounds(148, 118, 22, 20);
		add(pnlEncendido);
		
		pnlEncendido.setBackground(Color.black);
	}

	public void setId(String id){
		super.setId(id);
		canales = new int[]{canalBase, canalBase+1, canalBase+2, canalBase+3};
		
		canalPan = canalBase;
		canalTilt = canalBase + 1;
		canalColor = canalBase + 2;
		canalOnOff = canalBase + 3;
		
		entidad = (FixtureScanner)fixture;
		entidad.setListenerCambioPosicion(this);
		
		pnlCanvas.setEntidad(entidad);

		Dmx.enviar(canalBase, 0);
		Dmx.enviar(canalBase+1, 0);
		apagar();
		Dmx.enviar(canalColor, 6);
	}

	@Override
	public void actualizaEnvioDmx(int canal, int valor){
		if (canal == canalPan)
			entidad.setPosX(valor);
		else
			if (canal == canalTilt)
				entidad.setPosY(valor);
			else
				if (canal == canalColor)
					refrescaComboColor(valor);
				else
					if (canal == canalOnOff){
						if (valor<128)
							pnlEncendido.setBackground(Color.black);
						else
							pnlEncendido.setBackground(Color.white);
					}
					//else
					//	System.out.println("Otros: "+canal+","+valor);
		pnlCanvas.repaint();
	}

	@Override
	public void moverHasta(int x, int y, boolean fine) {
		Dmx.enviar(canalPan, x);
		Dmx.enviar(canalTilt, y);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(cbxColores))
			Dmx.enviar(canalColor, ((GoboNombreValor)cbxColores.getSelectedItem()).getValor());
		
		if (e.getSource().equals(btnApagar))
			apagar();

		if (e.getSource().equals(btnEncender))
			encender();
	}

	@Override
	public void apagar() {
		Dmx.enviar(canalOnOff, 0);
		pnlEncendido.setBackground(Color.black);

//		btnApagar.setEnabled(false);
//		btnEncender.setEnabled(true);
	}
	
	@Override
	public void encender() {
		Dmx.enviar(canalOnOff, 129);
	//	btnApagar.setEnabled(true);
	//	btnEncender.setEnabled(false);
		pnlEncendido.setBackground(Color.white);

	}
	
	private void refrescaComboColor(int valor){
		ArrayList<GoboNombreValor> lista = GoboNombreValorCfg.getListaGoboNombreValor("scanner.wpro.color");
		int indice = 0;
		for (GoboNombreValor par:lista){
			if (par.getValor() == valor){
				cbxColores.removeActionListener(this);
				cbxColores.setSelectedIndex(indice);
				cbxColores.addActionListener(this);
			}
			indice++;
		}
	}

	public int getPan(){
		return ((FixtureRobotica)fixture).getPosX();
	}

	public int getTilt(){
		return ((FixtureRobotica)fixture).getPosY();
	}
}
