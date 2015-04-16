/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.Vector3D;

public class Plane3D {

	/* The normal of this plane */
	private Vector3D normal;
	
	/* The distance / position along the normal this plane is */
	private float distance;
	
	/* The constructor */
	public Plane3D(Vector3D normal, float distance) {
		//Assign the variables
		this.normal = normal;
		this.distance = distance;
	}
	
	/* The method used to return a normalised version of this plane */
	public Plane3D normalise() {
		//Get the length of the normal
		float length = this.normal.getLength();
		//Return the new normalised plane
		return new Plane3D(this.normal.divideNew(length), this.distance / length);
	}
	
	/* The getters and setters */
	public Vector3D getNormal() { return this.normal; }
	public float getDistance() { return this.distance; }
	
}