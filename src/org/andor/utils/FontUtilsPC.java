/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.andor.core.Colour;
import org.andor.core.logger.Logger;

import android.util.Log;

public class FontUtilsPC {
	
	/* The static method to create a font */
	public static Font createFont(String font, float size) {
		return new Font(font , Font.PLAIN , (int) size);
	}
	
	/* The static method to create a GUIFont */
	public static TrueTypeFont createFont(String font, Colour colour, float size) {
		return createFont(createFont(font , size) , colour , size);
	}
	
	/* The static method to create a GUIFont */
	public static TrueTypeFont createFont(Font font, Colour colour, float size) {
		return new TrueTypeFont(new FontPC(font, colour, size));
	}
	
	/* The static method to create a font from a file */
	public static TrueTypeFont createFont(String path, boolean external, Colour colour, float size) {
		return createFont(createFont(path, external), colour, size);
	}
	
	/* The static method to create a font from a file */
	public static Font createFont(String path, boolean external) {
		//Default font
		Font font = createFont("Arial" , 12);
		try {
			//Check to see where the font is
			if (external)
				//Load font from file path
				font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(path));
			else
				//Load font from file path
				font = Font.createFont(Font.TRUETYPE_FONT, FileUtils.class.getResourceAsStream(path));
		} catch (FileNotFoundException e) {
			Logger.log("FontUtils getFont()", "File not found: " + path, Log.ERROR);
			e.printStackTrace();
		} catch (FontFormatException e) {
			Logger.log("FontUtils getFont()", "Font format exception: " + path, Log.ERROR);
			e.printStackTrace();
		} catch (IOException e) {
			Logger.log("FontUtils getFont()", "IOException: " + path, Log.ERROR);
			e.printStackTrace();
		}
		return font;
	}
	
}