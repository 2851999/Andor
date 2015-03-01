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
import org.andor.gui.GUIWindow;

public class IntObj_GUIWindow extends MLInterpreterObject {

	/* The constructor */
	public IntObj_GUIWindow() {
		//Call the super constructor
		super("GUIWindow");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//Create the object
		GUIWindow window = new GUIWindow(currentObject.getParameter("title").getValue(), currentObject.getParameter("width").getFloatValue(), currentObject.getParameter("height").getFloatValue());
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("background")) {
				//Get the object
				Object object = GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects);
				//Check what type of object the object is
				if (object instanceof Image)
					//Assign the image
					window.setImage((Image) object);
				else if (object instanceof Colour)
					//Assign the colour
					window.setColour((Colour) object);
			} else if (currentObject.getParameter(i).getName().equals("topBarBackground")) {
				//Get the object
				Object object = GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects);
				//Check what type of object the object is
				if (object instanceof Image)
					//Assign the image
					window.setTopImage((Image) object);
				else if (object instanceof Colour)
					//Assign the colour
					window.setTopColour((Colour) object);
			} else if (currentObject.getParameter(i).getName().equals("closeButton"))
				window.setCloseButton((GUIButton) MLInterpreter.getObject(currentObject.getParameter(i).getValue(), interpretedObjects).getObject());
		}
		//Setup the rest of the component
		IntObj_GUIComponent.interpret(currentObject, interpretedObjects, window);
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), window);
	}
	
	/* The method used to write a component */
	public void write(MLObject object, GUIComponent component) {
		//Check to see whether there is a background image
		if (((GUIWindow) component).hasImage()) {
			MLObject o = new MLObject("Image", object.getName() + "_image");
			//Write the object
			IntObj_Image.write(o, ((GUIWindow) component).getImage());
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("background", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		} else if (((GUIWindow) component).hasColour()) {
			MLObject o = new MLObject("Colour", object.getName() + "_colour");
			//Write the object
			IntObj_Colour.write(o, ((GUIWindow) component).getColour());
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("background", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		//Check to see whether there is a top bar background image
		if (((GUIWindow) component).hasTopImage()) {
			MLObject o = new MLObject("Image", object.getName() + "_image");
			//Write the object
			IntObj_Image.write(o, ((GUIWindow) component).getTopImage());
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("topBarBackground", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		} else if (((GUIWindow) component).hasTopColour()) {
			MLObject o = new MLObject("Colour", object.getName() + "_colour");
			//Write the object
			IntObj_Colour.write(o, ((GUIWindow) component).getTopColour());
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("topBarBackground", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		if (((GUIWindow) component).closeButton != null) {
			MLObject b = new MLObject("GUIButton", object.getName() + "_button");
			//Write the image
			GUIInterpreterObjects.getObject("GUIButton").write(b, ((GUISlider) component).sliderButton);
			//Add the object
			GUIInterpreter.addAdditionalObject(b);
			object.add(new MLParameter("closeButton", GUIInterpreter.OBJECT_REFERENCE + b.getName()));
		}
		//Add the parameters
		object.add(new MLParameter("title", ((GUIWindow) component).windowTitle));
		
		//Write the component's variables
		IntObj_GUIComponent.writeObject(object, component);
	}
	
}