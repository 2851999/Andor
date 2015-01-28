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
import org.andor.core.parser.ml.MLParameter;
import org.andor.gui.GUIComponent;
import org.andor.gui.GUIToolTip;

public class IntObj_GUIToolTip extends MLInterpreterObject {

	/* The constructor */
	public IntObj_GUIToolTip() {
		//Call the super constructor
		super("GUIToolTip");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//The parameters that are expected to be found
		String text = "";
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("text"))
				text = currentObject.getParameter(i).getValue();
		}
		//Create the object
		GUIToolTip toolTip = new GUIToolTip(text);
		//Setup the rest of the component
		IntObj_GUIComponent.interpret(currentObject, interpretedObjects, toolTip.label);
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), toolTip);
	}
	
	/* The method used to write a component */
	public void write(MLObject object, GUIComponent component) {
		
	}
	
	/* The static method used to write a tool tip to an object */
	public static void write(MLObject object, GUIToolTip toolTip) {
		//Add the parameters
		object.add(new MLParameter("text", toolTip.label.getText()));
		//Write the component's variables
		IntObj_GUIComponent.writeObject(object, toolTip.label);
	}
	
}