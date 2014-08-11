/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

public class FieldScanner {
	
	/* The list of class names used to represent objects that should
	 * also have their fields listed */
	public List<String> otherClasses;
	
	/* The list of all of the fields that were found */
	public List<Field> allFields;
	
	/* The list of all of the field names */
	public List<String> fieldNames;
	
	/* The list of all of the field values */
	public List<Object> fieldValues;
	
	/* The constructor */
	public FieldScanner() {
		//Assign the variables
		this.otherClasses = new ArrayList<String>();
		this.allFields = new ArrayList<Field>();
		this.fieldNames = new ArrayList<String>();
		this.fieldValues = new ArrayList<Object>();
	}
	
	/* The method used to reset this scanner */
	public void reset() {
		this.otherClasses.clear();
		this.allFields.clear();
		this.fieldNames.clear();
		this.fieldValues.clear();
	}
	
	/* The method used to scan an object for all the fields and add them to the list */
	public void scan(Object object) {
		//Start the scan
		this.scan("", object);
	}
	
	/* The method used to scan an object for all the fields and add them to the list */
	public void scan(String currentObject, Object object) {
		//Clear some of the list's
		this.allFields.clear();
		this.fieldNames.clear();
		this.fieldValues.clear();
		//Try and catch any errors
		try {
			//Get the object's class
			Class<?> c = object.getClass();
			//Get all of the fields in this class
			Field[] fields = c.getDeclaredFields();
			//Go through each field
			for (int a = 0; a < fields.length; a++) {
				//Make sure the field should be counted
				if (! FieldUtils.isPrivate(fields[a])) {
					//Check the current field
					if (fields[a].get(object) != null && this.isOtherClass(fields[a].get(object).getClass().getName())) {
						//Scan the current object
						if (currentObject.equals(""))
							this.scan(currentObject + fields[a].getName(), fields[a].get(object));
						else
							this.scan(currentObject + "." + fields[a].getName(), fields[a].get(object));
					} else {
						//Add the field
						this.allFields.add(fields[a]);
						if (! currentObject.equals(""))
							this.fieldNames.add(currentObject + fields[a].getName());
						else
							this.fieldNames.add(currentObject + fields[a].getName());
						this.fieldValues.add(fields[a].get(object));
					}
				}
			}
		} catch (IllegalArgumentException e) {
			//Log an error
			Logger.log("Andor - FieldScanner scan()", "An exception has occurred when scanning the object: " + object, Log.ERROR);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			//Log an error
			Logger.log("Andor - FieldScanner scan()", "An exception has occurred when scanning the object: " + object, Log.ERROR);
			e.printStackTrace();
		}
	}
	
	/* The method that returns whether a class's name is inside the 'otherClasses' list */
	public boolean isOtherClass(String name) {
		//The value
		boolean value = false;
		//Go through the other classes
		for (int a = 0; a < this.otherClasses.size(); a++) {
			//Check the current class
			if (this.otherClasses.get(a).equals(name)) {
				//Assign the value
				value = true;
				//Exit this loop
				break;
			}
		}
		//Return the value
		return value;
	}
	
	/* The method that returns all of the variable's names */
	public List<String> getFieldNames() {
		return this.fieldNames;
	}
	
	/* The method that returns all of the variable's values */
	public List<Object> getFieldValues(Object object) {
		return this.fieldValues;
	}
	
	/* The method used to add a class to also scan */
	public void addClass(String className) {
		//Add the name
		this.otherClasses.add(className);
	}
	
}