/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.tests;

import org.andor.core.BaseGame;
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
	
	/* The main method */
	public static void main(String[] args) {
		//Create a new instance of this test
		new BaseGameTest();
	}
	
}