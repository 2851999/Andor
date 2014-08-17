/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.android;

import org.andor.core.Renderer;
import org.andor.core.Shader;
import org.andor.utils.ArrayUtils;
import org.andor.utils.BufferUtils;
import org.andor.utils.ShaderUtils;

import android.opengl.GLES20;

public class AndroidRenderer extends Renderer {
	
	/* The Android shader code */
	private static final String[] androidVertexShaderCode = new String[] {
		    "attribute vec4 vPosition;",
		    "void main() {",
		    "  gl_Position = vPosition;",
		    "}" };
	
	private static final String[] androidFragmentShaderCode = new String[] {
		    "precision mediump float;",
		    "uniform vec4 vColor;",
		    "void main() {",
		    "  gl_FragColor = vColor;",
		    "}" };
	
	/* The Android shader */
	public Shader androidShader;
	
	/* The constructor with the render mode and the 
	 * number of vertex values given */
	public AndroidRenderer(int renderMode, int vertexValuesCount) {
		super(renderMode, vertexValuesCount);
		//Add this renderer to the list
		allRenderers.add(this);
		usage = GLES20.GL_STATIC_DRAW;
		//Setup the Android shader
		this.androidShader = new Shader();
		this.androidShader.vertexShader = ShaderUtils.createShader(ArrayUtils.toStringList(androidVertexShaderCode), GLES20.GL_VERTEX_SHADER);
		this.androidShader.fragmentShader = ShaderUtils.createShader(ArrayUtils.toStringList(androidFragmentShaderCode), GLES20.GL_FRAGMENT_SHADER);
		this.androidShader.create();
	}
	
	/* The method used to setup the buffers,
	 * assumes the vertices have already been set */
	public void setupBuffers() {
		//Create the vertices buffer
		this.verticesBuffer = BufferUtils.createFlippedBuffer(this.verticesData);
		
		//Check to see whether the normals have been set
		if (this.normalsData != null) {
			//Create the normals buffer
			this.normalsBuffer = BufferUtils.createFlippedBuffer(this.normalsData);
		}
		
		//Check to see whether the colours have been set
		if (this.colourData!= null) {
			//Create the colours buffer
			this.coloursBuffer = BufferUtils.createFlippedBuffer(this.colourData);
		}
		
		//Check to see whether the texture coordinates have been set
		if (this.textureData != null) {
			//Create the texture coordinates buffer
			this.texturesBuffer = BufferUtils.createFlippedBuffer(this.textureData);
		}
	}
	
	/* The method used to draw the object */
	public void render() {
	    // Add program to OpenGL ES environment
	    GLES20.glUseProgram(androidShader.program);

	    // get handle to vertex shader's vPosition member
	    int mPositionHandle = GLES20.glGetAttribLocation(androidShader.program, "vPosition");

	    // Enable a handle to the triangle vertices
	    GLES20.glEnableVertexAttribArray(mPositionHandle);

	    // Prepare the triangle coordinate data
	    GLES20.glVertexAttribPointer(mPositionHandle, this.vertexValuesCount,
	                                 GLES20.GL_FLOAT, false,
	                                 this.vertexValuesCount * Float.BYTES, this.verticesBuffer);

	    // get handle to fragment shader's vColor member
	    int mColorHandle = GLES20.glGetUniformLocation(androidShader.program, "vColor");

	    // Set colour for drawing the triangle
	    GLES20.glUniform4fv(mColorHandle, 1, colourData, 0);

	    // Draw the triangle
	    GLES20.glDrawArrays(this.renderMode, 0, this.verticesData.length / this.vertexValuesCount);

	    // Disable vertex array
	    GLES20.glDisableVertexAttribArray(mPositionHandle);
	}
	
	/* The method used to update the vertices */
	public void updateVertices(float[] verticesData) {
		//Set the vertices data
		this.verticesData = verticesData;
		//Create the vertices buffer
		this.verticesBuffer = BufferUtils.createFlippedBuffer(this.verticesData);
	}
	
	/* The method used to update the normals */
	public void updateNormals(float[] normalsData) {
		//Set the normals data
		this.normalsData = normalsData;
		//Create the normals buffer
		this.normalsBuffer = BufferUtils.createFlippedBuffer(this.normalsData);
	}
	
	/* The method used to update the colours */
	public void updateColours(float[] colourData) {
		//Set the colour data
		this.colourData = colourData;
		//Create the colour buffer
		this.coloursBuffer = BufferUtils.createFlippedBuffer(this.colourData);
	}
	
	/* The method used to update the texture coordinates */
	public void updateTextures(float[] textureData) {
		//Set the texture data
		this.textureData = textureData;
		//Create the colour buffer
		this.texturesBuffer = BufferUtils.createFlippedBuffer(this.textureData);
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
	
	/* The method used to release this renderer */
	public void release() {
		
	}
	
}