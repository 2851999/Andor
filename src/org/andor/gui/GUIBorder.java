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

public class GUIBorder extends Object2D {
	
	/* The component */
	public GUIComponent component;
	
	/* The renderer */
	public GUIComponentRenderer renderer;
	
	/* The thickness */
	private float thickness;
	
	/* The colours */
	private Colour[] colours;
	
	/* The images */
	private Image[] images;
	
	/* The default constructor */
	public GUIBorder() {
		
	}
	
	/* The constructor */
	public GUIBorder(GUIComponent component, float thickness) {
		//Assign the variables
		this.thickness = thickness;
		//Setup this border
		this.setup(component);
		//Set the colour to white
		this.colours = new Colour[] { Colour.WHITE };
	}
	
	/* The constructor */
	public GUIBorder(float thickness, Colour colour) {
		//Assign the variables
		this.thickness = thickness;
		//Set the colours
		this.colours = new Colour[] { colour };
	}
	
	/* The constructor */
	public GUIBorder(float thickness, Image image) {
		//Assign the variables
		this.thickness = thickness;
		//Set the images
		this.images = new Image[] { image };
	}
	
	/* The constructor */
	public GUIBorder(GUIComponent component, float thickness, Colour colour) {
		//Assign the variables
		this.thickness = thickness;
		//Setup this border
		this.setup(component);
		//Set the colours
		this.colours = new Colour[] { colour };
	}
	
	/* The constructor */
	public GUIBorder(GUIComponent component, float thickness, Image image) {
		//Assign the variables
		this.thickness = thickness;
		//Setup this border
		this.setup(component);
		//Set the images
		this.images = new Image[] { image };
	}
	
	/* The constructor */
	public GUIBorder(GUIComponent component, float thickness, Colour[] colours) {
		//Assign the variables
		this.thickness = thickness;
		//Setup this border
		this.setup(component);
		//Set the colours
		this.colours = colours;
	}
	
	/* The constructor */
	public GUIBorder(GUIComponent component, float thickness, Image[] images) {
		//Assign the variables
		this.thickness = thickness;
		//Setup this border
		this.setup(component);
		//Set the images
		this.images = images;
	}
	
	/* The method used to setup this boarder */
	public void setup(GUIComponent component) {
		//Assign the variables
		this.component = component;
		this.renderer = new GUIComponentRenderer(Object2DBuilder.createQuad(this.component.width + (this.thickness * 2), this.component.height + (this.thickness * 2), Colour.WHITE));
		this.position = new Vector2D(-this.thickness, -this.thickness);
		this.component.link(this);
		if (this.colours != null)
			this.renderer.colours = this.colours;
		else if (this.images != null)
			this.renderer.images = this.images;
	}
	
	/* The method used to render this boarder */
	public void render() {
		//Make sure this boarder has been setup
		if (this.renderer != null)
			this.renderer.render(this, this.component.active);
	}
	
}