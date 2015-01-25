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
import org.andor.core.parser.ml.MLParameter;
import org.andor.gui.GUIComponent;

public class IntObj_Colour extends MLInterpreterObject {

	/* The constructor */
	public IntObj_Colour() {
		//Call the super constructor
		super("Colour");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//The parameters that are expected to be found
		float r = 0, g = 0, b = 0, a = 0;
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("r"))
				r = currentObject.getParameter(i).getIntegerValue() / 255;
			else if (currentObject.getParameter(i).getName().equals("g"))
				g = currentObject.getParameter(i).getIntegerValue() / 255;
			else if (currentObject.getParameter(i).getName().equals("b"))
				b = currentObject.getParameter(i).getIntegerValue() / 255;
			else if (currentObject.getParameter(i).getName().equals("a"))
				a = currentObject.getParameter(i).getIntegerValue() / 255;
		}
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), new Colour(r, g, b, a));
	}
	
	/* The method used to write a component */
	public void write(MLObject object, GUIComponent component) {
		
	}
	
	/* The static method used to write a colour to an object */
	public static void write(MLObject object, Colour colour) {
		//Add the parameters to the object
		object.add(new MLParameter("r", "" + (int) (colour.r * 255)));
		object.add(new MLParameter("g", "" + (int) (colour.g * 255)));
		object.add(new MLParameter("b", "" + (int) (colour.b * 255)));
		object.add(new MLParameter("a", "" + (int) (colour.a * 255)));
	}
	
}