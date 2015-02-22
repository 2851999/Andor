package org.andor.utils;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import org.andor.core.Colour;

public class FontPC {
	
	/* The font */
	public Font font;
	
	/* The font size */
	public float fontSize;
	
	/* The colour */
	public Colour colour;
	
	/* The constructor of the font */
	public FontPC(Font font, Colour colour, float fontSize) {
		//Set the font
		this.font = font;
		
		//Set the colour and size
		this.colour = colour;
		this.fontSize = fontSize;
	}
	
	/* The method to get the width of a string */
	public float getWidth(String text, Graphics2D g2d) {
		g2d.setFont(font.deriveFont((float) fontSize));
		FontMetrics metrics = g2d.getFontMetrics(font.deriveFont((float) fontSize));
		return (float) metrics.getStringBounds(text , g2d).getWidth();
	}
	
	/* The method to get the height of a string */
	public float getHeight(String text, Graphics2D g2d) {
		g2d.setFont(font.deriveFont((float) fontSize));
		FontMetrics metrics = g2d.getFontMetrics(font.deriveFont((float) fontSize));
		return (float) metrics.getStringBounds(text , g2d).getHeight();
	}
	
}