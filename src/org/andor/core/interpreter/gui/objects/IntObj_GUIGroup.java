/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.interpreter.gui.objects;

import java.util.List;

import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;
import org.andor.gui.GUIComponent;
import org.andor.gui.GUIGroup;

public class IntObj_GUIGroup extends MLInterpreterObject {

	/* The constructor */
	public IntObj_GUIGroup() {
		//Call the super constructor
		super("GUIGroup");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//Create the object
		GUIGroup group = new GUIGroup(currentObject.name);
		//Setup the rest of the component
		IntObj_GUIComponent.interpret(currentObject, interpretedObjects, group);
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), group);
	}
	
	/* The method used to write a component */
	public void write(MLObject object, GUIComponent component) {
		//Write the component
		IntObj_GUIComponent.writeObject(object, component);
	}
	
}