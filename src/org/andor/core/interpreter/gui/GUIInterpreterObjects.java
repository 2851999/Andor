/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.interpreter.gui;

import java.util.List;

import org.andor.core.interpreter.gui.objects.IntObj_Colour;
import org.andor.core.interpreter.gui.objects.IntObj_Font;
import org.andor.core.interpreter.gui.objects.IntObj_GUIBorder;
import org.andor.core.interpreter.gui.objects.IntObj_GUIButton;
import org.andor.core.interpreter.gui.objects.IntObj_GUICheckBox;
import org.andor.core.interpreter.gui.objects.IntObj_GUIComponent;
import org.andor.core.interpreter.gui.objects.IntObj_GUIDropDownList;
import org.andor.core.interpreter.gui.objects.IntObj_GUIDropDownMenu;
import org.andor.core.interpreter.gui.objects.IntObj_GUIGroup;
import org.andor.core.interpreter.gui.objects.IntObj_GUILabel;
import org.andor.core.interpreter.gui.objects.IntObj_GUILoadingBar;
import org.andor.core.interpreter.gui.objects.IntObj_GUIRadioCheckBox;
import org.andor.core.interpreter.gui.objects.IntObj_GUISlider;
import org.andor.core.interpreter.gui.objects.IntObj_GUITextBox;
import org.andor.core.interpreter.gui.objects.IntObj_GUITextBoxCursor;
import org.andor.core.interpreter.gui.objects.IntObj_GUITextBoxSelection;
import org.andor.core.interpreter.gui.objects.IntObj_GUIToolTip;
import org.andor.core.interpreter.gui.objects.IntObj_GUIWindow;
import org.andor.core.interpreter.gui.objects.IntObj_Image;
import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.interpreter.ml.MLInterpreter;
import org.andor.core.interpreter.ml.MLInterpreterObject;
import org.andor.core.parser.ml.MLObject;
import org.andor.gui.GUIComponent;

public class GUIInterpreterObjects {
	
	/* The array of available objects */
	public static MLInterpreterObject[] objects = new MLInterpreterObject[] {
		new IntObj_Colour(),
		new IntObj_GUIComponent(),
		new IntObj_GUIButton(),
		new IntObj_Image(),
		new IntObj_GUILabel(),
		new IntObj_GUIToolTip(),
		new IntObj_Font(),
		new IntObj_GUIBorder(),
		new IntObj_GUICheckBox(),
		new IntObj_GUIDropDownMenu(),
		new IntObj_GUIDropDownList(),
		new IntObj_GUILoadingBar(),
		new IntObj_GUIGroup(),
		new IntObj_GUIRadioCheckBox(),
		new IntObj_GUISlider(),
		new IntObj_GUITextBox(),
		new IntObj_GUITextBoxCursor(),
		new IntObj_GUITextBoxSelection(),
		new IntObj_GUIWindow()
	};
	
	/* The static method used to interpret an object and return the component */
	public static GUIComponent interpret(MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//The component
		GUIComponent component = null;
		//Get the object the current object represents
		MLInterpreterObject object = getObject(currentObject.getType());
		//Make sure the object was found
		if (object != null) { 
			//Interpret the object
			MLInterpretedObject interpretedObject = object.interpret(currentObject, interpretedObjects);
			//Add the object to the list
			interpretedObjects.add(interpretedObject);
			//Assign the component if possible
			if (interpretedObject.getObject() instanceof GUIComponent)
				component = ((GUIComponent) interpretedObject.getObject());
		}
		//Return the component
		return component;
	}
	
	/* The static method used to write an object */
	public static MLObject write(GUIComponent component) {
		//The MLObject
		MLObject object = null;
		//Get the type of component
		String objectType = component.getClass().getSimpleName();
		//Get the object the current object represents
		MLInterpreterObject interpreterObject = getObject(objectType);
		//Make sure the object was found
		if (interpreterObject != null) {
			//Assign the object
			object = new MLObject(objectType, component.name);
			//Write the object
			interpreterObject.write(object, component);
		}
		//Return the object
		return object;
	}
	
	/* The static method used to get an object with a specific name */
	public static MLInterpreterObject getObject(String name) {
		//Go through each object
		for (int a = 0; a < objects.length; a++) {
			//Check the name of the current object
			if (objects[a].getName().equals(name))
				//Return the object
				return objects[a];
		}
		//Log a message
		MLInterpreter.log("Object with the name " + name + " does not exist");
		//Return nothing
		return null;
	}
	
}