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

public class PanelEjecutorProgramasLaser extends JPanel implements InterfacePanelEjecutorProgramas{

	private static final long serialVersionUID = -6134458468749746721L;
	
	private PanelTipoCambioAutomatico cambioAutomaticoLaser1Patron;
	private PanelTipoCambioAutomatico cambioAutomaticoLaser1Color;
	
	private PanelTipoCambioAutomatico cambioAutomaticoLaser1Efectos;

	private PanelSubMasterLista pnlLaser1Patron;
	private PanelSubMasterLista pnlLaser1Color;
	private PanelSubMasterLista pnlLaser1Efectos;

	private ConjuntoProgramas conjuntoProgramasLaser1Patron = new ConjuntoProgramas(TipoPrograma.LaserRGBPatron);
	private ConjuntoProgramas conjuntoProgramasLaser1Color = new ConjuntoProgramas(TipoPrograma.LaserRGBColor);
	private ConjuntoProgramas conjuntoProgramasLaser1Efectos = new ConjuntoProgramas(TipoPrograma.LaserRGBEfectos);
	
	public PanelEjecutorProgramasLaser() {
	  	setLayout(null);

	  	pnlLaser1Patron = new PanelSubMasterLista();
	  	pnlLaser1Color = new PanelSubMasterLista();
	  	pnlLaser1Efectos = new PanelSubMasterLista();

	  	JScrollPane scrlLaser1Patron = new JScrollPane(pnlLaser1Patron);
	  	scrlLaser1Patron.setBounds(10, 28, 460, 103);
	  	add(scrlLaser1Patron);

	  	JScrollPane scrlLaser1Color = new JScrollPane(pnlLaser1Color);
	  	scrlLaser1Color.setBounds(10, 214, 460, 52);
	  	add(scrlLaser1Color);

	  	JScrollPane scrlLaser1Efectos = new JScrollPane(pnlLaser1Efectos);
	  	scrlLaser1Efectos.setBounds(10, 328, 460, 79);
	  	add(scrlLaser1Efectos);

	  	cambioAutomaticoLaser1Patron = new PanelTipoCambioAutomatico();
	  	cambioAutomaticoLaser1Patron.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomaticoLaser1Patron.setBounds(10, 142, 460, 46);
	  	cambioAutomaticoLaser1Patron.setPrimerProgramaApagado(true);
	  	add(cambioAutomaticoLaser1Patron);

	  	cambioAutomaticoLaser1Color = new PanelTipoCambioAutomatico();
	  	cambioAutomaticoLaser1Color.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomaticoLaser1Color.setBounds(10, 271, 460, 46);
	  	cambioAutomaticoLaser1Color.setPrimerProgramaApagado(true);
	  	add(cambioAutomaticoLaser1Color);
	  	
	  	cambioAutomaticoLaser1Efectos= new PanelTipoCambioAutomatico();
	  	cambioAutomaticoLaser1Efectos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	  	cambioAutomaticoLaser1Efectos.setBounds(10, 418, 460, 46);
	  	cambioAutomaticoLaser1Efectos.setPrimerProgramaApagado(true);
	  	add(cambioAutomaticoLaser1Efectos);
	  	
		pnlLaser1Patron.setup(conjuntoProgramasLaser1Patron);
	  	cambioAutomaticoLaser1Patron.setup(pnlLaser1Patron, conjuntoProgramasLaser1Patron);
	  	
		pnlLaser1Color.setup(conjuntoProgramasLaser1Color);
	  	cambioAutomaticoLaser1Color.setup(pnlLaser1Color, conjuntoProgramasLaser1Color);

		pnlLaser1Efectos.setup(conjuntoProgramasLaser1Efectos);
	  	cambioAutomaticoLaser1Efectos.setup(pnlLaser1Efectos, conjuntoProgramasLaser1Efectos);

		JLabel lblCrystalMagic1 = new JLabel("L\u00E1ser RGB Patr\u00F3n");
		lblCrystalMagic1.setBounds(10, 11, 121, 14);
		add(lblCrystalMagic1);
		
		JLabel lblDerby1 = new JLabel("L\u00E1ser RGB Color");
		lblDerby1.setBounds(10, 199, 121, 14);
		add(lblDerby1);
	}
		
	@Override
	public void setCambioAutomatico(boolean habilitado) {
		if (!habilitado){
			cambioAutomaticoLaser1Patron.apagaEfectos();
			cambioAutomaticoLaser1Color.apagaEfectos();
			cambioAutomaticoLaser1Efectos.apagaEfectos();
		}else{
			cambioAutomaticoLaser1Patron.enciendeAutomatico();
			cambioAutomaticoLaser1Color.enciendeAutomatico();
			cambioAutomaticoLaser1Efectos.enciendeAutomatico();
		}
	}

	@Override
	public void apagar() {
		cambioAutomaticoLaser1Patron.apagaEfectos();
		cambioAutomaticoLaser1Color.apagaEfectos();
		cambioAutomaticoLaser1Efectos.apagaEfectos();
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
