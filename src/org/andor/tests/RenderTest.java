/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.tests;

import org.andor.core.BaseGame;
import org.andor.core.Camera3D;
import org.andor.core.Colour;
import org.andor.core.ImageLoader;
import org.andor.core.Settings;
import org.andor.core.SkyBox;
import org.andor.core.Vector3D;
import org.andor.core.input.Keyboard;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.Mouse;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.lighting.DirectionalLight;
import org.andor.core.lighting.LitScene;
import org.andor.core.lighting.LitSceneInterface;
import org.andor.core.lighting.PointLight;
import org.andor.core.model.Model;
import org.andor.core.model.OBJLoader;
import org.andor.core.render.Renderer;
import org.andor.utils.ClampUtils;
import org.andor.utils.OpenGLUtils;

public class RenderTest extends BaseGame implements LitSceneInterface {
	
	public static final String PATH = "H:/Andor/Tests/Render/";
	
	public Camera3D camera;
	public Model floor;
	public Model cube;
	public LitScene scene;
	
	public PointLight light1;
	
	public boolean wireframe;
	
	public void create() {
		this.camera = new Camera3D();
		this.camera.setRotation(20f, 0, 0);
		this.camera.setPosition(0, -1, -3);
		this.camera.setSkyBox(new SkyBox(ImageLoader.loadImage(PATH + "skybox4.png", true), 100));
		this.floor = OBJLoader.loadModel(PATH + "plane.obj", PATH, true);
		this.floor.prepare();
		this.cube = OBJLoader.loadModel(PATH + "monkey2.obj", PATH, true);
		this.cube.prepare();
		this.cube.setScale(1f);
		this.cube.setPosition(0, 0.5f, -1);
		this.scene = new LitScene(this);
		this.scene.lights.add(new DirectionalLight(Colour.WHITE, 0.8f, new Vector3D(0, 1, 1)));
		this.light1 = new PointLight(Colour.ORANGE, 0.8f, new Vector3D(0f, 0f, 0.2f));
		this.light1.setPosition(0, 0.5f, 0);
		this.scene.lights.add(this.light1);
		
		this.wireframe = false;
		Renderer.camera = camera;
		Mouse.lock();
	}
	
	public void update() {
		if (Keyboard.KEY_W)
			camera.moveForward(0.005f * this.getDelta());
		if (Keyboard.KEY_S)
			camera.moveBackward(0.005f * this.getDelta());
		if (Keyboard.KEY_A)
			camera.moveLeft(0.005f * this.getDelta());
		if (Keyboard.KEY_D)
			camera.moveRight(0.005f * this.getDelta());
		if (Keyboard.KEY_ESCAPE)
			requestClose();
		this.camera.rotation.x = ClampUtils.clamp(this.camera.rotation.x, -80, 80);
		
		this.cube.rotation.add(0.05f * getDelta());
	}
	
	public void render() {
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.setupPerspective(70, 1, 100);
		OpenGLUtils.enableDepthTest();
		OpenGLUtils.setupRemoveAlpha();
		OpenGLUtils.enableTexture2D();
		
		this.camera.useView();
		this.scene.render();
	}
	
	public void renderObjects() {
		this.floor.render();
		this.cube.render();
	}
	
	public void onKeyTyped(KeyboardEvent e) {
		if (e.getCode() == Keyboard.KEY_F3_CODE)
			Mouse.setLocked(! Mouse.isLocked());
		else if (e.getCode() == Keyboard.KEY_M_CODE) {
			this.wireframe = ! this.wireframe;
			if (this.wireframe)
				OpenGLUtils.enableWireframeMode();
			else
				OpenGLUtils.disableWireframeMode();
		} else if (e.getCode() == Keyboard.KEY_1_CODE)
			this.camera.setSkyBox(new SkyBox(ImageLoader.loadImage(PATH + "skybox1.png", true), 100));
		else if (e.getCode() == Keyboard.KEY_2_CODE)
			this.camera.setSkyBox(new SkyBox(ImageLoader.loadImage(PATH + "skybox2.png", true), 100));
		else if (e.getCode() == Keyboard.KEY_3_CODE)
			this.camera.setSkyBox(new SkyBox(ImageLoader.loadImage(PATH + "skybox3.png", true), 100));
		else if (e.getCode() == Keyboard.KEY_4_CODE)
			this.camera.setSkyBox(new SkyBox(ImageLoader.loadImage(PATH + "skybox4.png", true), 100));
		else if (e.getCode() == Keyboard.KEY_5_CODE)
			this.camera.setSkyBox(new SkyBox(ImageLoader.loadImage(PATH + "skybox5.png", true), 100));
		else if (e.getCode() == Keyboard.KEY_6_CODE)
			this.camera.setSkyBox(new SkyBox(ImageLoader.loadImage(PATH + "skybox6.jpg", true), 100));
	}
	
	public void onMouseMoved(MouseMotionEvent e) {
		if (Mouse.isLocked())
			camera.rotation.add(new Vector3D(e.dy * 0.5f, e.dx * 0.5f, 0));
	}
	
	public static void main(String[] args) {
		Settings.Window.Title = "Render Test";
		Settings.Window.Width = 1280;
		Settings.Window.Height = 720;
		Settings.Video.VSync = true;
		Settings.Debugging.ShowInformation = true;
		new RenderTest();
	}
	
}