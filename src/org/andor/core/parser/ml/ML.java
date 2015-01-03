/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.parser.ml;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class ML {
	
	/* Some information about ML */
	public static final String DESCRIPTION = "ML (Markup Language) is a simple way to present text in a format that"
											+"can be used to create config files, that may be used to define GUI's";
	public static final String VERSION = "V0.0.1";
	public static final String DATE = "21/12/2014";
	
	/* The boolean that represents whether full verbose logs should be used */
	public static final boolean VERBOSE = true;
	
	/* The static method used to log some verbose information */
	public static void log(String message) {
		//Check to see whether verbose is enabled
		if (VERBOSE)
			//Log the message
			Logger.log("ML", message, Log.DEBUG);
	}
	
}