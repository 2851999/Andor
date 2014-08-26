/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.andor.core.android.AudioLoaderAndroid;

public class AudioLoader {
	
	/* The static method used to load an audio file */
	public static Audio load(String filePath, boolean external) {
		if (! Settings.AndroidMode)
			return AudioLoaderPC.load(filePath, external);
		else
			return AudioLoaderAndroid.load(filePath, external);
	}
	
}