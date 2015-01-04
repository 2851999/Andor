/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.interpreter.ml;

public abstract class MLInterpreterMethod {
	
	/* The reference for this object */
	public String name;
	
	/* The constructor */
	public MLInterpreterMethod(String name) {
		//Assign the variables
		this.name = name;
	}
	
	/* The method used to interpret a specific object */
	public abstract Object interpret(String[] parameters);
	
	/* The method used to get the name */
	public String getName() { return this.name; }
	
}