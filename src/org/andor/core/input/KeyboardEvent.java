/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

import org.andor.core.Event;

public class KeyboardEvent extends Event {
	
	/* The name of this event */
	public static final String EVENT_NAME = "Keyboard Event";
	
	/* The description of this event */
	public static final String EVENT_DESCRIPTION = "An event from the keyboard";
	
	/* The key's character */
	public char keyCharacter;
	
	/* The key's code */
	public int keyCode;
	
	/* The constructor */
	public KeyboardEvent(char keyCharacter, int keyCode) {
		//Call the super constructor
		super(EVENT_NAME, EVENT_DESCRIPTION);
		//Assign the variables
		this.keyCharacter = keyCharacter;
		this.keyCode = keyCode;
	}
	
	/* The methods used to return the key's character and code */
	public char getCharacter() { return this.keyCharacter; }
	public int getCode() { return this.keyCode; }
	
}