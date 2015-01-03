/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.android;

import android.view.MotionEvent;

public class AndroidInputListener implements AndroidInputListenerInterface {
	
	/* The constructor */
	public AndroidInputListener() {
		//Add this listener
		AndroidInput.addListener(this);
	}

	/* Gesture event method */
	public boolean onDown(MotionEvent e) {
		return true;
	}
	
	/* Gesture event method */
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		return true;
	}
	
	/* Gesture event method */
	public void onLongPress(MotionEvent e) {}
	
	/* Gesture event method */
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
		return true;
	}
	
	/* Gesture event method */
	public void onShowPress(MotionEvent e) {}
	
	/* Gesture event method */
	public boolean onSingleTapUp(MotionEvent e) {
		return true;
	}
	
	/* Gesture event method */
	public boolean onDoubleTap(MotionEvent e) {
		return true;
	}
	
	/* Gesture event method */
	public boolean onDoubleTapEvent(MotionEvent e) {
		return true;
	}
	
	/* Gesture event method */
	public boolean onSingleTapConfirmed(MotionEvent e) {
		return true;
	}
	
	/* Gesture event method */
	public void onTouch(MotionEvent e) {}
	
}