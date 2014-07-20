/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.tests;

import org.andor.core.*;
import org.andor.core.input.*;
import org.andor.utils.*;

public class Cube3DTest extends BaseGame {
	
	/* The camera */
	public Camera3D camera;
	
	/* The 3D Object */
	public RenderableObject3D cube;
	
	/* The 3D Object */
	public RenderableObject3D bigCube;
	
	/* The texture */
	public Image texture;
	
	/* The font */
	public Font font;
	
	/* The constructor */
	public Cube3DTest() {
		
	}
	
	/* The method called when the loop has been created */
	public void create() {
		//Create the camera
		this.camera = new Camera3D();
		this.camera.flying = true;
		//Load the font
		this.font = FontUtils.createFont("Arial", 12);
		//Load the texture and bind it
		String texturePath = "C:/";
		//Create a skybox
		SkyBox skybox = new SkyBox(texturePath, new String[] {
				"sky-texture.png",
				"sky-texture.png",
				"sky-texture.png",
				"sky-texture.png",
				"sky-texture.png",
				"sky-texture.png"
		}, true);
		this.camera.setSkyBox(skybox);
		//Create the images
		ImageSet images = new ImageSet();
		Image grassSide = images.loadImage(texturePath + "Grass_Side.png", true);
		Image grass = images.loadImage(texturePath + "Grass.png", true);
		Image dirt = images.loadImage(texturePath + "Dirt.png", true);
		this.texture = images.joinImages();
		//Create the cube
		cube = Object3DBuilder.createCube(new Image[] {
				grassSide, grassSide, grassSide, grassSide,
				grass, dirt
		}, 1, 1, 1, Colour.WHITE);
		bigCube = Object3DBuilder.createCube(10, 10, 10, new Colour(130f / 255f, 176f / 255f, 255f / 255f, 0.8f));
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
		if (Keyboard.KEY_S)
			//Move the camera forwards
			camera.moveBackward(0.01f * getDelta());
		if (Keyboard.KEY_A)
			//Move the camera forwards
			camera.moveLeft(0.01f * getDelta());
		if (Keyboard.KEY_D)
			//Move the camera forwards
			camera.moveRight(0.01f * getDelta());
		
		if (Keyboard.KEY_ESCAPE)
			//Request to end the program
			requestClose();
	}
	
	/* The method called when the game loop is rendered */
	public void render() {
		//Setup OpenGL
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.enableDepthTest();
		OpenGLUtils.setupRemoveAlpha();
		OpenGLUtils.setupOrtho(1, -1);
		
		//Render the FPS
		this.font.render("Current FPS: " + this.getFPS(), 10, 10);
		
		OpenGLUtils.setupPerspective(70f, 0.1f, 1000f);
		OpenGLUtils.enableTexture2D();
		
		//Use the camera's view on the world
		this.camera.useView();
		
		//Bind the texture
		this.texture.bind();
		
		//Render the cube
		this.cube.render();
		
		OpenGLUtils.disableTexture2D();
		this.bigCube.render();
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
		else if (e.getCode() == Keyboard.KEY_R_CODE)
			//Reset the players position
			this.camera.position = new Vector3D(0, -2, 0);
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
		new Cube3DTest();
	}
	
}