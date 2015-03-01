package org.andor.utils;

import org.andor.core.android.FontAndroid;

public class TrueTypeFont {
	
	/* The font instances */
	public FontPC pcFont;
	public FontAndroid androidFont;
	
	/* The constructor */
	public TrueTypeFont(FontPC font) {
		//Assign the font
		this.pcFont = font;
	}
	
	/* The constructor */
	public TrueTypeFont(FontAndroid font) {
		//Assign the font
		this.androidFont = font;
	}
	
}