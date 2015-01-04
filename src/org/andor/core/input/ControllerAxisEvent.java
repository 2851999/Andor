/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

public class ControllerAxisEvent extends ControllerEvent {
	
	/* The controller's axis */
	public ControllerAxis axis;
	
	/* The axis value from this event */
	public float value;
	
	/* The constructor */
	public ControllerAxisEvent(InputController controller, ControllerAxis axis) {
		//Call the super constructor
		super(controller);
		//Assign the variables
		this.axis = axis;
		this.value = axis.getValue();
	}
	
	/* The get methods */
	public ControllerAxis getAxis() { return this.axis; }
	public float getAxisValue() { return this.value; }
	
}