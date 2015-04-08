/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils.shader;

public class Uniform {
	
	/* The name of this uniform */
	private String name;
	
	/* The constructor */
	public Uniform(String name) {
		//Assign the variables
		this.name = name;
	}
	
	/* The getters and setters */
	public String getName() { return this.name; }
	
}