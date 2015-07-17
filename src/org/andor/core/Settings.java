/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import org.andor.utils.ScreenResolution;

public class Settings {
	
	/* The static boolean that states whether Andor is running on Android */
	public static boolean AndroidMode = false;
	
	/* The static class containing all of the information about Andor */
	public static class Information {
		/* The version of Andor */
		public static final String VERSION = "V1.0.6";
		/* The build of Andor */
		public static final String BUILD = "Experimental";
		/* The date this build started development */
		public static final String DATE = "17/07/2015";
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
		/* The boolean that states whether the window is a fullscreen window */
		public static boolean WindowedFullscreen = false;
		/* The boolean that states whether the window should be undecorated */
		public static boolean Undecorated = false;
		/* The boolean that states whether the window should be resizable */
		public static boolean Resizable = false;
		
		/* The static method used to set the window size */
		public static void setSize(ScreenResolution resolution) {
			Width = resolution.getWidth();
			Height = resolution.getHeight();
		}
		
		/* The static method used to set the window size */
		public static void setSize(Vector2D size) {
			Width = size.getX();
			Height = size.getY();
		}
		
		/* The static method used to set the window size */
		public static void setSize(float width, float height) {
			Width = width;
			Height = height;
		}
	}
	
	/* The static class containing all of the video settings */
	public static class Video {
		/* The boolean that states whether VSync should be enabled */
		public static boolean VSync = false;
		/* The maximum FPS */
		public static int MaxFPS = 60;
		/* The preferred resolution (Fullscreen only) */
		public static ScreenResolution Resolution = ScreenResolution.RES_NATIVE;
		/* Whether any renderers should setup for deferred rendering */
		public static boolean DeferredRendering = false;
		/* The maximum number of samples to do using anisotropic filtering */
		public static int MaxAnisotropicSamples = 16;
		/* The MSAA samples 0 if not used */
		public static int Samples = 0;
	}
	
	/* The static class containing all of the physics settings */
	public static class Physics {
		/* The target number of times to update the physics per second */
		public static int TargetUpdate = 60;
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
		public static String FONT_DEFAULT = "/resources/andor/SEGOEUI.TTF";
		public static class Shaders {
			public static String FORWARD_DEFAULT = "/resources/andor/shaders/StandardForwardShader";
			public static String FORWARD_AMBIENT_LIGHT = "/resources/andor/shaders/ForwardAmbientLight";
			public static String FORWARD_DIRECTIONAL_LIGHT = "/resources/andor/shaders/ForwardDirectionalLight";
			public static String FORWARD_POINT_LIGHT = "/resources/andor/shaders/ForwardPointLight";
			public static String FORWARD_SPOT_LIGHT = "/resources/andor/shaders/ForwardSpotLight";
			public static String DEFERRED_GEOMETRY_PASS = "/resources/andor/shaders/StandardDeferredGeometryShader";
			public static String DEFERRED_FINAL_PASS = "/resources/andor/shaders/StandardDeferredFinalShader";
			public static String SHADOWMAP_GENERATOR = "/resources/andor/shaders/ShadowMapGenerator";
			public static String SHADOWMAP_FILTER = "/resources/andor/shaders/FilterGaussianBlur7x1";
		}
		public static class Icons {
			public static String ICON_16 = "/resources/andor/Icon16.png";
			public static String ICON_32 = "/resources/andor/Icon32.png";
		}
		public static Image DEFAULT_TEXTURE; //Assigned with render passes
		public static Image DEFAULT_NORMAL_MAP; //Assigned with render passes
	}
	
	/* The static class containing all of the debugging settings */
	public static class Debugging {
		/* The setting for turning on, on screen information */
		public static boolean ShowInformation = false;
		/* The setting for showing all of the buffers when deferred rendering */
		public static boolean ShowDeferredRenderingBuffers = false;
	}
	
}