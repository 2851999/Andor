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

public class ArrayUtils {
	
	/* The static method to turn a string list into a string array */
	public static String[] toArray(List<String> list) {
		//Create the array
		String[] array = new String[list.size()];
		//Go through the new array
		for (int a = 0; a < array.length; a++)
			//Set the current line
			array[a] = list.get(a);
		//Return the array
		return array;
	}
	
	/* The static method to turn a string array into a string list */
	public static List<String> toList(String[] array) {
		//Create the list
		List<String> list = new ArrayList<String>();
		//Go through the array
		for (int a = 0; a < array.length; a++)
			//Add the current line to the list
			list.add(array[a]);
		//Return the list
		return list;
	}
	
}