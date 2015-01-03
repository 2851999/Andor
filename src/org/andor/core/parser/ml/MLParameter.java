/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.parser.ml;

import org.andor.core.interpreter.ml.MLInterpreter;

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
	
	/* The methods used to get this value in a variety of forms */
	public int getIntegerValue() {
		//Make sure the value is an integer
		try {
			//Try an parse the value
			return Integer.parseInt(this.getValue());
		} catch (NumberFormatException e) {
			//Log an error
			MLInterpreter.log("Parameter with the name " + this.name + " has a value of " + this.value + " which is not an integer");
			//Return 0
			return 0;
		}
	}
	
	public float getFloatValue() {
		//Make sure the value is a float
		try {
			//Try an parse the value
			return Float.parseFloat(this.getValue());
		} catch (NumberFormatException e) {
			//Log an error
			MLInterpreter.log("Parameter with the name " + this.name + " has a value of " + this.value + " which is not a float");
			//Return 0
			return 0;
		}
	}
	
	public double getDoubleValue() {
		//Make sure the value is a double
		try {
			//Try an parse the value
			return Double.parseDouble(this.getValue());
		} catch (NumberFormatException e) {
			//Log an error
			MLInterpreter.log("Parameter with the name " + this.name + " has a value of " + this.value + " which is not a double");
			//Return 0
			return 0;
		}
	}
	
}