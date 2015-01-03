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

public class GUIImage extends GUIComponent {
	
	/* The constructor */
	public GUIImage(Image image) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(image, image.getWidth(), image.getHeight(), Colour.WHITE));
		//Assign the variables
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.renderer.images = new Image[] { image };
		this.renderer.colours = new Colour[] { Colour.WHITE };
	}
	
	/* The constructor */
	public GUIImage(Image image, Colour colour) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(image, image.getWidth(), image.getHeight(), colour));
		//Assign the variables
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.renderer.images = new Image[] { image };
		this.renderer.colours = new Colour[] { colour };
	}
	
	/* The constructor */
	public GUIImage(Image image, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(image, width, height, Colour.WHITE));
		//Assign the variables
		this.width = width;
		this.height = height;
		this.renderer.images = new Image[] { image };
		this.renderer.colours = new Colour[] { Colour.WHITE };
	}
	
	/* The constructor */
	public GUIImage(Image image, Colour colour, float width, float height) {
		//Call the super constructor
		super(Object2DBuilder.createQuad(image, width, height, colour));
		//Assign the variables
		this.width = width;
		this.height = height;
		this.renderer.images = new Image[] { image };
		this.renderer.colours = new Colour[] { colour };
	}
	
}