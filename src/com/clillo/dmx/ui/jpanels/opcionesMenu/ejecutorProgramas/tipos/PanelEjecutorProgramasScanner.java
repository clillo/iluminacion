package com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.clillo.dmx.configuracion.programas.TipoPrograma;
import com.clillo.dmx.programas.ConjuntoProgramas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelSubMasterLista;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelTipoCambioAutomatico;

public class PanelEjecutorProgramasScanner extends JPanel implements InterfacePanelEjecutorProgramas{

	private static final long serialVersionUID = -6134458468749746721L;

	private PanelTipoCambioAutomatico cambioAutomaticoPosiciones;
	private PanelTipoCambioAutomatico cambioAutomaticoColor; 
	private PanelTipoCambioAutomatico cambioAutomaticoDimmer; 
	private PanelTipoCambioAutomatico cambioAutomaticoGobos; 
	private PanelTipoCambioAutomatico cambioAutomaticoStrobo; 

	private PanelSubMasterLista pnlPosiciones;
	private PanelSubMasterLista pnlColor;
	private PanelSubMasterLista pnlDimmer;
	private PanelSubMasterLista pnlGobos;
	private PanelSubMasterLista pnlStrobo;
	
	private ConjuntoProgramas conjuntoProgramasPosiciones = new ConjuntoProgramas(TipoPrograma.MovingHeadBPPosicion);
	private ConjuntoProgramas conjuntoProgramasColor = new ConjuntoProgramas(TipoPrograma.MovingHeadBPColor);
	private ConjuntoProgramas conjuntoProgramasDimmer = new ConjuntoProgramas(TipoPrograma.MovingHeadBPDimmer);
	private ConjuntoProgramas conjuntoProgramasGobos = new ConjuntoProgramas(TipoPrograma.MovingHeadBPGobos);
	private ConjuntoProgramas conjuntoProgramasStrobo = new ConjuntoProgramas(TipoPrograma.MovingHeadBPStrobo);
	
	public PanelEjecutorProgramasScanner() {
	  	setLayout(null);

	  	pnlPosiciones = new PanelSubMasterLista();
	  	pnlColor = new PanelSubMasterLista();
	  	pnlDimmer = new PanelSubMasterLista();
	  	pnlGobos = new PanelSubMasterLista();
	  	pnlStrobo = new PanelSubMasterLista();

	  	JScrollPane scrlPosiciones = new JScrollPane(pnlPosiciones);
	  	scrlPosiciones.setBounds(10, 11, 460, 95);
	  	add(scrlPosiciones);

	  	JScrollPane scrlColor = new JScrollPane(pnlColor);
	  	scrlColor.setBounds(10, 173, 460, 96);
	  	add(scrlColor);
	  	
	  	JScrollPane scrlDimmer = new JScrollPane(pnlDimmer);
	  	scrlDimmer.setBounds(480, 252, 460, 153);
	  	add(scrlDimmer);

	  	JScrollPane scrlGobos = new JScrollPane(pnlGobos);
	  	scrlGobos.setBounds(480, 11, 460, 174);
	  	add(scrlGobos);

	  	JScrollPane scrlStrobo = new JScrollPane(pnlStrobo);
	  	scrlStrobo.setBounds(10, 341, 460, 64);
	  	add(scrlStrobo);

	  	cambioAutomaticoPosiciones = new PanelTipoCambioAutomatico();
	  	cambioAutomaticoPosiciones.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomaticoPosiciones.setBounds(10, 117, 460, 50);
	  	add(cambioAutomaticoPosiciones);
	    			  	
	  	cambioAutomaticoColor = new PanelTipoCambioAutomatico();
	  	cambioAutomaticoColor.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomaticoColor.setBounds(10, 280, 460, 50);
	  	add(cambioAutomaticoColor);
	  		  	
	  	cambioAutomaticoDimmer = new PanelTipoCambioAutomatico();
	  	cambioAutomaticoDimmer.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomaticoDimmer.setBounds(480, 416, 460, 50);
	  	cambioAutomaticoDimmer.setPrimerProgramaApagado(true);
	  	add(cambioAutomaticoDimmer);	  	

	  	cambioAutomaticoGobos = new PanelTipoCambioAutomatico();
	  	cambioAutomaticoGobos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomaticoGobos.setBounds(480, 196, 460, 50);
	  	add(cambioAutomaticoGobos);
	  	
	  	cambioAutomaticoStrobo = new PanelTipoCambioAutomatico();
	  	cambioAutomaticoStrobo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomaticoStrobo.setBounds(10, 416, 460, 50);
	  	add(cambioAutomaticoStrobo);

		pnlPosiciones.setup(conjuntoProgramasPosiciones);
	  	cambioAutomaticoPosiciones.setup(pnlPosiciones, conjuntoProgramasPosiciones);

	  	pnlColor.setup(conjuntoProgramasColor);
	  	cambioAutomaticoColor.setup(pnlColor, conjuntoProgramasColor);

	  	pnlDimmer.setup(conjuntoProgramasDimmer);
	  	cambioAutomaticoDimmer.setup(pnlDimmer, conjuntoProgramasDimmer);
	  	
	  	pnlGobos.setup(conjuntoProgramasGobos);
	  	cambioAutomaticoGobos.setup(pnlGobos, conjuntoProgramasGobos);

	  	pnlStrobo.setup(conjuntoProgramasStrobo);
	  	cambioAutomaticoStrobo.setup(pnlStrobo, conjuntoProgramasStrobo);
	}

	@Override
	public void setCambioAutomatico(boolean habilitado) {
		if (!habilitado){
			cambioAutomaticoPosiciones.apagaEfectos();
			cambioAutomaticoColor.apagaEfectos(); 
			cambioAutomaticoDimmer.apagaEfectos(); 
			cambioAutomaticoGobos.apagaEfectos();
			cambioAutomaticoStrobo.apagaEfectos();
		}else{
			cambioAutomaticoPosiciones.enciendeAutomatico();
			cambioAutomaticoColor.enciendeAutomatico(); 
			cambioAutomaticoDimmer.enciendeAutomatico(); 
			cambioAutomaticoGobos.enciendeAutomatico();
			cambioAutomaticoStrobo.enciendeAutomatico();
		}
	}

	@Override
	public void apagar() {
		cambioAutomaticoPosiciones.apagaEfectos();
		cambioAutomaticoColor.apagaEfectos(); 
		cambioAutomaticoDimmer.apagaEfectos(); 
		cambioAutomaticoGobos.apagaEfectos();
		cambioAutomaticoStrobo.apagaEfectos();
	}
	
	@Override
	public void setCambioAutomatico(boolean habilitado, int idSecundario) {
	}

	@Override
	public void activaVDJ() {
	}
}
