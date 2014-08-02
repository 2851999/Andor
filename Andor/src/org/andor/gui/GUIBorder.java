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
import org.andor.core.Vector2D;

public class GUIBorder extends Object2D {
	
	/* The component */
	public GUIComponent component;
	
	/* The renderer */
	public GUIComponentRenderer renderer;
	
	/* The default constructor */
	public GUIBorder() {
		
	}
	
	/* The constructor */
	public GUIBorder(GUIComponent component, float thickness) {
		//Setup this boarder
		this.setup(component, thickness);
		//Set the colour to black
		this.renderer.colours = new Colour[] { Colour.WHITE };
	}
	
	/* The constructor */
	public GUIBorder(GUIComponent component, float thickness, Colour[] colours) {
		//Setup this boarder
		this.setup(component, thickness);
		//Set the colours
		this.renderer.colours = colours;
	}
	
	/* The constructor */
	public GUIBorder(GUIComponent component, float thickness, Image[] images) {
		//Setup this boarder
		this.setup(component, thickness);
		//Set the colours
		this.renderer.images = images;
	}
	
	/* The method used to setup this boarder */
	public void setup(GUIComponent component, float thickness) {
		//Assign the variables
		this.component = component;
		this.renderer = new GUIComponentRenderer(Object2DBuilder.createQuad(this.component.width + (thickness * 2), this.component.height + (thickness * 2), Colour.WHITE));
		this.position = new Vector2D(-thickness, -thickness);
		this.component.link(this);
	}
	
	/* The method used to render this boarder */
	public void render() {
		//Make sure this boarder has been setup
		if (this.renderer != null)
			this.renderer.render(this, this.component.active);
	}
	
}