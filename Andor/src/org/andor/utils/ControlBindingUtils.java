/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.input.ControlBinding;
import org.andor.core.input.ControlBindingAxis;
import org.andor.core.input.ControlBindingButton;
import org.andor.core.input.ControlBindings;
import org.andor.core.input.ControllerAxis;
import org.andor.core.input.ControllerButton;
import org.andor.core.input.InputController;

public class ControlBindingUtils {
	
	/* The current controller being used when setting up control bindings */
	public static InputController currentController = null;
	
	/* The static method used to save a ControlBindings config */
	public static void save(ControlBindings controlBindings, String filePath) {
		//The file text
		List<String> fileText = new ArrayList<String>();
		//Go through each binding
		for (int a = 0; a < controlBindings.bindings.size(); a++) {
			//Get the current binding
			ControlBinding binding = controlBindings.bindings.get(a);
			//Add some information about the current binding
			fileText.add("Binding: " + binding.name + " {");
			
			fileText.add("\t" + "Type: " + binding.binding);
			
			//Make sure the axis binding isn't null
			if (binding.bindingAxis != null) {
				fileText.add("\t" + "Binding Axis: {");
				
				fileText.add("\t\t" + "KeyCodePos: " + binding.bindingAxis.keyCodePos);
				fileText.add("\t\t" + "KeyCodeNeg: " + binding.bindingAxis.keyCodeNeg);
				fileText.add("\t\t" + "AxisDirectionPos: " + binding.bindingAxis.axisDirectionPos);
				fileText.add("\t\t" + "AxisDirectionNeg: " + binding.bindingAxis.axisDirectionNeg);
				
				//Make sure the axis controller axis isn't null
				if (binding.bindingAxis.axisPos != null) {
					fileText.add("\t\t" + "Axis Pos: {");
					
					fileText.add("\t\t\t" + "Index: " + binding.bindingAxis.axisPos.index);
					
					fileText.add("\t\t" + "}");
				}
				
				//Make sure the axis controller axis isn't null
				if (binding.bindingAxis.axisNeg != null) {
					fileText.add("\t\t" + "Axis Neg: {");
					
					fileText.add("\t\t\t" + "Index: " + binding.bindingAxis.axisNeg.index);
					
					fileText.add("\t\t" + "}");
				}
				
				fileText.add("\t" + "}");
			}
			
			//Make sure the axis binding isn't null
			if (binding.bindingButton != null) {
				fileText.add("\t" + "Binding Button: {");
				
				fileText.add("\t\t" + "KeyCode: " + binding.bindingButton.keyCode);
				fileText.add("\t\t" + "MouseButton: " + binding.bindingButton.mouseButton);
				
				//Make sure the button controller axis isn't null
				if (binding.bindingButton.controllerButton != null) {
					fileText.add("\t\t" + "ControllerButton: {");
					
					fileText.add("\t\t\t" + "Index: " + binding.bindingButton.controllerButton.index);
					
					fileText.add("\t\t" + "}");
				}
				
				fileText.add("\t" + "}");
			}
			
			fileText.add("}");
		}
		//Save the file
		FileUtils.write(filePath, ArrayUtils.toStringArray(fileText));
	}
	
	/* The static method used to load a ControlBindings config */
	public static ControlBindings load(String filePath, boolean external) {
		//Read the file
		List<String> fileText = FileUtils.read(filePath, external);
		//Go through the text
		for (int a = 0; a < fileText.size(); a++)
			//Replace any tabs in the line
			fileText.set(a, fileText.get(a).replace("\t", ""));
		//The control bindings
		ControlBindings bindings = new ControlBindings();
		//The current index of the file being read
		int index = 0;
		//Keep going until the end of the file has been reached
		while (index < fileText.size()) {
			//Check the current line
			if (fileText.get(index).startsWith("Binding: ")) {
				//Get the binding name
				String bindingName = fileText.get(index).split(" ")[1];
				index++;
				//Get the type
				int bindingType = Integer.parseInt(fileText.get(index).split(" ")[1]);
				index++;
				//Check to see whether the controller has been set
				if (currentController != null)
					//Add a new binding using the controller
					bindings.add(bindingName, bindingType, currentController);
				else
					//Add a new binding
					bindings.add(bindingName, bindingType);
				//Check for a binding axis or button
				if (fileText.get(index).startsWith("Binding Axis: ")) {
					//Get the data
					index++;
					int keyCodePos = Integer.parseInt(fileText.get(index).split(" ")[1]);
					index++;
					int keyCodeNeg = Integer.parseInt(fileText.get(index).split(" ")[1]);
					index++;
					int axisDirectionPos = Integer.parseInt(fileText.get(index).split(" ")[1]);
					index++;
					int axisDirectionNeg = Integer.parseInt(fileText.get(index).split(" ")[1]);
					index++;
					//Set the data in the binding
					bindings.bindings.get(bindings.bindings.size() - 1).bindingAxis = new ControlBindingAxis(bindings.bindings.get(bindings.bindings.size() - 1));
					bindings.bindings.get(bindings.bindings.size() - 1).bindingAxis.keyCodePos = keyCodePos;
					bindings.bindings.get(bindings.bindings.size() - 1).bindingAxis.keyCodeNeg = keyCodeNeg;
					bindings.bindings.get(bindings.bindings.size() - 1).bindingAxis.axisDirectionPos = axisDirectionPos;
					bindings.bindings.get(bindings.bindings.size() - 1).bindingAxis.axisDirectionNeg = axisDirectionNeg;
					//Check for an axis pos
					if (fileText.get(index).startsWith("Axis Pos: ") && currentController != null) {
						index++;
						int axisIndex = Integer.parseInt(fileText.get(index).split(" ")[1]);
						index++;
						index++;
						//Set the data in the binding
						bindings.bindings.get(bindings.bindings.size() - 1).bindingAxis.axisPos = new ControllerAxis(currentController, axisIndex);
					}
					//Check for an axis neg
					if (fileText.get(index).startsWith("Axis Neg: ") && currentController != null) {
						index++;
						int axisIndex = Integer.parseInt(fileText.get(index).split(" ")[1]);
						index++;
						//Set the data in the binding
						bindings.bindings.get(bindings.bindings.size() - 1).bindingAxis.axisNeg = new ControllerAxis(currentController, axisIndex);
					}
				} else if (fileText.get(index).startsWith("Binding Button: ")) {
					//Get the data
					index++;
					int keyCode = Integer.parseInt(fileText.get(index).split(" ")[1]);
					index++;
					int mouseButton = Integer.parseInt(fileText.get(index).split(" ")[1]);
					index++;
					//Set the data in the binding
					bindings.bindings.get(bindings.bindings.size() - 1).bindingButton = new ControlBindingButton(bindings.bindings.get(bindings.bindings.size() - 1));
					bindings.bindings.get(bindings.bindings.size() - 1).bindingButton.keyCode = keyCode;
					bindings.bindings.get(bindings.bindings.size() - 1).bindingButton.mouseButton = mouseButton;
					//Check for a controller button
					if (fileText.get(index).startsWith("ControllerButton: ") && currentController != null) {
						index++;
						int buttonIndex = Integer.parseInt(fileText.get(index).split(" ")[1]);
						//Set the data in the binding
						bindings.bindings.get(bindings.bindings.size() - 1).bindingButton.controllerButton = new ControllerButton(currentController, buttonIndex);
					}
				}
			}
			//Increment the current index of the file being read
			index++;
		}
		//Return the bindings
		return bindings;
	}
	
}