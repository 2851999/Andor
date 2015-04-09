/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import org.andor.core.input.Input;
import org.andor.core.input.Keyboard;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.Mouse;
import org.andor.core.input.MouseEvent;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.input.ScrollEvent;
import org.andor.utils.OpenGLUtils;
import org.andor.utils.ScreenUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;

public class Window {
	
	/* The window instance */
	public static long instance = -1;
	
	/* The list of window event listeners */
	public static List<WindowEventListenerInterface> eventListeners = new ArrayList<WindowEventListenerInterface>();
	
	/* Callback references */
	private static GLFWKeyCallback keyCallback;
	private static GLFWMouseButtonCallback mouseButtonCallback;
	private static GLFWCursorPosCallback mousePosCallback;
	private static GLFWScrollCallback scrollCallback;
	private static GLFWWindowSizeCallback windowSizeCallback;
	
	/* The static method used to create the window */
	public static void create() {
		//Initialise GLFW
		GLFW.glfwInit();
		//Assign the values
		setResizable(Settings.Window.Resizable);
		setUndecorated(Settings.Window.Undecorated);
		//The monitor
		long monitor = 0;
		//Check the fullscreen value
		if (Settings.Window.Fullscreen || Settings.Window.WindowedFullscreen)
			monitor = GLFW.glfwGetPrimaryMonitor();
		//Check whether to use windowed fullscreen
		if (Settings.Window.WindowedFullscreen) {
			GLFW.glfwDefaultWindowHints();
			Settings.Window.Width = ScreenUtils.getScreenWidth();
			Settings.Window.Height = ScreenUtils.getScreenHeight();
		} else if (Settings.Window.Fullscreen) {
			Settings.Window.setSize(Settings.Video.Resolution);
		}
		if (Settings.Video.Samples != 0)
			GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, Settings.Video.Samples);
		GLFW.glfwWindowHint(GLFW.GLFW_REFRESH_RATE, 60);
		//Set the instance
		instance = GLFW.glfwCreateWindow((int) Settings.Window.Width, (int) Settings.Window.Height, Settings.Window.Title, monitor, 0L);
		//Move to centre
		centre();
		
