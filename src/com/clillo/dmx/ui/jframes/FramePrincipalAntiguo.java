package com.clillo.dmx.ui.jframes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.util.HashMap;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.clillo.dmx.comm.ControlRele;
import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.configuracion.VentanasCfg;
import com.clillo.dmx.configuracion.fixtures.RoboticasConfig;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelConfiguracion;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelControlRobotizados;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelDerbyRGBW;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelDerbyRGBW2;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelEjecutorProgramas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelEscenas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelHumoDMX;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelLaserRGB1;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelLaserRGB2;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMantienePuntosMovingHeads;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMantienePuntosScanners;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMonitorFixtures;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelRGBStageLight;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelReles;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelSnifferDMX;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelStageLight;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecucionAutomatica.PanelEjecucionAutomatica;
import com.clillo.dmx.ui.jpanels.opcionesMenu.show.PanelShowAmbientes;
import com.clillo.dmx.ui.jpanels.opcionesMenu.show.PanelShowEjecuta;

public class FramePrincipalAntiguo extends JFrame implements ActionListener {

	private static final long serialVersionUID = -1005621383743741880L;
	private static JDesktopPane desktop;

	private JMenuItem mitSalir;
	
	private JMenuItem mitCristalBall1;
	private JMenuItem mitCristalBall2;
	private JMenuItem mitDerbyRGB;
	private JMenuItem mitDerbyRGBW2;
	private JMenuItem mitConfiguracion;
	private JMenuItem mitShowEjecuta;
	private JMenuItem mitShowAmbientes;

	private JMenuItem mitMonitorFixtures;
	private JMenuItem mitControlMovingHeads;
	private JMenuItem mitSnifferDMX;	
	private JMenuItem mitMovingHead1;
	private JMenuItem mitMovingHead2;
	private JMenuItem mitScanner1;
	private JMenuItem mitScanner2;
	private JMenuItem mitScanner3;
	private JMenuItem mitScanner4;
	private JMenuItem mitEjecutorProgramas;
	private JMenuItem mitEjecucionAutomatica;
	private JMenuItem mitEscenas;
	private JMenuItem mitStageLight;
	private JMenuItem mitRGBStageLight;
	private JMenuItem mitLaserRGB2;
	private JMenuItem mitLaserRGB1;
	private JMenuItem mitHumoDMX;
	private JMenuItem mitReles;

	private PanelDerbyRGBW pnlDerbyRGB;
	private PanelDerbyRGBW2 pnlDerbyRGBW2;
	private PanelMonitorFixtures pnlMonitorFixtures;
	private PanelEjecutorProgramas pnlEjecutorProgramas;
	private PanelConfiguracion pnlConfiguracion;
	private PanelControlRobotizados pnlControlRobotizados;
	private PanelSnifferDMX pnlSnifferDMX;
	private PanelEjecucionAutomatica pnlEjecucionAutomatica; 
	private PanelStageLight pnlStageLight; 
	private PanelRGBStageLight pnlRGBStageLight; 
	private PanelEscenas pnlEscenas;
	private PanelMantienePuntosMovingHeads pnlMovingHead1; 
	private PanelMantienePuntosMovingHeads pnlMovingHead2; 
	private PanelMantienePuntosScanners pnlScanner1; 
	private PanelMantienePuntosScanners pnlScanner2; 
	private PanelMantienePuntosScanners pnlScanner3; 
	private PanelMantienePuntosScanners pnlScanner4;
	private PanelLaserRGB2 pnlLaserRGB2;
	private PanelLaserRGB1 pnlLaserRGB1;
	private PanelHumoDMX pnlHumoDMX;
	private PanelShowEjecuta pnlShowEjecuta;
	private PanelShowAmbientes pnlShowAmientes;
	private PanelReles pnlReles;

