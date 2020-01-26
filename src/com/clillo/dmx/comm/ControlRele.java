package com.clillo.dmx.comm;

import java.io.IOException;

public class ControlRele {

	private static Rs232 rs232;
	
	public enum TipoControl{CanalA  ("CanalA", 'A', 'B'), 
							CanalB  ("CanalB", 'C', 'D'), 
							CanalC  ("CanalC", 'E', 'F'), 
							CanalD  ("CanalD", 'G', 'H');
		
		private String glosa;
		private char encender;
		private char apagar;
		
		private TipoControl(String glosa, char encender, char apagar){
			this.glosa = glosa;
			this.encender = encender;
			this.apagar = apagar;
		}
		
		public String getGlosa(){
			return glosa;
		}

		public char getEncender() {
			return encender;
		}

		public char getApagar() {
			return apagar;
		}
		
		public static TipoControl get(String glosa){
			for (TipoControl tipo : values())
				if (tipo.glosa.equals(glosa))
					return tipo;

			return null;
		}
	};
	
	static{
		try {
			rs232 = new Rs232();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void encender(TipoControl tipo){
		rs232.enviar(tipo.getEncender());
	}
	
	public static void apagar(TipoControl tipo){
		rs232.enviar(tipo.apagar);
	}

	public static String ping(){
		return rs232.ping();
	}
	
	public static void stop(){
		try {
			rs232.desconectar();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
