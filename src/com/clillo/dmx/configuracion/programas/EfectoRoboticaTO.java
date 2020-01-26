package com.clillo.dmx.configuracion.programas;

import java.util.ArrayList;
import java.util.Random;

import com.clillo.dmx.configuracion.GoboNombreValorCfg;
import com.clillo.dmx.configuracion.programas.to.CanalValorTO;
import com.clillo.dmx.core.ListaFixtures;
import com.clillo.dmx.core.fixtures.Fixture;
import com.clillo.dmx.core.fixtures.robotizados.GoboNombreValor;

public class EfectoRoboticaTO {

	private Random rndEfecto = new Random();

	private String id;
	private String valor;
	private int valorDMX;
	private int canalDMX;
	private boolean aleatorio;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
		
		ArrayList<Fixture> lista = ListaFixtures.obtieneListadoFixtures();
		for (Fixture fix: lista){
			 ArrayList<CanalValorTO> listaCanales = fix.getListaCanales();
			 if (listaCanales!=null)
				 for (CanalValorTO canal: listaCanales)
					 if (canal.getCanalId().equals(id)){
						 canalDMX = canal.getCanalDMX();
						 return;
			}
		}
		System.out.println("Canal: "+id+" no encontrado");
	}
	
	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
		
		if (valor==null)
			return;
		
		for (String categoria:GoboNombreValorCfg.getLista().keySet()){
			ArrayList<GoboNombreValor> lista = GoboNombreValorCfg.getListaGoboNombreValor(categoria);
		
			for (GoboNombreValor gobo: lista){
				ArrayList<String> listaCanales = gobo.getListaCanales();
				for (String canal: listaCanales){
				//	System.out.println(canal+"\t"+id+"\t"+gobo.getId()+"\t"+valor);
					if (id.equals(canal))
						if (gobo.getId().equals(valor)){
							valorDMX = gobo.getValor();
							return;
						}
				}
			}
		}
		System.out.println("Valor: "+valor+" no encontrado, canal: "+id);
	}
	
	public int getValorDMX() {
		return valorDMX;
	}
	public int getCanalDMX() {
		return canalDMX;
	}
	
	public int getValorAleatorio() {
		ArrayList<Integer> valoresPosibles = new ArrayList<Integer>();
		
		for (String categoria:GoboNombreValorCfg.getLista().keySet()){
			ArrayList<GoboNombreValor> lista = GoboNombreValorCfg.getListaGoboNombreValor(categoria);
		
			for (GoboNombreValor gobo: lista){
				ArrayList<String> listaCanales = gobo.getListaCanales();
				for (String canal: listaCanales)
					if (id.equals(canal))
						valoresPosibles.add(gobo.getValor());
				
			}
		}

		int indice = rndEfecto.nextInt(valoresPosibles.size());

		return valoresPosibles.get(indice);
	}
	
	public boolean isAleatorio() {
		return aleatorio;
	}
	public void setAleatorio(boolean aleatorio) {
		this.aleatorio = aleatorio;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EfectoRoboticaTO [id=");
		builder.append(id);
		builder.append(", valor=");
		builder.append(valor);
		builder.append(", valorDMX=");
		builder.append(valorDMX);
		builder.append(", canalDMX=");
		builder.append(canalDMX);
		builder.append(", aleatorio=");
		builder.append(aleatorio);
		builder.append("]");
		return builder.toString();
	}
}
