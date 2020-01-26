package com.clillo.dmx.core.ambientes;

import java.util.ArrayList;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.configuracion.programas.to.CanalValorTO;
import com.clillo.dmx.core.ListaFixtures;
import com.clillo.dmx.core.fixtures.Fixture;

public class AmbienteParValor {

	private int canal;
	private int valor;
	
	public AmbienteParValor(int canal, int valor) {
		super();
		this.canal = canal;
		this.valor = valor;
	}
	public AmbienteParValor(String canalStr, int valor) {
		super();
		this.valor = valor;

		ArrayList<Fixture> lista = ListaFixtures.obtieneListadoFixtures();
		for (Fixture fix: lista){
			 ArrayList<CanalValorTO> listaCanales = fix.getListaCanales();
			 for (CanalValorTO canal: listaCanales)
				 if (canal.getCanalId().equals(canalStr)){
					 this.canal = canal.getCanalDMX();
					 return;
			}
		}
		System.out.println("Canal: "+canalStr+" no encontrado");
	}

	public void envia(){
		Dmx.enviar(canal, valor);
	}
	
	public int getCanal() {
		return canal;
	}
	public void setCanal(int canal) {
		this.canal = canal;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
}
