package org.andor.tests;

import org.andor.core.BaseGame;
import org.andor.core.Colour;
import org.andor.core.Vector2D;
import org.andor.gui.GUIBorder;
import org.andor.gui.GUIButton;
import org.andor.gui.GUICheckBox;
import org.andor.gui.GUIComponent;
import org.andor.gui.GUIComponentListener;
import org.andor.gui.GUIDropDownList;
import org.andor.gui.GUIDropDownMenu;
import org.andor.gui.GUIGroup;
import org.andor.gui.GUILabel;
import org.andor.gui.GUIRadioCheckBox;
import org.andor.gui.GUITextBox;
import org.andor.utils.Console;
import org.andor.utils.FontUtils;
import org.andor.utils.OpenGLUtils;

public class CopyOfGUITest extends BaseGame implements GUIComponentListener {
	
	public GUIButton button;
	public GUICheckBox checkBox;
	public GUIDropDownMenu menu;
	public GUIDropDownList list;
	public GUIGroup radio;
	public GUIRadioCheckBox radio1;
	public GUIRadioCheckBox radio2;
	public GUIRadioCheckBox radio3;
	public GUILabel label;
	public GUITextBox textBox;
	
	public CopyOfGUITest() {
		
	}
	
	/* The method called when the loop has been created */
	public void create() {
		this.button = new GUIButton(new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 100, 20);
		this.button.text = "Click Me";
		this.button.name = "Button";
		this.button.position = new Vector2D(20, 100);
		this.button.addListener(this);
		
		this.checkBox = new GUICheckBox(new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 20, 20);
		this.checkBox.name = "Checkbox";
		this.checkBox.position = new Vector2D(140, 100);
		this.checkBox.addListener(this);
		
		this.menu = new GUIDropDownMenu(new GUIButton("File", new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 100, 20));
		this.menu.addButton(new GUIButton("Save", new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 100, 20));
		this.menu.addButton(new GUIButton("Save As", new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 100, 20));
		this.menu.position = new Vector2D(180, 100);
		
		this.list = new GUIDropDownList(new GUIButton("800 x 600", new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 100, 20));
		this.list.addButton(new GUIButton("1024 x 720", new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 100, 20));
		this.list.addButton(new GUIButton("1920 x 1080", new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 100, 20));
		this.list.position = new Vector2D(300, 100);
		
		this.radio = new GUIGroup("RadioButtons");
		this.radio.position = new Vector2D(20, 200);
		
		this.radio1 = new GUIRadioCheckBox(new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 20, 20);
		this.radio1.text = "Option 1";
		this.radio1.position = new Vector2D(0, 0);
		this.radio2 = new GUIRadioCheckBox(new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 20, 20);
		this.radio2.text = "Option 2";
		this.radio2.position = new Vector2D(0, 30);
		this.radio3 = new GUIRadioCheckBox(new Colour[] { Colour.LIGHT_BLUE, Colour.BLUE, Colour.RED }, 20, 20);
		this.radio3.text = "Option 3";
		this.radio3.position = new Vector2D(0, 60);
		
		this.radio.add(this.radio1);
		this.radio.add(this.radio2);
		this.radio.add(this.radio3);
		
		this.label = new GUILabel("This is a label");
		this.label.position = new Vector2D(0, 0);
		
		this.radio.border = new GUIBorder(this.radio, 1f, new Colour[] { Colour.RED });
		
		this.textBox = new GUITextBox(Colour.WHITE, 100, 20);
		this.textBox.position = new Vector2D(20, 300);
		this.textBox.renderer.font.colour = Colour.BLACK;
		this.textBox.defaultText = "Enter something";
		this.textBox.defaultTextFont = FontUtils.createFont("Arial", 12, Colour.LIGHT_GREY);
		this.textBox.border = new GUIBorder(this.textBox, 1f, new Colour[] { Colour.LIGHT_BLUE });
		this.textBox.selection.renderer.colours = new Colour[] { new Colour(Colour.LIGHT_BLUE, 0.4f) };
	}
	
	/* The method called when the game loop is updated */
	public void update() {
		this.button.update();
		this.checkBox.update();
		this.menu.update();
		this.list.update();
		this.radio.update();
		this.label.update();
		this.textBox.update();
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
		this.radio.render();
		this.label.render();
		this.textBox.render();
	}
	
	public static void main(String[] args) {
		new CopyOfGUITest();
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