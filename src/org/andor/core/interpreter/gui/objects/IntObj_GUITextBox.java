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
import org.andor.core.interpreter.gui.GUIInterpreter;
import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.interpreter.ml.MLInterpreter;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;
import org.andor.core.parser.ml.MLParameter;
import org.andor.gui.GUIComponent;
import org.andor.gui.GUITextBox;
import org.andor.gui.GUITextBoxCursor;
import org.andor.gui.GUITextBoxSelection;

public class IntObj_GUITextBox extends MLInterpreterObject {

	/* The constructor */
	public IntObj_GUITextBox() {
		//Call the super constructor
		super("GUITextBox");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//The parameters that are expected to be found
		float width = currentObject.getParameter("width").getFloatValue();
		float height = currentObject.getParameter("height").getFloatValue();
		//Create the object
		GUITextBox textBox = new GUITextBox(width, height);
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("background")) {
				//Get the object
				Object object = GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects);
				//Check what type of object the object is
				if (object instanceof Image)
					//Assign the image
					textBox.setImage((Image) object);
				else if (object instanceof Colour)
					//Assign the colour
					textBox.setColour((Colour) object);
			} else if (currentObject.getParameter(i).getName().equals("colour"))
				//Assign the colour
				textBox.setColour((Colour) GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects));
			else if (currentObject.getParameter(i).getName().equals("image"))
				//Assign the image
				textBox.setImage((Image) GUIInterpreter.interpret(currentObject.getParameter(i).getValue(), interpretedObjects));
			else if (currentObject.getParameter(i).getName().equals("text"))
				//Assign the text
				textBox.text = currentObject.getParameter(i).getValue();
			else if (currentObject.getParameter(i).getName().equals("selected"))
				//Assign the variable
				textBox.selected = currentObject.getParameter(i).getBooleanValue();
			else if (currentObject.getParameter(i).getName().equals("masked"))
				//Assign the variable
				textBox.masked = currentObject.getParameter(i).getBooleanValue();
			else if (currentObject.getParameter(i).getName().equals("mask"))
				//Assign the variable
				textBox.mask = currentObject.getParameter(i).getValue();
			else if (currentObject.getParameter(i).getName().equals("defaultText"))
				//Assign the variable
				textBox.defaultText = currentObject.getParameter(i).getValue();
			else if (currentObject.getParameter(i).getName().equals("defaultTextFont"))
				//Assign the variable
				textBox.defaultTextFont = ((Font) MLInterpreter.getObject(currentObject.getParameter(i).getValue(), interpretedObjects).getObject());
			else if (currentObject.getParameter(i).getName().equals("cursor")) {
				//Assign the variable
				textBox.cursor = ((GUITextBoxCursor) MLInterpreter.getObject(currentObject.getParameter(i).getValue(), interpretedObjects).getObject());
				textBox.cursor.setup(textBox);
			} else if (currentObject.getParameter(i).getName().equals("selection")) {
				//Assign the variable
				textBox.selection = (GUITextBoxSelection) (MLInterpreter.getObject(currentObject.getParameter(i).getValue(), interpretedObjects).getObject());
				textBox.selection.setup(textBox);
			}
		}
		//Setup the rest of the component
		IntObj_GUIComponent.interpret(currentObject, interpretedObjects, textBox);
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), textBox);
	}
	
	/* The method used to write a component */
	public void write(MLObject object, GUIComponent component) {
		//Check to see whether there is an overlay image
		if (((GUITextBox) component).hasImage()) {
			MLObject o = new MLObject("Image", object.getName() + "_image");
			//Write the object
			IntObj_Image.write(o, ((GUITextBox) component).getImage());
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("image", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		if (((GUITextBox) component).hasColour()) {
			MLObject o = new MLObject("Colour", object.getName() + "_colour");
			//Write the object
			IntObj_Colour.write(o, ((GUITextBox) component).getColour());
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("colour", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		if (((GUITextBox) component).defaultTextFont != null) {
			//Create the object
			MLObject o = new MLObject("Font", object.name + "_font");
			//Write the data to the object
			IntObj_Font.write(o, component.renderer.font);
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("font",GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		if (((GUITextBox) component).cursor != null) {
			//Create the object
			MLObject o = new MLObject("GUITextBoxCursor", object.name + "_cursor");
			//Write the data to the object
			IntObj_GUITextBoxCursor.write(o, ((GUITextBox) component).cursor);
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("cursor",GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		if (((GUITextBox) component).selection != null) {
			//Create the object
			MLObject o = new MLObject("GUITextBoxSelection", object.name + "_selection");
			//Write the data to the object
			IntObj_GUITextBoxSelection.write(o, ((GUITextBox) component).selection);
			//Add the object
			GUIInterpreter.addAdditionalObject(o);
			//Add the parameter
			object.add(new MLParameter("selection",GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		}
		//Add the parameters
		object.add(new MLParameter("text", ((GUITextBox) component).text));
		object.add(new MLParameter("selected", "" + ((GUITextBox) component).selected));
		object.add(new MLParameter("masked", "" + ((GUITextBox) component).masked));
		object.add(new MLParameter("mask", ((GUITextBox) component).mask));
		object.add(new MLParameter("defaultText", "" + ((GUITextBox) component).defaultText));
		
		//Write the component's variables
		IntObj_GUIComponent.writeObject(object, component);
	}
	
}