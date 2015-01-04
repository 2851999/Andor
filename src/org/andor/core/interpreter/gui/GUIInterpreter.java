/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.interpreter.gui;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.interpreter.ml.MLInterpretedObject;
import org.andor.core.interpreter.ml.MLInterpreter;
import org.andor.core.interpreter.ml.MLInterpreterMethods;
import org.andor.core.parser.ml.MLObject;
import org.andor.core.parser.ml.MLTree;
import org.andor.gui.GUIButton;
import org.andor.gui.GUIComponent;
import org.andor.gui.GUIDropDownMenu;
import org.andor.gui.GUIGroup;
import org.andor.gui.GUIPanel;
import org.andor.gui.GUIPosition;
import org.andor.gui.GUIWindow;

public class GUIInterpreter {
	
	/* Here are some parts of the syntax used when interpreting a GUI from ML */
	public static final String OBJECT_REFERENCE = "#";
	
	/* The current working directory */
	public static String WORKING_DIRECTORY = "";
	public static boolean WORKING_INFOLDER = false;
	
	/* The GUIPosition */
	public static GUIPosition positionPreference;
	
	/* The static method used to create a GUIPanel given an MLTree object and working directory */
	public static GUIPanel interpret(String panelName, boolean autoUpdate, MLTree tree, String workingDirectory, boolean workingInFolder) {
		//Assign the working directory
		WORKING_DIRECTORY = workingDirectory;
		WORKING_INFOLDER = workingInFolder;
		//Return the panel
		return interpret(panelName, autoUpdate, tree);
	}
	
	/* The static method used to create a GUIPanel given an MLTree object */
	public static GUIPanel interpret(String panelName, boolean autoUpdate, MLTree tree) {
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
			interpretObjects(interpretedObjects.get(interpretedObjects.size() - 1).getObject(), objects.get(a).getObjects(), interpretedObjects);
		}
	}
	
	/* The static method used to interpret an object */
	public static void interpretObject(Object object, MLObject currentObject, List<MLInterpretedObject> interpretedObjects) {
		//Interpret the object
		
		//Setup the position preference
		positionPreference = GUIPosition.NONE;
		
		//The GUIComponent
		GUIComponent component = GUIInterpreterObjects.interpret(currentObject, interpretedObjects);
		
		//Make sure the component isn't null
		if (component != null) {
			//Check the type of the object
			if (object instanceof GUIPanel)
				//Cast the object and add the component
				((GUIPanel) object).add(component);
			else if (object instanceof GUIDropDownMenu)
				//Cast the object and add the component
				((GUIDropDownMenu) object).addButton((GUIButton) component);
			else if (object instanceof GUIGroup)
				//Cast the object and add the component
				((GUIGroup) object).add(component);
			else if (object instanceof GUIWindow)
				//Cast the object and add the component
				((GUIWindow) object).add(component);
			else if (object instanceof GUIComponent)
				//Cast the object and add the component
				((GUIComponent) object).add(component, positionPreference);
		}
	}
	
	/* The static method used to interpret a value */
	public static Object interpret(String value, List<MLInterpretedObject> interpretedObjects) {
		//Check the value to see whether it is referencing an object
		if (value.startsWith(MLInterpreter.OBJECT_REFERENCE))
			//Return the object
			return MLInterpreter.getObject(value, interpretedObjects).getObject();
		else if (value.startsWith(MLInterpreter.METHOD_REFERENCE))
			//Return the object
			return MLInterpreterMethods.interpret(value);
		else
			//Return the value
			return value;
	}
	
}