/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.render;

import java.util.Arrays;

import org.andor.core.Colour;
import org.andor.core.Image;
import org.andor.core.Matrix;
import org.andor.core.Object2DBuilder;
import org.andor.core.RenderableObject2D;
import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.Vector3D;
import org.andor.core.lighting.ShadowData;
import org.andor.utils.GLUtils;
import org.andor.utils.OpenGLUtils;

public class Filter {
	
	/* The shader */
	public Shader filter;
	
	/* The source texture */
	public Image source;
	
	/* The FBO that is rendered to */
	public FBO destination;
	
	/* The width and height */
	public float width;
	public float height;
	
	/* The plane */
	public RenderableObject2D plane;
	
	/* The constructor */
	public Filter(Shader filter, float width, float height) {
		//Assign the variables
		this.filter = filter;
		this.width = width;
		this.height = height;
		this.plane = Object2DBuilder.createQuad(new Image(), width, height, Colour.WHITE);
	}
	
	/* The constructor */
	public Filter(Shader filter, Image source, FBO destination, float width, float height) {
		//Assign the variables
		this.filter = filter;
		this.source = source;
		this.destination = destination;
		this.width = width;
		this.height = height;
		this.plane = Object2DBuilder.createQuad(new Image(), width, height, Colour.WHITE);
	}
	
	/* The method used to apply this filter */
	public void apply() {
		this.apply(source, destination);
	}
	
	/* The method used to apply this filter */
	public void apply(Image source, FBO destination) {
		//Bind the FBO
		destination.bind();
		
		//Setup the buffers
		OpenGLUtils.clearDepthBuffer();
		OpenGLUtils.disableDepthTest();
		OpenGLUtils.clearColourBuffer();
		OpenGLUtils.enableTexture2D();
		
		//Save the current view matrix
		float[] clone = Arrays.copyOf(Matrix.projectionMatrix.getValues(), 16);
		float[] clone2 = Arrays.copyOf(Matrix.viewMatrix.getValues(), 16);
		
		Matrix.projectionMatrix = Matrix.ortho(0, width, height, 0, -1, 1);
		Matrix.loadIdentity(Matrix.viewMatrix);
		Matrix.viewMatrix = Matrix.scale(Matrix.viewMatrix, new Vector3D(1, 1f, 1f));
		Matrix.viewMatrix = Matrix.rotate(Matrix.viewMatrix, 0, 1, 0, 0);
		Matrix.viewMatrix = Matrix.rotate(Matrix.viewMatrix, 0, 0, 1, 0);
		Matrix.viewMatrix = Matrix.rotate(Matrix.viewMatrix, 0, 0, 0, 1);
		Matrix.viewMatrix = Matrix.translate(Matrix.viewMatrix, new Vector3D(0, 0, 0));
		
		//Replace the shader in the render pass
		Renderer.getPass().customShader = ShadowData.filterShader;
		
		GLUtils.viewport(0, 0, (int) width, (int) height);
		
		//Create a plane
		plane.setTexture(source);
		//Render the plane
		plane.render();
		
		GLUtils.viewport(0, 0, (int) Settings.Window.Width, (int) Settings.Window.Height);
		
		Matrix.projectionMatrix.values = clone;
		Matrix.viewMatrix.values = clone2;
		
		//Reset the shader in the render pass
		Renderer.getPass().customShader = null;
		
		//Unbind the FBO
		destination.unbind();
		
		OpenGLUtils.enableDepthTest();
	}
	
	/* The getters and setters */
	public void setShader(Shader filter) { this.filter = filter; }
	public void setSource(Image source) { this.source = source; }
	public void setDestination(FBO destination) { this.destination = destination; }
	public Shader getShader() { return this.filter; }
	public Image getSource() { return this.source; }
	public FBO getDestination() { return this.destination; }
	
}