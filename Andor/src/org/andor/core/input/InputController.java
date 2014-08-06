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
	
	/* The array of axes within this controller */
	public ControllerAxis[] axes;
	
	/* The array of buttons within this controller */
	public ControllerButton[] buttons;
	
	/* The pov */
	public ControllerPov pov;
	
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
		
		//Create the arrays
		this.axes = new ControllerAxis[this.axisCount];
		this.buttons = new ControllerButton[this.buttonCount];
		
		//Go through all of the axes
		for (int a = 0; a < this.axes.length; a++)
			//Create the current axis
			this.axes[a] = new ControllerAxis(this, a);
		
		//Go through all of the button
		for (int a = 0; a < this.buttons.length; a++)
			//Create the current button
			this.buttons[a] = new ControllerButton(this, a);
		
		//Setup the pov
		this.pov = new ControllerPov(this);
	}
	
	/* The method used to check the input */
	public void checkInput() {
		//Go through all of the axes
		for (int a = 0; a < this.axes.length; a++)
			//Check for input on the current axis
			this.axes[a].checkInput();
		//Go through all of the buttons
		for (int a = 0; a < this.buttons.length; a++)
			//Check for in put on the current button
			this.buttons[a].checkInput();
		//Check the pov
		this.pov.checkInput();
	}
	
	/* The methods used to get values from this controller */
	public int getIndex() { return this.index; }
	public Controller getController() { return this.controller; }
	public String getName() { return this.name; }
	public int getAxisCount() { return this.axisCount; }
	public int getButtonCount() { return this.axisCount; }
	
}