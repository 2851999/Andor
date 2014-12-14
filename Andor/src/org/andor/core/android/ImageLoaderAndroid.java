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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageLoaderAndroid {
	
	/* The static method used to load an image given the file path, and
	 * the external variables */
	public static Image loadImage(String filePath, boolean external) {
		//Return the image
		return new Image(loadBitmap(filePath, external));
	}
	
	/* The static method used to load a bitmap given the file path, and
	 * the external variables */
	public static Bitmap loadBitmap(String filePath, boolean external) {
		//Try and catch any errors
		try {
			//Check the external value
			if (external) {
				return null;
			} else {
				//Make sure the file path doesn't start with a forward slash
				if (filePath.startsWith("/"))
					//Remove the forward slash
					filePath = filePath.substring(1);
				//Load the image assuming it is in the 'assets' folder
				return BitmapFactory.decodeStream(BaseActivity.instance.getAssets().open(filePath));
			}
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - ImageLoader loadBitmap()", "An exception has occurred when loading the file: " + filePath);
			e.printStackTrace();
			//Return null
			return null;
		}
	}
	
}