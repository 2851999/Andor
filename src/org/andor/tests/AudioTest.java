/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.tests;

import org.andor.core.Audio;
import org.andor.core.AudioLoader;
import org.andor.core.BaseGame;
import org.andor.core.Colour;
import org.andor.core.Font;
import org.andor.core.Object2DBuilder;
import org.andor.core.RenderableObject2D;
import org.andor.core.Settings;
import org.andor.core.Vector2D;
import org.andor.core.Vector3D;
import org.andor.core.Window;
import org.andor.core.input.Keyboard;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.MouseMotionEvent;
import org.andor.utils.FontUtils;
import org.andor.utils.OpenGLUtils;

public class AudioTest extends BaseGame {
	
	/* The 2D Object */
	public RenderableObject2D quad;
	
	/* The font */
	public Font font;
	
	/* The boolean that determine whether wireframe is on or off */
	public boolean wireframe;
	
	public Audio audio;
	
	public Vector3D soundVel;
	
	/* The constructor */
	public AudioTest() {
		
	}
	
	/* The method called when the loop has been created */
	public void create() {
		this.quad = Object2DBuilder.createQuad(100, 100, new Colour[] { Colour.LIGHT_BLUE, Colour.LIGHT_GREY, Colour.ORANGE, Colour.WHITE });
		this.quad.position = new Vector2D(100, 100);
		this.font = FontUtils.loadBitmapFont("C:/Andor/test2.png", true, 16, 12);
		this.audio = AudioLoader.load("C:/Andor/test2.wav", true);
	}
	
	/* The method called when the game loop has started */
	public void start() {
		//Play the audio
		this.audio.play();
	}
	
	/* The method called when the game loop is updated */
	public void update() {
		this.quad.rotation += 0.1f * getDelta();
		this.soundVel = new Vector3D(-0.001f, 0, 0);
		this.soundVel.multiply(this.getDelta());
		this.audio.sourceVelocity = this.soundVel;
		this.audio.sourcePosition.add(this.soundVel);
		this.audio.update();
	}
	
	/* The method called when the game loop is rendered */
	public void render() {
		//Setup OpenGL
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.setupRemoveAlpha();
		
		OpenGLUtils.setupOrtho(-1, 1);
		OpenGLUtils.disableTexture2D();
		
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
		new AudioTest();
	}
	
}