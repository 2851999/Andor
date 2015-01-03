/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.andor.utils.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;

public class AudioPC extends Audio {
	
	/* The default constructor */
	public AudioPC() {
		
	}
	
	/* The constructor for a .wav file */
	public AudioPC(WaveData waveData) {
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
		//Calculate the correct direction for the listener
        float xDirection = -1f * (float) Math.sin(Math.toRadians(this.listenerRotation.y));
        float zDirection = -1f * (float) Math.cos(Math.toRadians(this.listenerRotation.y));
		AL10.alListener(AL10.AL_ORIENTATION, BufferUtils.createFlippedBuffer(new float[] { xDirection, 0, zDirection, 0, 1f, 0 }));
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
	
}