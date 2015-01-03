/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

import org.andor.core.Vector2D;

public class ControllerPovEvent extends ControllerEvent {
	
	/* The controller's pov */
	public ControllerPov pov;
	
	/* The pov value from this event */
	public Vector2D value;
	
	/* The constructor */
	public ControllerPovEvent(InputController controller, ControllerPov pov) {
		//Call the super constructor
		super(controller);
		//Assign the variables
		this.pov = pov;
		this.value = pov.getValue();
	}
	
	/* The get methods */
	public ControllerPov getPov() { return this.pov; }
	public Vector2D getPovValue() { return this.value; }
	
}