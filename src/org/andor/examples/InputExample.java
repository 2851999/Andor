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
import org.andor.core.Camera3D;
import org.andor.core.Colour;
import org.andor.core.Image;
import org.andor.core.ImageSet;
import org.andor.core.Object3DBuilder;
import org.andor.core.RenderableObject3D;
import org.andor.core.Settings;
import org.andor.core.Vector3D;
import org.andor.core.input.Keyboard;
import org.andor.core.input.Mouse;
import org.andor.core.input.MouseMotionEvent;
import org.andor.utils.ClampUtils;
import org.andor.utils.OpenGLUtils;

/*
 * This example demonstrates how you can use input and move around a scene using
 * a Camera3D object
 */

public class InputExample extends BaseGame {
	
	public RenderableObject3D cube;
	
	/*
	 * The Camera3D object is used to change the way the world is seen, these
	 * can be switched to at any time and inherit the object classes allowing
	 * them to be moved, rotated and scaled. There is also a 2D variant
	 * (Camera2D)
	 */
	public Camera3D camera;
	
	public void create() {
		ImageSet images = new ImageSet();
		Image grassSide = images.loadImage("H:/Andor/Grass_Side.png", true);
		Image grass = images.loadImage("H:/Andor/Grass.png", true);
		Image dirt = images.loadImage("H:/Andor/Dirt.png", true);
		Image finalTexture = images.joinImages();
		this.cube = Object3DBuilder.createCube(new Image[] {
				grassSide, grassSide, grassSide, grassSide,
				grass, dirt
		}, 2, 2, 2, Colour.WHITE);
		this.cube.setTexture(finalTexture);
		this.cube.position.z = -3;
		
		//Time to setup the camera
		this.camera = new Camera3D();
		
		//If you want to be able to move on the y axis as well, you can simply set flying to true
		//this.camera.flying = true;
		
		//Now we will hide the cursor by locking it to the screen
		Mouse.lock();
	}
	
	public void update() {
		//To check for keyboard input you can use the Keyboard class
		
		//Here we check the WASD keys
		if (Keyboard.KEY_W)
			//Move the camera forward
			this.camera.moveForward(0.01f * getDelta());
		if (Keyboard.KEY_A)
			//Move the camera left
			this.camera.moveLeft(0.01f * getDelta());
		if (Keyboard.KEY_S)
			//Move the camera backwards
			this.camera.moveBackward(0.01f * getDelta());
		if (Keyboard.KEY_D)
			//Move the camera size ways
			this.camera.moveRight(0.01f * getDelta());
		
		//Check the escape key
		if (Keyboard.KEY_ESCAPE)
			//Request the game to stop
			this.requestClose();
	}
	
	public void render() {
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.enableDepthTest();
		OpenGLUtils.setupPerspective(60, Settings.Window.Width / Settings.Window.Height, 1, 100);
		
		//Now we want to use the camera's view of the world, to do this call
		//Note: Using this., is not necessary, it just shows that the object is part of this class
		this.camera.useView();
		
		//Once using the view, anything that is rendered will be rendered using the camera's perspective
		this.cube.render();
	}
	
	/*
	 * Although there is also a Mouse class, it will not give the motion events. Using the Keyboard, like
	 * in the code is state-driven, meaning that the program checks whether something needs to happen.
	 * The other method is event-driven meaning that when something happens a method is called and the
	 * response is determined, without constantly checking the mouse and keyboard.
	 * Below is an event called when the mouse moves, in our case we will use it to rotate the camera
	 */
	public void onMouseMoved(MouseMotionEvent e) {
		//Make sure the mouse is locked
		if (Mouse.isLocked()) {
			//Below we change the camera's rotation by adding on a new vector. In Andor, the position,
			//rotation and scale is stored in either a Vector2D or Vector3D instance. The example
			//below does the same as adding e.dy * 0.5f to the x rotation,  e.dx * 0.5f to the y value
			//and 0 to z.
			//The dx, and dy values is simply how many pixels the mouse has moved by on the screen
			this.camera.rotation.add(new Vector3D(e.dy * 0.5f, e.dx * 0.5f, 0));
			//Leaving the code as it is will allow the camera to flip completely upside down, so
			//to fix this, we clamp the values so they never go below -80, or more than 80 degrees
			//on the x axis
			this.camera.rotation.x = ClampUtils.clamp(this.camera.rotation.x, -80, 80);
		}
	}
	
	public static void main(String[] args) {
		Settings.Window.Title = "Input Example";
		//Quick note, you can use the Settings.Debugging class to change debugging settings e.g.
		Settings.Debugging.ShowInformation = true;
		new InputExample();
	}
	
}