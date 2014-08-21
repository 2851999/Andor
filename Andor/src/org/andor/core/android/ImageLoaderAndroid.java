/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.android;

import java.io.IOException;

import org.andor.core.Image;
import org.andor.core.logger.Logger;

import android.graphics.BitmapFactory;

public class ImageLoaderAndroid {
	
	/* The static method used to load an image given the file path, and
	 * the external variables */
	public static Image loadImage(String filePath, boolean external) {
		//Try and catch any errors
		try {
			//Check the external value
			if (external) {
				return null;
			} else {
				//Load the image assuming it is in the 'assets' folder
				return new Image(BitmapFactory.decodeStream(BaseActivity.instance.getAssets().open(filePath)));
			}
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - ImageLoader loadImage()", "An exception has occurred when loading the file: " + filePath);
			e.printStackTrace();
			//Return null
			return null;
		}
	}
	
}