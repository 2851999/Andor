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
import org.andor.core.interpreter.gui.GUIInterpreter;
import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.interpreter.ml.MLInterpreter;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;
import org.andor.core.parser.ml.MLParameter;
import org.andor.gui.GUIComponent;

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
		float fontSize = 12;
		int gridSize = 16;
		Colour colour = Colour.WHITE;
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("image"))
				image = ((Image) MLInterpreter.getObject(currentObject.getParameter(i).getValue(), interpretedObjects).getObject());
			else if (currentObject.getParameter(i).getName().equals("fontSize"))
				fontSize = currentObject.getParameter(i).getFloatValue();
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
	
	/* The method used to write a component */
	public void write(MLObject object, GUIComponent component) {
		
	}
	
	/* The static method used to write a font to an object */
	public static void write(MLObject object, Font font) {
		//Create the image object
		//Create the object
		MLObject o = new MLObject("Image", object.name + "_image");
		//Write the data to the object
		IntObj_Image.write(o, font.bitmapFont.renderer.material.diffuseTextureMap);
		//Add the object
		GUIInterpreter.addAdditionalObject(o);
		//Add the parameters
		object.add(new MLParameter("image", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		object.add(new MLParameter("fontSize", "" + font.bitmapFont.fontSize));
		object.add(new MLParameter("gridSize", "" + font.bitmapFont.gridWidth));
		//Create the object for the colour
		MLObject co = new MLObject("Colour", object.name + "_colour");
		//Write the data to the object
		IntObj_Colour.write(co, font.bitmapFont.colour[0]);
		//Add the object
		GUIInterpreter.addAdditionalObject(co);
		object.add(new MLParameter("colour", GUIInterpreter.OBJECT_REFERENCE + co.getName()));
	}
	
}