/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.utils.MathUtils;


public class PlaneCollider3D extends Collider3D {
	
	/* The plane */
	private Plane3D plane;

	/* The constructor */
	public PlaneCollider3D(PhysicsObject3D object, Plane3D plane) {
		super(object);
		//Assign the variables
		this.plane = plane;
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
		Sphere other = collider.getBounds();
		
		float distanceFromCircleCentre = MathUtils.abs(plane.getNormal().dot(other.getCentre()) + plane.getDistance());
		float distanceFromSphere = distanceFromCircleCentre - other.getRadius();
		
		//Return the data
		return new CollisionData3D(distanceFromSphere < 0, plane.getNormal().multiplyNew(distanceFromSphere));
	}
	
	/* The getters and setters */
	public void setBounds(Plane3D plane) { this.plane = plane; }
	public Plane3D getBounds() { return this.plane; }
	
}