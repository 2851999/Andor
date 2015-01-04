/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

import org.andor.core.Vector2D;

public class ControllerPov {
	
	/* The controller this POV is part of */
	public InputController controller;
	
	/* The current value of this POV (From the last time this hat was checked for input) */
	public Vector2D currentValue;
	
	/* The constructor */
	public ControllerPov(InputController controller) {
		//Assign the variables
		this.controller = controller;
		this.currentValue = new Vector2D();
	}
	
	/* The method used to check this axis for a change */
	public void checkInput() {
		//Get the current value of this hat (POV)
		float x = this.controller.getController().getPovX();
		float y = this.controller.getController().getPovY();
		//Check the value against the value from the last time it changed
		if (x != this.currentValue.x || y != this.currentValue.y) {
			//Assign the new value
			this.currentValue = new Vector2D(x, y);
			//Call an event
			Input.callPovChange(new ControllerPovEvent(this.controller, this));
		}
	}
	
	/* The get methods */
	public InputController getController() { return this.controller; }
	public Vector2D getValue() { return this.currentValue; }
	
}