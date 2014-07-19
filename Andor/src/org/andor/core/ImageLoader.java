/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.andor.utils.FileUtils;
import org.newdawn.slick.opengl.TextureLoader;

public class ImageLoader {
	
	/* The static method used to load an image given the file path, and
	 * the external variables */
	public static Image loadImage(String filePath, boolean external) {
		//Work out the file type
		if (filePath.contains(".")) {
			//Get the file extension
			String fileExtension = filePath.substring(filePath.lastIndexOf('.')).toUpperCase();
			//Return the image
			return loadImage(filePath, fileExtension, external);
		} else {
			//Log an error
			Logger.log("Andor - ImageLoader", "No file extension found in the file path " + filePath, Log.ERROR);
			//Return nothing
			return null;
		}
	}
	
	/* The static method used to load an image given the file path, file extension
	 * and the external variables */
	public static Image loadImage(String filePath, String fileExtension, boolean external) {
		//Try and catch any errors
		try {
			//Check to see whether the file is in a folder
			if (external)
				//Return the image
				return new Image(TextureLoader.getTexture(fileExtension, new FileInputStream(FileUtils.getPath(filePath))));
			else
				//Return the image
				return new Image(TextureLoader.getTexture(fileExtension, ImageLoader.class.getResourceAsStream(filePath)));
		} catch (FileNotFoundException e) {
			//Log an error
			Logger.log("Andor - ImageLoader", "Error loading the file " + filePath + " with the format " + fileExtension, Log.ERROR);
			e.printStackTrace();
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - ImageLoader", "Error loading the file " + filePath + " with the format " + fileExtension, Log.ERROR);
			e.printStackTrace();
		}
		//Return null if there was a problem
		return null;
	}
	
}