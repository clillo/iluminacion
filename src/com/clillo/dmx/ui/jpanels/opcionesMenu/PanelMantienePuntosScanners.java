package com.clillo.dmx.ui.jpanels.opcionesMenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.clillo.dmx.configuracion.escenas.PuntosRoboticasCfg;
import com.clillo.dmx.core.ListaFixtures;
import com.clillo.dmx.core.fixtures.FixtureScanner;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.ListenerCambioPosicion;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.ListenerMovimientos;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.PanelCanvas;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.PanelControlPosicion;
import com.clillo.dmx.ui.jpanels.fixtures.robotizados.Punto;
import com.clillo.dmx.ui.jpanels.opcionesMenu.escenas.PuntoRoboticas;

public class PanelMantienePuntosScanners extends PanelMenuGenerico implements ActionListener, ListenerCambioPosicion, ListSelectionListener, ListenerMovimientos{

	private static final long serialVersionUID = -5869553409971473557L;

	private PanelCanvas pnlCanvas;

	private JButton btnEditar;
	
	private PanelControlPosicion pnlControlPosicion;
	private PanelControlPosicion pnlTraslada; 
	private PanelControlPosicion pnlEscala;
	
	private JList<PuntoRoboticas> lstPuntos;
	private DefaultListModel<PuntoRoboticas> modeloLista;

	private FixtureScanner entidad;
	private JTextField txtPosicion;
	private JTextField txtFine;
	private JPanel pnl1;
	
	private String id;
	
	public PanelMantienePuntosScanners() {
	    this.configura(520, 470, "Mantiene Puntos Scanners");
	  	this.setLayout(null);
	  	
		pnl1 = new JPanel();
		pnl1.setLayout(null);
		pnl1.setBounds(4, 4, 508, 434);
		add(pnl1);
		
		txtPosicion = new JTextField();
		txtPosicion.setBounds(420, 24, 39, 20);
		pnl1.add(txtPosicion);

		txtFine = new JTextField();
		txtFine.setBounds(459, 24, 39, 20);
		pnl1.add(txtFine);

		pnlCanvas = new PanelCanvas();
		pnlCanvas.setBackground(Color.BLACK);
		pnlCanvas.setBounds(10, 24, 400, 400);
		pnlCanvas.setTxtPosicion(txtPosicion);
		pnlCanvas.setTxtFine(txtFine);
		pnl1.add(pnlCanvas);
		
		pnlControlPosicion = new PanelControlPosicion();
		pnlControlPosicion.setBounds(420, 55, 60, 54);
		pnl1.add(pnlControlPosicion);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(420, 185, 63, 75);
		pnl1.add(scrollPane);
		
		modeloLista = new DefaultListModel<PuntoRoboticas>();

		lstPuntos = new JList<PuntoRoboticas>(modeloLista);
		lstPuntos.addListSelectionListener(this);
		scrollPane.setViewportView(lstPuntos);
				
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(420, 271, 68, 23);
		btnEditar.addActionListener(this);
		pnl1.add(btnEditar);
		
		pnlTraslada = new PanelControlPosicion();
		pnlTraslada.setBounds(420, 305, 60, 54);
		pnl1.add(pnlTraslada);
		
		pnlEscala = new PanelControlPosicion();
		pnlEscala.setBounds(438, 370, 60, 54);
		pnl1.add(pnlEscala);		
	}
	
	private void actualizaPuntos(){
		lstPuntos.removeListSelectionListener(this);
		modeloLista.removeAllElements();
		lstPuntos.addListSelectionListener(this);

		ArrayList<Punto> listaPuntos = new ArrayList<Punto>();
		for (PuntoRoboticas pr: PuntosRoboticasCfg.getListaPuntos()){
			modeloLista.addElement(pr);
			Punto p = new Punto();
			p.setNombre(pr.getId());
			if ("scanner1".equals(this.id)){
				p.setX(pr.getScanner1Pan());
				p.setY(pr.getScanner1Tilt());
			}else
				if ("scanner2".equals(this.id)){
					p.setX(pr.getScanner2Pan());
					p.setY(pr.getScanner2Tilt());				
				}else
					if ("scanner3".equals(this.id)){
						p.setX(pr.getScanner3Pan());
						p.setY(pr.getScanner3Tilt());				
					}
					else
						if ("scanner4".equals(this.id)){
							p.setX(pr.getScanner4Pan());
							p.setY(pr.getScanner4Tilt());				
						}
				listaPuntos.add(p);
		}
		
		pnlCanvas.setListaPuntos(listaPuntos);
	}

