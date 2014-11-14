/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

public class Settings {
	
	/* The static boolean that states whether Andor is running on Android */
	public static boolean AndroidMode = false;
	
	/* The static class containing all of the information about Andor */
	public static class Information {
		/* The version of Andor */
		public static final String VERSION = "V0.0.2.5";
		/* The build of Andor */
		public static final String BUILD = "Experimental";
		/* The date this build started development */
		public static final String DATE = "13/11/2014";
	}
	
	/* The static class containing all of the settings required for the window */
	public static class Window {
		/* The title of the window */
		public static String Title = "Andor";
		/* The width and height of the window */
		public static float Width = 800;
		public static float Height = 600;
		/* The boolean that states whether the window is fullscreen */
		public static boolean Fullscreen = false;
	}
	
	/* The static class containing all of the video settings */
	public static class Video {
		/* The boolean that states whether VSync should be enabled */
		public static boolean VSync = false;
		/* The boolean that states whether anti aliasing should be used */
		public static boolean AntiAliasing = false;
		/* The maximum FPS */
		public static int MaxFPS = 60;
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
		/* The default font that will be used (Initialised in the GameLoop class) */
		public static Font DefaultFont;
	}
	
}