	private FrameInterno fiCristalBall1;
	private FrameInterno fiCristalBall2;
	private FrameInterno fiDerbyRGB;
	private FrameInterno fiDerbyRGBW2;
	private FrameInterno fiMonitorFixtures;
	private FrameInterno fiEjecutorProgramas;
	private FrameInterno fiConfiguracion;
	private FrameInterno fiControlMovingHeads;
	private FrameInterno fiSnifferDMX;
	private FrameInterno fiEjecucionAutomatica;
	private FrameInterno fiEscenas;
	private FrameInterno fiStageLight;
	private FrameInterno fiRGBStageLight;
	private FrameInterno fiMovingHead1;
	private FrameInterno fiMovingHead2;
	private FrameInterno fiScanner1;
	private FrameInterno fiScanner2;
	private FrameInterno fiScanner3;
	private FrameInterno fiScanner4;
	private FrameInterno fiLaserRGB2;
	private FrameInterno fiLaserRGB1;
	private FrameInterno fiHumoDMX;
	private FrameInterno fiShowEjecuta;
	private FrameInterno fiShowAmbientes;
	private FrameInterno fiReles;

	private HashMap<String , JMenuItem> hMenus = new HashMap<String, JMenuItem>();
	private HashMap<String , FrameInterno> hFrames = new HashMap<String, FrameInterno>();

	public FramePrincipalAntiguo() {
		enableEvents(64L);
		initialize();
	}

	private void agregaMenuArchivo(JMenuBar menubar){
		JMenu menuArchivo = new JMenu("Archivo");
		menubar.add(menuArchivo);

		mitSalir = new JMenuItem("Salir");
		mitSalir.addActionListener(this);

		menuArchivo.add(mitSalir);
	}
	
	private void agregaMenuProgramas(JMenuBar menubar){
		JMenu menuProgramas = new JMenu("Programas");
		menubar.add(menuProgramas);
		
		mitEjecutorProgramas = new JMenuItem("Ejecutor de Programas");
		mitEjecutorProgramas.addActionListener(this);
		menuProgramas.add(mitEjecutorProgramas);
		
		menuProgramas.addSeparator();

		mitEjecucionAutomatica = new JMenuItem("Ejecutor Automático de Fixtures");
		mitEjecucionAutomatica.addActionListener(this);
		menuProgramas.add(mitEjecucionAutomatica);
		
		menuProgramas.addSeparator();

		mitEscenas = new JMenuItem("Ejecutor de Escenas");
		mitEscenas.addActionListener(this);
		menuProgramas.add(mitEscenas);
	}
	
	private void agregaMenuConfigura(JMenuBar menubar){
		JMenu menuConfigura = new JMenu("Configura");
		menubar.add(menuConfigura);
			
		mitConfiguracion = new JMenuItem("Configuracion General");
		mitConfiguracion.addActionListener(this);
		menuConfigura.add(mitConfiguracion);
		
		menubar.add(menuConfigura);
	}
	
