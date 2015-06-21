/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils;

import java.util.List;

public class ParserUtils {
	
	/* The static method used to remove any whitespace from the beginning and end of a line */
	public static String removeWhitespace(String line) {
		//Remove the whitespace
		while (line.startsWith(" ") || line.startsWith("\t"))
			line = line.substring(1);
		while (line.endsWith(" ") || line.endsWith("\t"))
			line = line.substring(0, line.length() - 1);
		//Return the line
		return line;
	}
	
	/* The static method used to remove any whitespace from the beginning and end of parameters */
	public static void removeWhitespace(String[] parameters) {
		//Remove the whitespace
		for (int a = 0; a < parameters.length; a++)
			//Clean up the current line
			parameters[a] = removeWhitespace(parameters[a]);
	}
	
	/* The static method for calculating a string */
	public static String getString(String s) {
		//Return the string
		return s.substring(1, s.length() - 1);
	}
	
	/* The static method used to clean up some code */
	public static String[] cleanUp(String[] code) {
		//Convert the array to a list
		List<String> list = ArrayUtils.toStringList(code);
		//Go through each line
		for (int a= 0; a < list.size(); a++) {
			//Clean up the current line
			list.set(a, removeWhitespace(list.get(a)));
			//Check the current line to make sure it isn't empty
			if (list.get(a).equals(""))
				//Remove the current line
				list.remove(a);
		}
		//Return the cleaned up code
		return ArrayUtils.toStringArray(list);
	}
	
}