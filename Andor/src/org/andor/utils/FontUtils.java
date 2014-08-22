/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.andor.core.Colour;
import org.andor.core.Font;
import org.andor.core.FontPC;
import org.andor.core.Settings;
import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.newdawn.slick.TrueTypeFont;

public class FontUtils {
	
	/* The different font types */
	public static final int FONT_TYPE_PLAIN = 1;
	public static final int FONT_TYPE_BOLD = 2;
	public static final int FONT_TYPE_ITALIC = 3;
	
	/* The static method used to create a font */
	public static Font createFont(java.awt.Font font) {
		Font f = null;
		FontPC fpc = new FontPC(new TrueTypeFont(font, Settings.Video.AntiAliasing));
		f = new Font(fpc);
		fpc.instance = f;
		return f;
	}
	
	/* The static method used to create a font given its name, font type and size */
	public static Font createFont(String font, int fontType, float size) {
		Font f = null;
		FontPC fpc = new FontPC(createTrueTypeFont(new java.awt.Font(font, getFontType(fontType), (int) size)));
		f = new Font(fpc);
		fpc.instance = f;
		return f;
	}
	
	/* The static method used to create a font given its name and size */
	public static Font createFont(String font, float size) {
		Font f = null;
		FontPC fpc = new FontPC(createTrueTypeFont(font, FONT_TYPE_PLAIN, size));
		f = new Font(fpc);
		fpc.instance = f;
		return f;
	}
	
	/* The static method used to load a font from a file given the size*/
	public static Font load(String fontPath, boolean external, float size) {
		Font f = null;
		FontPC fpc = new FontPC(loadTrueTypeFont(fontPath, external, FONT_TYPE_PLAIN, size));
		f = new Font(fpc);
		fpc.instance = f;
		return f;
	}
	
	/* The static method used to load a font from a file given the font type and size */
	public static Font load(String fontPath, boolean external, int fontType, float size) {
		Font f = null;
		FontPC fpc = new FontPC(loadTrueTypeFont(fontPath, external, fontType, size));
		f = new Font(fpc);
		fpc.instance = f;
		return f;
	}
	
	/* The static method used to create a font given its colour */
	public static Font createFont(java.awt.Font font, Colour colour) {
		Font f = null;
		FontPC fpc = new FontPC(new TrueTypeFont(font, Settings.Video.AntiAliasing));
		f = new Font(fpc);
		fpc.instance = f;
		return f;
	}
	
	/* The static method used to create a font given its name, font type, size and colour */
	public static Font createFont(String font, int fontType, float size, Colour colour) {
		Font f = null;
		FontPC fpc = new FontPC(createTrueTypeFont(new java.awt.Font(font, getFontType(fontType), (int) size)));
		f = new Font(fpc, colour);
		fpc.instance = f;
		return f;
	}
	
	/* The static method used to create a font given its name, size and colour */
	public static Font createFont(String font, float size, Colour colour) {
		Font f = null;
		FontPC fpc = new FontPC(createTrueTypeFont(font, FONT_TYPE_PLAIN, size));
		f = new Font(fpc, colour);
		fpc.instance = f;
		return f;
	}
	
	/* The static method used to load a font from a file given the size and colour */
	public static Font load(String fontPath, boolean external, float size, Colour colour) {
		Font f = null;
		FontPC fpc = new FontPC(loadTrueTypeFont(fontPath, external, FONT_TYPE_PLAIN, size));
		f = new Font(fpc, colour);
		fpc.instance = f;
		return f;
	}
	
	/* The static method used to load a font from a file given the font type, size and colour */
	public static Font load(String fontPath, boolean external, int fontType, float size, Colour colour) {
		Font f = null;
		FontPC fpc = new FontPC(loadTrueTypeFont(fontPath, external, fontType, size));
		f = new Font(fpc, colour);
		fpc.instance = f;
		return f;
	}
	
	/* The static method used to create a font */
	public static TrueTypeFont createTrueTypeFont(java.awt.Font font) {
		return new TrueTypeFont(font, Settings.Video.AntiAliasing);
	}
	
	/* The static method used to create a font given its name, font type and size */
	public static TrueTypeFont createTrueTypeFont(String font, int fontType, float size) {
		return createTrueTypeFont(new java.awt.Font(font, getFontType(fontType), (int) size));
	}
	
	/* The static method used to create a font given its name and size */
	public static TrueTypeFont createTrueTypeFont(String font, float size) {
		return createTrueTypeFont(font, FONT_TYPE_PLAIN, size);
	}
	
	/* The static method used to load a font from a file given the size */
	public static TrueTypeFont loadTrueTypeFont(String fontPath, boolean external, float size) {
		return loadTrueTypeFont(fontPath, external, FONT_TYPE_PLAIN, size);
	}
	
	/* The static method used to load a font from a file given the font type and size */
	public static TrueTypeFont loadTrueTypeFont(String fontPath, boolean external, int fontType, float size) {
		//Setup the default font
		TrueTypeFont font = createTrueTypeFont("Arial" , 12);
		//Catch any errors
		try {
			//Check to see whether the file is external
			if (external) {
				//Load the font
				font = createTrueTypeFont(java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, new FileInputStream(FileUtils.getPath(fontPath))).deriveFont(size).deriveFont(getFontType(fontType)));
			} else {
				//Load the font
				font = createTrueTypeFont(java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, FontUtils.class.getResourceAsStream(fontPath)).deriveFont(size).deriveFont(getFontType(fontType)));
			}
		} catch (FileNotFoundException e) {
			Logger.log("Andor - FontUtils getFont()" , "File not found: " + fontPath , Log.ERROR);
			e.printStackTrace();
		} catch (FontFormatException e) {
			Logger.log(new Log("Andor - FontUtils getFont()" , "Font format exception: " + fontPath , Log.ERROR));
			e.printStackTrace();
		} catch (IOException e) {
			Logger.log(new Log("Andor - FontUtils getFont()" , "IOException: " + fontPath , Log.ERROR));
			e.printStackTrace();
		}
		//Return the font
		return font;
	}
	
	/* The static method used to turn a font type into a java Font type */
	public static int getFontType(int fontType) {
		//The type
		int type = 0;
		//Check the font type
		if (fontType == FONT_TYPE_PLAIN)
			type = java.awt.Font.PLAIN;
		else if (fontType == FONT_TYPE_BOLD)
			type = java.awt.Font.BOLD;
		else if (fontType == FONT_TYPE_ITALIC)
			type = java.awt.Font.ITALIC;
		//Return the type
		return type;
	}
	
}