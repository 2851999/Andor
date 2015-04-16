/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.Object3D;
import org.andor.core.Vector3D;

public class PhysicsObject3D extends Object3D {
	
	/* The velocity of this object (M/S) */
	public Vector3D velocity;
	public Vector3D angularVelocity;
	
	/* The acceleration of this object (M/S^2) */
	public Vector3D acceleration;
	public Vector3D angularAcceleration;
	
	/* The mass of this object */
	public float mass;
	
	/* The values that determine whether certain values are used */
	public boolean useVelocityX;
	public boolean useVelocityY;
	public boolean useAccelerationX;
	public boolean useAccelerationY;
	
	/* The restitution ( 0.0 - 1.0) */
	public float restitution;
	
	/* The collider instance for this object */
	public Collider3D collider;
	
	/* The constructor */
	public PhysicsObject3D() {
		
	}
	
	/* The constructor */
	public PhysicsObject3D(Object3D object) {
		//Setup this object
		this.setup(object);
	}
	
	/* The method used to setup this object */
	public void setup(Object3D object) {
		//Attach the object to this
		this.link(object);
		
		//Assign the variables
		this.velocity = new Vector3D();
		this.acceleration = new Vector3D();
		this.angularVelocity = new Vector3D();
		this.angularAcceleration = new Vector3D();
		this.width = object.width;
		this.height = object.height;
		this.depth = object.depth;
		this.mass = 1;
		this.restitution = 0;
		this.useVelocityX = true;
		this.useVelocityY = true;
		this.useAccelerationX = true;
		this.useAccelerationY = true;
	}
	
	/* The method used to update this physics object given the delta */
	public void update() {
		//Assign the values
		Vector3D a = this.acceleration.multiplyNew(Physics.deltaSeconds);
		Vector3D v = this.velocity.multiplyNew(Physics.deltaSeconds);
		
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
		this.angularVelocity.add(this.angularAcceleration.multiplyNew(Physics.deltaSeconds));
		//Apply the current velocity
		this.position.add(v);
		this.rotation.add(this.angularVelocity.multiplyNew(Physics.deltaSeconds));
	}
	
	/* The method used to apply a force on this object */
	public void applyForce(Vector3D force) {
		//Make sure the mass isn't 0
		if (this.mass != 0)
			this.velocity.add(force.divideNew(this.mass));
	}
	
	/* The method used to get the circle representation of this object */
	public Sphere getSphereBounds() {
		return new Sphere(getPosition(), getSize().max() / 2);
	}
	
	/* The method used to get the AABB representation of this object */
	public AABB3D getAABBBounds() {
		return new AABB3D(getPosition(), getPosition().addNew(getSize()));
	}
	
	/* The 'set' and 'get' methods */
	public void setVelocity(Vector3D velocity) { this.velocity = velocity; }
	public void setVelocity(float x, float y, float z) { this.velocity = new Vector3D(x, y, z); }
	public void setAngularVelocity(Vector3D angularVelocity) { this.angularVelocity = angularVelocity; }
	public void setAngularVelocity(float x, float y, float z) { this.angularVelocity = new Vector3D(x, y, z); }
	public void setAcceleration(Vector3D acceleration) { this.acceleration = acceleration; }
	public void setAcceleration(float x, float y, float z) { this.acceleration = new Vector3D(x, y, z); }
	public void setAngularAcceleration(Vector3D angularAcceleration) { this.angularAcceleration = angularAcceleration; }
	public void setAngularAcceleration(float x, float y, float z) { this.angularAcceleration = new Vector3D(x, y, z); }
	public void setMass(float mass) { this.mass = mass; }
	public void setRestitution(float restitution) { this.restitution = restitution; }
	public void setCollider(Collider3D collider) { this.collider = collider; }
	public void useVelocityX(boolean useVelocityX) { this.useVelocityX = useVelocityX; }
	public void useVelocityY(boolean useVelocityY) { this.useVelocityY = useVelocityY; }
	public void useAccelerationX(boolean useAccelerationX) { this.useAccelerationX = useAccelerationX; }
	public void useAccelerationY(boolean useAccelerationY) { this.useAccelerationY = useAccelerationY; }
	public Vector3D getVelocity() { return this.velocity; }
	public Vector3D getAngularVelocity() { return this.angularVelocity; }
	public Vector3D getAcceleration() { return this.acceleration; }
	public Vector3D getAngularAcceleration() { return this.angularAcceleration; }
	public float getMass() { return this.mass; }
	public float getInverseMass() {
		if (this.mass != 0)
			return 1 / this.mass;
		else
			return 0;
	}
	public float getRestitution() { return this.restitution; }
	public Collider3D getCollider() { return this.collider; }
	public boolean shouldUseVelocityX() { return this.useVelocityX; }
	public boolean shouldUseVelocityY() { return this.useVelocityY; }
	public boolean shouldUseAccelerationX() { return this.useAccelerationX; }
	public boolean shouldUseAccelerationY() { return this.useAccelerationY; }
	
}