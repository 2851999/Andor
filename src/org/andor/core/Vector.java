/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

public class Vector {
	
	/* The static method used to return the distance between two vectors */
	public static float distanceBetween(Vector2D v1, Vector2D v2) {
		//Return the value
		return new Vector2D(v2.x - v1.x, v2.y - v1.y).getLength();
	}
	
	/* The static method used to return the distance between two vectors */
	public static float distanceBetween(Vector3D v1, Vector3D v2) {
		//Return the value
		return new Vector3D(v2.x - v1.x, v2.y - v1.y, v2.z - v1.z).getLength();
	}
	
}