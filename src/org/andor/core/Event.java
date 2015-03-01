/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

public class Event {
	
	/* The name of this event */
	public String name;
	
	/* The description of this event */
	public String description;
	
	/* The constructor */
	public Event(String name, String description) {
		//Assign the variables
		this.name = name;
		this.description = description;
	}
	
	/* The methods used to return the name and description of this event */
	public String getName() { return this.name; }
	public String getDescription() { return this.description; }
	
	
}