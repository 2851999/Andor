/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.gui;

import org.andor.core.Colour;
import org.andor.core.Image;
import org.andor.core.Object2DBuilder;
import org.andor.core.Settings;
import org.andor.core.Vector2D;
import org.andor.core.input.ControllerAxisEvent;
import org.andor.core.input.ControllerButtonEvent;
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
	public GUIButton closeButton;
	
	/* The boolean that states whether the mouse is currently pressed within the top bar */
	private boolean mousePressed;
	
	/* The constructor */
	public GUIWindow(String windowTitle, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(width, height, Colour.WHITE), width, height);
		//Assign the variables
		this.windowTitle = windowTitle;
		this.topBar = new GUIFill(this);
		this.topBar.width = this.width;
		this.topBar.height = 20;
		this.mousePressed = false;
		Input.addListener(this);
	}
	
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
	
	/* The method used to centre this window */
	public void centre() {
		//Place this window into the centre of the window
		this.setPosition(new Vector2D((Settings.Window.Width / 2) - (this.width / 2), (Settings.Window.Height / 2) - (this.height / 2)));
	}
	
	/* The method used to assign the colour */
	public void setColour(Colour colour) { this.renderer.colours = new Colour[] { colour }; }
	
	/* The method used to assign the image */
	public void setImage(Image image) { this.renderer.images = new Image[] { image }; }
	
	public Colour getColour() { return this.renderer.colours[0]; }
	public Image getImage() { return this.renderer.images[0]; }
	public boolean hasColour() { return this.renderer.colours != null; }
	public boolean hasImage() { return this.renderer.images != null; }
	
	/* The method used to assign the top bar colour */
	public void setTopColour(Colour colour) { this.topBar.colour = colour; }
	
	/* The method used to assign the image */
	public void setTopImage(Image image) { this.topBar.image = image; }
	
	public Colour getTopColour() { return this.topBar.colour; }
	public Image getTopImage() { return this.topBar.image; }
	public boolean hasTopColour() { return this.topBar.colour != null; }
	public boolean hasTopImage() { return this.topBar.image != null; }
	
	/* The method called to update this component */
	protected void updateComponent() {
		//Update the close button
		if (this.closeButton != null) {
			this.closeButton.update();
			if (this.closeButton.clicked)
				this.close();
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
	
	/* The method used to close this window */
	public void close() {
		this.visible = false;
		this.active = false;
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
	
	public void setCloseButton(GUIButton closeButton) {
		this.closeButton = closeButton;
		this.closeButton.position = new Vector2D(this.width - closeButton.width, 0);
		this.link(closeButton);
	}
	
}