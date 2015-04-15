/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils;

import org.andor.core.Colour;
import org.andor.core.Vector2D;
import org.andor.core.Vector3D;

public class MathUtils {
	
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
	
	/* The static method used to clamp a colour */
	public static Colour clamp(Colour colour, float min, float max) {
		//Check the values
		if (colour.r < min)
			colour.r = min;
		else if (colour.r > max)
			colour.r = max;
		
		if (colour.g < min)
			colour.g = min;
		else if (colour.g > max)
			colour.g = max;
		
		if (colour.b < min)
			colour.b = min;
		else if (colour.b > max)
			colour.b = max;
		
		if (colour.a < min)
			colour.a = min;
		else if (colour.a > max)
			colour.a = max;
		//Return the colour
		return colour;
	}
	
	/* The static method used to clamp a colour */
	public static Colour clamp(Colour colour) {
		return clamp(colour, 0.0f, 1.0f);
	}
	
	/* The static method used to return the minimum value */
	public static float min(float a, float b) {
		if (a < b)
			return a;
		else
			return b;
	}
	
	/* The static method used to return the maximum value */
	public static float max(float a, float b) {
		if (a > b)
			return a;
		else
			return b;
	}
	
	/* The static method used to return the absolute value of something */
	public static float abs(float value) {
		if (value < 0)
			value *= -1;
		return value;
	}
	
	/* The static method used to return the absolute value of a Vector2D */
	public static Vector2D abs(Vector2D vector) {
		return new Vector2D(abs(vector.x), abs(vector.y));
	}
	
	/* The static method used to return the absolute value of a Vector3D */
	public static Vector3D abs(Vector3D vector) {
		return new Vector3D(abs(vector.x), abs(vector.y), abs(vector.z));
	}
	
}