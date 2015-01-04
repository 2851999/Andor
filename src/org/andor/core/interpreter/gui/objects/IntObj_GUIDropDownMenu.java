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
import org.andor.core.interpreter.ml.MLInterpreter;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;
import org.andor.gui.GUIButton;
import org.andor.gui.GUIDropDownMenu;

public class IntObj_GUIDropDownMenu extends MLInterpreterObject {

	/* The constructor */
	public IntObj_GUIDropDownMenu() {
		//Call the super constructor
		super("GUIDropDownMenu");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//Create the object
		GUIDropDownMenu dropDownMenu = new GUIDropDownMenu((GUIButton) MLInterpreter.getObject("menuButton", interpretedObjects).getObject());
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("menuOpen"))
				dropDownMenu.menuOpen = currentObject.getParameter(i).getBooleanValue();
		}
		//Setup the rest of the component
		IntObj_GUIComponent.interpret(currentObject, interpretedObjects, dropDownMenu);
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), dropDownMenu);
	}
	
}