package com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.clillo.dmx.configuracion.programas.TipoPrograma;
import com.clillo.dmx.programas.ConjuntoProgramas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelSubMasterLista;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelTipoCambioAutomatico;

public class PanelEjecutorProgramasPinSpot extends JPanel implements InterfacePanelEjecutorProgramas{

	private static final long serialVersionUID = -6134458468749746721L;

	private PanelTipoCambioAutomatico cambioAutomatico1;
	private PanelTipoCambioAutomatico cambioAutomatico2; 
	private PanelSubMasterLista pnlLista1;
	private PanelSubMasterLista pnlLista2;
	
	private ConjuntoProgramas conjuntoProgramasColor = new ConjuntoProgramas(TipoPrograma.PinSpotColor);
	private ConjuntoProgramas conjuntoProgramasDimmer = new ConjuntoProgramas(TipoPrograma.PinSpotDimmer);

	public PanelEjecutorProgramasPinSpot() {
	  	setLayout(null);

	  	pnlLista1 = new PanelSubMasterLista();
 
	  	JScrollPane scrlPanel1 = new JScrollPane(pnlLista1);
	  	scrlPanel1.setBounds(10, 11, 460, 364);
	  	add(scrlPanel1);

	  	pnlLista2 = new PanelSubMasterLista();
	  	
	  	JScrollPane scrlPanel2 = new JScrollPane(pnlLista2);
	  	scrlPanel2.setBounds(477, 11, 460, 364);
	  	add(scrlPanel2);
	  	
	  	cambioAutomatico1 = new PanelTipoCambioAutomatico();
	  	cambioAutomatico1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomatico1.setBounds(10, 383, 460, 48);
	  	add(cambioAutomatico1);
	    		
	  	
	  	cambioAutomatico2 = new PanelTipoCambioAutomatico();
	  	cambioAutomatico2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomatico2.setBounds(480, 383, 457, 48);
	  	cambioAutomatico2.setPrimerProgramaApagado(true);
	  	add(cambioAutomatico2);

 		pnlLista1.setup(conjuntoProgramasColor);
	  	cambioAutomatico1.setup(pnlLista1, conjuntoProgramasColor);

	  	pnlLista2.setup(conjuntoProgramasDimmer);
	  	cambioAutomatico2.setup(pnlLista2, conjuntoProgramasDimmer);
	}

	@Override
	public void setCambioAutomatico(boolean habilitado) {
		if (!habilitado){
			cambioAutomatico1.apagaEfectos();
			cambioAutomatico2.apagaEfectos(); 
		}else{
			cambioAutomatico1.enciendeAutomatico();
			cambioAutomatico2.enciendeAutomatico(); 
		}
	}

	@Override
	public void apagar() {
		cambioAutomatico1.apagaEfectos();
		cambioAutomatico2.apagaEfectos(); 
	}

	@Override
	public void setCambioAutomatico(boolean habilitado, int idSecundario) {
	}

	@Override
	public void activaVDJ() {
	}
}
