package com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.tipos;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.clillo.dmx.configuracion.programas.TipoPrograma;
import com.clillo.dmx.programas.ConjuntoProgramas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelSubMasterLista;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecutorProgramas.PanelTipoCambioAutomatico;

public class PanelEjecutorProgramasDerby extends JPanel implements InterfacePanelEjecutorProgramas{

	private static final long serialVersionUID = -6134458468749746721L;
	private PanelTipoCambioAutomatico cambioAutomaticoDerby2;
	private PanelSubMasterLista pnlDerby2;
	
	private ConjuntoProgramas conjuntoProgramasDerby2 = new ConjuntoProgramas(TipoPrograma.Derby2);
	
	public PanelEjecutorProgramasDerby() {
	  	setLayout(null);
	  	pnlDerby2 = new PanelSubMasterLista();

	  	JScrollPane scrlDerby2 = new JScrollPane(pnlDerby2);
	  	scrlDerby2.setBounds(10, 33, 487, 458);
	  	add(scrlDerby2);

	  	cambioAutomaticoDerby2 = new PanelTipoCambioAutomatico();
	  	cambioAutomaticoDerby2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomaticoDerby2.setBounds(10, 502, 460, 46);
	  	add(cambioAutomaticoDerby2);
	  	cambioAutomaticoDerby2.setPrimerProgramaApagado(true);

		pnlDerby2.setup(conjuntoProgramasDerby2);
	  	cambioAutomaticoDerby2.setup(pnlDerby2, conjuntoProgramasDerby2);
	
		JLabel lblDerby1 = new JLabel("Derbys  Conjuntos:");
		lblDerby1.setBounds(10, 11, 141, 14);
		add(lblDerby1);
	}
		
	@Override
	public void setCambioAutomatico(boolean habilitado) {
		if (!habilitado){
			cambioAutomaticoDerby2.apagaEfectos();
		}else{
			cambioAutomaticoDerby2.enciendeAutomatico();
		}
	}

	@Override
	public void apagar() {
		cambioAutomaticoDerby2.apagaEfectos();
	}

	@Override
	public void setCambioAutomatico(boolean habilitado, int idSecundario) {
		if (!habilitado){
			cambioAutomaticoDerby2.apagaEfectos();
		}else{
			cambioAutomaticoDerby2.enciendeAutomatico();
		}		
	}

	@Override
	public void activaVDJ() {
	}	
}
