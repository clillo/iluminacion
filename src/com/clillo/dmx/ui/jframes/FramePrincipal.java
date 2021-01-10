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

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.comm.serial.FactoryRS232;
import com.clillo.dmx.configuracion.VentanasCfg;
import com.clillo.dmx.configuracion.fixtures.RoboticasConfig;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelConfiguracion;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelControlRobotizados;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelCristalBall1;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelDerbyRGBW;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelDerbyRGBW2;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelEjecutorProgramas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMenuGenerico;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMonitorRGBW;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelSnifferDMX;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelSpider1;
import com.clillo.dmx.ui.jpanels.opcionesMenu.ejecucionAutomatica.PanelEjecucionAutomatica;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.PanelEncendidoRoboticas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.PanelFigurasRoboticas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.PanelRoboticasConjuntas;
import com.clillo.dmx.ui.jpanels.opcionesMenu.panelLaser.PanelLaserIlda1;
import com.clillo.dmx.ui.jpanels.opcionesMenu.panelLaser.PanelLaserMultiPunto1;

public class FramePrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = -1005621383743741880L;
	private static JDesktopPane desktop;

	private enum Opcion{ 
		
		CristalBall1 (new JMenuItem("Cristal Ball 1"), new PanelCristalBall1(), "panelCristalBall1"),

		DerbyRGBW1 (new JMenuItem("Mini Derby RGBW"), new PanelDerbyRGBW(), "panelDerbyRGB"),
		DerbyRGBW2 (new JMenuItem("Derby RGBW (2)"), new PanelDerbyRGBW2(), "panelDerbyRGBW2"),
		Configuracion (new JMenuItem("Configuración General"), new PanelConfiguracion(), "panelConfiguracion"),

		LaserMultiPunto1 (new JMenuItem("Laser MultiPunto 1"), new PanelLaserMultiPunto1(), "panelLaserMultiPunto1"),
		LaserIlda1 (new JMenuItem("Laser Ilda 1"), new PanelLaserIlda1(), "panelLaserIlda1"),
		
		EjecutorProgramas (new JMenuItem("Ejecutor de Programas"), new PanelEjecutorProgramas(), "panelEjecutorProgramas"),
		EjecucionAutomatica (new JMenuItem("Ejecutor Automático de Fixtures"), new PanelEjecucionAutomatica(), "panelEjecucionAutomatica"),
		
		MonitorFixtures (new JMenuItem("Monitor Fixtures RGB"), new PanelMonitorRGBW(), "panelMonitorFixtures"),
		ControlMovingHeads (new JMenuItem("Robotizados"), new PanelControlRobotizados(), "panelControlMovingHeads"),
		SnifferDMX (new JMenuItem("Sniffer DMX"), null, "panelSnifferDMX"),
	
		FigurasRoboticas (new JMenuItem("Figuras Robóticas"), new PanelFigurasRoboticas(), "panelFigurasRoboticas"),

		EncendidoRoboticas (new JMenuItem("Encendido Robóticas"), new PanelEncendidoRoboticas(), "panelEncendidoRoboticas"),
		RoboticasConjuntas (new JMenuItem("Robóticas Conjuntas"), new PanelRoboticasConjuntas(), "panelRoboticasConjuntas"),

		
		Spider1 (new JMenuItem("Spider 1"), new PanelSpider1(), "panelSpider1");

		private JMenuItem mit;
		private PanelMenuGenerico panel;
		private FrameInterno frame;
		private String conf;
		
		private Opcion(JMenuItem mit, PanelMenuGenerico panel,  String conf) {
			this.mit = mit;
			this.panel = panel;
			this.conf = conf;
		}

		public JMenuItem getMit() {
			return mit;
		}

		public PanelMenuGenerico getPanel() {
			return panel;
		}
	
		public void setPanel(PanelMenuGenerico panel) {
			this.panel = panel;
		}

		public void setFrame(FrameInterno frame) {
			this.frame = frame;
		}

		public FrameInterno getFrame() {
			return frame;
		}

		public String getConf() {
			return conf;
		}		
		
		
	};
	
	private JMenuItem mitSalir;
	
	private HashMap<String , JMenuItem> hMenus = new HashMap<String, JMenuItem>();
	private HashMap<String , FrameInterno> hFrames = new HashMap<String, FrameInterno>();

	public FramePrincipal() {
		enableEvents(64L);
		initialize();

		for(Opcion opc: Opcion.values())
			opc.getMit().addActionListener(this);
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
		
		menuProgramas.add(Opcion.EjecutorProgramas.getMit());
		menuProgramas.addSeparator();
		menuProgramas.add(Opcion.EjecucionAutomatica.getMit());
		menuProgramas.addSeparator();

	}
	
	private void agregaMenuConfigura(JMenuBar menubar){
		JMenu menuConfigura = new JMenu("Configura");

		menuConfigura.add(Opcion.Configuracion.getMit());
		menuConfigura.addSeparator();
		menuConfigura.add(Opcion.FigurasRoboticas.getMit());

		menuConfigura.addSeparator();
		menuConfigura.add(Opcion.EncendidoRoboticas.getMit());
	
		menuConfigura.addSeparator();
		menuConfigura.add(Opcion.RoboticasConjuntas.getMit());
		menubar.add(menuConfigura);
	}
	
	private void agregaMenuMonitorea(JMenuBar menubar){
		JMenu menuMonitorea = new JMenu("Monitorea");
		
		menuMonitorea.add(Opcion.MonitorFixtures.getMit());

		menuMonitorea.addSeparator();
		menuMonitorea.add(Opcion.ControlMovingHeads.getMit());
		menuMonitorea.addSeparator();

		menuMonitorea.add(Opcion.CristalBall1.getMit());

		menuMonitorea.addSeparator();
		
		menuMonitorea.add(Opcion.DerbyRGBW1.getMit());
		menuMonitorea.add(Opcion.DerbyRGBW2.getMit());

		menuMonitorea.addSeparator();

		menuMonitorea.add(Opcion.LaserIlda1.getMit());
		menuMonitorea.add(Opcion.LaserMultiPunto1.getMit());

		menuMonitorea.addSeparator();
		menuMonitorea.add(Opcion.Spider1.getMit());

		menuMonitorea.addSeparator();

		menuMonitorea.add(Opcion.SnifferDMX.getMit());
		menubar.add(menuMonitorea);		
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
		
		this.setJMenuBar(menubar);

		for(Opcion opc: Opcion.values())
			hMenus.put(opc.getConf(), opc.getMit());

		for (String id: hMenus.keySet())
			if (VentanasCfg.getPanelVisibilidad(id)){
				actionPerformed(new ActionEvent(hMenus.get(id), 1, null));
			}
	}
	
	private void cerrar(){
		for(Opcion opc: Opcion.values())
			hFrames.put(opc.getConf(), opc.getFrame());

		for (String id: hFrames.keySet())
			VentanasCfg.setPanelPos(id, hFrames.get(id));
		
		RoboticasConfig.graba();
		Dmx.stop();
		FactoryRS232.getEjecutor().close();
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
		
		if (source.equals(Opcion.SnifferDMX.getMit())){
			Opcion opc = Opcion.SnifferDMX;

			if (opc.getPanel()==null)
				opc.setPanel(new PanelSnifferDMX());
			
			opc.setFrame(new FrameInterno(opc.getPanel()));
			VentanasCfg.getPanelPos(opc.getConf(), opc.getFrame());
			muestraVentana(opc.getFrame());
			return;
		}
		
		for(Opcion opc: Opcion.values())
			if (source.equals(opc.getMit())){
				opc.setFrame(new FrameInterno(opc.getPanel()));
				VentanasCfg.getPanelPos(opc.getConf(), opc.getFrame());
				muestraVentana(opc.getFrame());
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
