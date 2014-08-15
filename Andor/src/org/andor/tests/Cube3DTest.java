/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.tests;

import org.andor.core.Audio;
import org.andor.core.AudioLoader;
import org.andor.core.BaseGame;
import org.andor.core.Camera3D;
import org.andor.core.Colour;
import org.andor.core.Font;
import org.andor.core.Image;
import org.andor.core.ImageSet;
import org.andor.core.Object3DBuilder;
import org.andor.core.Particle;
import org.andor.core.ParticleEffect;
import org.andor.core.ParticleEmitter;
import org.andor.core.RenderableObject3D;
import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.SkyBox;
import org.andor.core.Vector3D;
import org.andor.core.Window;
import org.andor.core.input.ControlBindingAxis;
import org.andor.core.input.ControlBindingButton;
import org.andor.core.input.ControlBindings;
import org.andor.core.input.ControlInputListener;
import org.andor.core.input.InputController;
import org.andor.core.input.InputManagerController;
import org.andor.core.input.Keyboard;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.Mouse;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.model.Model;
import org.andor.core.model.OBJLoader;
import org.andor.utils.ClampUtils;
import org.andor.utils.Console;
import org.andor.utils.ControllerUtils;
import org.andor.utils.FontUtils;
import org.andor.utils.OpenGLUtils;

public class Cube3DTest extends BaseGame implements ControlInputListener {
	
	/* The camera */
	public Camera3D camera;
	
	/* The 3D Object */
	public RenderableObject3D cube;
	
	/* The 3D Object */
	public RenderableObject3D bigCube;
	
	/* The model */
	public Model model;
	
	/* The texture */
	public Image texture;
	
	/* The font */
	public Font font;
	
	public Shader test;
	
	/* The boolean that determine whether wireframe is on or off */
	public boolean wireframe;
	
	public InputController controller;
	public ControlBindings bindings;
	public Audio audio;
	public ParticleEmitter particleEmitter;
	
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
		String path = "C:/Andor/";
		//Create a skybox
		SkyBox skybox = new SkyBox(path, new String[] {
				"front.png",
				"back.png",
				"left.png",
				"right.png",
				"top.png",
				"bottom.png"
		}, true);
		this.camera.setSkyBox(skybox);
		//Create the images
		ImageSet images = new ImageSet();
		Image grassSide = images.loadImage(path + "Grass_Side.png", true);
		Image grass = images.loadImage(path + "Grass.png", true);
		Image dirt = images.loadImage(path + "Dirt.png", true);
		this.texture = images.joinImages();
		//Create the cube
		cube = Object3DBuilder.createCube(new Image[] {
				grassSide, grassSide, grassSide, grassSide,
				grass, dirt
		}, 1, 1, 1, Colour.WHITE);
		bigCube = Object3DBuilder.createCube(10, 10, 10, new Colour(130f / 255f, 176f / 255f, 255f / 255f, 0.8f));
		//Load the model
		this.model = OBJLoader.loadModel(path + "monkey.obj", true);
		this.model.prepare();
		this.model.position.z = -10;
		//Set wireframe to false
		wireframe = false;
		test = new Shader();
		test.load("C:/Andor/light", true);
		test.create();
		//Lock the mouse
		Mouse.lock();
		
		//Get all of the available controllers
		InputController[] controllers = ControllerUtils.getAvailableControllers();
		//Go through the controllers
		for (InputController controller : controllers) {
			//Print out some information
			Console.println("Name: " + controller.name);
			Console.println("Axis Count: " + controller.axisCount);
			Console.println("Button Count: " + controller.buttonCount);
			Console.println("");
			//Check the controller
			if (controller.getName().equals("SPEEDLINK Strike 2 Gamepad"))
				this.controller = controller;
		}
		//Add the controller
		InputManagerController.addController(this.controller);
		bindings = new ControlBindings();
		bindings.addListener(this);
		bindings.load("C:/Andor/gamepadconfig.txt", true, controller);
		bindings.setController(this.controller);
		
		audio = AudioLoader.load("C:/Andor/test2.wav", true);
		
