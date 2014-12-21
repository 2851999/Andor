/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.parser.utils;

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
	
}