package com.clillo.utiles;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Log{

	private static Logger logger = null;

	static{
		try {
			logger = Logger.getRootLogger();
			logger.setAdditivity(true);
			PatternLayout layout = new PatternLayout("[%d{ISO8601}] %m%n");
			FileAppender appender = new DailyRollingFileAppender(layout, "../iluminacion.log", "'.['dd_MM_yyyy'].log'" );
			logger.addAppender(appender);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void manejaExcepcion(Throwable e){
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		logger.error(sw.toString());	
	}
	
	public static void debug(String text, String className) {
		StringBuilder sb = new StringBuilder();
		sb.append("[DEBUG][");
        sb.append(className);
        sb.append("] ");
        sb.append(text);
        
        logger.debug(sb.toString());
	}

	@SuppressWarnings("rawtypes")
	public static void debug(String toLog, Class c) {
		String clase = c.getName();
		debug(toLog, clase.substring(clase.lastIndexOf(".") + 1));
	}
}
