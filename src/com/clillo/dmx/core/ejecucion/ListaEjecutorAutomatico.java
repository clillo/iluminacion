package com.clillo.dmx.core.ejecucion;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import com.clillo.dmx.aleatorios.AleatorioPrioridad;

public class ListaEjecutorAutomatico {

	private static ArrayList<EscenaAutomatico> lista;	
	private static AleatorioPrioridad aleatorioPrioridad;

	public static ArrayList<EscenaAutomatico> getLista() {
		return lista;
	}

	public static EscenaAutomatico[] obtieneLista(){
		EscenaAutomatico[] s = new EscenaAutomatico[lista.size()];
		
		int i=0;
		for (EscenaAutomatico ss: lista)
			s[i++] = ss;
		return s;
	}
	
	static{
		refrescaLista();
	}
	
	public static void refrescaLista(){
		File archivos[] = new File("conf/automatico").listFiles();
		
		lista = new ArrayList<EscenaAutomatico>();

		for (File archivo : archivos)
			lista.add(creaShow(archivo.getAbsolutePath()));
		
		refrescaProbabilidades();
	}
	
	public static void refrescaProbabilidades(){
		int[] probabilidades = new int[lista.size()];
		
		int i=0;
		for (EscenaAutomatico e: lista)
			probabilidades[i++] = e.getProbabilidad();
		
		aleatorioPrioridad = new AleatorioPrioridad(probabilidades, "Automaticos");
		double[] salida = aleatorioPrioridad.getPorcentajes();
		
		i=0;
		for (EscenaAutomatico e: lista)
			e.setProbabilidadGlobalNormalizada((int)salida[i++]);
	}
	
	public static String obtieneNuevoNombre(){
		File f = new File("conf/automatico");
		File archivos[] = f.listFiles();
	
		int n=archivos.length+1;
		
		return f.getAbsolutePath()+ "/escena"+n+".xml";
	}
	
	public static int siguienteAleatorio(){
		return aleatorioPrioridad.siguiente();
	}
	
	private static EscenaAutomatico creaShow(String nombreArchivo){
		EscenaAutomatico s = new EscenaAutomatico();
		try {
			File archivo = new File(nombreArchivo);
			XMLConfiguration configuracion = new XMLConfiguration(archivo);
			configuracion.setSchemaValidation(false);
			
			String nombre = configuracion.getString("generales.nombre");
			s.setNombre(nombre);
			s.setNombreArchivo(nombreArchivo);
	
			try {
				s.setTiempoMaximo(configuracion.getInt("generales.tiempoMaximo"));
				s.setTiempoMinimo(configuracion.getInt("generales.tiempoMinimo"));
				s.setProbabilidad(configuracion.getInt("generales.probabilidad"));
			} catch (Exception e) {
		
			}
			
			if (s.getTiempoMaximo()<60000)
				s.setTiempoMaximo(60000);
			
			if (s.getTiempoMinimo()<10000)
				s.setTiempoMinimo(10000);
			
			List<HierarchicalConfiguration> fixtures = configuracion.configurationsAt("fixtures.fixture");
			
			for (HierarchicalConfiguration fixture : fixtures){
				String fix = fixture.getString("");

				if (fix.equals("crystalBall"))
					s.setBall(true);

				if (fix.equals("laser1"))
					s.setLaserMultiPunto(true);

				if (fix.equals("laser2"))
					s.setLaserIlda(true);

				if (fix.equals("derby1"))
					s.setDerby(true);

				if (fix.equals("pinspot"))
					s.setPinSpot(true);

				if (fix.equals("rgbw"))
					s.setrGBW(true);

				if (fix.equals("movingHeads"))
					s.setMovingHeads(true);

				if (fix.equals("scanners"))
					s.setScanners(true);	
				
				if (fix.equals("spider1"))
					s.setSpider(true);	
			}
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return s;
	}

}
