/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import org.andor.core.android.ImageLoaderAndroid;

public class ImageLoader {
	
	/* The static method used to load an image given the file path (Assumes
	 * external should be false) */
	public static Image loadImage(String filePath) {
		return loadImage(filePath, false);
	}
	
	/* The static method used to load an image given the file path, and
	 * the external variables */
	public static Image loadImage(String filePath, boolean external) {
		//Check to see whether Andor is currently running on Android
		if (! Settings.AndroidMode)
			//Load the image
			return ImageLoaderPC.loadImage(filePath, external);
		else
			return ImageLoaderAndroid.loadImage(filePath, external);
	}
	
	/* The static method used to load an image given the file path (Assumes
	 * external should be false) */
	public static Image loadImage(String filePath, TextureParameters parameters) {
		return loadImage(filePath, false, parameters);
	}
	
	/* The static method used to load an image given the file path, and
	 * the external variables */
	public static Image loadImage(String filePath, boolean external, TextureParameters parameters) {
		//Check to see whether Andor is currently running on Android
		if (! Settings.AndroidMode)
			//Load the image
			return ImageLoaderPC.loadImage(filePath, external, parameters);
		else
			return ImageLoaderAndroid.loadImage(filePath, external, parameters);
	}
	
}