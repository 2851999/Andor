/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.andor.utils.BufferUtils;
import org.andor.utils.FileUtils;

public class ImageLoader {
	
	/* The static method used to load an image given the file path, and
	 * the external variables */
	public static Image loadImage(String filePath, boolean external) {
		return loadImage(loadBufferedImage(filePath, external));
	}
	
	/* The static method used to load a buffered image given the file path,
	 * and the external variables */
	public static BufferedImage loadBufferedImage(String filePath, boolean external) {
		//The buffered image
		BufferedImage image = null;
		//Try and catch any errors
		try {
			//Check to see whether the file is in a folder
			if (external)
				//Return the image
				image = ImageIO.read(new FileInputStream(FileUtils.getPath(filePath)));
			else
				//Return the image
				image = ImageIO.read(ImageLoader.class.getResourceAsStream(filePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Return the image
		return image;
	}
	
	/* The static method used to create an image from a buffered image */
	public static Image loadImage(BufferedImage image) {
		//Get the number of colour components
		int numberOfColourComponents = image.getColorModel().getNumComponents();
		//Create the image
		Image outImage = new Image(loadByteBuffer(image, numberOfColourComponents), image.getWidth(), image.getHeight(), numberOfColourComponents);
		//Return the image
		return outImage;
	}
	
	/* The static method used to create a byte buffer from a buffered image */
	public static ByteBuffer loadByteBuffer(BufferedImage image, int numberOfColourComponents) {
		//Get the image's width and height
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();
		
		//Get the number of colour components 3 = RGB 4 = RGBA
		int components = numberOfColourComponents;
		
		//Create the array
		byte[] data = new byte[components * imageWidth * imageHeight];
		//Put the data into the data array
		image.getRaster().getDataElements(0, 0, imageWidth, imageHeight, data);
		//Create the byte buffer for each pixel
		ByteBuffer pixels = BufferUtils.createFlippedBuffer(data);
		
		//Return the byte buffer
		return pixels;
	}
	
}