	private void agregaMenuMonitorea(JMenuBar menubar){
		JMenu menuMonitorea = new JMenu("Monitorea");

		mitMonitorFixtures = new JMenuItem("Monitor Fixtures RGB");
		mitMonitorFixtures.addActionListener(this);
		menuMonitorea.add(mitMonitorFixtures);

		menuMonitorea.addSeparator();

		mitControlMovingHeads = new JMenuItem("Robotizados");
		mitControlMovingHeads.addActionListener(this);
		menuMonitorea.add(mitControlMovingHeads);

		menuMonitorea.addSeparator();

		mitCristalBall1 = new JMenuItem("Cristal Ball 1");
		mitCristalBall1.addActionListener(this);
		menuMonitorea.add(mitCristalBall1);

		mitCristalBall2 = new JMenuItem("Cristal Ball 2");
		mitCristalBall2.addActionListener(this);
		menuMonitorea.add(mitCristalBall2);

		menuMonitorea.addSeparator();
		mitDerbyRGB = new JMenuItem("Mini Derby RGBW");
		mitDerbyRGB.addActionListener(this);
		menuMonitorea.add(mitDerbyRGB);
		
		mitDerbyRGBW2 = new JMenuItem("Derby RGBW (2)");
		mitDerbyRGBW2.addActionListener(this);
		menuMonitorea.add(mitDerbyRGBW2);
		
		menuMonitorea.addSeparator();
		mitStageLight = new JMenuItem("Stage Ligth");
		mitStageLight.addActionListener(this);
		menuMonitorea.add(mitStageLight);

		mitRGBStageLight = new JMenuItem("RGB Stage Ligth");
		mitRGBStageLight.addActionListener(this);
		menuMonitorea.add(mitRGBStageLight);

		menuMonitorea.addSeparator();

		mitLaserRGB2 = new JMenuItem("Laser RGB2");
		mitLaserRGB2.addActionListener(this);
		menuMonitorea.add(mitLaserRGB2);

		mitLaserRGB1 = new JMenuItem("Laser RGB1");
		mitLaserRGB1.addActionListener(this);
		menuMonitorea.add(mitLaserRGB1);

		mitHumoDMX = new JMenuItem("Máquina Humo DMX");
		mitHumoDMX.addActionListener(this);
		menuMonitorea.add(mitHumoDMX);

		menuMonitorea.addSeparator();

		mitSnifferDMX = new JMenuItem("Sniffer DMX");
		mitSnifferDMX.addActionListener(this);
		menuMonitorea.add(mitSnifferDMX);

		menuMonitorea.addSeparator();

		mitMovingHead1 = new JMenuItem("Moving Head 1");
		mitMovingHead1.addActionListener(this);
		menuMonitorea.add(mitMovingHead1);

		mitMovingHead2 = new JMenuItem("Moving Head 2");
		mitMovingHead2.addActionListener(this);
		menuMonitorea.add(mitMovingHead2);

		menuMonitorea.addSeparator();

		mitScanner1 = new JMenuItem("Scanner 1");
		mitScanner1.addActionListener(this);
		menuMonitorea.add(mitScanner1);

		mitScanner2 = new JMenuItem("Scanner 2");
		mitScanner2.addActionListener(this);
		menuMonitorea.add(mitScanner2);

		mitScanner3 = new JMenuItem("Scanner 3");
		mitScanner3.addActionListener(this);
		menuMonitorea.add(mitScanner3);

		mitScanner4 = new JMenuItem("Scanner 4");
		mitScanner4.addActionListener(this);
		menuMonitorea.add(mitScanner4);

		menuMonitorea.addSeparator();

		mitReles = new JMenuItem("Relés");
		mitReles.addActionListener(this);
		menuMonitorea.add(mitReles);

		menubar.add(menuMonitorea);		
	}

	private void agregaMenuSow(JMenuBar menubar){
		JMenu menuShow = new JMenu("Show");
		menubar.add(menuShow);
			
		mitShowEjecuta = new JMenuItem("Ejecutor Show");
		mitShowEjecuta.addActionListener(this);
		menuShow.add(mitShowEjecuta);

		mitShowAmbientes = new JMenuItem("Ejecutor Show");
		mitShowAmbientes.addActionListener(this);
		menuShow.add(mitShowAmbientes);

		menubar.add(menuShow);
	}
	
