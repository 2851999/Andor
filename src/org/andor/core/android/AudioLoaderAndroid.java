/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.android;

import java.io.IOException;

import org.andor.core.Audio;
import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class AudioLoaderAndroid {
	
	/* The static method used to load an audio file */
	public static Audio load(String filePath, boolean external) {
		//The audio
		Audio audio = null;
		//Try and catch any errors
		try {
			MediaPlayer mediaPlayer = new MediaPlayer();
			//Check to see whether the file is in a folder
			if (external) {
				
			} else {
				AssetFileDescriptor afd = BaseActivity.instance.getAssets().openFd(filePath);
				mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
				afd.close();
			}
			mediaPlayer.prepare();
			//Load the audio
			audio = new AndroidAudio(mediaPlayer);
		} catch (IllegalArgumentException e) {
			//Log an error
			Logger.log("Andor - AudioLoaderAndroid load()" , "The was an exception when loading the audio: " + filePath, Log.ERROR);
			e.printStackTrace();
		} catch (IllegalStateException e) {
			//Log an error
			Logger.log("Andor - AudioLoaderAndroid load()" , "The was an exception when loading the audio: " + filePath, Log.ERROR);
			e.printStackTrace();
		} catch (IOException e) {
			//Log an error
			Logger.log("Andor - AudioLoaderAndroid load()" , "The was an exception when loading the audio: " + filePath, Log.ERROR);
			e.printStackTrace();
		}
		//Return the audio
		return audio;
	}
	
}