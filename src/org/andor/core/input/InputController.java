/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
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
	public int rumblerCount;
	
	/* The array of axes within this controller */
	public ControllerAxis[] axes;
	
	/* The array of buttons within this controller */
	public ControllerButton[] buttons;
	
	/* The array of rumblers within this controller */
	public ControllerRumbler[] rumblers;
	
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
		this.rumblerCount = this.controller.getRumblerCount();
		
		//Create the arrays
		this.axes = new ControllerAxis[this.axisCount];
		this.buttons = new ControllerButton[this.buttonCount];
		this.rumblers = new ControllerRumbler[this.rumblerCount];
		
		//Go through all of the axes
		for (int a = 0; a < this.axes.length; a++)
			//Create the current axis
			this.axes[a] = new ControllerAxis(this, a);
		
		//Go through all of the buttons
		for (int a = 0; a < this.buttons.length; a++)
			//Create the current button
			this.buttons[a] = new ControllerButton(this, a);
		
		//Go through all of the rumblers
		for (int a = 0; a < this.rumblers.length; a++)
			//Create the current rumbler
			this.rumblers[a] = new ControllerRumbler(this, a);
		
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
		//Go through all of the rumblers
		for (int a = 0; a < this.rumblers.length; a++)
			//Check for in put on the current rumblers
			this.rumblers[a].check();
		//Check the pov
		this.pov.checkInput();
	}
	
	/* The method used to make this controller rumble given the strength */
	public void rumble(float strength) {
		//Make sure there are rumblers
		if (this.rumblerCount > 0) {
			//Go through all of the rumblers
			for (int a = 0; a < this.rumblers.length; a++)
				//Rumble
				this.rumblers[a].rumble(strength);
		} else
			//Log an error
			Logger.log("Andor - InputController rumble()", "No rumblers found in the controller " + this.name, Log.ERROR);
	}
	
	/* The method used to make this controller rumble for a certain amount of time */
	public void rumble(long time, float strength) {
		if (this.rumblerCount > 0) {
			//Go through all of the rumblers
			for (int a = 0; a < this.rumblers.length; a++)
				//Rumble
				this.rumblers[a].rumble(time, strength);
		} else
			//Log an error
			Logger.log("Andor - InputController rumble()", "No rumblers found in the controller " + this.name, Log.ERROR);
	}
	
	/* The method used to make this controller stop rumbling */
	public void stopRumble() {
		if (this.rumblerCount > 0) {
			//Go through all of the rumblers
			for (int a = 0; a < this.rumblers.length; a++)
				//Stop rumbling
				this.rumblers[a].stop();
		} else
			//Log an error
			Logger.log("Andor - InputController rumble()", "No rumblers found in the controller " + this.name, Log.ERROR);
	}
	
	/* The methods used to get values from this controller */
	public int getIndex() { return this.index; }
	public Controller getController() { return this.controller; }
	public String getName() { return this.name; }
	public int getAxisCount() { return this.axisCount; }
	public int getButtonCount() { return this.buttonCount; }
	public int getRumblerCount() { return this.rumblerCount; }
	
}