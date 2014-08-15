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
import org.andor.utils.FieldScanner;
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
	
	/* The method used to save this binding */
	public void save(String path) {
		//The file text
		List<String> fileText = new ArrayList<String>();
		//The scanner
		FieldScanner scanner = new FieldScanner();
		//Add the required classes to the scanner
		scanner.addClass("org.andor.core.input.InputController");
		scanner.addClass("org.andor.core.input.ControlBinding");
		scanner.addClass("org.andor.core.input.ControlBindingAxis");
		scanner.addClass("org.andor.core.input.ControlBindingButton");
		scanner.addClass("org.andor.core.input.ControllerAxis");
		scanner.addClass("org.andor.core.input.ControllerButton");
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
			
			List<?> names = null;
			List<?> values = null;
			
			//Scan the binding
			scanner.scan(binding);
			//Get all of the names and values
			names = scanner.getFieldNames();
			values = scanner.getFieldValues();
			
			//Go through each field
			for (int b = 0; b < names.size(); b++)
				//Add onto the file text
				fileText.add(names.get(b) + ": " + values.get(b));
			fileText.add("END");
		}
		//Save the file
		FileUtils.write(path, ArrayUtils.toStringArray(fileText));
	}
	
	/* The method used to load a config */
	public void load(String path, boolean external, InputController controller) {
		//The file text
		List<String> fileText = FileUtils.read(path, external);
		//The scanner
		FieldScanner scanner = new FieldScanner();
		int a = 0;
		//Go through the bindings
		while (a < fileText.size()) {
			//The field names and values
			List<String> fieldNames = new ArrayList<String>();
			List<Object> fieldValues = new ArrayList<Object>();
			//Check the current line
			if (fileText.get(a).equals("AXIS")) {
				a++;
				//Add the binding
				this.bindings.add(new ControlBinding(this, fileText.get(a).split(": ")[1], ControlBinding.TYPE_AXIS));
				a++;
			} else if (fileText.get(a).equals("BUTTON")) {
				a++;
				//Add the binding
				this.bindings.add(new ControlBinding(this, fileText.get(a).split(": ")[1], ControlBinding.TYPE_BUTTON));
				a++;
			}
			while (! fileText.get(a).equals("END")) {
				//Add split up the current line
				String[] split = fileText.get(a).split(": ");
				//Add the name and the value
				fieldNames.add(split[0]);
				fieldValues.add(split[1]);
				
				//Check the name
				if (split[0].startsWith("bindingAxis.axisPos"))
					//Create the instance
					this.bindings.get(this.bindings.size() - 1).bindingAxis.axisPos = new ControllerAxis(controller, 0);
				else if (split[0].startsWith("bindingAxis.axisNeg"))
					//Create the instance
					this.bindings.get(this.bindings.size() - 1).bindingAxis.axisNeg = new ControllerAxis(controller, 0);
				else if (split[0].startsWith("bindingButton.controllerButton"))
					//Create the instance
					this.bindings.get(this.bindings.size() - 1).bindingButton.controllerButton = new ControllerButton(controller, 0);
				a++;
			}
			//Set the variables
			scanner.setFieldNames(fieldNames);
			scanner.setFieldValues(fieldValues);
			scanner.set(this.bindings.get(this.bindings.size() - 1));
			a += 2;
		}
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