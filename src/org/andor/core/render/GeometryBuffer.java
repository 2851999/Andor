/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.render;

import org.andor.core.Settings;
import org.andor.core.TextureParameters;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;

public class GeometryBuffer {
	
	/* The different types of textures associeted with this geometry buffer */
	public static final int TYPE_POSITION = 0;
	public static final int TYPE_DIFFUSE = 1;
	public static final int TYPE_NORMAL = 2;
	public static final int TYPE_DEPTH = 3;
	
	/* The FBO */
	public FBO fbo;
	
	/* The constructor */
	public GeometryBuffer() {
		//Assign the values
		this.fbo = new FBO(GL30.GL_FRAMEBUFFER);
		TextureParameters parameters = new TextureParameters().setFilter(GL11.GL_NEAREST).setClamp(true);
		this.fbo.add(new RenderTexture((int) Settings.Window.Width, (int) Settings.Window.Height, GL30.GL_RGBA16F, GL11.GL_RGBA, GL30.GL_COLOR_ATTACHMENT0, GL11.GL_FLOAT, parameters));
		this.fbo.add(new RenderTexture((int) Settings.Window.Width, (int) Settings.Window.Height, GL30.GL_RGBA16F, GL11.GL_RGBA, GL30.GL_COLOR_ATTACHMENT1, GL11.GL_FLOAT, parameters));
		this.fbo.add(new RenderTexture((int) Settings.Window.Width, (int) Settings.Window.Height, GL30.GL_RGBA16F, GL11.GL_RGBA, GL30.GL_COLOR_ATTACHMENT2, GL11.GL_FLOAT, parameters));
		this.fbo.add(new RenderTexture((int) Settings.Window.Width, (int) Settings.Window.Height, GL14.GL_DEPTH_COMPONENT32, GL11.GL_DEPTH_COMPONENT, GL30.GL_DEPTH_ATTACHMENT, GL11.GL_FLOAT, parameters));
		this.fbo.setup();
	}
	
	/* The method used to get a specific texture */
	public RenderTexture getTexture(int texture) {
		//Return the texture
		return this.fbo.getTexture(texture);
	}
	
	/* The method used to bind this FBO ready to write to it */
	public void bindWriting() {
		//Bind the FBO
		this.fbo.bindWriting();
	}
	
	/* The method used to unbind the FBO */
	public void unbind() {
		//Unbind the FBO
		this.fbo.unbind();
	}
	
}