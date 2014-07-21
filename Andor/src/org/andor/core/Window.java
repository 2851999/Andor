/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.nio.ByteBuffer;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Window {
	
	/* The static method used to create the window */
	public static void create() {
		//Catch any errors
		try {
			//Set the displays title
			Display.setTitle(Settings.Window.Title);
			//Set the correct display mode
			setDisplayMode();
			//Create the display
			Display.create();
		} catch (LWJGLException e) {
			//Log an error
			Logger.log("Andor - Window", "An exception has occurred when creating the display", Log.ERROR);
			e.printStackTrace();
		}
	}
	
	/* The static method used to get the fullscreen display mode */
	public static void setDisplayMode() {
		//Catch any errors
		try {
			//The target display mode
			DisplayMode target = null;
			//Check to see whether the display should be fullscreen
			if (Settings.Window.Fullscreen) {
				//Get the screens dimensions
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				//Get all of the available display modes
				DisplayMode[] availableDisplayModes = Display.getAvailableDisplayModes();
				//Go through each available display mode
				for (int a = 0; a < availableDisplayModes.length; a++) {
					//Get the current display mode
					DisplayMode current = availableDisplayModes[a];
					//Check the current display mode against the target size
					if ((current.getWidth() == screenSize.width) && (current.getHeight() == screenSize.height)) {
						//Check to see whether the current display has a higher frequency than the last one
						if ((target == null) || (current.getFrequency() >= target.getFrequency())) {
							//Check to see whether the bits per pixel value is higher than the last one
							if ((target == null) || (current.getBitsPerPixel() > target.getBitsPerPixel())) {
								//Set the target display mode
								target = current;
							}
						}
						//Get the desktop's display mode
						DisplayMode desktopDisplayMode = Display.getDesktopDisplayMode();
						//Check the current display mode against the desktop one
						if ((current.getBitsPerPixel() == desktopDisplayMode.getBitsPerPixel()) && current.getFrequency() == desktopDisplayMode.getFrequency()) {
							//This one is the same as the desktop's so it should be the most compatible display mode
							target = current;
							break;
						}
					}
				}
			} else {
				//Set the target display mode
				target = new DisplayMode((int) Settings.Window.Width, (int) Settings.Window.Height);
			}
			//Set the display mode to the target
			Display.setDisplayMode(target);
			//Make the display fullscreen if necessary
			Display.setFullscreen(Settings.Window.Fullscreen);
			//Set VSync if needed
			Display.setVSyncEnabled(Settings.Video.VSync);
			//Set the windows width and height values
			Settings.Window.Width = target.getWidth();
			Settings.Window.Height = target.getHeight();
		} catch (LWJGLException e) {
			//Log an error
			Logger.log("Andor - Window", "An exception has occurred when setting the display mode", Log.ERROR);
			e.printStackTrace();
		}
	}
	
	/* The static method used to update the display's settings */
	public static void updateDisplaySettings() {
		//Reset the display mode
		setDisplayMode();
		//Update OpenGL's resolution
		GL11.glScissor(0 , 0 , (int) Settings.Window.Width, (int) Settings.Window.Height);
		GL11.glViewport(0, 0, (int) Settings.Window.Width, (int) Settings.Window.Height);
	}
	
	/* The static method used to update the display */
	public static void updateDisplay() {
		//Update the display
		Display.update();
		//Sync the display with the maximum FPS
		Display.sync(Settings.Video.MaxFPS);
	}
	
	/* The static method to centre the window */
	public static void centre() {
		//Get the screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//Set the position of the window
		setPosition((screenSize.width / 2) - (Settings.Window.Width / 2), (screenSize.height / 2) - (Settings.Window.Height / 2));
	}
	
	/* The static method used to set the position of this window */
	public static void setPosition(float x, float y) {
		//Set the position of the window
		Display.setLocation((int) x, (int) y);
	}
	
	/* The static method used to set the window icon given a list of images */
	public static void setIcon(Image[] images) {
		ByteBuffer buffer1 = images[0].texture;
		ByteBuffer buffer2 = images[1].texture;
		System.out.println(Display.setIcon(new ByteBuffer[] { buffer1 , buffer2 }));
	}
	
	/* The static method used to determine whether this window should close */
	public static boolean shouldClose() {
		//Return whether close is requested
		return Display.isCloseRequested();
	}
	
	/* The static method used to close this window */
	public static void close() {
		//Destroy the display
		Display.destroy();
	}
	
}