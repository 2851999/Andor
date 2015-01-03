/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

public interface GameLoopInterface {
	
	/* The method called when the loop has been created */
	public void create();
	/* The method called when the game loop has started */
	public void start();
	/* The method called when the game loop is updated */
	public void update();
	/* The method called when the game loop is rendered */
	public void render();
	/* The method called when the game loop is stopped */
	public void stop();
	/* The method called when the game loop is closing */
	public void close();
	
}