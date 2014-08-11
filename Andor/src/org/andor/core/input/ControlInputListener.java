package org.andor.core.input;

public interface ControlInputListener {
	
	/* The method called when an axis value changes */
	public void onAxisChange(ControlBindingAxis binding);
	
	/* The method called when a button is pressed  */
	public void onButtonPressed(ControlBindingButton binding);
	
	/* The method called when a button is pressed  */
	public void onButtonReleased(ControlBindingButton binding);
	
}