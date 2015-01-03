/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import org.andor.core.input.InputController;
import org.lwjgl.input.Controllers;

public class ControllerUtils {
	
	/* The static method that returns a list of all the available controllers */
	public static InputController[] getAvailableControllers() {	
		//Get the number of controllers
		int count = Controllers.getControllerCount();
		//The controllers array
		InputController[] controllers = new InputController[count];
		//Go through the controllers
		for (int a = 0; a < controllers.length; a++)
			//Assign the current controller
			controllers[a] = new InputController(a);
		//Return the controllers
		return controllers;
	}
	
}