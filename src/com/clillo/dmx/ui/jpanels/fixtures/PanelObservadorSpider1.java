package com.clillo.dmx.ui.jpanels.fixtures;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.configuracion.GoboNombreValorCfg;
import com.clillo.dmx.core.fixtures.robotizados.GoboNombreValor;

/**

 *
 */
public class PanelObservadorSpider1 extends PanelObservador implements ActionListener, ListSelectionListener{

	private static final long serialVersionUID = -3664280122489375394L;
	
	private JButton btnEncender;
	private JButton btnApagar;
	private JList<GoboNombreValor> lstEfectos;
	
	private int valorEncendido;
	public static int sesibilidadLaserIlda;
	private boolean encendido;
	private ArrayList<GoboNombreValor> lista;
	
	public PanelObservadorSpider1() {
		setLayout(null);
		
		btnEncender = new JButton("Encender");
		btnEncender.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEncender.setBounds(37, 210, 71, 20);
		btnEncender.addActionListener(this);
		add(btnEncender);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnApagar.setBounds(111, 210, 71, 20);
		btnApagar.addActionListener(this);
		add(btnApagar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 189, 162);
		add(scrollPane);
		
		lista = GoboNombreValorCfg.getListaGoboNombreValor("spider1.efecto");
		GoboNombreValor listaTmp[] = new GoboNombreValor[lista.size()] ;
		int i = 0;
		for (GoboNombreValor par:lista)
			listaTmp[i ++] = par;
		
		lstEfectos = new JList<GoboNombreValor>(listaTmp);
		scrollPane.setViewportView(lstEfectos);
		lstEfectos.setSelectedIndex(0);
		lstEfectos.addListSelectionListener(this);
		
				
		valorEncendido = 1;
	}

	@Override
	public void setId(String id) {
		super.setId(id);
		canales = new int[50];
		for (int i=0; i<50; i++)
			canales[i] = canalBase + i;

		encendido = false;
	}
	
	@Override
	public void actualizaEnvioDmx(int canal, int valor) {
		
			if (canal == canalBase + 36){
				encendido = valor!=0;
				int i=0;
				for (GoboNombreValor gobo: lista){
					if (gobo.getValor()==valor)
						lstEfectos.setSelectedIndex(i);
					i++;
				}
				lstEfectos.ensureIndexIsVisible(lstEfectos.getSelectedIndex());
			}
	}

	@Override
	public void apagar() {
		Dmx.enviar(canalBase + 36, 0);
		encendido = false;
	}

	@Override
	public void encender(){
		Dmx.enviar(canalBase, valorEncendido);
		encendido = true;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnApagar))
			fixture.apagar();

		if (e.getSource().equals(btnEncender))
			fixture.encender();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting())
			return;
		Dmx.enviar(canalBase + 36, lstEfectos.getSelectedValue().getValor());
	}

}
