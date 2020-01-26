package com.clillo.dmx.programas.pasos;

import java.util.ArrayList;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.configuracion.programas.PasoTO;
import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.configuracion.programas.to.CanalValorTO;
import com.clillo.dmx.programas.conversorCanal.ConversorCanal;
import com.clillo.dmx.programas.conversorCanal.ConversorCanalDimmerRGBWSinFactor;

public class EjecutorPasosConversor implements EjecutorPasos{

	@Override
	public void ejecutaPaso(ProgramaTO programa){
	//	System.out.println("Ejecutando paso conversor");
		PasoTO paso = programa.siguientePaso();

		ArrayList<CanalValorTO> listaValores = paso.getListaValores();
		ConversorCanal conversor = programa.getConversorCanal();
		ConversorCanalDimmerRGBWSinFactor sinFactor = new ConversorCanalDimmerRGBWSinFactor();
		for (CanalValorTO valor: listaValores)
			if (paso.isMaximoBrilloBlanco())
				Dmx.enviar(valor.getCanalDMX(), sinFactor.getValorCanal(valor.getValorDMX()));
			else
				Dmx.enviar(valor.getCanalDMX(), conversor.getValorCanal(valor.getValorDMX()));
	}

}
