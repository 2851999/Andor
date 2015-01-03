/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class ThreadUtils {
	
	/* The static method used to pause the current thread
	 * for a given time in milliseconds */
	public static void sleepMilliseconds(long milliseconds) {
		//Catch any errors
		try {
			//Make the current thread sleep
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			//Log an error
			Logger.log("Andor - ThreadUtils sleepMilliseconds()" , "There was an exception when sleeping the thread", Log.ERROR);
			e.printStackTrace();
		}
	}
	
	/* The static method used to pause the current thread
	 * for a given time in seconds */
	public static void sleepSeconds(long seconds) {
		//Turn the seconds into milliseconds and then make the thread sleep
		//for that amount of time
		sleepMilliseconds(seconds * 1000);
	}
	
}