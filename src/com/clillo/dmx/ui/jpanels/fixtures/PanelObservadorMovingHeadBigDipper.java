package com.clillo.dmx.ui.jpanels.fixtures;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.configuracion.GoboNombreValorCfg;
import com.clillo.dmx.core.fixtures.FixtureMovingHead;
import com.clillo.dmx.core.fixtures.FixtureRobotica;
import com.clillo.dmx.core.fixtures.robotizados.GoboNombreValor;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.ListenerCambioPosicion;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.PanelCanvas;

/**
* +00: 01: Pan                   (0 stop)
* +01: 02: Tilt                  (0 stop)
* +02: 03: Speed                 (0 stop - fast to slow)
* +03: 04: Color                 (000 - 014 white)
*                                (015 - 027 color 1)
*                                (028 - 042 color 2)
*                                (043 - 054 color 3)
*                                (055 - 068 color 4)
*                                (069 - 082 color 5)
*                                (083 - 097 color 6)
*                                (098 - 110 color 7)
*                                (111 - 121 color 8)
*                                (122 - 127 color 9)
*                                (128 - 192 color rotating 1)
*                                (193 - 255 color rotating 2)
* +04: 05: Gobo                  (000 - 018 white)
*                                (019 - 037 gobo 1)
*                                (038 - 056 gobo 2)
*                                (057 - 075 gobo 3)
*                                (076 - 093 gobo 4)
*                                (094 - 113 gobo 5)
*                                (114 - 132 gobo 6)
*                                (133 - 144 gobo 7)
*                                (145 - 208 gobo rotating 1)
*                                (209 - 255 gobo rotating 2)
* +05: 06: Gobo Rotation		 (000 - 011 no rotation)
* 								 (012 - 130 F-S)
* 								 (131 - 255 S-F)         
* +06: 07: Strobe                (000 - 011 white)
* 								 (012 - 030 Reset after 10 secs)
* 								 (031 - 250 Shutter S-F White)
* 								 (251 - 255 white)
* +07: 08: Dimming               (0 blackout - dark to bright)
* +08: 09: Pan fine              (1-255 adjustment)
* +09: 10: Tilt fine             (1-255 adjustment)
* +10: 11: Prisma
* +11: 12: Focus 
*/
public class PanelObservadorMovingHeadBigDipper extends PanelObservadorRGBWPadre implements ListenerCambioPosicion, ActionListener, ChangeListener {
	
	private static final long serialVersionUID = -5766991298707309249L;
	
	private int canalPan;
	private int canalTilt;
	private int canalColor;
	private int canalGobos;
	private int canalGoboRotation;
	private int canalPrisma;
	private int canalFoco;
	private int canalStrobo;
	private int canalPanFine;
	private int canalTiltFine;

	private PanelCanvas pnlCanvas;
	private JTextField txtPosicion;
	private JTextField txtFine;
	
	private FixtureMovingHead entidad;

	private JComboBox<GoboNombreValor> cbxColores;
	private JComboBox<GoboNombreValor> cbxGobos;
	private JComboBox<GoboNombreValor> cbxRotar;
	private JCheckBox chkPrisma;
	private JSlider sldrFoco;
	private JComboBox<GoboNombreValor> cbxStrobo;
	
	private JButton btnEncender;
	private JButton btnApagar;

