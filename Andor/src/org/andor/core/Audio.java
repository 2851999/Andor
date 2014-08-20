/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

public class Audio {
	
	/* The list of audio that has been created */
	private static List<Audio> allAudio = new ArrayList<Audio>();
	
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
	
	/* The constructor for a .wav file */
	public Audio(WaveData waveData) {
		//Setup the audio
		this.sourceHandle = AL10.alGenSources();
		this.bufferHandle = AL10.alGenBuffers();
		this.sourcePosition = new Vector3D();
		this.sourceVelocity = new Vector3D();
		this.listenerPosition = new Vector3D();
		this.listenerVelocity = new Vector3D();
		this.listenerRotation = new Vector3D();
		this.pitch = 1.0f;
		this.gain = 1.0f;
		this.looping = false;
		AL10.alBufferData(bufferHandle, waveData.format, waveData.data, waveData.samplerate);
		waveData.dispose();
		//Setup the source
		AL10.alSourcei(this.sourceHandle, AL10.AL_BUFFER, this.bufferHandle);
		//Add this audio to the list
		allAudio.add(this);
	}
	
	/* The method used to play this audio */
	public void play() {
		//Update the audio
		this.update();
		AL10.alSourcePlay(this.sourceHandle);
	}
	
	/* The method used to stop this audio */
	public void stop() {
		AL10.alSourceStop(this.sourceHandle);
	}
	
	/* The method used to pause this audio */
	public void pause() {
		//Update this audio
		this.update();
		AL10.alSourcePause(this.sourceHandle);
	}
	
	/* The method used to resume this audio */
	public void resume() {
		AL10.alSourcePlay(this.sourceHandle);
	}
	
	/* The method used to update this audio */
	public void update() {
		//Update the listener
		AL10.alListener3f(AL10.AL_POSITION, this.listenerPosition.x, this.listenerPosition.y, this.listenerPosition.z);
		AL10.alListener3f(AL10.AL_VELOCITY, this.listenerVelocity.x, this.listenerVelocity.y, this.listenerVelocity.z);
		AL10.alListener3f(AL10.AL_ORIENTATION, this.listenerRotation.x, this.listenerRotation.y, this.listenerRotation.z);
		//Update the sources values
		AL10.alSource3f(this.sourceHandle, AL10.AL_POSITION, this.sourcePosition.x, this.sourcePosition.y, this.sourcePosition.z);
		AL10.alSource3f(this.sourceHandle, AL10.AL_VELOCITY, this.sourceVelocity.x, this.sourceVelocity.y, this.sourceVelocity.z);
		AL10.alSourcef(this.sourceHandle, AL10.AL_PITCH, this.pitch);
		AL10.alSourcef(this.sourceHandle, AL10.AL_GAIN, this.gain);
		//Check to see whether the source should loop
		if (this.looping)
			AL10.alSourcei(this.sourceHandle, AL10.AL_LOOPING, AL10.AL_TRUE);
		else
			AL10.alSourcei(this.sourceHandle, AL10.AL_LOOPING, AL10.AL_FALSE);
	}
	
	/* The method used to release this audio */
	public void release() {
		AL10.alDeleteSources(this.sourceHandle);
		AL10.alDeleteBuffers(this.bufferHandle);
	}
	
	/* The static method used to create the audio context */
	public static void create() {
		//Try and catch any errors
		try {
			AL.create();
		} catch (LWJGLException e) {
			//Log an error
			Logger.log("Andor - Audio", "An exception has occurred when creating the audio", Log.ERROR);
			e.printStackTrace();
		}
	}
	
	/* The static method used to create the audio context */
	public static void destroy() {
		AL.destroy();
	}
	
	/* The static method used to release all of the audio that have been created */
	public static void releaseAll() {
		//Go through each audio
		for (int a = 0; a < allAudio.size(); a++)
			//Delete the current audio
			allAudio.get(a).release();
	}
	
}