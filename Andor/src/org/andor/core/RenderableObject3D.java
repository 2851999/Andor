/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import org.lwjgl.opengl.GL11;

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
	
	/* The method used to render this object */
	public void render() {
		//Push the current matrix
		GL11.glPushMatrix();
		
		//Move to the correct position
		GL11.glTranslatef(this.getPosition().x, this.getPosition().y, this.getPosition().z);
		
		//Rotate by the specified amount
		GL11.glRotatef(this.rotation.x, 1, 0, 0);
		GL11.glRotatef(this.rotation.y, 0, 1, 0);
		GL11.glRotatef(this.rotation.z, 0, 0, 1);
		
		GL11.glScalef(this.scale.x, this.scale.y, this.scale.z);
		
		//Render the object using the renderer
		this.renderer.render();
		
		//Pop the current matrix
		GL11.glPopMatrix();
	}
	
	/* The method used to setup this object given the render mode
	 * and the vertices data */
	public void setup(int renderMode, float[] verticesData) {
		//Create the renderer
		this.renderer = new Renderer(renderMode, Renderer.VERTEX_VALUES_COUNT_3D);
		//Set the correct values
		this.renderer.setValues(verticesData);
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
	/* The method used to setup this object given the render mode,
	 * the vertices data and the colour data */
	public void setup(int renderMode, float[] verticesData, float[] colourData) {
		//Create the renderer
		this.renderer = new Renderer(renderMode, Renderer.VERTEX_VALUES_COUNT_3D);
		//Set the correct values
		this.renderer.setValues(verticesData, colourData);
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
	/* The method used to setup this object given the render mode,
	 * the vertices data, the colour data and the texture data */
	public void setup(int renderMode, float[] verticesData, float[] colourData, float[] textureData) {
		//Create the renderer
		this.renderer = new Renderer(renderMode, Renderer.VERTEX_VALUES_COUNT_3D);
		//Set the correct values
		this.renderer.setValues(verticesData, colourData, textureData);
		//Setup the buffers
		this.renderer.setupBuffers();
	}
	
}