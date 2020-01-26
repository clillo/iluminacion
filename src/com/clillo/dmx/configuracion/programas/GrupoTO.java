package com.clillo.dmx.configuracion.programas;

import java.util.ArrayList;

import com.clillo.dmx.core.ListaFixtures;
import com.clillo.dmx.core.fixtures.FixtureRobotica;
import com.clillo.dmx.programas.pasos.roboticas.EncendidoSecuencial;
import com.clillo.dmx.programas.pasos.roboticas.EncendidoSecuencial.TipoSecuencial;
import com.clillo.dmx.programas.pasos.roboticas.EncendidoTodas;
import com.clillo.dmx.programas.pasos.roboticas.Espiral;
import com.clillo.dmx.programas.pasos.roboticas.PosicionAleatoriaGlobal;
import com.clillo.dmx.programas.pasos.roboticas.PosicionAleatoriaGlobalSimetrico;
import com.clillo.dmx.programas.pasos.roboticas.PosicionAleatoriaGlobalSimetricoPan;
import com.clillo.dmx.programas.pasos.roboticas.PosicionAleatoriaGlobalSimetricoTilt;
import com.clillo.dmx.programas.pasos.roboticas.PosicionAleatoriaIndividual;
import com.clillo.dmx.programas.pasos.roboticas.PosicionPadre;
import com.clillo.dmx.programas.pasos.roboticas.Rebotando;
import com.clillo.dmx.programas.pasos.roboticas.Sinusoidal;
import com.clillo.dmx.programas.pasos.roboticas.Z;
import com.clillo.dmx.programas.pasos.roboticas.Z.TipoZ;

public class GrupoTO {

	public enum TipoAleatorioRoboticas{
		Individual         ("individual",          new PosicionAleatoriaIndividual()), 
		Global             ("global",              new PosicionAleatoriaGlobal()), 
		GlobalSimetrico    ("globalSimetrico",     new PosicionAleatoriaGlobalSimetrico()), 
		GlobalSimetricoPan ("globalSimetricoPan",  new PosicionAleatoriaGlobalSimetricoPan()), 
		GlobalSimetricoTilt("globalSimetricoTilt", new PosicionAleatoriaGlobalSimetricoTilt()),
		Rebotando          ("rebotando",           new Rebotando()),
		Espiral            ("espiral",             new Espiral()),
		Sinusoidal         ("sinusoidal",          new Sinusoidal()),
		ZTipo1         	   ("ztipo1",              new Z(TipoZ.tipo1)),
		ZTipo2         	   ("ztipo2",              new Z(TipoZ.tipo2)),
		ZTipo1Simetrico	   ("ztipo1Simetrico",     new Z(TipoZ.tipo1Simetrico)),
		ZTipo2Simetrico    ("ztipo2Simetrico",     new Z(TipoZ.tipo2Simetrico)),

		EncendidoTodasOff		    ("encendido.todasoff",             	 new EncendidoTodas(false)),
		EncendidoTodasOn		    ("encendido.todason",             	 new EncendidoTodas(true)),
		EncendidoSecuencialSimple1  ("encendido.secuencialSimple1",      new EncendidoSecuencial(TipoSecuencial.SimpleSentido1)),
		EncendidoSecuencialSimple2  ("encendido.secuencialSimple2",      new EncendidoSecuencial(TipoSecuencial.SimpleSentido2)),
		EncendidoSecuencialSimple3  ("encendido.secuencialSimple3",      new EncendidoSecuencial(TipoSecuencial.SimpleSentido3)),
		EncendidoSecuencialSimple4  ("encendido.secuencialSimple4",      new EncendidoSecuencial(TipoSecuencial.SimpleSentido4)),
		EncendidoSecuencialSimple5  ("encendido.secuencialSimple5",      new EncendidoSecuencial(TipoSecuencial.SimpleSentido5)),
		EncendidoSecuencialSimple6  ("encendido.secuencialSimple6",      new EncendidoSecuencial(TipoSecuencial.SimpleSentido6));

		private String nombreEnConf;
		private PosicionPadre posicion;
		
		private TipoAleatorioRoboticas(String nombreEnConf, PosicionPadre posicion){
			this.nombreEnConf = nombreEnConf;
			this.posicion = posicion;
		}
		
		public static TipoAleatorioRoboticas get(String nombreEnConf){
			for (TipoAleatorioRoboticas tipo : values())
				if (tipo.nombreEnConf.equals(nombreEnConf))
					return tipo;

			return null;
		}
		
		public PosicionPadre getPosicion(){
			return posicion;
		}
	};
	
	private int id;
	private ArrayList<String> listaIdFixtures;
	private ArrayList<FixtureRobotica> listaFixtures;
	private TipoAleatorioRoboticas tipoAleatorioRoboticas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<String> getListaIdFixtures() {
		return listaIdFixtures;
	}
	public void setListaIdFixtures(ArrayList<String> listaIdFixtures) {
		this.listaIdFixtures = listaIdFixtures;
		
		listaFixtures = new ArrayList<FixtureRobotica>();
		for (String id : listaIdFixtures)
			listaFixtures.add((FixtureRobotica)ListaFixtures.getFixture(id));
	}
	public ArrayList<FixtureRobotica> getListaFixtures() {
		return listaFixtures;
	}
	public void setListaFixtures(ArrayList<FixtureRobotica> listaFixtures) {
		this.listaFixtures = listaFixtures;
	}
	public TipoAleatorioRoboticas getTipoAleatorioRoboticas() {
		return tipoAleatorioRoboticas;
	}
	public void setTipoAleatorioRoboticas(TipoAleatorioRoboticas tipoAleatorioRoboticas) {
		this.tipoAleatorioRoboticas = tipoAleatorioRoboticas;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GrupoTO [id=");
		builder.append(id);
		builder.append(", listaIdFixtures=");
		builder.append(listaIdFixtures);
		builder.append(", tipoAleatorioRoboticas=");
		builder.append(tipoAleatorioRoboticas);
		builder.append("]");
		return builder.toString();
	}
}
