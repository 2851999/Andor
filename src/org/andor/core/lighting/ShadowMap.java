/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.lighting;

import org.andor.core.Image;
import org.andor.core.Settings;
import org.andor.core.TextureParameters;
import org.andor.core.Vector2D;
import org.andor.core.render.FBO;
import org.andor.core.render.Filter;
import org.andor.core.render.RenderTexture;
import org.andor.utils.GLUtils;
import org.andor.utils.OpenGLUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class ShadowMap {
	
	/* The size of this shadow map */
	private float width;
	private float height;
	
	/* The FBO for this shadow map */
	public FBO fbo;
	
	/* The temporary FBO for this shadow map */
	public FBO fboTemp;
	
	/* The gaussian blur filters */
	public Filter gaussianBlur;
	
	/* The constructor */
	public ShadowMap(float width, float height) {
		//Assign the variables
		this.width = width;
		this.height = height;
		//Create the FBO
		this.fbo = new FBO(GL30.GL_FRAMEBUFFER);
		//Add the depth texture
		TextureParameters parameters = new TextureParameters().setFilter(GL11.GL_LINEAR_MIPMAP_LINEAR).setClamp(true);
		this.fbo.add(new RenderTexture((int) width, (int) height, GL30.GL_RG32F, GL11.GL_RGBA, GL30.GL_COLOR_ATTACHMENT0, GL11.GL_FLOAT, parameters));
		//Setup the FBO
		this.fbo.setup();
		
		this.fboTemp = new FBO(GL30.GL_FRAMEBUFFER);
		this.fboTemp.add(new RenderTexture((int) width, (int) height, GL30.GL_RG32F, GL11.GL_RGBA, GL30.GL_COLOR_ATTACHMENT0, GL11.GL_FLOAT, parameters));
		this.fboTemp.setup();
		
		//Setup the gaussian blur filter
		this.gaussianBlur = new Filter(ShadowData.filterShader, this.width, this.height);
	}
	
	/* The method used to bind this shadow map ready for rendering to it */
	public void bind() {
		//Bind the FBO
		this.fbo.bind();
		
		//Setup the buffers
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.enableDepthTest();
		OpenGLUtils.setupRemoveAlpha();
		OpenGLUtils.enableTexture2D();
		
		//Setup the viewport
		GLUtils.viewport(0, 0, (int) this.width, (int) this.height);
	}
	
	/* The method used to unbind this shadow map ready for normal rendering */
	public void unbind() {
		//Unbind the FBO
		this.fbo.unbind();
		GLUtils.viewport(0, 0, (int) Settings.Window.Width, (int) Settings.Window.Height);
	}
	
	/* The method used to apply the gaussian blur filter to this shadow map */
	public void applyGaussianBlur(float blurAmount) {
		//Apply the filter on the x axis
		this.gaussianBlur.getShader().getUniforms().setUniform("blurScale", new Vector2D(1.0f / (this.width * blurAmount), 0));
		this.gaussianBlur.apply(getShadowMap(), this.fboTemp);
		
		//Apply the filter on the y axis
		this.gaussianBlur.getShader().getUniforms().setUniform("blurScale", new Vector2D(0, 1.0f / (this.height * blurAmount)));
		this.gaussianBlur.apply(new Image(this.fboTemp.getTexture(0).getPointer()), this.fbo);
	}
	
	/* The getter and setters */
	public float getWidth() { return this.width; }
	public float getHeight() { return this.height; }
	public Image getShadowMap() { return new Image(this.fbo.getTexture(0).getPointer()); }
	
}