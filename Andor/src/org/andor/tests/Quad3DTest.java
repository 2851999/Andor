/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.tests;

import org.andor.core.BaseGame;
import org.andor.core.Camera3D;
import org.andor.core.Colour;
import org.andor.core.Image;
import org.andor.core.ImageLoader;
import org.andor.core.Object3DBuilder;
import org.andor.core.RenderableObject3D;
import org.andor.utils.OpenGLUtils;

public class Quad3DTest extends BaseGame {
	
	/* The camera */
	public Camera3D camera;
	
	/* The 3D Object */
	public RenderableObject3D quad;
	
	/* The texture */
	public Image texture;
	
	/* The constructor */
	public Quad3DTest() {
		
	}
	
	/* The method called when the loop has been created */
	public void create() {
		//Create the camera and change its position
		this.camera = new Camera3D();
		this.camera.position.z = -2;
		//Create the quad and change its position
		quad = Object3DBuilder.createTexturedQuad(1, 1, Colour.WHITE);
		quad.position.z = -2;
		//Load the texture and bind it
		String texturePath = "C:/Grass_Side.png";
		this.texture = ImageLoader.loadImage(texturePath, true);
		this.texture.bind();
	}
	
	/* The method called when the game loop has started */
	public void start() {
		
	}
	
	/* The method called when the game loop is updated */
	public void update() {
		
	}
	
	/* The method called when the game loop is rendered */
	public void render() {
		//Setup OpenGL
		OpenGLUtils.setupPerspective(70f, 1f, 1000f);
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.enableDepthTest();
		
		//Use the camera's view on the world
		this.camera.useView();
		
		//Render the quad
		this.quad.render();
	}
	
	/* The method called when the game loop is stopped */
	public void stop() {
		
	}
	
	/* The method called when the game loop is closing */
	public void close() {
		
	}
	
	/* The main method */
	public static void main(String[] args) {
		//Create a new instance of this test
		new Quad3DTest();
	}
	
}