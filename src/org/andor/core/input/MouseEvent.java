/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
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
	
	/* The mouse button that was pressed */
	public int mouseButton;
	
	/* The position of the mouse at the time of this event */
	public float x;
	public float y;
	
	/* The constructor */
	public MouseEvent(int button) {
		//Call the super constructor
		super(EVENT_NAME, EVENT_DESCRIPTION);
		//Assign the variables
		this.leftButton = Mouse.leftButton;
		this.rightButton = Mouse.rightButton;
		this.middleButton = Mouse.middleButton;
		this.mouseButton = button;
		this.x = Mouse.x;
		this.y = Mouse.y;
	}
	
	/* The method used to check whether a certain button was pressed */
	public boolean wasButtonDown(int button) {
		return this.mouseButton == button;
	}
	
	/* The method that returns the button that was pressed at the time of this event */
	public int getButton() {
		return this.mouseButton;
	}
	
}