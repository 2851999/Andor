/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.parser.ml;

import java.util.List;

public class MLObject {
	
	/* The type of object */
	public String type;
	
	/* The name of this object */
	public String name;
	
	/* The parameters in this object */
	public List<MLParameter> parameters;
	
	/* The other objects within this object */
	public List<MLObject> objects;
	
	/* The constructor */
	public MLObject(String type, String name, List<MLParameter> parameters) {
		//Assign the variables
		this.type = type;
		this.name = name;
		this.parameters = parameters;
	}
	
	/* The method used to get a parameter with a specific name */
	public MLParameter getParameter(String name) {
		//The parameter
		MLParameter parameter = null;
		//Go through each of the parameters
		for (int a = 0; a < this.parameters.size(); a++) {
			//Check the current parameter
			if (this.parameters.get(a).getName().endsWith(name)) {
				//Assign the parameter
				parameter = this.parameters.get(a);
				//Exit the loop
				break;
			}
		}
		//Make sure the parameter was found
		if (parameter == null)
			//Log a message
			ML.log("MLObject : Parameter with the name " + name + " was not found, returning null");
		//Return the parameter
		return parameter;
	}
	
	/* The 'get' methods */
	public String getType() { return this.type; }
	public String getName() { return this.name; }
	public List<MLParameter> getParameters() { return this.parameters; }
	public List<MLObject> getObjects() { return this.objects; }
	
}