package com.clillo.dmx.ui.jpanels.opcionesMenu.figurasRoboticas.escenas.mhpositions;

import java.util.Arrays;

public class MHPositionsList {

	private String id;
	private MHPositionNodeElement[] positions;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public MHPositionNodeElement[] getPositions() {
		return positions;
	}
	public void setPositions(MHPositionNodeElement[] positions) {
		this.positions = positions;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MHPositionsList [id=");
		builder.append(id);
		builder.append(", positions=");
		builder.append(Arrays.toString(positions));
		builder.append("]");
		return builder.toString();
	}


}
