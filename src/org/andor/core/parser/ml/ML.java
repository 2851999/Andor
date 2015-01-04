/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.parser.ml;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class ML {
	
	/* Some information about ML */
	public static final String DESCRIPTION = "ML (Markup Language) is a simple way to present text in a format that"
											+"can be used to create config files, that may be used to define GUI's";
	public static final String VERSION = "V1.0.0";
	public static final String DATE = "04/01/2015";
	
	/* The boolean that represents whether full verbose logs should be used */
	public static boolean VERBOSE = false;
	
	/* The static method used to log some verbose information */
	public static void log(String message) {
		//Check to see whether verbose is enabled
		if (VERBOSE)
			//Log the message
			Logger.log("ML", message, Log.DEBUG);
	}
	
}