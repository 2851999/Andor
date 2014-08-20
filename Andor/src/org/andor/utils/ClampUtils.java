/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

public class ClampUtils {
	
	/* The static method used to clamp a value given the minimum and maximum values it can be */
	public static float clamp(float value, float min, float max) {
		//Check the value against the min/max
		if (value < min)
			value = min;
		else if (value > max)
			value = max;
		//Return the clamped value
		return value;
	}
	
}