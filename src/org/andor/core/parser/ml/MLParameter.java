/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.parser.ml;

public class MLParameter {
	
	/* The name of this parameter */
	private String name;
	
	/* The value of this parameter */
	private String value;
	
	/* The constructor */
	public MLParameter(String name, String value) {
		//Assign the variables
		this.name = name;
		this.value = value;
	}
	
	/* The 'set' and 'get' */
	public void setValue(String value) { this.value = value; }
	public String getName() { return this.name; }
	public String getValue() { return this.value; }
	
}