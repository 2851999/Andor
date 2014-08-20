/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.tests;

import org.andor.core.BaseGame;
import org.andor.core.Colour;
import org.andor.core.Font;
import org.andor.core.Image;
import org.andor.core.ImageLoader;
import org.andor.core.Object2DBuilder;
import org.andor.core.RenderableObject2D;
import org.andor.core.Settings;
import org.andor.core.Vector2D;
import org.andor.core.Window;
import org.andor.core.input.Keyboard;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.MouseMotionEvent;
import org.andor.utils.FontUtils;
import org.andor.utils.OpenGLUtils;

public class Quad2DTest extends BaseGame {
	
	/* The 2D Object */
	public RenderableObject2D quad;
	
	/* The font */
	public Font font;
	
	/* The boolean that determine whether wireframe is on or off */
	public boolean wireframe;
	
	public Image image;
	
	/* The constructor */
	public Quad2DTest() {
		
	}
	
	/* The method called when the loop has been created */
	public void create() {
		image = ImageLoader.loadImage("C:/Andor/Grass_Side.png", true);
		this.quad = Object2DBuilder.createQuad(image, 100, 100, new Colour[] { Colour.LIGHT_BLUE, Colour.LIGHT_GREY, Colour.ORANGE, Colour.WHITE });
		this.quad.position = new Vector2D(100, 100);
		this.font = FontUtils.createFont("Arial", 12);
	}
	
	/* The method called when the game loop has started */
	public void start() {
		
	}
	
	/* The method called when the game loop is updated */
	public void update() {
		this.quad.rotation += 0.1f * getDelta();
	}
	
	/* The method called when the game loop is rendered */
	public void render() {
		//Setup OpenGL
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.setupRemoveAlpha();
		
		OpenGLUtils.setupOrtho(-1, 1);
		OpenGLUtils.enableTexture2D();
		
		image.bind();
		this.quad.render();
		
		OpenGLUtils.enableTexture2D();
		
		//Render the FPS
		this.font.render("Current FPS: " + this.getFPS(), 10, 10);
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
		if (e.getCode() == Keyboard.KEY_M_CODE) {
			wireframe = !wireframe;
			if (wireframe)
				OpenGLUtils.enableWireframeMode();
			else
				OpenGLUtils.disableWireframeMode();
		} else if (e.getCode() == Keyboard.KEY_F11_CODE) {
			Settings.Window.Fullscreen = ! Settings.Window.Fullscreen;
			Window.updateDisplaySettings();
		}
	}
	
	/* The method called when the mouse moves */
	public void onMouseMoved(MouseMotionEvent e) {
		
	}
	
	
	/* The main method */
	public static void main(String[] args) {
		//Make the game fullscreen
		Settings.Window.Fullscreen = false;
		//Enable VSync
		Settings.Video.VSync = true;
		Settings.Video.MaxFPS = 60;
		//Create a new instance of this test
		new Quad2DTest();
	}
	
}