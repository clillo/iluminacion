package com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.clillo.dmx.aleatorios.AleatorioNormal;
import com.clillo.dmx.configuracion.programas.GrabaCatalogo;
import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.programas.NotificableEjecutorPasos;
import com.clillo.dmx.ui.dialogos.EligeProbabilidades;

public class PanelSubMasterPrograma extends JPanel implements NotificableEjecutorPasos, ActionListener, ChangeListener, AdjustmentListener, MouseListener{

	private static final long serialVersionUID = 8005477601123883469L;

	private JTextField txtNombre;
	private JTextField txtDetalles;
	private JScrollBar sldrVelocidad;
	private JRadioButton rbtSeleccionado;
	
	private ProgramaTO programa;
	private PanelSubMasterLista padre;
	private AleatorioNormal aleatorioVelocidad;
	private JTextField txtProbabilidad;
	
	private ListenerCambioProbabilidades listenerProbabilidades;
	
	private boolean tamañoWide;

	public PanelSubMasterPrograma() {
		setLayout(null);
 
		sldrVelocidad = new JScrollBar();
		sldrVelocidad.setOrientation(JScrollBar.HORIZONTAL);
		sldrVelocidad.setBounds(183, 3, 133, 16);
		sldrVelocidad.setMinimum(1);
		sldrVelocidad.setMaximum(500);
		add(sldrVelocidad);
		
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtNombre.setBounds(25, 3, 152, 16);
		txtNombre.setEditable(false);
		add(txtNombre);
		
		txtDetalles = new JTextField();
		txtDetalles.setText("255/255");
		txtDetalles.setHorizontalAlignment(SwingConstants.CENTER);
		txtDetalles.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtDetalles.setBounds(318, 3, 40, 16);
		txtDetalles.setEditable(false);
		add(txtDetalles);
		
		txtProbabilidad = new JTextField();
		txtProbabilidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtProbabilidad.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtProbabilidad.setEditable(false);
		txtProbabilidad.setBounds(364, 3, 62, 16);
		txtProbabilidad.addMouseListener(this);
		add(txtProbabilidad);
		
		rbtSeleccionado = new JRadioButton("");
		rbtSeleccionado.setBounds(1, 3, 21, 16);
		add(rbtSeleccionado);
		
		rbtSeleccionado.addActionListener(this);	
	}

	public void setTamañoWide(boolean tamañoWide) {
		this.tamañoWide = tamañoWide;
		
		if(tamañoWide){
			txtNombre.setBounds(25, 3, 180, 16);
			txtProbabilidad.setBounds(480, 3, 62, 16);
		//	txtDetalles.setBounds(418, 3, 40, 16);
			sldrVelocidad.setBounds(520, 3, 160, 16);
		}
	}

	@Override
	public void setBackground(Color bg) {
		super.setBackground(bg);
		if (sldrVelocidad!=null)
			sldrVelocidad.setBackground(bg);
		
		if (rbtSeleccionado!=null)
			rbtSeleccionado.setBackground(bg);
	}
	
	private boolean cambiaVelocidad(){
		boolean cambiaVelocidad = true;
		
		if ( (programa.getListaPasos().size()==1 && programa.getVelocidadMovimiento()==0))
			cambiaVelocidad = false;
		
		if (cambiaVelocidad)
			cambiaVelocidad = !programa.isEsOnOff();
		
		return cambiaVelocidad;
	}

