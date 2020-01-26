package com.clillo.dmx.ui.jpanels.opcionesMenu.puntosRobotizados;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;

import com.clillo.dmx.configuracion.programas.MovimientoTO;
import com.clillo.dmx.configuracion.programas.PasoTO;
import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.programas.ConjuntoProgramas;

import java.awt.Font;
import java.util.ArrayList;

public class PanelListaPuntosRobotizados extends JPanel{

	private static final long serialVersionUID = -6719782671047741614L;

	private JTextField txtPanF1;
	private JTextField txtTiltF1;
	private JTextField txtPanF2;
	private JTextField txtTiltF2;
	
	private JList<String> list;

	public PanelListaPuntosRobotizados() {
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 208, 175);
		add(scrollPane);
		
		list = new JList<String>();
		list.setFont(new Font("Courier New", Font.PLAIN, 11));
		scrollPane.setRowHeaderView(list);
		
		txtPanF1 = new JTextField();
		txtPanF1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtPanF1.setBounds(10, 195, 36, 17);
		add(txtPanF1);
		
		txtTiltF1 = new JTextField();
		txtTiltF1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtTiltF1.setBounds(52, 195, 36, 17);
		add(txtTiltF1);
		
		txtPanF2 = new JTextField();
		txtPanF2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtPanF2.setBounds(136, 195, 36, 17);
		add(txtPanF2);
		
		txtTiltF2 = new JTextField();
		txtTiltF2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtTiltF2.setBounds(182, 195, 36, 17);
		add(txtTiltF2);
	}
	
	public void setup(ConjuntoProgramas conjuntoProgramas) {
		String[] listado = new String[2];
		int n=0;
		for (ProgramaTO programa: conjuntoProgramas.getListaProgramas())
			if(programa.getId().contains("/fijos.")){
				ArrayList<PasoTO> listaPasos = programa.getListaPasos();
				
				for (PasoTO paso: listaPasos){
					ArrayList<MovimientoTO> listaMovimientos = paso.getListaMovimientos();
					StringBuilder sb = new StringBuilder();
					for (MovimientoTO movimiento: listaMovimientos){
						sb.append(movimiento.getPan()).append(",").append(movimiento.getTilt()).append("   ");
					}
					listado[n] = sb.toString();
					n++;
				}
			}
		list.setListData(listado);
	}
}