	public void setId(String id){
		entidad = (FixtureScanner)ListaFixtures.getFixture(id);
		pnlCanvas.setEntidad(entidad);
		entidad.setListenerCambioPosicion(this);
		pnlControlPosicion.setEntidad(entidad);
		
		pnlTraslada.setListener(this);
		pnlTraslada.setTipo(TipoMovimiento.TRASLADA);
		pnlEscala.setListener(this);
		pnlEscala.setTipo(TipoMovimiento.ESCALA);
		
		if (id.contains("1"))
			pnl1.setBorder(new TitledBorder(null, "Scanner 1", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		else
			if (id.contains("2"))
				pnl1.setBorder(new TitledBorder(null, "Scanner 2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			else
				if (id.contains("3"))
					pnl1.setBorder(new TitledBorder(null, "Scanner 3", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				else
					if (id.contains("4"))
						pnl1.setBorder(new TitledBorder(null, "Scanner 4", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		this.id = id;
		actualizaPuntos();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnEditar)){
			if (lstPuntos.getSelectedValue()!=null){
				PuntoRoboticas pr = lstPuntos.getSelectedValue();
				if ("scanner1".equals(this.id)){
					pr.setScanner1Pan(entidad.getPosX());
					pr.setScanner1Tilt(entidad.getPosY());
				}else 
					if ("scanner2".equals(this.id)){
						pr.setScanner2Pan(entidad.getPosX());
						pr.setScanner2Tilt(entidad.getPosY());
					}else 
						if ("scanner3".equals(this.id)){
							pr.setScanner3Pan(entidad.getPosX());
							pr.setScanner3Tilt(entidad.getPosY());
						}else 
							if ("scanner4".equals(this.id)){
								pr.setScanner4Pan(entidad.getPosX());
								pr.setScanner4Tilt(entidad.getPosY());
							}
				actualizaPuntos();
				pnlCanvas.repaint();
				PuntosRoboticasCfg.grabar();
			}
		}
	}

	@Override
	public void moverHasta(int x, int y, boolean fine) {
		pnlCanvas.repaint();
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if (arg0.getSource().equals(lstPuntos) && !arg0.getValueIsAdjusting()){
			if (lstPuntos.getSelectedValue()!=null)
				lstPuntos.getSelectedValue().mueveA();
			
			pnlCanvas.repaint();
		}
	}

	@Override
	public void mueveArriba(TipoMovimiento tipo) {
		for (PuntoRoboticas pr: PuntosRoboticasCfg.getListaPuntos()){		
			if ("scanner1".equals(this.id)){
				if (tipo == TipoMovimiento.TRASLADA)
					pr.setScanner1Tilt(pr.getScanner1Tilt()-1);
				else
					pr.setScanner1Tilt((int)(pr.getScanner1Tilt()/1.025));
			}else if ("scanner2".equals(this.id)){
					if (tipo == TipoMovimiento.TRASLADA)
						pr.setScanner2Tilt(pr.getScanner2Tilt()-1);
					else
						pr.setScanner2Tilt((int)(pr.getScanner2Tilt()/1.025));
				}else if ("scanner3".equals(this.id)){
					if (tipo == TipoMovimiento.TRASLADA)
						pr.setScanner3Tilt(pr.getScanner3Tilt()-1);
					else
						pr.setScanner3Tilt((int)(pr.getScanner3Tilt()/1.025));
				}else if ("scanner4".equals(this.id)){
					if (tipo == TipoMovimiento.TRASLADA)
						pr.setScanner4Tilt(pr.getScanner4Tilt()-1);
					else
						pr.setScanner4Tilt((int)(pr.getScanner4Tilt()/1.025));
				}
		}
		actualizaPuntos();
		pnlCanvas.repaint();
		PuntosRoboticasCfg.grabar();	
	}

	@Override
	public void mueveAbajo(TipoMovimiento tipo) {
		for (PuntoRoboticas pr: PuntosRoboticasCfg.getListaPuntos()){		
			if ("scanner1".equals(this.id)){
				if (tipo == TipoMovimiento.TRASLADA)
					pr.setScanner1Tilt(pr.getScanner1Tilt()+1);
				else
					pr.setScanner1Tilt((int)(pr.getScanner1Tilt()*1.025));
			}else if ("scanner2".equals(this.id)){
					if (tipo == TipoMovimiento.TRASLADA)
						pr.setScanner2Tilt(pr.getScanner2Tilt()+1);
					else
						pr.setScanner2Tilt((int)(pr.getScanner2Tilt()*1.025));
				}else if ("scanner3".equals(this.id)){
					if (tipo == TipoMovimiento.TRASLADA)
						pr.setScanner3Tilt(pr.getScanner3Tilt()+1);
					else
						pr.setScanner3Tilt((int)(pr.getScanner3Tilt()*1.025));
				}else if ("scanner4".equals(this.id)){
					if (tipo == TipoMovimiento.TRASLADA)
						pr.setScanner4Tilt(pr.getScanner4Tilt()+1);
					else
						pr.setScanner4Tilt((int)(pr.getScanner4Tilt()*1.025));
				}
		}
		actualizaPuntos();
		pnlCanvas.repaint();
		PuntosRoboticasCfg.grabar();			
	}

	@Override
	public void mueveIzquerda(TipoMovimiento tipo) {
		for (PuntoRoboticas pr: PuntosRoboticasCfg.getListaPuntos()){		
			if ("scanner1".equals(this.id)){
				if (tipo == TipoMovimiento.TRASLADA)
					pr.setScanner1Pan(pr.getScanner1Pan()-1);
				else
					pr.setScanner1Pan((int)(pr.getScanner1Pan()/1.025));
			}else if ("scanner2".equals(this.id)){
					if (tipo == TipoMovimiento.TRASLADA)
						pr.setScanner2Pan(pr.getScanner2Pan()-1);
					else
						pr.setScanner2Pan((int)(pr.getScanner2Pan()/1.025));
				}else if ("scanner3".equals(this.id)){
					if (tipo == TipoMovimiento.TRASLADA)
						pr.setScanner3Pan(pr.getScanner3Pan()-1);
					else
						pr.setScanner3Pan((int)(pr.getScanner3Pan()/1.025));
				}else if ("scanner4".equals(this.id)){
					if (tipo == TipoMovimiento.TRASLADA)
						pr.setScanner4Pan(pr.getScanner4Pan()-1);
					else
						pr.setScanner4Pan((int)(pr.getScanner4Pan()/1.025));
				}
		}
		actualizaPuntos();
		pnlCanvas.repaint();
		PuntosRoboticasCfg.grabar();		
	}

	@Override
	public void mueveDerecha(TipoMovimiento tipo) {
		for (PuntoRoboticas pr: PuntosRoboticasCfg.getListaPuntos()){		
			if ("scanner1".equals(this.id)){
				if (tipo == TipoMovimiento.TRASLADA)
					pr.setScanner1Pan(pr.getScanner1Pan()+1);
				else
					pr.setScanner1Pan((int)(pr.getScanner1Pan()*1.025));
			}else{
				if (tipo == TipoMovimiento.TRASLADA)
					pr.setScanner2Pan(pr.getScanner2Pan()+1);
				else
					pr.setScanner2Pan((int)(pr.getScanner2Pan()*1.025));
			}
		}
		actualizaPuntos();
		pnlCanvas.repaint();
		PuntosRoboticasCfg.grabar();			
	}
}