	public PanelObservadorMovingHeadBigDipper() {
		super(); 
		
		pnlColor.setBounds(184, 6, 10, 10);
		txtDimmer.setBounds(120, 0, 32, 16);

		txtPosicion = new JTextField();
		txtPosicion.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtPosicion.setBounds(0, 102, 40, 16);
		add(txtPosicion);
		
		txtFine = new JTextField();
		txtFine.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtFine.setBounds(48, 102, 40, 16);
		add(txtFine);
		
		pnlCanvas = new PanelCanvas();
		pnlCanvas.setBackground(Color.BLACK);
		pnlCanvas.setBounds(0, 0, 100, 100);
		pnlCanvas.setTxtPosicion(txtPosicion);
		pnlCanvas.setTxtFine(txtFine);
		add(pnlCanvas);
		
		cbxColores = new JComboBox<GoboNombreValor>();
		ArrayList<GoboNombreValor> lista = GoboNombreValorCfg.getListaGoboNombreValor("movingHead.color");
		for (GoboNombreValor par:lista)
			cbxColores.addItem(par);
		cbxColores.setFont(new Font("Tahoma", Font.PLAIN, 9));
		cbxColores.setBounds(0, 122, 75, 16);
		cbxColores.addActionListener(this);
		add(cbxColores);
		
		cbxGobos = new JComboBox<GoboNombreValor>();
		lista = GoboNombreValorCfg.getListaGoboNombreValor("movingHead.gobo");
			for (GoboNombreValor par:lista)
				cbxGobos.addItem(par);
		cbxGobos.setFont(new Font("Tahoma", Font.PLAIN, 9));
		cbxGobos.setBounds(90, 122, 75, 16);
		cbxGobos.addActionListener(this);
		add(cbxGobos);
		

		cbxRotar = new JComboBox<GoboNombreValor>();
		lista = GoboNombreValorCfg.getListaGoboNombreValor("movingHead.goboRotating");
			for (GoboNombreValor par:lista)
				cbxRotar.addItem(par);
		cbxRotar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		cbxRotar.setBounds(0, 145, 75, 16);
		cbxRotar.addActionListener(this);
		add(cbxRotar);
		
		chkPrisma = new JCheckBox("Prisma");
		chkPrisma.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chkPrisma.setBounds(105, 85, 60, 21);
		chkPrisma.addActionListener(this);
		add(chkPrisma);
		
		sldrFoco = new JSlider();
		sldrFoco.setMinimum(0);
		sldrFoco.setMaximum(255);
		sldrFoco.setValue(0);
		sldrFoco.setBounds(0, 172, 165, 20);
		sldrFoco.addChangeListener(this);
		add(sldrFoco);

		cbxStrobo = new JComboBox<GoboNombreValor>();
		lista = GoboNombreValorCfg.getListaGoboNombreValor("movingHead.strobo");
			for (GoboNombreValor par:lista)
				cbxStrobo.addItem(par);
		cbxStrobo.setFont(new Font("Tahoma", Font.PLAIN, 9));
		cbxStrobo.setBounds(90, 145, 75, 16);
		cbxStrobo.addActionListener(this);
		add(cbxStrobo);
		
		btnEncender = new JButton("");
		btnEncender.setBounds(154, 0, 16, 16);
		btnEncender.addActionListener(this);
		add(btnEncender);
		
		btnApagar = new JButton("");
		btnApagar.setBounds(103, 0, 16, 16);
		btnApagar.addActionListener(this);
		add(btnApagar);
	}

	public void setId(String id){
		super.setId(id);
		canales = new int[]{canalBase, canalBase+1, canalBase+2, canalBase+3, canalBase+4, canalBase+5, canalBase+6, canalBase+7, canalBase+8, canalBase+9, canalBase+10, canalBase+11};
		
		canalPan = canalBase;
		canalTilt = canalBase + 1;
		canalStrobo = canalBase + 3;
		canalColor = canalBase + 4;
		canalGobos = canalBase + 8;
		canalGoboRotation = canalBase + 6;

		canalPanFine = canalBase + 13;
		canalTiltFine = canalBase + 14;
		canalPrisma = canalBase + 11;
		canalFoco = canalBase + 12;
		
		/**
		 * 
		 * 				<canal id="movingHead3.pan">0</canal>
				<canal id="movingHead3.tilt">1</canal>
				<canal id="movingHead3.dim">2</canal>
				<canal id="movingHead3.strobo">3</canal>
				<canal id="movingHead3.color">4</canal>
				<canal id="movingHead3.gobocristal">5</canal>
				<canal id="movingHead3.goborotation">6</canal>
				<canal id="movingHead3.goboshake">7</canal>
				<canal id="movingHead3.gobonormal">8</canal>
				<canal id="movingHead3.gobonormalshake">9</canal>
				<canal id="movingHead3.foco">10</canal>
				<canal id="movingHead3.prisma">11</canal>
				<canal id="movingHead3.nc">12</canal>
				<canal id="movingHead3.panfino">13</canal>
				<canal id="movingHead3.tiltfino">14</canal>
		 * 
		 * 
		 */
		
		
		entidad = (FixtureMovingHead)fixture;
		entidad.setListenerCambioPosicion(this);

		pnlCanvas.setEntidad(entidad);

		for (int i=0; i<=12; i++)
			Dmx.enviar(canalBase+i, 0);
		
		Dmx.enviar(canalStrobo, 255);
	}

