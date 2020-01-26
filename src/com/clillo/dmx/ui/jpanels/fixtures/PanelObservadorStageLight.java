package com.clillo.dmx.ui.jpanels.fixtures;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.configuracion.GoboNombreValorCfg;
import com.clillo.dmx.core.fixtures.robotizados.GoboNombreValor;

/**
 * 
1	Encender/Apagar	encender=128	apagar=0								
2	Gobo	0	25	50	75	100	125	150	175	200	225
3	Velocidad Espejos	0	25	50	75	100	125	150	175	200	225
4	Automático Espejos	0-128 normal	153	178	203	228					
5	Dirección Espejos	0-85 izq	85-170 der	170-255 stop							

 * @author clillo
 *
 */
public class PanelObservadorStageLight extends PanelObservador implements ActionListener{

	private static final long serialVersionUID = -3664280122489375394L;
	private JButton btnEncender;
	private JButton btnApagar;
	private JComboBox<GoboNombreValor> cbxGobos; 
	private JComboBox<GoboNombreValor> cbxVelocidad;
	private JComboBox<GoboNombreValor> cbxAutomatico;
	private JComboBox<GoboNombreValor> cbxDireccion;
	
	public PanelObservadorStageLight() {
		setLayout(null);
		
		btnEncender = new JButton("Encender");
		btnEncender.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEncender.setBounds(65, 71, 71, 20);
		btnEncender.addActionListener(this);
		add(btnEncender);
		
		btnApagar = new JButton("Apagar");
		btnApagar.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnApagar.setBounds(139, 71, 71, 20);
		btnApagar.addActionListener(this);
		add(btnApagar);
		
		cbxGobos = new JComboBox<GoboNombreValor>();
		cbxGobos.setBounds(10, 11, 81, 22);
		cbxGobos.setFont(new Font("Tahoma", Font.PLAIN, 9));
		cbxGobos.addActionListener(this);
		add(cbxGobos);
		
		cbxVelocidad = new JComboBox<GoboNombreValor>();
		cbxVelocidad.setBounds(101, 11, 109, 22);
		cbxVelocidad.setFont(new Font("Tahoma", Font.PLAIN, 9));
		cbxVelocidad.addActionListener(this);
		add(cbxVelocidad);
		
		cbxAutomatico = new JComboBox<GoboNombreValor>();
		cbxAutomatico.setFont(new Font("Tahoma", Font.PLAIN, 9));
		cbxAutomatico.setBounds(10, 38, 81, 22);
		cbxAutomatico.addActionListener(this);
		add(cbxAutomatico);
		
		cbxDireccion = new JComboBox<GoboNombreValor>();
		cbxDireccion.setFont(new Font("Tahoma", Font.PLAIN, 9));
		cbxDireccion.setBounds(101, 38, 109, 22);
		cbxDireccion.addActionListener(this);
		add(cbxDireccion);

		ArrayList<GoboNombreValor> lista = GoboNombreValorCfg.getListaGoboNombreValor("stageligth.gobo");
		for (GoboNombreValor par:lista)
			cbxGobos.addItem(par);

		lista = GoboNombreValorCfg.getListaGoboNombreValor("stageligth.velocidad");
		for (GoboNombreValor par:lista)
			cbxVelocidad.addItem(par);

		lista = GoboNombreValorCfg.getListaGoboNombreValor("stageligth.automatico");
		for (GoboNombreValor par:lista)
			cbxAutomatico.addItem(par);

		lista = GoboNombreValorCfg.getListaGoboNombreValor("stagelight.direccion");
		for (GoboNombreValor par:lista)
			cbxDireccion.addItem(par);

	}

	@Override
	public void setId(String id) {
		super.setId(id);
		canales = new int[]{canalBase, canalBase+1, canalBase+2, canalBase+3, canalBase+4};
	}

	private void refrescaCombo(int valor, JComboBox<GoboNombreValor> combo, String idEfecto){
		ArrayList<GoboNombreValor> lista = GoboNombreValorCfg.getListaGoboNombreValor(idEfecto);
		int indice = 0;
		for (GoboNombreValor par:lista){
			if (par.getValor() == valor){
				combo.removeActionListener(this);
				combo.setSelectedIndex(indice);
				combo.addActionListener(this);
			}
			indice++;
		}
	}
	
	@Override
	public void actualizaEnvioDmx(int canal, int valor) {
		if (canal==canalBase + 1)
			refrescaCombo(valor, cbxGobos, "stageligth.gobo");
		else
			if (canal==canalBase + 2)
				refrescaCombo(valor, cbxVelocidad, "stageligth.velocidad");
			else
				if (canal==canalBase + 3)
					refrescaCombo(valor, cbxAutomatico, "stageligth.automatico");
				else
					if (canal==canalBase + 4)
						refrescaCombo(valor, cbxDireccion, "stagelight.direccion");
	}

	@Override
	public void apagar() {
		Dmx.enviar(canalBase, 0);
	}

	@Override
	public void encender(){
		Dmx.enviar(canalBase, 255);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnApagar))
			fixture.apagar();

		if (e.getSource().equals(btnEncender))
			fixture.encender();
		
		if (e.getSource().equals(cbxGobos))
			Dmx.enviar(canalBase+1, ((GoboNombreValor)cbxGobos.getSelectedItem()).getValor());

		if (e.getSource().equals(cbxVelocidad))
			Dmx.enviar(canalBase+2, ((GoboNombreValor)cbxVelocidad.getSelectedItem()).getValor());

		if (e.getSource().equals(cbxAutomatico))
			Dmx.enviar(canalBase+3, ((GoboNombreValor)cbxAutomatico.getSelectedItem()).getValor());

		if (e.getSource().equals(cbxDireccion))
			Dmx.enviar(canalBase+4, ((GoboNombreValor)cbxDireccion.getSelectedItem()).getValor());

	}
}
