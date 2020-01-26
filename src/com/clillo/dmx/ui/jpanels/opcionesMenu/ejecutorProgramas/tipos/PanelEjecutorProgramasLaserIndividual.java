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

public class PanelEjecutorProgramasLaserIndividual extends JPanel implements InterfacePanelEjecutorProgramas{

	private static final long serialVersionUID = -6134458468749746721L;
	
	private PanelTipoCambioAutomatico cambioAutomaticoLaser1Efectos;
	private PanelTipoCambioAutomatico cambioAutomaticoLaser2Efectos;
	private PanelTipoCambioAutomatico cambioAutomaticoSpider1Efectos;

	private PanelSubMasterLista pnlLaser1Efectos;
	private PanelSubMasterLista pnlLaser2Efectos;
	private PanelSubMasterLista pnlSpider1fectos;
	
	private ConjuntoProgramas conjuntoProgramasLaserMultiPunto1 = new ConjuntoProgramas(TipoPrograma.LaserRGBPatron);
	private ConjuntoProgramas conjuntoProgramasSpider1 = new ConjuntoProgramas(TipoPrograma.Spider1);
	private ConjuntoProgramas conjuntoProgramasLaserIlda1 = new ConjuntoProgramas(TipoPrograma.LaserIlda1);
	
	public PanelEjecutorProgramasLaserIndividual() {
	  	setLayout(null);
	  	pnlLaser1Efectos = new PanelSubMasterLista();
	  	pnlLaser2Efectos = new PanelSubMasterLista();
	  	pnlSpider1fectos = new PanelSubMasterLista();

	  	JScrollPane scrlLaser1Efectos = new JScrollPane(pnlLaser1Efectos);
	  	scrlLaser1Efectos.setBounds(10, 36, 460, 132);
	  	add(scrlLaser1Efectos);

	  	JScrollPane scrlLaser2Efectos = new JScrollPane(pnlLaser2Efectos);
	  	scrlLaser2Efectos.setBounds(480, 36, 460, 431);
	  	add(scrlLaser2Efectos);

	  	JScrollPane scrlSpiderEfectos = new JScrollPane(pnlSpider1fectos);
	  	scrlSpiderEfectos.setBounds(10, 267, 460, 210);
	  	add(scrlSpiderEfectos);

	  	cambioAutomaticoLaser1Efectos= new PanelTipoCambioAutomatico();
	  	cambioAutomaticoLaser1Efectos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomaticoLaser1Efectos.setBounds(10, 179, 460, 46);
	  	add(cambioAutomaticoLaser1Efectos);
	  	
	  	cambioAutomaticoLaser2Efectos = new PanelTipoCambioAutomatico();
	  	cambioAutomaticoLaser2Efectos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomaticoLaser2Efectos.setBounds(480, 484, 460, 46);
	  	add(cambioAutomaticoLaser2Efectos);

	  	cambioAutomaticoSpider1Efectos = new PanelTipoCambioAutomatico();
	  	cambioAutomaticoSpider1Efectos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomaticoSpider1Efectos.setBounds(10, 484, 460, 46);
	  	add(cambioAutomaticoSpider1Efectos);

		pnlLaser1Efectos.setup(conjuntoProgramasLaserMultiPunto1);
	  	cambioAutomaticoLaser1Efectos.setup(pnlLaser1Efectos, conjuntoProgramasLaserMultiPunto1);
	  	cambioAutomaticoLaser1Efectos.setPrimerProgramaApagado(true);

		pnlLaser2Efectos.setup(conjuntoProgramasLaserIlda1);
	  	cambioAutomaticoLaser2Efectos.setup(pnlLaser2Efectos, conjuntoProgramasLaserIlda1);
	  	cambioAutomaticoLaser2Efectos.setPrimerProgramaApagado(true);

	  	pnlSpider1fectos.setup(conjuntoProgramasSpider1);
		cambioAutomaticoSpider1Efectos.setup(pnlSpider1fectos, conjuntoProgramasSpider1);
		cambioAutomaticoSpider1Efectos.setPrimerProgramaApagado(true);

		JLabel lblLaserMultiPunto = new JLabel("L\u00E1ser MultiPunto");
		lblLaserMultiPunto.setBounds(10, 11, 121, 14);
		add(lblLaserMultiPunto);

		JLabel lblCrystalMagic1 = new JLabel("Spider 1");
		lblCrystalMagic1.setBounds(10, 242, 121, 14);
		add(lblCrystalMagic1);
		
		JLabel lblCrystalMagic2 = new JLabel("L\u00E1ser Ilda 1");
		lblCrystalMagic2.setBounds(480, 11, 104, 14);
		add(lblCrystalMagic2);
	}
		
	@Override
	public void setCambioAutomatico(boolean habilitado) {
		if (!habilitado){
			cambioAutomaticoLaser1Efectos.apagaEfectos();
			cambioAutomaticoLaser2Efectos.apagaEfectos();
			cambioAutomaticoSpider1Efectos.apagaEfectos();
		}else{
			cambioAutomaticoLaser1Efectos.enciendeAutomatico();
			cambioAutomaticoLaser2Efectos.enciendeAutomatico();
			cambioAutomaticoSpider1Efectos.enciendeAutomatico();
		}
	}

	@Override
	public void apagar() {
		cambioAutomaticoLaser1Efectos.apagaEfectos();
		cambioAutomaticoLaser2Efectos.apagaEfectos();
		cambioAutomaticoSpider1Efectos.apagaEfectos();
	}

	@Override
	public void setCambioAutomatico(boolean habilitado, int idSecundario) {
		if (!habilitado){
			if (idSecundario==1)
				cambioAutomaticoLaser1Efectos.apagaEfectos();
			if (idSecundario==2)
				cambioAutomaticoLaser2Efectos.apagaEfectos();
			if (idSecundario==3)
				cambioAutomaticoSpider1Efectos.apagaEfectos();
		}else{
			if (idSecundario==1)
				cambioAutomaticoLaser1Efectos.enciendeAutomatico();
			if (idSecundario==2)
				cambioAutomaticoLaser2Efectos.enciendeAutomatico();
			if (idSecundario==3)
				cambioAutomaticoSpider1Efectos.enciendeAutomatico();
		}
	}

	@Override
	public void activaVDJ() {
	}
	
}
