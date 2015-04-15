/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.utils.MathUtils;


public class PlaneCollider2D extends Collider2D {
	
	/* The plane */
	private Plane2D plane;

	/* The constructor */
	public PlaneCollider2D(PhysicsObject2D object, Plane2D plane) {
		super(object);
		//Assign the variables
		this.plane = plane;
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
		
		float distanceFromCircleCentre = MathUtils.abs(plane.getNormal().dot(other.getCentre()) + plane.getDistance());
		float distanceFromSphere = distanceFromCircleCentre - other.getRadius();
		
		//Return the data
		return new CollisionData(distanceFromSphere < 0, distanceFromSphere);
	}
	
	/* The getters and setters */
	public void setBounds(Plane2D plane) { this.plane = plane; }
	public Plane2D getBounds() { return this.plane; }
	
}