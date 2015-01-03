/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.android;

import org.andor.core.Audio;
import org.andor.core.Settings;

import android.media.MediaPlayer;

public class AndroidAudio extends Audio implements ActivityListenerInterface {
	
	/* The audio clip for android */
	public MediaPlayer androidAudioClip;
	
	/* Was the audio playing before pause */
	private boolean wasPlayingBeforePause;
	
	/* The constructor */
	public AndroidAudio(MediaPlayer androidAudioClip) {
		//Assign the variables
		this.androidAudioClip = androidAudioClip;
		this.wasPlayingBeforePause = false;
		//Add this audio to the list
		Audio.allAudio.add(this);
		//Add this listener
		BaseActivity.addListener(this);
	}
	
	/* The method to start then audio playing */
	public void play() {
		//Make sure the clip loops if needed
		this.androidAudioClip.setLooping(this.looping);
		//Start the audio clip
		this.androidAudioClip.start();
	}
	
	/* The method it stop the audio playing */
	public void stop() {
		//Stop the audio clip
		this.androidAudioClip.stop();
	}
	
	/* The method that returns whether the audio is playing */
	public boolean isPlaying() {
		return this.androidAudioClip.isPlaying();
	}
	
	/* The method called when the activity pauses */
	public void onActivityPaused() {
		//Check to see whether the PauseSoundsOnPause setting is enabled
		//and the audio is playing
		if (Settings.Android.PauseSoundsOnPause && this.isPlaying()) {
			//Assign 'wasPlayingBeforePause' to true
			this.wasPlayingBeforePause = true;
			//Pause the audio clip
			this.androidAudioClip.pause();
		}
	}
	
	/* The method called when the activity resumes */
	public void onActivityResumed() {
		//Check to see whether the PauseSoundsOnPause setting is enabled
		//clip was playing before the activity was paused
		if (Settings.Android.PauseSoundsOnPause && this.wasPlayingBeforePause) {
			//Assign 'wasPlayingBeforePause' to false
			this.wasPlayingBeforePause = false;
			//Resume the audio clip
			this.androidAudioClip.start();
		}
	}
	
	/* The other Android activity methods */
	public void onActivityCreated() {}
	public void onActivityStopped() {}
	public void onActivityRestarted() {}
	public void onActivityDestroy() {}
	
}
