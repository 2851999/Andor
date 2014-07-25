/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.andor.core.input.Input;
import org.andor.core.input.InputListenerInterface;
import org.andor.core.input.InputManager;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.MouseEvent;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.input.ScrollEvent;
import org.andor.utils.FPSCalculator;

public class GameLoop implements GameLoopInterface, InputListenerInterface {
	
	/* The boolean that states whether a close is requested */
	private boolean closeRequested;
	
	/* The FPS calculator */
	public FPSCalculator fpsCalculator;
	
	/* The default constructor */
	public GameLoop() {
		//Create the FPS calculator
		this.fpsCalculator = new FPSCalculator();
		//Create the window
		Window.create();
		//Create the input
		InputManager.create();
		//Add this input listener
		Input.addListener(this);
		//Call the create method
		this.create();
		//Start the game loop
		this.startGameLoop();
	}
	
	/* The method used to start the game loop */
	private void startGameLoop() {
		//Set close requested to false
		this.closeRequested = false;
		//Call the start method
		this.start();
		//Keep going until the window closes
		while (! Window.shouldClose() && ! this.closeRequested) {
			//Update the FPS calculator
			this.fpsCalculator.update();
			//Check the input
			InputManager.checkInput();
			//Call the update method
			this.update();
			//Call the render method
			this.render();
			//Update the display
			Window.updateDisplay();
		}
		//Destroy the input
		InputManager.destroy();
		//Close the window
		Window.close();
		//Call the stop method
		this.stop();
		//Call the close method
		this.close();
	}
	
	/* The method called to request the program to close  */
	public void requestClose() {
		//Set close requested to true
		this.closeRequested = true;
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
	
	/* The method called when a button on the mouse is pressed */
	public void onMousePressed(MouseEvent e) {
		
	}
	
	/* The method called when a button on the mouse is released */
	public void onMouseReleased(MouseEvent e)  {
		
	}
	
	/* The method called when a button on the mouse is clicked */
	public void onMouseClicked(MouseEvent e) {
		
	}
	
	/* The method called when the mouse moves */
	public void onMouseMoved(MouseMotionEvent e) {
		
	}
	
	/* The method called when a key on the keyboard is pressed */
	public void onKeyPressed(KeyboardEvent e) {
		
	}
	
	/* The method called when a key on the keyboard is released */
	public void onKeyReleased(KeyboardEvent e) {
		
	}
	
	/* The method called when a key on the keyboard is typed */
	public void onKeyTyped(KeyboardEvent e) {
		
	}
	
	/* The method called when the mouse scrolls */
	public void onScroll(ScrollEvent e) {
		
	}
	
	/* The method used to get the current delta */
	public long getDelta() { return this.fpsCalculator.currentDeltaTime; }
	
	/* The method used to get the current FPS */
	public float getFPS() { return this.fpsCalculator.currentFPS; }
	
}