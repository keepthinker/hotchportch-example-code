package com.keepthinker.example.log.log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Main {
	public static Logger logger = Logger.getLogger(Main.class);

	public static void main( String[] args )
	{
		log4jAllLevels();
		levelExample();
		newThread();
		sameNameLogger();
	}

	public static void log4jAllLevels(){
		logger.trace("trace");
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
		logger.fatal("fatal");
		Logger.getRootLogger().info("root");
		System.out.println();
	}

	public static void levelExample(){
		// get a logger instance named "com.foo"
		Logger  logger = Logger.getLogger("com.foo");

		// Now set its level. Normally you do not need to set the
		// level of a logger programmatically. This is usually done
		// in configuration files.
		logger.setLevel(Level.INFO);

		Logger barlogger = Logger.getLogger("com.foo.Bar");

		// This request is enabled, because WARN >= INFO.
		logger.warn("Low fuel level.");

		// This request is disabled, because DEBUG < INFO.
		logger.debug("Starting search for nearest gas station.");

		// The logger instance barlogger, named "com.foo.Bar",
		// will inherit its level from the logger named
		// "com.foo" Thus, the following request is enabled
		// because INFO >= INFO.
		barlogger.info("Located nearest gas station.");

		// This request is disabled, because DEBUG < INFO.
		barlogger.debug("Exiting gas station search");

		Logger parentLogger = Logger.getLogger("com");
		
		parentLogger.info("parent info");
		
		parentLogger.debug("parent info");

		parentLogger.setLevel(Level.WARN);

		logger.warn("Low fuel level.");
		
		logger.info("Low fuel level. info");

		logger.debug("Starting search for nearest gas station.");
		
		System.out.println();
	}

	public static void sameNameLogger(){;
		sameNameLogger(1);
	}
	public static void sameNameLogger(int time){
		for(int i = 0; i < time; i++){
			BasicConfigurator.configure();
			Logger x = Logger.getLogger("wombat");
			Logger y = Logger.getLogger("wombat");
			logger.info(x == y);
		}
	}

	public static void newThread(){
		new Thread(new Runnable(){
			@Override
			public void run() {
				logger.info("info");
			}
		}, "New thread").start();
	}
}
