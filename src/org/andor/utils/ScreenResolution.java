/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils;

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