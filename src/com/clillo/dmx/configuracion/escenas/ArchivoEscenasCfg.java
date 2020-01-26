package com.clillo.dmx.configuracion.escenas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import com.clillo.dmx.core.ListaFixtures;
import com.clillo.dmx.core.escenas.Escena;
import com.clillo.dmx.core.escenas.EscenaCanal;
import com.clillo.dmx.core.escenas.EscenaCanalLiteral;
import com.clillo.dmx.core.escenas.EscenaCanalOnOff;
import com.clillo.dmx.core.escenas.EscenaPaso;
import com.clillo.dmx.core.escenas.EscenaRobotica;
import com.clillo.utiles.Log;

public class ArchivoEscenasCfg {

	private XMLConfiguration conf;
	private Escena escena;

	public ArchivoEscenasCfg(String archivo) {

		try{
			 procesa(archivo);
		} catch (Exception ce) {
			ce.printStackTrace();
		}
	}
	
	private void procesa(String archivo) throws ConfigurationException{
		String strArchivo = "conf/escenas/"+archivo+".xml";
		Log.debug("Procesando Escena: "+strArchivo, this.getClass());

		conf = new XMLConfiguration(strArchivo);
		conf.setSchemaValidation(false);
		String  nombre = conf.getString("configuracion.nombre");
		
		escena = new Escena();
		escena.setId(archivo);
		escena.setNombreArchivo(archivo);
		escena.setTitulo(nombre);
		escena.setMinimo(conf.getInt("configuracion.minimo"));
		escena.setMaximo(conf.getInt("configuracion.maximo"));
		escena.setVelocidad(conf.getInt("configuracion.velocidad"));
		escena.setFactor(conf.getInt("configuracion.factor"));
	//	System.out.println(escena);
	
		ArrayList<EscenaCanal> listaCanales = new ArrayList<EscenaCanal>();
		HashMap<Integer, String> hashCanales = new HashMap<Integer, String>();
		
		List<HierarchicalConfiguration> canales = conf.configurationsAt("canales.canal");
		for (HierarchicalConfiguration canal : canales){
			int correlativo = canal.getInt("[@id]");
			String valor = canal.getString("");
			
			EscenaCanal ec = new EscenaCanal();
			ec.setId(correlativo);
			ec.setCanalStr(valor);
			
			hashCanales.put(ec.getId(), ec.getCanalStr());
			listaCanales.add(ec);
			//System.out.println(correlativo+"\t"+valor);
		}
		
		escena.setListaCanales(listaCanales);
		
		ArrayList<EscenaRobotica> listaRoboticas = new ArrayList<EscenaRobotica>();
		
		List<HierarchicalConfiguration> roboticas = conf.configurationsAt("canales.robotica");
		for (HierarchicalConfiguration robotica : roboticas){
			int correlativo = robotica.getInt("[@id]");
			String nom = robotica.getString("");
			
			EscenaRobotica er = new EscenaRobotica();
			er.setId(correlativo);
			er.setNombre(nom);
			listaRoboticas.add(er);
		}
		
//		boolean esRobotica = listaRoboticas.size()>0;
		
		escena.setListaRoboticas(listaRoboticas);
		
		ConversorRoboticasCanales conversor = new ConversorRoboticasCanales();
		conversor.convierte(escena);
		
		ArrayList<EscenaPaso> listaPasos = new ArrayList<EscenaPaso>();
		
		List<HierarchicalConfiguration> pasos = conf.configurationsAt("pasos.paso");
		for (HierarchicalConfiguration paso : pasos){
			EscenaPaso ep = new EscenaPaso();
			ep.setId(paso.getInt("[@id]"));
			ArrayList<EscenaCanal> listaICanales = new ArrayList<EscenaCanal>();
			
			List<HierarchicalConfiguration> icanales = paso.configurationsAt("canal");
			for (HierarchicalConfiguration icanal : icanales){
				int id = icanal.getInt("[@id]");
				String canalStr = hashCanales.get(id);
				String valorStr = icanal.getString("");
				
				EscenaCanal ec = null;
				
				ec = getEscenaCanal(canalStr, valorStr);

				if (ec==null){
					System.err.println("No está definido el canal o valor "+canalStr+"."+valorStr);
					continue;
				}
				
				ec.setId(id);
				ec.setCanalStr(canalStr);
				ec.setIdDMX(ListaFixtures.obtieneCanalDMX(ec.getCanalStr()));
				listaICanales.add(ec);
				escena.setTipoCanales(ec);				
			}

			List<HierarchicalConfiguration> ipuntos = paso.configurationsAt("punto");
			for (HierarchicalConfiguration ipunto : ipuntos){
				int id = ipunto.getInt("[@id]");
				String valorStr = ipunto.getString("");
				
				ArrayList<EscenaCanal> listaCanalesRoboticas = conversor.getCanales(id, valorStr);
				for (EscenaCanal ecr: listaCanalesRoboticas){
					//System.out.println(esRobotica+"->"+ecr);
					ecr.setIdDMX(ListaFixtures.obtieneCanalDMX(ecr.getCanalStr()));
					listaICanales.add(ecr);
					escena.setTipoCanales(ecr);
				}				
			}

			ep.setListaCanales(listaICanales);
			listaPasos.add(ep);
			
		}
		
		escena.setListaPasos(listaPasos);
		
	//for (EscenaPaso ep :escena.getListaPasos())
	//		System.out.println(ep);
	}
	
