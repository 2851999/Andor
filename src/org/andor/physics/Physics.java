/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.Settings;
import org.andor.utils.FPSCalculator;

public class Physics {
	
	/* The class containing all of the constants */
	public static class Constants {
		public static final double GRAVITATIONAL_CONSTANT = 6.67384e-11;
	}
	
	/* The FPS calculator */
	public static FPSCalculator fpsCalculator;
	
	/* The current delta in seconds */
	public static float deltaSeconds;
	
	/* The current update delta */
	public static float deltaUpdate;
	
	/* The static method used to setup the physics */
	public static void setup() {
		//Create the fps calculator
		fpsCalculator = new FPSCalculator();
	}
	
	/* The static method used to update the physics */
	public static void update() {
		//Update the fps calculator
		fpsCalculator.update();
		//Calculate the current delta in seconds
		deltaSeconds = (float) fpsCalculator.currentDeltaTime / 1000f;
		//Avoid getting infinity
		if (deltaSeconds != 0)
			//Calculate the current update delta in seconds
			deltaUpdate = (1f / Settings.Physics.TargetUpdate) / deltaSeconds;
	}
	
}