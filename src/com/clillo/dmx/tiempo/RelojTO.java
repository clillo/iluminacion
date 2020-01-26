package com.clillo.dmx.tiempo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.clillo.dmx.configuracion.TiemposCfg;
import com.clillo.dmx.ui.dialogos.EligeTiempo;
import com.clillo.utiles.Log;

public class RelojTO implements MouseListener {

	private boolean activo;
	private long siguienteEjecucion;
	private int miliSegundos = 1000;
	private NotificableReloj gatillable;
	private JTextField txtInterfaz;
	private String idReloj;
	
	public String getIdReloj() {
		return idReloj;
	}
	public void setIdReloj(String idReloj) {
		this.idReloj = idReloj;
		miliSegundos = TiemposCfg.getTiempoCambioAutomatico(idReloj);
		if (miliSegundos==0)
			Log.debug("No esta definido el tiempo minimo para el reloj: "+idReloj, this.getClass());
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
		if (activo==false){
			siguienteEjecucion=0;
			if (txtInterfaz!=null)
				txtInterfaz.setText("");
		}
	}
	public long getSiguienteEjecucion() {
		return siguienteEjecucion;
	}
	public void setSiguienteEjecucion(long siguienteEjecucion) {
		this.siguienteEjecucion = siguienteEjecucion;
	}
	public int getMiliSegundos() {
		return miliSegundos;
	}
	public NotificableReloj getGatillable() {
		return gatillable;
	}
	public void setGatillable(NotificableReloj gatillable) {
		this.gatillable = gatillable;
	}
	public JTextField getTxtInterfaz() {
		return txtInterfaz;
	}
	
	public void setTxtInterfaz(JTextField txtInterfaz) {
		this.txtInterfaz = txtInterfaz;
		txtInterfaz.setHorizontalAlignment(SwingConstants.CENTER);
		txtInterfaz.setBackground(Color.white);
		txtInterfaz.setForeground(Color.black);
		txtInterfaz.setEditable(false);
		txtInterfaz.addMouseListener(this);
		txtInterfaz.setFont(new Font("Tahoma", Font.PLAIN, 9));
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		EligeTiempo.setMilisegundos(miliSegundos);
		EligeTiempo.createAndShowGUI();
		
		if (EligeTiempo.isAceptar()){
			miliSegundos = EligeTiempo.getMilisegundos();
			TiemposCfg.setTiempoCambioAutomatico(idReloj, miliSegundos);
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RelojTO [activo=");
		builder.append(activo);
		builder.append(", siguienteEjecucion=");
		builder.append(siguienteEjecucion);
		builder.append(", miliSegundos=");
		builder.append(miliSegundos);
		builder.append(", gatillable=");
		builder.append(gatillable);
		builder.append(", txtInterfaz=");
		builder.append(txtInterfaz);
		builder.append(", idReloj=");
		builder.append(idReloj);
		builder.append("]");
		return builder.toString();
	}
	
	
}
