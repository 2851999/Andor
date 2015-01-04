/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class ControlBinding {
	
	/* The types of control bindings */
	public static final int TYPE_AXIS = 1;
	public static final int TYPE_BUTTON = 2;
	
	/* The ControlBindings instance */
	public ControlBindings bindings;
	
	/* The name of this binding */
	public String name;
	
	/* The type of binding this is */
	public int binding;
	
	/* The two types of bindings */
	public ControlBindingAxis bindingAxis;
	public ControlBindingButton bindingButton;
	
	/* The controller's index (If set) */
	public int controllerIndex;
	
	/* The controller's name (If set) */
	public String controllerName;
	
	/* The constructor */
	public ControlBinding(ControlBindings bindings, String name, int binding) {
		//Assign the variables
		this.bindings = bindings;
		this.name = name;
		this.binding = binding;
		//Check the binding type
		if (binding == TYPE_AXIS) {
			//Create the binding
			this.bindingAxis = new ControlBindingAxis(this);
		} else if (binding == TYPE_BUTTON) {
			//Create the binding
			this.bindingButton = new ControlBindingButton(this);
		} else {
			//Log an error
			Logger.log("Andor - ControlBinding", "Invalid binding type: " + this.binding, Log.ERROR);
		}
	}
	
	/* The constructor */
	public ControlBinding(ControlBindings bindings, String name, int binding, int controllerIndex, String controllerName) {
		//Assign the variables
		this.bindings = bindings;
		this.name = name;
		this.binding = binding;
		this.controllerIndex = controllerIndex;
		this.controllerName = controllerName;
		//Check the binding type
		if (binding == TYPE_AXIS) {
			//Create the binding
			this.bindingAxis = new ControlBindingAxis(this);
		} else if (binding == TYPE_BUTTON) {
			//Create the binding
			this.bindingButton = new ControlBindingButton(this);
		} else {
			//Log an error
			Logger.log("Andor - ControlBinding", "Invalid binding type: " + this.binding, Log.ERROR);
		}
	}
	
}