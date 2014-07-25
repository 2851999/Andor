/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.lwjgl.opengl.GL11;

public class RenderableObject2D extends Object2D {
	
	/* The renderer used to render this object */
	public Renderer renderer;
	
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
	
	/* The method used to render this object */
	public void render() {
		//Push the current matrix
		GL11.glPushMatrix();
		
		//Move to the correct position (For rotating)
		GL11.glTranslatef(this.getPosition().x + this.width / 2, this.getPosition().y + this.height / 2, 0);
		
		//Rotate by the specified amount
		GL11.glRotatef(this.rotation, 0, 0, 1);
		
		//Scale the object
		GL11.glScalef(this.scale.x, this.scale.y, 1);
		
		//Move to the correct position (For rendering)
		GL11.glTranslatef(-this.width / 2, -this.height / 2, 0);
		
		//Render the object using the renderer
		this.renderer.render();
		
		//Pop the current matrix
		GL11.glPopMatrix();
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
	
}