/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

public class ControllerButtonEvent extends ControllerEvent {
	
	/* The controller's button */
	public ControllerButton button;
	
	/* The buttons pressed value from this event */
	public boolean pressed;
	
	/* The constructor */
	public ControllerButtonEvent(InputController controller, ControllerButton button) {
		//Call the super constructor
		super(controller);
		//Assign the variables
		this.button = button;
		this.pressed = button.isPressed();
	}
	
	/* The get methods */
	public ControllerButton getButton() { return this.button; }
	public boolean getButtonPressed() { return this.pressed; }
	
}