	@Override
	protected void inicializaArreglosAuxiliares(Color[] colorDimmer, int[] nivelDimmer) {
		for (int i=0; i<=255; i++){
			int nivel =  nivel2Porcentaje(i); 
			colorDimmer[i] = new Color(nivel, nivel, nivel);
			nivelDimmer[i] = 100*i/255;
			//System.out.println(i+"\t"+nivelDimmer[i] );
		}
	}

	@Override
	protected int nivel2Porcentaje(int nivel) {
		return  100*nivel/255; 
	}

	@Override
	protected int porcentaje2Nivel(int porcentaje) {
		return (255*porcentaje/100);
	}

	@Override
	protected int getCanalR() {
		return -1;
	}

	@Override
	protected int getCanalG() {
		return -1;
	}

	@Override
	protected int getCanalB() {
		return -1;
	}

	@Override
	protected int getCanalDimmer() {
		return canalBase + 2;
	}

	@Override
	protected void actualizaEnvioDmxOtros(int canal, int valor) {
		if (canal == canalPan)
			entidad.setPosX(valor);
		else
			if (canal == canalTilt)
				entidad.setPosY(valor);
			else
				if (canal == canalColor)
					refrescaComboValor(valor, "movingHead.color", cbxColores);
				else
					if (canal == canalGobos)
						refrescaComboValor(valor, "movingHead.gobo", cbxGobos);
					else
						if (canal == canalGoboRotation)
							refrescaComboValor(valor, "movingHead.goboRotating", cbxRotar);
						else
							if (canal == canalStrobo)
								refrescaComboValor(valor, "movingHead.strobo", cbxStrobo);
							else
								if (canal == canalPrisma)
									if (valor==0)
										chkPrisma.setSelected(false);
									else
										chkPrisma.setSelected(true);
		pnlCanvas.repaint();
	}

	private void refrescaComboValor(int valor, String tipo, JComboBox<GoboNombreValor> combo){
		ArrayList<GoboNombreValor> lista = GoboNombreValorCfg.getListaGoboNombreValor(tipo);
		int indice = 0;
		for (GoboNombreValor par:lista){
			if (par.getValor() == valor){
				combo.removeActionListener(this);
				combo.setSelectedIndex(indice);
				combo.addActionListener(this);
			}
			indice++;
		}
	}
	
	@Override
	public void moverHasta(int x, int y, boolean fine) {
		if (fine){
			Dmx.enviar(canalPanFine, x);
			Dmx.enviar(canalTiltFine, y);
		}else{
			Dmx.enviar(canalPan, x);
			Dmx.enviar(canalTilt, y);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(cbxColores))
			Dmx.enviar(canalColor, ((GoboNombreValor)cbxColores.getSelectedItem()).getValor());

		if (e.getSource().equals(cbxGobos))
			Dmx.enviar(canalGobos, ((GoboNombreValor)cbxGobos.getSelectedItem()).getValor());
		
		if (e.getSource().equals(cbxRotar))
			Dmx.enviar(canalGoboRotation, ((GoboNombreValor)cbxRotar.getSelectedItem()).getValor());
	
		if (e.getSource().equals(cbxStrobo))
			Dmx.enviar(canalStrobo, ((GoboNombreValor)cbxStrobo.getSelectedItem()).getValor());

		if (e.getSource().equals(chkPrisma) && chkPrisma.isSelected())
			Dmx.enviar(canalPrisma, 255);

		if (e.getSource().equals(chkPrisma) && !chkPrisma.isSelected())
			Dmx.enviar(canalPrisma, 0);

		if (e.getSource().equals(btnApagar))
			apagar();

		if (e.getSource().equals(btnEncender))
			encender();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Dmx.enviar(canalFoco, sldrFoco.getValue());
	}
	
	public int getPan(){
		return ((FixtureRobotica)fixture).getPosX();
	}

	public int getTilt(){
		return ((FixtureRobotica)fixture).getPosY();
	}
}
