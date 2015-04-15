/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;


public class CircleCollider extends Collider2D {
	
	/* The circle */
	public Circle circle;

	/* The constructor */
	public CircleCollider(PhysicsObject2D object, Circle circle) {
		super(object);
		//Assign the variables
		this.circle = circle;
	}
	
	/* The method used to check for a collision between two objects */
	public CollisionData checkCollision(Collider2D other) {
		if (other instanceof CircleCollider)
			return checkCollision((CircleCollider) other);
		else
			return null;
	}
	
	/* The method used to check for a collision between two objects */
	public CollisionData checkCollision(CircleCollider collider) {
		//Get the other circle
		Circle other = collider.getBounds();
		
		//Get the distances between the two circles
		float radiusDistance = circle.getRadius() + other.getRadius();
		float centreDistance = other.getCentre().subtractNew(circle.getCentre()).getLength();
		
		//Get the overall distance between their surfaces
		float distance = centreDistance - radiusDistance;
		
		//Return the data
		return new CollisionData(centreDistance < radiusDistance, distance);
	}
	
	/* The getters and setters */
	public void setBounds(Circle circle) { this.circle = circle; }
	public Circle getBounds() { return this.circle; }
	
}