	private void initialize() {
		this.setTitle("Controlador");
		this.setSize(new Dimension(900, 360));
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		this.setContentPane(jPanel);
		desktop = new JDesktopPane();
		jPanel.add(desktop);
			
		JMenuBar menubar = new JMenuBar();
		agregaMenuArchivo(menubar);
		agregaMenuConfigura(menubar);
		agregaMenuMonitorea(menubar);
		agregaMenuProgramas(menubar);
		agregaMenuSow(menubar);
		
		this.setJMenuBar(menubar);

		pnlDerbyRGB            = new PanelDerbyRGBW();
		pnlDerbyRGBW2          = new PanelDerbyRGBW2();
		pnlMonitorFixtures     = new PanelMonitorFixtures();
		pnlEjecutorProgramas   = new PanelEjecutorProgramas();
		pnlConfiguracion       = new PanelConfiguracion();
		pnlControlRobotizados  = new PanelControlRobotizados();
		pnlEjecucionAutomatica = new PanelEjecucionAutomatica();
		pnlEscenas             = new PanelEscenas();
		pnlStageLight          = new PanelStageLight();
		pnlRGBStageLight       = new PanelRGBStageLight();
		pnlMovingHead1         = new PanelMantienePuntosMovingHeads();
		pnlMovingHead2         = new PanelMantienePuntosMovingHeads();
		pnlScanner1            = new PanelMantienePuntosScanners();
		pnlScanner2            = new PanelMantienePuntosScanners();
		pnlScanner3            = new PanelMantienePuntosScanners();
		pnlScanner4            = new PanelMantienePuntosScanners();
		pnlLaserRGB2           = new PanelLaserRGB2();
		pnlLaserRGB1           = new PanelLaserRGB1();
		pnlHumoDMX             = new PanelHumoDMX();
		pnlShowEjecuta         = new PanelShowEjecuta();
		pnlShowAmientes        = new PanelShowAmbientes();
		pnlReles               = new PanelReles();
	
		pnlMovingHead1.setId("movingHead1");
		pnlMovingHead2.setId("movingHead2");
		pnlScanner1.setId("scanner1");
		pnlScanner2.setId("scanner2");
		pnlScanner3.setId("scanner3");
		pnlScanner4.setId("scanner4");

		hMenus.put("panelCristalBall1"        , mitCristalBall1);
		hMenus.put("panelCristalBall2"        , mitCristalBall2);
		hMenus.put("panelDerbyRGB"            , mitDerbyRGB);
		hMenus.put("panelDerbyRGBW2"          , mitDerbyRGBW2);
		hMenus.put("panelMonitorFixtures"     , mitMonitorFixtures);
		hMenus.put("panelEjecutorProgramas"   , mitEjecutorProgramas);
		hMenus.put("panelConfiguracion"       , mitConfiguracion);
		hMenus.put("panelControlMovingHeads"  , mitControlMovingHeads);
		hMenus.put("panelSnifferDMX"          , mitSnifferDMX);
		hMenus.put("panelEjecucionAutomatica" , mitEjecucionAutomatica);
		hMenus.put("panelStageLight" ,          mitStageLight);
		hMenus.put("panelRGBStageLight" ,       mitRGBStageLight);
		hMenus.put("panelEscenas",              mitEscenas);
		hMenus.put("panelMovingHead1",          mitMovingHead1);
		hMenus.put("panelMovingHead2",          mitMovingHead2);
		hMenus.put("panelScanner1",             mitScanner1);
		hMenus.put("panelScanner2",             mitScanner2);
		hMenus.put("panelScanner3",             mitScanner3);
		hMenus.put("panelScanner4",             mitScanner4);
		hMenus.put("panelLaserRGB2",            mitLaserRGB2);
		hMenus.put("panelLaserRGB1",            mitLaserRGB1);
		hMenus.put("panelHumoDMX",              mitHumoDMX);
		hMenus.put("panelShowEjecuta",          mitShowEjecuta);
		hMenus.put("panelShowAmbientes",        mitShowAmbientes);
		hMenus.put("panelReles",        		mitReles);

		for (String id: hMenus.keySet())
			if (VentanasCfg.getPanelVisibilidad(id)){
			//	System.out.println("Activando menu "+id);
				actionPerformed(new ActionEvent(hMenus.get(id), 1, null));
			}
	}
	
