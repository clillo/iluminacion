package com.clillo.dmx.ui.jpanels.opcionesMenu.show;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.clillo.dmx.core.show.EjecutorShow;
import com.clillo.dmx.core.show.EjecutorShowSingleton;
import com.clillo.dmx.core.show.ListaShow;
import com.clillo.dmx.core.show.EscenaAutomatico;
import com.clillo.dmx.core.show.ShowDetalle;
import com.clillo.dmx.core.ambientes.*;
import com.clillo.dmx.ui.jpanels.opcionesMenu.PanelMenuGenerico;

import javax.swing.JScrollPane;
import javax.swing.JList;

public class PanelShowAmbientes extends PanelMenuGenerico implements ActionListener{

	private static final long serialVersionUID = -5869553409971473557L;

	private JButton btnEncender;
	private JButton btnApagar;
	
	private JList<Ambiente> lstShow;
	private JList<ShowDetalle> lstDetalleShow;
	private EjecutorShow ejecutor;
	
	public PanelShowAmbientes() {
	    this.configura(460, 300, "Ejecutor Ambientes");
	  	this.setLayout(null);
		
	  	JPanel pnlRGBStageLigth = new JPanel();
		pnlRGBStageLigth.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlRGBStageLigth.setBounds(10, 11, 413, 240);

		pnlRGBStageLigth.setLayout(null);
		
		btnEncender = new JButton("Encender");
		btnEncender.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEncender.setBounds(239, 11, 71, 20);
		btnEncender.addActionListener(this);
		pnlRGBStageLigth.add(btnEncender);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnApagar.setBounds(313, 11, 71, 20);
		btnApagar.addActionListener(this);
		
		pnlRGBStageLigth.add(btnApagar);
		
		add(pnlRGBStageLigth);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 257, 185);
		pnlRGBStageLigth.add(scrollPane);
		
		lstShow = new JList<Ambiente>();
		lstShow.setListData(ListaAmbientes.obtieneLista());
		scrollPane.setViewportView(lstShow);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(277, 42, 111, 187);
		pnlRGBStageLigth.add(scrollPane_1);
		
		lstDetalleShow = new JList<ShowDetalle>();
		scrollPane_1.setViewportView(lstDetalleShow);
		
		ejecutor = EjecutorShowSingleton.getEjecutor();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnApagar)){
			for (EscenaAutomatico s: ListaShow.getLista())
				ejecutor.ejecuta(false, s);
		}
		
		if (e.getSource().equals(btnEncender)){
			int n=0;
			for (EscenaAutomatico s: ListaShow.getLista())
				if (lstShow.isSelectedIndex(n++))
					ejecutor.ejecuta(true, s);
		}
	}
}
