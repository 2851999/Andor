/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.android;

import org.andor.core.input.Input;
import org.andor.core.input.Mouse;
import org.andor.core.input.MouseEvent;
import org.andor.core.input.MouseMotionEvent;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class InputManagerAndroid implements GestureDetector.OnGestureListener , GestureDetector.OnDoubleTapListener {
	
	/* An instance of this class */
	public static InputManagerAndroid instance;
	
	/* Gestures */
	public GestureDetector gestures;
	
	/* The default constructor */
	public InputManagerAndroid() {
		//Create the gesture detector
		this.gestures = new GestureDetector(BaseActivity.instance, this);
	}
	
	/* Gesture event method */
	public boolean onDown(MotionEvent e) {
		AndroidInput.callOnDown(e);
		return true;
	}
	
	/* Gesture event method */
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		AndroidInput.callOnFling(e1, e2, velocityX, velocityY);
		return true;
	}
	
	/* Gesture event method */
	public void onLongPress(MotionEvent e) {
		AndroidInput.callOnLongPress(e);
	}
	
	/* Gesture event method */
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		AndroidInput.callOnScroll(e1, e2, distanceX, distanceY);
		return true;
	}
	
	/* Gesture event method */
	public void onShowPress(MotionEvent e) {
		AndroidInput.callOnShowPress(e);
	}
	
	/* Gesture event method */
	public boolean onSingleTapUp(MotionEvent e) {
		AndroidInput.callOnSingleTapUp(e);
		return true;
	}
	
	/* Gesture event method */
	public boolean onDoubleTap(MotionEvent e) {
		AndroidInput.callOnDoubleTap(e);
		return true;
	}
	
	/* Gesture event method */
	public boolean onDoubleTapEvent(MotionEvent e) {
		AndroidInput.callOnDoubleTapEvent(e);
		return true;
	}
	
	/* Gesture event method */
	public boolean onSingleTapConfirmed(MotionEvent e) {
		AndroidInput.callOnSingleTapConfirmed(e);
		return true;
	}
	
	/* Gesture event method */
	public void onTouch(MotionEvent e) {
		AndroidInput.callOnTouch(e);
		//Set the Mouse variables (For GUI)
		if (e.getX() != Mouse.x || e.getY() != Mouse.y) {
			Mouse.lastX = Mouse.x;
			Mouse.lastY = Mouse.y;
			Mouse.x = e.getX();
			Mouse.y = e.getY();
			Input.callMouseMoved(new MouseMotionEvent(Mouse.lastX, Mouse.lastY, Mouse.x, Mouse.y));
			Input.callMouseDragged(new MouseMotionEvent(Mouse.lastX, Mouse.lastY, Mouse.x, Mouse.y));
		}
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			//Add the button to the list
			Mouse.buttonsDown.add(0);
			Mouse.leftButton = true;
			Input.callMousePressed(new MouseEvent(Mouse.LEFT_BUTTON));
		} else if (e.getAction() == MotionEvent.ACTION_UP) {
			//Remove the button from the list
			Mouse.buttonsDown.remove(Mouse.buttonsDown.indexOf(0));
			Mouse.leftButton = false;
			Input.callMouseReleased(new MouseEvent(Mouse.LEFT_BUTTON));
			Input.callMouseClicked(new MouseEvent(Mouse.LEFT_BUTTON));
		}
		this.gestures.onTouchEvent(e);
	}
	
	/* The static method used to create this */
	public static void create() {
		instance = new InputManagerAndroid();
	}
	
}