package com.clillo.vdj;

import java.io.FileInputStream;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;

public class AdvancedPlayer {

	private Bitstream bitstream;
	private Decoder decoder;
	private AudioDevice audio;
	private boolean closed = false;
	private String nombreArchivo;
	private double cantidadFrames;
	private double pos;

	public AdvancedPlayer(String nombreArchivo) throws Exception {
		this.nombreArchivo = nombreArchivo;
		bitstream = new Bitstream(new FileInputStream(nombreArchivo));

		cantidadFrames = cuentaFrames();

		audio = FactoryRegistry.systemRegistry().createAudioDevice();
		audio.open(decoder = new Decoder());
	}

	/**
	 * Plays a number of MPEG audio frames.
	 *
	 * @param frames The number of frames to play.
	 * @return true if the last frame was played, or false if there are more frames.
	 */
	public boolean play() throws JavaLayerException {
		boolean ret = true;

		int n=0;
		while (ret) {
			pos = n/cantidadFrames;
			if (n%10==0)
				System.out.println(n+"\t"+audio.getPosition()+"\t"+pos);
			ret = decodeFrame();
			n++;
		}

		AudioDevice out = audio;
		if (out != null) {
			
			out.flush();
			
			synchronized (this) {	
				close();
			}
		}
		return ret;
	}

	public synchronized void close() {
		AudioDevice out = audio;
		if (out != null) {
			audio = null;
			out.close();			
			try {
				bitstream.close();
			} catch (BitstreamException ex) {
			}
		}
	}

	private boolean decodeFrame() throws JavaLayerException {
		try {
			AudioDevice out = audio;
			if (out == null)
				return false;

			Header h = bitstream.readFrame();
			if (h == null)
				return false;

			SampleBuffer output = (SampleBuffer) decoder.decodeFrame(h,	bitstream);

			synchronized (this) {
				out = audio;
				if (out != null) 
					out.write(output.getBuffer(), 0, output.getBufferLength());
			}

			bitstream.closeFrame();
		} catch (RuntimeException ex) {
			throw new JavaLayerException("Exception decoding audio frame", ex);
		}
		return true;
	}

	private int cuentaFrames() throws Exception {
		FileInputStream mp3in = new FileInputStream(nombreArchivo);
		Bitstream  bitstream = new Bitstream(mp3in);
		int n=0;
		while (true){
			Header h = bitstream.readFrame();
			if (h == null){
				bitstream.close();
				return n;
			}
			bitstream.closeFrame();
			n++;
		}		
	}
	
	public void stop() {
		close();
	}
	
	public static void main(String[] args) throws Exception {
		String nombreArchivo = "C:\\Audio\\@Bodega\\006\\Ed Sheeran - Thinking Out Loud.mp3";
		AdvancedPlayer ap = new AdvancedPlayer(nombreArchivo);
		ap.play();
	}
}
