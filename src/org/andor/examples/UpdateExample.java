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
import org.andor.core.Colour;
import org.andor.core.Object2DBuilder;
import org.andor.core.RenderableObject2D;
import org.andor.core.Settings;
import org.andor.utils.OpenGLUtils;

/*
 * This example demonstrates how you can use the update
 * method to change how objects appear/behave
 */

public class UpdateExample extends BaseGame {
	
	/* This is the quad (Square) */
	public RenderableObject2D quad;
	
	/* This method is called to create any objects that are needed within the game */
	public void create() {
		//Create the quad
		quad = Object2DBuilder.createQuad(100, 100, Colour.ARRAY_RGB);
		quad.setPosition(200, 200);
		quad.setRotation(45);
		quad.setScale(2);
	}
	
	/* This method is called every frame (like render) to update the objects / game
	 * mechanics within the game
	 */
	public void update() {
		//Rotate the quad
		//To do this as it is a 2D object, you can just add a value onto
		//the rotation variable
		quad.rotation += 0.1f * getDelta();
		//Notice the call to getDelta(), this is simply the time since the last
		//frame and is used to make sure the rotation speed stays the same, no
		//matter what the frame rate is
		//Multiplying this value by 0.1f, makes the value smaller decreasing the
		//speed
	}
	
	/* This method is called to render every frame of the game */
	public void render() {
		//Setup the view
		OpenGLUtils.setupSimpleView2D();
		//Render the quad
		quad.render();
	}
	
	/* This is the main method, which is called when this program is first started */
	public static void main(String[] args) {
		Settings.Window.Title = "Update Example";
		new UpdateExample();
	}
	
}