package org.andor.tests;

import org.andor.core.BaseGame;
import org.andor.core.Colour;
import org.andor.core.Object3DBuilder;
import org.andor.game.DebugCamera3D;
import org.andor.physics.Physics;
import org.andor.physics.RenderablePhysicsObject3D;
import org.andor.utils.OpenGLUtils;

public class PhysicsTest3D extends BaseGame {
	
	public DebugCamera3D camera;
	public RenderablePhysicsObject3D cube;
	
	public void create() {
		camera = new DebugCamera3D();
		
		cube = new RenderablePhysicsObject3D(Object3DBuilder.createCube(0.5f, 0.5f, 0.5f, Colour.ARRAY_RGB));
		cube.velocity.z = -0.1f;
		cube.setAngularAcceleration(1f, 4f, 2f);
		cube.setPosition(0, 0, -1);
		
		Physics.setup();
	}
	
	public void update() {
		Physics.update();
		camera.update(getDelta());
		cube.update();
	}
	
	public void render() {
		OpenGLUtils.setupSimpleView3D();
		OpenGLUtils.enableDepthTest();
		camera.useView();
		cube.render();
	}
	
	public static void main(String[] args) {
		new PhysicsTest3D();
	}
	
}