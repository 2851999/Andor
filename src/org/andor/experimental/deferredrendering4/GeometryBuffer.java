package org.andor.experimental.deferredrendering4;

import org.andor.core.Settings;
import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.andor.utils.BufferUtils;
import org.lwjgl.opengl.GL11;
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
		GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, fboPointer);
		for (int a = 0; a < textures.length; a++) {
			this.textures[a] = GL11.glGenTextures();
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, textures[a]);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL30.GL_RGBA16F, (int) Settings.Window.Width, (int) Settings.Window.Height, 0, GL11.GL_RGBA, GL11.GL_FLOAT, (java.nio.ByteBuffer) null);
			GL30.glFramebufferTexture2D(GL30.GL_DRAW_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0 + a, GL11.GL_TEXTURE_2D, textures[a], 0);
		}
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, depthTexture);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL30.GL_DEPTH_COMPONENT32F, (int) Settings.Window.Width, (int) Settings.Window.Height, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (java.nio.ByteBuffer) null);
		GL30.glFramebufferTexture2D(GL30.GL_DRAW_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, depthTexture, 0);
		int status = GL30.glCheckFramebufferStatus(GL30.GL_FRAMEBUFFER);
		if( status != GL30.GL_FRAMEBUFFER_COMPLETE)
			Logger.log("GeometryBuffer", "Can't initialize an FBO render texture. FBO initialization failed.", Log.ERROR);
		GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, 0);
	}
	
	public void bindWriting() {
		//Bind the FBO
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, this.fboPointer);
		int buffers[] = { GL30.GL_COLOR_ATTACHMENT0, GL30.GL_COLOR_ATTACHMENT1, GL30.GL_COLOR_ATTACHMENT2 };
		GL20.glDrawBuffers(BufferUtils.createFlippedBuffer(buffers));
	}
	
	public void bindReading() {
		GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, fboPointer);
	}
	
	public void setReadBuffer(int type) {
		GL11.glReadBuffer(GL30.GL_COLOR_ATTACHMENT0 + type);
	}
	
	public void unbind() {
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
	}
	
}