	private EscenaCanal getEscenaCanal(String canalStr, String valorStr){
		String cual = canalStr+"."+valorStr;
		
		if (cual.equals("movingHead1.dim.on")) return new EscenaCanalOnOff(true, 255, 0);
		if (cual.equals("movingHead1.dim.off")) return new EscenaCanalOnOff(false, 255, 0);
		if (cual.equals("movingHead2.dim.on")) return new EscenaCanalOnOff(true, 255, 0);
		if (cual.equals("movingHead2.dim.off")) return new EscenaCanalOnOff(false, 255, 0);

		if (cual.equals("scanner1.onoff.on")) return new EscenaCanalOnOff(true, 129, 0);
		if (cual.equals("scanner1.onoff.off")) return new EscenaCanalOnOff(false, 129, 0);
		if (cual.equals("scanner2.onoff.on")) return new EscenaCanalOnOff(true, 129, 0);
		if (cual.equals("scanner2.onoff.off")) return new EscenaCanalOnOff(false, 129, 0);

		if (cual.equals("scanner3.onoff.on")) return new EscenaCanalOnOff(true, 129, 0);
		if (cual.equals("scanner3.onoff.off")) return new EscenaCanalOnOff(false, 129, 0);
		if (cual.equals("scanner4.onoff.on")) return new EscenaCanalOnOff(true, 129, 0);
		if (cual.equals("scanner4.onoff.off")) return new EscenaCanalOnOff(false, 129, 0);
		
		if (cual.equals("movingHead1.color.rotandoLento")) return new EscenaCanalLiteral(128);
		if (cual.equals("movingHead2.color.rotandoLento")) return new EscenaCanalLiteral(128);

		return null;
	}
	
	public Escena getEscena() {
		return escena;
	}
	
	public void grabar(){
		List<HierarchicalConfiguration> pasos = conf.configurationsAt("pasos.paso");
		for(EscenaPaso ep: escena.getListaPasos()){
			boolean encontrado = false;
			for (HierarchicalConfiguration paso : pasos){
				if (ep.getId() == paso.getInt("[@id]")){
					encontrado = true;
					List<HierarchicalConfiguration> icanales = paso.configurationsAt("canal");
					
					for(EscenaCanal ec: ep.getListaCanales())
						for (HierarchicalConfiguration icanal : icanales)
							if (ec.getId() == icanal.getInt("[@id]")){
								EscenaCanalOnOff eco = (EscenaCanalOnOff) ec;
								if (eco.isEncendido())
									icanal.setProperty("", "on");
								else
									icanal.setProperty("", "off");
							}
				}
			}
			
			if (!encontrado){
				conf.addProperty("pasos.paso(-1).[@id]", ep.getId()+"");
				
				for(EscenaCanal ec: ep.getListaCanales()){
					//
					conf.addProperty("pasos.paso.canal(-1).", ec.getValor());
					conf.addProperty("pasos.paso.canal.[@id]", ec.getId());
				}
			}
		}
		try {
			conf.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
}
