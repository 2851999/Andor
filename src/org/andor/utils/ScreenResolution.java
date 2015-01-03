/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class ScreenResolution {
	
	/* Some default resolutions that can be used */
	public static final ScreenResolution RES_640x480 = new ScreenResolution(640, 480);
	public static final ScreenResolution RES_1280x720 = new ScreenResolution(1280, 720);
	public static final ScreenResolution RES_1366x768 = new ScreenResolution(1366, 768);
	public static final ScreenResolution RES_1920x1080 = new ScreenResolution(1920, 1080);
	public static final ScreenResolution RES_2560x1440 = new ScreenResolution(2560, 1440);
	public static final ScreenResolution RES_3840x2160 = new ScreenResolution(3840, 2160);
	public static final ScreenResolution RES_NATIVE = new ScreenResolution(ScreenUtils.getScreenWidth(), ScreenUtils.getScreenHeight());
	public static final ScreenResolution RES_720P = RES_1280x720;
	public static final ScreenResolution RES_1080P = RES_1920x1080;
	public static final ScreenResolution RES_1440P = RES_2560x1440;
	public static final ScreenResolution RES_4K = RES_3840x2160;
	
	/* The static method used to get a list of available display modes */
	public static DisplayMode[] getAvailableDisplayModes() throws LWJGLException {
		return Display.getAvailableDisplayModes();
	}
	
	/* The static method used to get a list of supported resolutions as a string array */
	public static String[] getSupportedResolutions() {
		//Try and catch an error
		try {
			//Get the list of available display modes
			DisplayMode[] modes = getAvailableDisplayModes();
			//Create the list of resolutions
			String[] supported = new String[modes.length];
			//Go through each mode
			for (int a = 0; a < modes.length; a++)
				//Assign the current mode
				supported[a] = "" + modes[a].getWidth() + "x" + modes[a].getHeight() + " " + modes[a].getFrequency();
			//Return the array
			return supported;
		} catch (LWJGLException e) {
			e.printStackTrace();
			//Return nothing
			return null;
		}
	}
	
	/* The width and height of this resolution */
	public int width;
	public int height;
	
	/* The constructor */
	public ScreenResolution(int width, int height) {
		//Assign the variables
		this.width = width;
		this.height = height;
	}
	
	/* The 'set' and 'get' methods */
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }

}