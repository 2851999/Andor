/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.Vector3D;


public class AABBCollider3D extends Collider3D {

	/* The constructor */
	public AABBCollider3D(PhysicsObject3D object) {
		super(object);
	}
	
	/* The method used to check for a collision between two objects */
	public CollisionData3D checkCollision(Collider3D other) {
		if (other instanceof AABBCollider3D)
			return checkCollision((AABBCollider3D) other);
		else
			return null;
	}
	
	/* The method used to check for a collision between two objects */
	public CollisionData3D checkCollision(AABBCollider3D collider) {
		//Get the other AABB
		AABB3D aabb = getBounds();
		AABB3D other = collider.getBounds();
		
		Vector3D distances1 = other.getMinimum().subtractNew(aabb.getMaximum());
		Vector3D distances2 = aabb.getMinimum().subtractNew(other.getMaximum());
		Vector3D distances = distances1.max(distances2);
		Vector3D direction = collider.getObject().getPosition().subtractNew(getObject().getPosition());
		
		float maxDistance = distances.max();
		
		//Return the data
		return new CollisionData3D(maxDistance < 0, direction.multiplyNew(maxDistance));
	}
	
	/* The getters and setters */
	public AABB3D getBounds() { return this.getObject().getAABBBounds(); }
	
}