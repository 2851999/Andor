package org.andor.tests;

import org.andor.core.BaseGame;
import org.andor.core.Settings;
import org.andor.core.model.Model;
import org.andor.core.model.OBJLoader;
import org.andor.game.DebugCamera3D;
import org.andor.physics.Physics;
import org.andor.physics.PhysicsScene3D;
import org.andor.physics.RenderablePhysicsObject3D;
import org.andor.physics.SphereCollider;
import org.andor.utils.OpenGLUtils;

public class PhysicsTest3D extends BaseGame {
	
	public DebugCamera3D camera;
	public RenderablePhysicsObject3D sphere1;
	public RenderablePhysicsObject3D sphere2;
	public PhysicsScene3D scene;
	
	public void create() {
		camera = new DebugCamera3D();
		
		Model s1 = OBJLoader.loadModel("H:/Andor/sphere.obj", "H:/Andor/", true);
		s1.prepare();
		Model s2 = OBJLoader.loadModel("H:/Andor/sphere.obj", "H:/Andor/", true);
		s2.prepare();
		sphere1 = new RenderablePhysicsObject3D(s1);
		sphere1.velocity.z = -0.5f;
		sphere1.setPosition(0, 0, -1);
		sphere1.setCollider(new SphereCollider(sphere1));
		sphere1.setWidth(2);
		sphere1.setHeight(2);
		sphere1.setDepth(2);
		
		sphere2 = new RenderablePhysicsObject3D(s2);
		sphere2.setPosition(0, 0, -8);
		sphere2.setCollider(new SphereCollider(sphere2));
		sphere2.setWidth(2);
		sphere2.setHeight(2);
		sphere2.setDepth(2);
		
		scene = new PhysicsScene3D();
		scene.add(sphere1);
		scene.add(sphere2);
		
		Physics.setup();
	}
	
	public void update() {
		Physics.update();
		camera.update(getDelta());
		scene.checkCollisions();
		sphere1.update();
		sphere2.update();
	}
	
	public void render() {
		OpenGLUtils.setupSimpleView3D();
		OpenGLUtils.enableDepthTest();
		camera.useView();
		sphere1.render();
		sphere2.render();
	}
	
	public static void main(String[] args) {
		Settings.Video.VSync = true;
		Settings.Debugging.ShowInformation = true;
		new PhysicsTest3D();
	}
	
}