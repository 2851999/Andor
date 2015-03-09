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
import org.andor.core.Image;
import org.andor.core.ImageSet;
import org.andor.core.Object3DBuilder;
import org.andor.core.RenderableObject3D;
import org.andor.core.Settings;
import org.andor.utils.OpenGLUtils;

/*
 * This example demonstrates how you can render a textured cube, where
 * not all sides have the same texture
 */

public class TexturedCubeExample extends BaseGame {
	
	/* Now we are moving to 3D, we will use RenderableObject3D */
	public RenderableObject3D cube;
	
	public void create() {
		//You can add a texture to a cube, by adding the texture to the first parameter in
		//create cube and then assigning the texture using this.cube.setTexture()
		//This however only sets the whole cube to the same image. The best way to
		//use multiple images is by stitching these images together and applying the texture
		//using the ImageSet class
		
		//First we need to create the image set like this
		ImageSet images = new ImageSet();
		
		//Now that we have the set, we need to add the textures we want to add to it using the following
		//Don't forget to create objects to assign these two so we can use them in the cube.
		Image grassSide = images.loadImage("H:/Andor/Grass_Side.png", true);
		Image grass = images.loadImage("H:/Andor/Grass.png", true);
		Image dirt = images.loadImage("H:/Andor/Dirt.png", true);
		//Now to join the images and assign the final texture
		Image finalTexture = images.joinImages();
		
		//Now we can assign the textures
		this.cube = Object3DBuilder.createCube(new Image[] {
				grassSide, grassSide, grassSide, grassSide, //SIDES
				grass, dirt //TOP AND BOTTOM
		}, 0.5f, 0.5f, 0.5f, Colour.WHITE);
		
		//Now you can assign the texture to the one that has all of the images in it
		this.cube.setTexture(finalTexture);
		
		this.cube.position.z = -2;
		this.cube.rotation.y = 45;
	}
	
	public void render() {
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.enableDepthTest();
		OpenGLUtils.setupPerspective(60, Settings.Window.Width / Settings.Window.Height, 1, 100);
		
		cube.render();
	}
	
	public static void main(String[] args) {
		Settings.Window.Title = "Cube Example";
		new TexturedCubeExample();
	}
	
}