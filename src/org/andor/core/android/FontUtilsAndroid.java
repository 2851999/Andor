/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.android;

import org.andor.core.Colour;
import org.andor.utils.TrueTypeFont;

import android.graphics.Typeface;

public class FontUtilsAndroid {
	
	/* The static method to create a font */
	public static Typeface createFont(String font) {
		return Typeface.create(font , Typeface.NORMAL);
	}
	
	/* The static method to create a GUIFont */
	public static TrueTypeFont createFont(String font, Colour colour, float size) {
		return createFont(createFont(font) , colour , size);
	}
	
	/* The static method to create a GUIFont */
	public static TrueTypeFont createFont(Typeface font, Colour colour, float size) {
		return new TrueTypeFont(new FontAndroid(font, colour, size));
	}
	
	/* The static method to create a font from a file */
	public static TrueTypeFont createFont(String path, boolean external, Colour colour, float size) {
		return createFont(createFont(path, external), colour, size);
	}
	
	/* The static method to create a font from a file */
	public static Typeface createFont(String path, boolean external) {
		//Default font
		Typeface typeface = createFont("Arial");
		
		//Check the external value
		if (external)
			//Create the typeface
			typeface = Typeface.createFromFile(path);
		else {
			if (path.startsWith("/"))
				path = path.substring(1);
			//Make sure the font file is in at least the assets folder assets/fonts/NAME.TTF
			//Create the typeface
			typeface = Typeface.createFromAsset(BaseActivity.instance.getAssets(), path);
		}
		
		//Return the font
		return typeface;
	}
	
}