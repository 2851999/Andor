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
import org.andor.core.interpreter.gui.GUIInterpreterObjects;
import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.interpreter.ml.MLInterpreter;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;
import org.andor.core.parser.ml.MLParameter;
import org.andor.gui.GUIButton;
import org.andor.gui.GUIComponent;
import org.andor.gui.GUISlider;

public class IntObj_GUISlider extends MLInterpreterObject {

	/* The constructor */
	public IntObj_GUISlider() {
		//Call the super constructor
		super("GUISlider");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//The parameters that are expected to be found
		float width = currentObject.getParameter("width").getFloatValue();
		float height = currentObject.getParameter("height").getFloatValue();
		int direction = currentObject.getParameter("direction").getIntegerValue();
		//Create the object
		GUISlider slider = new GUISlider((GUIButton) MLInterpreter.getObject(currentObject.getParameter("button").getValue(), interpretedObjects).getObject(), direction, width, height);
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("background")) {
				//Get the object
				Object object = GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects);
				//Check what type of object the object is
				if (object instanceof Image)
					//Assign the image
					slider.setImage((Image) object);
				else if (object instanceof Colour)
					//Assign the colour
					slider.setColour((Colour) object);
			} else if (currentObject.getParameter(i).getName().equals("colour"))
				//Assign the colour
				slider.setColour((Colour) GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects));
			else if (currentObject.getParameter(i).getName().equals("image"))
				//Assign the image
				slider.setImage((Image) GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects)); 
		}
		//Setup the rest of the component
		IntObj_GUIComponent.interpret(currentObject, interpretedObjects, slider);
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), slider);
	}
	
	/* The method used to write a component */
	public void write(MLObject object, GUIComponent component) {
		MLObject b = new MLObject("GUIButton", object.getName() + "_button");
		//Write the image
		GUIInterpreterObjects.getObject("GUIButton").write(b, ((GUISlider) component).sliderButton);
		//Add the object
		GUIInterpreter.addAdditionalObject(b);
		//Check to see whether there is an overlay image
		if (((GUISlider) component).hasImage()) {
			MLObject o = new MLObject("Image", object.getName() + "_image");
			//Write the object
			IntObj_Image.write(o, ((GUISlider) component).getImage());
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("image", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		if (((GUISlider) component).hasColour()) {
			MLObject o = new MLObject("Colour", object.getName() + "_colour");
			//Write the object
			IntObj_Colour.write(o, ((GUISlider) component).getColour());
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("colour", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		//Add the parameters
		object.add(new MLParameter("button", GUIInterpreter.OBJECT_REFERENCE + b.getName()));
		object.add(new MLParameter("direction", "" + ((GUISlider) component).sliderDirection));
		
		//Write the component's variables
		IntObj_GUIComponent.writeObject(object, component);
	}
	
}