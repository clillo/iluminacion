package com.clillo.dmx.configuracion;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import com.clillo.dmx.configuracion.programas.to.AccionHTTPTO;
import com.clillo.dmx.configuracion.programas.to.CanalValorTO;
import com.clillo.dmx.core.ListaFixtures;
import com.clillo.dmx.core.fixtures.Fixture;
import com.clillo.dmx.core.fixtures.FixtureCrystalBall1;
import com.clillo.dmx.core.fixtures.FixtureDerbyRGBW;
import com.clillo.dmx.core.fixtures.FixtureHTTP;
import com.clillo.dmx.core.fixtures.FixtureMovingHead;
import com.clillo.dmx.core.fixtures.FixtureRgbw;
import com.clillo.dmx.core.fixtures.FixtureScanner;
import com.clillo.dmx.core.fixtures.FixtureSpider1;
import com.clillo.dmx.core.fixtures.laser.FixtureLaserIlda1;
import com.clillo.dmx.core.fixtures.laser.FixtureLaserMultipunto1;
import com.clillo.dmx.core.fixtures.laser.FixtureLaserRGB1;
import com.clillo.dmx.core.fixtures.laser.FixtureLaserRGB2;

public class FixturesCfg {

	private static final File ARCHIVO = new File("conf/fixtures/catalogoFixtures.xml");
	private static XMLConfiguration configuracion;
	
	static {
		try{
			configuracion = new XMLConfiguration(ARCHIVO);
			configuracion.setSchemaValidation(false);
					
			List<HierarchicalConfiguration> fixtures = configuracion.configurationsAt("fixture");
			for (HierarchicalConfiguration fixture : fixtures)
				procesaFixture(fixture);

		} catch (Exception ce) {
			ce.printStackTrace();
		}
	}
	
	private static void procesaFixture(HierarchicalConfiguration fixture){

		String fixId = fixture.getString("[@id]");
		String tipo = fixture.getString("tipo");
		String nombre = fixture.getString("nombre");
		
		Fixture fixTo = null;
		if (tipo.equals("RGBW"))
			fixTo = new FixtureRgbw(nombre);
		
		if (tipo.equals("MovingHead"))
			fixTo = new FixtureMovingHead(nombre);

		if (tipo.equals("Scanner"))
			fixTo = new FixtureScanner(nombre);

		if (tipo.equals("CrystalBall1"))
			fixTo = new FixtureCrystalBall1( nombre);

		if (tipo.equals("laserrgb2"))
			fixTo = new FixtureLaserRGB2(nombre);

		if (tipo.equals("laserrgb1"))
			fixTo = new FixtureLaserRGB1(nombre);

		if (tipo.equals("derbyrgbw"))
			fixTo = new FixtureDerbyRGBW(nombre);

		if (tipo.equals("laserMulipunto1"))
			fixTo = new FixtureLaserMultipunto1(nombre);

		if (tipo.equals("laserIlda1"))
			fixTo = new FixtureLaserIlda1(nombre);

		if (tipo.equals("spider1"))
			fixTo = new FixtureSpider1(nombre);
		
		if (fixTo==null){
			System.out.println("ATENCION: tipo no encontrado: "+tipo+ " en archivo catalogoFixtures.xml");
			return;
		}
		
		fixTo.setFixId(fixId);

		ListaFixtures.addFixture(fixTo);
		
		String tipoComm = fixture.getString("comm.tipo");
					
		if (tipoComm.equals("DMX")){
			int canalInicial = fixture.getInt("comm.canalInicial");
			fixTo.setCanalDMXInicial(canalInicial);
			
			ArrayList<CanalValorTO> listaCanales = new ArrayList<CanalValorTO>();
			int nCanales = fixture.getInt("comm.numeroCanales");
			int colorR = fixture.getInt("comm.sniffer.colorR");
			int colorG = fixture.getInt("comm.sniffer.colorG");
			int colorB = fixture.getInt("comm.sniffer.colorB");
						
			List<HierarchicalConfiguration> canales = fixture.configurationsAt("comm.canales.canal");
			
			for (HierarchicalConfiguration canal : canales){
				String canalId = canal.getString("[@id]");
				int valor = canal.getInt("");
				CanalValorTO cvto = new CanalValorTO();
				cvto.setCanalId(canalId);
				cvto.setCanalDMX(canalInicial+valor);
				listaCanales.add(cvto);
				
				ListaFixtures.addCanal(canalId, canalInicial+valor);
			}
			
			fixTo.setListaCanales(listaCanales);
			fixTo.setnCanales(nCanales);
			fixTo.setColor(new Color(colorR, colorG, colorB));
			
			return ;
		}
		
		if (tipoComm.equals("HTTP-SIMPLE")){
			String ip = fixture.getString("comm.ip");
			int puerto = fixture.getInt("comm.puerto");
			ArrayList<AccionHTTPTO> listaAcciones = new ArrayList<>();
			FixtureHTTP fhhtp = (FixtureHTTP)fixTo;
			
			fhhtp.setPuerto(puerto);
			fhhtp.setIp(ip);
			
			List<HierarchicalConfiguration> acciones = fixture.configurationsAt("comm.acciones.accion");
			
			for (HierarchicalConfiguration accion : acciones){
				String accionId = accion.getString("[@id]");
				String valor = accion.getString("");
				AccionHTTPTO to = new AccionHTTPTO();
				to.setId(accionId);
				to.setAccion(valor);
				listaAcciones.add(to);
			}			
			
			fhhtp.setListaAcciones(listaAcciones);

		}
		
	}
	
	/**
	 * No borrar este método, sirve para ejecutar el bloque estático que inicializa la clase
	 */
	public static void carga(){
		
	}

	public static void main(String[] args) {
		
	}
}
