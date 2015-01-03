/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.gui;

import org.andor.core.Colour;
import org.andor.core.Image;
import org.andor.core.Object2DBuilder;
import org.andor.core.Vector2D;
import org.andor.core.input.ControllerAxisEvent;
import org.andor.core.input.ControllerButtonEvent;
import org.andor.core.input.ControllerPovEvent;
import org.andor.core.input.Input;
import org.andor.core.input.InputListenerInterface;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.MouseEvent;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.input.ScrollEvent;

public class GUIWindow extends GUIComponent implements InputListenerInterface {
	
	/* The window's title */
	public String windowTitle;
	
	/* The top bar */
	public GUIFill topBar;
	
	/* The close button */
	private GUIButton closeButton;
	
	/* The boolean that states whether the mouse is currently pressed within the top bar */
	private boolean mousePressed;
	
	/* The constructor */
	public GUIWindow(String windowTitle, Colour colour, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(width, height, Colour.WHITE), width, height);
		//Assign the variables
		this.renderer.colours = new Colour[] { colour };
		this.windowTitle = windowTitle;
		this.topBar = new GUIFill(this);
		this.topBar.width = this.width;
		this.topBar.height = 20;
		this.mousePressed = false;
		Input.addListener(this);
	}
	
	/* The constructor */
	public GUIWindow(String windowTitle, Image image, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(width, height, Colour.WHITE), width, height);
		//Assign the variables
		this.renderer.colours = new Colour[] { Colour.WHITE };
		this.renderer.images = new Image[] { image };
		this.windowTitle = windowTitle;
		this.topBar = new GUIFill(this);
		this.topBar.width = this.width;
		this.topBar.height = 20;
		this.mousePressed = false;
		Input.addListener(this);
	}
	
	/* The constructor */
	public GUIWindow(String windowTitle, Colour colour, Image image, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(width, height, Colour.WHITE), width, height);
		//Assign the variables
		this.renderer.colours = new Colour[] { colour };
		this.renderer.images = new Image[] { image };
		this.windowTitle = windowTitle;
		this.topBar = new GUIFill(this);
		this.topBar.width = this.width;
		this.topBar.height = 20;
		this.mousePressed = false;
		Input.addListener(this);
	}
	
	/* The method called to update this component */
	protected void updateComponent() {
		//Update the close button
		if (this.closeButton != null) {
			this.closeButton.update();
			if (this.closeButton.clicked)
				this.visible = false;
		}
	}
	
	/* The method called to render this component */
	protected void renderComponent() {
		//Fill the top bar
		this.topBar.render();
		//Render the text
		this.renderTextAtCentre(this.windowTitle, new Vector2D(0, -(this.height / 2 - this.topBar.height / 2)));
		//Render the close button
		if (this.closeButton != null)
			this.closeButton.render();
	}
	
	/* The method called when a button on the mouse is pressed */
	public void onMousePressed(MouseEvent e) {
		//Make sure this is visible and active
		if (this.visible && this.active) {
			if (this.topBar.getBounds().contains(e.x, e.y))
				this.mousePressed = true;
		}
	}
	
	/* The method called when a button on the mouse is released */
	public void onMouseReleased(MouseEvent e) {
		//Make sure this is visible and active
		if (this.visible && this.active)
			this.mousePressed = false;
	}
	
	/* The method called when a button on the mouse is clicked */
	public void onMouseClicked(MouseEvent e) { }
	
	/* The method called when the mouse moves */
	public void onMouseMoved(MouseMotionEvent e) { }
	
	/* The method called when the mouse is dragged */
	public void onMouseDragged(MouseMotionEvent e) {
		//Make sure this is visible and active
		if (this.visible && this.active) {
			if (this.topBar.getBounds().contains(e.startX, e.startY) && this.mousePressed)
				this.position.add(new Vector2D(e.dx, e.dy));
		}
	}
	
	/* The method called when a key on the keyboard is pressed */
	public void onKeyPressed(KeyboardEvent e) { }
	
	/* The method called when a key on the keyboard is released */
	public void onKeyReleased(KeyboardEvent e) { }
	
	/* The method called when a key on the keyboard is typed */
	public void onKeyTyped(KeyboardEvent e) { }
	
	/* The method called when the mouse scrolls */
	public void onScroll(ScrollEvent e) { }
	
	/* The method called when an axis changes */
	public void onAxisChange(ControllerAxisEvent e) { }
	
	/* The method called when a button is pressed */
	public void onButtonPressed(ControllerButtonEvent e) { }
	
	/* The method called when a button is released */
	public void onButtonReleased(ControllerButtonEvent e) { }
	
	/* The method called when the pov is changed */
	public void onPovChange(ControllerPovEvent e) { }
	
	public void setCloseButton(GUIButton closeButton) {
		this.closeButton = closeButton;
		this.closeButton.position = new Vector2D(this.width - closeButton.width, 0);
		this.link(closeButton);
	}
	
}