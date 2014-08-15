/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.input;

import org.andor.core.Settings;
import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.lwjgl.LWJGLException;

public class InputManager {
	
	/* The static method used to check the input */
	public static void checkInput() {
		//Check the mouse and keyboard
		checkMouse();
		checkKeyboard();
		InputManagerController.checkInput();
	}
	
	/* The static method used to check the mouse input */
	public static void checkMouse() {
		
		//Check if the current mouse position is different to the last
		if (Mouse.x != org.lwjgl.input.Mouse.getX() ||
				Mouse.y != (Settings.Window.Height - org.lwjgl.input.Mouse.getY())) {
			//Set the last and new position values
			Mouse.lastX = Mouse.x;
			Mouse.lastY = Mouse.y;
			Mouse.x = (float) org.lwjgl.input.Mouse.getX();
			Mouse.y = (float) (Settings.Window.Height - org.lwjgl.input.Mouse.getY());
			
			//Call a mouse moved event
			Input.callMouseMoved(new MouseMotionEvent(Mouse.lastX, Mouse.lastY, Mouse.x, Mouse.y));
			
			//Check to see whether the mouse button is pressed
			if (org.lwjgl.input.Mouse.isButtonDown(0))
				//Call a mouse dragged event
				Input.callMouseDragged(new MouseMotionEvent(Mouse.lastX, Mouse.lastY, Mouse.x, Mouse.y));
		}
		
		//Check to see whether the left mouse button is down
		if (org.lwjgl.input.Mouse.isButtonDown(0) && ! Mouse.leftButton) {
			//Set the button as being down and call a mouse event
			Mouse.leftButton = true;
			Input.callMousePressed(new MouseEvent(true, false, false));
		} else if (! org.lwjgl.input.Mouse.isButtonDown(0) && Mouse.leftButton) {
			//Set the button as being up and call a mouse events
			Mouse.leftButton = false;
			MouseEvent e = new MouseEvent(true, false, false);
			Input.callMouseReleased(e);
			Input.callMouseClicked(e);
		}
		
		//Check to see whether the right mouse button is down
		if (org.lwjgl.input.Mouse.isButtonDown(1) && ! Mouse.rightButton) {
			//Set the button as being down and call a mouse event
			Mouse.rightButton = true;
			Input.callMousePressed(new MouseEvent(false, true, false));
		} else if (! org.lwjgl.input.Mouse.isButtonDown(1) && Mouse.rightButton) {
			//Set the button as being up and call a mouse events
			Mouse.rightButton = false;
			MouseEvent e = new MouseEvent(false, true, false);
			Input.callMouseReleased(e);
			Input.callMouseClicked(e);
		}
		
		//Check to see whether the middle mouse button is down
		if (org.lwjgl.input.Mouse.isButtonDown(2) && ! Mouse.middleButton) {
			//Set the button as being down and call a mouse event
			Mouse.middleButton = true;
			Input.callMousePressed(new MouseEvent(false, false, true));
		} else if (! org.lwjgl.input.Mouse.isButtonDown(2) && Mouse.middleButton) {
			//Set the button as being up and call a mouse events
			Mouse.middleButton = false;
			MouseEvent e = new MouseEvent(false, false, true);
			Input.callMouseReleased(e);
			Input.callMouseClicked(e);
		}
		
		//Check the scroll wheel
		int dWheel = org.lwjgl.input.Mouse.getDWheel();
		if (dWheel != 0)
			//Call a scroll event
			Input.callScroll(new ScrollEvent(dWheel));
	}
	
	/* The static method used to check the keyboard input */
	public static void checkKeyboard() {
		//Check for an event
		while (org.lwjgl.input.Keyboard.next()) {
			if (org.lwjgl.input.Keyboard.getEventKeyState()) {
				//Get the correct key code
				int keyCode = Keyboard.convertKeyCode(org.lwjgl.input.Keyboard.getEventKey());
				//Get the correct character
				char keyCharacter = org.lwjgl.input.Keyboard.getEventCharacter();
				//The keyboard event
				KeyboardEvent e = new KeyboardEvent(keyCharacter , keyCode);
				//Call the key event
				Keyboard.onKeyPressed(e);
				//Call a keyboard event
				Input.callKeyPressed(e);
				//Set the last keyboard event
				Keyboard.lastKeyboardEvent = e;
			} else {
				//Get the correct key code
				int keyCode = Keyboard.convertKeyCode(org.lwjgl.input.Keyboard.getEventKey());
				//Get the correct character
				char keyCharacter = org.lwjgl.input.Keyboard.getEventCharacter();
				//The keyboard event
				KeyboardEvent e = new KeyboardEvent(keyCharacter , keyCode);
				//Call the key event
				Keyboard.onKeyReleased(e);
				//Call the keyboard events
				Input.callKeyReleased(e);
				Input.callKeyTyped(e);
				//Set the last keyboard event
				Keyboard.lastKeyboardEvent = e;
			}
		}
	}
	
	/* The static method to setup the keyboard and mouse */
	public static void create() {
		try {
			//Create the keyboard and mouse
			org.lwjgl.input.Keyboard.create();
			org.lwjgl.input.Mouse.create();
			//Enable repeat events (To allow for holding down keys etc.)
			org.lwjgl.input.Keyboard.enableRepeatEvents(true);
			//Create the controllers
			InputManagerController.create();
		} catch (LWJGLException e) {
			//Log an error
			Logger.log("InputManager create()" , "An error occurred when creting the keyboard and mouse" , Log.ERROR);
			e.printStackTrace();
		}
	}
	
	/* The static method to destroy the keyboard and mouse */
	public static void destroy() {
		//Destroy the keyboard and mouse
		org.lwjgl.input.Keyboard.destroy();
		org.lwjgl.input.Mouse.destroy();
		//Destroy the controllers
		InputManagerController.destroy();
	}
	
}