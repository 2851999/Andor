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
 * This example demonstrates how you can create renderable objects
 * and render them
 */

public class ObjectsExample extends BaseGame {
	
	/* This is the quad (Square) */
	public RenderableObject2D quad;
	
	/* This method is called to create any objects that are needed within the game */
	public void create() {
		//Create the quad using the object builder, with a width and height
		//of 100 and using an array colour. You can also use a single colour, array
		//colours simply assign a different colour for each corner of the object
		quad = Object2DBuilder.createQuad(100, 100, Colour.ARRAY_RGB);
		
		//Now you can change various values from within the quad e.g.
		quad.setPosition(200, 200); //Change the position to (200, 200)
		quad.setRotation(45);       //Rotate it (45 degrees)
		quad.setScale(2);           //Scale it (2 times)
	}
	
	/* This method is called to render every frame of the game */
	public void render() {
		OpenGLUtils.setupSimpleView2D();
		//Render the quad
		quad.render();
	}
	
	/* This is the main method, which is called when this program is first started */
	public static void main(String[] args) {
		Settings.Window.Title = "Objects Example";
		new ObjectsExample();
	}
	
}