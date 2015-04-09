/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.Object2D;
import org.andor.core.Vector2D;

public class PhysicsObject2D extends Object2D {
	
	/*
	 * NOTE: Currently each pixel of the screen represents 1 meter (2D)
	 */
	
	/* The velocity of this object (M/S) */
	public Vector2D velocity;
	
	/* The acceleration of this object (M/S^2) */
	public Vector2D acceleration;
	
	/* The mass of this object */
	public float mass;
	
	/* The constructor */
	public PhysicsObject2D(Object2D object) {
		//Attach the object to this
		this.link(object);
		/*
		 * NOTE
		 * 
		 * Currently, unless this is directly implemented into Andor
		 * the physics object and render object will have to be kept
		 * separate, where you will link the render object to the
		 * physics object above but change scale, rotation or
		 * position in the physics object
		 */
		
		//Assign the variables
		this.velocity = new Vector2D();
		this.acceleration = new Vector2D();
		this.mass = 1;
	}
	
	/* The method used to update this physics object given the delta */
	public void update() {
		//Apply the current acceleration
		this.velocity.add(this.acceleration.multiplyNew(Physics.deltaSeconds));
		//Apply the current velocity
		this.position.add(this.velocity.multiplyNew(Physics.deltaSeconds));
	}
	
	/* The method used to apply a force on this object */
	public void applyForce(Vector2D force) {
		//F = MA, therefore A = F / M
		//Make sure the mass isn't 0
		if (this.mass != 0)
			//Calculate the acceleration due to the force and add it (Sudden impulse, ignore time
			//so just add to velocity instead of acceleration)
			this.velocity.add(force.divideNew(this.mass));
	}
	
	/* The 'set' and 'get' methods */
	public void setVelocity(Vector2D velocity) { this.velocity = velocity; }
	public void setVelocity(float x, float y) { this.velocity = new Vector2D(x, y); }
	public void setAcceleration(Vector2D acceleration) { this.acceleration = acceleration; }
	public void setAcceleration(float x, float y) { this.acceleration = new Vector2D(x, y); }
	public void setMass(float mass) { this.mass = mass; }
	public Vector2D getVelocity() { return this.velocity; }
	public Vector2D getAcceleration() { return this.acceleration; }
	public float getMass() { return this.mass; }
	
}