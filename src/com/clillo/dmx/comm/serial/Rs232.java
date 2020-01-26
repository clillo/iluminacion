package com.clillo.dmx.comm.serial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;

import com.clillo.dmx.comm.Configuracion;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class Rs232 implements SerialPortEventListener {

	private static final int DATA_RATE = 9600;
	private static final int TIME_OUT = 2000;

	private BufferedReader input;

    private SerialPort serialPort  = null;
    
	private ArrayList<Rs232Listener> listaDeListeners = new ArrayList<Rs232Listener>();

    public Rs232() throws Exception{
		if (!Configuracion.estaComunicacionHabilitada())
			return ;
		if(true)
			return;
		CommPortIdentifier portId = null;
		@SuppressWarnings("rawtypes")
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			System.out.println(currPortId.getName());
			if (currPortId.getPortType() == CommPortIdentifier.PORT_SERIAL){
				System.out.println("Puerto Serial Encontrado: " + currPortId.getName());
				portId = currPortId;
			}
		}
		if (portId == null) {
			System.out.println("Atención: No se encontró el puerto del Arduino");
			return;
		}
		
	
		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			
			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	public void agregaListener(Rs232Listener listener){
		for (Rs232Listener l: listaDeListeners)
			if (l==listener)
				return;
		
		listaDeListeners.add(listener);
	}
	
	public void eliminaListener(Rs232Listener l){
		listaDeListeners.remove(l);
	}
	
	public synchronized void close() {
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
		}
	}

	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				int inputLine=input.read();
				//System.out.println(inputLine);
				for (Rs232Listener l: listaDeListeners)
					l.actualizaEnvio((char) inputLine);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

    

	
	public static void main(String[] args) throws Exception {
		Rs232 rs = new Rs232();

		rs.close();
	}
}
