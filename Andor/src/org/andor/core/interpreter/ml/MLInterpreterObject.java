/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.interpreter.ml;

import org.andor.core.parser.ml.MLObject;

public abstract class MLInterpreterObject {
	
	/* The reference for this object */
	public String name;
	
	/* The constructor */
	public MLInterpreterObject(String name) {
		//Assign the variables
		this.name = name;
	}
	
	/* The method used to interpret a specific object */
	public abstract MLInterpretedObject interpret(MLObject currentObject);
	
	/* The method used to get the name */
	public String getName() { return this.name; }
	
}