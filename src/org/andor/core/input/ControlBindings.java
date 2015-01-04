/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

import java.util.ArrayList;
import java.util.List;

public class ControlBindings {
	
	/* The control bindings */
	public List<ControlBinding> bindings;
	
	/* The listeners */
	public List<ControlInputListener> listeners;
	
	/* The constructor */
	public ControlBindings() {
		//Assign the variables
		this.bindings = new ArrayList<ControlBinding>();
		this.listeners = new ArrayList<ControlInputListener>();
	}
	
	/* The method used to add a binding */
	public void add(String name, int binding) {
		//Add the binding
		this.bindings.add(new ControlBinding(this, name, binding));
	}
	
	/* The method used to add a binding */
	public void add(String name, int binding, InputController controller) {
		//Add the binding
		this.bindings.add(new ControlBinding(this, name, binding, controller.index, controller.name));
	}
	
	/* The method used to get a binding given its name */
	public ControlBinding get(String name) {
		//The binding
		ControlBinding binding = null;
		//Go through the list of bindings
		for (int a = 0; a < this.bindings.size(); a++) {
			//Check the current binding's
			if (name.equals(this.bindings.get(a).name)) {
				//Assign the binding
				binding = this.bindings.get(a);
				//Exit this loop
				break;
			}
		}
		//Return the binding
		return binding;
	}
	
	/* The method used to add a listener */
	public void addListener(ControlInputListener listener) {
		//Add the listener
		this.listeners.add(listener);
	}
	
	/* The method's used to call an event in all of the listeners */
	public void callAxisChange(ControlBindingAxis binding) {
		//Go through the listeners
		for (int a = 0; a < this.listeners.size(); a++)
			//Call the event within the current listener
			this.listeners.get(a).onAxisChange(binding);
	}
	
	public void callButtonPressed(ControlBindingButton binding) {
		//Go through the listeners
		for (int a = 0; a < this.listeners.size(); a++)
			//Call the event within the current listener
			this.listeners.get(a).onButtonPressed(binding);
	}
	
	public void callButtonReleased(ControlBindingButton binding) {
		//Go through the listeners
		for (int a = 0; a < this.listeners.size(); a++)
			//Call the event within the current listener
			this.listeners.get(a).onButtonReleased(binding);
	}
	
	/* The method used to set the controller */
	public void setController(InputController controller) {
		//Go through each binding
		for (int a = 0; a < this.bindings.size(); a++) {
			//Set the current bindings controller
			this.bindings.get(a).controllerIndex = controller.index;
			this.bindings.get(a).controllerName = controller.name;
		}
	}
	
}