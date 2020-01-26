package com.clillo.dmx.configuracion.ejecucionAutomatica;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import com.clillo.dmx.core.ListaFixtures;
import com.clillo.dmx.core.fixtures.Fixture;

public class GruposEjecucionCfg {

	private static XMLConfiguration configuracion;

	private static HashMap<String, GrupoEjecucionTO> listaGrupos;
	
	static {
		try{
			File f = new File("conf/generales/gruposEjecucion.xml");
			
			configuracion = new XMLConfiguration(f.getAbsolutePath());
			configuracion.setSchemaValidation(false);
				
			listaGrupos = new HashMap<String, GrupoEjecucionTO>();
			
			List<HierarchicalConfiguration> grupos = configuracion.configurationsAt("grupo");
			for (HierarchicalConfiguration grupo : grupos){
				GrupoEjecucionTO g = new GrupoEjecucionTO();
				g.setId(grupo.getString("[@id]"));
			//	System.out.println("Grupo: "+grupo.getString("[@id]"));
				
				ArrayList<Fixture> listaFixtures = new ArrayList<Fixture>();
				List<HierarchicalConfiguration> fixtures = grupo.configurationsAt("fixture");
				for (HierarchicalConfiguration fixture : fixtures)
					listaFixtures.add(ListaFixtures.getFixture(fixture.getString("")));
				
				g.setListaFixtures(listaFixtures);
				listaGrupos.put(g.getId(), g);
			}

		} catch (Exception ce) {
			ce.printStackTrace();
		}
	}

	public static GrupoEjecucionTO getGrupo(String id) {
		return listaGrupos.get(id);
	}
}
