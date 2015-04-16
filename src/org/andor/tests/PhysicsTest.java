/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.tests;

import org.andor.core.BaseGame;
import org.andor.core.Colour;
import org.andor.core.Object2DBuilder;
import org.andor.core.Settings;
import org.andor.core.input.Keyboard;
import org.andor.physics.AABBCollider2D;
import org.andor.physics.Physics;
import org.andor.physics.PhysicsScene2D;
import org.andor.physics.RenderablePhysicsObject2D;
import org.andor.utils.OpenGLUtils;

public class PhysicsTest extends BaseGame {
	
	public PhysicsScene2D scene;
	public RenderablePhysicsObject2D quad;
	public RenderablePhysicsObject2D quad2;
	
	public void create() {
		Physics.setup();
		scene = new PhysicsScene2D();
		
//		quad = new RenderablePhysicsObject2D(Object2DBuilder.createCircle(20, 60, Colour.RED));
//		quad.setCollider(new CircleCollider(quad));
		quad = new RenderablePhysicsObject2D(Object2DBuilder.createQuad(40, 40, Colour.RED));
		quad.setCollider(new AABBCollider2D(quad));
		quad.setPosition(100, Settings.Window.Height / 2 + 20);
		quad.setVelocity(80, 0);
		
//		quad2 = new RenderablePhysicsObject2D(Object2DBuilder.createCircle(20, 60, Colour.RED));
//		quad2.setCollider(new CircleCollider(quad2));
		quad2 = new RenderablePhysicsObject2D(Object2DBuilder.createQuad(40, 40, Colour.RED));
		quad2.setCollider(new AABBCollider2D(quad2));
		quad2.setPosition(200, Settings.Window.Height / 2);
		
		scene.add(quad);
		scene.add(quad2);
	}
	
	public void update() {
		Physics.update();
		scene.checkCollisions();
		quad.update();
		quad2.update();
		
		if (Keyboard.KEY_ESCAPE)
			this.requestClose();
	}
	
	public void render() {
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.setupOrtho(-1, 1);
		
		quad.render();
		quad2.render();
		
		OpenGLUtils.disableWireframeMode();
	}
	
	public static void main(String[] args) {
		Settings.Video.VSync = true;
		Settings.Debugging.ShowInformation = true;
		new PhysicsTest();
	}
	
}