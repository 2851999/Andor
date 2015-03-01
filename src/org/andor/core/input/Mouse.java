/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.Settings;
import org.andor.core.Window;
import org.lwjgl.glfw.GLFW;

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
	
	/* The mouse button that is currently pressed */
	public static List<Integer> buttonsDown = new ArrayList<Integer>();
	
	/* The static method used to lock the mouse */
	public static void lock() {
		//Hide the cursor
		GLFW.glfwSetInputMode(Window.instance, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
		//Call a mouse moved event (Prevent future issues with GUI's not updating)
		Input.callMouseMoved(new MouseMotionEvent(Mouse.x, Mouse.y, Mouse.x, Mouse.y));
	}
	
	/* The static method used to unlock the mouse */
	public static void unlock() {
		//Show the cursor
		GLFW.glfwSetInputMode(Window.instance, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
		//Move the mouse into the centre
		centre();
		//Reset the mouse x and y, to make sure the InputManager doesn't
		//call mouse moved for this
		Mouse.x = Settings.Window.Width / 2;
		Mouse.y = Settings.Window.Height / 2;
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
		return GLFW.glfwGetInputMode(Window.instance, GLFW.GLFW_CURSOR) == GLFW.GLFW_CURSOR_DISABLED;
	}
	
	/* The static method used to set the position of the mouse */
	public static void setPosition(float x, float y) {
		//Move the mouse
		GLFW.glfwSetCursorPos(Window.instance, x, y);
		//Reset the mouse x and y, to make sure the InputManager doesn't
		//call mouse moved for this
		Mouse.x = x;
		Mouse.y = y;
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
	
	/* The static method that returns whether a certain button on the mouse is currently down */
	public static boolean isButtonDown(int button) {
		return Mouse.buttonsDown.contains(button);
	}
	
}