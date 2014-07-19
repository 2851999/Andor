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
import org.andor.core.ImageSet;
import org.andor.core.Object3DBuilder;
import org.andor.core.RenderableObject3D;
import org.andor.core.Settings;
import org.andor.core.Vector3D;
import org.andor.core.input.Keyboard;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.Mouse;
import org.andor.core.input.MouseMotionEvent;
import org.andor.utils.OpenGLUtils;

public class Quad3DTest extends BaseGame {
	
	/* The camera */
	public Camera3D camera;
	
	/* The 3D Object */
	public RenderableObject3D cube;
	
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
		this.camera.flying = true;
		//Load the texture and bind it
		String texturePath = "C:/";
		//Create the images
		ImageSet images = new ImageSet();
		Image grassSide = images.loadImage(texturePath + "Grass_Side.png", true);
		Image grass = images.loadImage(texturePath + "Grass.png", true);
		Image dirt = images.loadImage(texturePath + "Dirt.png", true);
		this.texture = images.joinImages();
		this.texture.bind();
		//Create the cube and change its position
		cube = Object3DBuilder.createCube(new Image[] {
				grassSide, grassSide, grassSide, grassSide,
				grass, dirt
		}, 1, 1, 1, Colour.WHITE);
		cube.position.z = -2;
		//Lock the mouse
		Mouse.lock();
	}
	
	/* The method called when the game loop has started */
	public void start() {
		
	}
	
	/* The method called when the game loop is updated */
	public void update() {
		//Check the keys
		if (Keyboard.KEY_W)
			//Move the camera forwards
			camera.moveForward(0.01f * getDelta());
		else if (Keyboard.KEY_S)
			//Move the camera forwards
			camera.moveBackward(0.01f * getDelta());
		else if (Keyboard.KEY_A)
			//Move the camera forwards
			camera.moveLeft(0.01f * getDelta());
		else if (Keyboard.KEY_D)
			//Move the camera forwards
			camera.moveRight(0.01f * getDelta());
		
		if (Keyboard.KEY_ESCAPE)
			//Request to end the program
			requestClose();
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
		this.cube.render();
	}
	
	/* The method called when the game loop is stopped */
	public void stop() {
		
	}
	
	/* The method called when the game loop is closing */
	public void close() {
		
	}
	
	/* The method called when a key is typed moves */
	public void onKeyTyped(KeyboardEvent e) {
		//Check the key that was pressed
		if (e.getCode() == Keyboard.KEY_F3_CODE)
			//Toggle the mouse locked
			Mouse.setLocked(! Mouse.isLocked());
	}
	
	/* The method called when the mouse moves */
	public void onMouseMoved(MouseMotionEvent e) {
		//Make sure the mouse is locked
		if (Mouse.isLocked())
			//Change the camera's rotation
			camera.rotation.add(new Vector3D(e.dy * 0.5f, e.dx * 0.5f, 0));
	}
	
	
	/* The main method */
	public static void main(String[] args) {
		//Make the game fullscreen
		Settings.Window.Fullscreen = true;
		//Enable VSync
		Settings.Video.VSync = true;
		//Create a new instance of this test
		new Quad3DTest();
	}
	
}