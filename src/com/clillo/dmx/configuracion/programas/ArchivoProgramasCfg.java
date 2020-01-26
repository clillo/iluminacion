package com.clillo.dmx.configuracion.programas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import com.clillo.dmx.configuracion.programas.GrupoTO.TipoAleatorioRoboticas;
import com.clillo.dmx.configuracion.programas.TipoGatillador;
import com.clillo.dmx.configuracion.programas.to.AccionHTTPTO;
import com.clillo.dmx.configuracion.programas.to.AleatorioTO;
import com.clillo.dmx.configuracion.programas.to.CanalValorTO;
import com.clillo.dmx.configuracion.programas.to.PasoProgramaTO;
import com.clillo.dmx.configuracion.programas.to.PasoProgramaTO.TipoRandom;
import com.clillo.dmx.core.ListaFixtures;
import com.clillo.dmx.core.fixtures.Fixture;
import com.clillo.dmx.core.fixtures.FixtureHTTP;
import com.clillo.utiles.Log;

public class ArchivoProgramasCfg {

	private XMLConfiguration confNiveles;
	private XMLConfiguration confCatalogo;
	private HashMap<String, ProgramaTO> listaProgramas;
	
	public ArchivoProgramasCfg(String archivo, TipoPrograma tipoPrograma) {
		try{
			 procesaNiveles(archivo, tipoPrograma); 
			 procesaCatalogo(archivo, tipoPrograma);
		} catch (Exception ce) {
			ce.printStackTrace();
		}
	}
	
	private void procesaCatalogo(String archivo, TipoPrograma tipoPrograma) throws ConfigurationException{
		String strArchivo = "conf/programas/"+archivo+".catalogo.xml";
		Log.debug("Procesando catalogo: "+strArchivo, this.getClass());

		confCatalogo = new XMLConfiguration(strArchivo);
		confCatalogo.setSchemaValidation(false);
						
		List<HierarchicalConfiguration> programas = confCatalogo.configurationsAt("programa");
		for (HierarchicalConfiguration programa : programas){
			int correlativo = programa.getInt("[@id]");
			String id = archivo+"."+correlativo;
			ProgramaTO pto = listaProgramas.get(id);
			if (pto==null)
				throw new ConfigurationException("No se encuentra el programa: "+id + " en el achivo " + strArchivo);
			
			pto.setCorrelativo(correlativo);
			pto.setNombre(programa.getString("nombre"));
			if (programa.getString("velocidad")!=null)
				pto.setVelocidad(programa.getInt("velocidad"));	
				
			if (programa.getString("porcentajeVelocidad.actual")!=null)
				pto.setPorcentajeVelocidadActual(programa.getInt("porcentajeVelocidad.actual"));
			
			if (programa.getString("porcentajeVelocidad.minimo")!=null)
				pto.setPorcentajeVelocidadMinimo(programa.getInt("porcentajeVelocidad.minimo"));
			
			if (programa.getString("porcentajeVelocidad.maximo")!=null)
				pto.setPorcentajeVelocidadMaximo(programa.getInt("porcentajeVelocidad.maximo"));
			
			if (programa.getString("velocidadMovimiento")!=null)
				pto.setVelocidadMovimiento(programa.getInt("velocidadMovimiento"));
			
			if (programa.getString("probabilidadGlobal")!=null)
				pto.getParametros().setProbabilidadGlobal(programa.getInt("probabilidadGlobal"));

			if (programa.getString("tiempoEncendidoMedia")!=null)
				pto.getParametros().setTiempoEncendidoMedia(programa.getInt("tiempoEncendidoMedia"));

			if (programa.getString("tiempoEncendidoDevSta")!=null)
				pto.getParametros().setTiempoEncendidoDevSta(programa.getInt("tiempoEncendidoDevSta"));
			
			if (programa.getString("onoff")!=null)
				pto.setEsOnOff(programa.getString("onoff").equals("true"));
			
			if (programa.getString("gatillablePorVDJ")!=null)
				pto.setEsVdjAble(programa.getString("gatillablePorVDJ").equals("true"));

			if(!pto.isEsVdjAble())
				pto.setTipoGatillador(TipoGatillador.RelojInterno);
					
			listaProgramas.put(pto.getId(), pto);
		}
	}
	
