/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.examples;

/*
 * Import all of the required classes
 */
import org.andor.core.BaseGame;
import org.andor.core.Settings;
import org.andor.utils.OpenGLUtils;

/*
 * This is a simple example to show how you can setup a basic game loop
 * and render to the screen
 */

/*
 * This creates the class and extends BaseGame.
 * This means that the contents of BaseGame will be accessible in this class. The
 * BaseGame class is simply used to setup a game loop.
 */
public class SetupExample extends BaseGame {
	
	/* This method is called to render every frame of the game */
	public void render() {
		//Now its time to setup the screen, for simplicity we will just use
		//a simple 2D view so we don't need to set any other parameters
		OpenGLUtils.setupSimpleView2D();
		//Lets change the font size, as the default may be too small. Here we
		//will set it to 20
		Settings.Engine.DefaultFont.setSize(20);
		//Now to render some text to the screen, for this we will use the
		//default font that has already been setup for us
		Settings.Engine.DefaultFont.render("Hello World!", 0, 0);
	}
	
	/* This is the main method, which is called when this program is first started */
	public static void main(String[] args) {
		//Here you can now assign any value within the Settings class
		//For this example, we will use the default values, but change window title
		//to 'Setup Example'
		Settings.Window.Title = "Setup Example";
		//Now to start the game loop, simply create a new instance of this class:
		new SetupExample();
	}
	
}