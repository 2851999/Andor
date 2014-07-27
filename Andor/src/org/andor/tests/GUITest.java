package org.andor.tests;

import org.andor.core.BaseGame;
import org.andor.core.Colour;
import org.andor.core.Vector2D;
import org.andor.gui.GUIButton;
import org.andor.gui.GUICheckBox;
import org.andor.gui.GUIComponent;
import org.andor.gui.GUIComponentListener;
import org.andor.gui.GUIDropDownList;
import org.andor.gui.GUIDropDownMenu;
import org.andor.utils.Console;
import org.andor.utils.OpenGLUtils;

public class GUITest extends BaseGame implements GUIComponentListener {
	
	public GUIButton button;
	public GUICheckBox checkBox;
	public GUIDropDownMenu menu;
	public GUIDropDownList list;
	
	public GUITest() {
		
	}
	
	/* The method called when the loop has been created */
	public void create() {
		this.button = new GUIButton(new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 100, 20);
		this.button.text = "Click Me";
		this.button.name = "Button";
		this.button.position = new Vector2D(100, 100);
		this.button.addListener(this);
		
		this.checkBox = new GUICheckBox(new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 20, 20);
		this.checkBox.name = "Checkbox";
		this.checkBox.position = new Vector2D(220, 100);
		this.checkBox.addListener(this);
		
		this.menu = new GUIDropDownMenu(new GUIButton("File", new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 100, 20));
		this.menu.addButton(new GUIButton("Save", new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 100, 20));
		this.menu.addButton(new GUIButton("Save As", new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 100, 20));
		this.menu.position = new Vector2D(260, 100);
		
		this.list = new GUIDropDownList(new GUIButton("800 x 600", new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 100, 20));
		this.list.addButton(new GUIButton("1024 x 720", new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 100, 20));
		this.list.addButton(new GUIButton("1920 x 1080", new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 100, 20));
		this.list.position = new Vector2D(380, 100);
	}
	
	/* The method called when the game loop is updated */
	public void update() {
		this.button.update();
		this.checkBox.update();
		this.menu.update();
		this.list.update();
	}
	
	/* The method called when the game loop is rendered */
	public void render() {
		//Setup OpenGL
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.setupRemoveAlpha();
		
		OpenGLUtils.setupOrtho(-1, 1);
		
		this.button.render();
		this.checkBox.render();
		this.menu.render();
		this.list.render();
	}
	
	public static void main(String[] args) {
		new GUITest();
	}
	
	public void onMouseEnter(GUIComponent component) {
		Console.println("Your mouse entered the component with the name: " + component.name);
	}
	
	public void onMouseLeave(GUIComponent component) {
		Console.println("Your mouse left the component with the name: " + component.name);
	}
	
	public void onClicked(GUIComponent component) {
		Console.println("You clicked the component with the name: " + component.name);
	}
	
}