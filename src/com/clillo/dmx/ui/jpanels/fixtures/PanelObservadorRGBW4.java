package com.clillo.dmx.ui.jpanels.fixtures;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.clillo.dmx.ui.dialogos.EligeColor;
import com.clillo.dmx.ui.dialogos.EligeColorListener;
import com.clillo.dmx.ui.dialogos.EligeDimmer;
import com.clillo.dmx.ui.dialogos.EligeDimmerListener;

/**
 * Representa un dmx con leds rgb y blanco
 * El canal 1 es el control de brillo. 0-15 apagado, 16-255 desde apagado hasta encendido total
 * El canal 2 es el valor para r
 * El canal 3 es el valor para g
 * El canal 4 es el valor para b
 * El canal 5 es el valor para w (blanco)
 * El canal 6 es el valor para strobo. 0-15 apagado, 16-255 velocidad desde lento hasta rápido
 * El canal 7 es el control para el cambio de colores automático. 0-15 apagado, 16-255 velocidad del cambio

 * Strobo: rápido 190
 */
public class PanelObservadorRGBW4 extends JPanel implements MouseListener, EligeDimmerListener, EligeColorListener {
	
	private static final long serialVersionUID = -5766991298707309249L;

	private JPanel pnlColor;
	private JTextField txtDimmer;

	private PanelObservadorRGBW id1;
	private PanelObservadorRGBW id2;
	private PanelObservadorRGBW id3;
	private PanelObservadorRGBW id4;

	private int r;
	private int g;
	private int b;
	private int dimmer;
	
	public PanelObservadorRGBW4() {
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
	
	}
	
	public void setId(PanelObservadorRGBW id1, PanelObservadorRGBW id2, PanelObservadorRGBW id3, PanelObservadorRGBW id4){
		this.id1 = id1;
		this.id2 = id2;
		this.id3 = id3;
		this.id4 = id4;
	}



	protected int nivel2Porcentaje(int nivel) {
		return 100*(nivel-16)/239; 
	}

	protected int porcentaje2Nivel(int porcentaje) {
		return (255*porcentaje/100);
	}


	protected void actualizaEnvioDmxOtros(int canal, int valor) {
		//System.out.println("Error al tratar de manejar el mensaje al canal "+canal+" desde el observador rgb");
	}
	

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
		id1.actualizaColor(r, g, b);
		id2.actualizaColor(r, g, b);
		id3.actualizaColor(r, g, b);
		id4.actualizaColor(r, g, b);
		this.r = r;
		this.g = g;
		this.b = b;
		try {
			pnlColor.setBackground(new Color(r, g, b));
		} catch (Exception e) {
			System.out.println("ERROR al actualizar colores: "+r+","+g+","+b);
		}
	
	}
	
	@Override
	public void actualizaDimmer(int nivel) {
		id1.actualizaDimmer(nivel);
		id2.actualizaDimmer(nivel);
		id3.actualizaDimmer(nivel);
		id4.actualizaDimmer(nivel);
		dimmer = nivel;
		txtDimmer.setText(dimmer+"%");
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
