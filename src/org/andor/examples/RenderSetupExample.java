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
 * This example demonstrates how you prepare the display to
 * render something
 */

public class RenderSetupExample extends BaseGame {
	
	public RenderableObject2D quad;
	
	public void create() {
		quad = Object2DBuilder.createQuad(100, 100, Colour.ARRAY_RGB);
		quad.setPosition(200, 200);
	}
	
	public void render() {
		/*
		 * As you can see, the code for this example is similar to the others.
		 * You may have seen how we were previously using
		 * 		OpenGLUtils.setupSimpleView2D();
		 * in order prepare the screen for rendering. This method can be used
		 * in simple applications, however there are times when you will need
		 * to do this differently. To show this we well write out the code
		 * that this method performs for us.
		 */
		
		//Firstly, before rendering you should clear the colour buffer for the
		//screen. You don't need to know exactly what it is, but it clears
		//things from the previous render call.
		OpenGLUtils.clearColourBuffer();
		
		//Next you need to specify how you want to render things to the screen
		//The example below sets up an orthographic view. This is most commonly
		//used when rendering things in 2D, however for 3D it is best to use
		//a perspective view, so that things that are further away appear smaller
		//than ones closer.
		//In this case the method requires the width and height of the view, which we
		//will just give the window's width and height but also znear and zfar values.
		//These values determine how close/how far away things 
		OpenGLUtils.setupOrtho(Settings.Window.Width, Settings.Window.Height, -1, 1);
		//Colours can be defined as RGBA (Red, Green, Blue, Alpha) where alpha defines
		//the transparency. Calling the method below, allows these values to be
		//rendered allowing transparency.
		//In this case it is not needed, as there is no transparency, but when
		//rendering text it is needed.
		OpenGLUtils.setupRemoveAlpha();
		
		quad.render();
	}
	
	public static void main(String[] args) {
		Settings.Window.Title = "Render Setup Example";
		new RenderSetupExample();
	}
	
}