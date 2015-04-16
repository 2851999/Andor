/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.Vector2D;


public class CircleCollider extends Collider2D {

	/* The constructor */
	public CircleCollider(PhysicsObject2D object) {
		super(object);
	}
	
	/* The method used to check for a collision between two objects */
	public CollisionData2D checkCollision(Collider2D other) {
		if (other instanceof CircleCollider)
			return checkCollision((CircleCollider) other);
		else
			return null;
	}
	
	/* The method used to check for a collision between two objects */
	public CollisionData2D checkCollision(CircleCollider collider) {
		//Get the other circle
		Circle circle = getBounds();
		Circle other = collider.getBounds();
		
		//Get the distances between the two circles
		float radiusDistance = circle.getRadius() + other.getRadius();
		Vector2D direction = other.getCentre().subtractNew(circle.getCentre());
		float centreDistance = direction.getLength();
		direction.divide(centreDistance);
		
		//Get the overall distance between their surfaces
		float distance = centreDistance - radiusDistance;
		
		//Return the data
		return new CollisionData2D(distance < 0, direction.multiplyNew(distance));
	}
	
	/* The getters and setters */
	public Circle getBounds() {
		return this.getObject().getCircleBounds();
	}
	
}