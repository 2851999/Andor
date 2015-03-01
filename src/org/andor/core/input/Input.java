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

import org.lwjgl.glfw.GLFW;

public class Input {
	
	/* The different controllers */
	public static final int CONTROLLER_1 = GLFW.GLFW_JOYSTICK_1;
	public static final int CONTROLLER_2 = GLFW.GLFW_JOYSTICK_2;
	public static final int CONTROLLER_3 = GLFW.GLFW_JOYSTICK_3;
	public static final int CONTROLLER_4 = GLFW.GLFW_JOYSTICK_4;
	public static final int CONTROLLER_5 = GLFW.GLFW_JOYSTICK_5;
	public static final int CONTROLLER_6 = GLFW.GLFW_JOYSTICK_6;
	public static final int CONTROLLER_7 = GLFW.GLFW_JOYSTICK_7;
	public static final int CONTROLLER_8 = GLFW.GLFW_JOYSTICK_8;
	public static final int CONTROLLER_9 = GLFW.GLFW_JOYSTICK_9;
	public static final int CONTROLLER_10 = GLFW.GLFW_JOYSTICK_10;
	public static final int CONTROLLER_11 = GLFW.GLFW_JOYSTICK_11;
	public static final int CONTROLLER_12 = GLFW.GLFW_JOYSTICK_12;
	public static final int CONTROLLER_13 = GLFW.GLFW_JOYSTICK_13;
	public static final int CONTROLLER_14 = GLFW.GLFW_JOYSTICK_14;
	public static final int CONTROLLER_15 = GLFW.GLFW_JOYSTICK_15;
	public static final int CONTROLLER_16 = GLFW.GLFW_JOYSTICK_16;
	
	/* The input listeners */
	public static List<InputListenerInterface> listeners = new ArrayList<InputListenerInterface>();
	
	/* The static method used to add an input listener */
	public static void addListener(InputListenerInterface listener) {
		//Add the listener to the list of listeners
		listeners.add(listener);
	}
	
	/* The static method used to call a mouse pressed event */
	public static void callMousePressed(MouseEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onMousePressed(e);
	}
	
	/* The static method used to call a mouse released event */
	public static void callMouseReleased(MouseEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onMouseReleased(e);
	}
	
	/* The static method used to call a mouse clicked event */
	public static void callMouseClicked(MouseEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onMouseClicked(e);
	}
	
	/* The static method used to call a mouse moved event */
	public static void callMouseMoved(MouseMotionEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onMouseMoved(e);
	}
	
	/* The static method used to call a mouse dragged event */
	public static void callMouseDragged(MouseMotionEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onMouseDragged(e);
	}
	
	/* The static method used to call a scroll event */
	public static void callScroll(ScrollEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onScroll(e);
	}
	
	/* The static method used to call a key pressed event */
	public static void callKeyPressed(KeyboardEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onKeyPressed(e);
	}
	
	/* The static method used to call a key released event */
	public static void callKeyReleased(KeyboardEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onKeyReleased(e);
	}
	
	/* The static method used to call a key typed event */
	public static void callKeyTyped(KeyboardEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onKeyTyped(e);
	}
	
	/* The static method used to call an axis change event */
	public static void callAxisChange(ControllerAxisEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onAxisChange(e);
	}
	
	/* The static method used to call a button pressed event */
	public static void callButtonPressed(ControllerButtonEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onButtonPressed(e);
	}
	
	
	/* The static method used to call a button pressed event */
	public static void callButtonReleased(ControllerButtonEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onButtonReleased(e);
	}
	
}