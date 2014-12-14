/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import java.util.Arrays;

public class RenderableObject3D extends Object3D {
	
	/* The renderer used to render this object */
	public Renderer renderer;
	
	/* The clone of the previous matrix */
	public float[] clone;
	
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
		//Update the view matrix
		this.updateViewMatrix();
		//Make sure the renderer has been initialised
		if (this.renderer != null)
			//Render the object using the renderer
			this.renderer.render();
		//Restore the view matrix
		this.restoreViewMatrix();
	}
	
	/* The method used to update the current view matrix */
	public void updateViewMatrix() {
		//Get the position
		Vector3D p = this.getPosition();
		//Get the rotation
		Vector3D r = this.getRotation();
		//Get the scale
		Vector3D s = this.getScale();
		//Save the current matrix
		clone = Arrays.copyOf(Matrix.modelMatrix.getValues(), 16);
		//Move to the correct position
		Matrix.modelMatrix = Matrix.translate(Matrix.modelMatrix, p);
		//Rotate by the specified amount
		Matrix.modelMatrix = Matrix.rotate(Matrix.modelMatrix, r.x, 1, 0, 0);
		Matrix.modelMatrix = Matrix.rotate(Matrix.modelMatrix, r.y, 0, 1, 0);
		Matrix.modelMatrix = Matrix.rotate(Matrix.modelMatrix, r.z, 0, 0, 1);
		//Scale by the specified amount
		Matrix.modelMatrix = Matrix.scale(Matrix.modelMatrix, s);
	}
	
	/* The method used to restore the current view matrix */
	public void restoreViewMatrix() {
		//Restore the current matrix
		Matrix.modelMatrix.values = clone;
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