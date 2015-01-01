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

public class GUIInterpreterObjects {
	
	/* The array of available objects */
	public static GUIInterpreterObject[] objects = new GUIInterpreterObject[] {
		
	};
	
	/* The static method used to interpret an object and return the component */
	public static GUIComponent interpret(MLObject currentObject) {
		//The component
		GUIComponent component = null;
		//Get the object the current object represents
		GUIInterpreterObject object = getObject(currentObject.getType());
		//Make sure the object was found
		if (object != null)
			//Interpret the object
			component = object.interpret(currentObject);
		//Return the component
		return component;
	}
	
	/* The static method used to get an object with a specific name */
	public static GUIInterpreterObject getObject(String name) {
		//Go through each object
		for (int a = 0; a < objects.length; a++) {
			//Check the name of the current object
			if (objects[a].getName().equals(name))
				//Return the object
				return objects[a];
		}
		//Log a message
		MLGUI.log("Object with the name " + name + " does not exist");
		//Return nothing
		return null;
	}
	
}