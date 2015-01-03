/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.andor.utils.Timer;

public class Animation2D {
	
	/* The 2D image entity */
	public ImageEntity2D entity;
	
	/* The images */
	public Image[] images;
	
	/* The time between each frame of the animation */
	public long timeBetweenFrame;
	
	/* The timer */
	public Timer timer;
	
	/* The current frame */
	public int currentFrame;
	
	/* Is the animation running */
	public boolean running;
	
	/* The boolean that states whether this animation should repeat */
	public boolean repeat;
	
	/* The constructor */
	public Animation2D(ImageEntity2D entity, Image[] images, long timeBetweenFrame) {
		//Assign the variables
		this.entity = entity;
		this.images = images;
		this.timeBetweenFrame = timeBetweenFrame;
		this.repeat = false;
		this.timer = new Timer();
		//Reset the animation
		this.reset();
	}
	
	/* The constructor */
	public Animation2D(ImageEntity2D entity, Image[] images, long timeBetweenFrame, boolean repeat) {
		//Assign the variables
		this.entity = entity;
		this.images = images;
		this.timeBetweenFrame = timeBetweenFrame;
		this.repeat = repeat;
		this.timer = new Timer();
		//Reset the animation
		this.reset();
	}
	
	/* The method used to update this animation */
	public void update() {
		//Make sure this animation is running
		if (this.running) {
			//Check the timer
			if (this.timer.hasTimePassed(this.timeBetweenFrame)) {
				//Increment the current frame
				this.currentFrame++;
				//Check the current frame
				if (this.currentFrame == this.images.length) {
					//Check to see whether this animation should repeat
					if (this.repeat) {
						//Reset this animation
						this.reset();
						//Start this animation
						this.start();
					} else {
						//Stop this animation
						this.stop();
					}
				} else {
					//Set the image
					this.entity.setImage(this.images[this.currentFrame]);
					//Restart the timer
					this.timer.restart();
				}
			}
		}
	}
	
	/* The method used to start this animation */
	public void start() {
		//Set running to true
		this.running = true;
		//Start the timer
		this.timer.start();
	}
	
	/* The method used to reset this animation */
	public void reset() {
		//Reset the variables
		this.running = false;
		this.currentFrame = 0;
		this.timer.reset();
		this.entity.setImage(images[this.currentFrame]);
	}
	
	/* The method used to pause this animation */
	public void pause() {
		//Pause the timer
		this.timer.pause();
	}
	
	/* The method used to resume this animation */
	public void resume() {
		//Resume the timer
		this.timer.resume();
	}
	
	/* The method used to restart this animation */
	public void restart() {
		this.stop();
		this.reset();
		this.start();
	}
	
	/* The method used to stop this animation */
	public void stop() {
		//Set running to false
		this.running = false;
		//Stop the timer
		this.timer.stop();
	}
	
	/* The set/get methods */
	public void setImages(Image[] images) { this.images = images; }
	public void setTimeBetweenFrame(long timeBetweenFrame) { this.timeBetweenFrame = timeBetweenFrame; }
	public Image[] getImages() { return this.images; }
	public long getTimeBetweenFrame() { return this.timeBetweenFrame; }
	
}