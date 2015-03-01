/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/


package org.andor.core.android;

import org.andor.core.Colour;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class FontAndroid {
	
	/* The font */
	public Typeface typeface;
	
	/* The font size */
	public float fontSize;
	
	/* The colour */
	public Colour colour;
	
	/* The constructor of the font */
	public FontAndroid(Typeface typeface , Colour colour , float fontSize) {
		//Set the typeface
		this.typeface = typeface;
		
		//Set the colour and size
		this.colour = colour;
		this.fontSize = fontSize;
	}
	
	/* The method to get the width of a string */
	public float getWidth(String text, Paint paint) {
		Rect bounds = new Rect();
		paint.setTextSize((float) this.fontSize);
		paint.setTypeface(this.typeface);
		paint.getTextBounds(text, 0, text.length(), bounds);
		return bounds.width();
	}
	
	/* The method to get the height of a string */
	public float getHeight(String text, Paint paint) {
		Rect bounds = new Rect();
		paint.setTextSize((float) this.fontSize);
		paint.setTypeface(this.typeface);
		paint.getTextBounds(text, 0, text.length(), bounds);
		return bounds.height();
	}
	
}