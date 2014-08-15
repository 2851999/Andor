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

import org.andor.utils.FileUtils;
import org.lwjgl.util.WaveData;

public class AudioLoader {
	
	/* The static method used to load a .wav audio file */
	public static Audio load(String filePath, boolean external) {
		//The audio
		Audio audio = null;
		//Try and catch any errors
		try {
			//The buffered input stream
			BufferedInputStream bis = null;
			//Check to see whether the file is in a folder
			if (external)
				bis = new BufferedInputStream(new FileInputStream(FileUtils.getPath(filePath)));
			else
				bis = new BufferedInputStream(ImageLoader.class.getResourceAsStream(filePath));
			//Load the audio
			audio = new Audio(WaveData.create(bis));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//Return the audio
		return audio;
	}
	
}