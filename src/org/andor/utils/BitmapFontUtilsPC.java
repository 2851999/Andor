/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.andor.core.Image;
import org.andor.core.ImageLoaderPC;

public class BitmapFontUtilsPC {
	
	/* The static method used to generate a bitmap font image given a font */
	public static Image generateBitmapFontImage(FontPC font) {
		//Calculate the cell size and then assign the font size
		float cellSize = font.fontSize;
		font.fontSize = cellSize / 1.7f;
		//32 - 127
		//Define the sizes
		int gridSize = 16;
		int startChar = 32;
		int endChar = 127;
		//Setup the image
		BufferedImage image = new BufferedImage((int) (cellSize * gridSize), (int) (cellSize * gridSize), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D g2d = image.createGraphics();
		//Assign the font
		g2d.setFont(font.font.deriveFont(font.fontSize));
		g2d.setColor(new Color(font.colour.r, font.colour.g, font.colour.b, font.colour.a));
		//Go through each character
		for (int a = startChar; a < endChar; a++) {
			//The current position
			float x = cellSize / 2 - (font.getWidth("" + (char) startChar, g2d) / 2);
			float y = cellSize / 2 - (font.getHeight("" + (char) startChar, g2d) / 2);
			//Calculate the current position
			float posX = ((int) a % gridSize) * cellSize;
			//Calculate the cell's y position
			float posY = (float) ((Math.floor((int) a / gridSize)) * cellSize);
			//Render the text
			g2d.drawString("" + (char) a, posX + x, posY + y + g2d.getFontMetrics(font.font.deriveFont(font.fontSize)).getAscent());
		}
		//Return the image
		return ImageLoaderPC.loadImage(image);
	}
	
}