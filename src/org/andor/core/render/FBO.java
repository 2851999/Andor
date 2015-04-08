/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.render;

import java.util.ArrayList;
import java.util.List;

import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.andor.utils.BufferUtils;
import org.andor.utils.GLUtils;
import org.lwjgl.opengl.GL30;

public class FBO {
	
	/* The target */
	public int target;
	
	/* The pointer to this FBO */
	public int pointer;
	
	/* The various textures within this FBO */
	public List<RenderTexture> textures;
	
	/* The constructor */
	public FBO(int target) {
		//Assign the variables
		this.target = target;
		this.pointer = GLUtils.genFramebuffers();
		this.textures = new ArrayList<RenderTexture>();
	}
	
	/* The method used to add a texture */
	public void add(RenderTexture texture) {
		//Add the texture
		this.textures.add(texture);
	}
	
	/* The method used to setup this FBO */
	public void setup() {
		//Bind this framebuffer
		GLUtils.bindFramebuffer(this.target, this.pointer);
		//Go through the textures
		for (int a = 0; a < this.textures.size(); a++)
			//Point this FBO to the texture
			GLUtils.framebufferTexture2D(this.target, this.textures.get(a).getAttachment(), this.textures.get(a).getParameters().getTarget(), this.textures.get(a).getPointer(), 0);
		//Check the status of the FBO
		int status = GLUtils.checkFramebufferStatus(this.target);
		//Check for any errors
		if(status != GL30.GL_FRAMEBUFFER_COMPLETE)
			Logger.log("FBO", "Can't initialise the FBO", Log.ERROR);
		this.unbind();
	}
	
	/* The method used to bind this FBO ready to write to it */
	public void bind() {
		//Bind the FBO
		GLUtils.bindFramebuffer(this.target, this.pointer);
		//The buffers
		int[] buffers = new int[this.textures.size()];
		//Go through the textures
		for (int a = 0; a < this.textures.size(); a++) {
			//Check the current texture
			if (this.textures.get(a).getAttachment() != GL30.GL_DEPTH_ATTACHMENT)
				//Assign the next texture
				buffers[a] = this.textures.get(a).getAttachment();
		}
		GLUtils.drawBuffers(BufferUtils.createFlippedBuffer(buffers));
	}
	
	/* The method used to unbind the FBO */
	public void unbind() {
		//Unbind the FBO
		GL30.glBindFramebuffer(this.target, 0);
	}
	
	/* The getter and setters */
	public RenderTexture getTexture(int texture) { return this.textures.get(texture); }
	
}