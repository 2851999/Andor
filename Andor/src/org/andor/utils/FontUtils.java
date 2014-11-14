/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import org.andor.core.BitmapText;
import org.andor.core.Colour;
import org.andor.core.Font;
import org.andor.core.ImageLoader;

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
	
}