		//Create input
		GLFW.glfwSetKeyCallback(instance, keyCallback = new GLFWKeyCallback() {
			public void invoke(long window, int key, int scancode, int action, int mods) {
				//Check the action
				if (action == GLFW.GLFW_PRESS) {
					//Get the correct key code
					int keyCode = Keyboard.convertKeyCode(key);
					//Get the correct character
					char keyCharacter = (char) key;
					//The keyboard event
					KeyboardEvent e = new KeyboardEvent(keyCharacter , keyCode);
					//Call the key event
					Keyboard.onKeyPressed(e);
					//Call a keyboard event
					Input.callKeyPressed(e);
					//Set the last keyboard event
					Keyboard.lastKeyboardEvent = e;
				} else if (action == GLFW.GLFW_RELEASE) {
					//Get the correct key code
					int keyCode = Keyboard.convertKeyCode(key);
					//Get the correct character
					char keyCharacter = (char) key;
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
		});
		
		GLFW.glfwSetMouseButtonCallback(instance, mouseButtonCallback = new GLFWMouseButtonCallback() {
			public void invoke(long window, int button, int action, int mods) {
				//Check the current event
				if (action == GLFW.GLFW_PRESS) {
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
				} else if (action == GLFW.GLFW_RELEASE) {
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
		});
		
		GLFW.glfwSetCursorPosCallback(instance, mousePosCallback = new GLFWCursorPosCallback() {
			public void invoke(long window, double x, double y) {
				//Set the last and new position values
				Mouse.lastX = Mouse.x;
				Mouse.lastY = Mouse.y;
				Mouse.x = (float) x;
				Mouse.y = (float) y;
				
				//Call a mouse moved event
				Input.callMouseMoved(new MouseMotionEvent(Mouse.lastX, Mouse.lastY, Mouse.x, Mouse.y));
				
				//Check to see whether the mouse button is pressed
				if (Mouse.isButtonDown(0))
					//Call a mouse dragged event
					Input.callMouseDragged(new MouseMotionEvent(Mouse.lastX, Mouse.lastY, Mouse.x, Mouse.y));
			}
		});
		
		GLFW.glfwSetScrollCallback(instance, scrollCallback = new GLFWScrollCallback() {
			public void invoke(long window, double xOffset, double yOffset) {
				if (yOffset != 0)
					//Call a scroll event
					Input.callScroll(new ScrollEvent((float) yOffset));
			}
		});
		
		GLFW.glfwSetWindowSizeCallback(instance, windowSizeCallback = new GLFWWindowSizeCallback() {
			public void invoke(long instance, int width, int height) {
				//Create the old window size
				Vector2D oldSize = new Vector2D(Settings.Window.Width, Settings.Window.Height);
				//Assign the size
				Settings.Window.Width = width;
				Settings.Window.Height = height;
				//Update OpenGL's resolution
				GL11.glScissor(0, 0, (int) Settings.Window.Width, (int) Settings.Window.Height);
				GL11.glViewport(0, 0, (int) Settings.Window.Width, (int) Settings.Window.Height);
				//Create the new window size
				Vector2D newSize = new Vector2D(Settings.Window.Width, Settings.Window.Height);
				//Call a resized event
				callOnWindowResized(new WindowSizeEvent(oldSize, newSize));
			}
		});
		
		//Set the correct display mode
		setDisplayMode();
		//Make the OpenGL context current
		GLFW.glfwMakeContextCurrent(instance);
		//Create the OpenGL context
		GLContext.createFromCurrent();
		//Enable VSync if necessary
		setVSync(Settings.Video.VSync);
		//Update OpenGL's resolution
		GL11.glScissor(0, 0, (int) Settings.Window.Width, (int) Settings.Window.Height);
		GL11.glViewport(0, 0, (int) Settings.Window.Width, (int) Settings.Window.Height);
	}
	
	/* The static method used to get the fullscreen display mode */
	public static void setDisplayMode() {
		//Set the resizable value
		setResizable(Settings.Window.Resizable);
		//Check the fullscreen value
		if (Settings.Window.Fullscreen) {
			//Assign the resolution
			GLFW.glfwSetWindowSize(instance, Settings.Video.Resolution.getWidth(), Settings.Video.Resolution.getHeight());
			//Assign the settings
			Settings.Window.Width = Settings.Video.Resolution.getWidth();
			Settings.Window.Height = Settings.Video.Resolution.getHeight();
		} else
			//Assign the resolution
			GLFW.glfwSetWindowSize(instance, (int) Settings.Window.Width, (int) Settings.Window.Height);
	}
	
	/* The static method used to update the display's settings */
	public static void updateDisplaySettings() {
		//Set the displays title
		GLFW.glfwSetWindowTitle(instance, Settings.Window.Title);
		//Set the undecorated property
		//Reset the display mode
		setDisplayMode();
		setVSync(Settings.Video.VSync);
		//Update OpenGL's resolution
		GL11.glScissor(0, 0, (int) Settings.Window.Width, (int) Settings.Window.Height);
		GL11.glViewport(0, 0, (int) Settings.Window.Width, (int) Settings.Window.Height);
	}
	
	/* The static method used to update the display */
	public static void updateDisplay() {
		//Swap the buffers
		GLFW.glfwSwapBuffers(instance);
		//Poll for window events
		GLFW.glfwPollEvents();
	}
	
	/* The static method used to set the undecorated property */
	public static void setUndecorated(boolean undecorated) {
		//Set the value
		GLFW.glfwWindowHint(GLFW.GLFW_DECORATED, OpenGLUtils.getValue(! undecorated));
	}
	
	/* The static method used to set the vsync property */
	public static void setVSync(boolean vsync) {
		//Check the value
		if (vsync)
			GLFW.glfwSwapInterval(1);
		else
			GLFW.glfwSwapInterval(0);
	}
	
	/* The static method to centre the window */
	public static void centre() {
		//Get the screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//Set the position of the window
		setPosition((screenSize.width / 2) - (Settings.Window.Width / 2), (screenSize.height / 2) - (Settings.Window.Height / 2));
	}
	
	/* The static method used to return the centre x value */
	public static float getCentreX() {
		//Get the screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//Set the position of the window
		return (screenSize.width / 2) - (Settings.Window.Width / 2);
	}
	
	/* The static method used to return the centre x value */
	public static float getCentreY() {
		//Get the screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//Set the position of the window
		return (screenSize.height / 2) - (Settings.Window.Height / 2);
	}
	
	/* The static method used to set the position of this window */
	public static void setPosition(float x, float y) {
		if (! Settings.Window.Fullscreen)
			//Set the position of the window
			GLFW.glfwSetWindowPos(instance, (int) x, (int) y);
	}
	
	/* The static method used to set the window as resizable */
	public static void setResizable(boolean resizable) {
		//Check to see whether the value has been assigned
		GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, OpenGLUtils.getValue(resizable));
	}
	
	/* The static method used to set the window icon given a list of images */
	public static void setIcon(Image[] images) {
		//ByteBuffer buffer1 = images[0].texture;
		//ByteBuffer buffer2 = images[1].texture;
		//Display.setIcon(new ByteBuffer[] { buffer1 , buffer2 });
	}
	
	/* The static method used to show this window */
	public static void show() {
		GLFW.glfwShowWindow(instance);
	}
	
	/* The static method used to hide this window */
	public static void hide() {
		GLFW.glfwHideWindow(instance);
	}
	
	/* The static method used to determine whether this window should close */
	public static boolean shouldClose() {
		//Return whether close is requested
		return OpenGLUtils.getBoolean(GLFW.glfwWindowShouldClose(instance));
	}
	
	/* The static method used to close this window */
	public static void destroy() {
		//Release the callbacks
		keyCallback.release();
		mouseButtonCallback.release();
		mousePosCallback.release();
		scrollCallback.release();
		windowSizeCallback.release();
		//Destroy the window
		GLFW.glfwDestroyWindow(instance);
	}
	
	/* The static method used to call a window resized event */
	public static void callOnWindowResized(WindowSizeEvent e) {
		//Go through the listeners
		for (int a = 0; a < eventListeners.size(); a++)
			//Call the event in the current listener
			eventListeners.get(a).onWindowResized(e);
	}
	
	/* The static method used to add a window event listener */
	public static void addWindowEventListener(WindowEventListenerInterface listener) {
		//Add the listener to the list
		eventListeners.add(listener);
	}
	
}