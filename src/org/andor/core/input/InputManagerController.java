/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

import java.util.ArrayList;
import java.util.List;

public class InputManagerController {
	
	/* The controllers that are being monitored */
	public static List<InputController> controllers = new ArrayList<InputController>();;
	
	/* The static method used to create the controllers */
	public static void create() {
		
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
		
	}
	
	/* The static method used to add a controller */
	public static void addController(InputController controller) {
		controllers.add(controller);
	}
	
}