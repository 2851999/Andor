/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.input.InputController;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL11;

public class ControllerUtils {
	
	/* The static method used to return a list of available controllers */
	public static InputController[] getAvailableControllers() {
		//The list of controller indexes
		List<Integer> availableIndexes = new ArrayList<Integer>();
		//Go through the list of controllers
		for (int a = 0; a < 15; a++) {
			//Check to see whether the current controller exists
			if (isPresent(a))
				//Add the controller
				availableIndexes.add(a);
		}
		//Create the list of controllers
		InputController[] controllers = new InputController[availableIndexes.size()];
		//Go through the list of controllers
		for (int a = 0; a < availableIndexes.size(); a++)
			//Assign the controller in the list
			controllers[a] = new InputController(availableIndexes.get(a));
		//Return the list of controllers
		return controllers;
	}
	
	/* The static method that returns whether a particular controller is present */
	public static boolean isPresent(int index) {
		return GLFW.glfwJoystickPresent(index) == GL11.GL_TRUE;
	}
	
}