/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.model;

import org.andor.core.Vector3D;

public class ModelParserUtils {
	
	/* The static method used to get the vector value of a line */
	public static Vector3D getVectorValue(String line) {
		//Split up the line using a space
		String[] split = line.split(" ");
		//Get the vector values
		float vectorX = getValue(split[1]);
		float vectorY = getValue(split[2]);
		float vectorZ = 0;
		//Check to see whether there is a third split (Texture coordinates
		//may only have two values)
		if (split.length == 4)
			vectorZ = getValue(split[3]);
		//Create the vector
		Vector3D vector = new Vector3D(vectorX, vectorY, vectorZ);
		//Return the vector
		return vector;
	}

	/* The static method used to get the double value of a string */
	public static float getValue(String value) {
		//Return the float
		return Float.parseFloat(value);
	}
	
}