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
import org.andor.core.Settings;
import org.andor.core.Vector3D;
import org.andor.core.input.Keyboard;
import org.andor.core.input.Mouse;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.model.Model;
import org.andor.core.model.OBJLoader;
import org.andor.core.render.ForwardPass;
import org.andor.core.render.RenderPasses;
import org.andor.experimental.PhongPass;
import org.andor.utils.ClampUtils;
import org.andor.utils.OpenGLUtils;

public class LightingTest extends BaseGame {
	
	public Model model;
	public static Camera3D camera;
	
	public void create() {
		this.model = OBJLoader.loadModel("H:/Andor/teapot.obj", "H:/Andor/", true);
		this.model.prepare();
		this.model.scale.multiply(1f);
		camera = new Camera3D();
		camera.position.setZ(-2f);
		camera.flying = true;
		//this.model.rotation.y = 90;
		RenderPasses.setPass(ForwardPass.PASS_NAME, new PhongPass());
		
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
	}
	
	public void render() {
		OpenGLUtils.enableDepthTest();
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.setupPerspective(70, 1f, 100f);
		
		camera.useView();
		this.model.render();
	}
	
	public void onMouseMoved(MouseMotionEvent e) {
		if (Mouse.isLocked())
			camera.rotation.add(new Vector3D(e.dy * 0.5f, e.dx * 0.5f, 0));
		camera.rotation.x = ClampUtils.clamp(camera.rotation.x, -80, 80);
	}
	
	public static void main(String[] args) {
		Settings.Window.Title = "Lighting Test";
		Settings.Debugging.ShowInformation = true;
		new LightingTest();
	}
	
}