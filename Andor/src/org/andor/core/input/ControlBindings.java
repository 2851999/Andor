/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.input;

import java.util.ArrayList;
import java.util.List;

import org.andor.utils.ArrayUtils;
import org.andor.utils.FileUtils;

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
	
	/* The method used to save this binding */
	public void save(String path) {
		//The file text
		List<String> fileText = new ArrayList<String>();
		//Go through the bindings
		for (int a = 0; a < this.bindings.size(); a++) {
			//Get the current binding
			ControlBinding binding = this.bindings.get(a);
			//Get the type
			String type = "";
			if (binding.binding == ControlBinding.TYPE_AXIS)
				type = "AXIS";
			else if (binding.binding == ControlBinding.TYPE_BUTTON)
				type = "BUTTON";
			//Add the type
			fileText.add(type);
			fileText.add("Name: " + binding.name);
			if (binding.binding == ControlBinding.TYPE_AXIS) {
				fileText.add("KeyCodePos: " + binding.bindingAxis.keyCodePos);
				fileText.add("KeyCodeNeg: " + binding.bindingAxis.keyCodeNeg);
				fileText.add("AxisDirectionPos: " + binding.bindingAxis.axisDirectionPos);
				fileText.add("AxisDirectionNeg: " + binding.bindingAxis.axisDirectionNeg);
				if (binding.bindingAxis.axisPos != null)
					fileText.add("AxisPosIndex: " + binding.bindingAxis.axisPos.index);
				if (binding.bindingAxis.axisNeg != null)
					fileText.add("AxisNegIndex: " + binding.bindingAxis.axisNeg.index);
			} else if (binding.binding == ControlBinding.TYPE_BUTTON) {
				fileText.add("KeyCode: " + binding.bindingButton.keyCode);
				if (binding.bindingButton.controllerButton != null)
					fileText.add("ControllerButtonIndex: " + binding.bindingButton.controllerButton.index);
			}
			fileText.add("END");
		}
		//Save the file
		FileUtils.write(path, ArrayUtils.toStringArray(fileText));
	}
	
	/* The method used to load a config */
	public void load(String path, boolean external, InputController controller) {
		//The file text
		List<String> fileText = FileUtils.read(path, external);
		int a = 0;
		//Go through the bindings
		while (a < fileText.size()) {
			//Check the current line
			if (fileText.get(a).equals("AXIS")) {
				a++;
				//Add the binding
				this.bindings.add(new ControlBinding(this, fileText.get(a).split(": ")[1], ControlBinding.TYPE_AXIS));
				while (! fileText.get(a).equals("END")) {
					if (fileText.get(a).startsWith("KeyCodePos"))
						this.bindings.get(this.bindings.size() - 1).bindingAxis.keyCodePos = Integer.parseInt(fileText.get(a).split(": ")[1]);
					else if (fileText.get(a).startsWith("KeyCodeNeg"))
						this.bindings.get(this.bindings.size() - 1).bindingAxis.keyCodeNeg = Integer.parseInt(fileText.get(a).split(": ")[1]);
					else if (fileText.get(a).startsWith("AxisDirectionPos"))
						this.bindings.get(this.bindings.size() - 1).bindingAxis.axisDirectionPos = Integer.parseInt(fileText.get(a).split(": ")[1]);
					else if (fileText.get(a).startsWith("AxisDirectionNeg"))
						this.bindings.get(this.bindings.size() - 1).bindingAxis.axisDirectionNeg = Integer.parseInt(fileText.get(a).split(": ")[1]);
					else if (fileText.get(a).startsWith("AxisPosIndex")) {
						this.bindings.get(this.bindings.size() - 1).bindingAxis.axisPos = new ControllerAxis(controller, Integer.parseInt(fileText.get(a).split(": ")[1]));
						this.bindings.get(this.bindings.size() - 1).controllerIndex = controller.index;
					} else if (fileText.get(a).startsWith("AxisNegIndex")) {
						this.bindings.get(this.bindings.size() - 1).bindingAxis.axisNeg = new ControllerAxis(controller, Integer.parseInt(fileText.get(a).split(": ")[1]));
						this.bindings.get(this.bindings.size() - 1).controllerIndex = controller.index;
					}
					a++;
				}
			} else if (fileText.get(a).equals("BUTTON")) {
				a++;
				//Add the binding
				this.bindings.add(new ControlBinding(this, fileText.get(a).split(": ")[1], ControlBinding.TYPE_BUTTON));
				while (! fileText.get(a).equals("END")) {
					if (fileText.get(a).startsWith("KeyCode"))
						this.bindings.get(this.bindings.size() - 1).bindingButton.keyCode = Integer.parseInt(fileText.get(a).split(": ")[1]);
					else if (fileText.get(a).startsWith("ControllerButtonIndex")) {
						this.bindings.get(this.bindings.size() - 1).bindingButton.controllerButton = new ControllerButton(controller, Integer.parseInt(fileText.get(a).split(": ")[1]));
						this.bindings.get(this.bindings.size() - 1).controllerIndex = controller.index;
					}
					a++;
				}
			}
			//Keep going
			a++;
		}
	}
	
}