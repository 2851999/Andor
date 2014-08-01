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

public class GUICheckBox extends GUIComponent {
	
	/* The boolean that represents whether this is checked or not */
	public boolean checked;
	
	/* The text */
	public String text;
	
	/* The constructor */
	public GUICheckBox(Colour[] colours, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(width, height, Colour.WHITE));
		//Set the values
		this.renderer.colours = colours;
		this.width = width;
		this.height = height;
		this.checked = false;
	}
	
	/* The constructor */
	public GUICheckBox(Image[] images, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(images[0], width, height, Colour.WHITE));
		//Set the values
		this.renderer.images = images;
		this.width = width;
		this.height = height;
		this.checked = false;
	}
	
	/* The constructor */
	public GUICheckBox(Image[] images, Colour[] colours, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(images[0], width, height, colours));
		//Set the values
		this.renderer.images = images;
		this.renderer.colours = colours;
		this.width = width;
		this.height = height;
		this.checked = false;
	}
	
	/* The method used to update this component */
	protected void updateComponent() {
		//Get the number of components to the images/colours
		int components = this.renderer.getTotalComponents();
		//Check to see the state of this button
		if (this.checked) {
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
	protected void renderComponent() {
		//Make sure there is text to be rendered
		if (this.text != null)
			//Render the text
			this.renderTextAtCenter(this.text, new Vector2D(((this.renderer.font.getWidth(this.text) / 2) + (this.width / 2)) + 4, 0));
	}
	
	/* The method called when the component is clicked */
	protected void componentOnClicked() {
		//Toggle checked
		this.checked = ! this.checked;
	}
	
	/* The methods used to set/toggle/return some values */
	public void setChecked(boolean checked) { this.checked = checked; }
	public void toggleChecked() { this.checked = ! this.checked; }
	public boolean isChecked() { return this.checked; }
	
}