/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.andor.core.logger.Logger;
import org.andor.utils.BufferUtils;
import org.andor.utils.FileUtils;

public class ImageLoaderPC {
	
	
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
				image = ImageIO.read(ImageLoaderPC.class.getResourceAsStream(filePath));
		} catch (FileNotFoundException e) {
			//Log an error
			Logger.log("Andor - ImageLoader loadBufferedImage()", "File not found: " + filePath);
			e.printStackTrace();
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - ImageLoader loadBufferedImage()", "An exception has occurred when loading the file: " + filePath);
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
	
	/* The static method used to write an image */
	public static void write(Image image, String path, String format) {
		//Get the image's width and height
		int imageWidth = image.getWidth();
		int imageHeight = image.getHeight();
		
		//Create the array
		byte[] data = new byte[image.texture.remaining()];
		image.texture.get(data);
		//Not a great solution, but sometimes, an index out of bounds can be caused when a RGB image is
		//being saved as a RGBA image, so to fix these issues attempt RGBA, if that doesn't work assume
		//it should be saved as an RGB image
		//Setup the image
		BufferedImage i = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_4BYTE_ABGR);
		try {
			//Put the data into image
			i.getRaster().setDataElements(0, 0, imageWidth, imageHeight, data);
		} catch (IndexOutOfBoundsException e) {
			//Setup the image
			i = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_3BYTE_BGR);
			//Make sure there is data to be read
			if (data.length != 0)
				//Put the data into image
				i.getRaster().setDataElements(0, 0, imageWidth, imageHeight, data);
		}
		//Put the buffer back at position 0, to make sure that remaining will be more than 0,
		//after calling it once already
		image.texture.position(0);
		//Try and catch any errors
        try {
        	//Write the file
			ImageIO.write(i, format, new File(path));
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - ImageLoader write()", "An exception has occurred when writing the file: " + path);
			e.printStackTrace();
		}
	}
	
}