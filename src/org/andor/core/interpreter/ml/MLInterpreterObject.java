/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.interpreter.ml;

import java.util.List;

import org.andor.core.parser.ml.MLObject;
import org.andor.gui.GUIComponent;

public abstract class MLInterpreterObject {
	
	/* The reference for this object */
	public String name;
	
	/* The constructor */
	public MLInterpreterObject(String name) {
		//Assign the variables
		this.name = name;
	}
	
	/* The method used to interpret a specific object */
	public abstract MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects);
	
	/* The method used to write a component */
	public abstract void write(MLObject object, GUIComponent component);
	
	/* The method used to get the name */
	public String getName() { return this.name; }
	
}