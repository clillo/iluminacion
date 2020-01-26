package com.clillo.dmx.ui.jpanels.fixtures;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.ui.dialogos.EligeColor;
import com.clillo.dmx.ui.dialogos.EligeColorListener;
import com.clillo.dmx.ui.dialogos.EligeDimmer;
import com.clillo.dmx.ui.dialogos.EligeDimmerListener;

public abstract class PanelObservadorRGBWPadre extends PanelObservador implements MouseListener, EligeDimmerListener, EligeColorListener {
	
	private static final long serialVersionUID = 2473818482168626029L;

	protected JPanel pnlColor;
	protected JTextField txtDimmer;

	private int r;
	private int g;
	private int b;
	private int dimmer;
	
	private Color[] colorDimmer = new Color[256];
	private int[] nivelDimmer = new int[256];

	public PanelObservadorRGBWPadre() {
		setLayout(null);
		
		pnlColor = new JPanel();
		pnlColor.setBounds(0, 0, 16, 16);
		pnlColor.addMouseListener(this);
		add(pnlColor);
		
		txtDimmer = new JTextField();
		txtDimmer.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtDimmer.setHorizontalAlignment(SwingConstants.CENTER);
		txtDimmer.setBackground(Color.white);
		txtDimmer.setForeground(Color.black);
		txtDimmer.setBounds(22, 0, 32, 16);
		txtDimmer.setEditable(false);
		txtDimmer.addMouseListener(this);
		txtDimmer.setToolTipText("Click para cambiar porcentaje Dimmer");
		add(txtDimmer);

		pnlColor.setBackground(Color.black);
		txtDimmer.setText("0%");

		inicializaArreglosAuxiliares(colorDimmer, nivelDimmer);
	
		r = g = b = 0;
		dimmer = 0;
	}
	
	protected abstract void inicializaArreglosAuxiliares(Color[] colorDimmer, int[] nivelDimmer);
	
	protected abstract int nivel2Porcentaje(int nivel);

	protected abstract int porcentaje2Nivel(int porcentaje);

	protected abstract int getCanalR();
	protected abstract int getCanalG();
	protected abstract int getCanalB();
	protected abstract int getCanalDimmer();

	public void setId(String id){
		super.setId(id);
		actualizaColor(255, 255, 255);
	}

	@Override
	public void actualizaEnvioDmx(int canal, int valor) {
	//	System.out.println("Actualizando Envio "+canal+"\t"+valor);
		boolean actualizarColor = false;
		if (canal == getCanalDimmer())
			dimmer = valor;
		else
			if (canal == getCanalR()){
				r = valor;
				actualizarColor = true;
			}
			else
				if (canal == getCanalG()){
					g = valor;
					actualizarColor = true;
				}				
				else
					if (canal == getCanalB()){
						b = valor;
						actualizarColor = true;
					}
						
		//System.out.println("Actualizando Color "+r+","+g+","+b);
		if (actualizarColor)
			try {
				pnlColor.setBackground(new Color(r, g, b));
				
			} catch (Exception e) {
				System.out.println("ERROR al actualizar colores: "+r+","+g+","+b);
			}
		
		txtDimmer.setText(nivelDimmer[dimmer]+"%");
		
		actualizaEnvioDmxOtros(canal, valor);
	}

	protected abstract  void actualizaEnvioDmxOtros(int canal, int valor);

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource().equals(pnlColor)){
			int anteriorR = r;
			int anteriorG = g;
			int anteriorB = b;
			
			EligeColor.setListener(this);
			EligeColor.setColorInicial(new Color(r, g, b));
			EligeColor.createAndShowGUI();
			
			if (!EligeColor.isAceptar())
				 actualizaColor(anteriorR, anteriorG, anteriorB);
						
		}
		
		if (e.getSource().equals(txtDimmer)){
			int anteriorNivel = dimmer;
			EligeDimmer.setListener(this);
			EligeDimmer.setNivel(dimmer);
			EligeDimmer.createAndShowGUI();
			
			if (!EligeDimmer.isAceptar())
				actualizaDimmer(anteriorNivel);
		}
	}

	@Override
	public void actualizaColor(int r, int g, int b) {
		if (getCanalR()>=0)
			Dmx.enviar(getCanalR(),  r);

		if (getCanalG()>=0)
			Dmx.enviar(getCanalG(),  g);

		if (getCanalB()>=0)
			Dmx.enviar(getCanalB(),  b);
	}
	
	@Override
	public void actualizaDimmer(int nivel) {
		Dmx.enviar(getCanalDimmer(), porcentaje2Nivel(nivel));
	}
	
	@Override
	public void apagar() {
		actualizaDimmer(0);
	}
	
	@Override
	public void encender(){
		if (dimmer==0 || dimmer>100)
			actualizaDimmer(100);
		else
			actualizaDimmer(dimmer);
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
}
