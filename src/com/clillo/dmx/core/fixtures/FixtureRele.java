package com.clillo.dmx.core.fixtures;

import com.clillo.dmx.comm.ControlRele;
import com.clillo.dmx.comm.ControlRele.TipoControl;
import com.clillo.utiles.Log;

public class FixtureRele extends Fixture{

	private TipoControl tipoControl;

	public FixtureRele(int id, String nombre) {
		super(id, nombre);
	}

	public void setFixId(String fixId) {
		super.setFixId(fixId);

		if (getFixId().equals("canalA"))
			tipoControl =  TipoControl.CanalA;

		if (getFixId().equals("canalB"))
			tipoControl =  TipoControl.CanalB;

		if (getFixId().equals("canalC"))
			tipoControl = TipoControl.CanalC;

		if (getFixId().equals("canalD"))
			tipoControl =  TipoControl.CanalD;
	}

	@Override
	public void encender() {
		Log.debug("Encendiendo "+tipoControl.getGlosa(), this.getClass());
		ControlRele.encender(tipoControl);
	}

	@Override
	public void apagar() {
		Log.debug("Apagando "+tipoControl.getGlosa(), this.getClass());
		ControlRele.apagar(tipoControl);
	}
}
