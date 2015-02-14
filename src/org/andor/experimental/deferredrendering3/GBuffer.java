package org.andor.experimental.deferredrendering3;

import org.andor.core.Settings;
import org.andor.utils.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class GBuffer {
	
	public static final int TYPE_POSITION = 0;
	public static final int TYPE_DIFFUSE = 1;
	public static final int TYPE_NORMAL = 2;
	public static final int NUMBER_OF_TEXTURES = 3;
	
	public int fboPointer;
	public int[] textures;
	public int depthTexture;
	
	public GBuffer() {
		fboPointer = GL30.glGenFramebuffers();
		GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, fboPointer);
		textures = new int[NUMBER_OF_TEXTURES];
		depthTexture = GL11.glGenTextures();
		for (int a = 0; a < textures.length; a++) {
			textures[a] = GL11.glGenTextures();
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
			System.out.println("Can't initialize an FBO render texture. FBO initialization failed.");
		GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, 0);
	}
	
	public void bindWriting() {
		// Bind our FBO and set the viewport to the proper size
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, this.fboPointer);

		// Clear the render targets
		GL11.glClearColor(1.0f, 0.0f, 0.0f, 1.0f );

		// Specify what to render an start acquiring
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