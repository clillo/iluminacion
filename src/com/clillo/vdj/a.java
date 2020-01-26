package com.clillo.vdj;

import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class a {

	public static void main(String[] args) {
		String nombreArchivo = "C:\\Audio\\@Bodega\\006\\Ed Sheeran - Thinking Out Loud.mp3";
		try{
			FileInputStream mp3in = new FileInputStream(nombreArchivo);
		    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(mp3in);
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioInputStream);
		    clip.start();
		    while (clip.isRunning())
		    	;
		}
		catch(Exception ex)
		{
		}
	}
}
