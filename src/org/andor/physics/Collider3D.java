/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

public abstract class Collider3D {
	
	/* The object this is attached to */
	private PhysicsObject3D object;
	
	/* The constructor */
	public Collider3D(PhysicsObject3D object) { this.object = object; }
	
	/* The method used to check for a collision between two objects */
	public abstract CollisionData3D checkCollision(Collider3D other);
	
	/* The getters and setters */
	public PhysicsObject3D getObject() { return this.object; }
	
}