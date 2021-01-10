package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.mhpositions;

public class Point {

	public static class Builder{
	    private int canal;
	    private int dmx;
	    
	    public Builder withCanal(int canal){
	    	this.canal = canal;
	    	return this;
	    }
	    
	    public Builder withDmx(int dmx){
	    	this.dmx = dmx;
	    	return this;
	    }
	    
	    public Point build(){
	    	Point p = new Point();
	    	p.setCanal(this.canal);
	    	p.setDmx(this.dmx);
	    	return p;
	    }
	}
	
    private int canal;
    private int dmx;
    
	public int getCanal() {
		return canal;
	}
	public void setCanal(int canal) {
		this.canal = canal;
	}
	public int getDmx() {
		return dmx;
	}
	public void setDmx(int dmx) {
		this.dmx = dmx;
	}
    

    
}
