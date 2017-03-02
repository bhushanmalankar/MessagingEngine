package my.assignment.messaging.commons;

import my.assignment.messaging.service.MessagingEngine;

public class Bootstrap {
	
	public static boolean debug;
	
	public static void start(){
		debug=false;
		MessagingEngine.resetMessageCounter();
		ApplicationCache applicationCache = new ApplicationCache();
		applicationCache.init();
		
	}

}