	private void procesaNiveles(String archivo, TipoPrograma tipoPrograma) throws ConfigurationException{
		String strArchivo = "conf/programas/"+archivo+".niveles.xml";
		Log.debug("Procesando niveles: "+strArchivo, this.getClass());
		
		confNiveles = new XMLConfiguration(strArchivo);
		confNiveles.setSchemaValidation(false);
				
		listaProgramas = new HashMap<String, ProgramaTO>();
		
		// hay que obtener la conversión de los id de canales a los canales DMX físicos:
		 ArrayList<Fixture> listaFix = ListaFixtures.obtieneListadoFixtures();
		 HashMap<String, Integer> hashCanales = new HashMap<String,Integer>();
		 
		 for (Fixture fix: listaFix)
			 if (fix.getListaCanales()!=null)
				 for (CanalValorTO canal:fix.getListaCanales())
					 hashCanales.put(canal.getCanalId(), canal.getCanalDMX());
		 				
		List<HierarchicalConfiguration> programas = confNiveles.configurationsAt("programa");
		for (HierarchicalConfiguration programa : programas){
			ProgramaTO pto = new ProgramaTO();
			pto.setIdNumerico(programa.getString("[@id]"));
			pto.setId(archivo+"."+programa.getString("[@id]"));
			pto.setTipo(tipoPrograma);
		//	System.out.println("Programa : "+pto.getId());
			
			if (tipoPrograma==null)
				System.out.println("Tipo programa sin ejecutor de pasos (archivo "+archivo+ ")");
			pto.setEjecutorPasos(tipoPrograma.getEjecutorPasos());
			pto.setConversorCanal(tipoPrograma.getConversorCanal());

			listaProgramas.put(pto.getId(), pto);
			
			ArrayList<PasoTO> listaPasos = new ArrayList<PasoTO>();
			pto.setListaPasos(listaPasos);
			
			List<HierarchicalConfiguration> pasos = programa.configurationsAt("paso");
			
			for (HierarchicalConfiguration paso : pasos)
				procesaPaso(paso, listaPasos, hashCanales, archivo);
		}
	}
	
	private void procesaPaso(HierarchicalConfiguration pasoHC, ArrayList<PasoTO> listaPasos, HashMap<String, Integer> hashCanales, String archivo){
		PasoTO pasoTO = new PasoTO();
		listaPasos.add(pasoTO);
		pasoTO.setId(pasoHC.getInt("[@id]"));
		
		pasoTO.setMaximoBrilloBlanco(pasoHC.getString("maxBrillo")!=null);
		
		ArrayList<CanalValorTO> listaValores = new ArrayList<CanalValorTO>();
		
		List<HierarchicalConfiguration> canales = pasoHC.configurationsAt("canal");
		for (HierarchicalConfiguration canal: canales){
			CanalValorTO cvTo = new CanalValorTO();
			String idCanal = canal.getString("[@id]");
			if (hashCanales.get(idCanal)!=null)
				cvTo.setCanalDMX(hashCanales.get(idCanal));
			else{
				System.out.println("No existe el canal "+idCanal+" en la configuración del programa (archivo "+archivo+ ")");
			}
	
			if (canal.getString("") !=null)
				cvTo.setValorDMX(canal.getInt(""));
			else{
				String valor = canal.getString("aleatorio.[@id]");
				cvTo.setAleatorio(true);
				cvTo.setIdAleatorio(valor);
			}
			
			listaValores.add(cvTo);
		}

		if (listaValores.size()>0)
			pasoTO.setListaValores(listaValores);

		ArrayList<MovimientoTO> listaMovimientos = new ArrayList<MovimientoTO>();

		List<HierarchicalConfiguration> movimientos = pasoHC.configurationsAt("movimiento");
		for (HierarchicalConfiguration movimiento: movimientos){
			MovimientoTO movTo = new MovimientoTO();
			movTo.setFixture(movimiento.getString("[@fixture]"));
			movTo.setPan(movimiento.getInt("pan"));
			movTo.setTilt(movimiento.getInt("tilt"));
			//System.out.println(movTo);
			listaMovimientos.add(movTo);
		}
		
		if (listaMovimientos.size()>0)
			pasoTO.setListaMovimientos(listaMovimientos);

		ArrayList<EfectoRoboticaTO> listaEfectosRobotica = new ArrayList<EfectoRoboticaTO>();

		List<HierarchicalConfiguration> efectos = pasoHC.configurationsAt("efecto");
		for (HierarchicalConfiguration efecto: efectos){
			EfectoRoboticaTO movTo = new EfectoRoboticaTO();
			movTo.setId(efecto.getString("[@id]"));
			movTo.setValor(efecto.getString(""));
			
			if (efecto.getString("aleatorio")!=null)
				movTo.setAleatorio(efecto.getBoolean("aleatorio"));
			
			listaEfectosRobotica.add(movTo);
		}
		
		if (listaEfectosRobotica.size()>0)
			pasoTO.setListaEfectosRobotica(listaEfectosRobotica);
		
		ArrayList<AccionHTTPTO> listaAcciones = new ArrayList<AccionHTTPTO>();

		List<HierarchicalConfiguration> acciones = pasoHC.configurationsAt("accion");
		procesaAcciones(acciones, listaAcciones, archivo);
				
		if (listaAcciones.size()>0)
			pasoTO.setListaAcciones(listaAcciones);

		ArrayList<PasoProgramaTO> listaProgramas = new ArrayList<PasoProgramaTO>();

		List<HierarchicalConfiguration> programas = pasoHC.configurationsAt("programa");
		procesaProgramas(programas, listaProgramas, archivo);
				
		if (listaProgramas.size()>0)
			pasoTO.setListaProgramas(listaProgramas);

		procesaGrupos(pasoHC, pasoTO);

		ArrayList<AleatorioTO> listaAleatorios = new ArrayList<AleatorioTO>();

		List<HierarchicalConfiguration> aleatorios = pasoHC.configurationsAt("aleatorio");
		procesAleatorios(aleatorios, listaAleatorios, archivo);
				
		if (listaAleatorios.size()>0)
			pasoTO.setListaAleatorios(listaAleatorios);

	}

