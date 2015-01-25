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
import org.andor.core.interpreter.gui.GUIInterpreter;
import org.andor.core.interpreter.gui.GUIInterpreterObjects;
import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.interpreter.ml.MLInterpreter;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;
import org.andor.core.parser.ml.MLParameter;
import org.andor.gui.GUIButton;
import org.andor.gui.GUIComponent;
import org.andor.gui.GUIDropDownList;

public class IntObj_GUIDropDownList extends MLInterpreterObject {

	/* The constructor */
	public IntObj_GUIDropDownList() {
		//Call the super constructor
		super("GUIDropDownList");
	}
	
	/* The method used to interpret a specific object */
	public MLInterpretedObject interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//Create the object
		GUIDropDownList dropDownList = new GUIDropDownList((GUIButton) MLInterpreter.getObject(currentObject.getParameter("menuButton").getValue(), interpretedObjects).getObject());
		//Go through each parameter
		for (int i = 0; i < currentObject.getParameters().size(); i++) {
			//Check the name and assign the values
			if (currentObject.getParameter(i).getName().equals("menuOpen"))
				dropDownList.menuOpen = currentObject.getParameter(i).getBooleanValue();
			else if (currentObject.getParameter(i).getName().equals("overlayImage"))
				dropDownList.setupOverlay((Image) MLInterpreter.getObject(currentObject.getParameter(i).getValue(), interpretedObjects).getObject());
		}
		//Setup the rest of the component
		IntObj_GUIComponent.interpret(currentObject, interpretedObjects, dropDownList);
		//Return the object
		return new MLInterpretedObject(currentObject.getName(), dropDownList);
	}
	
	/* The method used to write a component */
	public void write(MLObject object, GUIComponent component) {
		//Create the menu button object
		MLObject o = new MLObject("GUIButton", object.getName() + "_menuButton");
		//Write the button
		GUIInterpreterObjects.getObject("GUIButton").write(o, ((GUIDropDownList) component).menuButton);
		//Add the object
		GUIInterpreter.addAdditionalObject(o);
		//Check to see whether there is an overlay image
		if (((GUIDropDownList) component).overlayImage != null) {
			MLObject io = new MLObject("Image", object.getName() + "_overlayImage");
			//Write the object
			IntObj_Image.write(io, ((GUIDropDownList) component).overlayImage);
			//Add the object
			GUIInterpreter.addAdditionalObject(io);
			//Add the parameter
			object.add(new MLParameter("overlayImage", GUIInterpreter.OBJECT_REFERENCE + io.getName()));
		}
		//Add the parameters
		object.add(new MLParameter("menuButton", GUIInterpreter.OBJECT_REFERENCE + o.getName()));
		object.add(new MLParameter("menuOpen", "" + ((GUIDropDownList) component).menuOpen));
		
		//Write the component's variables
		IntObj_GUIComponent.writeObject(object, component);
	}
	
}