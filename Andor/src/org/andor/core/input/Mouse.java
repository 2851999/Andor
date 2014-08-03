/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.input;

import org.andor.core.Settings;

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
	
	/* The static method used to lock the mouse */
	public static void lock() {
		//Hide the cursor
		org.lwjgl.input.Mouse.setGrabbed(true);
		//Stop clipping the cursor within the window
		org.lwjgl.input.Mouse.setClipMouseCoordinatesToWindow(false);
		//Reset the mouse x and y, to make sure the InputManager doesn't
		//call mouse moved for this
		Mouse.x = (float) org.lwjgl.input.Mouse.getX();
		Mouse.y = (float) (Settings.Window.Height - org.lwjgl.input.Mouse.getY());
		Mouse.lastX = x;
		Mouse.lastY = y;
		//Call a mouse moved event (Prevent future issues with GUI's not updating)
		Input.callMouseMoved(new MouseMotionEvent(Mouse.x, Mouse.y, Mouse.x, Mouse.y));
	}
	
	/* The static method used to unlock the mouse */
	public static void unlock() {
		//Hide the cursor
		org.lwjgl.input.Mouse.setGrabbed(false);
		//Stop clipping the cursor within the window
		org.lwjgl.input.Mouse.setClipMouseCoordinatesToWindow(true);
		//Move the mouse into the centre
		centre();
		//Reset the mouse x and y, to make sure the InputManager doesn't
		//call mouse moved for this
		Mouse.x = (float) org.lwjgl.input.Mouse.getX();
		Mouse.y = (float) (Settings.Window.Height - org.lwjgl.input.Mouse.getY());
		Mouse.lastX = x;
		Mouse.lastY = y;
		//Call a mouse moved event (Prevent future issues with GUI's not updating)
		Input.callMouseMoved(new MouseMotionEvent(Mouse.x, Mouse.y, Mouse.x, Mouse.y));
	}
	
	/* The static method used to set the mouse locked or unlocked */
	public static void setLocked(boolean locked) {
		if (locked)
			lock();
		else
			unlock();
	}
	
	/* The static method used to determine whether the mouse is locked */
	public static boolean isLocked() {
		return org.lwjgl.input.Mouse.isGrabbed() && ! org.lwjgl.input.Mouse.isClipMouseCoordinatesToWindow() && org.lwjgl.input.Mouse.isInsideWindow();
	}
	
	/* The static method used to set the position of the mouse */
	public static void setPosition(float x, float y) {
		//Move the mouse
		org.lwjgl.input.Mouse.setCursorPosition((int) x, (int) y);
		//Reset the mouse x and y, to make sure the InputManager doesn't
		//call mouse moved for this
		Mouse.x = (float) org.lwjgl.input.Mouse.getX();
		Mouse.y = (float) (Settings.Window.Height - org.lwjgl.input.Mouse.getY());
		Mouse.lastX = x;
		Mouse.lastY = y;
		//Call a mouse moved event (Prevent future issues with GUI's not updating)
		Input.callMouseMoved(new MouseMotionEvent(Mouse.x, Mouse.y, Mouse.x, Mouse.y));
	}
	
	/* The static method used to set centre the mouse */
	public static void centre() {
		//Set the position into the middle of the screen
		setPosition(Settings.Window.Width / 2, Settings.Window.Height / 2);
	}
	
}