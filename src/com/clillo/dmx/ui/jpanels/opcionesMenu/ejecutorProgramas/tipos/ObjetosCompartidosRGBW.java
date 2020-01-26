package com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos;

import com.clillo.dmx.configuracion.programas.TipoPrograma;
import com.clillo.dmx.programas.ConjuntoProgramas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelSubMasterLista;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelTipoCambioAutomatico;

public class ObjetosCompartidosRGBW {

	private PanelSubMasterLista pnlColor;
	private PanelSubMasterLista pnlDimmer;
	private PanelSubMasterLista pnlStrobo;
	
	private PanelTipoCambioAutomatico cambioAutomaticoColor;
	private PanelTipoCambioAutomatico cambioAutomaticoDimmer; 
	private PanelTipoCambioAutomatico cambioAutomaticoStrobo;
	
	private ConjuntoProgramas conjuntoProgramasColor = new ConjuntoProgramas(TipoPrograma.RGBWColor);
	private ConjuntoProgramas conjuntoProgramasDimmer = new ConjuntoProgramas(TipoPrograma.RGBWDimmer);
	private ConjuntoProgramas conjuntoProgramasStrobo = new ConjuntoProgramas(TipoPrograma.RGBWStrobo);
	
	public PanelSubMasterLista getPnlColor() {
		return pnlColor;
	}
	public void setPnlColor(PanelSubMasterLista pnlColor) {
		this.pnlColor = pnlColor;
	}
	public PanelSubMasterLista getPnlDimmer() {
		return pnlDimmer;
	}
	public void setPnlDimmer(PanelSubMasterLista pnlDimmer) {
		this.pnlDimmer = pnlDimmer;
	}
	public PanelSubMasterLista getPnlStrobo() {
		return pnlStrobo;
	}
	public void setPnlStrobo(PanelSubMasterLista pnlStrobo) {
		this.pnlStrobo = pnlStrobo;
	}
	public PanelTipoCambioAutomatico getCambioAutomaticoColor() {
		return cambioAutomaticoColor;
	}
	public void setCambioAutomaticoColor(PanelTipoCambioAutomatico cambioAutomaticoColor) {
		this.cambioAutomaticoColor = cambioAutomaticoColor;
	}
	public PanelTipoCambioAutomatico getCambioAutomaticoDimmer() {
		return cambioAutomaticoDimmer;
	}
	public void setCambioAutomaticoDimmer(PanelTipoCambioAutomatico cambioAutomaticoDimmer) {
		this.cambioAutomaticoDimmer = cambioAutomaticoDimmer;
	}
	public PanelTipoCambioAutomatico getCambioAutomaticoStrobo() {
		return cambioAutomaticoStrobo;
	}
	public void setCambioAutomaticoStrobo(PanelTipoCambioAutomatico cambioAutomaticoStrobo) {
		this.cambioAutomaticoStrobo = cambioAutomaticoStrobo;
	}

	public ConjuntoProgramas getConjuntoProgramasColor() {
		return conjuntoProgramasColor;
	}
	public void setConjuntoProgramasColor(ConjuntoProgramas conjuntoProgramasColor) {
		this.conjuntoProgramasColor = conjuntoProgramasColor;
	}
	public ConjuntoProgramas getConjuntoProgramasDimmer() {
		return conjuntoProgramasDimmer;
	}
	public void setConjuntoProgramasDimmer(ConjuntoProgramas conjuntoProgramasDimmer) {
		this.conjuntoProgramasDimmer = conjuntoProgramasDimmer;
	}
	public ConjuntoProgramas getConjuntoProgramasStrobo() {
		return conjuntoProgramasStrobo;
	}
	public void setConjuntoProgramasStrobo(ConjuntoProgramas conjuntoProgramasStrobo) {
		this.conjuntoProgramasStrobo = conjuntoProgramasStrobo;
	}
}
