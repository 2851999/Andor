/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.input;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Controllers;

public class InputManagerController {
	
	/* The controllers that are being monitored */
	public static List<InputController> controllers;
	
	/* The static method used to create the controllers */
	public static void create() {
		//Try and catch any errors
		try {
			//Create the controllers
			Controllers.create();
			//Create the list of controllers
			controllers = new ArrayList<InputController>();
		} catch (LWJGLException e) {
			//Log an error
			Logger.log("InputManagerController create()" , "An error occurred when creating the Controllers" , Log.ERROR);
			e.printStackTrace();
		}
	}
	
	/* The static method used to check the input */
	public static void checkInput() {
		//Go through the list of controllers
		for (int a = 0; a < controllers.size(); a++)
			//Check the current controller's input
			controllers.get(a).checkInput();
	}
	
	/* The static method used to destroy the controllers */
	public static void destroy() {
		//Destroy the controllers
		Controllers.destroy();
	}
	
	/* The static method used to add a controller */
	public static void addController(InputController controller) {
		controllers.add(controller);
	}
	
}