/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import java.util.ArrayList;
import java.util.List;

public class PropertiesFile {
	
	/* The file name */
	public String path;
	
	/* Is the file in a folder */
	public boolean external;
	
	/* The text in the file */
	public List<String> fileText;
	
	/* The constructor */
	public PropertiesFile() {
		//Assign the values
		this.path = "";
		this.external = true;
		this.fileText = new ArrayList<String>();
	}
	
	/* The constructor with the path and external values given */
	public PropertiesFile(String path , boolean external) {
		//Assign the values
		this.path = path;
		this.external = external;
		this.fileText = new ArrayList<String>();
	}
	
	/* The method used to read the file assuming the path has been set */
	public void read() {
		//Assign the file text
		this.fileText = FileUtils.read(this.path , this.external);
	}
	
	/* The method used to read the file given its path */
	public void read(String path , boolean external) {
		//Assign the file text
		this.fileText = FileUtils.read(path , external);
	}
	
	
	/* The method used to save the file assuming the path has been set */
	public void write() {
		//Write the file
		FileUtils.write(this.path , ArrayUtils.toStringArray(this.fileText));
	}
	
	/* The method used to save the file given its path */
	public void write(String path) {
		//Write the file to the file path
		FileUtils.write(path , ArrayUtils.toStringArray(this.fileText));
	}
	
	/* The method used to add a property */
	public void addProperty(String propertyName , String propertyValue) {
		//Add the property to the file text
		this.fileText.add(propertyName + ": " + propertyValue);
	}
	
	/* The method used to get a property */
	public String getProperty(String propertyName) {
		//The property's value
		String propertyValue = "";
		//Set the property's value
		propertyValue = this.fileText.get(this.getPropertyLine(propertyName)).split(propertyName + ": ")[1];
		//Return the property's value
		return propertyValue;
	}
	
	/* The method used to get the line a property is on */
	public int getPropertyLine(String propertyName) {
		//The line the property is on
		int propertyLine = 0;
		//Loop through the file text
		for (int a = 0; a < this.fileText.size(); a++) {
			//Check if the property is on this line
			if (this.fileText.get(a).startsWith(propertyName + ": ")) {
				//Set the property line
				propertyLine = a;
				//Exit the loop
				break;
			}
		}
		//Return the line
		return propertyLine;
	}
	
	/* The method used to set a property */
	public void setProperty(String propertyName , String propertyValue) {
		//The property's line
		int propertyLine = this.getPropertyLine(propertyName);
		//Set the value
		this.fileText.set(propertyLine , propertyName + ": " + propertyValue);
	}
	
	/* The method to set the file path */
	public void setPath(String path) {
		//Set the file path
		this.path = path;
	}
	
}