	private void cerrar(){
		hFrames.put("panelCristalBall1"        , fiCristalBall1);
		hFrames.put("panelCristalBall2"        , fiCristalBall2);
		hFrames.put("panelDerbyRGB"            , fiDerbyRGB);
		hFrames.put("panelDerbyRGBW2"          , fiDerbyRGBW2);
		hFrames.put("panelMonitorFixtures"     , fiMonitorFixtures);
		hFrames.put("panelEjecutorProgramas"   , fiEjecutorProgramas);
		hFrames.put("panelConfiguracion"       , fiConfiguracion);
		hFrames.put("panelControlMovingHeads"  , fiControlMovingHeads);
		hFrames.put("panelSnifferDMX"          , fiSnifferDMX);
		hFrames.put("panelEjecucionAutomatica" , fiEjecucionAutomatica);
		hFrames.put("panelStageLight" ,          fiStageLight);
		hFrames.put("panelRGBStageLight" ,       fiRGBStageLight);
		hFrames.put("panelEscenas" ,             fiEscenas);
		hFrames.put("panelMovingHead1" ,         fiMovingHead1);
		hFrames.put("panelMovingHead2" ,         fiMovingHead2);
		hFrames.put("panelScanner1",             fiScanner1);
		hFrames.put("panelScanner2",             fiScanner2);
		hFrames.put("panelScanner3",             fiScanner3);
		hFrames.put("panelScanner4",             fiScanner4);
		hFrames.put("panelLaserRGB2",            fiLaserRGB2);
		hFrames.put("panelLaserRGB1",            fiLaserRGB1);
		hFrames.put("panelHumoDMX",              fiHumoDMX);
		hFrames.put("panelShowEjecuta",          fiShowEjecuta);
		hFrames.put("panelShowAmbientes",        fiShowAmbientes);
		hFrames.put("panelReles",                fiReles);

		for (String id: hFrames.keySet())
			VentanasCfg.setPanelPos(id, hFrames.get(id));
		
		RoboticasConfig.graba();
		ControlRele.stop();
		Dmx.stop();
		dispose();
		System.exit(0);
	}

