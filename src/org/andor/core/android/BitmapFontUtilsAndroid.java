/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/


package org.andor.core.android;

import org.andor.core.Image;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class BitmapFontUtilsAndroid {
	
	/* The static method used to generate a bitmap font image given a font */
	public static Image generateBitmapFontImage(FontAndroid font) {
		//Calculate the cell size and then assign the font size
		float cellSize = font.fontSize;
		font.fontSize = cellSize / 1.7f;
		//32 - 127
		//Define the sizes
		int gridSize = 16;
		int startChar = 32;
		int endChar = 127;
		//Create a bitmap
		Bitmap bitmap = Bitmap.createBitmap((int) (cellSize * gridSize), (int) (cellSize * gridSize), Bitmap.Config.ARGB_8888);
		//Create the canvas object
		Canvas canvas = new Canvas(bitmap);
		//Create the paint object
		Paint paint = new Paint();
		//Assign the font
		paint.setTypeface(font.typeface);
		paint.setColor(Color.argb((int) (font.colour.a * 255), (int) (font.colour.r * 255), (int) (font.colour.g * 255), (int) (font.colour.b * 255)));
		paint.setTextSize(font.fontSize);
		//Go through each character
		for (int a = startChar; a < endChar; a++) {
			//The current position
			float x = cellSize / 2 - (font.getWidth("" + (char) startChar, paint) / 2);
			float y = cellSize / 2 - (font.getHeight("" + (char) startChar, paint) / 2);
			//Calculate the current position
			float posX = ((int) a % gridSize) * cellSize;
			//Calculate the cell's y position
			float posY = (float) ((Math.floor((int) a / gridSize)) * cellSize);
			//Render the text
			canvas.drawText("" + (char) a, posX + x, posY + y + paint.getFontMetrics().descent, paint);
		}
		//Return the image
		return new Image(bitmap);
	}
	
}