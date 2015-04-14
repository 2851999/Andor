/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import org.andor.utils.GLUtils;
import org.lwjgl.opengl.EXTTextureFilterAnisotropic;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TextureParameters {
	
	/* The default values */
	public static int DEFAULT_TARGET = GL11.GL_TEXTURE_2D;
	public static int DEFAULT_FILTER = GL11.GL_LINEAR;
	public static int DEFAULT_CLAMP = GL12.GL_CLAMP_TO_EDGE;
	public static boolean DEFAULT_SHOULD_CLAMP = false;
	
	/* The default instances of the texture parameters */
	public static TextureParameters DEFAULT_NEAREST = new TextureParameters().setFilter(GL11.GL_NEAREST);
	
	/* The values */
	public int target;
	public int filter;
	public boolean shouldClamp;
	public int clamp;
	
	/* The constructor */
	public TextureParameters() {
		//Assign the default values
		this.target = DEFAULT_TARGET;
		this.filter = DEFAULT_FILTER;
		this.shouldClamp = DEFAULT_SHOULD_CLAMP;
		this.clamp = DEFAULT_CLAMP;
	}
	
	/* The constructor with the parameters given */
	public TextureParameters(int target, int filter, boolean shouldClamp) {
		//Assign the default values
		this.target = target;
		this.filter = filter;
		this.shouldClamp = shouldClamp;
		this.clamp = DEFAULT_CLAMP;
	}
	
	/* The constructor with the parameters given */
	public TextureParameters(int target, int filter, int clamp) {
		//Assign the default values
		this.target = target;
		this.filter = filter;
		this.shouldClamp = true;
		this.clamp = clamp;
	}
	
	/* The static method used to apply all the texture values to a texture */
	public void apply(int texture) {
		this.apply(texture, true);
	}
	
	/* The static method used to apply all the texture values to a texture */
	public void apply(int texture, boolean bind) {
		if (bind)
			//Bind the texture
			GLUtils.bindTexture(target, texture);
		//Apply the filter
		GLUtils.texParameteri(target, GL11.GL_TEXTURE_MAG_FILTER, filter);
		GLUtils.texParameteri(target, GL11.GL_TEXTURE_MIN_FILTER, filter);
		//Apply clamping if necessary
		if (this.shouldClamp) {
			GLUtils.texParameterf(target, GL11.GL_TEXTURE_WRAP_S, this.clamp);
			GLUtils.texParameterf(target, GL11.GL_TEXTURE_WRAP_T, this.clamp);
		}
		if (! Settings.AndroidMode) {
			//Check for mipmapping
			if(filter == GL11.GL_NEAREST_MIPMAP_NEAREST ||
					filter == GL11.GL_NEAREST_MIPMAP_LINEAR ||
					filter == GL11.GL_LINEAR_MIPMAP_NEAREST ||
					filter == GL11.GL_LINEAR_MIPMAP_LINEAR) { //TRILINEAR
				GLUtils.generateMipmap(target);
				
				GLUtils.texParameterf(target, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, Settings.Video.MaxAnisotropicSamples);
			}
		} else {
			GLUtils.texParameteri(target, GL12.GL_TEXTURE_BASE_LEVEL, 0);
			GLUtils.texParameteri(target, GL12.GL_TEXTURE_MAX_LEVEL, 0);
		}
	}
	
	/* The getters and setters */
	public int getTarget() { return this.target; }
	public int getFilter() { return this.filter; }
	public int getClamp() { return this.clamp; }
	public boolean shouldClamp() { return this.shouldClamp; }
	public TextureParameters setTarget(int target) { this.target = target; return this; }
	public TextureParameters setFilter(int filter) { this.filter = filter; return this; }
	public TextureParameters setClamp(int clamp) { this.clamp = clamp; return this; }
	public TextureParameters setShouldClamp(boolean shouldClamp) { this.shouldClamp = shouldClamp; return this; }
	
}