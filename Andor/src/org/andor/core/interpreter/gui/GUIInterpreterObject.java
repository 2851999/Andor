/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.interpreter.gui;

import org.andor.core.parser.ml.MLObject;
import org.andor.gui.GUIComponent;

public abstract class GUIInterpreterObject {
	
	/* The reference for this object */
	public String name;
	
	/* The constructor */
	public GUIInterpreterObject(String name) {
		//Assign the variables
		this.name = name;
	}
	
	/* The method used to interpret a specific object */
	public abstract GUIComponent interpret(MLObject currentObject);
	
	/* The method used to get the name */
	public String getName() { return this.name; }
	
}