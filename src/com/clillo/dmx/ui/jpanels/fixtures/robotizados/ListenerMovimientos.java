package com.clillo.dmx.ui.jpanels.fixtures.robotizados;

public interface ListenerMovimientos {
	
	public enum TipoMovimiento{TRASLADA, ESCALA}

	public void mueveArriba(TipoMovimiento tipo);
	
	public void mueveAbajo(TipoMovimiento tipo);
	
	public void mueveIzquerda(TipoMovimiento tipo);
	
	public void mueveDerecha(TipoMovimiento tipo);
}
