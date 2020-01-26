package com.clillo.dmx.configuracion;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import com.clillo.dmx.configuracion.programas.ArchivoProgramasCfg;
import com.clillo.dmx.configuracion.programas.PasoTO;
import com.clillo.dmx.configuracion.programas.ProgramaTO;
import com.clillo.dmx.configuracion.programas.TipoPrograma;
import com.clillo.dmx.configuracion.programas.to.PasoProgramaTO;

public class ProgramasCfg {

	private static HashMap<String, ArchivoProgramasCfg> listaArchivos = new HashMap<String, ArchivoProgramasCfg>();
	private static ArrayList<ProgramaTO> listaProgramas = new ArrayList<ProgramaTO>(); 
	
	static {
		procesaDirectorio("conf\\programas\\");
	}
	
	public static void procesaDirectorio(String directorio){
		File f = new File(directorio);
		File lista[] = f.listFiles();
		
		if (lista==null)
			System.out.println("No se encuentra el directorio de trabajo: "+directorio);
		
		for (File archivo: lista)
			if (archivo.isDirectory()){
				String categoria = archivo.getName();
				
				File f2 = new File(directorio+categoria);
				File lista2[] = f2.listFiles();
				for (File archivo2: lista2){
					String nombreSecuencia = archivo2.getName();
					int pos = nombreSecuencia.indexOf('.');
					if (nombreSecuencia.endsWith(".catalogo.xml")){
						nombreSecuencia = nombreSecuencia.substring(0, pos);
						//System.out.println(categoria+"/"+nombreSecuencia);
						TipoPrograma tipo = TipoPrograma.getTipoPorNombre(categoria);
						if (tipo==null)
							System.out.println("La categoría de programa "+categoria+" (directorio conf) no tiene un tipo definido en el código");
						ArchivoProgramasCfg a = new ArchivoProgramasCfg(categoria+"/"+nombreSecuencia, tipo);
						listaArchivos.put(categoria+"/"+nombreSecuencia, a);
						listaProgramas.addAll(a.getListaProgramas());
					}
				}
			}
		
		encuentraReferenciasPasosProgramas();
	}
	
	/**
	 * Este método debe llamase luego de tener cargada la lista completa de los programas en memoria
	 */
	private static void encuentraReferenciasPasosProgramas(){
		
		HashMap<TipoPrograma, ArrayList<ProgramaTO>> hash = new HashMap<>();
		
		for(ProgramaTO to: listaProgramas){
			ArrayList<ProgramaTO> subLista = hash.get(to.getTipo());
			if (subLista == null){
				subLista = new ArrayList<>();
				hash.put(to.getTipo(), subLista);
			}
			subLista.add(to);
		}
			
		for(ProgramaTO to: hash.get(TipoPrograma.RGBWConjunto))
			for (PasoTO paso: to.getListaPasos()){
				if (paso.getListaProgramas() == null)
					continue;

				for(PasoProgramaTO ppto: paso.getListaProgramas()){
					if (ppto.getListaIdsProgramas() != null){
						ppto.setListaProgramas(new ArrayList<ProgramaTO>());
						for (String id : ppto.getListaIdsProgramas()){
							ProgramaTO pto = buscaPrograma(paso, hash, id, ppto.getTipo());	
							ppto.getListaProgramas().add(pto);
						}
							
					}else{					
						ProgramaTO pto = buscaPrograma(paso, hash, ppto.getId(), ppto.getTipo());	
						ppto.setPrograma(pto);
					}
				}
			}
	}
	
	private static ProgramaTO buscaPrograma(PasoTO paso, HashMap<TipoPrograma, ArrayList<ProgramaTO>> hash, String id, TipoPrograma tipoPrograma){
		ArrayList<ProgramaTO> subLista = hash.get(tipoPrograma);
		for (ProgramaTO pto: subLista){
			if(pto.getId().equals(id))
				return pto;
			//System.out.println(pto.getId() + "\t" + id+"\t"+tipoPrograma);
		}
		//	System.out.println("----------------------------");
		return null;
	}
	
	public static ArrayList<ProgramaTO> getListaProgramas(){		
		return listaProgramas;
	}
		
	public static HashMap<String, ArchivoProgramasCfg> getListaArchivos() {
		return listaArchivos;
	}

	private static ArchivoProgramasCfg getArchivo(String id){
		StringTokenizer st = new StringTokenizer(id, ".");
		String archivo = st.nextToken();
		return listaArchivos.get(archivo);
	}
	
	public static ProgramaTO getPrograma(String id) throws Exception{
		ArchivoProgramasCfg archivo = getArchivo(id);
		if (archivo==null)
			throw new Exception("Programa no existe: "+id);
		return archivo.getPrograma(id);
	}

	public static void actualizaPaso(String idPrograma, int idPaso, HashMap<Integer, Integer> hash) throws Exception{
		ArchivoProgramasCfg archivo = getArchivo(idPrograma);
		if (archivo==null)
			throw new Exception("Programa no existe: "+idPrograma);

		archivo.actualizaPaso(idPrograma, idPaso, hash);
	}

	public static void agregaPaso(String idPrograma, int idPaso, HashMap<Integer, Integer> hash) throws Exception{
		ArchivoProgramasCfg archivo = getArchivo(idPrograma);
		if (archivo==null)
			throw new Exception("Programa no existe: "+idPrograma);

		archivo.agregaPaso(idPrograma, idPaso, hash);
	}
	
}
