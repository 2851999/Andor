package org.andor.core.render;

import org.andor.core.Settings;
import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.andor.utils.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class GeometryBuffer {
	
	/* The different types of textures associeted with this geometry buffer */
	public static final int TYPE_POSITION = 0;
	public static final int TYPE_DIFFUSE = 1;
	public static final int TYPE_NORMAL = 2;
	
	/* The total number of textures */
	public static final int NUMBER_OF_TEXTURES = 3;
	
	/* The FBO pointer */
	public int fboPointer;
	
	/* The textures */
	public int[] textures;
	
	/* The depth texture */
	public int depthTexture;
	
	/* The constructor */
	public GeometryBuffer() {
		//Assign the values
		this.fboPointer = GL30.glGenFramebuffers();
		this.textures = new int[NUMBER_OF_TEXTURES];
		this.depthTexture = GL11.glGenTextures();
		//Bind the FBO
		GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, fboPointer);
		//Go through each texture
		for (int a = 0; a < textures.length; a++) {
			//Generate the current texture
			this.textures[a] = GL11.glGenTextures();
			//Bind the texture
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, textures[a]);
			//Set the parameters
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			//Setup the texture
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL30.GL_RGBA16F, (int) Settings.Window.Width, (int) Settings.Window.Height, 0, GL11.GL_RGBA, GL11.GL_FLOAT, (java.nio.ByteBuffer) null);
			//Attach the texture to the FBO
			GL30.glFramebufferTexture2D(GL30.GL_DRAW_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0 + a, GL11.GL_TEXTURE_2D, textures[a], 0);
		}
		//Bind the depth texture
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, depthTexture);
		//Set the parameters
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		//Setup the texture
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL14.GL_DEPTH_COMPONENT32, (int) Settings.Window.Width, (int) Settings.Window.Height, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (java.nio.ByteBuffer) null);
		//Attach the depth texture
		GL30.glFramebufferTexture2D(GL30.GL_DRAW_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, depthTexture, 0);
		//Check the status of the FBO
		int status = GL30.glCheckFramebufferStatus(GL30.GL_DRAW_FRAMEBUFFER);
		//Check for any errors
		if(status != GL30.GL_FRAMEBUFFER_COMPLETE)
			Logger.log("GeometryBuffer", "Can't initialise the GeometryBuffer's FBO", Log.ERROR);
		//Unbind the FBO
		GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, 0);
	}
	
	/* The method used to bind this FBO ready to write to it */
	public void bindWriting() {
		//Bind the FBO
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, this.fboPointer);
		int buffers[] = { GL30.GL_COLOR_ATTACHMENT0, GL30.GL_COLOR_ATTACHMENT1, GL30.GL_COLOR_ATTACHMENT2 };
		GL20.glDrawBuffers(BufferUtils.createFlippedBuffer(buffers));
	}
	
	/* The method used to bind this FBO ready to read from it */
	public void bindReading() {
		//Bind the FBO
		GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, fboPointer);
	}
	
	/* The method used to set the current buffer being read from */
	public void setReadBuffer(int type) {
		GL11.glReadBuffer(GL30.GL_COLOR_ATTACHMENT0 + type);
	}
	
	/* The method used to unbind the FBO */
	public void unbind() {
		//Unbind the FBO
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
	}
	
}