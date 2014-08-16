/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

public class Settings {
	
	/* The static class containing all of the information about Andor */
	public static class Information {
		/* The version of Andor */
		public static final String VERSION = "V0.0.1.9";
		/* The build of Andor */
		public static final String BUILD = "Development";
		/* The date this build started development */
		public static final String DATE = "16/08/2014";
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
	
	/* The static class containing all of the audio settings */
	public static class Audio {
		/* The sound effect volume */
		public static int SoundEffectVolume = 100;
		/* The music volume */
		public static int MusicVolume = 100;
	}
	
}