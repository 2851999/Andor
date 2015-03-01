/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

public class WindowSizeEvent extends Event {
	
	/* The name of this event */
	public static final String EVENT_NAME = "Window Size Event";
	
	/* The description of this event */
	public static final String EVENT_DESCRIPTION = "An event relating to the window's size";
	
	/* The old and new window sizes */
	public Vector2D oldSize;
	public Vector2D newSize;
	
	/* The constructor */
	public WindowSizeEvent(Vector2D oldSize, Vector2D newSize) {
		//Call the super constructor
		super(EVENT_NAME, EVENT_DESCRIPTION);
		//Assign the variables
		this.oldSize = oldSize;
		this.newSize = newSize;
	}
	
	/* The method used to calculate the scale and return it */
	public Vector2D getScale() {
		//Return the value
		return new Vector2D(this.newSize.x / this.oldSize.x, this.newSize.y / this.oldSize.y);
	}
	
	/* The methods used to 'get' various values */
	public Vector2D getOld() { return this.oldSize; }
	public Vector2D getNew() { return this.newSize; }
	
}