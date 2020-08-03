package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas;

import com.clillo.dmx.comm.dmx.Dmx;

public class FixtureToDmx {
	
	public static void actualizaNodoToDmx(Escena escena){
		for (NodoEscena nodo :escena.getListaNodos())
			actualizaNodoToDmx(nodo);
	}
	
	public static void actualizaNodoToDmx(NodoEscena nodo){
	
		if (nodo==null)
			return;
		if (nodo.getMovingHead()==null)
			return;
		switch (nodo.getMovingHead()) {
			case MvHd1_90:
				Dmx.enviar(260, nodo.getPan1());
				Dmx.enviar(273, nodo.getPan2());
				Dmx.enviar(261, nodo.getTilt1());
				Dmx.enviar(274, nodo.getTilt2());
				Dmx.enviar(262, nodo.getDimmer());
			break;
			
			case MvHd2_90:
				Dmx.enviar(240, nodo.getPan1());
				Dmx.enviar(253, nodo.getPan2());
				Dmx.enviar(241, nodo.getTilt1());
				Dmx.enviar(254, nodo.getTilt2());
				Dmx.enviar(242, nodo.getDimmer());
			break;

			case MvHd1_60:
				Dmx.enviar(101, nodo.getPan1());
				Dmx.enviar(109, nodo.getPan2());
				Dmx.enviar(102, nodo.getTilt1());
				Dmx.enviar(110, nodo.getTilt2());
				Dmx.enviar(108, nodo.getDimmer());
			break;
			
			case MvHd2_60:
				Dmx.enviar(115, nodo.getPan1());
				Dmx.enviar(123, nodo.getPan2());
				Dmx.enviar(116, nodo.getTilt1());
				Dmx.enviar(124, nodo.getTilt2());
				Dmx.enviar(122, nodo.getDimmer());

			break;

		}
	}

}
