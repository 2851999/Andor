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
import org.andor.gui.GUILoadingBar;

public class IntObj_GUILoadingBar extends MLInterpreterObject {

	/* The constructor */
	public IntObj_GUILoadingBar() {
		//Call the super constructor
		super("GUILoadingBar");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//The parameters that are expected to be found
		int loadingStages = currentObject.getParameter("stages").getIntegerValue();
		float width = currentObject.getParameter("width").getFloatValue();
		float height = currentObject.getParameter("height").getFloatValue();
		//Create the object
		GUILoadingBar loadingBar = new GUILoadingBar(loadingStages, width, height);
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("background")) {
				//Get the object
				Object object = GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects);
				//Check what type of object the object is
				if (object instanceof Image)
					//Assign the image
					loadingBar.setBackgroundImage((Image) object);
				else if (object instanceof Colour)
					//Assign the colour
					loadingBar.setBackgroundColour((Colour) object);
			} else if (currentObject.getParameter(i).getName().equals("fill")) {
				//Get the object
				Object object = GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects);
				//Check what type of object the object is
				if (object instanceof Image)
					//Assign the image
					loadingBar.setFillImage((Image) object);
				else if (object instanceof Colour)
					//Assign the colour
					loadingBar.setFillColour((Colour) object);
			} else if (currentObject.getParameter(i).getName().equals("currentStage"))
				//Assign the current stage
				loadingBar.currentLoadingStage = currentObject.getParameter(i).getIntegerValue();
		}
		//Setup the rest of the component
		IntObj_GUIComponent.interpret(currentObject, interpretedObjects, loadingBar);
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), loadingBar);
	}
	
}