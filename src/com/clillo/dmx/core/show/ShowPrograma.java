package com.clillo.dmx.core.show;

import com.clillo.dmx.configuracion.programas.TipoPrograma;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelSubMasterLista;

public class ShowPrograma {

	private TipoPrograma tipoPrograma;
	private PanelSubMasterLista panelSubMasterLista;
	private String id;
	
	public ShowPrograma(String tipo, String id) {
		super();
		this.id = id;
	//	System.out.println(tipo+"\t"+id);
		tipoPrograma = TipoPrograma.getTipoPorNombre(tipo);
		panelSubMasterLista = tipoPrograma.getPanelSubMasterLista();
	//	System.out.println(panelSubMasterLista);
	}

	public void ejecuta(){
		panelSubMasterLista.seleccionaProgramaPorId(id);
	}
}
