package com.clillo.dmx.core.fixtures.laser;

import java.util.ArrayList;
import java.util.HashMap;

import com.clillo.dmx.configuracion.programas.to.AccionHTTPTO;
import com.clillo.dmx.core.fixtures.FixtureHTTP;
import com.clillo.dmx.ui.jpanels.fixtures.laser.PanelObservadorLaserMultiPunto1;

public class FixtureLaserMultipunto1 extends FixtureHTTP{
	
	private boolean rojoVerde;
	private boolean azul;
	private HashMap<AccionLaserMultiPunto1, String> hashAcciones = new HashMap<>();
	private PanelObservadorLaserMultiPunto1 panel;
	
	public enum AccionLaserMultiPunto1{
		EncenderAzul("encenderA"), 
		EncenderRojoVerde("encenderRV"), 
		ApagarAzul("apagarA"), 
		ApagarRojoVerde("apagarRV");
		
		private String id;
		
		private AccionLaserMultiPunto1(String id) {
			this.id = id;
		}

		public String getId() {
			return id;
		}
	};
	
	public FixtureLaserMultipunto1(String nombre) {
		super(nombre);
	}
	
	public void setListaAcciones(ArrayList<AccionHTTPTO> listaAcciones) {
		super.setListaAcciones(listaAcciones);
		for (AccionLaserMultiPunto1 al: AccionLaserMultiPunto1.values())
			for (AccionHTTPTO a: listaAcciones){
				if (al.getId().equals(a.getId()))
					hashAcciones.put(al, a.getAccion());
			}
		apagar();
	}
	
	public void setPanel(PanelObservadorLaserMultiPunto1 panel) {
		this.panel = panel;
	}

	public void apagar() {
		enviarMensajeHHTP(hashAcciones.get(AccionLaserMultiPunto1.ApagarAzul));
		enviarMensajeHHTP(hashAcciones.get(AccionLaserMultiPunto1.ApagarRojoVerde));
	}
	
	public void encender(){
		if (!azul && !rojoVerde){
			azul = true;
			rojoVerde = true;
		}
		actualizarEstado();
	}
	
	public void actualizarEstado(){
		enviarMensajeHHTP(hashAcciones.get(azul?AccionLaserMultiPunto1.EncenderAzul:AccionLaserMultiPunto1.ApagarAzul));
		enviarMensajeHHTP(hashAcciones.get(rojoVerde?AccionLaserMultiPunto1.EncenderRojoVerde:AccionLaserMultiPunto1.ApagarRojoVerde));
		if (panel!=null)
			panel.actualizarEstado();
	}
	
	public boolean isRojoVerde() {
		return rojoVerde;
	}

	public void setRojoVerde(boolean rojoVerde) {
		this.rojoVerde = rojoVerde;
	}

	public boolean isAzul() {
		return azul;
	}

	public void setAzul(boolean azul) {
		this.azul = azul;
	}

}
