/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import java.nio.FloatBuffer;

import org.andor.utils.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class Renderer {
	
	/* The numbers of values for the vertices in both 2D and 3D space */
	public static final int VERTEX_VALUES_COUNT_2D = 2;
	public static final int VERTEX_VALUES_COUNT_3D = 3;
	
	/* The default number of values for the colour and texture data */
	public static final int DEFAULT_COLOUR_VALUES_COUNT = 4;
	public static final int DEFAULT_TEXTURE_VALUES_COUNT = 2;
	
	/* The number of values for each piece of data */
	public int vertexValuesCount = 0;
	public int colourValuesCount = DEFAULT_COLOUR_VALUES_COUNT;
	public int textureValuesCount = DEFAULT_TEXTURE_VALUES_COUNT;
	
	/* The data used for rendering */
	public float[] verticesData;
	public float[] colourData;
	public float[] textureData;
	
	/* The buffers */
	public FloatBuffer verticesBuffer;
	public FloatBuffer coloursBuffer;
	public FloatBuffer texturesBuffer;
	
	/* The handles for each buffer */
	public int verticesHandle;
	public int coloursHandle;
	public int texturesHandle;
	
	/* The render mode */
	public int renderMode;
	
	/* The constructor with the render mode and the 
	 * number of vertex values given */
	public Renderer(int renderMode, int vertexValuesCount) {
		//Assign the variables
		this.renderMode = renderMode;
		this.vertexValuesCount = vertexValuesCount;
	}
	
	/* The method used to setup the buffers,
	 * assumes the vertices have already been set */
	public void setupBuffers() {
		//Create the vertices buffer
		this.verticesBuffer = BufferUtils.createFlippedBuffer(this.verticesData);
		
		//Setup the vertices handle
		this.verticesHandle = GL15.glGenBuffers();
		
		//Bind the vertices buffer and give OpenGL the data
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.verticesHandle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.verticesBuffer, GL15.GL_STATIC_DRAW);
		GL11.glVertexPointer(this.vertexValuesCount, GL11.GL_FLOAT, 0, 0L);
		
		//Check to see whether the colours have been set
		if (this.colourData!= null) {
			//Create the colours buffer
			this.coloursBuffer = BufferUtils.createFlippedBuffer(this.colourData);
			
			//Setup the colours handle
			this.coloursHandle = GL15.glGenBuffers();
			
			//Bind the colours buffer and give OpenGL the data
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.coloursHandle);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.coloursBuffer, GL15.GL_STATIC_DRAW);
			GL11.glColorPointer(this.colourValuesCount, GL11.GL_FLOAT, 0, 0L);
		}
		
		//Check to see whether the texture coordinates have been set
		if (this.textureData != null) {
			//Create the texture coordinates buffer
			this.texturesBuffer = BufferUtils.createFlippedBuffer(this.textureData);
			
			//Setup the texture coordinates handle
			this.texturesHandle = GL15.glGenBuffers();
			
			//Bind the texture coordinates buffer and give OpenGL the data
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.texturesHandle);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.texturesBuffer, GL15.GL_STATIC_DRAW);
			GL11.glTexCoordPointer(this.texturesHandle, GL11.GL_FLOAT, 0, 0L);
		}
	}
	
	/* The method used to draw the object */
	public void render() {
		//Enable the arrays as needed
		GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
		if (this.colourData != null)
			GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
		if (this.textureData != null)
			GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		//Draw the arrays
		GL11.glDrawArrays(this.renderMode, 0, this.verticesData.length / this.vertexValuesCount);
		//Disable the arrays as needed
		if (this.textureData != null)
			GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
		if (this.colourData != null)
			GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);
		GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
	}
	
	/* The method used to set the vertices */
	public void setValues(float[] verticesData) {
		this.verticesData = verticesData;
	}
	
	/* The method used to set the vertices and the colours */
	public void setValues(float[] verticesData, float[] colourData) {
		this.verticesData = verticesData;
		this.colourData = colourData;
	}
	
	/* The method used to set the vertices, colours and the texture coordinates */
	public void setValues(float[] verticesData, float[] colourData, float[] textureData) {
		this.verticesData = verticesData;
		this.colourData = colourData;
		this.textureData = textureData;
	}
	
}