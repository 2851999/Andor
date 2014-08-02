/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.gui;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.Rectangle;
import org.andor.core.Vector2D;
import org.andor.core.input.Input;
import org.andor.core.input.InputListenerInterface;
import org.andor.core.input.KeyboardEvent;
import org.andor.core.input.MouseEvent;
import org.andor.core.input.MouseMotionEvent;
import org.andor.core.input.ScrollEvent;

public class GUIDropDownMenu extends GUIComponent implements GUIComponentListener, InputListenerInterface {
	
	/* The menu button */
	public GUIButton menuButton;
	
	/* The other buttons in this drop down menu */
	protected List<GUIButton> buttons;
	
	/* The boolean that represents whether this menu is open */
	public boolean menuOpen;
	
	/* The constructor */
	public GUIDropDownMenu(GUIButton menuButton) {
		//Call the super constructor
		super(null);
		//Assign the variables
		this.menuButton = menuButton;
		this.width = this.menuButton.width;
		this.height = this.menuButton.height;
		this.buttons = new ArrayList<GUIButton>();
		this.menuOpen = false;
		//Add this component listener to the menu button
		this.menuButton.addListener(this);
		//Add this input listener interface
		Input.addListener(this);
	}
	
	/* The method used to update this component */
	protected void updateComponent() {
		//Update the menu button
		this.menuButton.update();
		//Set the menu buttons visible value
		this.menuButton.visible = this.visible;
		//Set the menu buttons active value
		this.menuButton.active = this.active;
		//Set the position of the menu button
		this.menuButton.position = this.position;
		//Set the border enabled
		this.borderEnabled = this.menuOpen;
		//The current y position
		float currentY = this.position.y + this.menuButton.height;
		//Set all of the components visible and active values
		for (int a = 0; a < this.buttons.size(); a++) {
			this.buttons.get(a).visible = this.visible && this.menuOpen;
			this.buttons.get(a).active = this.active && this.menuOpen;
			//Set the current button's position
			this.buttons.get(a).position = new Vector2D(this.position.x, currentY);
			//Update the current button
			this.buttons.get(a).update();
			//Add onto the current y position
			currentY += this.buttons.get(a).height;
		}
	}
	
	/* The method used to render this component */
	protected void renderComponent() {
		//Render the menu button if it is set
		if (this.menuButton != null)
			this.menuButton.render();
		//Make sure this menu is open
		if (this.menuOpen) {
			//Go through all of the buttons
			for (int a = 0; a < this.buttons.size(); a++)
				//Render the current button
				this.buttons.get(a).render();
		}
	}
	
	/* The method called when the mouse enters a gui component */
	public void onMouseEnter(GUIComponent component) {
		
	}
	
	/* The method called when the mouse exits a gui component */
	public void onMouseLeave(GUIComponent component) {
		
	}
	
	/* The method called when a gui component is clicked */
	public void onClicked(GUIComponent component) {
		//Toggle menu open
		this.menuOpen = ! this.menuOpen;
	}
	
	/* The method called when a button on the mouse is pressed */
	public void onMousePressed(MouseEvent e) { }
	
	/* The method called when a button on the mouse is released */
	public void onMouseReleased(MouseEvent e) { }
	
	/* The method called when a button on the mouse is clicked */
	public void onMouseClicked(MouseEvent e) {
		//Check to see whether the mouse was clicked within this menu
		if (! this.getMenuBounds().contains(e.x, e.y) && this.menuOpen)
			//Hide this menu
			this.menuOpen = false;
	}
	
	/* The method called when the mouse moves */
	public void onMouseMoved(MouseMotionEvent e) { }
	
	/* The method called when the mouse is dragged */
	public void onMouseDragged(MouseMotionEvent e) { }
	
	/* The method called when a key on the keyboard is pressed */
	public void onKeyPressed(KeyboardEvent e) { }
	
	/* The method called when a key on the keyboard is released */
	public void onKeyReleased(KeyboardEvent e) { }
	
	/* The method called when a key on the keyboard is typed */
	public void onKeyTyped(KeyboardEvent e) { }
	
	/* The method called when the mouse scrolls */
	public void onScroll(ScrollEvent e) { }
	
	/* The methods used to add/set/toggle/return values */
	public void addButton(GUIButton button) {
		//Add this to the buttons component listeners
		button.addListener(this);
		//Add the button to the list of buttons
		this.buttons.add(button);
		//Get the new bounds of this menu
		Rectangle bounds = this.getMenuBounds();
		//Set the width and height
		this.width = bounds.width;
		this.height = bounds.height;
	}
	
	public void setOpen(boolean menuOpen) { this.menuOpen = menuOpen; }
	public void toggleOpen() { this.menuOpen = ! this.menuOpen; }
	public boolean isOpen() { return this.menuOpen; }
	public List<GUIButton> getButtons() { return this.getButtons(); }
	
	public Rectangle getMenuBounds() {
		//The positions
		float x = this.position.x;
		float y = this.position.y;
		//Get the maximum width and total height
		float width = this.menuButton.width;
		float height = this.menuButton.height;
		for (int a = 0; a < this.buttons.size(); a++) {
			//The current button
			GUIButton button = this.buttons.get(a);
			//Add onto the height
			height += button.height;
			//Check the width of the current button
			if (button.width > width)
				this.width = button.width;
		}
		//Return the rectangle
		return new Rectangle(x, y, width, height);
	}
	
}