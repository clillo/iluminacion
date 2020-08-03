package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas;

import com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.NodoEscena;

public interface InformaCambiosUsuario {
	
	public void cambioVentana(FixtureRobotica entidad);
	
	public void cambioPosicionCursor(int x, int y, double pan, double tilt, NodoEscena nodoActual);

	public void cambioPosicionCursor(int x, int y, double pan, double tilt);

}
