package com.clillo.dmx.comm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.comm.CommPortIdentifier;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;

import com.clillo.utiles.Log;

public class Rs232 implements SerialPortEventListener {

	private OutputStream outputStream = null;
    private InputStream inputStream = null;
    private SerialPort serialPort  = null;
    private String ultimoLeido = null;
     
    public Rs232() throws Exception{
    	if (true){
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
		CommPortIdentifier portId;
		
		outputStream = null;
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();

			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				System.out.println("Puerto Serial Encontrado: " + portId.getName());

				serialPort = (SerialPort) portId.open("Iluminacion", 1);
				outputStream = serialPort.getOutputStream();
				inputStream = serialPort.getInputStream();

	            serialPort.addEventListener(this);
	            serialPort.notifyOnDataAvailable(true);
	            
				serialPort.setSerialPortParams(19200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,	SerialPort.PARITY_NONE);
			}
		}
		
		if (serialPort==null)
			System.out.println("Atención: No se encontró el puerto del Arduino");
    	}
	}
 
	public void enviar(char mensaje) {
		Log.debug(mensaje+"", Rs232.class);	
		
		if (outputStream == null)
			return;
		
		byte[] b = new byte[1];
		b[0] = (byte)mensaje;
		
		try {
			outputStream.write(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    public void serialEvent(SerialPortEvent evt) {
        if (evt.getEventType() != SerialPortEvent.DATA_AVAILABLE)
        	return ;
       
		if (inputStream == null)
			return;

        try{
            byte data[] = new byte[1024];
            inputStream.read(data);
            ultimoLeido = new String(data);
        }
        catch (Exception e){
            e.printStackTrace();
        }   
    }
    
    public String ping(){
    	ultimoLeido = null;
    	enviar('P');
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
    	return ultimoLeido;
    }
    
    public void desconectar() throws IOException{
    	if (serialPort!=null){
	        serialPort.removeEventListener();
	        serialPort.close();
    	}
    	
    	if (inputStream!=null)
    		inputStream.close();
    	
    	if (outputStream!=null)
    		outputStream.close();
    }
	
	public static void main(String[] args) throws Exception {
		Rs232 rs = new Rs232();
		Thread.sleep(500);
		rs.enviar('G');
		Thread.sleep(1000);
		rs.enviar('H');
		Thread.sleep(1000);
		System.out.println(rs.ping());
		rs.desconectar();
	}
}
