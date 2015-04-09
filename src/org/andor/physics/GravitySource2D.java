/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.Vector2D;

public class GravitySource2D {
	
	/* The object this is connected to */
	public PhysicsObject2D object;
	
	/* The constructor */
	public GravitySource2D(PhysicsObject2D object) {
		//Assign the variables
		this.object = object;
	}

	/* The method gravity to an object */
	public void apply(PhysicsObject2D object) {
		//How many pixels in a meter
		float pixelsInMeter = 200;
		//First step is to calculate then difference between the positions
		float dx = object.getPosition().x - this.object.getPosition().x;
		float dy = object.getPosition().y - this.object.getPosition().y;
		//Calculate the distance between the two masses
		float r = (float) Math.sqrt((dx * dx) + (dy * dy)) / pixelsInMeter;
		//Calculate the force of gravity
		float f = (float) (Physics.Constants.GRAVITATIONAL_CONSTANT * (float) ((long) (this.object.getMass() * object.getMass()) / (float) (r * r)));
		
		//Calculate the acceleration
		float a = f / object.mass;
		//Calculate the final values
		Vector2D fa = new Vector2D((dx / r) * -1, (dy / r) * -1);
		fa.multiply(a);
		//Add the acceleration to the velocity
		object.velocity.add(fa.multiplyNew(Physics.deltaUpdate));
	}
	
}