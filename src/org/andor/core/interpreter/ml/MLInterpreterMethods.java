/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.interpreter.ml;

import org.andor.core.interpreter.ml.methods.IntMethod_RGB;
import org.andor.core.parser.utils.ParserUtils;

public abstract class MLInterpreterMethods {
	
	/* The array of available methods */
	public static MLInterpreterMethod[] methods = new MLInterpreterMethod[] {
		new IntMethod_RGB()
	};
	
	/* The static method used to find a method then interpret some information to return an object */
	public static Object interpret(String value) {
		//Remove the method declaration
		value = value.substring(1);
		//Get the method name
		String methodName = value.substring(0, value.indexOf(MLInterpreter.METHOD_PARAMETERS_OPEN));
		//Get the parameters
		String[] methodParameters = value.substring(value.indexOf(MLInterpreter.METHOD_PARAMETERS_OPEN) + 1, value.indexOf(MLInterpreter.METHOD_PARAMETERS_CLOSE)).split(",");
		//Clean up the parameters
		ParserUtils.removeWhitespace(methodParameters);
		//Return the object
		return getMethod(methodName).interpret(methodParameters);
	}
	
	/* The static method used to get an object with a specific name */
	public static MLInterpreterMethod getMethod(String name) {
		//Go through each object
		for (int a = 0; a < methods.length; a++) {
			//Check the name of the current object
			if (methods[a].getName().equals(name))
				//Return the object
				return methods[a];
		}
		//Log a message
		MLInterpreter.log("Method with the name " + name + " does not exist");
		//Return nothing
		return null;
	}
	
}