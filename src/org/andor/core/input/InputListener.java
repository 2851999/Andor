/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.input;

public class InputListener implements InputListenerInterface {
	
	/* The constructor */
	public InputListener() {
		//Add this input listener interface
		Input.addListener(this);
	}
	
	/* MOUSE STUFF */
	
	/* The method called when a button on the mouse is pressed */
	public void onMousePressed(MouseEvent e) { }
	
	/* The method called when a button on the mouse is released */
	public void onMouseReleased(MouseEvent e) { }
	
	/* The method called when a button on the mouse is clicked */
	public void onMouseClicked(MouseEvent e) { }
	
	/* The method called when the mouse moves */
	public void onMouseMoved(MouseMotionEvent e) { }
	
	/* The method called when the mouse is dragged */
	public void onMouseDragged(MouseMotionEvent e) { }
	
	/* The method called when the mouse scrolls */
	public void onScroll(ScrollEvent e) { }
	
	/* KEYBOARD STUFF */
	
	/* The method called when a key on the keyboard is pressed */
	public void onKeyPressed(KeyboardEvent e) { }
	
	/* The method called when a key on the keyboard is released */
	public void onKeyReleased(KeyboardEvent e) { }
	
	/* The method called when a key on the keyboard is typed */
	public void onKeyTyped(KeyboardEvent e) { }
	
	/* CONTROLLER STUFF */
	
	/* The method called when an axis changes */
	public void onAxisChange(ControllerAxisEvent e) { }
	
	/* The method called when a button is pressed */
	public void onButtonPressed(ControllerButtonEvent e) { }
	
	/* The method called when a button is released */
	public void onButtonReleased(ControllerButtonEvent e) { }
	
	/* The method called when the pov is changed */
	public void onPovChange(ControllerPovEvent e) { }
	
}