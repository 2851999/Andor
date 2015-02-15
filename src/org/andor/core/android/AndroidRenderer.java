/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.android;

import org.andor.core.Matrix;
import org.andor.core.Renderer;
import org.andor.core.Shader;
import org.andor.utils.ArrayUtils;
import org.andor.utils.BufferUtils;
import org.andor.utils.ShaderUtils;

import android.opengl.GLES20;

public class AndroidRenderer extends Renderer {
	
	/* The default Android shader */
	public static Shader defaultAndroidShader;
	
	/* The constructor with the render mode and the 
	 * number of vertex values given */
	public AndroidRenderer(int renderMode, int vertexValuesCount) {
		super(renderMode, vertexValuesCount);
		//Set the default usage
		usage = GLES20.GL_STATIC_DRAW;
		//Setup the Android shader
		if (defaultAndroidShader == null) {
			defaultAndroidShader = new AndroidShader();
			defaultAndroidShader.vertexShader = ShaderUtils.createShader(ArrayUtils.toStringList(ShaderUtils.vertexAndorMain), GLES20.GL_VERTEX_SHADER);
			defaultAndroidShader.fragmentShader = ShaderUtils.createShader(ArrayUtils.toStringList(ShaderUtils.vertexAndorMain), GLES20.GL_FRAGMENT_SHADER);
			defaultAndroidShader.create();
		}
	}
	
