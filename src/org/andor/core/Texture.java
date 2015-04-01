/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import org.andor.utils.GLUtils;
import org.lwjgl.opengl.GL11;

public class Texture {
	
	/* The pointer to this texture */
	public int pointer;
	
	/* The texture parameters */
	public TextureParameters parameters;
	
	/* The constructor */
	public Texture() {
		//Assign the variables
		this.pointer = GLUtils.genTextures();
		this.parameters = new TextureParameters();
		
		//Add this image to the list
		Textures.allTextures.add(this);
	}
	
	/* The constructor */
	public Texture(int pointer) {
		//Assign the variables
		this.pointer = pointer;
		this.parameters = new TextureParameters();
		
		//Add this image to the list
		Textures.allTextures.add(this);
	}
	
	/* The constructor */
	public Texture(TextureParameters parameters) {
		//Assign the variables
		this.pointer = GLUtils.genTextures();
		this.parameters = parameters;
	}
	
	/* The constructor */
	public Texture(int pointer, TextureParameters parameters) {
		//Assign the variables
		this.pointer = pointer;
		this.parameters = parameters;
	}
	
	/* The method used to apply this textures parameters */
	public void applyParameters() {
		this.parameters.apply(this.pointer);
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
	
}