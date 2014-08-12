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
		//Clear some of the list's
		this.allFields.clear();
		this.fieldNames.clear();
		this.fieldValues.clear();
		//Start the scan
		this.scan("", object);
	}
	
	/* The method used to scan an object for all the fields and add them to the list */
	public void scan(String currentObject, Object object) {
		//Try and catch any errors
		try {
			//Get the object's class
			Class<?> c = object.getClass();
			//Get all of the fields in this class
			Field[] fields = c.getDeclaredFields();
			//Go through each field
			for (int a = 0; a < fields.length; a++) {
				//Make sure the field should be counted
				if (! FieldUtils.isPrivate(fields[a]) && ! FieldUtils.isPublicStaticFinal(fields[a])) {
					//Check the current field
					if (fields[a].get(object) != null && this.isOtherClass(fields[a].get(object).getClass().getName())) {
						//Scan the current object
						if (currentObject.equals(""))
							this.scan(fields[a].getName(), fields[a].get(object));
						else
							this.scan(currentObject + "." + fields[a].getName(), fields[a].get(object));
					} else {
						//Add the field
						this.allFields.add(fields[a]);
						if (! currentObject.equals(""))
							this.fieldNames.add(currentObject + "." + fields[a].getName());
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
	
	/* The method used to add a class to also scan */
	public void addClass(String className) {
		//Add the name
		this.otherClasses.add(className);
	}
	
	/* The method used to set all of the variables's values */
	public void set(Object object) {
		//Try and catch any errors
		try {
			//Go through the field names
			for (int a = 0; a < this.fieldNames.size(); a++) {
				//Get the current field's name
				String current = this.fieldNames.get(a);
				//The name of the current field
				String currentField = current;
				//The object
				Object o = object;
				//Check the field name
				if (current.contains(".")) {
					//Set the class
					o = this.getClassFromName(object, current);
					//Set the current field
					String[] split = current.split("\\.");
					currentField = split[split.length - 1];
				}
				//Get the current field from the current class
				Field f = o.getClass().getDeclaredField(currentField);
				//The fields type
				String fieldType = f.getType().getTypeName();
				//The current value
				String currentValue = "" + this.fieldValues.get(a);
				//The value
				Object value = null;
				//Check the fields type
				if (fieldType.equals("java.lang.String"))
					value = currentValue;
				else if (fieldType.equals("java.lang.Integer"))
					value = Integer.parseInt(currentValue);
				else if (fieldType.equals("java.lang.Long"))
					value = Long.parseLong(currentValue);
				else if (fieldType.equals("java.lang.Float"))
					value = Float.parseFloat(currentValue);
				else if (fieldType.equals("java.lang.Double"))
					value = Double.parseDouble(currentValue);
				else if (fieldType.equals("java.lang.Boolean"))
					value = Boolean.parseBoolean(currentValue);
				else
					value = null;
				if (value == null) {
					//Don't do anything
				} else {
					//Set the current field's value
					f.set(o, value);
				}
			}
		} catch (IllegalArgumentException e) {
			//Log an error
			Logger.log("Andor - FieldScanner set()", "An exception has occurred when setting the values in the object: " + object, Log.ERROR);
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			//Log an error
			Logger.log("Andor - FieldScanner set()", "An exception has occurred when setting the values in the object: " + object, Log.ERROR);
			e.printStackTrace();
		} catch (SecurityException e) {
			//Log an error
			Logger.log("Andor - FieldScanner set()", "An exception has occurred when setting the values in the object: " + object, Log.ERROR);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			//Log an error
			Logger.log("Andor - FieldScanner set()", "An exception has occurred when setting the values in the object: " + object, Log.ERROR);
			e.printStackTrace();
		}
	}
	
	/* The method used to get a class from an object given the route to get to it */
	public Object getClassFromName(Object object, String route) {
		//The object and class
		Object o = object;
		Class<?> c = object.getClass();
		//Try and catch any errors
		try {
			//Split up the route into its separate parts
			String[] split = route.split("\\.");
			//Go through the split
			for (int a = 0; a < split.length - 1; a++) {
				//Assign the current object and class
				o = (c.getDeclaredField(split[a])).get(o);
				c = o.getClass();
			}
		} catch (IllegalArgumentException e) {
			//Log an error
			Logger.log("Andor - FieldScanner getClassFromName()", "An exception has occurred when getting the class from the route: " + route, Log.ERROR);
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			//Log an error
			Logger.log("Andor - FieldScanner getClassFromName()", "An exception has occurred when getting the class from the route: " + route, Log.ERROR);
			e.printStackTrace();
		} catch (SecurityException e) {
			//Log an error
			Logger.log("Andor - FieldScanner getClassFromName()", "An exception has occurred when getting the class from the route: " + route, Log.ERROR);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			//Log an error
			Logger.log("Andor - FieldScanner getClassFromName()", "An exception has occurred when getting the class from the route: " + route, Log.ERROR);
			e.printStackTrace();
		} 
		//Return the class
		return o;
	}
	
	/* The methods used to set/get the field names/values */
	public void setFieldNames(List<String> fieldNames) { this.fieldNames = fieldNames; }
	public void setFieldValues(List<Object> fieldValues) { this.fieldValues = fieldValues; }
	public List<String> getFieldNames() { return this.fieldNames; }
	public List<Object> getFieldValues() { return this.fieldValues; }
	
}