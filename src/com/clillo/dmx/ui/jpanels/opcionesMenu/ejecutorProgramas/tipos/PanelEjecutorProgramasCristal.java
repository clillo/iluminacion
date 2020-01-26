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

public class PanelEjecutorProgramasCristal extends JPanel implements InterfacePanelEjecutorProgramas{

	private static final long serialVersionUID = -6134458468749746721L;
	
	private PanelTipoCambioAutomatico cambioAutomaticoBall1;
	
	private PanelSubMasterLista pnlBall1;
	
	private ConjuntoProgramas conjuntoProgramasBall1 = new ConjuntoProgramas(TipoPrograma.CrystalBall1);
	
	public PanelEjecutorProgramasCristal() {
	  	setLayout(null);

	  	pnlBall1 = new PanelSubMasterLista();

	  	JScrollPane scrlCrystalMagic1 = new JScrollPane(pnlBall1);
	  	scrlCrystalMagic1.setBounds(10, 28, 460, 378);
	  	add(scrlCrystalMagic1);

	  	cambioAutomaticoBall1 = new PanelTipoCambioAutomatico();
	  	cambioAutomaticoBall1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomaticoBall1.setBounds(10, 417, 460, 46);
	  	cambioAutomaticoBall1.setPrimerProgramaApagado(true);
	  	add(cambioAutomaticoBall1);
	  	
		pnlBall1.setup(conjuntoProgramasBall1);
	  	cambioAutomaticoBall1.setup(pnlBall1, conjuntoProgramasBall1);
	  	
		JLabel lblCrystalMagic1 = new JLabel("Crystal Ball 1:");
		lblCrystalMagic1.setBounds(10, 11, 83, 14);
		add(lblCrystalMagic1);
	}
		
	@Override
	public void setCambioAutomatico(boolean habilitado) {
		if (!habilitado){
			cambioAutomaticoBall1.apagaEfectos();

		}else{
			cambioAutomaticoBall1.enciendeAutomatico();

		}
	}

	@Override
	public void apagar() {
		cambioAutomaticoBall1.apagaEfectos();
	}

	@Override
	public void setCambioAutomatico(boolean habilitado, int idSecundario) {
		
	}

	@Override
	public void activaVDJ() {
	}
}
