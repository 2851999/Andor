/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.interpreter.gui.objects;

import java.util.List;

import org.andor.core.Image;
import org.andor.core.ImageLoader;
import org.andor.core.interpreter.gui.GUIInterpreter;
import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;
import org.andor.core.parser.ml.MLParameter;
import org.andor.gui.GUIComponent;

public class IntObj_Image extends MLInterpreterObject {

	/* The constructor */
	public IntObj_Image() {
		//Call the super constructor
		super("Image");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//The parameters that are expected to be found
		String path = "";
		String location = "SAME"; //SAME, INTERNAL, EXTERNAL
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("path"))
				path = currentObject.getParameter(i).getValue();
			else if (currentObject.getParameter(i).getName().equals("location"))
				location = currentObject.getParameter(i).getValue();
		}
		//The image
		Image image = null;
		//Check the location and create the object
		if (location.equals("SAME"))
			//Load the image
			image = ImageLoader.loadImage(GUIInterpreter.WORKING_DIRECTORY + "/" + path, GUIInterpreter.WORKING_INFOLDER);
		else if (location.equals("INTERNAL"))
			//Load the image
			image = ImageLoader.loadImage(path, false);
		else if (location.equals("EXTERNAL"))
			//Load the image
			image = ImageLoader.loadImage(path, true);
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), image);
	}
	
	/* The method used to write a component */
	public void write(MLObject object, GUIComponent component) {
		
	}
	
	/* The static method used to write an image */
	public static void write(MLObject object, Image image) {
		//Write the parameters to the object
		object.add(new MLParameter("path", GUIInterpreter.save(image, object.getName())));
		object.add(new MLParameter("location", "SAME"));
	}
	
}