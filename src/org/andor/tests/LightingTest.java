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
import org.andor.core.Settings;
import org.andor.core.Vector3D;
import org.andor.core.input.Keyboard;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.Mouse;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.lighting.BaseLight;
import org.andor.core.lighting.DirectionalLight;
import org.andor.core.lighting.LitScene;
import org.andor.core.lighting.LitSceneInterface;
import org.andor.core.lighting.PointLight;
import org.andor.core.model.Model;
import org.andor.core.model.OBJLoader;
import org.andor.core.render.Renderer;
import org.andor.utils.MathUtils;
import org.andor.utils.OpenGLUtils;

public class LightingTest extends BaseGame implements LitSceneInterface {
	
	public Model model;
	public Model model2;
	public static Camera3D camera;
	public BaseLight light;
	public LitScene scene;
	
	public void create() {
		this.model = OBJLoader.loadModel("H:/Andor/monkey2.obj", "H:/Andor/", true);
		this.model.prepare();
		this.model.scale.multiply(1f);
		this.model2 = OBJLoader.loadModel("H:/Andor/monkey2.obj", "H:/Andor/", true);
		this.model2.prepare();
		this.model2.scale.multiply(1f);
		this.model2.position.x += 3;
		camera = new Camera3D();
		camera.position.setZ(-2f);
		camera.flying = true;
		scene = new LitScene(this);
		//this.model.rotation.y = 90;
		//RenderPasses.setPass(ForwardPass.PASS_NAME, new PhongPass());
		scene.lights.add(new DirectionalLight(Colour.RED, 0.8f, new Vector3D(0, 1, 1)));
		light = new PointLight(Colour.BLUE, 0.8f, new Vector3D(0, 0, 1));
		light.position = new Vector3D(0, 0, 1);
		//light = new SpotLight(Colour.YELLOW, 0.9f, new Vector3D(0, 0, 0.4f), new Vector3D(0, 0, -1), 0.9f);
		//light.position = new Vector3D(0, 0, 2);
		scene.lights.add(light);
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
			this.requestClose();
		
		this.model.rotation.y += 0.08f * this.getDelta();
		//this.model2.rotation.y -= 0.08f * this.getDelta();
	}
	
	public void render() {
		OpenGLUtils.enableDepthTest();
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.setupPerspective(70, 1f, 100f);
		OpenGLUtils.setupRemoveAlpha();
		
		camera.useView();
		this.scene.render();
	}
	
	public void renderObjects() {
		this.model.render();
		this.model2.render();
	}
	
	public void onMouseMoved(MouseMotionEvent e) {
		if (Mouse.isLocked())
			camera.rotation.add(new Vector3D(e.dy * 0.5f, e.dx * 0.5f, 0));
		camera.rotation.x = MathUtils.clamp(camera.rotation.x, -80, 80);
	}
	
	public void onKeyTyped(KeyboardEvent e) {
		if (e.getCode() == Keyboard.KEY_F3_CODE)
			Mouse.setLocked(! Mouse.isLocked());
	}
	
	public static void main(String[] args) {
		Settings.Window.Title = "Lighting Test";
		Settings.Video.VSync = true;
		Settings.Window.Width = 1200;
		Settings.Window.Height = 720;
		Settings.Debugging.ShowInformation = true;
		new LightingTest();
	}
	
}