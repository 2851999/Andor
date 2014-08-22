/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.android;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.Image;
import org.andor.core.Vector2D;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class ImageSetAndroid {
	
	/* The loaded images */
	public List<Bitmap> loadedImages;
	
	/* The images */
	public List<Image> images;
	
	/* The constructor */
	public ImageSetAndroid() {
		//Assign the variables
		this.loadedImages = new ArrayList<Bitmap>();
		this.images = new ArrayList<Image>();
	}
	
	/* The method used to join the images and calculate the
	 * texture coordinates for each image */
	public Image joinImages() {
		//Get the total size of the images
		Vector2D totalSize = getTotalSize();
		//Create a bitmap
		Bitmap bitmap = Bitmap.createBitmap((int) totalSize.x, (int) totalSize.y, Bitmap.Config.ARGB_8888);
		//Create the canvas object
		Canvas canvas = new Canvas(bitmap);
		//The current x position
		float x = 0;
		//Go through each image
		for (int a = 0; a < this.loadedImages.size(); a++) {
			//Draw the image
			canvas.drawBitmap(this.loadedImages.get(a), (int) x, 0, null);
			//Add onto the texture coordinate values
			float top = (0f) / bitmap.getHeight();
			float bottom = ((float) this.loadedImages.get(a).getHeight()) / bitmap.getHeight();
			float left = (x / bitmap.getWidth());
			float right = (x + this.loadedImages.get(a).getWidth()) / bitmap.getWidth();
			//Set the equivalent image's texture coordinates
			this.images.get(a).top = top;
			this.images.get(a).bottom = bottom;
			this.images.get(a).left = left;
			this.images.get(a).right = right;
			//Add onto the x position
			x += this.loadedImages.get(a).getWidth();
		}
		//Create the new image
		Image image = new Image(bitmap);
		//Return the image
		return image;
	}
	
	/* The method used to load an image */
	public Image loadImage(String filePath, boolean external) {
		//Load the image
		Bitmap bitmap = ImageLoaderAndroid.loadBitmap(filePath, external);
		//Add the image to the list of loaded images
		loadedImages.add(bitmap);
		//Create the image instance
		Image image = new Image(bitmap.getWidth(), bitmap.getHeight());
		//Add the image to the list of images
		this.images.add(image);
		//Return the image
		return image;
	}
	
	/* The method used to get the total width and height of all of the images */
	public Vector2D getTotalSize() {
		//The total width
		float totalWidth = 0;
		float totalHeight = 0;
		//Go through all of the images
		for (int a = 0; a < this.images.size(); a++) {
			//Add onto the total width
			totalWidth += this.images.get(a).getWidth();
			//Get the current image's height 
			float height = this.images.get(a).getHeight();
			//Check the total height against the current image's height
			if (height > totalHeight)
				//Set the total height
				totalHeight = height;
		}
		//Return the values in a Vector2D
		return new Vector2D(totalWidth, totalHeight);
	}
	
}