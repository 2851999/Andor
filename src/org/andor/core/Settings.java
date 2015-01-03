/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.andor.utils.ScreenResolution;

public class Settings {
	
	/* The static boolean that states whether Andor is running on Android */
	public static boolean AndroidMode = false;
	
	/* The static class containing all of the information about Andor */
	public static class Information {
		/* The version of Andor */
		public static final String VERSION = "V0.0.2.7";
		/* The build of Andor */
		public static final String BUILD = "Experimental";
		/* The date this build started development */
		public static final String DATE = "03/01/2015";
	}
	
	/* The static class containing all of the settings required for the window */
	public static class Window {
		/* The title of the window */
		public static String Title = "Andor";
		/* The width and of the window */
		public static float Width = 800;
		public static float Height = 600;
		/* The boolean that states whether the window is fullscreen */
		public static boolean Fullscreen = false;
		/* The boolean that states whether the window should be undecorated */
		public static boolean Undecorated = false;
	}
	
	/* The static class containing all of the video settings */
	public static class Video {
		/* The boolean that states whether VSync should be enabled */
		public static boolean VSync = false;
		/* The boolean that states whether anti aliasing should be used */
		public static boolean AntiAliasing = false;
		/* The maximum FPS */
		public static int MaxFPS = 60;
		/* The preffered resolution (Fullscreen only) */
		public static ScreenResolution Resolution = ScreenResolution.RES_NATIVE;
	}
	
	/* The static class containing all of the Android settings */
	public static class Android {
		/* The setting for the screen orientation portrait */
		public static final int SCREEN_ORIENTATION_PORTRAIT = 1;
		/* The setting for the screen orientation landscape */
		public static final int SCREEN_ORIENTATION_LANDSCAPE = 2;
		/* The setting for the screen orientation */
		public static int ScreenOrientation = SCREEN_ORIENTATION_PORTRAIT;
		/* The setting that states whether sounds should be paused when
		 * onPause() is called */
		public static boolean PauseSoundsOnPause = true;
	}
	
	/* The static class containing all of the Engine settings */
	public static class Engine {
		/* The default font that will be used (Initialised in the GameLoop/AndroidDisplayRenderer classes) */
		public static Font DefaultFont;
	}
	
	/* The static class containing all of the places and names of all of the resources needed by Andor */
	public static class Resources {
		public static String FONT_DEFAULT = "/resources/andor/defaultfont.png";
		public static String SHADER_FORWARD_DEFAULT = "/resources/andor/shaders/render/forward/andorshader";
		public static String SHADER_FORWARD_LIGHT = "/resources/andor/shaders/render/forward/lighting/lightfrag";
	}
	
}