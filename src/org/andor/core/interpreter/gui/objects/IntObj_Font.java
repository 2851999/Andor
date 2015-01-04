/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.interpreter.gui.objects;

import java.util.List;

import org.andor.core.BitmapText;
import org.andor.core.Colour;
import org.andor.core.Font;
import org.andor.core.Image;
import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.interpreter.ml.MLInterpreter;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;

public class IntObj_Font extends MLInterpreterObject {

	/* The constructor */
	public IntObj_Font() {
		//Call the super constructor
		super("Font");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//The parameters that are expected to be found
		Image image = null;
		int fontSize = 12;
		int gridSize = 16;
		Colour colour = Colour.WHITE;
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("image"))
				image = ((Image) MLInterpreter.getObject(currentObject.getParameter(i).getValue(), interpretedObjects).getObject());
			else if (currentObject.getParameter(i).getName().equals("fontSize"))
				fontSize = currentObject.getParameter(i).getIntegerValue();
			else if (currentObject.getParameter(i).getName().equals("gridSize"))
				gridSize = currentObject.getParameter(i).getIntegerValue();
			else if (currentObject.getParameter(i).getName().equals("colour"))
				colour = ((Colour) MLInterpreter.getObject(currentObject.getParameter(i).getValue(), interpretedObjects).getObject());
		}
		//Create the font
		Font font = new Font(new BitmapText(image, gridSize, fontSize, colour));
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), font);
	}
	
}