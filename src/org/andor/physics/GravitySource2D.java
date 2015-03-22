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

public class GravitySource2D extends PhysicsObject2D {
	
	/* The constructor */
	public GravitySource2D(Object2D object) {
		super(object);
	}

	/* The method gravity to an object */
	public void apply(PhysicsObject2D object) {
		//How many pixels in a meter
		float pixelsInMeter = 200;
		//First step is to calculate then difference between the positions
		float dx = object.getPosition().x - this.getPosition().x;
		float dy = object.getPosition().y - this.getPosition().y;
		//Calculate the distance between the two masses
		float r = (float) Math.sqrt((dx * dx) + (dy * dy)) / pixelsInMeter;
		//Calculate the force of gravity
		float f = (float) (Physics.Constants.GRAVITATIONAL_CONSTANT * (float) ((long) (this.mass * object.mass) / (float) (r * r)));
		
		//Calculate the acceleration
		float a = f / object.mass;
		//Calculate the final values
		Vector2D fa = new Vector2D((dx / r) * -1, (dy / r) * -1);
		fa.multiply(a);
		//Add the acceleration to the velocity
		object.velocity.add(fa.multiplyNew(Physics.deltaUpdate));
	}
	
}