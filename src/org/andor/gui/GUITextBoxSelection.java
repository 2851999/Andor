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
	
	/* The default constructor */
	public GUITextBoxSelection() {
		
	}
	
	/* The constructor */
	public GUITextBoxSelection(GUITextBox textBox) {
		//Assign the variables
		this.textBox = textBox;
		this.width = 0;
		this.height = 0;
		this.renderer = new GUIComponentRenderer(Object2DBuilder.createQuad(this.width, this.height, Colour.WHITE));
		this.renderer.colours = new Colour[] { Colour.BLACK };
	}
	
	/* The method used to setup this */
	public void setup(GUITextBox textBox) {
		//Assign the variables
		this.textBox = textBox;
	}
	
	/* The method used to set the colour */
	public void setColour(Colour colour) { this.renderer.colours = new Colour[] { colour }; }
	
	/* The method used to set the image */
	public void setImage(Image image) { this.renderer.images = new Image[] { image }; }
	
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