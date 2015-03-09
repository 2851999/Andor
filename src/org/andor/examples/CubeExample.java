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
import org.andor.core.Object3DBuilder;
import org.andor.core.RenderableObject3D;
import org.andor.core.Settings;
import org.andor.utils.OpenGLUtils;

/*
 * This example demonstrates how you can setup a 3D environment
 * and render a cube
 */

public class CubeExample extends BaseGame {
	
	/* Now we are moving to 3D, we will use RenderableObject3D */
	public RenderableObject3D cube;
	
	public void create() {
		//Setting up the cube, is similar to the quad you just need to account
		//for the depth of the cube
		//Notice how the size of the cube is 0.5, this is because in 3D space things
		//are not measured in pixels, so a value of 1 will fill up most of the screen
		this.cube = Object3DBuilder.createCube(0.5f, 0.5f, 0.5f, Colour.ARRAY_RGB);
		//You can also add a texture to this cube, in the same way we did for the quad
		//Lets move the cube on the x axis and rotate it to make it easier to see
		this.cube.position.z = -2;
		this.cube.rotation.y = 45;
	}
	
	public void render() {
		OpenGLUtils.clearColourBuffer();
		//As this is a 3D view, you should also clear the depth buffer and enable depth test
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.enableDepthTest();
		//And also setup a perspective view
		//The line below shows you how to do this
		//The first parameter (60) is the fov (or field of view) on the y axis. This value
		//determines the angle between the area you can see, the best way to explain this
		//is by changing the value and see what happens for yourself.
		//The second parameter is the aspect ratio, which is the width / height, you don't need
		//to specify this as like with setupOrtho, Andor will calculate it for you
		//Next is the znear and zfar values that are the same as in setupOrtho, notice
		//how as we will be rendering in 3D space, these values are larger than before, and znear
		//isn't -1
		OpenGLUtils.setupPerspective(60, Settings.Window.Width / Settings.Window.Height, 1, 100);
		
		//Now you can render the cube, just like the quad
		cube.render();
	}
	
	public static void main(String[] args) {
		Settings.Window.Title = "Cube Example";
		new CubeExample();
	}
	
}