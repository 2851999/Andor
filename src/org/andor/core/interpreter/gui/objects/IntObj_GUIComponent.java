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
import org.andor.core.Font;
import org.andor.core.Image;
import org.andor.core.Object2DBuilder;
import org.andor.core.Settings;
import org.andor.core.interpreter.gui.GUIInterpreter;
import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.interpreter.ml.MLInterpreter;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;
import org.andor.core.parser.ml.MLParameter;
import org.andor.gui.GUIBorder;
import org.andor.gui.GUIComponent;
import org.andor.gui.GUIComponentRenderer;
import org.andor.gui.GUIPosition;
import org.andor.gui.GUIToolTip;

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
		}
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), component);
	}
	
	/* The static method used to setup a component */
	public static void interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects, GUIComponent component) {
		//Make sure the width and height have been assigned
		if (currentObject.doesParameterExist("width"))
			component.width = currentObject.getParameter("width").getFloatValue();
		if (currentObject.doesParameterExist("height"))
			component.height = currentObject.getParameter("height").getFloatValue();
		//Assign the other values
		component.name = currentObject.getName();
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
			else if (currentObject.getParameter(i).getName().equals("toolTip")) {
				component.toolTip = ((GUIToolTip) MLInterpreter.getObject(currentObject.getParameter(i).getValue(), interpretedObjects).getObject());
				component.toolTip.setComponent(component);
			} else if (currentObject.getParameter(i).getName().equals("font"))
				component.renderer.font = ((Font) MLInterpreter.getObject(currentObject.getParameter(i).getValue(), interpretedObjects).getObject());
			else if (currentObject.getParameter(i).getName().equals("border")) {
				component.border = ((GUIBorder) MLInterpreter.getObject(currentObject.getParameter(i).getValue(), interpretedObjects).getObject());
				component.border.setup(component);
			} else if (currentObject.getParameter(i).getName().equals("position"))
				GUIInterpreter.positionPreference = GUIPosition.valueOf(currentObject.getParameter(i).getValue());
			else if (currentObject.getParameter(i).getName().equals("inactiveColour"))
				component.renderer.inactiveColour = ((Colour) MLInterpreter.getObject(currentObject.getParameter(i).getValue(), interpretedObjects).getObject());
			else if (currentObject.getParameter(i).getName().equals("inactiveImage"))
				component.renderer.inactiveImage = ((Image) MLInterpreter.getObject(currentObject.getParameter(i).getValue(), interpretedObjects).getObject());
		}
	}
	
	/* The static method used to return a GUIComponentRenderer */
	public static GUIComponentRenderer interpretRenderer(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//The parameters that are expected to be found
		float width = currentObject.getParameter("width").getFloatValue();
		float height = currentObject.getParameter("height").getFloatValue();
		//Create the renderer
		GUIComponentRenderer renderer = new GUIComponentRenderer(Object2DBuilder.createQuad(width, height));
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
			//Setup the colours
			renderer.set(colours);
		}
		if (currentObject.doesParameterExist("images")) {
			//Get the array of objects that were referenced
			String[] objects = currentObject.getParameter("images").getValue().split(",");
			//Setup the images array
			Image[] images = new Image[objects.length];
			//Go through each object
			for (int a = 0; a < objects.length; a++)
				//Assign the current image
				images[a] = ((Image) MLInterpreter.getObject(objects[a], interpretedObjects).getObject());
			//Set the textures
			renderer.set(images);
		}
		//Return the renderer
		return renderer;
	}
	
	/* The method used to write a component */
	public void write(MLObject object, GUIComponent component) {
		
	}
	
	/* The static method used to write a component to an object */
	public static void writeObject(MLObject object, GUIComponent component) {
		//Write all of the parameters to the object
		object.add(new MLParameter("width", "" + component.getWidth()));
		object.add(new MLParameter("height", "" + component.getHeight()));
		object.add(new MLParameter("x", "" + component.position.x));
		object.add(new MLParameter("y", "" + component.position.y));
		object.add(new MLParameter("visible", "" + component.visible));
		object.add(new MLParameter("active", "" + component.active));
		object.add(new MLParameter("repeatClickedEvents", "" + component.repeatClickedEvents));
		object.add(new MLParameter("borderEnabled", "" + component.borderEnabled));
		//Write additional objects that are needed
		if (component.renderer.font != null && component.renderer.font.bitmapFont.image != Settings.Engine.DefaultFont.bitmapFont.image) {
			//Create the object
			MLObject o = new MLObject("Font", object.name + "_font");
			//Write the data to the object
			IntObj_Font.write(o, component.renderer.font);
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("font",GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		if (component.border != null) {
			//Create the object
			MLObject o = new MLObject("GUIBorder", object.name + "_border");
			//Write the data to the object
			IntObj_GUIBorder.write(o, component.border);
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("border", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		if (component.positionPreference != GUIPosition.NONE)
			//Add the parameter
			object.add(new MLParameter("position", component.positionPreference.toString()));
		if (component.renderer.inactiveColour != null) {
			//Create the object
			MLObject o = new MLObject("Colour", object.name + "_inactiveColour");
			//Write the data to the object
			IntObj_Colour.write(o, component.renderer.inactiveColour);
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("inactiveColour", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		if (component.renderer.inactiveImage != null) {
			//Create the object
			MLObject o = new MLObject("Image", object.name + "_inactiveImage");
			//Write the data to the object
			IntObj_Image.write(o, component.renderer.inactiveImage);
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("inactiveImage", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		if (component.toolTip != null) {
			//Create the object
			MLObject o = new MLObject("GUIToolTip", object.name + "_toolTip");
			//Write the data to the object
			IntObj_GUIToolTip.write(o, component.toolTip);
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("toolTip", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		
		//Write all of the parameters needed for the renderer
		if (component.renderer.colours != null) {
			//The current value of the parameter
			String parameterValue = "";
			//Go through each of the colours
			for (int a = 0; a < component.renderer.colours.length; a++) {
				//Create the object
				MLObject o = new MLObject("Colour", object.name + "_colours" + a);
				//Write the data to the object
				IntObj_Colour.write(o, component.renderer.colours[a]);
				//Add the object
				GUIInterpreter.addAdditionalObject(o);
				//Add the value to the parameter
				if (parameterValue.equals(""))
					parameterValue = GUIInterpreter.OBJECT_REFERENCE + o.getName();
				else
					parameterValue += "," + GUIInterpreter.OBJECT_REFERENCE + o.getName();
			}
			//Add the parameter to the object
			object.add(new MLParameter("colours", parameterValue));
		}
		//Write all of the parameters needed for the renderer
		if (component.renderer.images != null) {
			//The current value of the parameter
			String parameterValue = "";
			//Go through each of the colours
			for (int a = 0; a < component.renderer.images.length; a++) {
				//Create the object
				MLObject o = new MLObject("Image", object.name + "_images" + a);
				//Write the data to the object
				IntObj_Image.write(o, component.renderer.images[a]);
				//Add the object
				GUIInterpreter.addAdditionalObject(o);
				//Add the value to the parameter
				if (parameterValue.equals(""))
					parameterValue = GUIInterpreter.OBJECT_REFERENCE + o.getName();
				else
					parameterValue += "," + GUIInterpreter.OBJECT_REFERENCE + o.getName();
			}
			//Add the parameter to the object
			object.add(new MLParameter("images", parameterValue));
		}
	}
	
}