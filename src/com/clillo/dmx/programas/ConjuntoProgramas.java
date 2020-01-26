package com.clillo.dmx.programas;

import java.util.ArrayList;

import com.clillo.dmx.aleatorios.AleatorioPrioridad;
import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.configuracion.programas.TipoPrograma;
import com.clillo.dmx.tiempo.RelojTO;
import com.clillo.utiles.Log;

public class ConjuntoProgramas {

	private ArrayList<ProgramaTO> listaProgramas;
	private TipoPrograma tipoPrograma;
	private EjecutorMultiplesProgramas ejecutor;
	
	private int indiceSecuencial;
	private AleatorioPrioridad aleatorioPrioridad;
	
	public ConjuntoProgramas(TipoPrograma tipoPrograma) {
		this.tipoPrograma = tipoPrograma;
		
		ejecutor = EjecutorMultiplesProgramasSingleton.getEjecutor();
		listaProgramas = ejecutor.getListaProgramas(tipoPrograma);
		
		indiceSecuencial = 0;
		
		procesaProbabilidades();
	}
	
	public void procesaProbabilidades(){
		int[] probabilidades = new int[listaProgramas.size()];
		
		int i=0;
		for (ProgramaTO programa: listaProgramas)
			probabilidades[i++] = programa.getParametros().getProbabilidadGlobal();
		
		aleatorioPrioridad = new AleatorioPrioridad(probabilidades, tipoPrograma.getNombre());
		double[] salida = aleatorioPrioridad.getPorcentajes();
		
		i=0;
		for (ProgramaTO programa: listaProgramas){
			programa.getParametros().setProbabilidadGlobalNormalizada((int)salida[i]);
			Log.debug(programa.getId()+"\t"+probabilidades[i]+"\t"+programa.getParametros().getProbabilidadGlobalNormalizada()+"\t"+salida[i], this.getClass());
			i++;
		}

	}

	public ArrayList<ProgramaTO> getListaProgramas() {
		return listaProgramas;
	}

	public TipoPrograma getTipoPrograma() {
		return tipoPrograma;
	}

	public EjecutorMultiplesProgramas getEjecutor() {
		return ejecutor;
	}
	
	public ProgramaTO siguienteSecuencial(){
		ProgramaTO programa = listaProgramas.get(indiceSecuencial);
		indiceSecuencial = (indiceSecuencial+1)  % listaProgramas.size();
		return programa;
	}
	
	public ProgramaTO siguienteAleatorio(){
		ProgramaTO programa = listaProgramas.get(aleatorioPrioridad.siguiente());
		return programa;
	}
	
	public void setReloj(RelojTO reloj){
		for (ProgramaTO programa: listaProgramas)
			programa.getParametros().setReloj(reloj);
	}
}
