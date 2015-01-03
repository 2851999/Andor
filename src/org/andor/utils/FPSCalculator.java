/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

public class FPSCalculator {
	
	/* The different modes of calculating the FPS */
	public static final int MODE_FPS_PER_FRAME = 1;
	public static final int MODE_FPS_PER_SECOND = 2;
	public static final int MODE_FPS_OFF = 3;
	
	/* The current FPS mode */
	public int mode;
	
	/* The time of the last frame (Milliseconds) */
	public long lastFrameTime;
	
	/* The time of the last frame (Milliseconds) used for calculating the delta time */
	public long lastDeltaFrameTime;
	
	/* The current delta time */
	public long currentDeltaTime;
	
	/* The current FPS */
	public float currentFPS;
	
	/* The current FPS's count */
	public int fpsCount;
	
	/* The constructor */
	public FPSCalculator() {
		//Assign the variables
		this.mode = MODE_FPS_PER_SECOND;
		this.lastFrameTime = Time.getMiliseconds();
		this.lastDeltaFrameTime = Time.getMiliseconds();
		this.currentDeltaTime = 0;
		this.currentFPS = 0;
	}
	
	/* The method called to update this, should be called every frame */
	public void update() {
		//Make sure the FPS calculation is turned on
		if (this.mode != MODE_FPS_OFF) {
			//Check the mode
			if (this.mode == MODE_FPS_PER_FRAME) {
				//Recalculate the current delta
				this.currentDeltaTime = Time.getMiliseconds() - this.lastDeltaFrameTime;
				//Set the time this update occurred
				this.lastDeltaFrameTime = Time.getMiliseconds();
				//Make sure the current delta is not 0 (Prevents DivideByZero Exception)
				if (this.currentDeltaTime != 0)
					//Calculate the current FPS
					this.currentFPS = 1000 / this.currentDeltaTime;
			} else if (this.mode == MODE_FPS_PER_SECOND) {
				//Recalculate the current delta
				this.currentDeltaTime = Time.getMiliseconds() - this.lastDeltaFrameTime;
				//Set the time this update occurred
				this.lastDeltaFrameTime = Time.getMiliseconds();
				//Add 1 to the current FPS count
				this.fpsCount++;
				//Check to see whether the require amount of time has passed
				if ((Time.getMiliseconds() - this.lastFrameTime) >= 1000) {
					//Set the time this update occurred
					this.lastFrameTime = Time.getMiliseconds();
					//Set the current FPS
					this.currentFPS = fpsCount;
					//Reset the FPS count
					this.fpsCount = 0;
				}
			}
		}
	}
	
	/* The method used to reset this */
	public void reset() {
		this.lastFrameTime = Time.getMiliseconds();
		this.lastDeltaFrameTime = Time.getMiliseconds();
		this.currentDeltaTime = 0;
		this.currentFPS = 0;
	}
	
}