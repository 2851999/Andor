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
	
	/* The method used to write a component */
	public void write(MLObject object, GUIComponent component) {
		//Check to see whether there is an overlay image
		if (((GUILoadingBar) component).hasBackgroundImage()) {
			MLObject bio = new MLObject("Image", object.getName() + "_backgroundImage");
			//Write the image
			IntObj_Image.write(bio, ((GUILoadingBar) component).getBackgroundImage());
			//Add the object
			GUIInterpreter.addAdditionalObject(bio);
			//Add the parameter
			object.add(new MLParameter("background", GUIInterpreter.OBJECT_REFERENCE + bio.getName()));
		} else if (((GUILoadingBar) component).hasBackgroundColour()) {
			MLObject bco = new MLObject("Colour", object.getName() + "_backgroundColour");
			//Write the colour
			IntObj_Colour.write(bco, ((GUILoadingBar) component).getBackgroundColour());
			//Add the object
			GUIInterpreter.addAdditionalObject(bco);
			//Add the parameter
			object.add(new MLParameter("background", GUIInterpreter.OBJECT_REFERENCE + bco.getName()));
		}
		
		if (((GUILoadingBar) component).hasFillImage()) {
			MLObject fio = new MLObject("Image", object.getName() + "_fillImage");
			//Write the image
			IntObj_Image.write(fio, ((GUILoadingBar) component).getFillImage());
			//Add the object
			GUIInterpreter.addAdditionalObject(fio);
			//Add the parameter
			object.add(new MLParameter("fill", GUIInterpreter.OBJECT_REFERENCE + fio.getName()));
		} else if (((GUILoadingBar) component).hasBackgroundColour()) {
			MLObject fco = new MLObject("Colour", object.getName() + "_FillColour");
			//Write the colour
			IntObj_Colour.write(fco, ((GUILoadingBar) component).getFillColour());
			//Add the object
			GUIInterpreter.addAdditionalObject(fco);
			//Add the parameter
			object.add(new MLParameter("fill", GUIInterpreter.OBJECT_REFERENCE + fco.getName()));
		}
		//Add the parameters
		object.add(new MLParameter("stages", "" + ((GUILoadingBar) component).loadingStages));
		if (((GUILoadingBar) component).currentLoadingStage != 0)
			object.add(new MLParameter("currentStage", "" + ((GUILoadingBar) component).currentLoadingStage));
		
		//Write the component's variables
		IntObj_GUIComponent.writeObject(object, component);
	}
	
}