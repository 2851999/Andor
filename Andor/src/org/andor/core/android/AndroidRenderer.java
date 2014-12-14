/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.android;

import org.andor.core.Image;
import org.andor.core.Matrix;
import org.andor.core.Matrix4D;
import org.andor.core.Renderer;
import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.utils.ArrayUtils;
import org.andor.utils.BufferUtils;
import org.andor.utils.ShaderUtils;

import android.opengl.GLES20;

public class AndroidRenderer extends Renderer {
	
	/* The active texture */
	public static Image texture;
	
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
		//Create the vertices buffer
		this.verticesBuffer = BufferUtils.createFlippedBuffer(this.verticesData);
		int[] vh = new int[1];
		GLES20.glGenBuffers(1, vh, 0);
		//Setup the vertices handle
		this.verticesHandle = vh[0];
		
		//Bind the vertices buffer and give OpenGL the data
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.verticesHandle);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * verticesData.length, this.verticesBuffer, this.usage);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		
		//Check to see whether the normals have been set
		if (this.normalsData != null) {
			//Create the normals buffer
			this.normalsBuffer = BufferUtils.createFlippedBuffer(this.normalsData);
			int[] nh = new int[1];
			GLES20.glGenBuffers(1, nh, 0);
			//Setup the normals handle
			this.normalsHandle = nh[0];
			
			//Bind the normals buffer and give OpenGL the data
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.normalsHandle);
			GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * normalsData.length, this.normalsBuffer, this.usage);
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		}
		
		//Check to see whether the colours have been set
		if (this.colourData!= null) {
			//Create the colours buffer
			this.coloursBuffer = BufferUtils.createFlippedBuffer(this.colourData);
			int[] ch = new int[1];
			GLES20.glGenBuffers(1, ch, 0);
			//Setup the colours handle
			this.coloursHandle = ch[0];
			
			//Bind the colours buffer and give OpenGL the data
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.coloursHandle);
			GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * colourData.length, this.coloursBuffer, this.usage);
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		}
		
		//Check to see whether the texture coordinates have been set
		if (this.textureData != null) {
			//Create the texture coordinates buffer
			this.texturesBuffer = BufferUtils.createFlippedBuffer(this.textureData);
			int[] th = new int[1];
			GLES20.glGenBuffers(1, th, 0);
			//Setup the texture coordinates handle
			this.texturesHandle = th[0];
			
			//Bind the texture coordinates buffer and give OpenGL the data
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.texturesHandle);
			GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * textureData.length, this.texturesBuffer, this.usage);
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
		//Multiply the matrices together to get the final model view projection matrix
		Matrix4D projectionViewMatrix = Matrix.multiply(Matrix.projectionMatrix, Matrix.viewMatrix);
		Matrix.modelViewProjectionMatrix = Matrix.transpose((Matrix.multiply(projectionViewMatrix, Matrix.modelMatrix)));
		//Calculate the normal matrix
		Matrix.normalMatrix = Matrix.invert(Matrix.transpose(Matrix.modelMatrix));
		//Set the correct android shader
		Shader androidShader = defaultAndroidShader;
		if (currentShader != null)
			androidShader = currentShader;
		//Use the shader program
		GLES20.glUseProgram(androidShader.program);
		int vertexPositionAttribute = androidShader.getAttributeLocation("andor_vertexPosition");
		int normalAttribute = 0;
		int colourAttribute = 0;
		int texturesAttribute = 0;
		int modelMatrixAttribute = androidShader.getUniformLocation("andor_modelmatrix");
		int viewMatrixAttribute = androidShader.getUniformLocation("andor_viewmatrix");
		int projectionMatrixAttribute = androidShader.getUniformLocation("andor_projectionmatrix");
		int matrixAttribute = androidShader.getUniformLocation("andor_modelviewprojectionmatrix");
		int normalMatrixAttribute = androidShader.getUniformLocation("andor_normalmatrix");
		GLES20.glUniformMatrix4fv(modelMatrixAttribute, 1, false, Matrix.modelMatrix.getValues(), 0);
		GLES20.glUniformMatrix4fv(viewMatrixAttribute, 1, false, Matrix.viewMatrix.getValues(), 0);
		GLES20.glUniformMatrix4fv(projectionMatrixAttribute, 1, false, Matrix.projectionMatrix.getValues(), 0);
		GLES20.glUniformMatrix4fv(matrixAttribute, 1, false, Matrix.modelViewProjectionMatrix.getValues(), 0);
		GLES20.glUniformMatrix4fv(normalMatrixAttribute, 1, false, Matrix.normalMatrix.getValues(), 0);
		GLES20.glEnableVertexAttribArray(vertexPositionAttribute);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.verticesHandle);
		GLES20.glVertexAttribPointer(vertexPositionAttribute, this.vertexValuesCount, GLES20.GL_FLOAT, false, 0, 0);
		//Enable the arrays as needed
		if (this.normalsData != null) {
			normalAttribute = androidShader.getAttributeLocation("andor_normal");
			GLES20.glEnableVertexAttribArray(normalAttribute);
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.normalsHandle);
			GLES20.glVertexAttribPointer(normalAttribute, this.vertexValuesCount, GLES20.GL_FLOAT, false, 0, 0);
		}
		if (this.colourData != null) {
			colourAttribute = androidShader.getAttributeLocation("andor_vcolour");
			GLES20.glEnableVertexAttribArray(colourAttribute);
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.coloursHandle);
			GLES20.glVertexAttribPointer(colourAttribute, this.colourValuesCount, GLES20.GL_FLOAT, false, 0, 0);
		}
		if (this.textureData != null) {
			texturesAttribute = androidShader.getAttributeLocation("andor_vtextureCoord");
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.texturesHandle);
			GLES20.glEnableVertexAttribArray(texturesAttribute);
			GLES20.glVertexAttribPointer(texturesAttribute, this.textureValuesCount, GLES20.GL_FLOAT, false, 0, 0);
			GLES20.glUniform1i(androidShader.getUniformLocation("andor_texture"), 0);
			if (texture != null)
				GLES20.glUniform1f(androidShader.getUniformLocation("andor_hasTexture"), 1f);
		}
		if (this.drawOrder != null) {
			GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, this.drawOrderHandle);
			GLES20.glDrawElements(this.renderMode, this.drawOrder.length, GLES20.GL_UNSIGNED_SHORT, 0);
			GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
		} else {
			//Draw the arrays
			GLES20.glDrawArrays(this.renderMode, 0, this.verticesData.length / this.vertexValuesCount);
		}
		//Disable the arrays as needed
		if (this.normalsData != null)
			GLES20.glDisableVertexAttribArray(normalAttribute);
		if (this.textureData != null)
			GLES20.glDisableVertexAttribArray(texturesAttribute);
		if (this.colourData != null)
			GLES20.glDisableVertexAttribArray(colourAttribute);
		GLES20.glDisableVertexAttribArray(vertexPositionAttribute);
		//Stop using the android shader program
		GLES20.glUseProgram(0);
	}
	
	/* The method used to update the vertices */
	public void updateVertices(float[] verticesData) {
		//Set the vertices data
		this.verticesData = verticesData;
		//Create the vertices buffer
		this.verticesBuffer = BufferUtils.createFlippedBuffer(this.verticesData);
		//Bind the vertices buffer and give OpenGL the data
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.verticesHandle);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * verticesData.length, this.verticesBuffer, this.usage);
	}
	
	/* The method used to update the normals */
	public void updateNormals(float[] normalsData) {
		//Set the normals data
		this.normalsData = normalsData;
		//Create the normals buffer
		this.normalsBuffer = BufferUtils.createFlippedBuffer(this.normalsData);
		//Make sure Andor isn't currently running on Android
		if (! Settings.AndroidMode) {
			//Bind the normals buffer and give OpenGL the data
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.normalsHandle);
			GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * normalsData.length, this.normalsBuffer, this.usage);
		}
	}
	
	/* The method used to update the colours */
	public void updateColours(float[] colourData) {
		//Set the colour data
		this.colourData = colourData;
		//Create the colour buffer
		this.coloursBuffer = BufferUtils.createFlippedBuffer(this.colourData);
		//Bind the colours buffer and give OpenGL the data
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.coloursHandle);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * this.colourData.length, this.coloursBuffer, this.usage);
	}
	
	/* The method used to update the texture coordinates */
	public void updateTextures(float[] textureData) {
		//Set the texture data
		this.textureData = textureData;
		//Create the colour buffer
		this.texturesBuffer = BufferUtils.createFlippedBuffer(this.textureData);
		//Bind the texture coordinates buffer and give OpenGL the data
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.texturesHandle);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * this.textureData.length, this.texturesBuffer, this.usage);
	}
	
	/* The method used to update the draw order */
	public void updateDrawOrder(short[] drawOrder) {
		//Set the draw order
		this.drawOrder = drawOrder;
		//Create the draw order buffer
		this.drawOrderBuffer = BufferUtils.createFlippedBuffer(this.drawOrder);
		//Bind the texture coordinates buffer and give OpenGL the data
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.drawOrderHandle);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Short.BYTES * this.drawOrder.length, this.drawOrderBuffer, this.usage);
	}
	
	/* The method used to release this renderer */
	public void release() {
		GLES20.glDeleteBuffers(1, new int[] { this.verticesHandle }, 0);
		if (this.normalsData != null)
			GLES20.glDeleteBuffers(1, new int[] { this.normalsHandle }, 0);
		if (this.colourData != null)
			GLES20.glDeleteBuffers(1, new int[] { this.coloursHandle }, 0);
		if (this.textureData != null)
			GLES20.glDeleteBuffers(1, new int[] { this.texturesHandle }, 0);
		if (this.drawOrder != null)
			GLES20.glDeleteBuffers(1, new int[] { this.drawOrderHandle }, 0);
	}
	
}