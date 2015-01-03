/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.parser.ml;

import java.util.ArrayList;
import java.util.List;

public class MLTree {
	
	/* The version of this tree (ML Version) */
	private String version;
	
	/* The list of objects */
	private List<MLObject> objects;
	
	/* The constructor */
	public MLTree() {
		//Assign the variables
		this.version = "Unknown";
		this.objects = new ArrayList<MLObject>();
	}
	
	/* The method used to add an object */
	public void add(MLObject object) { this.objects.add(object); }
	
	/* The method used to get an object given its name */
	public MLObject get(String name) {
		//The object
		MLObject object = null;
		//Go through each object
		for (int a = 0; a < this.objects.size(); a++) {
			//Check the name of the current object
			if (this.objects.get(a).name.equals(name)) {
				//Set the object
				object = this.objects.get(a);
				//Exit the loop
				break;
			}
		}
		//Return the object
		return object;
	}
	
	/* The 'set' and 'get' methods */
	public void setVersion(String version) { this.version = version; }
	public String getVersion() { return this.version; }
	public List<MLObject> getObjects() { return this.objects; }
	
}