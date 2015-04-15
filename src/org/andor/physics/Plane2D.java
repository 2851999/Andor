/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.Vector2D;

public class Plane2D {

	/* The normal of this plane */
	private Vector2D normal;
	
	/* The distance / position along the normal this plane is */
	private float distance;
	
	/* The constructor */
	public Plane2D(Vector2D normal, float distance) {
		//Assign the variables
		this.normal = normal;
		this.distance = distance;
	}
	
	/* The method used to return a normalised version of this plane */
	public Plane2D normalise() {
		//Get the length of the normal
		float length = this.normal.getLength();
		//Return the new normalised plane
		return new Plane2D(this.normal.divideNew(length), this.distance / length);
	}
	
	/* The getters and setters */
	public Vector2D getNormal() { return this.normal; }
	public float getDistance() { return this.distance; }
	
}