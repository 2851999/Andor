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
import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;

public class IntObj_GUIColour extends MLInterpreterObject {

	/* The constructor */
	public IntObj_GUIColour() {
		//Call the super constructor
		super("GUIColour");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//The variables that are expected to be found
		int r = 0, g = 0, b = 0, a = 0;
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("r"))
				r = currentObject.getParameter(i).getIntegerValue();
			else if (currentObject.getParameter(i).getName().equals("g"))
				g = currentObject.getParameter(i).getIntegerValue();
			else if (currentObject.getParameter(i).getName().equals("b"))
				b = currentObject.getParameter(i).getIntegerValue();
			else if (currentObject.getParameter(i).getName().equals("a"))
				a = currentObject.getParameter(i).getIntegerValue();
		}
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), new Colour(r, g, b, a));
	}
	
}