	public void setup(ProgramaTO programa, PanelSubMasterLista padre) {
		this.padre = padre;
		this.programa = programa;
		refresca();

		pasoEjecutado();
		aleatorioVelocidad = new AleatorioNormal(100, 50);

		if (programa.getPorcentajeVelocidadMinimo()>0)
			sldrVelocidad.setMinimum(programa.getPorcentajeVelocidadMinimo());
		
		if (programa.getPorcentajeVelocidadMaximo()>0)
			sldrVelocidad.setMaximum(programa.getPorcentajeVelocidadMaximo());
		
		if (!cambiaVelocidad()){
			txtNombre.setBounds(25, 3, (tamañoWide?185:100), 16);
			txtDetalles.setVisible(false);
			if(!tamañoWide || programa.isNingunProgramaSeleccionado()){
				sldrVelocidad.setVisible(false);
			}else{
				txtNombre.setText(programa.getNombre()+" ("+programa.getPorcentajeVelocidadActual()+"%)");

				sldrVelocidad.setValue(programa.getPorcentajeVelocidadActual());
				sldrVelocidad.addAdjustmentListener(this);
			}
			txtProbabilidad.setBounds((tamañoWide?220:130), 3, 295, 16);
			txtProbabilidad.setHorizontalAlignment(SwingConstants.LEFT);
		}
	}
	
	public void refresca(){
		if (!cambiaVelocidad()){
							
			if (tamañoWide){
				txtNombre.setText(programa.getNombre()+" ("+programa.getPorcentajeVelocidadActual()+"%)");
				
				sldrVelocidad.removeAdjustmentListener(this);
				sldrVelocidad.setValue(programa.getPorcentajeVelocidadActual());
				sldrVelocidad.addAdjustmentListener(this);	
			}else
				txtNombre.setText(programa.getNombre());	

			txtProbabilidad.setText("Probabilidad: "+programa.getParametros().getProbabilidadGlobalNormalizada()+" Nro. de ejecuciones: "+programa.getParametros().getCantidadEjecuciones()+" Tiempo de ejecución: "+programa.getParametros().getSumaTiempos()/1000);
		}else{
			txtNombre.setText(programa.getNombre()+" ("+programa.getPorcentajeVelocidadActual()+"%)");
			
			sldrVelocidad.removeAdjustmentListener(this);
			sldrVelocidad.setValue(programa.getPorcentajeVelocidadActual());
			sldrVelocidad.addAdjustmentListener(this);
			
			txtProbabilidad.setText(programa.getParametros().getProbabilidadGlobalNormalizada()+"-"+programa.getParametros().getCantidadEjecuciones()+"-"+programa.getParametros().getSumaTiempos()/1000);
		}
	}
	
	public JRadioButton getRbtSeleccionado() {
		return rbtSeleccionado;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(rbtSeleccionado))
			padre.seleccionaPrograma();
	}

	@Override
	public void pasoEjecutado(){
		txtDetalles.setText((programa.getPasoActual()+1)+"/"+programa.getListaPasos().size());
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource().equals(sldrVelocidad)){
//			System.out.println(sldrVelocidad.getValue());
			programa.setPorcentajeVelocidadActual(sldrVelocidad.getValue());
			refresca();
		}
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		programa.setPorcentajeVelocidadActual(sldrVelocidad.getValue());
		refresca();
	}
	
	public void cambiaVelocidadAleatorio(){
		//System.out.println("Cambiando velocidad aleatorio: "+sldrVelocidad.isEnabled());
		if (!sldrVelocidad.isEnabled())
			return;
		
		int valorSiguiente = aleatorioVelocidad.siguiente();
		programa.setPorcentajeVelocidadActual(valorSiguiente);
		refresca();
	}

	public ProgramaTO getPrograma() {
		return programa;
	}
	
	public void setListenerProbabilidades(ListenerCambioProbabilidades listenerProbabilidades) {
		this.listenerProbabilidades = listenerProbabilidades;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		EligeProbabilidades.setup(programa);
		EligeProbabilidades.createAndShowGUI();
		
		if (EligeProbabilidades.isAceptar()){
			programa.getParametros().setProbabilidadGlobal(EligeProbabilidades.getProbabilidad());
			programa.getParametros().setTiempoEncendidoMedia(EligeProbabilidades.getTiempoMedioEncendido());			
			programa.getParametros().setTiempoEncendidoDevSta(EligeProbabilidades.getTiempoEncendidoDevSta());
			programa.setGrabar(true);
			GrabaCatalogo.grabaTodos();
			
			if (listenerProbabilidades!=null)
				listenerProbabilidades.cambia();
		}
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
