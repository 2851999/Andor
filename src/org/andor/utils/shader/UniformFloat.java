/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils.shader;

public class UniformFloat extends Uniform {
	
	/* The value */
	private float value;
	
	/* The constructor */
	public UniformFloat(String name) {
		super(name);
		//Assign the values
		this.value = 0;
	}
	
	/* The constructor */
	public UniformFloat(String name, float value) {
		super(name);
		//Assign the values
		this.value = value;
	}
	
	/* The getter and setters */
	public void setValue(float value) { this.value = value; }
	public float getValue() { return this.value; }
	
}