/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.TextureImpl;

public class Font {
	
	/* The font */
	public TrueTypeFont font;
	
	/* The colour */
	public Colour colour;
	
	/* The constructor with the font */
	public Font(TrueTypeFont font) {
		//Assign the variables
		this.font = font;
		this.colour = Colour.WHITE;
	}
	
	/* The constructor with the font and colour given */
	public Font(TrueTypeFont font, Colour colour) {
		//Assign the variables
		this.font = font;
		this.colour = colour;
	}
	
	/* The method used to render some text */
	public void render(String text, float x, float y) {
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		//Enable 2D textures
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		//Render the font
		this.font.drawString(x, y, text, new Color(this.colour.r, this.colour.g, this.colour.b, this.colour.a));
		TextureImpl.unbind();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopAttrib();
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
		return this.font.getWidth(text);
	}
	
	public float getHeight(String text) {
		return this.font.getHeight();
	}
	
}