/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.input;

import org.andor.core.Event;

public class MouseMotionEvent extends Event {
	
	/* The name of this event */
	public static final String EVENT_NAME = "Mouse Motion Event";
	
	/* The description of this event */
	public static final String EVENT_DESCRIPTION = "A motion event from the mouse";
	
	/* The booleans that represents whether each mouse button was down at the
	 * time of this event */
	public boolean leftButton = false;
	public boolean rightButton = false;
	public boolean middleButton = false;
	
	/* The start position of the mouse */
	public float startX;
	public float startY;
	
	/* The end position of the mouse */
	public float endX;
	public float endY;
	
	/* The change in the position of the mouse */
	public float dx;
	public float dy;
	
	/* The constructor */
	public MouseMotionEvent(float startX, float startY, float endX, float endY) {
		//Call the super constructor
		super(EVENT_NAME, EVENT_DESCRIPTION);
		//Assign the variables
		//Assign the variables
		leftButton = Mouse.leftButton;
		rightButton = Mouse.rightButton;
		middleButton = Mouse.middleButton;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.dx = this.endX - this.startX;
		this.dy = this.endY - this.startY;
	}
	
	/* The method used to check whether a certain button was pressed */
	public boolean wasButtonDown(int button) {
		//Check what button it is and return the value
		if (button == Mouse.LEFT_BUTTON)
			return leftButton;
		else if (button == Mouse.RIGHT_BUTTON)
			return rightButton;
		else if (button == Mouse.MIDDLE_BUTTON)
			return middleButton;
		else
			return false;
	}
	
	/* The methods used to return some of these values */
	public boolean wasDragged() { return this.leftButton; }
	public float getStartX() { return this.startX; }
	public float getStartY() { return this.startY; }
	public float getEndX() { return this.endX; }
	public float getEndY() { return this.endY; }
	public float getDX() { return this.dx; }
	public float getDY() { return this.dy; }
	
}