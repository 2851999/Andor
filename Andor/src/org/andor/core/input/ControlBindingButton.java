/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.input;

public class ControlBindingButton extends InputListener {
	
	/* The control binding instance */
	private ControlBinding controlBinding;
	
	/* The key code used with this binding */
	public int keyCode;
	
	/* The controller button used with this binding */
	public ControllerButton controllerButton;
	
	/* The boolean that states whether an event is expected to setup this binding */
	public boolean expectingEvent;
	
	/* The boolean value that represents whether this binding is pressed */
	public boolean pressed;
	
	/* The constructor */
	public ControlBindingButton(ControlBinding controlBinding) {
		//Assign the variables
		this.controlBinding = controlBinding;
		this.reset();
	}
	
	/* The method used to prepare this to set the control bindings */
	public void set() { this.expectingEvent = true; }
	
	/* The method used to reset this binding */
	public void reset() {
		//Assign the variables
		this.expectingEvent = false;
		this.keyCode = -1;
		this.pressed = false;
		this.controllerButton = null;
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
	public void onKeyPressed(KeyboardEvent e) {
		//Check the key
		if (e.keyCode == this.keyCode) {
			//Check the current value
			if (this.pressed != true) {
				//Assign the value
				this.pressed = true;
				//Call an event
				this.controlBinding.bindings.callButtonPressed(this);
			}
		}
	}
	
	/* The method called when a key on the keyboard is released */
	public void onKeyReleased(KeyboardEvent e) {
		//Check the key
		if (e.keyCode == this.keyCode) {
			//Check the current value
			if (this.pressed != false) {
				//Assign the value
				this.pressed = false;
				//Call an event
				this.controlBinding.bindings.callButtonReleased(this);
			}
		}
	}
	
	/* The method called when a key on the keyboard is typed */
	public void onKeyTyped(KeyboardEvent e) {
		//Check to see whether this is expecting an event
		if (this.expectingEvent) {
			//Assign the key
			this.keyCode = e.keyCode;
			//Stop expecting this event
			this.expectingEvent = false;
		}
	}
	
	/* CONTROLLER STUFF */
	
	/* The method called when an axis changes */
	public void onAxisChange(ControllerAxisEvent e) { }
	
	/* The method called when a button is pressed */
	public void onButtonPressed(ControllerButtonEvent e) {
		//Make sure this is the right controller
		if (e.controller.index == this.controlBinding.controllerIndex) {
			//Check to see whether this is expecting an event
			if (this.expectingEvent) {
				//Assign the button
				this.controllerButton = e.getButton();
				//Stop expecting this event
				this.expectingEvent = false;
			} else if (this.controllerButton != null && this.controllerButton.index == e.getButton().index) {
				//Check the current value
				if (this.pressed != true) {
					//Assign the value
					this.pressed = true;
					//Call an event
					this.controlBinding.bindings.callButtonPressed(this);
				}
			}
		}
	}
	
	/* The method called when a button is released */
	public void onButtonReleased(ControllerButtonEvent e) {
		//Make sure this is the right controller
		if (e.controller.index == this.controlBinding.controllerIndex) {
			//Check the button
			if (this.controllerButton != null && this.controllerButton.index == e.getButton().index) {
				//Check the current value
				if (this.pressed != false) {
					//Assign the value
					this.pressed = false;
					//Call an event
					this.controlBinding.bindings.callButtonReleased(this);
				}
			}
		}
	}
	
	/* The method called when the pov is changed */
	public void onPovChange(ControllerPovEvent e) { }
	
	/* The 'get' methods */
	public ControlBinding getControlBinding() { return this.controlBinding; }
	public int getKeyCode() { return this.keyCode; }
	public ControllerButton getControllerButton() { return this.controllerButton; }
	
	
}