	private void procesAleatorios(List<HierarchicalConfiguration> aleatorios, ArrayList<AleatorioTO> listaAleatorios, String archivo){
		for (HierarchicalConfiguration aleatorio: aleatorios){
			AleatorioTO to = new AleatorioTO();
			to.setId(aleatorio.getString("[@id]"));
			
			ArrayList<Integer> listaValores = new ArrayList<>();

			List<HierarchicalConfiguration> valores = aleatorio.configurationsAt("valor");
			for (HierarchicalConfiguration valor: valores)
				listaValores.add(valor.getInt(""));
			
			to.setListaValores(listaValores);
			listaAleatorios.add(to);
		}
	}
	
	private void procesaProgramas(List<HierarchicalConfiguration> programas, ArrayList<PasoProgramaTO> listaProgramas, String archivo){
		for (HierarchicalConfiguration accion: programas){
			PasoProgramaTO to = new PasoProgramaTO();
			List<HierarchicalConfiguration> alternativas = accion.configurationsAt("id");
			
			to.setTipoRandom(TipoRandom.obtienePorCodigo(accion.getString("random")));
			
			ArrayList<String> listaHijos = new ArrayList<>();

			for(HierarchicalConfiguration alternativa: alternativas)
				listaHijos.add(accion.getString("tipo")+"/"+alternativa.getString(""));

			
			if(listaHijos.size()==1){
				String id = listaHijos.get(0);
				
				if(id.endsWith("ninguno"))
					to.setNingunProgramaSeleccionado(true);
				else
					to.setId(id);
			}else
				to.setListaIdsProgramas(listaHijos);
			
			to.setTipo(TipoPrograma.getTipoPorNombre(accion.getString("tipo")));
		
			listaProgramas.add(to);
		}
	}
	
	private void procesaAcciones(List<HierarchicalConfiguration> acciones, ArrayList<AccionHTTPTO> listaAcciones, String archivo){
		ArrayList<Fixture> lista = ListaFixtures.obtieneListadoFixtures();
		HashMap<String, FixtureHTTP> hash = new HashMap<>();

		for (Fixture fix: lista)
			if (fix instanceof FixtureHTTP)
				hash.put(fix.getFixId(), (FixtureHTTP)fix);
		
		for (HierarchicalConfiguration accion: acciones){
			AccionHTTPTO to = new AccionHTTPTO();
			to.setFixture(accion.getString("[@fixture]"));
			to.setAccion(accion.getString(""));
			FixtureHTTP fh = hash.get(to.getFixture());
			if (fh == null){
				System.err.println("En el archivo de programas, se referencia a un Fixture que no existe o no es HTTP: ["+to.getFixture()+ "] archivo: "+archivo);
				continue;
			}
			to.setIp(fh.getIp());
			to.setPuerto(fh.getPuerto());
			
			for (AccionHTTPTO acc: fh.getListaAcciones())
				if (acc.getId().equals(to.getAccion()))
					to.setAccion(acc.getAccion());
			
			listaAcciones.add(to);
		}

	}
	
