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
	public float angularVelocity;
	
	/* The acceleration of this object (M/S^2) */
	public Vector2D acceleration;
	public float angularAcceleration;
	
	/* The mass of this object */
	public float mass;
	
	/* The values that determine whether certain values are used */
	public boolean useVelocityX;
	public boolean useVelocityY;
	public boolean useAccelerationX;
	public boolean useAccelerationY;
	
	/* The collider instance for this object */
	public Collider2D collider;
	
	/* The constructor */
	public PhysicsObject2D() {
		
	}
	
	/* The constructor */
	public PhysicsObject2D(Object2D object) {
		//Setup this object
		this.setup(object);
	}
	
	/* The method used to setup this object */
	public void setup(Object2D object) {
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
		this.width = object.width;
		this.height = object.height;
		this.mass = 1;
		this.useVelocityX = true;
		this.useVelocityY = true;
		this.useAccelerationX = true;
		this.useAccelerationY = true;
	}
	
	/* The method used to update this physics object given the delta */
	public void update() {
		//Assign the values
		Vector2D a = this.acceleration.multiplyNew(Physics.deltaSeconds);
		Vector2D v = this.velocity.multiplyNew(Physics.deltaSeconds);
		
		//Apply any changes needed
		if (! this.useAccelerationX)
			a.x = 0;
		if (! this.useAccelerationY)
			a.y = 0;
		if (! this.useVelocityX)
			v.x = 0;
		if (! this.useVelocityY)
			v.y = 0;
		
		//Apply the current acceleration
		this.velocity.add(a);
		this.angularVelocity += this.angularAcceleration * Physics.deltaSeconds;
		//Apply the current velocity
		this.position.add(v);
		this.rotation += this.angularVelocity * Physics.deltaSeconds;
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
	
	/* The method used to get the circle representation of this object */
	public Circle getCircleBounds() {
		return new Circle(getPosition(), getSize().max() / 2);
	}
	
	/* The method used to get the AABB representation of this object */
	public AABB2D getAABBBounds() {
		return new AABB2D(getPosition(), getSize());
	}
	
	/* The 'set' and 'get' methods */
	public void setVelocity(Vector2D velocity) { this.velocity = velocity; }
	public void setVelocity(float x, float y) { this.velocity = new Vector2D(x, y); }
	public void setAngularVelocity(float angularVelocity) { this.angularVelocity = angularVelocity; }
	public void setAcceleration(Vector2D acceleration) { this.acceleration = acceleration; }
	public void setAcceleration(float x, float y) { this.acceleration = new Vector2D(x, y); }
	public void setAngularAcceleration(float angularAcceleration) { this.angularAcceleration = angularAcceleration; }
	public void setMass(float mass) { this.mass = mass; }
	public void setCollider(Collider2D collider) { this.collider = collider; }
	public void useVelocityX(boolean useVelocityX) { this.useVelocityX = useVelocityX; }
	public void useVelocityY(boolean useVelocityY) { this.useVelocityY = useVelocityY; }
	public void useAccelerationX(boolean useAccelerationX) { this.useAccelerationX = useAccelerationX; }
	public void useAccelerationY(boolean useAccelerationY) { this.useAccelerationY = useAccelerationY; }
	public Vector2D getVelocity() { return this.velocity; }
	public float getAngularVelocity() { return this.angularVelocity; }
	public Vector2D getAcceleration() { return this.acceleration; }
	public float getAngularAcceleration() { return this.angularAcceleration; }
	public float getMass() { return this.mass; }
	public Collider2D getCollider() { return this.collider; }
	public boolean shouldUseVelocityX() { return this.useVelocityX; }
	public boolean shouldUseVelocityY() { return this.useVelocityY; }
	public boolean shouldUseAccelerationX() { return this.useAccelerationX; }
	public boolean shouldUseAccelerationY() { return this.useAccelerationY; }
	
}