package com.clillo.dmx.core.show;

import java.util.ArrayList;

import com.clillo.dmx.comm.dmx.Dmx;
import com.clillo.dmx.configuracion.programas.to.CanalValorTO;
import com.clillo.dmx.core.ListaFixtures;
import com.clillo.dmx.core.fixtures.Fixture;

public class ShowParValor {

	private int canal;
	private int valor;
	
	public ShowParValor(int canal, int valor) {
		super();
		this.canal = canal;
		this.valor = valor;
	}
	public ShowParValor(String canalStr, int valor) {
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
}
