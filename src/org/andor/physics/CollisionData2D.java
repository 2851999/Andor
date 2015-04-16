/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.Vector2D;

public class CollisionData2D {
	
	/* The boolean that states whether there is a collision */
	private boolean intersects;
	
	/* The direction and distance of the collision */
	private Vector2D direction;
	
	/* The constructor */
	public CollisionData2D(boolean intersects, Vector2D direction) {
		//Assign the variables
		this.intersects = intersects;
		this.direction = direction;
	}
	
	/* The getters and setters */
	public boolean doesIntersect() { return this.intersects; }
	public float getDistance() { return this.direction.getLength(); }
	public Vector2D getDirection() { return this.direction; }
	
}