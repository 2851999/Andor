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
import org.andor.core.Object2D;
import org.andor.core.Object2DBuilder;

public class GUIFill extends Object2D {
	
	/* The component */
	public GUIComponent component;
	
	/* The renderer */
	public GUIComponentRenderer renderer;
	
	/* The colour of this fill */
	public Colour colour;
	
	/* The image of this fill */
	public Image image;
	
	/* The default constructor */
	public GUIFill() {
		
	}
	
	/* The constructor */
	public GUIFill(GUIComponent component) {
		//Setup this fill
		this.setup(component, null);
	}
	
	/* The constructor */
	public GUIFill(GUIComponent component, Colour colour) {
		//Setup this fill
		this.setup(component, null);
		this.colour = colour;
	}
	
	/* The constructor */
	public GUIFill(GUIComponent component, Image image) {
		//Setup this fill
		this.setup(component, image);
		//Set the image
		this.image = image;
	}
	
	/* The method used to setup this fill */
	public void setup(GUIComponent component, Image image) {
		//Assign the variables
		this.component = component;
		this.component.link(this);
		if (image != null)
			this.renderer = new GUIComponentRenderer(Object2DBuilder.createQuad(image, this.component.width, this.component.height, Colour.WHITE));
		else
			this.renderer = new GUIComponentRenderer(Object2DBuilder.createQuad(this.component.width, this.component.height, Colour.WHITE));
		this.colour = Colour.WHITE;
	}
	
	/* The method used to update this fill */
	public void update() {
		
	}
	
	/* The method used to fill this boarder */
	public void render() {
		//Make sure this fill has been setup
		if (this.renderer != null) {
			this.renderer.colours = new Colour[] { this.colour };
			if (this.image != null)
				this.renderer.images = new Image[] { this.image };
			this.renderer.entity.renderer.updateVertices(Object2DBuilder.createQuadV(this.width, this.height));
			this.renderer.render(this, this.component.active);
		}
	}
	
}