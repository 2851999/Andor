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
	
	/* The 'set' and 'get' */
	public void setVersion(String version) { this.version = version; }
	public String getVersion() { return this.version; }
	
}