/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

public abstract class Collider2D {
	
	/* The object this is attached to */
	private PhysicsObject2D object;
	
	/* The constructor */
	public Collider2D(PhysicsObject2D object) { this.object = object; }
	
	/* The method used to check for a collision between two objects */
	public abstract CollisionData checkCollision(Collider2D other);
	
	/* The getters and setters */
	public PhysicsObject2D getObject() { return this.object; }
	
}