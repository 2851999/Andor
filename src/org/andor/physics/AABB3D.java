/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.physics;

import org.andor.core.Vector3D;

public class AABB3D {
	
	/* The maximum and minimum extents */
	private Vector3D min;
	private Vector3D max;
	
	/* The constructor */
	public AABB3D(Vector3D min, Vector3D max) {
		//Assign the variables
		this.min = min;
		this.max = max;
	}
	
	/* The getters and setters */
	public Vector3D getMinimum() { return this.min; }
	public Vector3D getMaximum() { return this.max; }
	
}