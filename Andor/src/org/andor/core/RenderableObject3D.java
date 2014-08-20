/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import java.util.Arrays;

import org.andor.core.android.AndroidDisplayRenderer;
import org.lwjgl.opengl.GL11;

import android.opengl.Matrix;

public class RenderableObject3D extends Object3D {
	
	/* The renderer used to render this object */
	public Renderer renderer;
	
	/* The default constructor */
	public RenderableObject3D() {
		
	}
	
	/* The constructor with the render mode,
	 * the vertices data, width, height and depth given */
	public RenderableObject3D(int renderMode, float[] verticesData, float width, float height, float depth) {
		//Call the super constructor
		super(width, height, depth);
		//Setup this object
		this.setup(renderMode, verticesData);
	}
	
	/* The constructor with the render mode,
	 * the vertices data, the colour data, width, height and depth given */
	public RenderableObject3D(int renderMode, float[] verticesData, float[] colourData, float width, float height, float depth) {
		//Call the super constructor
		super(width, height, depth);
		//Setup this object
		this.setup(renderMode, verticesData, colourData);
	}
	
	/* The constructor with the render mode,
	 * the vertices data, the colour data, the texture data, width, height and depth given */
	public RenderableObject3D(int renderMode, float[] verticesData, float[] colourData, float[] textureData, float width, float height, float depth) {
		//Call the super constructor
		super(width, height, depth);
		//Setup this object
		this.setup(renderMode, verticesData, colourData, textureData);
	}
	
	/* The constructor with the render mode,
	 * the vertices data, width and height given */
	public RenderableObject3D(int renderMode, float[] verticesData, short[] drawOrder, float width, float height, float depth) {
		//Call the super constructor
		super(width, height, depth);
		//Setup this object
		this.setup(renderMode, verticesData, drawOrder);
	}
	
	/* The constructor with the render mode,
	 * the vertices data, the colour data, width and height given */
	public RenderableObject3D(int renderMode, float[] verticesData, float[] colourData, short[] drawOrder, float width, float height, float depth) {
		//Call the super constructor
		super(width, height, depth);
		//Setup this object
		this.setup(renderMode, verticesData, colourData, drawOrder);
	}
	
	/* The constructor with the render mode,
	 * the vertices data, the colour data, the texture data width and height given */
	public RenderableObject3D(int renderMode, float[] verticesData, float[] colourData, float[] textureData, short[] drawOrder, float width, float height, float depth) {
		//Call the super constructor
		super(width, height, depth);
		//Setup this object
		this.setup(renderMode, verticesData, colourData, textureData, drawOrder);
	}
	
	/* The method used to render this object */
	public void render() {
		//Get the position
		Vector3D p = this.getPosition();
		//Get the rotation
		Vector3D r = this.getRotation();
		//Get the scale
		Vector3D s = this.getScale();
		//Check to see whether Andor is currently running on Android
		if (! Settings.AndroidMode) {
			//Push the current matrix
			GL11.glPushMatrix();
			
			//Move to the correct position
			GL11.glTranslatef(p.x, p.y, p.z);
			
			//Rotate by the specified amount
			GL11.glRotatef(r.x, 1, 0, 0);
			GL11.glRotatef(r.y, 0, 1, 0);
			GL11.glRotatef(r.z, 0, 0, 1);
			
			//Scale by the specified amount
			GL11.glScalef(s.x, s.y, s.z);
			
			//Render the object using the renderer
			this.renderer.render();
			
			//Pop the current matrix
			GL11.glPopMatrix();
		} else {
			//Save the current matrix
			float[] clone = Arrays.copyOf(AndroidDisplayRenderer.mMVPMatrix, AndroidDisplayRenderer.mMVPMatrix.length);
			//Move to the correct position
			Matrix.translateM(AndroidDisplayRenderer.mMVPMatrix, 0, p.x, p.y, p.z);
			//Rotate by the specified amount
			Matrix.rotateM(AndroidDisplayRenderer.mMVPMatrix, 0, r.x, 1, 0, 0);
			Matrix.rotateM(AndroidDisplayRenderer.mMVPMatrix, 0, r.y, 0, 1, 0);
			Matrix.rotateM(AndroidDisplayRenderer.mMVPMatrix, 0, r.z, 0, 0, 1);
			//Scale by the specified amount
			Matrix.scaleM(AndroidDisplayRenderer.mMVPMatrix, 0, s.x, s.y, s.z);
			//Render the object using the renderer
			this.renderer.render();
			//Restore the current matrix
			AndroidDisplayRenderer.mMVPMatrix = clone;
		}
	}
	
	/* The method used to setup this object given the render mode
	 * and the vertices data */
	public void setup(int renderMode, float[] verticesData) {
		//Create the renderer
		this.renderer = Renderer.create(renderMode, Renderer.VERTEX_VALUES_COUNT_3D);
		//Set the correct values
		this.renderer.setValues(verticesData);
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
	/* The method used to setup this object given the render mode,
	 * the vertices data and the colour data */
	public void setup(int renderMode, float[] verticesData, float[] colourData) {
		//Create the renderer
		this.renderer = Renderer.create(renderMode, Renderer.VERTEX_VALUES_COUNT_3D);
		//Set the correct values
		this.renderer.setValues(verticesData, colourData);
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
	/* The method used to setup this object given the render mode,
	 * the vertices data, the colour data and the texture data */
	public void setup(int renderMode, float[] verticesData, float[] colourData, float[] textureData) {
		//Create the renderer
		this.renderer = Renderer.create(renderMode, Renderer.VERTEX_VALUES_COUNT_3D);
		//Set the correct values
		this.renderer.setValues(verticesData, colourData, textureData);
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
	/* The method used to setup this object given the render mode
	 * and the vertices data */
	public void setup(int renderMode, float[] verticesData, short[] drawOrder) {
		//Create the renderer
		this.renderer = Renderer.create(renderMode, Renderer.VERTEX_VALUES_COUNT_3D);
		//Set the correct values
		this.renderer.setValues(verticesData, drawOrder);
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
	/* The method used to setup this object given the render mode,
	 * the vertices data and the colour data */
	public void setup(int renderMode, float[] verticesData, float[] colourData, short[] drawOrder) {
		//Create the renderer
		this.renderer = Renderer.create(renderMode, Renderer.VERTEX_VALUES_COUNT_3D);
		//Set the correct values
		this.renderer.setValues(verticesData, colourData, drawOrder);
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
	/* The method used to setup this object given the render mode,
	 * the vertices data, the colour data and the texture data */
	public void setup(int renderMode, float[] verticesData, float[] colourData, float[] textureData, short[] drawOrder) {
		//Create the renderer
		this.renderer = Renderer.create(renderMode, Renderer.VERTEX_VALUES_COUNT_3D);
		//Set the correct values
		this.renderer.setValues(verticesData, colourData, textureData, drawOrder);
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
}