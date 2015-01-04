/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.interpreter.ml.methods;

import org.andor.core.Colour;
import org.andor.core.interpreter.ml.MLInterpreterMethod;

public class IntMethod_RGBA extends MLInterpreterMethod {
	
	/* The constructor */
	public IntMethod_RGBA() {
		//Call the super constructor
		super("rgba");
	}
	
	/* The method used to interpret a specific object */
	public Object interpret(String[] parameters) {
		//Check the number of parameters
		if (parameters.length == 4)
			//Return the colour
			return new Colour(Integer.parseInt(parameters[0]) / 255, Integer.parseInt(parameters[1]) / 255, Integer.parseInt(parameters[2]) / 255, Integer.parseInt(parameters[3]) / 255);
		//Return nothing
		return null;
	}
	
}