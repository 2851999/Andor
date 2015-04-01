/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import java.nio.ByteBuffer;

import org.andor.core.logger.Logger;
import org.andor.utils.GLUtils;
import org.lwjgl.opengl.GL11;

import android.graphics.Bitmap;
import android.opengl.GLES20;

public class Image extends Texture {
	
	/* The texture used in this image */
	public ByteBuffer texture;
	
	/* The width and height of this image */
	public int width;
	public int height;
	
	/* The number of components e.g. RGB = 3, RGBA = 4 */
	public int numberOfColourComponents;
	
	/* The texture coordinates of this image */
	public float top;
	public float bottom;
	public float left;
	public float right;
	
	/* The bitmap (Android) */
	public Bitmap bitmap;
	
	/* The constructor with the pointer given */
	public Image(int pointer) {
		//Assign the pointer
		this.pointer = pointer;
		this.top = 0f;
		this.bottom = 1f;
		this.left = 0f;
		this.right = 1f;
	}
	
	/* The constructor with only the width and height given */
	public Image(int width, int height) {
		//Assign the variables
		this.width = width;
		this.height = height;
		this.top = 0f;
		this.bottom = 1f;
		this.left = 0f;
		this.right = 1f;
		//Add this image to the list
		Textures.allTextures.add(this);
	}
	
	/* The constructor */
	public Image(ByteBuffer texture, int width, int height, int numberOfColourComponents) {
		//Assign the variables
		this.texture = texture;
		this.width = width;
		this.height = height;
		this.numberOfColourComponents = numberOfColourComponents;
		this.top = 0f;
		this.bottom = 1f;
		this.left = 0f;
		this.right = 1f;
		this.loadPC();
	}
	
	/* The constructor (Android) */
	public Image(Bitmap bitmap) {
		//Assign the variables
		this.bitmap = bitmap;
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
		this.numberOfColourComponents = 4;
		this.top = 0f;
		this.bottom = 1f;
		this.left = 0f;
		this.right = 1f;
		this.loadAndroid();
	}
	
	/* The constructor with the pointer given */
	public Image(int pointer, TextureParameters parameters) {
		//Invoke the super constructor
		super(parameters);
		//Assign the pointer
		this.pointer = pointer;
		this.top = 0f;
		this.bottom = 1f;
		this.left = 0f;
		this.right = 1f;
	}
	
	/* The constructor with only the width and height given */
	public Image(int width, int height, TextureParameters parameters) {
		//Invoke the super constructor
		super(parameters);
		//Assign the variables
		this.width = width;
		this.height = height;
		this.top = 0f;
		this.bottom = 1f;
		this.left = 0f;
		this.right = 1f;
		//Add this image to the list
		Textures.allTextures.add(this);
	}
	
	/* The constructor */
	public Image(ByteBuffer texture, int width, int height, int numberOfColourComponents, TextureParameters parameters) {
		//Invoke the super constructor
		super(parameters);
		//Assign the variables
		this.texture = texture;
		this.width = width;
		this.height = height;
		this.numberOfColourComponents = numberOfColourComponents;
		this.top = 0f;
		this.bottom = 1f;
		this.left = 0f;
		this.right = 1f;
		this.loadPC();
	}
	
	/* The constructor (Android) */
	public Image(Bitmap bitmap, TextureParameters parameters) {
		//Invoke the super constructor
		super(parameters);
		this.loadAndroid();
	}
	
	/* The method used to load an image on PC */
	private void loadPC() {
		//Bind the texture
		GLUtils.bindTexture(GL11.GL_TEXTURE_2D, this.pointer);
		
		//The mode for rendering
		int colourMode = 0;
		//Check the number of components
		if (this.numberOfColourComponents == 3)
			//Set the correct mode
			colourMode = GL11.GL_RGB;
		else if (this.numberOfColourComponents == 4)
			//Set the correct mode
			colourMode = GL11.GL_RGBA;
		else if (this.numberOfColourComponents == 1)
			colourMode = GL11.GL_RED;
		else {
			//Log an error
			Logger.log("Andor - Image", "Unknown number of colour components " + this.numberOfColourComponents);
		}
		
		//Setup this image
		GLUtils.texImage2D(GL11.GL_TEXTURE_2D, 0, colourMode, this.width, this.height, 0, colourMode, GL11.GL_UNSIGNED_BYTE, this.texture);
		
		//Setup the texture environment
		GLUtils.texEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
		
		//Apply the parameters
		this.applyParameters();
	}
	
	/* The method used to load an image on Android */
	private void loadAndroid() {
		//Bind the texture
		GLUtils.bindTexture(GLES20.GL_TEXTURE_2D, this.pointer);
		
		//Setup this image
		android.opengl.GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, this.bitmap, 0);
		
		//The bitmap is no longer needed so recycle it
		this.bitmap.recycle();
		
		//Apply the parameters
		this.applyParameters();
	}
	
	/* The 'get' methods */
	public ByteBuffer getTexture() { return texture; }
	public Bitmap getBitmap() { return this.bitmap; }
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	public int getPointer() { return this.pointer; }
	
	public float[] getTextureCoordinates() {
		return new float[] { this.top, this.bottom, this.left, this.right };
	}

	
}