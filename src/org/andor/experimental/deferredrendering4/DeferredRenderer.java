/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.experimental.deferredrendering4;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import org.andor.core.Image;
import org.andor.core.Matrix;
import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.andor.utils.BufferUtils;
import org.andor.utils.ShaderUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class DeferredRenderer {
	
	/* The different shapes that can be rendered */
	public static final int QUADS = 1;
	public static final int TRIANGLES = 2;
	public static final int POINTS = 3;
	
	/* The number of vertex values for both 2D and 3D */
	public static final int VERTEX_VALUES_COUNT_2D = 2;
	public static final int VERTEX_VALUES_COUNT_3D = 3;
	
	/* The default number of colour, or texture coordinate values */
	public static final int DEFAULT_COLOUR_VALUES_COUNT = 4;
	public static final int DEFAULT_TEXTURE_COORD_VALUES_COUNT = 2;
	
	/* The data arrays */
	public float[] vertices;
	public float[] colours;
	public float[] normals;
	public float[] textureCoords;
	public short[] drawOrder;
	
	/* The data handles */
	public int verticesHandle;
	public int coloursHandle;
	public int normalsHandle;
	public int texturesHandle;
	public int drawOrderHandle;
	
	/* The data buffers */
	public FloatBuffer verticesBuffer;
	public FloatBuffer coloursBuffer;
	public FloatBuffer normalsBuffer;
	public FloatBuffer textureCoordsBuffer;
	public ShortBuffer drawOrderBuffer;
	
	/* The usage of the VBO */
	public int usage;
	
	/* The render mode this renderer should be using */
	public int renderMode;
	
	/* The number of vertices, colours, and texture coordinate values */
	public int vertexValuesCount;
	public int colourValuesCount;
	public int textureCoordValuesCount;
	
	/* The shaders */
	public Shader geometryShader;
	public Shader finalShader;
	
	/* The geometry buffer */
	public GeometryBuffer geometryBuffer;
	
	public Image texture;
	
	/* The constructor */
	public DeferredRenderer(int renderMode, int vertexValuesCount) {
		//Assign the values
		this.renderMode = convert(renderMode);
		this.vertexValuesCount = vertexValuesCount;
		this.colourValuesCount = DEFAULT_COLOUR_VALUES_COUNT;
		this.textureCoordValuesCount = DEFAULT_TEXTURE_COORD_VALUES_COUNT;
		this.usage = GL15.GL_STATIC_DRAW;
		this.renderMode = GL11.GL_TRIANGLES;
		//Load and assign the shaders
		this.geometryShader = ShaderUtils.createShader(Settings.Resources.Shaders.DEFERRED_GEOMETRY_PASS, false);
		this.finalShader = ShaderUtils.createShader(Settings.Resources.Shaders.DEFERRED_FINAL_PASS, false);
		//Setup the geometry buffer
		this.geometryBuffer = new GeometryBuffer();
	}
	
	/* The method used to setup the buffers */
	public void setupBuffers() {
		//Check to see whether each respective element has been set and set it up if necissary
		if (vertices != null) {
			this.verticesBuffer = BufferUtils.createFlippedBuffer(this.vertices);
			this.verticesHandle = GL15.glGenBuffers();
			
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.verticesHandle);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.verticesBuffer, this.usage);
		}
		if (colours != null) {
			this.coloursBuffer = BufferUtils.createFlippedBuffer(this.colours);
			this.coloursHandle = GL15.glGenBuffers();
			
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.coloursHandle);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.coloursBuffer, this.usage);
		}
		if (normals != null) {
			this.normalsBuffer = BufferUtils.createFlippedBuffer(this.normals);
			this.normalsHandle = GL15.glGenBuffers();
			
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.normalsHandle);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.normalsBuffer, this.usage);
		}
		if (textureCoords != null) {
			this.textureCoordsBuffer = BufferUtils.createFlippedBuffer(this.textureCoords);
			this.texturesHandle = GL15.glGenBuffers();
			
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.texturesHandle);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.textureCoordsBuffer, this.usage);
		}
		if (drawOrder != null) {
			this.drawOrderBuffer = BufferUtils.createFlippedBuffer(this.drawOrder);
			this.drawOrderHandle = GL15.glGenBuffers();
			
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.drawOrderHandle);
			GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.drawOrderBuffer, this.usage);
		}
		//Unbind any bound buffers
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/* The method used to render the geometry to the geometry buffer */
	public void renderGeometry() {
		//Calculate the matrices for rendering
		Matrix.calculateRenderMatrices();
		//Bind the geometry buffer
		geometryBuffer.bindWriting();
		//Use the geometry shader
		geometryShader.use();
		
		//The attributes within the shader
		int vertexAttribute = geometryShader.getAttributeLocation("vertex");
		int colourAttribute = geometryShader.getAttributeLocation("colour");
		int normalAttribute = geometryShader.getAttributeLocation("normal");
		int textureCoordsAttribute = geometryShader.getAttributeLocation("textureCoord");
		
		//Give the shader the matrices
		this.geometryShader.setUniformMatrix("modelViewProjectionMatrix", Matrix.modelViewProjectionMatrix);
		this.geometryShader.setUniformMatrix("normalMatrix", Matrix.normalMatrix);
		
		if (this.vertices != null) {
			GL20.glEnableVertexAttribArray(vertexAttribute);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.verticesHandle);
			GL20.glVertexAttribPointer(vertexAttribute, this.vertexValuesCount, GL11.GL_FLOAT, false, 0, 0);
		}
		
		if (this.colours != null) {
			GL20.glEnableVertexAttribArray(colourAttribute);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.coloursHandle);
			GL20.glVertexAttribPointer(colourAttribute, this.colourValuesCount, GL11.GL_FLOAT, false, 0, 0);
		}
		
		if (this.normals != null) {
			GL20.glEnableVertexAttribArray(normalAttribute);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.normalsHandle);
			GL20.glVertexAttribPointer(normalAttribute, this.vertexValuesCount, GL11.GL_FLOAT, false, 0, 0);
		}
		
		if (this.textureCoords != null) {
			GL20.glEnableVertexAttribArray(textureCoordsAttribute);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.texturesHandle);
			GL20.glVertexAttribPointer(textureCoordsAttribute, this.textureCoordValuesCount, GL11.GL_FLOAT, false, 0, 0);
		}
		
		if (texture != null) {
			texture.bind();
			this.geometryShader.setUniformi("texture", 0);
		}
		
		if (this.drawOrder != null) {
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.drawOrderHandle);
			GL11.glDrawElements(this.renderMode, this.drawOrder.length, GL11.GL_UNSIGNED_SHORT, 0);
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		} else {
			GL11.glDrawArrays(this.renderMode, 0, this.vertices.length / this.vertexValuesCount);
		}
		
		if (this.textureCoords != null)
			GL20.glDisableVertexAttribArray(textureCoordsAttribute);
		if (this.normals != null)
			GL20.glDisableVertexAttribArray(normalAttribute);
		if (this.colours != null)
			GL20.glDisableVertexAttribArray(colourAttribute);
		if (this.vertices != null)
			GL20.glDisableVertexAttribArray(vertexAttribute);
		
		//Stop using the shader
		geometryShader.stopUsing();
		//Unbind the geometry buffer
		this.geometryBuffer.unbind();
	}
	
	/* The method used to render normally without the geometry buffer */
	public void render() {
		Matrix.calculateRenderMatrices();
		finalShader.use();
		
		int vertexAttribute = finalShader.getAttributeLocation("vertex");
		int colourAttribute = finalShader.getAttributeLocation("colour");
		int normalAttribute = finalShader.getAttributeLocation("normal");
		int textureCoordsAttribute = finalShader.getAttributeLocation("textureCoord");
		
		this.finalShader.setUniformMatrix("modelViewProjectionMatrix", Matrix.modelViewProjectionMatrix);
		this.finalShader.setUniformMatrix("normalMatrix", Matrix.normalMatrix);
		
		if (this.vertices != null) {
			GL20.glEnableVertexAttribArray(vertexAttribute);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.verticesHandle);
			GL20.glVertexAttribPointer(vertexAttribute, this.vertexValuesCount, GL11.GL_FLOAT, false, 0, 0);
		}
		
		if (this.colours != null) {
			GL20.glEnableVertexAttribArray(colourAttribute);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.coloursHandle);
			GL20.glVertexAttribPointer(colourAttribute, this.colourValuesCount, GL11.GL_FLOAT, false, 0, 0);
		}
		
		if (this.normals != null) {
			GL20.glEnableVertexAttribArray(normalAttribute);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.normalsHandle);
			GL20.glVertexAttribPointer(normalAttribute, this.vertexValuesCount, GL11.GL_FLOAT, false, 0, 0);
		}
		
		if (this.textureCoords != null) {
			GL20.glEnableVertexAttribArray(textureCoordsAttribute);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.texturesHandle);
			GL20.glVertexAttribPointer(textureCoordsAttribute, this.textureCoordValuesCount, GL11.GL_FLOAT, false, 0, 0);
		}
		
		if (texture != null) {
			texture.bind();
			this.finalShader.setUniformi("texture", 0);
		}
		
		if (this.drawOrder != null) {
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.drawOrderHandle);
			GL11.glDrawElements(this.renderMode, this.drawOrder.length, GL11.GL_UNSIGNED_SHORT, 0);
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		} else {
			GL11.glDrawArrays(this.renderMode, 0, this.vertices.length / this.vertexValuesCount);
		}
		
		if (this.textureCoords != null)
			GL20.glDisableVertexAttribArray(textureCoordsAttribute);
		if (this.normals != null)
			GL20.glDisableVertexAttribArray(normalAttribute);
		if (this.colours != null)
			GL20.glDisableVertexAttribArray(colourAttribute);
		if (this.vertices != null)
			GL20.glDisableVertexAttribArray(vertexAttribute);
		
		finalShader.stopUsing();
	}
	
	/* The method used to update the vertices */
	public void updateVertices(float[] vertices) {
		//Set the vertices data
		this.vertices = vertices;
		//Create the vertices buffer
		this.verticesBuffer = BufferUtils.createFlippedBuffer(this.vertices);
		//Check to see whether the handle has been setup
		if (this.verticesHandle == -2)
			//Setup the handle
			this.verticesHandle = GL15.glGenBuffers();
		//Bind the vertices buffer and give OpenGL the data
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.verticesHandle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.verticesBuffer, this.usage);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/* The method used to update the normals */
	public void updateNormals(float[] normals) {
		//Set the normals data
		this.normals = normals;
		//Create the normals buffer
		this.normalsBuffer = BufferUtils.createFlippedBuffer(this.normals);
		//Check to see whether the handle has been setup
		if (this.normalsHandle == -2)
			//Setup the handle
			this.normalsHandle = GL15.glGenBuffers();
		//Bind the normals buffer and give OpenGL the data
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.normalsHandle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.normalsBuffer, this.usage);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/* The method used to update the colours */
	public void updateColours(float[] colours) {
		//Set the colour data
		this.colours = colours;
		//Create the colour buffer
		this.coloursBuffer = BufferUtils.createFlippedBuffer(this.colours);
		//Check to see whether the handle has been setup
		if (this.coloursHandle == -2)
			//Setup the handle
			this.coloursHandle = GL15.glGenBuffers();
		//Bind the colours buffer and give OpenGL the data
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.coloursHandle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.coloursBuffer, this.usage);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/* The method used to update the texture coordinates */
	public void updateTextures(float[] textureCoords) {
		//Set the texture data
		this.textureCoords = textureCoords;
		//Create the textures buffer
		this.textureCoordsBuffer = BufferUtils.createFlippedBuffer(this.textureCoords);
		//Check to see whether the handle has been setup
		if (this.texturesHandle == -2)
			//Setup the handle
			this.texturesHandle = GL15.glGenBuffers();
		//Bind the texture coordinates buffer and give OpenGL the data
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.texturesHandle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.textureCoordsBuffer, this.usage);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/* The method used to update the draw order */
	public void updateDrawOrder(short[] drawOrder) {
		//Set the draw order
		this.drawOrder = drawOrder;
		//Create the draw order buffer
		this.drawOrderBuffer = BufferUtils.createFlippedBuffer(this.drawOrder);
		//Check to see whether the handle has been setup
		if (this.drawOrderHandle == -2)
			//Setup the handle
			this.drawOrderHandle = GL15.glGenBuffers();
		//Bind the texture coordinates buffer and give OpenGL the data
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.drawOrderHandle);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, this.drawOrderBuffer, this.usage);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/* The method used to release this renderer */
	public void release() {
		if (this.vertices != null)
			GL15.glDeleteBuffers(this.verticesHandle);
		if (this.normals != null)
			GL15.glDeleteBuffers(this.normalsHandle);
		if (this.colours != null)
			GL15.glDeleteBuffers(this.coloursHandle);
		if (this.textureCoords != null)
			GL15.glDeleteBuffers(this.texturesHandle);
		if (this.drawOrder != null)
			GL15.glDeleteBuffers(this.drawOrderHandle);
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
				Logger.log("Andor - DeferredRenderer", "Invalid render mode: " + renderMode, Log.ERROR);
				//Return -1
				return -1;
			}
		}
		//Return -1
		return -1;
	}
	
}