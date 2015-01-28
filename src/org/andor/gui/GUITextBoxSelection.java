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
import org.andor.core.Object2D;
import org.andor.core.Object2DBuilder;
import org.andor.core.Vector2D;

public class GUITextBoxSelection extends Object2D {
	
	/* The text box instance */
	public GUITextBox textBox;
	
	/* The renderer */
	public GUIComponentRenderer renderer;
	
	/* The colour and image */
	public Colour colour;
	public Image image;
	
	/* The default constructor */
	public GUITextBoxSelection() {
		
	}
	
	/* The constructor */
	public GUITextBoxSelection(GUITextBox textBox) {
		//Setup
		this.setup(textBox);
	}
	
	/* The method used to setup this */
	public void setup(GUITextBox textBox) {
		//Assign the variables
		this.textBox = textBox;
		this.width = 0;
		this.height = 0;
		this.renderer = new GUIComponentRenderer(Object2DBuilder.createQuad(this.width, this.height, Colour.WHITE));
		this.renderer.colours = new Colour[] { Colour.BLACK };
		//Set the colour and image
		if (this.colour != null)
			this.setColour(colour);
		if (this.image != null)
			this.setImage(image);
	}
	
	/* The methods used to set the colour/image and check whether they are set/return them */
	public void setColour(Colour colour) { this.renderer.colours = new Colour[] { colour }; }
	public void setImage(Image image) { this.renderer.images = new Image[] { image }; }
	public Colour getColour() { return this.renderer.colours[0]; }
	public Image getImage() { return this.renderer.images[0]; }
	public boolean hasColour() { return this.renderer.colours != null; }
	public boolean hasImage() { return this.renderer.images != null; }
	
	/* The method used to render this cursor */
	public void render() {
		//Check to see whether there is a selection
		if (this.textBox.isSelection) {
			//Get the position of the text box
			Vector2D p = this.textBox.getPosition();
			//Get the position and size of the selection
			float selectionX = 0;
			//Catch any errors
			try {
				if (this.textBox.selectionIndexStart < this.textBox.selectionIndexEnd)
					selectionX = p.x + this.textBox.renderer.font.getWidth(this.textBox.renderText.substring(0, this.textBox.selectionIndexStart - this.textBox.viewIndexStart));
				else
					selectionX = p.x + this.textBox.renderer.font.getWidth(this.textBox.renderText.substring(0, this.textBox.selectionIndexEnd - this.textBox.viewIndexStart));
			} catch (StringIndexOutOfBoundsException e) {
				
			}
			float selectionY = p.y;
			float selectionWidth = this.textBox.renderer.font.getWidth(this.textBox.getRenderTextSelection());
			float selectionHeight = this.textBox.height;
			//Update the renderer
			this.renderer.entity.renderer.updateVertices(Object2DBuilder.createQuadV(selectionWidth, selectionHeight));
			//Set the position
			this.position = new Vector2D(selectionX, selectionY);
			//Render the selection
			this.renderer.render(this, this.textBox.active);
		}
	}
	
}