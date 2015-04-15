/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.Vector2D;


public class AABBCollider2D extends Collider2D {
	
	/* The AABB 2D */
	private AABB2D aabb;

	/* The constructor */
	public AABBCollider2D(PhysicsObject2D object, AABB2D aabb) {
		super(object);
		//Assign the variables
		this.aabb = aabb;
	}
	
	/* The method used to check for a collision between two objects */
	public CollisionData checkCollision(Collider2D other) {
		if (other instanceof AABBCollider2D)
			return checkCollision((AABBCollider2D) other);
		else
			return null;
	}
	
	/* The method used to check for a collision between two objects */
	public CollisionData checkCollision(AABBCollider2D collider) {
		//Get the other AABB
		AABB2D other = collider.getBounds();
		
		Vector2D distances1 = other.getMinimum().subtractNew(aabb.getMaximum());
		Vector2D distances2 = aabb.getMinimum().subtractNew(other.getMaximum());
		Vector2D distances = distances1.max(distances2);
		
		float maxDistance = distances.max();
		
		//Return the data
		return new CollisionData(maxDistance < 0, maxDistance);
	}
	
	/* The getters and setters */
	public void setBounds(AABB2D aabb) { this.aabb = aabb; }
	public AABB2D getBounds() { return this.aabb; }
	
}