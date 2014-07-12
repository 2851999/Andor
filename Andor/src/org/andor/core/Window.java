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

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

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
				target = new DisplayMode(Settings.Window.Width, Settings.Window.Height);
			}
			//Set the display mode to the target
			Display.setDisplayMode(target);
			//Make the display fullscreen if necessary
			Display.setFullscreen(Settings.Window.Fullscreen);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
}