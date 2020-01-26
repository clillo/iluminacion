package com.clillo.dmx.configuracion.programas.to;

import java.io.Serializable;
import java.util.ArrayList;

import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.configuracion.programas.TipoPrograma;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelSubMasterLista;

public class PasoProgramaTO implements Serializable{

	private static final long serialVersionUID = -2099400564239843273L;

	public enum TipoRandom{
		PrimerPaso,
		PasoAPaso;
		
		public static TipoRandom obtienePorCodigo(String cod){
			if (cod==null)
				return null;
			
			if (cod.equals("primerPaso"))
				return TipoRandom.PrimerPaso;
			
			if (cod.equals("pasoAPaso"))
				return TipoRandom.PasoAPaso;
			
			return null;
		}
	}
	
	private TipoPrograma tipo;
	private String id;
	private ProgramaTO programa;
	private PanelSubMasterLista panelCambio;
	private boolean ningunProgramaSeleccionado;
	private ArrayList<String> listaIdsProgramas;
	private ArrayList<ProgramaTO> listaProgramas;
	private TipoRandom tipoRandom;
		
	private static int ultimoId=0;

	private int idInterno;
	
	public PasoProgramaTO() {
		idInterno = ultimoId++;
	}
	
	public TipoPrograma getTipo() {
		return tipo;
	}
	public void setTipo(TipoPrograma tipo) {
		this.tipo = tipo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ProgramaTO getPrograma() {
		return programa;
	}
	public void setPrograma(ProgramaTO programa) {
		this.programa = programa;
	}
	public PanelSubMasterLista getPanelCambio() {
		return panelCambio;
	}
	public void setPanelCambio(PanelSubMasterLista panelCambio) {
		this.panelCambio = panelCambio;
	}
	public boolean isNingunProgramaSeleccionado() {
		return ningunProgramaSeleccionado;
	}
	public void setNingunProgramaSeleccionado(boolean ningunProgramaSeleccionado) {
		this.ningunProgramaSeleccionado = ningunProgramaSeleccionado;
	}
	public ArrayList<String> getListaIdsProgramas() {
		return listaIdsProgramas;
	}
	public void setListaIdsProgramas(ArrayList<String> listaIdsProgramas) {
		this.listaIdsProgramas = listaIdsProgramas;
	}
	public ArrayList<ProgramaTO> getListaProgramas() {
		return listaProgramas;
	}
	public void setListaProgramas(ArrayList<ProgramaTO> listaProgramas) {
		this.listaProgramas = listaProgramas;
	}
	public TipoRandom getTipoRandom() {
		return tipoRandom;
	}
	public void setTipoRandom(TipoRandom tipoRandom) {
		this.tipoRandom = tipoRandom;
	}

	public int getIdInterno() {
		return idInterno;
	}
}
