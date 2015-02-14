/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.experimental.deferredrendering2;

import org.andor.core.BaseGame;
import org.andor.core.Colour;
import org.andor.core.Image;
import org.andor.core.Object2DBuilder;
import org.andor.core.Settings;
import org.andor.utils.OpenGLUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;

public class FBOTest extends BaseGame {
	
	/* The pointer to the buffer */
	public int pointer;
	
	/* The test values */
	public int colourBufferPointer;
	public int depthBufferPointer;
	
	public DeferredRenderer renderer;
	public DeferredRenderer quad;
	
	/* The constructor */
	public FBOTest() {
		
	}
	
	/* The method used to setup this frame buffer */
	public void create() {
		this.pointer = GL30.glGenFramebuffers();
		this.colourBufferPointer = GL11.glGenTextures();
		this.depthBufferPointer = GL11.glGenTextures();
		
		renderer = new DeferredRenderer();
		renderer.vertices = Object2DBuilder.createQuadV(100, 100);
		renderer.colours = Object2DBuilder.createColourArray(4, Colour.ARRAY_RGB);
		renderer.drawOrder = Object2DBuilder.createQuadDO();
		renderer.setupBuffers();
		
		quad = new DeferredRenderer();
		quad.vertices = Object2DBuilder.createQuadV(Settings.Window.Width, Settings.Window.Height);
		quad.textureCoords = Object2DBuilder.createQuadT(new Image(0, 0));
		quad.colours = Object2DBuilder.createColourArray(4, Colour.WHITE);
		quad.drawOrder = Object2DBuilder.createQuadDO();
		quad.setupBuffers();
		
		//Switch to the frame buffer
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, this.pointer);
		this.colourBufferPointer = GL11.glGenTextures();
		//Initialise the textures
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.colourBufferPointer);
		//Make the colour buffer linear filtered
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		//Create the texture data
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, (int) Settings.Window.Width, (int) Settings.Window.Height, 0, GL11.GL_RGBA, GL11.GL_INT, (java.nio.ByteBuffer) null);
		//Attach the texture to the frame buffer
		GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, this.colourBufferPointer, 0);
		this.depthBufferPointer = GL11.glGenTextures();
		//Bind the depth render buffer
		GL30.glBindRenderbuffer(GL30.GL_FRAMEBUFFER, this.depthBufferPointer);
		//Get the data space for the depth render buffer
		GL30.glRenderbufferStorage(GL30.GL_FRAMEBUFFER, GL14.GL_DEPTH_COMPONENT24, (int) Settings.Window.Width, (int) Settings.Window.Height);
		//Bind it to the render buffer
		GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL30.GL_RENDERBUFFER, this.depthBufferPointer);
		
		//Switch back to normal
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
	}
	
	/* The method used to render the test */
	public void render() {
		//Unbind any previous textures
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		//Switch to rendering to the FBO
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, this.pointer);
		//Clear
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.setupOrtho(1, -1);
		OpenGLUtils.disableTexture2D();
		//Set the colour
		//GL11.glColor3f(1.0f, 1.0f, 1.0f);
		DeferredRenderer.texture = null;
		renderer.render();
        //Switch to rendering on frame buffer
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
		OpenGLUtils.setupOrtho(1, -1);
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.setupRemoveAlpha();
		OpenGLUtils.enableTexture2D();
		
		//GL11.glColor3f(1.0f, 1.0f, 1.0f);
		DeferredRenderer.texture = new Image(0, 0);
		DeferredRenderer.texture.textureId = this.colourBufferPointer;
        quad.render();
	}
	
	public void draw() {
		
	}
	
	public static void main(String[] args) {
		new FBOTest();
	}
	
}