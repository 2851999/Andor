/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.input;

import org.andor.core.Event;

public class MouseEvent extends Event {
	
	/* The name of this event */
	public static final String EVENT_NAME = "Mouse Event";
	
	/* The description of this event */
	public static final String EVENT_DESCRIPTION = "An event from the mouse";
	
	/* The booleans that represents whether each mouse button was down at the
	 * time of this event */
	public boolean leftButton = false;
	public boolean rightButton = false;
	public boolean middleButton = false;
	
	/* The position of the mouse at the time of this event */
	public float x;
	public float y;
	
	/* The constructor */
	public MouseEvent(boolean leftButton, boolean rightButton, boolean middleButton) {
		//Call the super constructor
		super(EVENT_NAME, EVENT_DESCRIPTION);
		//Assign the variables
		this.leftButton = leftButton;
		this.rightButton = rightButton;
		this.middleButton = middleButton;
		this.x = Mouse.x;
		this.y = Mouse.y;
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
	
}