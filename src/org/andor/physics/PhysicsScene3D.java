/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.Vector3D;
import org.andor.utils.MathUtils;

public class PhysicsScene3D {
	
	/* The list of physics objects */
	public List<PhysicsObject3D> objects;
	
	/* The constructor */
	public PhysicsScene3D() {
		//Assign the variables
		this.objects = new ArrayList<PhysicsObject3D>();
	}
	
	/* The method used to add an object to this scene */
	public void add(PhysicsObject3D object) {
		//Add the object
		this.objects.add(object);
	}
	
	/* The method used to detect collisions and resolve them */
	public void checkCollisions() {
		//Go through the objects
		for (int a = 0; a < this.objects.size(); a++) {
			//Get the object
			PhysicsObject3D objectA = objects.get(a);
			//Make sure this object has a collider
			if (objectA.getCollider() != null) {
				for (int b = a + 1; b < this.objects.size(); b++) {
					//Get the object
					PhysicsObject3D objectB = objects.get(b);
					//Make sure this object has a collider
					if (objectB.getCollider() != null) {
						//Get the collision data
						CollisionData3D data = objectA.getCollider().checkCollision(objectB.getCollider());
						//Check to see whether there is a collision
						if (data.doesIntersect()) {
//							Vector2D direction = data.getDirection().normalised();
//							Vector2D other = direction.reflect(objectA.getVelocity().normalised());
//							objectA.velocity = objectA.getVelocity().reflect(other);
//							objectB.velocity = objectB.getVelocity().reflect(direction);
							applyImpulse(objectA, objectB, data);
						}
					}
				}
			}
		}
	}
	
	public void applyImpulse(PhysicsObject3D objectA, PhysicsObject3D objectB, CollisionData3D data) {
		Vector3D changeInVelocity = objectB.getVelocity().subtractNew(objectA.getVelocity());
		float contactVelocity = changeInVelocity.dot(data.getDirection().normalised().multiplyNew(-1));
		if (contactVelocity > 0)
			return;
		
		float e = MathUtils.min(objectA.getRestitution(), objectB.getRestitution());
		float j = -(1.0f + e) * contactVelocity;
		j /= objectA.getInverseMass() + objectB.getInverseMass();
		Vector3D impulse = data.getDirection().normalised().multiplyNew(-1).multiplyNew(j);
		objectA.velocity.subtract(impulse.multiplyNew(objectA.getInverseMass()));
		objectB.velocity.add(impulse.multiplyNew(objectB.getInverseMass()));
	}
	
}