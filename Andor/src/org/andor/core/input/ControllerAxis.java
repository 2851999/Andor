/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.input;

public class ControllerAxis {
	
	/* The controller this axis is part of */
	public InputController controller;
	
	/* The index of the axis */
	public int index;
	
	/* The name of the axis */
	public String name;
	
	/* The current value of this axis (From the last time this axis was checked for input) */
	public float currentValue;
	
	/* The boolean that states whether an event has been called once */
	private boolean calledOnce;
	
	/* The constructor */
	public ControllerAxis(InputController controller, int index) {
		//Assign the variables
		this.controller = controller;
		this.index = index;
		this.name = this.controller.getController().getAxisName(this.index);
		this.currentValue = 0;
		this.calledOnce = false;
	}
	
	/* The method used to check this axis for a change */
	public void checkInput() {
		//Get the current value of this axis
		float value = this.controller.getController().getAxisValue(this.index);
		//Check the value against the value from the last time it changed
		if (value != this.currentValue) {
			//Assign the new value
			this.currentValue = value;
			//Value -1 prior to input, so ignore it unless that value is actually from the user
			if (calledOnce || this.currentValue == 0)
				//Call an event
				Input.callAxisChange(new ControllerAxisEvent(this.controller, this));
			if (! calledOnce)
				calledOnce = true;
		}
	}
	
	/* The get methods */
	public InputController getController() { return this.controller; }
	public int getIndex() { return this.index; }
	public String getName() { return this.name; }
	public float getValue() { return this.currentValue; }
	
}