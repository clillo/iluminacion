package com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelSubMasterLista;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelTipoCambioAutomatico;

public class PanelEjecutorProgramasRGBW extends JPanel implements InterfacePanelEjecutorProgramas{

	private static final long serialVersionUID = -6134458468749746721L;

	private ObjetosCompartidosRGBW objCompartidos;

	public PanelEjecutorProgramasRGBW(ObjetosCompartidosRGBW objCompartidos) {
		this.objCompartidos = objCompartidos;
	  	setLayout(null);

	  	objCompartidos.setPnlColor(new PanelSubMasterLista());
 
	  	JScrollPane scrlPanelColor = new JScrollPane(objCompartidos.getPnlColor());
	  	scrlPanelColor.setBounds(10, 11, 455, 460);
	  	add(scrlPanelColor);

	  	objCompartidos.setPnlDimmer(new PanelSubMasterLista());
	  	
	  	JScrollPane scrlPanelDimmer = new JScrollPane(objCompartidos.getPnlDimmer());
	  	scrlPanelDimmer.setBounds(475, 11, 460, 254);
	  	add(scrlPanelDimmer);
	  	
	    objCompartidos.setPnlStrobo(new PanelSubMasterLista());

	  	JScrollPane scrlPanelStrobo = new JScrollPane(objCompartidos.getPnlStrobo());
	  	scrlPanelStrobo.setBounds(475, 330, 460, 141);
	  	add(scrlPanelStrobo);

	  	objCompartidos.setCambioAutomaticoColor(new PanelTipoCambioAutomatico());
	  	objCompartidos.getCambioAutomaticoColor().setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	objCompartidos.getCambioAutomaticoColor().setBounds(10, 482, 455, 46);
	  	add(objCompartidos.getCambioAutomaticoColor());
	    			  	
	  	objCompartidos.setCambioAutomaticoDimmer(new PanelTipoCambioAutomatico());
	  	objCompartidos.getCambioAutomaticoDimmer().setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	objCompartidos.getCambioAutomaticoDimmer().setBounds(475, 276, 460, 46);
	  	objCompartidos.getCambioAutomaticoDimmer().setPrimerProgramaApagado(true);
	  	add(objCompartidos.getCambioAutomaticoDimmer());

	  	objCompartidos.setCambioAutomaticoStrobo(new PanelTipoCambioAutomatico());
	  	objCompartidos.getCambioAutomaticoStrobo().setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	objCompartidos.getCambioAutomaticoStrobo().setBounds(475, 482, 460, 46);
	  	add(objCompartidos.getCambioAutomaticoStrobo());
	  	
	  	objCompartidos.getPnlColor().setup(objCompartidos.getConjuntoProgramasColor());
		objCompartidos.getCambioAutomaticoColor().setup(objCompartidos.getPnlColor(), objCompartidos.getConjuntoProgramasColor());

		objCompartidos.getPnlDimmer().setup(objCompartidos.getConjuntoProgramasDimmer());
		objCompartidos.getCambioAutomaticoDimmer().setup(objCompartidos.getPnlDimmer(), objCompartidos.getConjuntoProgramasDimmer());
	  	
		objCompartidos.getPnlStrobo().setup(objCompartidos.getConjuntoProgramasStrobo());
	  	objCompartidos.getCambioAutomaticoStrobo().setup(objCompartidos.getPnlStrobo(), objCompartidos.getConjuntoProgramasStrobo());
	}
	
	@Override
	public void setCambioAutomatico(boolean habilitado) {
		if (!habilitado){
			objCompartidos.getCambioAutomaticoColor().apagaEfectos();
			objCompartidos.getCambioAutomaticoDimmer().apagaEfectos(); 
			objCompartidos.getCambioAutomaticoStrobo().apagaEfectos();
		}else{
			objCompartidos.getCambioAutomaticoColor().enciendeAutomatico();
			objCompartidos.getCambioAutomaticoDimmer().enciendeAutomatico(); 
			objCompartidos.getCambioAutomaticoStrobo().enciendeAutomatico();
		}
	}

	@Override
	public void apagar() {
		objCompartidos.getCambioAutomaticoColor().apagaEfectos();
		objCompartidos.getCambioAutomaticoDimmer().apagaEfectos(); 
		objCompartidos.getCambioAutomaticoStrobo().apagaEfectos();
	}

	@Override
	public void setCambioAutomatico(boolean habilitado, int idSecundario) {
	}

	@Override
	public void activaVDJ() {
		objCompartidos.getCambioAutomaticoColor().activaVDJ();
		objCompartidos.getCambioAutomaticoDimmer().activaVDJ(); 
		objCompartidos.getCambioAutomaticoStrobo().activaVDJ();
	}
}
