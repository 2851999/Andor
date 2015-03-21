/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

public class ColourAnimation2D extends Animation2D {
	
	/* The colours */
	public Colour[] colours;
	
	/* The constructor */
	public ColourAnimation2D(RenderableObject2D entity, Colour[] colours, long timeBetweenFrame) {
		super(entity, timeBetweenFrame);
		//Assign the variables
		this.colours = colours;
	}
	
	/* The constructor */
	public ColourAnimation2D(RenderableObject2D entity, Colour[] colours, long timeBetweenFrame, boolean repeat) {
		super(entity, timeBetweenFrame, repeat);
		//Assign the variables
		this.colours = colours;
	}
	
	/* The method used to update this animation */
	public void updateAnimation() {
		//Set the image
		this.entity.setColour(this.colours[this.currentFrame]);
	}
	
	/* The method used to reset this animation */
	public void resetAnimation() {
		this.entity.setColour(this.colours[this.currentFrame]);
	}
	
	/* The method used to determine whether the animation has finished */
	public boolean hasFinished() {
		return this.currentFrame == this.colours.length;
	}
	
	/* The set/get methods */
	public void setColours(Colour[] colours) { this.colours = colours; }
	public Colour[] getColours() { return this.colours; }
	
}