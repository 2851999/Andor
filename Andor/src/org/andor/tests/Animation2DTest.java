/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.tests;

import org.andor.core.Animation2D;
import org.andor.core.BaseGame;
import org.andor.core.Font;
import org.andor.core.Image;
import org.andor.core.ImageEntity2D;
import org.andor.core.ImageLoader;
import org.andor.core.Settings;
import org.andor.core.Vector2D;
import org.andor.core.Window;
import org.andor.core.input.Keyboard;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.MouseMotionEvent;
import org.andor.utils.FontUtils;
import org.andor.utils.OpenGLUtils;

public class Animation2DTest extends BaseGame {
	
	public Animation2D animation;
	
	/* The font */
	public Font font;
	
	/* The boolean that determine whether wireframe is on or off */
	public boolean wireframe;
	
	/* The constructor */
	public Animation2DTest() {
		
	}
	
	/* The method called when the loop has been created */
	public void create() {
		this.font = FontUtils.createFont("Arial", 12);
		Image image = ImageLoader.loadImage("C:/Andor/Grass.png", true);
		ImageEntity2D entity = new ImageEntity2D(image);
		entity.position = new Vector2D(100, 100);
		this.animation = new Animation2D(entity, new Image[] {
				ImageLoader.loadImage("C:/Andor/Grass.png", true), ImageLoader.loadImage("C:/Andor/Dirt.png", true)
		}, 1000);
		this.animation.start();
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
		
		this.animation.entity.render();
		
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