/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.input;

public class ControlBindingAxis extends InputListener {
	
	/* The control binding instance */
	public ControlBinding controlBinding;
	
	/* The key codes */
	public int keyCodePos;
	public int keyCodeNeg;
	
	/* The axis directions (Positive/Negative) multiplied when axis event received
	 * to make sure that each direction is the right way */
	public int axisDirectionPos;
	public int axisDirectionNeg;
	
	/* The axes */
	public ControllerAxis axisPos;
	public ControllerAxis axisNeg;
	
	/* The booleans that states whether an event is expected to setup this binding */
	public boolean expectingEventPos;
	public boolean expectingEventNeg;
	
	/* The current value of this axis */
	public float currentValue;
	
	/* The constructor */
	public ControlBindingAxis(ControlBinding controlBinding) {
		//Assign the variables
		this.controlBinding = controlBinding;
		this.reset();
	}
	
	/* The method used to prepare this to set the positive/negative control bindings */
	public void setPos() { this.expectingEventPos = true; }
	public void setNeg() { this.expectingEventNeg = true; }
	
	/* The method used to reset this binding */
	public void reset() {
		//Assign the variables
		this.expectingEventPos = false;
		this.expectingEventNeg = false;
		this.currentValue = 0;
		this.keyCodePos = -1;
		this.keyCodeNeg = -1;
		this.axisDirectionPos = 0;
		this.axisDirectionNeg = 0;
		this.axisPos = null;
		this.axisNeg = null;
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
		//Make sure nothing is expected
		if (! this.expectingEventPos && ! this.expectingEventNeg) {
			//Check the key code
			if (e.keyCode == this.keyCodePos || e.keyCode == this.keyCodeNeg) {
				//The value
				int value = 0;
				//Assign the value based on what key was pressed
				if (e.keyCode == this.keyCodePos)
					value = 1;
				else if (e.keyCode == this.keyCodeNeg)
					value = -1;
				//Check the value
				if (value != this.currentValue) {
					//Assign the current value
					this.currentValue = value;
					//Call an event
					this.controlBinding.bindings.callAxisChange(this);
				}
			}
		}
	}
	
	/* The method called when a key on the keyboard is released */
	public void onKeyReleased(KeyboardEvent e) {
		//Make sure nothing is expected
		if (! this.expectingEventPos && ! this.expectingEventNeg) {
			//Check the key code
			if (e.keyCode == this.keyCodePos || e.keyCode == this.keyCodeNeg) {
				//Check the value
				if (0 != this.currentValue) {
					//Assign the current value
					this.currentValue = 0;
					//Call an event
					this.controlBinding.bindings.callAxisChange(this);
				}
			}
		}
	}
	
	/* The method called when a key on the keyboard is typed */
	public void onKeyTyped(KeyboardEvent e) {
		//Check the expecting values
		if (this.expectingEventPos) {
			//Assign the key
			this.keyCodePos = e.keyCode;
			//Stop expecting the event
			this.expectingEventPos = false;
		} else if (this.expectingEventNeg) {
			//Assign the key
			this.keyCodeNeg = e.keyCode;
			//Stop expecting the event
			this.expectingEventNeg = false;
		}
	}
	
	/* CONTROLLER STUFF */
	
	/* The method called when an axis changes */
	public void onAxisChange(ControllerAxisEvent e) {
		//Make sure this is the right controller
		if (e.controller.index == this.controlBinding.controllerIndex) {
			//Check the expecting values
			if (this.expectingEventPos) {
				//Assign the axis
				this.axisPos = e.axis;
				//Assign the axis values
				this.axisDirectionPos = this.getDirection(e.getAxisValue());
				//Stop expecting the event
				this.expectingEventPos = false;
			} else if (this.expectingEventNeg) {
				//Assign the axis
				this.axisNeg = e.axis;
				//The direction
				int direction = 0;
				if (e.getAxisValue() > 0)
					direction = 1;
				else if (e.getAxisValue() < 0)
					direction = -1;
				//Assign the axis values
				this.axisDirectionNeg = direction;
				//Stop expecting the event
				this.expectingEventNeg = false;
			} else {
				//Check the axis
				if (this.axisPos != null && e.getAxis().index == this.axisPos.index) {
					//Check the direction
					if (this.getDirection(e.getAxisValue()) == this.axisDirectionPos) {
						//Get the value
						float value = e.getAxisValue();
						//Check the direction (Should be positive)
						if (getDirection(value) == -1)
							value *= -1;
						//Set the current value
						this.currentValue = value;
						//Call an event
						this.controlBinding.bindings.callAxisChange(this);
					} else if (this.getDirection(e.getAxisValue()) == 0) {
						//Set the current value
						this.currentValue = 0;
						//Call an event
						this.controlBinding.bindings.callAxisChange(this);
					}
				}
				if (this.axisNeg != null && e.getAxis().index == this.axisNeg.index) {
					//Check the direction
					if (this.getDirection(e.getAxisValue()) == this.axisDirectionNeg) {
						//Get the value
						float value = e.getAxisValue();
						//Check the direction (Should be negative)
						if (getDirection(value) == 1)
							value *= -1;
						//Set the current value
						this.currentValue = value;
						//Call an event
						this.controlBinding.bindings.callAxisChange(this);
					} else if (this.getDirection(e.getAxisValue()) == 0) {
						//Set the current value
						this.currentValue = 0;
						//Call an event
						this.controlBinding.bindings.callAxisChange(this);
					}
				}
			}
		}
	}
	
	/* The method called when a button is pressed */
	public void onButtonPressed(ControllerButtonEvent e) { }
	
	/* The method called when a button is released */
	public void onButtonReleased(ControllerButtonEvent e) { }
	
	/* The method called when the pov is changed */
	public void onPovChange(ControllerPovEvent e) { }
	
	/* The method used to get the direction of a value */
	public int getDirection(float value) {
		//The direction
		int direction = 0;
		if (value > 0)
			direction = 1;
		else if (value < 0)
			direction = -1;
		//Return the direction
		return direction;
	}
	
}