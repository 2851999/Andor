/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

public class ControllerButton {
	
	/* The controller this axis is part of */
	public InputController controller;
	
	/* The index of the button */
	public int index;
	
	/* The name of the button */
	public String name;
	
	/* The boolean that states whether this button is pressed */
	public boolean pressed;
	
	/* The constructor */
	public ControllerButton(InputController controller, int index) {
		//Assign the variables
		this.controller = controller;
		this.index = index;
		this.name = this.controller.getController().getButtonName(this.index);
		this.pressed = false;
	}
	
	/* The method used to check this button for a change */
	public void checkInput() {
		//Get the current value of this button
		boolean p = this.controller.getController().isButtonPressed(this.index);
		//Check the value against the value from the last time it changed
		if (p != this.pressed) {
			//Assign the new value
			this.pressed = p;
			//Check what the change was
			if (this.pressed)
				//Call an event
				Input.callButtonPressed(new ControllerButtonEvent(this.controller, this));
			else
				//Call an event
				Input.callButtonReleased(new ControllerButtonEvent(this.controller, this));
		}
	}
	
	/* The get methods */
	public InputController getController() { return this.controller; }
	public int getIndex() { return this.index; }
	public String getName() { return this.name; }
	public boolean isPressed() { return this.pressed; }
	
}