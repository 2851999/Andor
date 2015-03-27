/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

public class ImageAnimation2D extends Animation2D {
	
	/* The images */
	private Image[] images;
	
	/* The constructor */
	public ImageAnimation2D(RenderableObject2D entity, Image[] images, long timeBetweenFrame) {
		super(entity, timeBetweenFrame);
		//Assign the variables
		this.images = images;
	}
	
	/* The constructor */
	public ImageAnimation2D(RenderableObject2D entity, Image[] images, long timeBetweenFrame, boolean repeat) {
		super(entity, timeBetweenFrame, repeat);
		//Assign the variables
		this.images = images;
	}
	
	/* The method used to update this animation */
	public void updateAnimation() {
		//Set the image
		this.getEntity().setTexture(this.images[this.getCurrentFrame()]);
	}
	
	/* The method used to reset this animation */
	public void resetAnimation() {
		this.getEntity().setTexture(this.images[this.getCurrentFrame()]);
	}
	
	/* The method used to determine whether the animation has finished */
	public boolean hasFinished() {
		return this.getCurrentFrame() == this.images.length;
	}
	
	/* The set/get methods */
	public void setImages(Image[] images) { this.images = images; }
	public Image[] getImages() { return this.images; }
	
}