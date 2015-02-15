/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.experimental.deferredrendering1;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex3f;

import org.andor.core.BaseGame;
import org.andor.core.Colour;
import org.andor.core.Image;
import org.andor.core.Object2DBuilder;
import org.andor.core.RenderableObject2D;
import org.andor.core.Renderer;
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
	
	public RenderableObject2D test;
	
	/* The constructor */
	public FBOTest() {
		
	}
	
	/* The method used to setup this frame buffer */
	public void create() {
		this.pointer = GL30.glGenFramebuffers();
		this.colourBufferPointer = GL11.glGenTextures();
		this.depthBufferPointer = GL11.glGenTextures();
		
		test = Object2DBuilder.createQuad(new Image(10, 10), 500f, 500f, new Colour[] { Colour.RED, Colour.GREEN, Colour.BLUE, Colour.WHITE });
		
		//Switch to the frame buffer
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, this.pointer);
		
		//Initialise the textures
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.colourBufferPointer);
		//Make the colour buffer linear filtered
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		//Create the texture data
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, (int) Settings.Window.Width, (int) Settings.Window.Height, 0, GL11.GL_RGBA, GL11.GL_INT, (java.nio.ByteBuffer) null);
		//Attach the texture to the frame buffer
		GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, this.colourBufferPointer, 0);
		
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
		GL11.glClearColor (1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glLoadIdentity();
		OpenGLUtils.setupOrtho(1, -1);
		//Set the colour
		//GL11.glColor3f(1.0f, 1.0f, 1.0f);
		test.render();
        //Enable texturing
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        //Switch to rendering on frame buffer
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.colourBufferPointer);
        Renderer.globalTexture = new Image(0, 0);
		OpenGLUtils.setupOrtho(1, -1);
		
		//GL11.glColor3f(1.0f, 1.0f, 1.0f);
        test.render();
        Renderer.globalTexture = null;
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glFlush();
	}
	
	public void draw() {
        glBegin(GL_QUADS);
        // Front Face
        glTexCoord2f(0.0f, 0.0f); glVertex3f(-1.0f, -1.0f,  1.0f);  // Bottom Left Of The Texture and Quad
        glTexCoord2f(1.0f, 0.0f); glVertex3f( 1.0f, -1.0f,  1.0f);  // Bottom Right Of The Texture and Quad
        glTexCoord2f(1.0f, 1.0f); glVertex3f( 1.0f,  1.0f,  1.0f);  // Top Right Of The Texture and Quad
        glTexCoord2f(0.0f, 1.0f); glVertex3f(-1.0f,  1.0f,  1.0f);  // Top Left Of The Texture and Quad
        // Back Face
        glTexCoord2f(1.0f, 0.0f); glVertex3f(-1.0f, -1.0f, -1.0f);  // Bottom Right Of The Texture and Quad
        glTexCoord2f(1.0f, 1.0f); glVertex3f(-1.0f,  1.0f, -1.0f);  // Top Right Of The Texture and Quad
        glTexCoord2f(0.0f, 1.0f); glVertex3f( 1.0f,  1.0f, -1.0f);  // Top Left Of The Texture and Quad
        glTexCoord2f(0.0f, 0.0f); glVertex3f( 1.0f, -1.0f, -1.0f);  // Bottom Left Of The Texture and Quad
        // Top Face
        glTexCoord2f(0.0f, 1.0f); glVertex3f(-1.0f,  1.0f, -1.0f);  // Top Left Of The Texture and Quad
        glTexCoord2f(0.0f, 0.0f); glVertex3f(-1.0f,  1.0f,  1.0f);  // Bottom Left Of The Texture and Quad
        glTexCoord2f(1.0f, 0.0f); glVertex3f( 1.0f,  1.0f,  1.0f);  // Bottom Right Of The Texture and Quad
        glTexCoord2f(1.0f, 1.0f); glVertex3f( 1.0f,  1.0f, -1.0f);  // Top Right Of The Texture and Quad
        // Bottom Face
        glTexCoord2f(1.0f, 1.0f); glVertex3f(-1.0f, -1.0f, -1.0f);  // Top Right Of The Texture and Quad
        glTexCoord2f(0.0f, 1.0f); glVertex3f( 1.0f, -1.0f, -1.0f);  // Top Left Of The Texture and Quad
        glTexCoord2f(0.0f, 0.0f); glVertex3f( 1.0f, -1.0f,  1.0f);  // Bottom Left Of The Texture and Quad
        glTexCoord2f(1.0f, 0.0f); glVertex3f(-1.0f, -1.0f,  1.0f);  // Bottom Right Of The Texture and Quad
        // Right face
        glTexCoord2f(1.0f, 0.0f); glVertex3f( 1.0f, -1.0f, -1.0f);  // Bottom Right Of The Texture and Quad
        glTexCoord2f(1.0f, 1.0f); glVertex3f( 1.0f,  1.0f, -1.0f);  // Top Right Of The Texture and Quad
        glTexCoord2f(0.0f, 1.0f); glVertex3f( 1.0f,  1.0f,  1.0f);  // Top Left Of The Texture and Quad
        glTexCoord2f(0.0f, 0.0f); glVertex3f( 1.0f, -1.0f,  1.0f);  // Bottom Left Of The Texture and Quad
        // Left Face
        glTexCoord2f(0.0f, 0.0f); glVertex3f(-1.0f, -1.0f, -1.0f);  // Bottom Left Of The Texture and Quad
        glTexCoord2f(1.0f, 0.0f); glVertex3f(-1.0f, -1.0f,  1.0f);  // Bottom Right Of The Texture and Quad
        glTexCoord2f(1.0f, 1.0f); glVertex3f(-1.0f,  1.0f,  1.0f);  // Top Right Of The Texture and Quad
        glTexCoord2f(0.0f, 1.0f); glVertex3f(-1.0f,  1.0f, -1.0f);  // Top Left Of The Texture and Quad
        glEnd();
	}
	
	public static void main(String[] args) {
		new FBOTest();
	}
	
}