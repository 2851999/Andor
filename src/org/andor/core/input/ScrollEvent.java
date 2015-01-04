/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

import org.andor.core.Event;

public class ScrollEvent extends Event {
	
	/* The name of this event */
	public static final String EVENT_NAME = "Scroll Event";
	
	/* The description of this event */
	public static final String EVENT_DESCRIPTION = "An event from the mouse's scroll wheel";
	
	/* The distance the mouse wheel moved */
	public float distance;
	
	/* The constructor */
	public ScrollEvent(float distance) {
		//Call the super constructor
		super(EVENT_NAME, EVENT_DESCRIPTION);
		//Assign the variables
		this.distance = distance;
	}
	
	/* The methods used to return information about this event */
	public float getDistance() { return this.distance; }
	
}