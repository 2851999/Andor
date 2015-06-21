/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

public interface WindowEventListenerInterface {
	
	/* The method called when the window changes size */
	public void onWindowResized(WindowSizeEvent e);
	
	/* The method called when the cursor enters the window */
	public void onCursorEnter();
	
	/* The method called when the cursor leaves the window */
	public void onCursorExit();
	
}