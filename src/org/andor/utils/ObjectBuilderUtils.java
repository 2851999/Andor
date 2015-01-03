/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/


package org.andor.utils;

import org.andor.core.Colour;

public class ObjectBuilderUtils {
	
	/* The static method used to create the colour array for each vertices given one colour,
	 * and the number of vertices */
	public static float[] createColourArray(int numberOfVertices, Colour colour) {
		//Get the colours float array
		float[] currentColourArray = colour.getValuesRGBA();
		//Create the float array with the right amount of values
		float[] colourArray = new float[numberOfVertices * 4];
		//Go through the colour array
		for (int a = 0; a < colourArray.length; a += 4) {
			//Set the current colour values
			colourArray[a] = currentColourArray[0];
			colourArray[a + 1] = currentColourArray[1];
			colourArray[a + 2] = currentColourArray[2];
			colourArray[a + 3] = currentColourArray[3];
		}
		//Return the colour array
		return colourArray;
	}
	
	/* The static method used to create the colour array for each vertices given one colour,
	 * and the number of vertices */
	public static float[] createColourArray(int numberOfVertices, Colour[] colours) {
		//The current colour
		int currentColour = 0;
		//Create the float array with the right amount of values
		float[] colourArray = new float[numberOfVertices * 4];
		//Go through the colour array
		for (int a = 0; a < colourArray.length; a += 4) {
			//Get the current colour
			float[] currentColourArray = colours[currentColour].getValuesRGBA();
			//Set the current colour values
			colourArray[a] = currentColourArray[0];
			colourArray[a + 1] = currentColourArray[1];
			colourArray[a + 2] = currentColourArray[2];
			colourArray[a + 3] = currentColourArray[3];
			//Check to see whether there is another colour
			if (currentColour >= colours.length - 1)
				//Reset the current colour
				currentColour = 0;
			else
				//Increment the current colour
				currentColour++;
		}
		//Return the colour array
		return colourArray;
	}
	
}
