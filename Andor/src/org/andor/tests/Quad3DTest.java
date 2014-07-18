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
import org.andor.core.Object3DBuilder;
import org.andor.core.RenderableObject3D;
import org.andor.core.Settings;
import org.andor.utils.OpenGLUtils;

public class Quad3DTest extends BaseGame {
	
	/* The camera */
	public Camera3D camera;
	
	/* The 3D Object */
	public RenderableObject3D quad;
	
	/* The constructor */
	public Quad3DTest() {
		
	}
	
	/* The method called when the loop has been created */
	public void create() {
		this.camera = new Camera3D();
		quad = Object3DBuilder.createQuad(1, 1, new Colour[] { Colour.RED, Colour.GREEN, Colour.BLUE });
		quad.position.z = -2;
	}
	
	/* The method called when the game loop has started */
	public void start() {
		
	}
	
	/* The method called when the game loop is updated */
	public void update() {
		
	}
	
	/* The method called when the game loop is rendered */
	public void render() {
		OpenGLUtils.setupPerspective(70, 1f, 1000f);
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.enableDepthTest();
		
		this.camera.useView();
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
		Settings.Window.Fullscreen = true;
		//Create a new instance of this test
		new Quad3DTest();
	}
	
}