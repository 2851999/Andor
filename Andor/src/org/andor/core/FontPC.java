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

public class FontPC {
	
	/* The font */
	public TrueTypeFont font;
	
	/* The font instance */
	public Font instance;
	
	/* The constructor with the font */
	public FontPC(TrueTypeFont font) {
		//Assign the variables
		this.font = font;
	}
	
	/* The method used to render some text */
	public void render(String text, float x, float y) {
		GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
		//Enable 2D textures
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		//Render the text
		this.font.drawString(x, y, text, new Color(this.instance.colour.r, this.instance.colour.g, this.instance.colour.b, this.instance.colour.a));
		TextureImpl.unbind();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glPopAttrib();
	}
	
	/* The method used to get the width/height of some text */
	public float getWidth(String text) {
		return this.font.getWidth(text);
	}
	
	public float getHeight(String text) {
		return this.font.getHeight(text);
	}
	
}