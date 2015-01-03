/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.tests;

import org.andor.core.BaseGame;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.MouseEvent;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.input.ScrollEvent;
import org.andor.core.logger.Logger;

public class BaseGameTest extends BaseGame {
	
	/* The constructor */
	public BaseGameTest() {
		
	}
	
	/* The method called when the loop has been created */
	public void create() {
		Logger.log("BaseGameTest", "GameLoop Created");
	}
	
	/* The method called when the game loop has started */
	public void start() {
		Logger.log("BaseGameTest", "GameLoop Started");
	}
	
	/* The method called when the game loop is updated */
	public void update() {
		Logger.log("BaseGameTest", "GameLoop Updated");
	}
	
	/* The method called when the game loop is rendered */
	public void render() {
		Logger.log("BaseGameTest", "GameLoop Rendered FPS: " + getFPS());
	}
	
	/* The method called when the game loop is stopped */
	public void stop() {
		Logger.log("BaseGameTest", "GameLoop Stopped");
	}
	
	/* The method called when the game loop is closing */
	public void close() {
		Logger.log("BaseGameTest", "GameLoop Closing");
	}
	
	/* The method called when a button on the mouse is pressed */
	public void onMousePressed(MouseEvent e) {
		Logger.log("BaseGameTest", "The mouse was pressed " + e.getButton());
	}
	
	/* The method called when a button on the mouse is released */
	public void onMouseReleased(MouseEvent e)  {
		Logger.log("BaseGameTest", "The mouse was released " + e.getButton());
	}
	
	/* The method called when a button on the mouse is clicked */
	public void onMouseClicked(MouseEvent e) {
		Logger.log("BaseGameTest", "The mouse was clicked " + e.getButton());
	}
	
	/* The method called when the mouse moves */
	public void onMouseMoved(MouseMotionEvent e) {
		Logger.log("BaseGameTest", "The mouse was moved (" + e.dx + "," + e.dy + ")");
	}
	
	/* The method called when a key on the keyboard is pressed */
	public void onKeyPressed(KeyboardEvent e) {
		Logger.log("BaseGameTest", "A key was pressed " + e.keyCharacter);
	}
	
	/* The method called when a key on the keyboard is released */
	public void onKeyReleased(KeyboardEvent e) {
		Logger.log("BaseGameTest", "A key was released " + e.keyCharacter);
	}
	
	/* The method called when a key on the keyboard is typed */
	public void onKeyTyped(KeyboardEvent e) {
		Logger.log("BaseGameTest", "A key was typed " + e.keyCharacter);
	}
	
	/* The method called when the mouse scrolls */
	public void onScroll(ScrollEvent e) {
		Logger.log("BaseGameTest", "The scroll wheel moved " + e.distance);
	}
	
	/* The main method */
	public static void main(String[] args) {
		//Create a new instance of this test
		new BaseGameTest();
	}
	
}