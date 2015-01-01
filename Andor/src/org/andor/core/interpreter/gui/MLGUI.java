/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.interpreter.gui;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class MLGUI {
	
	/* The static method used to log an error when interpreting a GUI */
	public static void log(String message) {
		//Log the message
		Logger.log("ML GUI Interpreter", message, Log.ERROR);
	}
	
}