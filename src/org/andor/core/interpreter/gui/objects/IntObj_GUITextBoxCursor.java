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
import org.andor.core.parser.ml.MLParameter;
import org.andor.gui.GUIComponent;
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
				cursor.colour = ((Colour) GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects));
			else if (currentObject.getParameter(i).getName().equals("image"))
				//Get the object
				cursor.image = ((Image) GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects));
			else if (currentObject.getParameter(i).getName().equals("timeBetweenBlink"))
				//Assign the time between
				cursor.timeBetweenBlink = currentObject.getParameter(i).getLongValue();
			else if (currentObject.getParameter(i).getName().equals("thickness"))
				//Assign the current stage
				cursor.width = currentObject.getParameter(i).getFloatValue();
		}
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), cursor);
	}
	
	/* The method used to write a component */
	public void write(MLObject object, GUIComponent component) {
		
	}
	
	/* The static method used to write a cursor */
	public static void write(MLObject object, GUITextBoxCursor cursor) {
		//Check to see whether there is an overlay image
		if (cursor.hasImage()) {
			MLObject o = new MLObject("Image", object.getName() + "_image");
			//Write the object
			IntObj_Image.write(o, cursor.getImage());
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("image", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		if (cursor.hasColour()) {
			MLObject o = new MLObject("Colour", object.getName() + "_colour");
			//Write the object
			IntObj_Colour.write(o, cursor.getColour());
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("colour", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		//Add the parameters
		object.add(new MLParameter("timeBetweenBlink", "" + cursor.timeBetweenBlink));
		object.add(new MLParameter("thickness", "" + cursor.width));
	}
	
}