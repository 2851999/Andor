/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.android;

import android.view.MotionEvent;

public interface AndroidInputListenerInterface {

	/* Gesture event method */
	public boolean onDown(MotionEvent e);
	
	/* Gesture event method */
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY);
	
	/* Gesture event method */
	public void onLongPress(MotionEvent e);
	
	/* Gesture event method */
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY);
	
	/* Gesture event method */
	public void onShowPress(MotionEvent e);
	
	/* Gesture event method */
	public boolean onSingleTapUp(MotionEvent e);
	
	/* Gesture event method */
	public boolean onDoubleTap(MotionEvent e);
	
	/* Gesture event method */
	public boolean onDoubleTapEvent(MotionEvent e);
	
	/* Gesture event method */
	public boolean onSingleTapConfirmed(MotionEvent e);
	
	/* Gesture event method */
	public void onTouch(MotionEvent e);
	
}