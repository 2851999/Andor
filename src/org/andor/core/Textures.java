/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import java.util.ArrayList;
import java.util.List;

public class Textures {
	
	/* The list of textures that have been created */
	public static List<Texture> allTextures = new ArrayList<Texture>();
	
	/* The static method used to release all of the images that have been created */
	public static void releaseAll() {
		//Go through each image
		for (int a = 0; a < allTextures.size(); a++)
			//Delete the current image
			allTextures.get(a).release();
	}
	
}