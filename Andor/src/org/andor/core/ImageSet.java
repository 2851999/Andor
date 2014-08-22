/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.andor.core.android.ImageSetAndroid;

public class ImageSet {
	
	/* The PC image set */
	public ImageSetPC imageSetPC;
	
	/* The Android image set */
	public ImageSetAndroid imageSetAndroid;
	
	/* The constructor */
	public ImageSet() {
		//Check to see whether Andor is currently running on Android
		if (! Settings.AndroidMode)
			//Create the instance
			this.imageSetPC = new ImageSetPC();
		else
			this.imageSetAndroid = new ImageSetAndroid();
	}
	
	/* The method used to join the images and calculate the
	 * texture coordinates for each image */
	public Image joinImages() {
		//Check to see whether Andor is currently running on Android
		if (! Settings.AndroidMode)
			//Return the image
			return this.imageSetPC.joinImages();
		else
			return this.imageSetAndroid.joinImages();
	}
	
	/* The method used to load an image */
	public Image loadImage(String filePath, boolean external) {
		//Check to see whether Andor is currently running on Android
		if (! Settings.AndroidMode)
			//Return the image
			return this.imageSetPC.loadImage(filePath, external);
		else
			return this.imageSetAndroid.loadImage(filePath, external);
	}
	
	/* The method used to get the total width and height of all of the images */
	public Vector2D getTotalSize() {
		//Check to see whether Andor is currently running on Android
		if (! Settings.AndroidMode)
			//Return the total size
			return this.imageSetPC.getTotalSize();
		else
			return this.imageSetAndroid.getTotalSize();
	}
	
}