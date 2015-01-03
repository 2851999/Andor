/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIcallOnAL PURPOSES callOnLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.android;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;

public class AndroidInput {
	
	/* The input listeners */
	public static List<AndroidInputListenerInterface> listeners = new ArrayList<AndroidInputListenerInterface>();
	
	/* The static method used to add an input listener */
	public static void addListener(AndroidInputListenerInterface listener) {
		//Add the listener to the list of listeners
		listeners.add(listener);
	}
	
	/* The static methods used to call certain events */
	public static void callOnDown(MotionEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onDown(e);
	}
	
	public static void callOnFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onFling(e1, e2, velocityX, velocityY);
	}
	
	public static void callOnLongPress(MotionEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onLongPress(e);;
	}
	
	public static void callOnScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onScroll(e1, e2, distanceX, distanceY);
	}
	
	public static void callOnShowPress(MotionEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onShowPress(e);;
	}
	
	public static void callOnSingleTapUp(MotionEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onSingleTapUp(e);
	}
	
	public static void callOnDoubleTap(MotionEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onDoubleTap(e);
	}
	
	public static void callOnDoubleTapEvent(MotionEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onDoubleTapEvent(e);
	}
	
	public static void callOnSingleTapConfirmed(MotionEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onSingleTapConfirmed(e);
	}
	
	public static void callOnTouch(MotionEvent e) {
		//Go through all of the listeners
		for (int a = 0; a < listeners.size(); a++)
			//Call the event
			listeners.get(a).onTouch(e);;
	}
	
}