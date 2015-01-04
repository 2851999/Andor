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
import org.andor.gui.GUIButton;

public class IntObj_GUIButton extends MLInterpreterObject {

	/* The constructor */
	public IntObj_GUIButton() {
		//Call the super constructor
		super("GUIButton");
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
		GUIButton button = new GUIButton(IntObj_GUIComponent.interpretRenderer(currentObject, interpretedObjects));
		//Assign the variables
		button.text = text;
		//Setup the rest of the component
		IntObj_GUIComponent.interpret(currentObject, interpretedObjects, button);
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), button);
	}
	
}