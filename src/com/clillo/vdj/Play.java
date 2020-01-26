package com.clillo.vdj;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackListener;

public class Play extends AdvancedPlayer{
	
	public Play(InputStream arg0) throws JavaLayerException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public void playx() throws Exception{
		String nombreArchivo = "C:\\Audio\\@Bodega\\006\\Ed Sheeran - Thinking Out Loud.mp3";
		
		FileInputStream mp3in = new FileInputStream(nombreArchivo);
		AdvancedPlayer ap = new AdvancedPlayer(mp3in);

		while (!ap.play(1555))
			System.out.println("tic");
	}

	public static void main(String[] args) throws Exception {
		
		Play p = new Play(null);
		p.playx();
		/*
		String nombreArchivo = "C:\\Audio\\@Bodega\\006\\Ed Sheeran - Thinking Out Loud.mp3";
		String[] args2 = new String[1];
		args2[0] = nombreArchivo;
		
		FileInputStream mp3in = new FileInputStream(nombreArchivo);
		AdvancedPlayer ap = new AdvancedPlayer(mp3in);
		ap.play();
		*/
		//jlp player = jlp.createInstance(args2);

		//player.play();
		/*
		PrintStream out = System.out;
		
		FileInputStream mp3in = new FileInputStream(nombreArchivo);
		Bitstream in = new Bitstream(mp3in);
		
		InputStream id3in = in.getRawID3v2();
		int size = id3in.available();
		Header header = in.readFrame();
		if (out != null)
		{
			out.println("--- "+nombreArchivo+" ---");
			out.println("ID3v2Size="+size);
			out.println("version="+header.version());
			out.println("version_string="+header.version_string());
			out.println("layer="+header.layer());
			out.println("frequency="+header.frequency());
			out.println("frequency_string="+header.sample_frequency_string());
			out.println("bitrate="+header.bitrate());
			out.println("bitrate_string="+header.bitrate_string());
			out.println("mode="+header.mode());
			out.println("mode_string="+header.mode_string());
			out.println("slots="+header.slots());
			out.println("vbr="+header.vbr());
			out.println("vbr_scale="+header.vbr_scale());
			out.println("max_number_of_frames="+header.max_number_of_frames(mp3in.available()));
			out.println("min_number_of_frames="+header.min_number_of_frames(mp3in.available()));
			out.println("ms_per_frame="+header.ms_per_frame());
			out.println("frames_per_second="+(float) ((1.0 / (header.ms_per_frame())) * 1000.0));
			out.println("total_ms="+header.total_ms(mp3in.available()));
			out.println("SyncHeader="+header.getSyncHeader());
			out.println("checksums="+header.checksums());
			out.println("copyright="+header.copyright());
			out.println("original="+header.original());
			out.println("padding="+header.padding());
			out.println("framesize="+header.calculate_framesize());
			out.println("number_of_subbands="+header.number_of_subbands());				
		}
		
		in.close();
		mp3in.close();*/
	}
}