		this.particleEmitter = new ParticleEmitter();
		this.particleEmitter.particleColour = Colour.RED;
		this.particleEmitter.position.y = 1f;
		this.particleEmitter.particleInitialVelocity = new Vector3D(0, 1f, 0);
		this.particleEmitter.particlesPerUpdate = 600;
		this.particleEmitter.particleLifeTime = 1000;
		this.particleEmitter.uniformity = 80;
		this.particleEmitter.particleEffect = new FireEffect();
	}
	
	/* The method called when the game loop has started */
	public void start() {
		this.audio.play();
	}
	
	/* The method called when the game loop is updated */
	public void update() {
		if (Keyboard.KEY_ESCAPE)
			//Request to end the program
			requestClose();
		
		camera.moveForward(this.bindings.get("Walk").bindingAxis.currentValue / 100 * getDelta());
		camera.moveLeft(this.bindings.get("Stride").bindingAxis.currentValue / 100 * getDelta());
		camera.rotation.y += this.bindings.get("LookX").bindingAxis.currentValue / 3 * getDelta();
		camera.rotation.x += this.bindings.get("LookY").bindingAxis.currentValue / 3 * getDelta();
		
		this.camera.rotation.x = ClampUtils.clamp(this.camera.rotation.x, -80, 80);
		                                                                                                                                   
		this.audio.listenerPosition = this.camera.position.clone();
		this.audio.listenerPosition.multiply(new Vector3D(-1, 1, 1));
		this.audio.listenerRotation = this.camera.rotation;
		this.audio.sourcePosition = this.model.position.clone();
		this.audio.sourcePosition.multiply(new Vector3D(1, 1, -1));
		this.audio.update();
		
		this.particleEmitter.update();
		
		Vector3D change = new Vector3D(0, 0.1f, 0);
		change.multiply(getDelta());
		this.model.rotation.add(change);
	}
	
	/* The method called when the game loop is rendered */
	public void render() {
		//Setup OpenGL
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.setupRemoveAlpha();
		
		OpenGLUtils.setupPerspective(70f, 0.1f, 1000f);
		OpenGLUtils.enableDepthTest();
		
		//Use the camera's view on the world
		this.camera.useView();
		
		OpenGLUtils.disableTexture2D();
		test.use();
		this.model.render();
		test.stopUsing();
		
		OpenGLUtils.enableTexture2D();
		
		//Bind the texture
		this.texture.bind();
		
		//Render the cube
		this.cube.render();
		
		this.texture.unbind();
		
		OpenGLUtils.disableTexture2D();
		
		this.bigCube.render();
		this.particleEmitter.render();
		
		OpenGLUtils.enableTexture2D();
		
		
		OpenGLUtils.setupOrtho(-1, 1);
		
		//Render the FPS
		this.font.render("Current FPS: " + this.getFPS(), 10, 10);
		this.font.render("Object Face Count: " + this.model.faces.size(), 10, 26);
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
		else if (e.getCode() == Keyboard.KEY_1_CODE)
			this.cube.renderer.updateColours(Object3DBuilder.createColourArray(24, Colour.WHITE));
		else if (e.getCode() == Keyboard.KEY_2_CODE)
			this.cube.renderer.updateColours(Object3DBuilder.createColourArray(24, Colour.RED));
		else if (e.getCode() == Keyboard.KEY_3_CODE)
			this.cube.renderer.updateColours(Object3DBuilder.createColourArray(24, Colour.GREEN));
		else if (e.getCode() == Keyboard.KEY_4_CODE)
			this.cube.renderer.updateColours(Object3DBuilder.createColourArray(24, Colour.BLUE));
		else if (e.getCode() == Keyboard.KEY_5_CODE)
			this.cube.renderer.updateColours(Object3DBuilder.createColourArray(24, new Colour(1f, 1f, 1f, 0.95f)));
		else if (e.getCode() == Keyboard.KEY_6_CODE)
			this.cube.renderer.updateColours(Object3DBuilder.createColourArray(24, new Colour(1f, 1f, 1f, 0.8f)));
		else if (e.getCode() == Keyboard.KEY_7_CODE)
			this.cube.renderer.updateColours(Object3DBuilder.createColourArray(24, new Colour(1f, 1f, 1f, 0.5f)));
		else if (e.getCode() == Keyboard.KEY_8_CODE)
			this.cube.renderer.updateColours(Object3DBuilder.createColourArray(24, new Colour(1f, 1f, 1f, 0.1f)));
		else if (e.getCode() == Keyboard.KEY_9_CODE)
			this.cube.renderer.updateColours(Object3DBuilder.createColourArray(24, new Colour(1f, 1f, 1f, 0.0f)));
		else if (e.getCode() == Keyboard.KEY_F11_CODE) {
			Settings.Window.Fullscreen = ! Settings.Window.Fullscreen;
			Window.updateDisplaySettings();
		}
	}
	
	/* The method called when the mouse moves */
	public void onMouseMoved(MouseMotionEvent e) {
		//Make sure the mouse is locked
		if (Mouse.isLocked())
			//Change the camera's rotation
			camera.rotation.add(new Vector3D(e.dy * 0.5f, e.dx * 0.5f, 0));
	}
	
	public void onAxisChange(ControlBindingAxis binding) {
		
	}
	
	public void onButtonPressed(ControlBindingButton binding) {
		
	}
	
	public void onButtonReleased(ControlBindingButton binding) {
		if (binding.getControlBinding().name.equals("Wireframe")) {
			wireframe = !wireframe;
			if (wireframe)
				OpenGLUtils.enableWireframeMode();
			else
				OpenGLUtils.disableWireframeMode();
		} else if (binding.getControlBinding().name.equals("Rumble")) {
			this.controller.rumble(500, 1f);
			//Reset the players position
			this.camera.position = new Vector3D(0, -2, 0);
			this.camera.rotation = new Vector3D(0, 0, 0);
		}
	}
	
	
	/* The main method */
	public static void main(String[] args) {
		//Make the game fullscreen
		Settings.Window.Fullscreen = false;
		//Enable VSync
		Settings.Video.VSync = true;
		Settings.Video.MaxFPS = 60;
		//Create a new instance of this test
		new Cube3DTest();
	}
	
	private class FireEffect implements ParticleEffect {
		
		public void update(Particle particle) {
			float life = particle.getPercentageOfLife() / 100;
			particle.colour = new Colour(Colour.RED);
			if (life > 0.5 && life < 0.75) {
				particle.colour = Colour.ORANGE.clone();
				particle.colour.g -= life;
			}
			if (life > 0.75)
				particle.colour = Colour.GREY.clone();
		}
		
	}
	
}