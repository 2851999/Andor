/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.render;

import org.andor.core.Texture;
import org.andor.core.TextureParameters;
import org.andor.utils.GLUtils;

public class RenderTexture extends Texture {
	
	/* The values needed to define this texture */
	public int internalFormat;
	public int format;
	public int attachment;
	public int type;
	
	/* The constructor */
	public RenderTexture(int width, int height, int internalFormat, int format, int attachment, int type, TextureParameters parameters) {
		super(parameters);
		//Assign the variables
		this.internalFormat = internalFormat;
		this.format = format;
		this.attachment = attachment;
		this.type = type;
		//Bind the image
		GLUtils.bindTexture(parameters.getTarget(), this.pointer);
		//Setup the texture
		GLUtils.texImage2D(parameters.getTarget(), 0, this.internalFormat, width, height, 0, this.format, this.type, (java.nio.ByteBuffer) null);
		//Apply the parameters
		this.parameters.apply(this.pointer, false);
	}
	
	/* The getter and setters */
	public void setInternalFormat(int internalFormat) { this.internalFormat = internalFormat; }
	public void setFormat(int format) { this.format = format; }
	public void setAttachment(int attachment) { this.attachment = attachment; }
	public void setType(int type) { this.type = type; }
	public int getInternalFormat() { return this.internalFormat; }
	public int getFormat() { return this.format; }
	public int getAttachment() { return this.attachment; }
	public int getType() { return this.type; }
	
}