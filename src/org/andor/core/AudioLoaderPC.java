/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.andor.utils.FileUtils;
import org.lwjgl.util.WaveData;

public class AudioLoaderPC {
	
	/* The static method used to load an audio file */
	public static Audio load(String filePath, boolean external) {
		//The audio
		Audio audio = null;
		//Try and catch any errors
		try {
			//Check to see whether Andor is currently running on Android
			if (! Settings.AndroidMode) {
				//The buffered input stream
				BufferedInputStream bis = null;
				//Check to see whether the file is in a folder
				if (external)
					bis = new BufferedInputStream(new FileInputStream(FileUtils.getPath(filePath)));
				else
					bis = new BufferedInputStream(ImageLoader.class.getResourceAsStream(filePath));
				//Load the audio
				audio = new AudioPC(WaveData.create(bis));
			}
		} catch (FileNotFoundException e) {
			//Log an error
			Logger.log("Andor - AudioLoaderPC load()" , "The file wasn't found: " + filePath, Log.ERROR);
			e.printStackTrace();
		}
		//Return the audio
		return audio;
	}
	
}