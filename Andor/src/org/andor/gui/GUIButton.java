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

public class GUIButton extends GUIComponent {
	
	/* The text */
	public String text;
	
	/* The constructor */
	public GUIButton(Colour[] colours, float width, float height) {
		//Call the super constructor
		//If using a colour, the colour must be used to colour VBO setup properly (Same for images)
		super(Object2DBuilder.createQuad(width, height, Colour.WHITE));
		//Set the values
		this.renderer.colours = colours;
		this.width = width;
		this.height = height;
	}
	
	/* The constructor */
	public GUIButton(Image[] images, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(images[0], width, height, Colour.WHITE));
		//Set the values
		this.renderer.images = images;
		this.width = width;
		this.height = height;
	}
	
	/* The constructor */
	public GUIButton(Image[] images, Colour[] colours, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(images[0], width, height, colours));
		//Set the values
		this.renderer.images = images;
		this.renderer.colours = colours;
		this.width = width;
		this.height = height;
	}
	
	/* The method used to update this component */
	public void updateComponent() {
		//Get the number of components to the images/colours
		int components = this.renderer.getTotalComponents();
		//Check to see the state of this button
		if (this.clicked) {
			if (components == 1)
				this.renderer.componentIndex = 0;
			else if (components == 2)
				this.renderer.componentIndex = 1;
			else if (components == 3)
				this.renderer.componentIndex = 2;
		} else if (this.mouseHoveringInside) {
			if (components == 1)
				this.renderer.componentIndex = 0;
			else if (components == 2)
				this.renderer.componentIndex = 1;
			else if (components == 3)
				this.renderer.componentIndex = 1;
		} else {
			if (components == 1)
				this.renderer.componentIndex = 0;
			else if (components == 2)
				this.renderer.componentIndex = 0;
			else if (components == 3)
				this.renderer.componentIndex = 0;
		}
	}
	
	/* The method used to render this component */
	public void renderComponent() {
		//Render the text
		this.renderTextAtCenter(this.text);
	}
	
	/* The methods used to set/return values */
	public void setText(String text) { this.text = text; }
	public String getText() { return this.text; }
	
}