package com.clillo.dmx.programas.pasos.roboticas;

import com.clillo.dmx.configuracion.programas.GrupoTO;
import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.core.fixtures.FixtureRobotica;

public abstract class PosicionPadre {

	protected GrupoTO grupo;
	protected ProgramaTO programa;

	public void setGrupo(GrupoTO grupo) {
		this.grupo = grupo;
	}

	public void setPrograma(ProgramaTO programa) {
		this.programa = programa;
	}

	protected void mueveEntidad(int pan, int tilt, FixtureRobotica fixture){
		fixture.setVelocidadActual(programa.getVelocidadParaMovimientoRoboticos());
		fixture.freeze();
		fixture.moverA(pan, tilt);
		programa.setEjecutandoMovimiento(true);
		fixture.setProgramaMovimientoEnEjecucion(programa);
	}
	
	protected void mueveEntidadRelativa(double x, double y, FixtureRobotica fixture){
		fixture.setVelocidadActual(programa.getVelocidadParaMovimientoRoboticos());
		fixture.freeze();
		fixture.moverARelativo(x, y);
		programa.setEjecutandoMovimiento(true);
		fixture.setProgramaMovimientoEnEjecucion(programa);
	}
	
	public abstract void inicia();
	
	public abstract void mueve();
}
