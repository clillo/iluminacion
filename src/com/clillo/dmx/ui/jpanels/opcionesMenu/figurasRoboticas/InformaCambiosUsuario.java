package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas;

import com.clillo.dmx.ui.jpanels.fixtures.robotizados.Punto;

public interface InformaCambiosUsuario {
	
	public void cambioVentana();
	
	public void cambioPosicionCursor(int x, int y, double pan, double tilt, Punto puntoActual);

	public void cambioPosicionCursor(int x, int y, double pan, double tilt);

}
