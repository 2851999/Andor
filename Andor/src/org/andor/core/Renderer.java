/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;

import org.andor.core.android.AndroidRenderer;
import org.andor.core.android.AndroidShader;
import org.andor.core.logger.Logger;
import org.andor.utils.ArrayUtils;
import org.andor.utils.BufferUtils;
import org.andor.utils.ShaderUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import android.opengl.GLES20;
import android.util.Log;

public class Renderer {
	
	/* The different shapes that can be rendered */
	public static final int QUADS = 1;
	public static final int TRIANGLES = 2;
	public static final int POINTS = 3;
	
	/* The list of renderer's that have been created */
	protected static List<Renderer> allRenderers = new ArrayList<Renderer>();
	
	/* The numbers of values for the vertices in both 2D and 3D space */
	public static final int VERTEX_VALUES_COUNT_2D = 2;
	public static final int VERTEX_VALUES_COUNT_3D = 3;
	
	/* The default number of values for the colour and texture data */
	public static final int DEFAULT_COLOUR_VALUES_COUNT = 4;
	public static final int DEFAULT_TEXTURE_VALUES_COUNT = 2;
	
	/* The usage type of the buffers */
	public int usage;
	
	/* The number of values for each piece of data */
	public int vertexValuesCount = 0;
	public int colourValuesCount = DEFAULT_COLOUR_VALUES_COUNT;
	public int textureValuesCount = DEFAULT_TEXTURE_VALUES_COUNT;
	
	/* The data used for rendering */
	public float[] verticesData;
	public float[] normalsData;
	public float[] colourData;
	public float[] textureData;
	public short[] drawOrder;
	
	/* The buffers */
	public FloatBuffer verticesBuffer;
	public FloatBuffer normalsBuffer;
	public FloatBuffer coloursBuffer;
	public FloatBuffer texturesBuffer;
	public ShortBuffer drawOrderBuffer;
	
	/* The handles for each buffer */
	public int verticesHandle;
	public int normalsHandle;
	public int coloursHandle;
	public int texturesHandle;
	public int drawOrderHandle;
	
	/* The render mode */
	public int renderMode;
	
	/* The active texture */
	public static Image texture;
	
	/* The default shader */
	public static Shader defaultShader;
	
	/* The current shader */
	public static Shader currentShader;
	
