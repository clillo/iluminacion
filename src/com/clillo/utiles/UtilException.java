package com.clillo.utiles;


public class UtilException extends Exception{

	private static final long serialVersionUID = 1L;

	public UtilException(String msg) {
		super(msg);
	}
	
	public UtilException(Exception e) {
		super(e.getMessage());
		this.setStackTrace(e.getStackTrace());
	}
	
}
