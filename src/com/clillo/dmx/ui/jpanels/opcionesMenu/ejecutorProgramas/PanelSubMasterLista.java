package com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.clillo.dmx.comm.vdj.FactoryVDJ;
import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.programas.ConjuntoProgramas;

public class PanelSubMasterLista extends JPanel implements ListenerCambioProbabilidades{

	private static final long serialVersionUID = 8005477601123883469L;
	
	private ArrayList<PanelSubMasterPrograma> listaPanelesDetalles;

	private ButtonGroup grupoBotones;
	private JRadioButton primero=null;

	private ProgramaTO programaSeleccionadoAnterior;
	private int posYMaximo;

	private ConjuntoProgramas conjuntoProgramas;
	
	private boolean tamañoWide;
	
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(390, posYMaximo);
    }
    
	public PanelSubMasterLista() {
		setLayout(null);
		grupoBotones = new ButtonGroup();
		programaSeleccionadoAnterior = null;
		posYMaximo = 0;
	}

	public void setup(ConjuntoProgramas conjuntoProgramas) {
		this.conjuntoProgramas = conjuntoProgramas;
		conjuntoProgramas.getTipoPrograma().setPanelSubMasterLista(this);
		
		listaPanelesDetalles = new ArrayList<PanelSubMasterPrograma>();
		
		for (ProgramaTO programa: conjuntoProgramas.getListaProgramas()){
			PanelSubMasterPrograma panel = new PanelSubMasterPrograma();
			panel.setTamañoWide(tamañoWide);
			listaPanelesDetalles.add(panel);
			panel.setup(programa, this);
			programa.setNotificableEjecutorPasos(panel);
		}
		
		boolean flag = true;

		int i = 0;
		for (PanelSubMasterPrograma panel : listaPanelesDetalles) {		
			if (tamañoWide)
				panel.setBounds(10, 10 + (i * 23), 850, 21);
			else
				panel.setBounds(10, 10 + (i * 23), 650, 21);
			
			panel.setListenerProbabilidades(this);
			
			if (flag)
				panel.setBackground(Color.lightGray);
			else
				panel.setBackground(Color.darkGray);

			flag = !flag;
			add(panel);

			grupoBotones.add(panel.getRbtSeleccionado());
			if (primero==null)
				primero = panel.getRbtSeleccionado();
			i++;
		}
		
		i++;
		posYMaximo = 10 + (i * 23);
		
		FactoryVDJ.agregaListener(conjuntoProgramas.getEjecutor());
	}
	
	public void setTamañoWide(boolean tamañoWide) {
		this.tamañoWide = tamañoWide;
	}

	public void seleccionaPrograma(){
		ProgramaTO programaSeleccionado = null;
		PanelSubMasterPrograma panelSeleccionado = null;
		for (PanelSubMasterPrograma panel : listaPanelesDetalles)
			if (panel.getRbtSeleccionado().isSelected()){
				programaSeleccionado = panel.getPrograma();
				panelSeleccionado = panel;
				break;
			}

		seleccionaPrograma(programaSeleccionado, panelSeleccionado);
	}
	
	private void seleccionaPrograma(ProgramaTO programaSeleccionado, PanelSubMasterPrograma panelSeleccionado){
		if (programaSeleccionadoAnterior!=null){
			conjuntoProgramas.getEjecutor().ejecuta(false, programaSeleccionadoAnterior);
			for (PanelSubMasterPrograma panel : listaPanelesDetalles)
				if (panel.getPrograma().getId().equals(programaSeleccionadoAnterior.getId()))
					panel.refresca();	
		}

		if (programaSeleccionado==null)
			return;

		conjuntoProgramas.getEjecutor().ejecuta(true, programaSeleccionado);
		programaSeleccionadoAnterior = programaSeleccionado;
		panelSeleccionado.refresca();
	}
	
	public void seleccionaNingunPrograma(){
		grupoBotones.clearSelection();
		seleccionaPrograma();
	}
	
	public void seleccionaPrimerPrograma(){
		grupoBotones.setSelected(primero.getModel(), true);
		seleccionaPrograma();
	}

	public void seleccionaPrograma(ProgramaTO programaSeleccionado){
		PanelSubMasterPrograma panelSeleccionado = null;
		for (PanelSubMasterPrograma panel : listaPanelesDetalles){
			if (panel.getPrograma().equals(programaSeleccionado)){
				panelSeleccionado = panel;
				grupoBotones.setSelected(panel.getRbtSeleccionado().getModel(), true);
				break;
			}
		}

		panelSeleccionado.cambiaVelocidadAleatorio();
		seleccionaPrograma(programaSeleccionado, panelSeleccionado);
	}
	
	public void seleccionaProgramaDesdeEjecutorDePasos(ProgramaTO programaSeleccionado){
		PanelSubMasterPrograma panelSeleccionado = null;
		for (PanelSubMasterPrograma panel : listaPanelesDetalles){
			if (panel.getPrograma().equals(programaSeleccionado)){
				panelSeleccionado = panel;
				grupoBotones.setSelected(panel.getRbtSeleccionado().getModel(), true);
				break;
			}
		}

		seleccionaPrograma(programaSeleccionado, panelSeleccionado);
	}
	
	public void cambiaSecuencial(){
		ProgramaTO siguientePrograma = conjuntoProgramas.siguienteSecuencial();
		ejecutaPrograma(siguientePrograma);	
	}
	
	public void cambiaAleatorio(){
		ProgramaTO siguientePrograma = conjuntoProgramas.siguienteAleatorio();
		ejecutaPrograma(siguientePrograma);
	}
	
	private void ejecutaPrograma(ProgramaTO programa){
		PanelSubMasterPrograma panel = null;

		for (PanelSubMasterPrograma pnl : listaPanelesDetalles)
			if (pnl.getPrograma().getId().equals(programa.getId()))
				panel = pnl;

		if (panel==null){
			System.out.println("ERROR GRAVE: programa no encontrado: "+programa);
			return;
		}
		
		panel.getRbtSeleccionado().setSelected(true);
		panel.cambiaVelocidadAleatorio();
		seleccionaPrograma();		
	}

	@Override
	public void cambia() {
		conjuntoProgramas.procesaProbabilidades();
		for (PanelSubMasterPrograma panel : listaPanelesDetalles) 
			panel.refresca();
	}

	public void seleccionaProgramaPorId(String id){
		ProgramaTO programaSeleccionado = null;
		PanelSubMasterPrograma panelSeleccionado = null;
		for (PanelSubMasterPrograma panel : listaPanelesDetalles){
			if (panel.getPrograma().getIdNumerico().equals(id)){
				programaSeleccionado = panel.getPrograma();
				panelSeleccionado = panel;
				grupoBotones.setSelected(panel.getRbtSeleccionado().getModel(), true);
				break;
			}
		}

		System.out.println("Seleccionando programa: "+id+ " - "+programaSeleccionado);

		seleccionaPrograma(programaSeleccionado, panelSeleccionado);
	}
	

}