	/* The constructor with the render mode and the 
	 * number of vertex values given */
	public Renderer(int renderMode, int vertexValuesCount) {
		//Assign the variables
		this.renderMode = convert(renderMode);
		this.vertexValuesCount = vertexValuesCount;
		//Add this renderer to the list
		allRenderers.add(this);
		//Make sure Andor isn't currently running on Android
		if (! Settings.AndroidMode) {
			usage = GL15.GL_STATIC_DRAW;
			//Setup the shader
			if (defaultShader == null) {
				defaultShader = new Shader();
				defaultShader.vertexShader = ShaderUtils.createShader(ArrayUtils.toStringList(ShaderUtils.pcVertexAndorMain), Shader.VERTEX_SHADER);
				defaultShader.fragmentShader = ShaderUtils.createShader(ArrayUtils.toStringList(ShaderUtils.pcFragmentAndorMain), Shader.FRAGMENT_SHADER);
				defaultShader.create();
			}
		} else {
			//Setup the shader
			if (defaultShader == null) {
				defaultShader = new AndroidShader();
				defaultShader.vertexShader = ShaderUtils.createShader(ArrayUtils.toStringList(ShaderUtils.androidVertexAndorMain), Shader.VERTEX_SHADER);
				defaultShader.fragmentShader = ShaderUtils.createShader(ArrayUtils.toStringList(ShaderUtils.androidFragmentAndorMain), Shader.FRAGMENT_SHADER);
				defaultShader.create();
			}
		}
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
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.verticesBuffer, this.usage);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		//Check to see whether the normals have been set
		if (this.normalsData != null) {
			//Create the normals buffer
			this.normalsBuffer = BufferUtils.createFlippedBuffer(this.normalsData);
			
			//Setup the normals handle
			this.normalsHandle = GL15.glGenBuffers();
			
			//Bind the normals buffer and give OpenGL the data
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.normalsHandle);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.normalsBuffer, this.usage);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		}
		
		//Check to see whether the colours have been set
		if (this.colourData!= null) {
			//Create the colours buffer
			this.coloursBuffer = BufferUtils.createFlippedBuffer(this.colourData);
			
			//Setup the colours handle
			this.coloursHandle = GL15.glGenBuffers();
			
			//Bind the colours buffer and give OpenGL the data
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.coloursHandle);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.coloursBuffer, this.usage);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		}
		
		//Check to see whether the texture coordinates have been set
		if (this.textureData != null) {
			//Create the texture coordinates buffer
			this.texturesBuffer = BufferUtils.createFlippedBuffer(this.textureData);
			
			//Setup the texture coordinates handle
			this.texturesHandle = GL15.glGenBuffers();
			
			//Bind the texture coordinates buffer and give OpenGL the data
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.texturesHandle);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.texturesBuffer, this.usage);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		}
		
		//Check to see whether the draw order has been set
		if (this.drawOrder != null) {
			this.drawOrderBuffer = BufferUtils.createFlippedBuffer(this.drawOrder);
			this.drawOrderHandle = GL15.glGenBuffers();
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.drawOrderHandle);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.drawOrderBuffer, this.usage);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		}
	}
	
	/* The method used to draw the object */
	public void render() {
		//Multiply the matrices together to get the final model view projection matrix
		Matrix4D projectionViewMatrix = Matrix.multiplyMatrices(Matrix.projectionMatrix, Matrix.viewMatrix);
		Matrix.modelViewProjectionMatrix = Matrix.transpose((Matrix.multiplyMatrices(projectionViewMatrix, Matrix.modelMatrix)));
		//Set the correct shader
		Shader shader = defaultShader;
		if (currentShader != null)
			shader = currentShader;
		//Use the shader program
		GL20.glUseProgram(shader.program);
		int vertexPositionAttribute = shader.getAttributeLocation("andor_vertexPosition");
		int normalAttribute = 0;
		int colourAttribute = 0;
		int texturesAttribute = 0;
		int modelMatrixAttribute = shader.getUniformLocation("andor_modelmatrix");
		int viewMatrixAttribute = shader.getUniformLocation("andor_viewmatrix");
		int projectionMatrixAttribute = shader.getUniformLocation("andor_projectionmatrix");
		int matrixAttribute = shader.getUniformLocation("andor_modelviewprojectionmatrix");
		GL20.glUniformMatrix4(modelMatrixAttribute, false, BufferUtils.createFlippedBuffer(Matrix.modelMatrix.getValues()));
		GL20.glUniformMatrix4(viewMatrixAttribute, false, BufferUtils.createFlippedBuffer(Matrix.viewMatrix.getValues()));
		GL20.glUniformMatrix4(projectionMatrixAttribute, false, BufferUtils.createFlippedBuffer(Matrix.projectionMatrix.getValues()));
		GL20.glUniformMatrix4(matrixAttribute, false, BufferUtils.createFlippedBuffer(Matrix.modelViewProjectionMatrix.getValues()));
		GL20.glEnableVertexAttribArray(vertexPositionAttribute);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.verticesHandle);
		GL20.glVertexAttribPointer(vertexPositionAttribute, this.vertexValuesCount, GL11.GL_FLOAT, false, 0, 0);
		//Enable the arrays as needed
		if (this.normalsData != null) {
			normalAttribute = shader.getAttributeLocation("andor_normal");
			GL20.glEnableVertexAttribArray(normalAttribute);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.normalsHandle);
			GL20.glVertexAttribPointer(normalAttribute, this.vertexValuesCount, GL11.GL_FLOAT, false, 0, 0);
		}
		if (this.colourData != null) {
			colourAttribute = shader.getAttributeLocation("andor_vcolour");
			GL20.glEnableVertexAttribArray(colourAttribute);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.coloursHandle);
			GL20.glVertexAttribPointer(colourAttribute, this.colourValuesCount, GL11.GL_FLOAT, false, 0, 0);
		}
		if (this.textureData != null) {
			texturesAttribute = shader.getAttributeLocation("andor_vtextureCoord");
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.texturesHandle);
			GL20.glEnableVertexAttribArray(texturesAttribute);
			GL20.glVertexAttribPointer(texturesAttribute, this.textureValuesCount, GL11.GL_FLOAT, false, 0, 0);
			GL20.glUniform1i(shader.getUniformLocation("andor_texture"), 0);
			if (texture != null)
				GL20.glUniform1f(shader.getUniformLocation("andor_hasTexture"), 1f);
			else
				GL20.glUniform1f(shader.getUniformLocation("andor_hasTexture"), 0f);
		} else {
			GL20.glUniform1f(shader.getUniformLocation("andor_hasTexture"), 0f);
		}
		if (this.drawOrder != null) {
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.drawOrderHandle);
			GL11.glDrawElements(this.renderMode, this.drawOrder.length, GL11.GL_UNSIGNED_SHORT, 0);
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		} else {
			//Draw the arrays
			GL11.glDrawArrays(this.renderMode, 0, this.verticesData.length / this.vertexValuesCount);
		}
		//Disable the arrays as needed
		if (this.normalsData != null)
			GL20.glDisableVertexAttribArray(normalAttribute);
		if (this.textureData != null)
			GL20.glDisableVertexAttribArray(texturesAttribute);
		if (this.colourData != null)
			GL20.glDisableVertexAttribArray(colourAttribute);
		GL20.glDisableVertexAttribArray(vertexPositionAttribute);
		//Stop using the shader program
		GL20.glUseProgram(0);
	}
	
	/* The method used to update the vertices */
	public void updateVertices(float[] verticesData) {
		//Set the vertices data
		this.verticesData = verticesData;
		//Create the vertices buffer
		this.verticesBuffer = BufferUtils.createFlippedBuffer(this.verticesData);
		//Bind the vertices buffer and give OpenGL the data
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.verticesHandle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.verticesBuffer, this.usage);
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
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.normalsHandle);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.normalsBuffer, this.usage);
		}
	}
	
	/* The method used to update the colours */
	public void updateColours(float[] colourData) {
		//Set the colour data
		this.colourData = colourData;
		//Create the colour buffer
		this.coloursBuffer = BufferUtils.createFlippedBuffer(this.colourData);
		//Bind the colours buffer and give OpenGL the data
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.coloursHandle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.coloursBuffer, this.usage);
	}
	
	/* The method used to update the texture coordinates */
	public void updateTextures(float[] textureData) {
		//Set the texture data
		this.textureData = textureData;
		//Create the textures buffer
		this.texturesBuffer = BufferUtils.createFlippedBuffer(this.textureData);
		//Bind the texture coordinates buffer and give OpenGL the data
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.texturesHandle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.texturesBuffer, this.usage);
	}
	
	/* The method used to update the draw order */
	public void updateDrawOrder(short[] drawOrder) {
		//Set the draw order
		this.drawOrder = drawOrder;
		//Create the draw order buffer
		this.drawOrderBuffer = BufferUtils.createFlippedBuffer(this.drawOrder);
		//Bind the texture coordinates buffer and give OpenGL the data
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.drawOrderHandle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.drawOrderBuffer, this.usage);
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
	
	/* The method used to set the vertices and the draw orders */
	public void setValues(float[] verticesData, short[] drawOrder) {
		this.verticesData = verticesData;
		this.drawOrder = drawOrder;
	}
	
	/* The method used to set the vertices, colours and the draw order */
	public void setValues(float[] verticesData, float[] colourData, short[] drawOrder) {
		this.verticesData = verticesData;
		this.colourData = colourData;
		this.drawOrder = drawOrder;
	}
	
	/* The method used to set the vertices, colours, texture coordinates and the draw order */
	public void setValues(float[] verticesData, float[] colourData, float[] textureData, short[] drawOrder) {
		this.verticesData = verticesData;
		this.colourData = colourData;
		this.textureData = textureData;
		this.drawOrder = drawOrder;
	}
	
	/* The method used to release this renderer */
	public void release() {
		GL15.glDeleteBuffers(this.verticesHandle);
		if (this.normalsData != null)
			GL15.glDeleteBuffers(this.normalsHandle);
		if (this.colourData != null)
			GL15.glDeleteBuffers(this.coloursHandle);
		if (this.textureData != null)
			GL15.glDeleteBuffers(this.texturesHandle);
		if (this.drawOrder != null)
			GL15.glDeleteBuffers(this.drawOrderHandle);
	}
	
	/* The static method used to release all of the renderer's that have been created */
	public static void releaseAll() {
		//Go through each renderer
		for (int a = 0; a < allRenderers.size(); a++)
			//Delete the current renderer
			allRenderers.get(a).release();
	}
	
	/* The static method used to convert the render mode */
	private static int convert(int renderMode) {
		//Check to see whether Andor is currently running on android
		if (! Settings.AndroidMode) {
			//Check the render mode
			if (renderMode == QUADS)
				return GL11.GL_QUADS;
			else if (renderMode == TRIANGLES)
				return GL11.GL_TRIANGLES;
			else if (renderMode == POINTS)
				return GL11.GL_POINTS;
			else {
				//Log an error
				Logger.log("Andor - Renderer", "Invalid render mode: " + renderMode, Log.ERROR);
				//Return -1
				return -1;
			}
		} else {
			//Check the render mode
			if (renderMode == QUADS)
				return GLES20.GL_TRIANGLE_FAN;
			else if (renderMode == TRIANGLES)
				return GLES20.GL_TRIANGLES;
			else if (renderMode == POINTS)
				return GLES20.GL_POINTS;
			else {
				//Log an error
				Logger.log("Andor - Renderer", "Invalid render mode: " + renderMode, Log.ERROR);
				//Return -1
				return -1;
			}
		}
	}
	
	/* The static method used to create a renderer instance appropriate for the current
	 * platform */
	public static Renderer create(int renderMode, int vertexValuesCount) {
		//Check to see whether Andor is currently running on Android
		if (! Settings.AndroidMode)
			//Return the default renderer
			return new Renderer(renderMode, vertexValuesCount);
		else
			//Return the Android renderer
			return new AndroidRenderer(renderMode, vertexValuesCount);
	}
	
}