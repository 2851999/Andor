/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.interpreter.ml;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class MLInterpreter {
	
	/* The static method used to log an error when interpreting an ML tree */
	public static void log(String message) {
		//Log the message
		Logger.log("ML Interpreter", message, Log.ERROR);
	}
	
}