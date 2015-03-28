/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.tests;

import org.andor.core.Audio;
import org.andor.core.AudioLoader;
import org.andor.core.BaseGame;
import org.andor.core.BitmapText;
import org.andor.core.Camera3D;
import org.andor.core.Colour;
import org.andor.core.Font;
import org.andor.core.Image;
import org.andor.core.ImageLoader;
import org.andor.core.ImageSet;
import org.andor.core.Object3DBuilder;
import org.andor.core.Particle;
import org.andor.core.ParticleEffect;
import org.andor.core.ParticleEmitter;
import org.andor.core.RenderableObject3D;
import org.andor.core.Settings;
import org.andor.core.SkyBox;
import org.andor.core.Vector2D;
import org.andor.core.Vector3D;
import org.andor.core.input.ControlBindingAxis;
import org.andor.core.input.ControlBindingButton;
import org.andor.core.input.ControlBindings;
import org.andor.core.input.ControlInputListener;
import org.andor.core.input.Input;
import org.andor.core.input.InputController;
import org.andor.core.input.InputManagerController;
import org.andor.core.input.Keyboard;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.Mouse;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.model.Model;
import org.andor.core.model.OBJLoader;
import org.andor.core.render.DeferredScene;
import org.andor.utils.ClampUtils;
import org.andor.utils.ControlBindingUtils;
import org.andor.utils.ControllerUtils;
import org.andor.utils.FontUtils;
import org.andor.utils.OpenGLUtils;
import org.andor.utils.ScreenResolution;
import org.lwjgl.opengl.GL11;

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
	
	/* The boolean that determine whether wireframe is on or off */
	public boolean wireframe;
	
	public InputController controller;
	public ControlBindings bindings;
	public Audio audio;
	public ParticleEmitter particleEmitter;
	
	public BitmapText bitmapText;
	
	public DeferredScene scene;
	
	/* The constructor */
	public Cube3DTest() {
		
	}
	
	/* The method called when the loop has been created */
	public void create() {
		//Create the camera
		this.camera = new Camera3D();
		this.camera.flying = true;
		//Load the font
		this.font = FontUtils.loadBitmapFont("H:/Andor/test2.png", true, 16, 12);
		//Load the texture and bind it
		String path = "H:/Andor/";
		this.camera.setSkyBox(new SkyBox(ImageLoader.loadImage(path + "skybox1.png", true), 100));
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
		cube.setTexture(this.texture);
		bigCube = Object3DBuilder.createCube(10, 10, 10, new Colour(130f / 255f, 176f / 255f, 255f / 255f, 0.8f));
		//Load the model
		this.model = OBJLoader.loadModel(path + "ship.obj", path, true);
		this.model.prepare();
		this.model.position.z = -20;
		this.model.scale.multiply(0.03f);
		
		//Set wireframe to false
		wireframe = false;
		//Lock the mouse
		Mouse.lock();
		
		if (ControllerUtils.isPresent(Input.CONTROLLER_1))
			InputManagerController.addController(this.controller = new InputController(Input.CONTROLLER_1));
		
		audio = AudioLoader.load("H:/Andor/test2.wav", true);
		
		this.particleEmitter = new ParticleEmitter();
		this.particleEmitter.particleColour = Colour.RED;
		this.particleEmitter.position.y = 4f;
		this.particleEmitter.particleInitialVelocity = new Vector3D(0, 0.9f, 0);
		this.particleEmitter.particlesPerUpdate = 400;
		this.particleEmitter.particleLifeTime = 3000;
		this.particleEmitter.uniformity = 10;
		this.particleEmitter.particleEffect = new FireEffect();
		
		this.bitmapText = new BitmapText(ImageLoader.loadImage("H:/Andor/test2.png", true), 16, 40);
		this.bitmapText.update("Hello World");
		this.bitmapText.position = new Vector2D(100, 100);
		
		//ControlBindingUtils.save(this.bindings, "H:/Andor/Controller/gamepad.txt");
		ControlBindingUtils.currentController = this.controller;
		this.bindings = ControlBindingUtils.load("H:/Andor/Controller/gamepad.txt", true);
		this.bindings.addListener(this);
		
		//this.light0 = Light3D.createVertexSpot(0, new Vector3D(0, 8, -10), new Colour(0.1f, 0.1f, 0.1f), new Colour(1, 0, 0), new Colour(1, 1, 1), new Vector3D(0, -1, 0), 50);
		//this.light1 = Light3D.createVertexDirectional(1, new Vector3D(1, 0, 1), new Colour(0.1f, 0.1f, 0.1f), new Colour(0, 1, 0), new Colour(1, 1, 1));
		//this.light2 = Light3D.createVertexPoint(2, new Vector3D(0, 3, -7), new Colour(0.1f, 0.1f, 0.1f), new Colour(0, 0, 1), new Colour(1, 1, 1));
		scene = new DeferredScene();
		
		InputController[] controllers = ControllerUtils.getAvailableControllers();
		for (int a = 0; a < controllers.length; a++)
			System.out.println(controllers[a].getName());
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
		if (Keyboard.KEY_Q)
			this.particleEmitter.particleEffect = new WaterEffect();
		if (Keyboard.KEY_F)
			this.particleEmitter.particleEffect = new FireEffect();
		
		camera.moveForward(this.bindings.get("Walk").bindingAxis.currentValue / 100 * getDelta());
		camera.moveLeft(this.bindings.get("Stride").bindingAxis.currentValue / 100 * getDelta());
		camera.rotation.y += this.bindings.get("LookX").bindingAxis.currentValue / 3 * getDelta();
		camera.rotation.x += this.bindings.get("LookY").bindingAxis.currentValue / 3 * getDelta();
		
		this.camera.rotation.x = ClampUtils.clamp(this.camera.rotation.x, -80, 80);
		                                                                                                                                   
		this.audio.listenerPosition = this.camera.position.clone();
		this.audio.listenerPosition.multiply(new Vector3D(-1, 1, 1));
		//this.audio.listenerRotation = this.camera.rotation;
		this.audio.sourcePosition = this.model.position.clone();
		this.audio.sourcePosition.multiply(new Vector3D(1, 1, -1));
		this.audio.update();
		
		//this.particleEmitter.update();
		
		Vector3D change = new Vector3D(0, 0.06f, 0);
		change.multiply(getDelta());
		this.model.rotation.add(change);
	}
	
	/* The method called when the game loop is rendered */
	public void render() {
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		scene.startRendering();
		GL11.glPointSize(4);
		//Setup OpenGL
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.setupRemoveAlpha();
		
		OpenGLUtils.setupPerspective(70f, 1f, 1000f);
		OpenGLUtils.enableDepthTest();
		
		if (wireframe)
			OpenGLUtils.enableWireframeMode();
		else
			OpenGLUtils.disableWireframeMode();
		
		//Use the camera's view on the world
		this.camera.useView();
		
		OpenGLUtils.disableTexture2D();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		
		//this.light0.use();
		//this.light1.use();
		//this.light2.use();
		model.render();
		//this.light2.stopUsing();
		//this.light1.stopUsing();
		//this.light0.stopUsing();
		
		OpenGLUtils.enableTexture2D();
		
		//Render the cube
		this.cube.render();
		
		OpenGLUtils.disableTexture2D();
		
		this.bigCube.render();
		this.particleEmitter.render();
		
		OpenGLUtils.enableTexture2D();
		OpenGLUtils.disableDepthTest();
		
		OpenGLUtils.setupOrtho(-1, 1);
		
		this.bitmapText.render();
		
		//Render the FPS
		this.font.render("Current FPS: " + this.getFPS(), 10, 10);
		this.font.render("Object Face Count: " + this.model.getNumberOfFaces(), 10, 26);
		this.font.render("Particle Count: " + this.particleEmitter.particles.size(), 10, 42);
		scene.stopRendering();
		
		if (wireframe)
			OpenGLUtils.disableWireframeMode();
		
		scene.renderToScreen();
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
		} else if (binding.getControlBinding().name.equals("Rumble")) {
			//this.controller.rumble(500, 1f);
			//Reset the players position
			this.camera.position = new Vector3D(0, -2, 0);
			this.camera.rotation = new Vector3D(0, 0, 0);
		}
	}
	
	
	/* The main method */
	public static void main(String[] args) {
		//Make the game fullscreen
		Settings.Window.Fullscreen = false;
		Settings.Window.WindowedFullscreen = false;
		Settings.Window.Undecorated = false;
		//Enable VSync
		Settings.Video.VSync = true;
		Settings.Video.MaxFPS = 60;
		Settings.Window.Width = 1024;
		Settings.Window.Height = 768;
		Settings.Video.Resolution = ScreenResolution.RES_1080P;
		Settings.Debugging.ShowDeferredRenderingBuffers = true;
		Settings.Video.DeferredRendering = true;
		//Create a new instance of this test
		new Cube3DTest();
	}
	
	public class FireEffect implements ParticleEffect {
		
		public void update(Particle particle) {
			float life = particle.getPercentageOfLife() / 100;
			particle.colour = new Colour(Colour.RED);
			if (life > 0.5 && life < 0.75) {
				particle.colour = Colour.ORANGE.clone();
				particle.colour.g -= life;
			}
			if (life > 0.75)
				particle.colour = Colour.GREY.clone();
//			Random r = new Random();
//			particle.colour = new Colour(r.nextFloat(), r.nextFloat(), r.nextFloat(), r.nextFloat());
		}
		
	}
	
	public class WaterEffect implements ParticleEffect {
		
		public void update(Particle particle) {
			float life = particle.getPercentageOfLife() / 100;
			particle.colour = new Colour(Colour.BLUE);
			particle.velocity.z = 0.1f;
			if (life > 0.5 && life < 0.75) {
				particle.colour = Colour.LIGHT_BLUE.clone();
				particle.colour.g -= life;
			}
			if (life > 0.75) {
				particle.colour = Colour.WHITE.clone();
				particle.colour.a -= life / 2;
			}
			particle.velocity.subtract(new Vector3D(0, life / 100, 0));
		}
		
	}
	
}