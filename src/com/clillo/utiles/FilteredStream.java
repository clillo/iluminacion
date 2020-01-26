package com.clillo.utiles;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class FilteredStream extends FilterOutputStream{
	
	private JTextArea text;

	public FilteredStream(OutputStream out,JTextArea text) {
		super(out);
		this.text = text;
	}

    public void write(byte b[]) throws IOException {
        String aString = new String(b);
        refrescaFrame(aString);
     }

    public void write(byte b[], int off, int len) throws IOException {
        String aString = new String(b , off , len);
        refrescaFrame(aString);
    }
    
    private void refrescaFrame(String aString){
    	text.append(aString);
    }
}