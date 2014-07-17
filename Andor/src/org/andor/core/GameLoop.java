/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.andor.utils.FPSCalculator;

public class GameLoop implements GameLoopInterface {
	
	/* The FPS calculator */
	public FPSCalculator fpsCalculator;
	
	/* The default constructor */
	public GameLoop() {
		//Create the FPS calculator
		this.fpsCalculator = new FPSCalculator();
		//Create the window
		Window.create();
		//Call the create method
		this.create();
		//Start the game loop
		this.startGameLoop();
	}
	
	/* The method used to start the game loop */
	private void startGameLoop() {
		//Call the start method
		this.start();
		//Keep going until the window closes
		while (! Window.shouldClose()) {
			//Update the FPS calculator
			this.fpsCalculator.update();
			//Call the update method
			this.update();
			//Call the render method
			this.render();
			//Update the display
			Window.updateDisplay();
		}
		//Call the stop method
		this.stop();
		//Call the close method
		this.close();
	}
	
	/* The method called when the loop has been created */
	public void create() {
		
	}
	
	/* The method called when the game loop has started */
	public void start() {
		
	}
	
	/* The method called when the game loop is updated */
	public void update() {
		
	}
	
	/* The method called when the game loop is rendered */
	public void render() {
		
	}
	
	/* The method called when the game loop is stopped */
	public void stop() {
		
	}
	
	/* The method called when the game loop is closing */
	public void close() {
		
	}
	
	/* The method used to get the current delta */
	public long getDelta() { return this.fpsCalculator.currentDeltaTime; }
	
	/* The method used to get the current FPS */
	public float getFPS() { return this.fpsCalculator.currentFPS; }
	
}