package com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.clillo.dmx.configuracion.programas.PasoTO;
import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.configuracion.programas.TipoGatillador;
import com.clillo.dmx.configuracion.programas.TipoPrograma;
import com.clillo.dmx.configuracion.programas.to.PasoProgramaTO;
import com.clillo.dmx.programas.ConjuntoProgramas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelSubMasterLista;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelTipoCambioAutomatico;

public class PanelEjecutorProgramasRGBWConjuntos extends JPanel implements InterfacePanelEjecutorProgramas{

	private static final long serialVersionUID = -6134458468749746721L;

	private PanelTipoCambioAutomatico cambioAutomatico;
	private ConjuntoProgramas conjuntoProgramas = new ConjuntoProgramas(TipoPrograma.RGBWConjunto);

	
	public PanelEjecutorProgramasRGBWConjuntos(ObjetosCompartidosRGBW objCompartidos) {
	  	setLayout(null);

	  	PanelSubMasterLista panel = new PanelSubMasterLista();
 
	  	JScrollPane scrlPanelColor = new JScrollPane(panel);
	  	scrlPanelColor.setBounds(10, 11, 718, 460);
	  	add(scrlPanelColor);

	  	cambioAutomatico = new PanelTipoCambioAutomatico();
	  	cambioAutomatico.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomatico.setBounds(10, 482, 455, 46);
	  	add(cambioAutomatico);
	  	
	  	panel.setTamañoWide(true);
		panel.setup(conjuntoProgramas);
	  	cambioAutomatico.setup(panel, conjuntoProgramas);
		cambioAutomatico.setPrimerProgramaApagado(true);
		
	  	for (ProgramaTO prog: conjuntoProgramas.getListaProgramas()){
	  		prog.setProgramasDelegados(new ArrayList<ProgramaTO>());
	  		prog.setTipoGatillador(TipoGatillador.RelojExterno);
	  		if (prog.getNombre().contains("Ningun Programa Seleccionado"))
	  			prog.setNingunProgramaSeleccionado(true);
	  		for (PasoTO paso: prog.getListaPasos())
	  			if(paso.getListaProgramas()!=null)
		  			for (PasoProgramaTO ppto: paso.getListaProgramas()){
		  				if (ppto.getPrograma() != null)
		  					prog.getProgramasDelegados().add(ppto.getPrograma());
		  				
		  				if(ppto.getListaProgramas()!=null)
		  					for(ProgramaTO p: ppto.getListaProgramas())
		  						prog.getProgramasDelegados().add(p);
		  				
		  				if (ppto.getTipo() == TipoPrograma.RGBWColor)
		  					ppto.setPanelCambio(objCompartidos.getPnlColor());
		  				if (ppto.getTipo() == TipoPrograma.RGBWDimmer)
		  					ppto.setPanelCambio(objCompartidos.getPnlDimmer());
		  				if (ppto.getTipo() == TipoPrograma.RGBWStrobo)
		  					ppto.setPanelCambio(objCompartidos.getPnlStrobo());
		  			}
	  	}
	  				
	}
	
	@Override
	public void setCambioAutomatico(boolean habilitado) {
		if (!habilitado){
			cambioAutomatico.apagaEfectos();
		}else{
			cambioAutomatico.enciendeAutomatico();
		}
	}

	@Override
	public void apagar() {
		cambioAutomatico.apagaEfectos();
	}

	@Override
	public void setCambioAutomatico(boolean habilitado, int idSecundario) {
	}

	@Override
	public void activaVDJ() {
		cambioAutomatico.activaVDJ();
	}
}
