/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import java.nio.ByteBuffer;
import org.andor.core.logger.Logger;
import org.lwjgl.opengl.GL11;

public class Image {
	
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
	
	/* The texture ID */
	public int textureId;
	
	/* The constructor with only the width and height given */
	public Image(int width, int height) {
		//Assign the variables
		this.width = width;
		this.height = height;
		this.top = 0f;
		this.bottom = 1f;
		this.left = 0f;
		this.right = 1f;
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
		this.textureId = GL11.glGenTextures();
		
		//Bind the texture
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureId);
		
		//Enable 2D textures
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		
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
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, colourMode, this.width, this.height, 0, colourMode, GL11.GL_UNSIGNED_BYTE, this.texture);
		
		//Setup the texture environment
		GL11.glTexEnvi(GL11.GL_TEXTURE_ENV, GL11.GL_TEXTURE_ENV_MODE, GL11.GL_MODULATE);
		
		//Set the minification and magnification values
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
	}
	
	/* The method used to bind this image ready for OpenGL rendering */
	public void bind() {
		//Bind the texture
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.textureId);
	}
	
	/* The method used to unbind this image */
	public void unbind() {
		//Unbind this texture
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	/* The method used to delete this image */
	public void delete() {
		//Delete this texture
		GL11.glDeleteTextures(this.textureId);
	}
	
	/* The 'get' methods */
	public ByteBuffer getTexture() { return texture; }
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	
	public float[] getTextureCoordinates() {
		return new float[] { this.top, this.bottom, this.left, this.right };
	}
	
}