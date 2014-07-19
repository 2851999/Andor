/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.input;

public class Mouse {
	
	/* The integer representations of each button on the mouse */
	public static final int LEFT_BUTTON = 0;
	public static final int RIGHT_BUTTON = 1;
	public static final int MIDDLE_BUTTON = 2;
	
	/* The value that represents whether the left mouse button is down */
	public static boolean leftButton = false;
	
	/* The value that represents whether the middle mouse button is down */
	public static boolean middleButton = false;
	
	/* The value that represents whether the right mouse button is down */
	public static boolean rightButton = false;
	
	/* The last position of the mouse */
	public static float lastX = -1;
	
	/* The last y position of the mouse */
	public static float lastY = -1;
	
	/* The current x position of the mouse */
	public static float x = -1;
	
	/* The current y position of the mouse */
	public static float y = -1;
	
	/* The method used to check whether a certain button is pressed */
	public static boolean isButtonDown(int button) {
		//Check what button it is and return the value
		if (button == LEFT_BUTTON)
			return leftButton;
		else if (button == RIGHT_BUTTON)
			return rightButton;
		else if (button == MIDDLE_BUTTON)
			return middleButton;
		else
			return false;
	}
	
}