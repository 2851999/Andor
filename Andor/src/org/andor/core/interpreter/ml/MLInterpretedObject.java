/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.interpreter.ml;

public class MLInterpretedObject {
	
	/* The name of this object */
	public String name;
	
	/* The object this represents */
	public Object object;
	
	/* The constructor */
	public MLInterpretedObject(String name, Object object) {
		//Assign the variables
		this.name = name;
		this.object = object;
	}
	
	/* The 'get' methods */
	public String getName() { return this.name; }
	public Object getObject() { return this.object; }
	
}