	@Override
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING){
			cerrar();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(mitSalir))
			cerrar();
				
		if (source.equals(mitDerbyRGB)){
			fiDerbyRGB = new FrameInterno(pnlDerbyRGB);
			VentanasCfg.getPanelPos("panelDerbyRGB", fiDerbyRGB);
			muestraVentana(fiDerbyRGB);
		}

		if (source.equals(mitDerbyRGBW2)){
			fiDerbyRGBW2 = new FrameInterno(pnlDerbyRGBW2);
			VentanasCfg.getPanelPos("panelDerbyRGBW2", fiDerbyRGBW2);
			muestraVentana(fiDerbyRGBW2);
		}

		if (source.equals(mitShowEjecuta)){
			fiShowEjecuta = new FrameInterno(pnlShowEjecuta);
			VentanasCfg.getPanelPos("panelShowEjecuta", fiShowEjecuta);
			muestraVentana(fiShowEjecuta);
		}

		if (source.equals(mitShowAmbientes)){
			fiShowAmbientes = new FrameInterno(pnlShowAmientes);
			VentanasCfg.getPanelPos("panelShowAmbientes", fiShowAmbientes);
			muestraVentana(fiShowAmbientes);
		}

		if (source.equals(mitMonitorFixtures)){
			fiMonitorFixtures = new FrameInterno(pnlMonitorFixtures);
			VentanasCfg.getPanelPos("panelMonitorFixtures", fiMonitorFixtures);
			muestraVentana(fiMonitorFixtures);
		}
		
		if (source.equals(mitEjecutorProgramas)){
			fiEjecutorProgramas = new FrameInterno(pnlEjecutorProgramas);
			VentanasCfg.getPanelPos("panelEjecutorProgramas", fiEjecutorProgramas);
			muestraVentana(fiEjecutorProgramas);
		}

		if (source.equals(mitEjecucionAutomatica)){
			fiEjecucionAutomatica = new FrameInterno(pnlEjecucionAutomatica);
			VentanasCfg.getPanelPos("panelEjecucionAutomatica", fiEjecucionAutomatica);
			muestraVentana(fiEjecucionAutomatica);
		}

		if (source.equals(mitEscenas)){
			fiEscenas = new FrameInterno(pnlEscenas);
			VentanasCfg.getPanelPos("panelEscenas", fiEscenas);
			muestraVentana(fiEscenas);
		}
		
		if (source.equals(mitConfiguracion)){
			fiConfiguracion = new FrameInterno(pnlConfiguracion);
			VentanasCfg.getPanelPos("panelConfiguracion", fiConfiguracion);
			muestraVentana(fiConfiguracion);
		}
		
		if (source.equals(mitControlMovingHeads)){
			fiControlMovingHeads = new FrameInterno(pnlControlRobotizados);
			VentanasCfg.getPanelPos("panelControlMovingHeads", fiControlMovingHeads);
			muestraVentana(fiControlMovingHeads);
		}
		
		if (source.equals(mitStageLight)){
			fiStageLight = new FrameInterno(pnlStageLight);
			VentanasCfg.getPanelPos("panelStageLight", fiStageLight);
			muestraVentana(fiStageLight);
		}
		
		if (source.equals(mitRGBStageLight)){
			fiRGBStageLight = new FrameInterno(pnlRGBStageLight);
			VentanasCfg.getPanelPos("panelRGBStageLight", fiRGBStageLight);
			muestraVentana(fiRGBStageLight);
		}
		
		if (source.equals(mitSnifferDMX)){
			if (pnlSnifferDMX==null)
				pnlSnifferDMX = new PanelSnifferDMX();
			fiSnifferDMX = new FrameInterno(pnlSnifferDMX);
			VentanasCfg.getPanelPos("panelSnifferDMX", fiSnifferDMX);
			muestraVentana(fiSnifferDMX);
		}
		
		if (source.equals(mitMovingHead1)){
			fiMovingHead1 = new FrameInterno(pnlMovingHead1);
			VentanasCfg.getPanelPos("panelMovingHead1", fiMovingHead1);
			muestraVentana(fiMovingHead1);
		}
		
		if (source.equals(mitMovingHead2)){
			fiMovingHead2 = new FrameInterno(pnlMovingHead2);
			VentanasCfg.getPanelPos("panelMovingHead2", fiMovingHead2);
			muestraVentana(fiMovingHead2);
		}
		
		if (source.equals(mitScanner1)){
			fiScanner1 = new FrameInterno(pnlScanner1);
			VentanasCfg.getPanelPos("panelScanner1", fiScanner1);
			muestraVentana(fiScanner1);
		}

		if (source.equals(mitScanner2)){
			fiScanner2 = new FrameInterno(pnlScanner2);
			VentanasCfg.getPanelPos("panelScanner2", fiScanner2);
			muestraVentana(fiScanner2);
		}
		
		if (source.equals(mitScanner3)){
			fiScanner3 = new FrameInterno(pnlScanner3);
			VentanasCfg.getPanelPos("panelScanner3", fiScanner3);
			muestraVentana(fiScanner3);
		}
		
		if (source.equals(mitScanner4)){
			fiScanner4 = new FrameInterno(pnlScanner4);
			VentanasCfg.getPanelPos("panelScanner4", fiScanner4);
			muestraVentana(fiScanner4);
		}

		if (source.equals(mitLaserRGB2)){
			fiLaserRGB2 = new FrameInterno(pnlLaserRGB2);
			VentanasCfg.getPanelPos("panelLaserRGB2", fiLaserRGB2);
			muestraVentana(fiLaserRGB2);
		}

		if (source.equals(mitLaserRGB1)){
			fiLaserRGB1 = new FrameInterno(pnlLaserRGB1);
			VentanasCfg.getPanelPos("panelLaserRGB1", fiLaserRGB1);
			muestraVentana(fiLaserRGB1);
		}

		if (source.equals(mitHumoDMX)){
			fiHumoDMX = new FrameInterno(pnlHumoDMX);
			VentanasCfg.getPanelPos("panelHumoDMX", fiHumoDMX);
			muestraVentana(fiHumoDMX);
		}
		
		if (source.equals(mitReles)){
			fiReles = new FrameInterno(pnlReles);
			VentanasCfg.getPanelPos("panelReles", fiReles);
			muestraVentana(fiReles);
		}

	}
	
	private void muestraVentana(FrameInterno mif){
		desktop.add(mif);
		try {
			mif.setSelected(true);
		} catch (PropertyVetoException ex) {
			ex.printStackTrace();
		}
	}

	public static JDesktopPane getDesktop() {
		return desktop;
	}
}
