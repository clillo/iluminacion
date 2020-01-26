package com.clillo.dmx.core.escenas;

import com.clillo.dmx.configuracion.escenas.PuntosRoboticasCfg;
import com.clillo.dmx.ui.jpanels.opcionesMenu.escenas.PuntoRoboticas;

public class EscenaCanalPosicionRoboticas extends EscenaCanal {
	
	private String idPosicion;

	public EscenaCanalPosicionRoboticas(String canalStr, String valorStr) {
		super();
			
		this.setCanalStr(canalStr);
		idPosicion = valorStr;
		
		this.setValor(0);
		for (PuntoRoboticas pr: PuntosRoboticasCfg.getListaPuntos())
			if (pr.getId().equals(valorStr)){
				if (canalStr.equals("movingHead1.pan"))
					this.setValor(pr.getMovingHead1Pan());
				else if (canalStr.equals("movingHead1.tilt"))
					this.setValor(pr.getMovingHead1Tilt());
				else  if (canalStr.equals("movingHead2.pan"))
					this.setValor(pr.getMovingHead2Pan());
				else if (canalStr.equals("movingHead2.tilt"))
					this.setValor(pr.getMovingHead2Tilt());
				else 	if (canalStr.equals("movingHead1.panfine"))
					this.setValor(pr.getMovingHead1PanFine());
				else if (canalStr.equals("movingHead1.tiltfine"))
					this.setValor(pr.getMovingHead1TiltFine());
				else  if (canalStr.equals("movingHead2.panfine"))
					this.setValor(pr.getMovingHead2PanFine());
				else if (canalStr.equals("movingHead2.tiltfine"))
					this.setValor(pr.getMovingHead2TiltFine());

				else
					if (canalStr.equals("scanner1.pan"))
						this.setValor(pr.getScanner1Pan());
					else if (canalStr.equals("scanner1.tilt"))
						this.setValor(pr.getScanner1Tilt());
					else
						if (canalStr.equals("scanner2.pan"))
							this.setValor(pr.getScanner2Pan());
						else if (canalStr.equals("scanner2.tilt"))
							this.setValor(pr.getScanner2Tilt());
						else
							if (canalStr.equals("scanner3.pan"))
								this.setValor(pr.getScanner3Pan());
							else if (canalStr.equals("scanner3.tilt"))
								this.setValor(pr.getScanner3Tilt());
							else
								if (canalStr.equals("scanner4.pan"))
									this.setValor(pr.getScanner4Pan());
								else if (canalStr.equals("scanner4.tilt"))
									this.setValor(pr.getScanner4Tilt());
					
			}

		
	}
	
	public String getIdPosicion() {
		return idPosicion;
	}

	public void setIdPosicion(String idPosicion) {
		this.idPosicion = idPosicion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EscenaCanalPosicionRoboticas [");
		builder.append("id=").append(getId());
		builder.append(", canalStr=").append(getCanalStr());
		builder.append(", idDMX=").append(getIdDMX());
		builder.append(", valor=").append(getValor());
		builder.append(", idPosicion=").append(idPosicion);
		builder.append("]");
		return builder.toString();
	}
}
