/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import java.util.ArrayList;
import java.util.List;

public class Audio {
	
	/* The list of audio that has been created */
	protected static List<Audio> allAudio = new ArrayList<Audio>();
	
	/* The source handle */
	public int sourceHandle;
	
	/* The buffer handle */
	public int bufferHandle;
	
	/* The position and the velocity of this audio's source */
	public Vector3D sourcePosition;
	public Vector3D sourceVelocity;
	
	/* The position, velocity and the rotation of this audio's listener */
	public Vector3D listenerPosition;
	public Vector3D listenerVelocity;
	public Vector3D listenerRotation;
	
	/* The other values used for the audio */
	public float pitch;
	public float gain;
	
	/* The boolean that represents whether this audio should loop */
	public boolean looping;
	
	/* The default constructor */
	public Audio() {
		//Add this audio to the list
		Audio.allAudio.add(this);
	}
	
	/* The method used to play this audio */
	public void play() {}
	
	/* The method used to stop this audio */
	public void stop() {}
	
	/* The method used to pause this audio */
	public void pause() {}
	
	/* The method used to resume this audio */
	public void resume() {}
	
	/* The method used to update this audio */
	public void update() {}
	
	/* The method used to release this audio */
	public void release() {}
	
	/* The static method used to release all of the audio that have been created */
	public static void releaseAll() {
		//Go through each audio
		for (int a = 0; a < allAudio.size(); a++)
			//Delete the current audio
			allAudio.get(a).release();
	}
	
}