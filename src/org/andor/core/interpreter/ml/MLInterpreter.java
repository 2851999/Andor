/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.interpreter.ml;

import java.util.List;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class MLInterpreter {
	
	/* Some of the syntax for interpreting */
	public static final String OBJECT_REFERENCE = "#";
	
	/* The static method used to log an error when interpreting an ML tree */
	public static void log(String message) {
		//Log the message
		Logger.log("ML Interpreter", message, Log.ERROR);
	}
	
	/* The static method used to find a ML Object with a specific name given the list of objects */
	public static MLInterpretedObject getObject(String name, List<MLInterpretedObject> interpretedObjects) {
		//Remove the object reference
		name = name.replace(OBJECT_REFERENCE, "");
		//Go through each object
		for (int a = 0; a < interpretedObjects.size(); a++) {
			//Check the name
			if (interpretedObjects.get(a).getName().equals(name))
				//Return the object
				return interpretedObjects.get(a);
		}
		//Log an error
		log("Couldn't find the object with the name " + name + " make sure it is defined and is above this objects declaration");
		//Return null
		return null;
	}
	
}