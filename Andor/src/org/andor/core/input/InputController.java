/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.input;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

public class InputController {
	
	/* The index of this controller */
	public int index;
	
	/* This controller */
	public Controller controller;
	
	/* The name of this controller */
	public String name;
	
	/* The other attributes of the controller */
	public int axisCount;
	public int buttonCount;
	
	/* The constructor */
	public InputController(int index) {
		//Assign the variables
		this.index = index;
		//Setup the controller
		this.controller = (Controller) Controllers.getController(this.index);
		//Poll the controller
		this.controller.poll();
		this.name = this.controller.getName();
		this.axisCount = this.controller.getAxisCount();
		this.buttonCount = this.controller.getButtonCount();
	}
	
	/* The method used to check the input */
	public void checkInput() {
		
	}
	
}