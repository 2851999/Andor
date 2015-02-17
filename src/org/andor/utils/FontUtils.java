/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils;

import org.andor.core.BitmapText;
import org.andor.core.Colour;
import org.andor.core.Font;
import org.andor.core.Image;
import org.andor.core.ImageLoader;
import org.andor.core.Settings;
import org.andor.core.android.BitmapFontUtilsAndroid;
import org.andor.core.android.FontUtilsAndroid;

public class FontUtils {
	
	/* The static static method used to load a bitmap font */
	public static Font loadBitmapFont(String imagePath, boolean external, int gridSize, int fontSize, Colour[] colour) {
		return new Font(new BitmapText(ImageLoader.loadImage(imagePath, external), gridSize, fontSize, colour));
	}
	
	/* The static static method used to load a bitmap font */
	public static Font loadBitmapFont(String imagePath, boolean external, int gridSize, int fontSize, Colour colour) {
		return new Font(new BitmapText(ImageLoader.loadImage(imagePath, external), gridSize, fontSize, colour));
	}
	
	/* The static static method used to load a bitmap font */
	public static Font loadBitmapFont(String imagePath, boolean external, int gridSize, int fontSize) {
		return new Font(new BitmapText(ImageLoader.loadImage(imagePath, external), gridSize, fontSize));
	}
	
	/* The static method used to load a bitmap font assumes the grid size is 16 */
	public static Font loadBitmapFont(String imagePath, boolean external, int fontSize, Colour[] colour) { 
		return loadBitmapFont(imagePath, external, 16, fontSize, colour);
	}
	
	/* The static method used to load a bitmap font assumes the external value is false and the grid size is 16 */
	public static Font loadBitmapFont(String imagePath, int fontSize, Colour[] colour) { 
		return loadBitmapFont(imagePath, false, fontSize, colour);
	}
	
	/* The static method used to load a bitmap font assumes the grid size is 16 */
	public static Font loadBitmapFont(String imagePath, boolean external, int fontSize, Colour colour) { 
		return loadBitmapFont(imagePath, external, 16, fontSize, colour);
	}
	
	/* The static method used to load a bitmap font assumes the external value is false and the grid size is 16 */
	public static Font loadBitmapFont(String imagePath, int fontSize, Colour colour) { 
		return loadBitmapFont(imagePath, false, fontSize, colour);
	}
	
	/* The static method used to load a bitmap font assumes the grid size is 16 */
	public static Font loadBitmapFont(String imagePath, boolean external, int fontSize) { 
		return loadBitmapFont(imagePath, external, 16, fontSize);
	}
	
	/* The static method used to load a bitmap font assumes the external value is false and the grid size is 16 */
	public static Font loadBitmapFont(String imagePath, int fontSize) { 
		return loadBitmapFont(imagePath, false, fontSize);
	}
	
	/* The static method used to create a bitmap front given the name of a font */
	public static Font createBitmapFont(String font, Colour colour, float fontSize, float cellSize, int gridSize) {
		return new Font(new BitmapText(generateBitmapFontImage(createFont(font, Colour.WHITE, 16), cellSize), gridSize, fontSize, colour));
	}
	
	/* The static method used to create a bitmap front given the name of a font */
	public static Font createBitmapFont(String font, Colour[] colours, float fontSize, float cellSize, int gridSize) {
		return new Font(new BitmapText(generateBitmapFontImage(createFont(font, Colour.WHITE, 16), cellSize), gridSize, fontSize, colours));
	}
	
	/* The static method used to create a bitmap front given the name of a font */
	public static Font createBitmapFont(String font, Colour colour, float fontSize) {
		return createBitmapFont(font, colour, fontSize, fontSize, 16);
	}
	
	/* The static method used to create a bitmap front given the name of a font */
	public static Font createBitmapFont(String font, Colour[] colours, float fontSize) {
		return createBitmapFont(font, colours, fontSize, fontSize, 16);
	}
	
	/* The static method used to create a bitmap front given the name of a font */
	public static Font createBitmapFont(String font, float fontSize) {
		return createBitmapFont(font, Colour.WHITE, fontSize, fontSize, 16);
	}
	
	/* The static method used to create a bitmap front given the name of a font */
	public static Font createBitmapFont(String path, boolean external, Colour colour, float fontSize, float cellSize, int gridSize) {
		return new Font(new BitmapText(generateBitmapFontImage(createFont(path, external, Colour.WHITE, 16), cellSize), gridSize, fontSize, colour));
	}
	
	/* The static method used to create a bitmap front given the name of a font */
	public static Font createBitmapFont(String path, boolean external, Colour colour, float fontSize, float cellSize) {
		return createBitmapFont(path, external, colour, fontSize, cellSize, 16);
	}
	
	/* The static method used to create a bitmap front given the name of a font */
	public static Font createBitmapFont(String path, boolean external, Colour colour, float fontSize) {
		return createBitmapFont(path, external, colour, fontSize, fontSize, 16);
	}
	
	/* The static method used to create a bitmap front given the name of a font */
	public static Font createBitmapFont(String path, boolean external, float fontSize) {
		return createBitmapFont(path, external, Colour.WHITE, fontSize, fontSize, 16);
	}
	
	/* The static method used to generate an image of a bitmap font */
	public static Image generateBitmapFontImage(TrueTypeFont font, float cellSize) {
		//Check the Android setting
		if (! Settings.AndroidMode)
			//Return the image
			return BitmapFontUtilsPC.generateBitmapFontImage(font.pcFont, cellSize);
		else
			return BitmapFontUtilsAndroid.generateBitmapFontImage(font.androidFont, cellSize);
	}
	
	/* The static method used to create a font */
	public static TrueTypeFont createFont(String font, Colour colour, float fontSize) {
		//Check the Android setting
		if (! Settings.AndroidMode)
			//Return the image
			return FontUtilsPC.createFont(font, colour, fontSize);
		else
			return FontUtilsAndroid.createFont(font, colour, fontSize);
	}
	
	/* The static method used to create a font from a file */
	public static TrueTypeFont createFont(String path, boolean external, float fontSize) {
		//Return the font
		return createFont(path, external, Colour.WHITE, fontSize);
	}
	
	/* The static method used to create a font from a file */
	public static TrueTypeFont createFont(String path, boolean external, Colour colour, float fontSize) {
		//Check the Android setting
		if (! Settings.AndroidMode)
			//Return the image
			return FontUtilsPC.createFont(path, external, colour, fontSize);
		else
			return FontUtilsAndroid.createFont(path, external, colour, fontSize);
	}
	
}