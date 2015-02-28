/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.input;

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
		/*//Check if the current mouse position is different to the last
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
		
		//Get all of the mouse events
		while (org.lwjgl.input.Mouse.next()) {
			//Get the button
			int button = org.lwjgl.input.Mouse.getEventButton();
			//Check the current event
			if (org.lwjgl.input.Mouse.isButtonDown(button)) {
				//Make sure the button isn't already in the list
				if (! Mouse.buttonsDown.contains(button)) {
					//Add the button to the list
					Mouse.buttonsDown.add(button);
					//Check to see whether the left, right or middle mouse was the one that was pressed
					if (button == 0)
						//Set the button as being down
						Mouse.leftButton = true;
					else if (button == 1)
						//Set the button as being down
						Mouse.rightButton = true;
					if (button == 2)
						//Set the button as being down
						Mouse.middleButton = true;
					//Call an event
					Input.callMousePressed(new MouseEvent(button));
				}
			} else {
				//Check to see whether the button is already in the list
				if (Mouse.buttonsDown.contains(button)) {
					//Remove the button from the list
					Mouse.buttonsDown.remove(Mouse.buttonsDown.indexOf(button));
					//Check to see whether the left, right or middle mouse was the one that was released
					if (button == 0)
						//Set the button as being down
						Mouse.leftButton = false;
					else if (button == 1)
						//Set the button as being down
						Mouse.rightButton = false;
					if (button == 2)
						//Set the button as being down
						Mouse.middleButton = false;
					//Call an event
					Input.callMouseReleased(new MouseEvent(button));
					Input.callMouseClicked(new MouseEvent(button));
				}
			}
		}
		
		//Check the scroll wheel
		int dWheel = org.lwjgl.input.Mouse.getDWheel();
		if (dWheel != 0)
			//Call a scroll event
			Input.callScroll(new ScrollEvent(dWheel));
			*/
	}
	
	/* The static method used to check the keyboard input */
	public static void checkKeyboard() {
		/*//Check for an event
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
		}*/
	}
	
	/* The static method to setup the keyboard and mouse */
	public static void create() {
		/*try {
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
		}*/
	}
	
	/* The static method to destroy the keyboard and mouse */
	public static void destroy() {
		/*//Destroy the keyboard and mouse
		org.lwjgl.input.Keyboard.destroy();
		org.lwjgl.input.Mouse.destroy();
		//Destroy the controllers
		InputManagerController.destroy(); */
	}
	
}