/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.parser.ml;

import java.util.ArrayList;
import java.util.List;

public class MLObject {
	
	/* The type of object */
	public String type;
	
	/* The name of this object */
	public String name;
	
	/* The parameters in this object */
	private List<MLParameter> parameters;
	
	/* The other objects within this object */
	private List<MLObject> objects;
	
	/* The constructor */
	public MLObject(String type, String name) {
		//Assign the variables
		this.type = type;
		this.name = name;
		this.parameters = new ArrayList<MLParameter>();
		this.objects = new ArrayList<MLObject>();
	}
	
	/* The method used to add a parameter */
	public void add(MLParameter parameter) {
		//Add the parameter to the list
		this.parameters.add(parameter);
	}
	
	/* The method used to add an object */
	public void add(MLObject object) {
		//Add the object to the list
		this.objects.add(object);
	}
	
	/* The method that returns a boolean representing whether a specific parameter is present */
	public boolean doesParameterExist(String name) {
		//Go through each of the parameters
		for (int a = 0; a < this.parameters.size(); a++) {
			//Check the current parameter
			if (this.parameters.get(a).getName().endsWith(name))
				return true;
		}
		return false;
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
	public MLParameter getParameter(int i) { return this.parameters.get(i); }
	public List<MLParameter> getParameters() { return this.parameters; }
	public List<MLObject> getObjects() { return this.objects; }
	
}