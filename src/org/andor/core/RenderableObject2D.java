/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import java.util.Arrays;

import org.andor.core.render.Renderer;
import org.andor.utils.ObjectBuilderUtils;

public class RenderableObject2D extends Object2D {
	
	/* The renderer used to render this object */
	public Renderer renderer;
	
	/* The clone of the previous matrix */
	public float[] clone;
	
	/* The default constructor */
	public RenderableObject2D() {
		
	}
	
	/* The constructor with the render mode,
	 * the vertices data, width and height given */
	public RenderableObject2D(int renderMode, float[] verticesData, float width, float height) {
		//Call the super constructor
		super(width, height);
		//Setup this object
		this.setup(renderMode, verticesData);
	}
	
	/* The constructor with the render mode,
	 * the vertices data, the colour data, width and height given */
	public RenderableObject2D(int renderMode, float[] verticesData, float[] colourData, float width, float height) {
		//Call the super constructor
		super(width, height);
		//Setup this object
		this.setup(renderMode, verticesData, colourData);
	}
	
	/* The constructor with the render mode,
	 * the vertices data, the colour data, the texture data width and height given */
	public RenderableObject2D(int renderMode, float[] verticesData, float[] colourData, float[] textureData, float width, float height) {
		//Call the super constructor
		super(width, height);
		//Setup this object
		this.setup(renderMode, verticesData, colourData, textureData);
	}
	
	/* The constructor with the render mode,
	 * the vertices data, width and height given */
	public RenderableObject2D(int renderMode, float[] verticesData, short[] drawOrder, float width, float height) {
		//Call the super constructor
		super(width, height);
		//Setup this object
		this.setup(renderMode, verticesData, drawOrder);
	}
	
	/* The constructor with the render mode,
	 * the vertices data, the colour data, width and height given */
	public RenderableObject2D(int renderMode, float[] verticesData, float[] colourData, short[] drawOrder, float width, float height) {
		//Call the super constructor
		super(width, height);
		//Setup this object
		this.setup(renderMode, verticesData, colourData, drawOrder);
	}
	
	/* The constructor with the render mode,
	 * the vertices data, the colour data, the texture data width and height given */
	public RenderableObject2D(int renderMode, float[] verticesData, float[] colourData, float[] textureData, short[] drawOrder, float width, float height) {
		//Call the super constructor
		super(width, height);
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
	}
	
	/* The method used to update the current view matrix */
	public void updateViewMatrix() {
		//Apply this models transform
		if (renderer != null) {
			Matrix4D matrix = new Matrix4D();
			Matrix.loadIdentity(matrix);
			matrix = Matrix.transform(matrix, this.getPosition(), this.getRotation(), this.getScale());
			
			if (! Arrays.equals(renderer.modelMatrix.getValues(), matrix.getValues())) {
				renderer.modelMatrix = matrix;
				renderer.updateModelMatrix();
			}
			//Calculate the matrices for rendering
			Matrix.calculateRenderMatrices(renderer.modelMatrix);
		}
	}
	
	/* The method used to setup this object given the render mode
	 * and the vertices data */
	public void setup(int renderMode, float[] verticesData) {
		//Create the renderer
		this.renderer = new Renderer(renderMode, Renderer.VERTEX_VALUES_COUNT_2D);
		//Set the correct values
		this.renderer.setValues(verticesData);
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
	/* The method used to setup this object given the render mode,
	 * the vertices data and the colour data */
	public void setup(int renderMode, float[] verticesData, float[] colourData) {
		//Create the renderer
		this.renderer = new Renderer(renderMode, Renderer.VERTEX_VALUES_COUNT_2D);
		//Set the correct values
		this.renderer.setValues(verticesData, colourData);
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
	/* The method used to setup this object given the render mode,
	 * the vertices data, the colour data and the texture data */
	public void setup(int renderMode, float[] verticesData, float[] colourData, float[] textureData) {
		//Create the renderer
		this.renderer = new Renderer(renderMode, Renderer.VERTEX_VALUES_COUNT_2D);
		//Set the correct values
		this.renderer.setValues(verticesData, colourData, textureData);
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
	/* The method used to setup this object given the render mode
	 * and the vertices data */
	public void setup(int renderMode, float[] verticesData, short[] drawOrder) {
		//Create the renderer
		this.renderer = new Renderer(renderMode, Renderer.VERTEX_VALUES_COUNT_2D);
		//Set the correct values
		this.renderer.setValues(verticesData, drawOrder);
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
	/* The method used to setup this object given the render mode,
	 * the vertices data and the colour data */
	public void setup(int renderMode, float[] verticesData, float[] colourData, short[] drawOrder) {
		//Create the renderer
		this.renderer = new Renderer(renderMode, Renderer.VERTEX_VALUES_COUNT_2D);
		//Set the correct values
		this.renderer.setValues(verticesData, colourData, drawOrder);
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
	/* The method used to setup this object given the render mode,
	 * the vertices data, the colour data and the texture data */
	public void setup(int renderMode, float[] verticesData, float[] colourData, float[] textureData, short[] drawOrder) {
		//Create the renderer
		this.renderer = new Renderer(renderMode, Renderer.VERTEX_VALUES_COUNT_2D);
		//Set the correct values
		this.renderer.setValues(verticesData, colourData, textureData, drawOrder);
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
	/* The method used to assign the texture */
	public void setTexture(Image texture) {
		this.renderer.setTexture(texture);
	}
	
	/* The method used to set the colour of this object */
	public void setColour(Colour colour) {
		//Update the colours
		this.renderer.updateColours(ObjectBuilderUtils.createColourArray(this.renderer.vertices.length, colour));
	}
	
	/* The method used to set the colour of this object */
	public void setColour(Colour[] colours) {
		//Update the colours
		this.renderer.updateColours(ObjectBuilderUtils.createColourArray(this.renderer.vertices.length, colours));
	}
	
}