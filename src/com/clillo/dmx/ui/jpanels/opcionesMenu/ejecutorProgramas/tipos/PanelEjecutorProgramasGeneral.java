package com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.clillo.dmx.configuracion.programas.TipoPrograma;
import com.clillo.dmx.programas.ConjuntoProgramas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelSubMasterLista;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelTipoCambioAutomatico;

public class PanelEjecutorProgramasGeneral extends JPanel implements InterfacePanelEjecutorProgramas{

	private static final long serialVersionUID = -6134458468749746721L;

	private PanelTipoCambioAutomatico cambioAutomaticoPosiciones;

	private PanelSubMasterLista pnlPosiciones;
	
	private ConjuntoProgramas conjuntoProgramasPosiciones = new ConjuntoProgramas(TipoPrograma.Escenas);


	public PanelEjecutorProgramasGeneral() {
	  	setLayout(null);

	  	pnlPosiciones = new PanelSubMasterLista();

	  	JScrollPane scrlPosiciones = new JScrollPane(pnlPosiciones);
	  	scrlPosiciones.setBounds(10, 11, 460, 394);
	  	add(scrlPosiciones);

	  	cambioAutomaticoPosiciones = new PanelTipoCambioAutomatico();
	  	cambioAutomaticoPosiciones.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomaticoPosiciones.setBounds(10, 416, 460, 50);
	  	add(cambioAutomaticoPosiciones);

		pnlPosiciones.setup(conjuntoProgramasPosiciones);
	  	cambioAutomaticoPosiciones.setup(pnlPosiciones, conjuntoProgramasPosiciones);
	  	
	  	JPanel panel = new JPanel();
	  	panel.setBorder(new TitledBorder(null, "Shortcuts", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	panel.setBounds(480, 11, 460, 394);
	  	add(panel);
	}

	@Override
	public void setCambioAutomatico(boolean habilitado) {
		if (!habilitado){
			cambioAutomaticoPosiciones.apagaEfectos();
		}else{
			cambioAutomaticoPosiciones.enciendeAutomatico();
		}
	}

	@Override
	public void apagar() {
		cambioAutomaticoPosiciones.apagaEfectos();
	}

	@Override
	public void setCambioAutomatico(boolean habilitado, int idSecundario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activaVDJ() {
		// TODO Auto-generated method stub
		
	}
}
