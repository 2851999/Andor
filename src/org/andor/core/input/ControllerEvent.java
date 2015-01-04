/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

import org.andor.core.Event;

public class ControllerEvent extends Event {
	
	/* The name of this event */
	public static final String EVENT_NAME = "Controller Event";
	
	/* The description of this event */
	public static final String EVENT_DESCRIPTION = "An event from a controller";
	
	/* The controller instance */
	public InputController controller;
	
	/* The constructor */
	public ControllerEvent(InputController controller) {
		//Call the super constructor
		super(EVENT_NAME, EVENT_DESCRIPTION);
		//Assign the variables
		this.controller = controller;
	}
	
	/* The get methods */
	public InputController getController() { return this.controller; }
	
}