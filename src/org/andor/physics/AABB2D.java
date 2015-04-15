/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.Vector2D;

public class AABB2D {
	
	/* The maximum and minimum extents */
	private Vector2D min;
	private Vector2D max;
	
	/* The constructor */
	public AABB2D(Vector2D min, Vector2D max) {
		//Assign the variables
		this.min = min;
		this.max = max;
	}
	
	/* The getters and setters */
	public Vector2D getMinimum() { return this.min; }
	public Vector2D getMaximum() { return this.max; }
	
}