package com.clillo.dmx.comm.serial;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;


public class SerialTest implements SerialPortEventListener {
	SerialPort serialPort;

	private static final String PORT_NAMES[] = { "COM4" };

	private BufferedReader input;
	private int n=0;

	private static final int TIME_OUT = 2000;

	private static final int DATA_RATE = 230400;// 115200;// 128000 ; //256000 ; //;// 230400;//57600;//9600;//2400; //4800

	public void initialize() {

		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			System.out.println(currPortId.getName());
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}

		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);

			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,  SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		//	serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8,  SerialPort.STOPBITS_1, SerialPort.PARITY_SPACE);
			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			
			// add event listeners
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
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
				
				System.out.print(imprimeNumero(inputLine)+" ");
				if (inputLine==1472 || n>100){
					System.out.println("|");
					n=0;
				}
					
				n++;
			//	if(n>=34){
				//	System.out.println(imprimeNumero(inputLine));
					//n=0;
//				}else
//					System.out.print(imprimeNumero(inputLine)+" ");
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

	private String imprimeNumero(int n){
		
		if (n==0 || n== 65533)
			return "   ";
		if(n<10)
			return "  "+n;
		if(n<100)
			return " "+n;
		return ""+n;
	}
	
	public static void main(String[] args) throws Exception {
		SerialTest main = new SerialTest();
		main.initialize();
		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		t.start();
		System.out.println("Started");
	}
}