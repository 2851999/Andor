/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.andor.core.logger.Logger;
import org.andor.utils.GLUtils;
import org.lwjgl.opengl.GL11;

import android.graphics.Bitmap;
import android.opengl.GLES20;

public class Image {
	
	/* The list of images that has been created */
	private static List<Image> allImages = new ArrayList<Image>();
	
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
	
	/* The texture pointer */
	public int pointer;
	
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
		allImages.add(this);
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
		
		//Setup the texture ID
		this.pointer = GLUtils.genTextures();
		
		//Bind the texture
		GLUtils.bindTexture(GL11.GL_TEXTURE_2D, this.pointer);
		
		//Enable 2D textures
		GLUtils.enable(GL11.GL_TEXTURE_2D);
		
		//The mode for rendering
		int colourMode = 0;
		//Check the number of components
		if (this.numberOfColourComponents == 3)
			//Set the correct mode
			colourMode = GL11.GL_RGB;
		else if (this.numberOfColourComponents == 4)
			//Set the correct mode
			colourMode = GL11.GL_RGBA;
		else
			//Log an error
			Logger.log("Andor - Image", "Unknown number of colour components " + this.numberOfColourComponents);
		
		//Setup this image
		GLUtils.texImage2D(GL11.GL_TEXTURE_2D, 0, colourMode, this.width, this.height, 0, colourMode, GL11.GL_UNSIGNED_BYTE, this.texture);
		
		//Setup the texture environment
		GLUtils.texEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
		
		//Set the minification and magnification values
		GLUtils.texParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GLUtils.texParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		
		//Add this image to the list
		allImages.add(this);
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
		
		//Setup the texture ID
		this.pointer = GLUtils.genTextures();
		
		//Bind the texture
		GLUtils.bindTexture(GLES20.GL_TEXTURE_2D, this.pointer);
		
		//Enable 2D textures
		GLUtils.enable(GLES20.GL_TEXTURE_2D);
		
		//Setup this image
		android.opengl.GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, this.bitmap, 0);
		
		//The bitmap is no longer needed so recycle it
		this.bitmap.recycle();
		
		//Set the minification and magnification values
		GLUtils.texParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
		GLUtils.texParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
		
		//Add this image to the list
		allImages.add(this);
	}
	
	/* The method used to bind this image ready for OpenGL rendering */
	public void bind() {
		//Bind the texture
		GLUtils.bindTexture(GL11.GL_TEXTURE_2D, this.pointer);
	}
	
	/* The method used to unbind this image */
	public void unbind() {
		//Bind nothing
		GLUtils.bindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	/* The method used to release this image */
	public void release() {
		//Delete this texture
		GLUtils.deleteTextures(this.pointer);
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
	
	/* The static method used to release all of the images that have been created */
	public static void releaseAll() {
		//Go through each image
		for (int a = 0; a < allImages.size(); a++)
			//Delete the current image
			allImages.get(a).release();
	}

	
}