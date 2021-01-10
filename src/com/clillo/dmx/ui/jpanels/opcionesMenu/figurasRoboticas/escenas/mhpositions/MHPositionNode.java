package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.mhpositions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MHPositionNode {

    private String fixture;
    private int pan;
    private int panFine;
    private int tilt;
    private int tiltFine;

    public Map<String, Integer> getDMXMap(){
        Map<String, Integer> map = new HashMap<>();
        map.put("pan", pan);
        map.put("tilt", tilt);
        map.put("panFine", panFine);
        map.put("tiltFine", tiltFine);
        return map;
    }

    public List<Point> getPointList(final Map<String, Integer> fixtureMap) {
        final List<Point> pointList = new ArrayList<Point>();

        final Map<String, Integer> positionMap = this.getDMXMap();

        for(String canal: fixtureMap.keySet()){
            if (positionMap.containsKey(canal)) {
                int dmxCanal = fixtureMap.get(canal);
                int dmxValue = positionMap.get(canal);
                pointList.add(new Point.Builder()
                        .withCanal(dmxCanal)
                        .withDmx(dmxValue)
                        .build());
            }
        }

        return pointList;
    }

	public String getFixture() {
		return fixture;
	}

	public void setFixture(String fixture) {
		this.fixture = fixture;
	}

	public int getPan() {
		return pan;
	}

	public void setPan(int pan) {
		this.pan = pan;
	}

	public int getPanFine() {
		return panFine;
	}

	public void setPanFine(int panFine) {
		this.panFine = panFine;
	}

	public int getTilt() {
		return tilt;
	}

	public void setTilt(int tilt) {
		this.tilt = tilt;
	}

	public int getTiltFine() {
		return tiltFine;
	}

	public void setTiltFine(int tiltFine) {
		this.tiltFine = tiltFine;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MHPositionNode [fixture=");
		builder.append(fixture);
		builder.append(", pan=");
		builder.append(pan);
		builder.append(", panFine=");
		builder.append(panFine);
		builder.append(", tilt=");
		builder.append(tilt);
		builder.append(", tiltFine=");
		builder.append(tiltFine);
		builder.append("]");
		return builder.toString();
	}
    
	
    
}
