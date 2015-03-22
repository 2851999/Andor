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
import org.andor.core.RenderableObject2D;
import org.andor.core.Settings;
import org.andor.core.input.Keyboard;
import org.andor.physics.GravitySource2D;
import org.andor.physics.Physics;
import org.andor.physics.PhysicsObject2D;
import org.andor.utils.OpenGLUtils;

public class PhysicsTest extends BaseGame {
	
	public RenderableObject2D quad;
	public PhysicsObject2D quadPhysics;
	
	public RenderableObject2D quad2;
	public GravitySource2D quadPhysics2;
	
	public void create() {
		quad = Object2DBuilder.createQuad(40, 40, Colour.RED);
		quadPhysics = new PhysicsObject2D(quad);
		quadPhysics.setPosition(490, (Settings.Window.Height / 2 - quad.getHeight() / 2) + 200);
		quadPhysics.setVelocity(600, 0);
		quadPhysics.setAcceleration(0, 0);
		quadPhysics.mass = 1999999999;
		
		quad2 = Object2DBuilder.createQuad(40, 40, Colour.RED);
		quadPhysics2 = new GravitySource2D(quad2);
		quadPhysics2.setPosition(Settings.Window.Width / 2 - quad.getWidth() / 2, Settings.Window.Height / 2 - quad.getHeight() / 2);
		//quadPhysics2.applyForce(new Vector2D(-20f, 0));
		quadPhysics2.mass = 1999999999;
		//quadPhysics2.setVelocity(-20, 0);
		//quadPhysics2.setAcceleration(-50, 0);
		Physics.setup();
	}
	
	public void update() {
		Physics.update();
		
		quadPhysics.update();
		quadPhysics2.update();
		quadPhysics2.apply(quadPhysics);
		
		if (quad.getBounds().intersects(quad2.getBounds()))
			quad.setColour(Colour.GREEN);
		else
			quad.setColour(Colour.RED);
		
		if (quad2.getBounds().intersects(quad.getBounds()))
			quad2.setColour(Colour.GREEN);
		else
			quad2.setColour(Colour.RED);
		
		if (Keyboard.KEY_ESCAPE)
			this.requestClose();
	}
	
	public void render() {
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.setupOrtho(-1, 1);
		OpenGLUtils.enableWireframeMode();
		
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