	private void procesaGrupos(HierarchicalConfiguration pasoHC, PasoTO pasoTO){
		ArrayList<GrupoTO> listaGrupos = new ArrayList<GrupoTO>();

		List<HierarchicalConfiguration> grupos = pasoHC.configurationsAt("grupo");
		for (HierarchicalConfiguration grupo: grupos){
			GrupoTO grupoTo = new GrupoTO();
			grupoTo.setId(grupo.getInt("[@id]"));
			
			ArrayList<String> listaIdFixtures = new ArrayList<String>();
			List<HierarchicalConfiguration> fixtures = grupo.configurationsAt("fixture");
			for (HierarchicalConfiguration fixture: fixtures)
				listaIdFixtures.add(fixture.getString(""));
			
			grupoTo.setListaIdFixtures(listaIdFixtures);
			
			String aleatorio = grupo.getString("aleatorio");
			grupoTo.setTipoAleatorioRoboticas(TipoAleatorioRoboticas.get(aleatorio));

			listaGrupos.add(grupoTo);
		}
		
		if (listaGrupos.size()>0)
			pasoTO.setListaGrupos(listaGrupos);
	}
	
	public ProgramaTO getPrograma(String id){
		return listaProgramas.get(id);
	}

	public ArrayList<ProgramaTO> getListaProgramas(){
		ArrayList<ProgramaTO> lista = new ArrayList<ProgramaTO>();
		for (ProgramaTO programa: listaProgramas.values())
			lista.add(programa);
		
		Collections.sort(lista);
		return lista;
	}
	
	public void actualizaPaso(String idPrograma, int idPaso, HashMap<Integer, Integer> hash){
		boolean actualizar = false;
		List<HierarchicalConfiguration> programas = confNiveles.configurationsAt("programa");
		for (HierarchicalConfiguration programa : programas)
			if(programa.getString("[@id]").equals(idPrograma))
				for (HierarchicalConfiguration paso : programa.configurationsAt("paso"))
					if (paso.getInt("[@id]")==idPaso){
						//System.out.println("Encontrado");
						List<HierarchicalConfiguration> canales = paso.configurationsAt("canal");
						for (HierarchicalConfiguration canal: canales){
							int nuevoValor = hash.get(canal.getInt("[@dmx]"));
							if (canal.getInt("")!=nuevoValor){
								canal.setProperty("", nuevoValor);
								//System.out.println(canal.getInt("")+"\t"+nuevoValor+"\t"+canal.getProperty(""));
								actualizar = true;
							}
						}
					}
		if (actualizar)
		try {
			confNiveles.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void agregaPaso(String idPrograma, int idPaso, HashMap<Integer, Integer> hash){
		List<HierarchicalConfiguration> programas = confNiveles.configurationsAt("programa");
		
		for (HierarchicalConfiguration programa : programas)
			if(programa.getString("[@id]").equals(idPrograma)){
				programa.addProperty("paso(-1)[@id]", idPaso);
				
				for (HierarchicalConfiguration paso : programa.configurationsAt("paso"))
					if (paso.getInt("[@id]")==idPaso){
						
						ArrayList<Integer> lista = new ArrayList<Integer>(); 
						
						for (int canal: hash.keySet())
							lista.add(canal);
						
						Collections.sort(lista);
						
						for (int canal: lista)
							paso.addProperty("canal(-1)[@dmx]", canal);
						
						List<HierarchicalConfiguration> canales = paso.configurationsAt("canal");
						for (HierarchicalConfiguration canal: canales){
							canal.setProperty("", hash.get(canal.getInt("[@dmx]")));
						}
					}
			}	
		
		try {
			confNiveles.save();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
	}

}
