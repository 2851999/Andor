/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.input;

import java.util.ArrayList;
import java.util.List;

public class Input {
	
	/* The input listeners */
	public static List<InputListener> listeners = new ArrayList<InputListener>();
	
	/* The static method used to add an input listener */
	public static void addListener(InputListener listener) {
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
	
	/* The static method used to call a scroll event */
	public static void callScroll(ScrollEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onScroll(e);
	}
	
}