package com.clillo.dmx.ui.jpanels.opcionesMenu.escenas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.clillo.dmx.core.escenas.EscenaCanal;
import com.clillo.dmx.core.escenas.EscenaCanalOnOff;
import com.clillo.dmx.core.escenas.EscenaCanalPosicionRoboticas;

class CeldaPasoValor extends JPanel  {
 
	private static final long serialVersionUID = -4650428777327769052L;
//	private JSpinner spinner;
//	private SpinnerNumberModel model;
   
	private JTextField txtValor;
	private EscenaCanal ec;
	
	private JCheckBox chkValor;
    
	public CeldaPasoValor(EscenaCanal ec) {
        setLayout(null);
        
        /*model = new SpinnerNumberModel(0, 0, 255, 1);      
        spinner = new JSpinner(model);
        spinner.setBounds(10, 0, 45, 18);
        add(spinner);
        */
        if (ec instanceof EscenaCanalPosicionRoboticas){
	        txtValor = new JTextField();
	        txtValor.setBounds(10, 0, 50, 16);
	        txtValor.setFont(new Font("Monospaced", Font.PLAIN, 9));
	
	        add(txtValor);
        }

        if (ec instanceof EscenaCanalOnOff){
	        chkValor = new JCheckBox();
	        chkValor.setBounds(10, 0, 39, 16);
	        add(chkValor);
        }
    }

    public void setValor(EscenaCanal valor, boolean estaSeleccionado){
    	if(estaSeleccionado)
    		this.setBackground(Color.blue);
    	else
    		this.setBackground(Color.lightGray);
    	//spinner.setValue(valor);
    	this.ec = valor;
    	//txtNumero.setText(valor.getValor()+"");
    	if (valor instanceof EscenaCanalOnOff){
	    	EscenaCanalOnOff eco = (EscenaCanalOnOff)valor;
	    	chkValor.setSelected(eco.isEncendido());
    	}
    	
    	if (valor instanceof EscenaCanalPosicionRoboticas){
    		EscenaCanalPosicionRoboticas ecp = (EscenaCanalPosicionRoboticas)valor;
    		txtValor.setText(ecp.getIdPosicion());
    	}
    }
        
    public EscenaCanal getValor(){
    	//return model.getNumber().intValue();
    	//ec.setValor(Integer.parseInt(txtNumero.getText()));
       	if (ec instanceof EscenaCanalOnOff){
       		EscenaCanalOnOff eco = (EscenaCanalOnOff)ec;
       		eco.setEncendido(chkValor.isSelected());
       		return eco;
       	}
       	return null;
    }
}
