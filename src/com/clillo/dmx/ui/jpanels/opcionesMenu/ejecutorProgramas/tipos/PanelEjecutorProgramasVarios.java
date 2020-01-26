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

public class PanelEjecutorProgramasVarios extends JPanel implements InterfacePanelEjecutorProgramas{

	private static final long serialVersionUID = -6134458468749746721L;

	public enum FixtureVarios {StageLight}
	private PanelTipoCambioAutomatico cambioAutomaticoStageLight;
	private PanelSubMasterLista pnlStageLight;
	
	private ConjuntoProgramas conjuntoProgramasStageLigth = new ConjuntoProgramas(TipoPrograma.RGBStageLight);
	private JLabel lblStageLight;

	public PanelEjecutorProgramasVarios() {
	  	setLayout(null);
	  	pnlStageLight = new PanelSubMasterLista();

		JScrollPane scrlStagelight = new JScrollPane(pnlStageLight);
		scrlStagelight.setBounds(10, 189, 460, 64);
		add(scrlStagelight);

	  	cambioAutomaticoStageLight = new PanelTipoCambioAutomatico();
	  	cambioAutomaticoStageLight.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomaticoStageLight.setBounds(10, 264, 460, 46);
	  	add(cambioAutomaticoStageLight);
		
		pnlStageLight.setup(conjuntoProgramasStageLigth);
		cambioAutomaticoStageLight.setup(pnlStageLight, conjuntoProgramasStageLigth);
		
		lblStageLight = new JLabel("Stage Light:");
		lblStageLight.setBounds(10, 164, 83, 14);
		add(lblStageLight);
	}
		
	@Override
	public void setCambioAutomatico(boolean habilitado) {
		if (!habilitado){
			cambioAutomaticoStageLight.apagaEfectos();
		}else{
			cambioAutomaticoStageLight.enciendeAutomatico();
		}
	}

	@Override
	public void apagar() {
		cambioAutomaticoStageLight.apagaEfectos();
	}
	
	public void automatico(FixtureVarios cual, boolean valor){
		if (!valor){
			if (cual==FixtureVarios.StageLight)
				cambioAutomaticoStageLight.apagaEfectos();
			
		}else{			
			if (cual==FixtureVarios.StageLight)
				cambioAutomaticoStageLight.enciendeAutomatico();
			
		}
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
