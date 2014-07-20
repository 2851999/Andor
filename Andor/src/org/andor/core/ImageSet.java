/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ImageSet {
	
	/* The loaded images */
	public List<BufferedImage> loadedImages;
	
	/* The images */
	public List<Image> images;
	
	/* The constructor */
	public ImageSet() {
		//Assign the variables
		this.loadedImages = new ArrayList<BufferedImage>();
		this.images = new ArrayList<Image>();
	}
	
	/* The method used to join the images and calculate the
	 * texture coordinates for each image */
	public Image joinImages() {
		//Get the total size of the images
		Vector2D totalSize = getTotalSize();
		//Create a buffered image
		BufferedImage bufferedImage = new BufferedImage((int) totalSize.x, (int) totalSize.y, BufferedImage.TYPE_4BYTE_ABGR);
		//Create the graphics object
		Graphics2D g2d = bufferedImage.createGraphics();
		//The current x position
		float x = 0;
		//Go through each image
		for (int a = 0; a < this.loadedImages.size(); a++) {
			//Draw the image
			g2d.drawImage(this.loadedImages.get(a), (int) x, 0, null);
			//Add onto the texture coordinate values
			float top = (0f) / bufferedImage.getHeight();
			float bottom = ((float) this.loadedImages.get(a).getHeight()) / bufferedImage.getHeight();
			float left = (x / bufferedImage.getWidth());
			float right = (x + this.loadedImages.get(a).getWidth()) / bufferedImage.getWidth();
			//Set the equivalent image's texture coordinates
			this.images.get(a).top = top;
			this.images.get(a).bottom = bottom;
			this.images.get(a).left = left;
			this.images.get(a).right = right;
			//Add onto the x position
			x += this.loadedImages.get(a).getWidth();
		}
		//Create the new image
		Image image = ImageLoader.loadImage(bufferedImage);
		//Return the image
		return image;
	}
	
	/* The method used to load an image */
	public Image loadImage(String filePath, boolean external) {
		//Load the image
		BufferedImage bufferedImage = ImageLoader.loadBufferedImage(filePath, external);
		//Add the image to the list of loaded images
		loadedImages.add(bufferedImage);
		//Create the image instance
		Image image = ImageLoader.loadImage(bufferedImage);
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