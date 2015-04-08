/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils.shader;

import org.andor.core.Vector3D;

public class UniformVector3D extends Uniform {
	
	/* The value */
	private Vector3D value;
	
	/* The constructor */
	public UniformVector3D(String name) {
		super(name);
		//Assign the values
		this.value = new Vector3D();
	}
	
	/* The constructor */
	public UniformVector3D(String name, Vector3D value) {
		super(name);
		//Assign the values
		this.value = value;
	}
	
	/* The getter and setters */
	public void setValue(Vector3D value) { this.value = value; }
	public Vector3D getValue() { return this.value; }
	
}