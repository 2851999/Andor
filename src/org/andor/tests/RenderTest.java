/* *********************************************
 * ANDORf
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
import org.andor.core.TextureParameters;
import org.andor.core.Vector3D;
import org.andor.core.input.Keyboard;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.Mouse;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.lighting.BaseLight;
import org.andor.core.lighting.DirectionalLight;
import org.andor.core.lighting.LitScene;
import org.andor.core.lighting.LitSceneInterface;
import org.andor.core.model.Model;
import org.andor.core.model.OBJLoader;
import org.andor.core.render.Renderer;
import org.andor.utils.ClampUtils;
import org.andor.utils.OpenGLUtils;
import org.andor.utils.ScreenResolution;
import org.lwjgl.opengl.GL11;

public class RenderTest extends BaseGame implements LitSceneInterface {
	
	public static final String PATH = "H:/Andor/Tests/Render/";
	
	public Camera3D camera;
	public Model model;
	public Model plane;
	public Model cube;
	public LitScene scene;
	
	public BaseLight light1;
	public float speed;
	public boolean wireframe;
	
	public void create() {
		this.camera = new Camera3D();
		this.camera.setRotation(20f, 0, 0);
		this.camera.setPosition(0, -1, -3);
		this.camera.setSkyBox(new SkyBox(ImageLoader.loadImage(PATH + "skybox4.png", true, new TextureParameters().setFilter(GL11.GL_NEAREST)), 200));
		this.camera.flying = true;
		
		//this.model = OBJLoader.loadModel(PATH + "crytek-sponza/sponza.obj", PATH + "crytek-sponza/", true);
		this.model = OBJLoader.loadModel(PATH + "plane.obj", PATH + "", true);
		this.model.prepare(true);
		this.model.setScale(4f);
		this.model.setPosition(0, 0, 0f);
		
		this.plane = OBJLoader.loadModel(PATH + "plane2.obj", PATH, true);
		this.plane.prepare(true);
		this.plane.setScale(0.5f);
		this.plane.setPosition(0, 0.5f, 0);
		this.plane.setRotation(0, 0, -30);
		
		this.cube = OBJLoader.loadModel(PATH + "monkey2.obj", PATH, true);
		this.cube.prepare();
		this.cube.setScale(0.5f);
		this.cube.setPosition(4, 0.5f, 0);
		
		this.scene = new LitScene(this);
		
		//this.scene.lights.add(new DirectionalLight(Colour.WHITE, 1f, new Vector3D(-1f, 0f, 0f)));
		//this.scene.lights.add(new DirectionalLight(Colour.WHITE, 1f, new Vector3D(0f, 1f, 0f)));
		//this.scene.lights.add(new DirectionalLight(Colour.WHITE, 0.8f, new Vector3D(-1f, 0f, 0f)));
		
		this.light1 = new DirectionalLight(Colour.WHITE, 1f, new Vector3D(0f, 1f, 0f));
		this.light1.getRotation().setX(45);
		this.light1.getRotation().setZ(45);
		
		//this.light1.position = new Vector3D(0, 0, 0);
		//this.light1 = new PointLight(Colour.LIGHT_BLUE, 1f, new Vector3D(0f, 0.4f, 0.2f));
		//this.light1.setPosition(0, -2f, 0);
		this.scene.add(this.light1);
		
		//this.plane.parts.get(0).setTexture(new Image(scene.shadowMap.getShadowMap()));
		//this.plane.parts.get(0).material.normalTextureMap = null;
		//this.plane.parts.get(0).material.displacementTextureMap = null;
		
		//BaseLight light2 = new PointLight(Colour.ORANGE, 1f, new Vector3D(0f, 0.4f, 0.2f));
		//light2.setPosition(0, 1f, 0);
		//this.scene.lights.add(light2);
		
		this.wireframe = false;
		Renderer.camera = camera;
		Renderer.ambientLight =  new Colour(0.1f, 0.1f, 0.1f);
		Mouse.lock();
	}
	
	public void update() {
		if (Keyboard.KEY_LSHIFT)
			speed = 0.02f;
		else
			speed = 0.005f;
		if (Keyboard.KEY_W)
			camera.moveForward(speed * this.getDelta());
		if (Keyboard.KEY_S)
			camera.moveBackward(speed * this.getDelta());
		if (Keyboard.KEY_A)
			camera.moveLeft(speed * this.getDelta());
		if (Keyboard.KEY_D)
			camera.moveRight(speed * this.getDelta());
		if (Keyboard.KEY_ESCAPE)
			requestClose();
		this.camera.rotation.x = ClampUtils.clamp(this.camera.rotation.x, -80, 80);
		
		this.cube.getRotation().add(0.1f * getDelta());
		
		if (Keyboard.KEY_UP)
			this.light1.position.x -= 0.005f * getDelta();
		if (Keyboard.KEY_DOWN)
			this.light1.position.x += 0.005f * getDelta();
		if (Keyboard.KEY_LEFT)
			this.light1.position.z += 0.005f * getDelta();
		if (Keyboard.KEY_RIGHT)
			this.light1.position.z -= 0.005f * getDelta();
	}
	
	public void render() {
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.setupPerspective(70, 1, 100);
		//OpenGLUtils.setupOrtho(-1, 1, -1, 1, -1, 100);
		OpenGLUtils.enableDepthTest();
		OpenGLUtils.setupRemoveAlpha();
		OpenGLUtils.enableTexture2D();
		//Matrix.projectionMatrix = Matrix.lightProjectionMatrix;
		
		this.camera.useView();
		//Matrix.viewMatrix = Matrix.lightViewMatrix;
		
		//GL11.glEnable(GL11.GL_CULL_FACE);
		//GL11.glCullFace(GL11.GL_CW);
		
		this.scene.render();
		
		//GL11.glDisable(GL11.GL_CULL_FACE);
	}
	
	public void renderObjects() {
		this.model.render();
		this.plane.render();
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
			this.camera.setSkyBox(new SkyBox(ImageLoader.loadImage(PATH + "skybox1.png", true, new TextureParameters().setFilter(GL11.GL_NEAREST)), 100));
		else if (e.getCode() == Keyboard.KEY_2_CODE)
			this.camera.setSkyBox(new SkyBox(ImageLoader.loadImage(PATH + "skybox2.png", true, new TextureParameters().setFilter(GL11.GL_NEAREST)), 100));
		else if (e.getCode() == Keyboard.KEY_3_CODE)
			this.camera.setSkyBox(new SkyBox(ImageLoader.loadImage(PATH + "skybox3.png", true, new TextureParameters().setFilter(GL11.GL_NEAREST)), 100));
		else if (e.getCode() == Keyboard.KEY_4_CODE)
			this.camera.setSkyBox(new SkyBox(ImageLoader.loadImage(PATH + "skybox4.png", true, new TextureParameters().setFilter(GL11.GL_NEAREST)), 100));
		else if (e.getCode() == Keyboard.KEY_5_CODE)
			this.camera.setSkyBox(new SkyBox(ImageLoader.loadImage(PATH + "skybox5.png", true, new TextureParameters().setFilter(GL11.GL_NEAREST)), 100));
		else if (e.getCode() == Keyboard.KEY_6_CODE)
			this.camera.setSkyBox(new SkyBox(ImageLoader.loadImage(PATH + "skybox6.jpg", true, new TextureParameters().setFilter(GL11.GL_NEAREST)), 100));
	}
	
	public void onMouseMoved(MouseMotionEvent e) {
		if (Mouse.isLocked())
			camera.rotation.add(new Vector3D(e.dy * 0.5f, e.dx * 0.5f, 0));
	}
	
	public static void main(String[] args) {
		Settings.Window.Title = "Render Test";
		//Settings.Window.Width = 1280;
		//Settings.Window.Height = 720;
		Settings.Window.setSize(ScreenResolution.RES_720P);
		Settings.Video.VSync = true;
		Settings.Debugging.ShowInformation = true;
		Settings.Video.Resolution = ScreenResolution.RES_720P;
		Settings.Window.Fullscreen = false;
		TextureParameters.DEFAULT_FILTER = GL11.GL_LINEAR_MIPMAP_LINEAR;
		new RenderTest();
	}
	
}