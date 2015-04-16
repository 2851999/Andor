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

	/* The constructor */
	public AABBCollider2D(PhysicsObject2D object) {
		super(object);
	}
	
	/* The method used to check for a collision between two objects */
	public CollisionData2D checkCollision(Collider2D other) {
		if (other instanceof AABBCollider2D)
			return checkCollision((AABBCollider2D) other);
		else
			return null;
	}
	
	/* The method used to check for a collision between two objects */
	public CollisionData2D checkCollision(AABBCollider2D collider) {
		//Get the other AABB
		AABB2D aabb = getBounds();
		AABB2D other = collider.getBounds();
		
		Vector2D distances1 = other.getMinimum().subtractNew(aabb.getMaximum());
		Vector2D distances2 = aabb.getMinimum().subtractNew(other.getMaximum());
		Vector2D distances = distances1.max(distances2);
		Vector2D direction = collider.getObject().getPosition().subtractNew(getObject().getPosition());
		
		float maxDistance = distances.max();
		
		//Return the data
		return new CollisionData2D(maxDistance < 0, direction.multiplyNew(maxDistance));
	}
	
	/* The getters and setters */
	public AABB2D getBounds() { return this.getObject().getAABBBounds(); }
	
}