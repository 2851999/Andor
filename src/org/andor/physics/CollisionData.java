/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

public class CollisionData {
	
	/* The boolean that states whether there is a collision */
	private boolean intersects;
	
	/* The penetration distance of the two objects */
	private float distance;
	
	/* The constructor */
	public CollisionData(boolean intersects, float distance) {
		//Assign the variables
		this.intersects = intersects;
		this.distance = distance;
	}
	
	/* The getters and setters */
	public boolean doesIntersect() { return this.intersects; }
	public float getDistance() { return this.distance; }
	
}