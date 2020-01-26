package com.clillo.dmx.ui.jpanels.opcionesMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;

import com.clillo.dmx.configuracion.programas.TipoPrograma;
import com.clillo.dmx.programas.ConjuntoProgramas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.puntosRobotizados.PanelListaPuntosRobotizados;


public class PanelMantienePuntosRobotizados extends PanelMenuGenerico implements ActionListener{

	private static final long serialVersionUID = -5869553409971473557L;

	private JButton btnPuntosMovingHeads; 
	private JButton btnPuntosScanners;
	
	private ConjuntoProgramas conjuntoProgramasPosicionesScanner = new ConjuntoProgramas(TipoPrograma.ScannerPosicion);
	private ConjuntoProgramas conjuntoProgramasPosicionesMovingHead = new ConjuntoProgramas(TipoPrograma.MovingHeadPosicion);

	public PanelMantienePuntosRobotizados() {
	    this.configura(440, 440, "Mantiene Puntos Robotizados");
	  	this.setLayout(null);
		
	  	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	  	tabbedPane.setBounds(10, 11, 430, 278);
	  	add(tabbedPane);
	  	
	  	PanelListaPuntosRobotizados listaScanners = new PanelListaPuntosRobotizados();
	  	listaScanners.setup(conjuntoProgramasPosicionesScanner);
	  	tabbedPane.addTab("Scanners", null, listaScanners, null);

	  	PanelListaPuntosRobotizados listaMovingHeads = new PanelListaPuntosRobotizados();
	  	listaMovingHeads.setup(conjuntoProgramasPosicionesMovingHead);
	  	tabbedPane.addTab("Moving Heads", null, listaMovingHeads, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnPuntosMovingHeads))
			;

		if (e.getSource().equals(btnPuntosScanners))
			;
	}
}
