/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils.shader;

import org.andor.core.Vector2D;

public class UniformVector2D extends Uniform {
	
	/* The value */
	private Vector2D value;
	
	/* The constructor */
	public UniformVector2D(String name) {
		super(name);
		//Assign the values
		this.value = new Vector2D();
	}
	
	/* The constructor */
	public UniformVector2D(String name, Vector2D value) {
		super(name);
		//Assign the values
		this.value = value;
	}
	
	/* The getter and setters */
	public void setValue(Vector2D value) { this.value = value; }
	public Vector2D getValue() { return this.value; }
	
}