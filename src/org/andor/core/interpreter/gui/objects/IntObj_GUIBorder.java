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
import org.andor.core.interpreter.ml.MLInterpreter;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;
import org.andor.core.parser.ml.MLParameter;
import org.andor.gui.GUIBorder;
import org.andor.gui.GUIComponent;

public class IntObj_GUIBorder extends MLInterpreterObject {

	/* The constructor */
	public IntObj_GUIBorder() {
		//Call the super constructor
		super("GUIBorder");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//The parameters that are expected to be found
		Image image = null;
		Colour colour = Colour.BLACK;
		float thickness = 1;
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("image"))
				image = ((Image) MLInterpreter.getObject(currentObject.getParameter(i).getValue(), interpretedObjects).getObject());
			else if (currentObject.getParameter(i).getName().equals("thickness"))
				thickness = currentObject.getParameter(i).getFloatValue();
			else if (currentObject.getParameter(i).getName().equals("colour"))
				colour = ((Colour) GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects));
		}
		//The border
		GUIBorder border = null;
		//Create the border
		if (image == null)
			border = new GUIBorder(thickness, colour);
		else
			border = new GUIBorder(thickness, image);
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), border);
	}
	
	/* The method used to write a component */
	public void write(MLObject object, GUIComponent component) {
		
	}
	
	/* The static method used to write a border to an object */
	public static void write(MLObject object, GUIBorder border) {
		//Check to see whether there is an overlay image
		if (border.hasImage()) {
			MLObject o = new MLObject("Image", object.getName() + "_image");
			//Write the image
			IntObj_Image.write(o, border.getImage());
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("image", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		if (border.hasColour()) {
			MLObject o = new MLObject("Colour", object.getName() + "_colour");
			//Write the colour
			IntObj_Colour.write(o, border.getColour());
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("colour", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		//Add the parameters
		object.add(new MLParameter("thickness", "" + border.getThickness()));
	}
	
}