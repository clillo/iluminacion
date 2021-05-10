package com.clillo.dmx.comm.dmx;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * Para hacer funcionar la librería:
 * 
 * 	1.- Copiar contenido de la carpeta dll a c:\windows\system32
 *  2.- Usar un jre de 32 bits como runtime del proyecto java
 *  
 * @author carlos
 *
 */
public class ComunicacionDMX {

    public interface simpleDLL extends Library {
    	simpleDLL INSTANCE = (simpleDLL) Native.loadLibrary("K8062d", simpleDLL.class);
        void StartDevice();
        void StopDevice();
        void SetChannelCount(int channel);
        void SetData(int channel, int data);
    }
    
    private simpleDLL sdll = null;

    protected ComunicacionDMX() {
		sdll = simpleDLL.INSTANCE;
		
		if (sdll!=null){
			sdll.StartDevice();
			sdll.SetChannelCount(512);
		}
	}
    
    protected void stop(){
		if (sdll!=null){
    		sdll.StopDevice();
    		sdll = null;
		}
	}
	
	protected void enviar(int canal, int dato){
		if (canal<0 || canal>512 || dato<0 || dato>255)
			return;
		
		

		if (sdll!=null){
		   sdll.SetData(canal, dato);
		//   System.out.println(canal+"\t"+dato);
		}
	}	
}

