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
import org.andor.core.Object2DBuilder;
import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.interpreter.ml.MLInterpreter;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;
import org.andor.gui.GUIComponent;
import org.andor.gui.GUIComponentRenderer;

public class IntObj_GUIComponent extends MLInterpreterObject {

	/* The constructor */
	public IntObj_GUIComponent() {
		//Call the super constructor
		super("GUIComponent");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//The parameters that are expected to be found
		float width = currentObject.getParameter("width").getFloatValue();
		float height = currentObject.getParameter("height").getFloatValue();
		//Create the object
		GUIComponent component = new GUIComponent(Object2DBuilder.createQuad(width, height), width, height);
		//Assign the other values
		component.name = currentObject.getName();
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("visible"))
				component.visible = currentObject.getParameter(i).getBooleanValue();
			else if (currentObject.getParameter(i).getName().equals("active"))
				component.active = currentObject.getParameter(i).getBooleanValue();
			else if (currentObject.getParameter(i).getName().equals("repeatClickedEvents"))
				component.repeatClickedEvents = currentObject.getParameter(i).getBooleanValue();
			else if (currentObject.getParameter(i).getName().equals("borderEnabled"))
				component.borderEnabled = currentObject.getParameter(i).getBooleanValue();
			else if (currentObject.getParameter(i).getName().equals("colours")) {
				
			}
		}
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), component);
	}
	
	/* The static method used to setup a component */
	public static void interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects, GUIComponent component) {
		//The parameters that are expected to be found
		float width = currentObject.getParameter("width").getFloatValue();
		float height = currentObject.getParameter("height").getFloatValue();
		//Assign the other values
		component.name = currentObject.getName();
		component.width = width;
		component.height = height;
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("x"))
				component.position.x = currentObject.getParameter(i).getFloatValue();
			else if (currentObject.getParameter(i).getName().equals("y"))
				component.position.y = currentObject.getParameter(i).getFloatValue();
			else if (currentObject.getParameter(i).getName().equals("visible"))
				component.visible = currentObject.getParameter(i).getBooleanValue();
			else if (currentObject.getParameter(i).getName().equals("active"))
				component.active = currentObject.getParameter(i).getBooleanValue();
			else if (currentObject.getParameter(i).getName().equals("repeatClickedEvents"))
				component.repeatClickedEvents = currentObject.getParameter(i).getBooleanValue();
			else if (currentObject.getParameter(i).getName().equals("borderEnabled"))
				component.borderEnabled = currentObject.getParameter(i).getBooleanValue();
		}
	}
	
	/* The static method used to return a GUIComponentRenderer */
	public static GUIComponentRenderer interpretRenderer(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//The renderer
		GUIComponentRenderer renderer = null;
		//The parameters that are expected to be found
		float width = currentObject.getParameter("width").getFloatValue();
		float height = currentObject.getParameter("height").getFloatValue();
		//Check for the colours or textures
		if (currentObject.doesParameterExist("colours")) {
			//Get the array of objects that were referenced
			String[] objects = currentObject.getParameter("colours").getValue().split(",");
			//Setup the colours array
			Colour[] colours = new Colour[objects.length];
			//Go through each object
			for (int a = 0; a < objects.length; a++)
				//Assign the current colour
				colours[a] = ((Colour) MLInterpreter.getObject(objects[a], interpretedObjects).getObject());
			//Setup the renderer
			renderer = new GUIComponentRenderer(Object2DBuilder.createQuad(width, height, Colour.WHITE));
			renderer.set(colours);
		}
		//Return the renderer
		return renderer;
	}
	
}