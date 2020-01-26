package com.clillo.dmx.programas.pasos;

import java.util.ArrayList;

import com.clillo.dmx.comm.tcp.HTTP;
import com.clillo.dmx.configuracion.programas.PasoTO;
import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.configuracion.programas.to.AccionHTTPTO;

public class EjecutorPasosAcciones implements EjecutorPasos {

	@Override
	public void ejecutaPaso(ProgramaTO programa){
		PasoTO paso = programa.siguientePaso();

		ArrayList<AccionHTTPTO> listaAcciones = paso.getListaAcciones();
		if (listaAcciones!=null)
			for (AccionHTTPTO accion: listaAcciones)
				HTTP.enviarMensajeHHTP(accion.getAccion(), accion.getIp(), accion.getPuerto());
	}
}
