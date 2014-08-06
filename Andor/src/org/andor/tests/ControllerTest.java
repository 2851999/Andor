/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.tests;

import org.andor.core.BaseGame;
import org.andor.core.input.ControllerAxisEvent;
import org.andor.core.input.ControllerButtonEvent;
import org.andor.core.input.ControllerPovEvent;
import org.andor.core.input.InputController;
import org.andor.core.input.InputManagerController;
import org.andor.utils.Console;
import org.andor.utils.ControllerUtils;

public class ControllerTest extends BaseGame {
	
	/* The method called to create the game */
	public void create() {
		//Get all of the available controllers
		InputController[] controllers = ControllerUtils.getAvailableControllers();
		//Go through the controllers
		for (InputController controller : controllers) {
			//Print out some information
			Console.println("Name: " + controller.name);
			Console.println("Axis Count: " + controller.axisCount);
			Console.println("Button Count: " + controller.buttonCount);
			Console.println("");
		}
		//Add the last controller (In my case this is a joystick)
		InputManagerController.addController(controllers[controllers.length - 1]);
	}
	
	/* The method called to update the game */
	public void update() {
		
	}
	
	/* The method called when an axis changes */
	public void onAxisChange(ControllerAxisEvent e) {
		Console.println("Axis Changed: " + e.getAxis().getName() + " New Value: " + e.getAxisValue());
	}
	
	/* The method called when a button is pressed */
	public void onButtonPressed(ControllerButtonEvent e) {
		Console.println("Button Pressed: " + e.getButton().getName());
	}
	
	/* The method called when a button is released */
	public void onButtonReleased(ControllerButtonEvent e) {
		Console.println("Button Released: " + e.getButton().getName());
	}
	
	/* The method called when the pov is changed */
	public void onPovChange(ControllerPovEvent e) {
		Console.println("POV Change X: " + e.getPovValue().x + " Y: " + e.getPovValue().y);
	}
	
	/* The main method */
	public static void main(String[] args) {
		new ControllerTest();
	}
	
}