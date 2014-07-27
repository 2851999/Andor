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

import org.andor.core.Vector2D;

public class GUIDropDownMenu extends GUIComponent implements GUIComponentListener {
	
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
		this.buttons = new ArrayList<GUIButton>();
		this.menuOpen = false;
		//Add this component listener to the menu button
		this.menuButton.addListener(this);
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
	
	/* The methods used to add/set/toggle/return values */
	public void addButton(GUIButton button) {
		//Add this to the buttons component listeners
		button.addListener(this);
		//Add the button to the list of buttons
		this.buttons.add(button);
	}
	
	public void setOpen(boolean menuOpen) { this.menuOpen = menuOpen; }
	public void toggleOpen() { this.menuOpen = ! this.menuOpen; }
	public boolean isOpen() { return this.menuOpen; }
	public List<GUIButton> getButtons() { return this.getButtons(); }
	
}