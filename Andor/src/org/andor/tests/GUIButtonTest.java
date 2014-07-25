package org.andor.tests;

import org.andor.core.BaseGame;
import org.andor.core.Colour;
import org.andor.core.Vector2D;
import org.andor.gui.GUIButton;
import org.andor.utils.OpenGLUtils;

public class GUIButtonTest extends BaseGame {
	
	public GUIButton button;
	
	public GUIButtonTest() {
		
	}
	
	/* The method called when the loop has been created */
	public void create() {
		this.button = new GUIButton(new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 100, 20);
		this.button.text = "Hello";
		this.button.position = new Vector2D(100, 100);
	}
	
	/* The method called when the game loop is updated */
	public void update() {
		this.button.update();
	}
	
	/* The method called when the game loop is rendered */
	public void render() {
		//Setup OpenGL
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.setupRemoveAlpha();
		
		OpenGLUtils.setupOrtho(-1, 1);
		
		this.button.render();
	}
	
	public static void main(String[] args) {
		new GUIButtonTest();
	}
	
}