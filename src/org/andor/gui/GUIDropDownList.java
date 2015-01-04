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
import org.andor.core.RenderableObject2D;

public class GUIDropDownList extends GUIDropDownMenu {
	
	/* The overlay */
	public RenderableObject2D overlay;
	
	/* The overlay image */
	public Image overlayImage;
	
	/* The constructor */
	public GUIDropDownList(GUIButton menuButton) {
		//Call the super constructor
		super(menuButton);
	}
	
	/* The constructor with the overlay image */
	public GUIDropDownList(GUIButton menuButton, Image overlayImage) {
		//Call the super constructor
		super(menuButton);
		//Setup the overlay
		this.setupOverlay(overlayImage);
	}
	
	/* The method used to setup the overlay */
	public void setupOverlay(Image overlayImage) {
		//Setup the overlay
		this.overlayImage = overlayImage;
		this.overlay = Object2DBuilder.createQuad(this.overlayImage, menuButton.width, menuButton.height, Colour.WHITE);
	}
	
	/* The method used to render this component */
	protected void renderComponent() {
		//Render the menu button if it is set
		if (this.menuButton != null)
			this.menuButton.render();
		//Render the overlay if it is set
		if (this.overlay != null) {
			this.overlay.position = this.menuButton.position;
			this.overlay.render();
		}
		//Make sure this menu is open
		if (this.menuOpen) {
			//Go through all of the buttons
			for (int a = 0; a < this.buttons.size(); a++)
				//Render the current button
				this.buttons.get(a).render();
		}
	}
	
	/* The method called when a gui component is clicked */
	public void onClicked(GUIComponent component) {
		//Make sure the component is not the menu button
		if (component != this.menuButton) {
			//Close the menu
			this.menuOpen = false;
			//Add the menu button to the list of buttons
			this.buttons.add(this.menuButton);
			//The new menu button
			GUIButton newMenuButton = null;
			//Look at all of the buttons
			for (int a = 0; a < this.buttons.size(); a++) {
				//Check the current component
				if (this.buttons.get(a).equals(component)) {
					//Set the new menu button
					newMenuButton = this.buttons.get(a);
					//Remove the button from the list
					this.buttons.remove(a);
					break;
				}
			}
			//Set the menu button
			this.menuButton = newMenuButton;
		} else {
			//Toggle the menu open
			this.menuOpen = ! this.menuOpen;
		}
	}
	
}