	/* The method used to setup the buffers,
	 * assumes the vertices have already been set */
	public void setupBuffers() {
		//Check to see whether the vertices have been set
		if (this.vertices != null) {
			//Create the vertices buffer
			this.verticesBuffer = BufferUtils.createFlippedBuffer(this.vertices);
			int[] vh = new int[1];
			GLES20.glGenBuffers(1, vh, 0);
			//Setup the vertices handle
			this.verticesHandle = vh[0];
			
			//Bind the vertices buffer and give OpenGL the data
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.verticesHandle);
			GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * vertices.length, this.verticesBuffer, this.usage);
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		}
		
		//Check to see whether the normals have been set
		if (this.normals != null) {
			//Create the normals buffer
			this.normalsBuffer = BufferUtils.createFlippedBuffer(this.normals);
			int[] nh = new int[1];
			GLES20.glGenBuffers(1, nh, 0);
			//Setup the normals handle
			this.normalsHandle = nh[0];
			
			//Bind the normals buffer and give OpenGL the data
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.normalsHandle);
			GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * normals.length, this.normalsBuffer, this.usage);
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		}
		
		//Check to see whether the colours have been set
		if (this.colours!= null) {
			//Create the colours buffer
			this.coloursBuffer = BufferUtils.createFlippedBuffer(this.colours);
			int[] ch = new int[1];
			GLES20.glGenBuffers(1, ch, 0);
			//Setup the colours handle
			this.coloursHandle = ch[0];
			
			//Bind the colours buffer and give OpenGL the data
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.coloursHandle);
			GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * colours.length, this.coloursBuffer, this.usage);
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		}
		
		//Check to see whether the texture coordinates have been set
		if (this.textureCoords != null) {
			//Create the texture coordinates buffer
			this.textureCoordsBuffer = BufferUtils.createFlippedBuffer(this.textureCoords);
			int[] th = new int[1];
			GLES20.glGenBuffers(1, th, 0);
			//Setup the texture coordinates handle
			this.texturesHandle = th[0];
			
			//Bind the texture coordinates buffer and give OpenGL the data
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.texturesHandle);
			GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * textureCoords.length, this.textureCoordsBuffer, this.usage);
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		}
		
		//Check to see whether the draw order has been set
		if (this.drawOrder != null) {
			this.drawOrderBuffer = BufferUtils.createFlippedBuffer(this.drawOrder);
			int[] doh = new int[1];
			GLES20.glGenBuffers(1, doh, 0);
			this.drawOrderHandle = doh[0];
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.drawOrderHandle);
			GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Short.BYTES * this.drawOrder.length, this.drawOrderBuffer, this.usage);
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		}
	}
	
	/* The method used to draw the object */
	public void render() {
		//Calculate the matrices for rendering
		Matrix.calculateRenderMatrices();
		//The shader
		Shader shader = null;
		//Check what shader to use
		if (customShader != null)
			shader = customShader;
		else
			shader = defaultAndroidShader;
		//Use the selected shader
		shader.use();
		
		//The attributes within the shader
		int vertexAttribute = shader.getAttributeLocation("andor_vertex");
		int colourAttribute = shader.getAttributeLocation("andor_colour");
		int normalAttribute = shader.getAttributeLocation("andor_normal");
		int textureCoordsAttribute = shader.getAttributeLocation("andor_textureCoord");
		
		//Give the shader the matrices
		shader.setUniformMatrix("andor_modelViewProjectionMatrix", Matrix.modelViewProjectionMatrix);
		shader.setUniformMatrix("andor_normalMatrix", Matrix.normalMatrix);
		//Enable the arrays as needed
		if (this.vertices != null) {
			GLES20.glEnableVertexAttribArray(vertexAttribute);
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.verticesHandle);
			GLES20.glVertexAttribPointer(vertexAttribute, this.vertexValuesCount, GLES20.GL_FLOAT, false, 0, 0);
		}
		if (this.normals != null) {
			GLES20.glEnableVertexAttribArray(normalAttribute);
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.normalsHandle);
			GLES20.glVertexAttribPointer(normalAttribute, this.vertexValuesCount, GLES20.GL_FLOAT, false, 0, 0);
		}
		if (this.colours != null) {
			GLES20.glEnableVertexAttribArray(colourAttribute);
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.coloursHandle);
			GLES20.glVertexAttribPointer(colourAttribute, this.colourValuesCount, GLES20.GL_FLOAT, false, 0, 0);
		}
		if (this.textureCoords != null) {
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.texturesHandle);
			GLES20.glEnableVertexAttribArray(textureCoordsAttribute);
			GLES20.glVertexAttribPointer(textureCoordsAttribute, this.textureCoordValuesCount, GLES20.GL_FLOAT, false, 0, 0);
		}
		
		if (this.texture != null) {
			this.texture.bind();
			shader.setUniformi("andor_texture", 0);
		} else if (globalTexture  != null) {
			globalTexture.bind();
			shader.setUniformi("andor_texture", 0);
		}
		
		if (this.drawOrder != null) {
			GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, this.drawOrderHandle);
			GLES20.glDrawElements(this.renderMode, this.drawOrder.length, GLES20.GL_UNSIGNED_SHORT, 0);
			GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
		} else {
			//Draw the arrays
			GLES20.glDrawArrays(this.renderMode, 0, this.vertices.length / this.vertexValuesCount);
		}
		//Disable the arrays as needed
		if (this.normals != null)
			GLES20.glDisableVertexAttribArray(normalAttribute);
		if (this.textureCoords != null)
			GLES20.glDisableVertexAttribArray(textureCoordsAttribute);
		if (this.colours != null)
			GLES20.glDisableVertexAttribArray(colourAttribute);
		if (this.vertices != null)
			GLES20.glDisableVertexAttribArray(vertexAttribute);
		//Stop using the shader
		shader.stopUsing();
	}
	
	/* The method used to update the vertices */
	public void updateVertices(float[] verticesData) {
		//Set the vertices data
		this.vertices = verticesData;
		//Create the vertices buffer
		this.verticesBuffer = BufferUtils.createFlippedBuffer(this.vertices);
		//Check to see whether the handle has been setup
		if (this.verticesHandle == -2) {
			int[] h = new int[1];
			GLES20.glGenBuffers(1, h, 0);
			//Setup the texture coordinates handle
			this.verticesHandle = h[0];
		}
		//Bind the vertices buffer and give OpenGL the data
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.verticesHandle);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * verticesData.length, this.verticesBuffer, this.usage);
	}
	
	/* The method used to update the normals */
	public void updateNormals(float[] normalsData) {
		//Set the normals data
		this.normals = normalsData;
		//Create the normals buffer
		this.normalsBuffer = BufferUtils.createFlippedBuffer(this.normals);
		//Check to see whether the handle has been setup
		if (this.normalsHandle == -2) {
			int[] h = new int[1];
			GLES20.glGenBuffers(1, h, 0);
			//Setup the texture coordinates handle
			this.normalsHandle = h[0];
		}
		//Bind the normals buffer and give OpenGL the data
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.normalsHandle);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * normalsData.length, this.normalsBuffer, this.usage);
	}
	
	/* The method used to update the colours */
	public void updateColours(float[] colourData) {
		//Set the colour data
		this.colours = colourData;
		//Create the colour buffer
		this.coloursBuffer = BufferUtils.createFlippedBuffer(this.colours);
		//Check to see whether the handle has been setup
		if (this.coloursHandle == -2) {
			int[] h = new int[1];
			GLES20.glGenBuffers(1, h, 0);
			//Setup the texture coordinates handle
			this.coloursHandle = h[0];
		}
		//Bind the colours buffer and give OpenGL the data
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.coloursHandle);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * this.colours.length, this.coloursBuffer, this.usage);
	}
	
	/* The method used to update the texture coordinates */
	public void updateTextures(float[] textureData) {
		//Set the texture data
		this.textureCoords = textureData;
		//Create the colour buffer
		this.textureCoordsBuffer = BufferUtils.createFlippedBuffer(this.textureCoords);
		//Check to see whether the handle has been setup
		if (this.texturesHandle == -2) {
			int[] h = new int[1];
			GLES20.glGenBuffers(1, h, 0);
			//Setup the texture coordinates handle
			this.texturesHandle = h[0];
		}
		//Bind the texture coordinates buffer and give OpenGL the data
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.texturesHandle);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * this.textureCoords.length, this.textureCoordsBuffer, this.usage);
	}
	
	/* The method used to update the draw order */
	public void updateDrawOrder(short[] drawOrder) {
		//Set the draw order
		this.drawOrder = drawOrder;
		//Create the draw order buffer
		this.drawOrderBuffer = BufferUtils.createFlippedBuffer(this.drawOrder);
		//Check to see whether the handle has been setup
		if (this.drawOrderHandle == -2) {
			int[] h = new int[1];
			GLES20.glGenBuffers(1, h, 0);
			//Setup the texture coordinates handle
			this.drawOrderHandle = h[0];
		}
		//Bind the texture coordinates buffer and give OpenGL the data
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.drawOrderHandle);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Short.BYTES * this.drawOrder.length, this.drawOrderBuffer, this.usage);
	}
	
	/* The method used to release this renderer */
	public void release() {
		GLES20.glDeleteBuffers(1, new int[] { this.verticesHandle }, 0);
		if (this.normals != null)
			GLES20.glDeleteBuffers(1, new int[] { this.normalsHandle }, 0);
		if (this.colours != null)
			GLES20.glDeleteBuffers(1, new int[] { this.coloursHandle }, 0);
		if (this.textureCoords != null)
			GLES20.glDeleteBuffers(1, new int[] { this.texturesHandle }, 0);
		if (this.drawOrder != null)
			GLES20.glDeleteBuffers(1, new int[] { this.drawOrderHandle }, 0);
	}
	
}