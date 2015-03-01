/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.interpreter.gui.objects;

import java.util.List;

import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;
import org.andor.core.parser.ml.MLParameter;
import org.andor.gui.GUICheckBox;
import org.andor.gui.GUIComponent;

public class IntObj_GUICheckBox extends MLInterpreterObject {

	/* The constructor */
	public IntObj_GUICheckBox() {
		//Call the super constructor
		super("GUICheckBox");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//The parameters that are expected to be found
		String text = null;
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("text"))
				text = currentObject.getParameter(i).getValue();
		}
		//Create the object
		GUICheckBox checkBox = new GUICheckBox(IntObj_GUIComponent.interpretRenderer(currentObject, interpretedObjects));
		//Assign the variables
		checkBox.text = text;
		//Setup the rest of the component
		IntObj_GUIComponent.interpret(currentObject, interpretedObjects, checkBox);
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), checkBox);
	}
	
	/* The method used to write a component */
	public void write(MLObject object, GUIComponent component) {
		//Add the parameters
		object.add(new MLParameter("text", ((GUICheckBox) component).text));
		//Write the component's variables
		IntObj_GUIComponent.writeObject(object, component);
	}
	
}