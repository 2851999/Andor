/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.interpreter.gui;

import java.util.List;

import org.andor.core.interpreter.gui.objects.IntObj_GUIButton;
import org.andor.core.interpreter.gui.objects.IntObj_GUIColour;
import org.andor.core.interpreter.gui.objects.IntObj_GUIComponent;
import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.interpreter.ml.MLInterpreter;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;
import org.andor.gui.GUIComponent;

public class GUIInterpreterObjects {
	
	/* The array of available objects */
	public static MLInterpreterObject[] objects = new MLInterpreterObject[] {
		new IntObj_GUIColour(),
		new IntObj_GUIComponent(),
		new IntObj_GUIButton()
	};
	
	/* The static method used to interpret an object and return the component */
	public static GUIComponent interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//The component
		GUIComponent component = null;
		//Get the object the current object represents
		MLInterpreterObject object = getObject(currentObject.getType());
		//Make sure the object was found
		if (object != null) { 
			//Interpret the object
			MLInterpretedObject interpretedObject = object.interpret(currentObject, interpretedObjects);
			//Add the object to the list
			interpretedObjects.add(interpretedObject);
			//Assign the component if possible
			if (interpretedObject.getObject() instanceof GUIComponent)
				component = ((GUIComponent) interpretedObject.getObject());
		}
		//Return the component
		return component;
	}
	
	/* The static method used to get an object with a specific name */
	public static MLInterpreterObject getObject(String name) {
		//Go through each object
		for (int a = 0; a < objects.length; a++) {
			//Check the name of the current object
			if (objects[a].getName().equals(name))
				//Return the object
				return objects[a];
		}
		//Log a message
		MLInterpreter.log("Object with the name " + name + " does not exist");
		//Return nothing
		return null;
	}
	
}