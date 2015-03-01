/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.tests;

import org.andor.core.Animation2D;
import org.andor.core.BaseGame;
import org.andor.core.Font;
import org.andor.core.Image;
import org.andor.core.ImageLoader;
import org.andor.core.Settings;
import org.andor.core.Sprite2D;
import org.andor.core.Vector2D;
import org.andor.core.Window;
import org.andor.core.input.Keyboard;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.MouseMotionEvent;
import org.andor.utils.FontUtils;
import org.andor.utils.OpenGLUtils;

public class Animation2DTest extends BaseGame {
	
	public Sprite2D animation;
	
	/* The font */
	public Font font;
	
	/* The boolean that determine whether wireframe is on or off */
	public boolean wireframe;
	
	/* The constructor */
	public Animation2DTest() {
		
	}
	
	/* The method called when the loop has been created */
	public void create() {
		this.font = FontUtils.loadBitmapFont("H:/Andor/test2.png", true, 16, 12);
		Image grass = ImageLoader.loadImage("H:/Andor/Grass.png", true);
		Image dirt = ImageLoader.loadImage("H:/Andor/Dirt.png", true);
		this.animation = new Sprite2D(grass);
		this.animation.position = new Vector2D(100, 100);
		this.animation.add(new Animation2D(this.animation, new Image[] { grass, dirt }, 1000), "Animation", 1);
		this.animation.start(1);
	}
	
	/* The method called when the game loop has started */
	public void start() {
		
	}
	
	/* The method called when the game loop is updated */
	public void update() {
		this.animation.update();
	}
	
	/* The method called when the game loop is rendered */
	public void render() {
		//Setup OpenGL
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.setupRemoveAlpha();
		
		OpenGLUtils.setupOrtho(-1, 1);
		OpenGLUtils.disableTexture2D();
		
		this.animation.render();
		
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
		new Animation2DTest();
	}
	
}