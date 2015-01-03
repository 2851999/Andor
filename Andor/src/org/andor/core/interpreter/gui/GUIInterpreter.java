/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.interpreter.gui;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.parser.ml.MLObject;
import org.andor.core.parser.ml.MLTree;
import org.andor.gui.GUIComponent;
import org.andor.gui.GUIGroup;
import org.andor.gui.GUIPanel;

public class GUIInterpreter {
	
	/* Here are some parts of the syntax used when interpreting a GUI from ML */
	public static final String OBJECT_REFERENCE = "#";
	
	/* The static method used to create a GUIPanel given an MLTree object */
	public static GUIPanel interpret(String panelName, boolean autoUpdate, MLTree tree) {
		//Future update: could separate into two stages:
		//1. Create objects
		//2. Arrange objects
		//Could also add interpreter system for ML and the GUI one extends that system
		
		//The GUIPanel
		GUIPanel panel = new GUIPanel(panelName, autoUpdate);
		//The objects that have been currently interpreted
		List<MLInterpretedObject> interpretedObjects = new ArrayList<MLInterpretedObject>();
		//Interpret the objects
		interpretObjects(panel, tree.getObjects(), interpretedObjects);
		//Return the GUI
		return panel;
	}
	
	/* The static method used to interpret a list of objects and add it to another object */
	public static void interpretObjects(Object object, List<MLObject> objects, List<MLInterpretedObject> interpretedObjects) {
		//Go through each of the objects
		for (int a = 0; a < objects.size(); a++) {
			//Interpret the current object
			interpretObject(object, objects.get(a), interpretedObjects);
			//Interpret the objects within the current object
			interpretObjects(object, objects.get(a).getObjects(), interpretedObjects);
		}
	}
	
	/* The static method used to interpret an object */
	public static void interpretObject(Object object, MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//Interpret the object
		
		//The GUIComponent
		GUIComponent component = GUIInterpreterObjects.interpret(currentObject, interpretedObjects);
		
		//Make sure the component isn't null
		if (component != null) {
			//Check the type of the object
			if (object instanceof GUIPanel)
				//Cast the object and add the component
				((GUIPanel) object).add(component);
			else if (object instanceof GUIGroup)
				//Cast the object and add the component
				((GUIGroup) object).add(component);
			else if (object instanceof GUIComponent)
				//Cast the object and add the component
				((GUIComponent) object).add(component);
		}
	}
	
}