package com.clillo.utiles;

public class HoraUtil {

	public static String toString(long msec){
		
		int minutos=0, segundos=0, milisegundos=0;
		
		if (msec>=1000){
			milisegundos = (int)(msec % 1000);
			msec = msec/1000;
			if (msec>=60){
				segundos = (int)(msec % 60);
				msec = msec/60;
				if (msec>=60){
					minutos = (int)(msec % 60);
				}else
					minutos	= (int)msec;
			}else
				segundos = (int)msec;
		}else
			return "";

		if (minutos>0 && segundos==0)
			return minutos +" min";

		if (minutos>0)
			return minutos +" m "+segundos + " s";

		if (segundos>0)
			return segundos +" seg";

		if (milisegundos>0)
			return milisegundos +" msec";
			
		return "";
	}

	public static String toStringElige(long msec){
		
		int minutos=0, segundos=0, milisegundos=0;
		
		if (msec>=1000){
			milisegundos = (int)(msec % 1000);
			msec = msec/1000;
			if (msec>=60){
				segundos = (int)(msec % 60);
				msec = msec/60;
				if (msec>=60){
					minutos = (int)(msec % 60);
				}else
					minutos	= (int)msec;
			}else
				segundos = (int)msec;
		}else
			return msec + " msec";

		if (minutos>0 && segundos==0)
			return minutos +" min";

		if (minutos>0)
			return minutos +" m "+segundos + " s";

		if (segundos>0)
			return segundos +" seg";

		if (milisegundos>0)
			return milisegundos +" msec";
			
		return "";
	}

}
