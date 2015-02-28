/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import org.lwjgl.glfw.GLFW;

public class InputController {
	
	/* The index of this controller */
	public int index;
	
	/* The name of this controller */
	public String name;
	
	/* The other attributes of the controller */
	public int axisCount;
	public int buttonCount;
	public int rumblerCount;
	
	/* The array of axes within this controller */
	public ControllerAxis[] axes;
	
	/* The array of buttons within this controller */
	public ControllerButton[] buttons;
	
	/* The constructor */
	public InputController(int index) {
		//Assign the variables
		this.index = index;
		//Assign the values
		this.name = GLFW.glfwGetJoystickName(index);
		
		FloatBuffer axesBuffer = GLFW.glfwGetJoystickAxes(index);
		ByteBuffer buttonsBuffer = GLFW.glfwGetJoystickButtons(index);
		
		float[] axesArray = new float[axesBuffer.capacity()];
		byte[] buttonsArray = new byte[buttonsBuffer.capacity()];
		
		this.axisCount = axesArray.length;
		this.buttonCount = buttonsArray.length;
		
		//Create the arrays
		this.axes = new ControllerAxis[this.axisCount];
		this.buttons = new ControllerButton[this.buttonCount];
		
		//Go through all of the axes
		for (int a = 0; a < this.axes.length; a++)
			//Create the current axis
			this.axes[a] = new ControllerAxis(this, a);
		
		//Go through all of the buttons
		for (int a = 0; a < this.buttons.length; a++)
			//Create the current button
			this.buttons[a] = new ControllerButton(this, a);
	}
	
	/* The method used to check the input */
	public void checkInput() {
		//Go through all of the axes
		for (int a = 0; a < this.axes.length; a++)
			//Check for input on the current axis
			this.axes[a].checkInput();
		//Go through all of the buttons
		for (int a = 0; a < this.buttons.length; a++)
			//Check for in put on the current button
			this.buttons[a].checkInput();
	}
	
	/* The methods used to get values from this controller */
	public int getIndex() { return this.index; }
	public String getName() { return this.name; }
	public int getAxisCount() { return this.axisCount; }
	public int getButtonCount() { return this.buttonCount; }
	public int getRumblerCount() { return this.rumblerCount; }
	
}