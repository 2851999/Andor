/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.interpreter.gui.objects;

import java.util.List;

import org.andor.core.Colour;
import org.andor.core.Image;
import org.andor.core.interpreter.gui.GUIInterpreter;
import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;
import org.andor.gui.GUITextBoxCursor;

public class IntObj_GUITextBoxCursor extends MLInterpreterObject {

	/* The constructor */
	public IntObj_GUITextBoxCursor() {
		//Call the super constructor
		super("GUITextBoxCursor");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//Create the object
		GUITextBoxCursor cursor = new GUITextBoxCursor();
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("background")) {
				//Get the object
				Object object = GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects);
				//Check what type of object the object is
				if (object instanceof Image)
					//Assign the image
					cursor.setImage((Image) object);
				else if (object instanceof Colour)
					//Assign the colour
					cursor.setColour((Colour) object);
			} else if (currentObject.getParameter(i).getName().equals("colour"))
				//Get the object
				cursor.setColour((Colour) GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects));
			else if (currentObject.getParameter(i).getName().equals("image"))
				//Get the object
				cursor.setImage((Image) GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects));
			else if (currentObject.getParameter(i).getName().equals("timeBetweenBlink"))
				//Assign the current stage
				cursor.timeBetweenBlink = currentObject.getParameter(i).getLongValue();
		}
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), cursor);
	}
	
}