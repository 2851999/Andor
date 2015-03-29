/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.model;

import org.andor.core.Vector2D;
import org.andor.core.Vector3D;

public class ModelParserUtils {
	
	/* The static method used to get the vector value of a line */
	public static Vector3D getVectorValue3D(String line) {
		//Split up the line using a space
		String[] split = line.split(" ");
		//Get the vector values
		float vectorX = getValue(split[1]);
		float vectorY = getValue(split[2]);
		float vectorZ = getValue(split[3]);
		//Return the vector
		return new Vector3D(vectorX, vectorY, vectorZ);
	}
	
	/* The static method used to get the vector value of a line */
	public static Vector2D getVectorValue2D(String line) {
		//Split up the line using a space
		String[] split = line.split(" ");
		//Get the vector values
		float vectorX = getValue(split[1]);
		float vectorY = getValue(split[2]);
		//Return the vector
		return new Vector2D(vectorX, vectorY);
	}

	/* The static method used to get the double value of a string */
	public static float getValue(String value) {
		//Return the float
		return Float.parseFloat(value);
	}
	
}