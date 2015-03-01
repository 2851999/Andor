/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.gui;

import org.andor.core.Font;

public class GUILabel extends GUIComponent {
	
	/* The text */
	private String text;
	
	/* The constructor */
	public GUILabel(String text) {
		//Call the super constructor
		super(null);
		//Assign the variables
		this.text = text;
		this.width = this.renderer.font.getWidth(text);
		this.height = this.renderer.font.getHeight(text);
	}
	
	/* The constructor */
	public GUILabel(String text, Font font) {
		//Call the super constructor
		super(null);
		//Assign the variables
		this.text = text;
		this.renderer.font = font;
		this.width = font.getWidth(text);
		this.height = font.getHeight(text);
	}
	
	/* The method used to render this component */
	protected void renderComponent() {
		//Render the text
		this.renderText(this.text);
	}
	
	/* The methods used to set/return values */
	public void setText(String text) {
		this.text = text;
		this.width = this.renderer.font.getWidth(text);
		this.height = this.renderer.font.getHeight(text);
	}
	public void setFont(Font font) {
		this.renderer.font = font;
		this.width = font.getWidth(text);
		this.height = font.getHeight(text);
	}
	public String getText() { return this.text; }
	
}