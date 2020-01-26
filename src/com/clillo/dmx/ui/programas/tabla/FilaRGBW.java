package com.clillo.dmx.ui.programas.tabla;

import java.awt.Color;
import java.util.HashMap;

import com.clillo.dmx.configuracion.ProgramasCfg;

public class FilaRGBW {

	private int canalInicial1;
	private int canalInicial2;
	private int canalInicial3;
	private int canalInicial4;
	
	private int nroFila;
	private Color color1;
	private Color color2;
	private Color color3;
	private Color color4;
	private HashMap<Integer, Integer> hash;
	
	private String idPrograma;
	private int idPaso;

    public FilaRGBW(int nroFila, HashMap<Integer, Integer> hash, int canalInicial1, int canalInicial2, int canalInicial3, int canalInicial4, String idPrograma, int idPaso){
    	this.nroFila = nroFila;
        this.hash = hash;
        this.canalInicial1 = canalInicial1;
        this.canalInicial2 = canalInicial2;
        this.canalInicial3 = canalInicial3;
        this.canalInicial4 = canalInicial4;
        this.idPrograma = idPrograma;
        this.idPaso = idPaso;
        
		color1 = new Color(hash.get(canalInicial1), hash.get(canalInicial1+1), hash.get(canalInicial1+2));
		color2 = new Color(hash.get(canalInicial2), hash.get(canalInicial2+1), hash.get(canalInicial2+2));
		color3 = new Color(hash.get(canalInicial3), hash.get(canalInicial3+1), hash.get(canalInicial3+2));
		color4 = new Color(hash.get(canalInicial4), hash.get(canalInicial4+1), hash.get(canalInicial4+2));
    }
    
    public void actualizar(){
    	try {
			ProgramasCfg.actualizaPaso(idPrograma, idPaso, hash);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
   
    private void setColor(Color color, int canal){
		int r = color.getRed();
		int g = color.getGreen();
		int b = color.getBlue();
		
		hash.put(canal, r);
		hash.put(canal+1, g);
		hash.put(canal+2, b);
    }
    
	public HashMap<Integer, Integer> getHash() {
		return hash;
	}

	public int getNroFila() {
		return nroFila;
	}

	public Color getColor1() {
		return color1;
	}

	public void setColor1(Color color1) {
		this.color1 = color1;
		setColor(color1, canalInicial1);
	}

	public Color getColor2() {
		return color2;
	}

	public void setColor2(Color color2) {
		this.color2 = color2;
		setColor(color2, canalInicial2);
	}

	public Color getColor3() {
		return color3;
	}

	public void setColor3(Color color3) {
		this.color3 = color3;
		setColor(color3, canalInicial3);
	}

	public Color getColor4() {
		return color4;
	}

	public void setColor4(Color color4) {
		this.color4 = color4;
		setColor(color4, canalInicial4);
	}
}
