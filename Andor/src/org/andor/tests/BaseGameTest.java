/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.tests;

import org.andor.core.BaseGame;

public class BaseGameTest extends BaseGame {
	
	/* The constructor */
	public BaseGameTest() {
		
	}
	
	/* The method called when the loop has been created */
	public void create() {
		System.out.println("GameLoop Created");
	}
	
	/* The method called when the game loop has started */
	public void start() {
		System.out.println("GameLoop Started");
	}
	
	/* The method called when the game loop is updated */
	public void update() {
		System.out.println("GameLoop Updated");
	}
	
	/* The method called when the game loop is rendered */
	public void render() {
		System.out.println("GameLoop Rendered");
	}
	
	/* The method called when the game loop is stopped */
	public void stop() {
		System.out.println("GameLoop Stopped");
	}
	
	/* The method called when the game loop is closing */
	public void close() {
		System.out.println("GameLoop Closing");
	}
	
	/* The main method */
	public static void main(String[] args) {
		//Create a new instance of this test
		new BaseGameTest();
	}
	
}