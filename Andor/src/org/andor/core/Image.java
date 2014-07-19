/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.newdawn.slick.opengl.Texture;

public class Image {
	
	/* The texture used in this image */
	public Texture texture;
	
	/* The constructor */
	public Image(Texture texture) {
		//Assign the variables
		this.texture = texture;
	}
	
	/* The method used to bind this image ready for OpenGL rendering */
	public void bind() {
		//Bind the texture
		this.texture.bind();
	}
	
	/* The 'get' methods */
	public Texture getTexture() { return texture; }
	public int getWidth() { return this.texture.getImageWidth(); }
	public int getHeight() { return this.texture.getImageHeight(); }
	
}