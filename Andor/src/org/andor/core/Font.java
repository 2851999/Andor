/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

public class Font {
	
	/* The bitmap font */
	public BitmapText bitmapFont;
	
	/* The colour */
	public Colour colour;
	
	/* The constructor with the font */
	public Font(BitmapText bitmapFont) {
		//Assign the variables
		this.bitmapFont = bitmapFont;
		this.colour = Colour.WHITE;
	}
	
	/* The method used to render some text */
	public void render(String text, float x, float y) {
		//Bind the image
		this.bitmapFont.image.bind();
		//Set the position
		this.bitmapFont.position = new Vector2D(x, y);
		//Check the text
		if (this.bitmapFont.getCurrentText() == null || ! this.bitmapFont.getCurrentText().equals(text))
			this.bitmapFont.update(text);
		//Render the text
		this.bitmapFont.render();
	}
	
	public void renderAtCentre(String text, Object2D object) {
		this.renderAtCentre(text, object, new Vector2D(0, 0));
	}
	
	public void renderAtCentre(String text, Object2D object, Vector2D offset) {
		//Get the width and height of the text
		float textWidth = this.getWidth(text);
		float textHeight = this.getHeight(text);
		//Get the centre of the object
		Vector2D centre = object.getCentre();
		//Calculate the position to render the text
		float textX = centre.x - (textWidth / 2);
		float textY = centre.y - (textHeight / 2);
		//Add the offset
		textX += offset.x;
		textY += offset.y;
		//Render the text
		this.render(text, textX, textY);
	}
	
	/* The method used to get the width/height of some text */
	public float getWidth(String text) {
		return this.bitmapFont.getWidth(text);
	}
	
	public float getHeight(String text) {
		return this.bitmapFont.getHeight(text);
	}
	
}