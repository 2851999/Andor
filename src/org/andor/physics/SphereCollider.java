/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.Vector3D;


public class SphereCollider extends Collider3D {

	/* The constructor */
	public SphereCollider(PhysicsObject3D object) {
		super(object);
	}
	
	/* The method used to check for a collision between two objects */
	public CollisionData3D checkCollision(Collider3D other) {
		if (other instanceof SphereCollider)
			return checkCollision((SphereCollider) other);
		else
			return null;
	}
	
	/* The method used to check for a collision between two objects */
	public CollisionData3D checkCollision(SphereCollider collider) {
		//Get the other circle
		Sphere sphere = getBounds();
		Sphere other = collider.getBounds();
		
		//Get the distances between the two circles
		float radiusDistance = sphere.getRadius() + other.getRadius();
		Vector3D direction = other.getCentre().subtractNew(sphere.getCentre());
		float centreDistance = direction.getLength();
		direction.divide(centreDistance);
		//Get the overall distance between their surfaces
		float distance = centreDistance - radiusDistance;
		
		
		//Return the data
		return new CollisionData3D(distance < 0, direction.multiplyNew(distance));
	}
	
	/* The getters and setters */
	public Sphere getBounds() {
		return this.getObject().getSphereBounds();
	}
	
}