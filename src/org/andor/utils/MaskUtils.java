/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils;

public class MaskUtils {
	
	/* The static method used to mask a string given the string and the mask */
	public static String mask(String string, String mask) {
		//The masked string
		String maskedString = "";
		//Get the length of the string
		int stringLength = string.length();
		//Iterate the same number as there are letters in the string
		for (int a = 0; a < stringLength; a++)
			//Add on the mask to the masked string
			maskedString += mask;
		//Return the masked string
		return maskedString;
	}
	
}