/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.android;

import org.andor.core.Renderer;
import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.utils.ArrayUtils;
import org.andor.utils.BufferUtils;
import org.andor.utils.ShaderUtils;
import android.opengl.GLES20;

public class AndroidRenderer extends Renderer {
	
	/* The Android shader code */
	private static final String[] androidVertexShaderCode = new String[] {
		 	"attribute vec4 colour;",
		 	"varying vec4 fcolour;",
		    "attribute vec4 vertexPosition;",
		 	"uniform mat4 matrix;",
		    "void main() {",
		    "  fcolour = colour;",
		    "  gl_Position = matrix * vertexPosition;",
		    "}" };
	
	private static final String[] androidFragmentShaderCode = new String[] {
		    "varying vec4 fcolour;",
		    "void main() {",
		    "  gl_FragColor = fcolour;",
		    "}" };
	
	/* The Android shader */
	public Shader androidShader;
	
	/* The constructor with the render mode and the 
	 * number of vertex values given */
	public AndroidRenderer(int renderMode, int vertexValuesCount) {
		super(renderMode, vertexValuesCount);
		//Set the default usage
		usage = GLES20.GL_STATIC_DRAW;
		//Setup the Android shader
		this.androidShader = new AndroidShader();
		this.androidShader.vertexShader = ShaderUtils.createShader(ArrayUtils.toStringList(androidVertexShaderCode), GLES20.GL_VERTEX_SHADER);
		this.androidShader.fragmentShader = ShaderUtils.createShader(ArrayUtils.toStringList(androidFragmentShaderCode), GLES20.GL_FRAGMENT_SHADER);
		this.androidShader.create();
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
			GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * verticesData.length, this.normalsBuffer, this.usage);
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
		this.androidShader.use();
		//Enable the arrays as needed
		int vertexPositionAttribute = this.androidShader.getAtrrbuteLocation("vertexPosition");
		int normalAttribute = 0;
		int colourAttribute = 0;
		int texturesAttribute = 0;
		int matrixAttribute = this.androidShader.getUniformLocation("matrix");
		GLES20.glUniformMatrix4fv(matrixAttribute, 1, false, AndroidDisplayRenderer.mMVPMatrix, 0);
		GLES20.glEnableVertexAttribArray(vertexPositionAttribute);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.verticesHandle);
		GLES20.glVertexAttribPointer(vertexPositionAttribute, this.vertexValuesCount, GLES20.GL_FLOAT, false, 0, 0);
		if (this.normalsData != null) {
			GLES20.glEnableVertexAttribArray(normalAttribute);
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.normalsHandle);
			GLES20.glVertexAttribPointer(normalAttribute, 2, GLES20.GL_FLOAT, false, 0, 0);
		}
		if (this.colourData != null) {
			colourAttribute = this.androidShader.getAtrrbuteLocation("colour");
			GLES20.glEnableVertexAttribArray(colourAttribute);
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.coloursHandle);
			GLES20.glVertexAttribPointer(colourAttribute, this.colourValuesCount, GLES20.GL_FLOAT, false, 0, 0);
		}
		if (this.textureData != null) {
			GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.texturesHandle);
			GLES20.glEnableVertexAttribArray(texturesAttribute);
			GLES20.glVertexAttribPointer(texturesAttribute, this.textureValuesCount, GLES20.GL_FLOAT, false, 0, 0);
		}
		//Check to see whether the draw order has been set
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
		this.androidShader.stopUsing();
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
			GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, Float.BYTES * verticesData.length, this.normalsBuffer, this.usage);
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