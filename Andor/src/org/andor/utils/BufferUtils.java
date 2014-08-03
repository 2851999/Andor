/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.utils;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtils {
	
	/* The static method used to create a byte buffer given the data */
	public static ByteBuffer createBuffer(byte[] data) {
		//Create the buffer
		ByteBuffer buffer = org.lwjgl.BufferUtils.createByteBuffer(data.length);
		//Give the data to the buffer
		buffer.put(data);
		//Return the buffer
		return buffer;
	}
	
	/* The static method used to create an integer buffer given the data */
	public static IntBuffer createBuffer(int[] data) {
		//Create the buffer
		IntBuffer buffer = org.lwjgl.BufferUtils.createIntBuffer(data.length);
		//Give the data to the buffer
		buffer.put(data);
		//Return the buffer
		return buffer;
	}
	
	/* The static method used to create a float buffer given the data */
	public static FloatBuffer createBuffer(float[] data) {
		//Create the buffer
		FloatBuffer buffer = org.lwjgl.BufferUtils.createFloatBuffer(data.length);
		//Give the data to the buffer
		buffer.put(data);
		//Return the buffer
		return buffer;
	}
	
	/* The static method used to create a double buffer given the data */
	public static DoubleBuffer createBuffer(double[] data) {
		//Create the buffer
		DoubleBuffer buffer = org.lwjgl.BufferUtils.createDoubleBuffer(data.length);
		//Give the data to the buffer
		buffer.put(data);
		//Return the buffer
		return buffer;
	}
	
	/* The static method used to create a flipped integer buffer */
	public static ByteBuffer createFlippedBuffer(byte[] data) {
		//Get the buffer
		ByteBuffer buffer = createBuffer(data);
		//Flip the buffer
		buffer.flip();
		//Return the buffer
		return buffer;
	}
	
	/* The static method used to create a flipped integer buffer */
	public static IntBuffer createFlippedBuffer(int[] data) {
		//Get the buffer
		IntBuffer buffer = createBuffer(data);
		//Flip the buffer
		buffer.flip();
		//Return the buffer
		return buffer;
	}
	
	/* The method used to create a flipped float buffer */
	public static FloatBuffer createFlippedBuffer(float[] data) {
		//Get the buffer
		FloatBuffer buffer = createBuffer(data);
		//Flip the buffer
		buffer.flip();
		//Return the buffer
		return buffer;
	}
	
	/* The static method used to create a flipped double buffer */
	public static DoubleBuffer createFlippedBuffer(double[] data) {
		//Get the buffer
		DoubleBuffer buffer = createBuffer(data);
		//Flip the buffer
		buffer.flip();
		//Return the buffer
		return buffer;
	}
	
}