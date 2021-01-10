package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas;

import com.clillo.dmx.comm.dmx.Dmx;

public class FixtureToDmx {
	
	public static void actualizaNodoToDmx(Escena escena){
		for (NodoEscena nodo :escena.getListaNodos())
			actualizaPosicionNodoToDmx(nodo);
	}
	
	public static void actualizaEncendidoNodoToDmx(Escena escena){
		for (NodoEscena nodo :escena.getListaNodos())
			actualizaEncendidoNodoToDmx(nodo);
	}
	
	public static void actualizaPosicionNodoToDmx(NodoEscena nodo){
	
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
			break;
			
			case MvHd2_90:
				Dmx.enviar(240, nodo.getPan1());
				Dmx.enviar(253, nodo.getPan2());
				Dmx.enviar(241, nodo.getTilt1());
				Dmx.enviar(254, nodo.getTilt2());
			break;

			case MvHd1_60:
				Dmx.enviar(101, nodo.getPan1());
				Dmx.enviar(109, nodo.getPan2());
				Dmx.enviar(102, nodo.getTilt1());
				Dmx.enviar(110, nodo.getTilt2());
			break;
			
			case MvHd2_60:
				Dmx.enviar(115, nodo.getPan1());
				Dmx.enviar(123, nodo.getPan2());
				Dmx.enviar(116, nodo.getTilt1());
				Dmx.enviar(124, nodo.getTilt2());

			break;

		}
	}

	public static void actualizaEncendidoNodoToDmx(NodoEscena nodo){
		
		if (nodo==null)
			return;
		if (nodo.getMovingHead()==null)
			return;
		switch (nodo.getMovingHead()) {
			case MvHd1_90:
				Dmx.enviar(262, nodo.getDimmer());
				Dmx.enviar(263, nodo.getStrobo());
				Dmx.enviar(264, nodo.getColor());
				Dmx.enviar(265, nodo.getGoboCristal());
				Dmx.enviar(266, nodo.getGoboRotation());
				Dmx.enviar(269, nodo.getGoboShake());
				Dmx.enviar(268, nodo.getGobo());
				Dmx.enviar(270, nodo.getFocus());
				Dmx.enviar(271, nodo.getPrisma());
				
				//269 normal shake
			break;
			
			case MvHd2_90:
				Dmx.enviar(242, nodo.getDimmer());
				Dmx.enviar(243, nodo.getStrobo());
				Dmx.enviar(244, nodo.getColor());
				Dmx.enviar(245, nodo.getGoboCristal());
				Dmx.enviar(246, nodo.getGoboRotation());
				Dmx.enviar(249, nodo.getGoboShake());
				Dmx.enviar(248, nodo.getGobo());
				
				Dmx.enviar(250, nodo.getFocus());
				Dmx.enviar(251, nodo.getPrisma());
			break;

			case MvHd1_60:
				Dmx.enviar(104, nodo.getColor());
				Dmx.enviar(105, nodo.getGobo());
				Dmx.enviar(106, nodo.getGoboRotation());
				Dmx.enviar(107, nodo.getStrobo());
				Dmx.enviar(108, nodo.getDimmer());
				
				Dmx.enviar(111, nodo.getPrisma());
			break;
			
			case MvHd2_60:
				Dmx.enviar(118, nodo.getColor());
				Dmx.enviar(119, nodo.getGobo());
				Dmx.enviar(120, nodo.getGoboRotation());
				Dmx.enviar(121, nodo.getStrobo());
				Dmx.enviar(122, nodo.getDimmer());
				
				Dmx.enviar(125, nodo.getPrisma());


			break;

		}
	}

}
