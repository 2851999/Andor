/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;

import org.andor.core.android.AndroidRenderer;
import org.andor.core.deferredrendering.GeometryBuffer;
import org.andor.core.logger.Log;
import org.andor.core.logger.Logger;
import org.andor.utils.ArrayUtils;
import org.andor.utils.BufferUtils;
import org.andor.utils.ShaderUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import android.opengl.GLES20;

public class Renderer {
	
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
	
	/* The list of renderer's that have been created */
	protected static List<Renderer> allRenderers = new ArrayList<Renderer>();
	
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
	public static Shader forwardRenderShader;
	public static Shader deferredShaderGeometry;
	public static Shader deferredShaderDefault;
	public static Shader deferredShaderLight;
	
	/* The custom shader */
	public static Shader customShader;
	
	/* The geometry buffer */
	public static GeometryBuffer geometryBuffer;
	
	/* The boolean that turns deferred rendering mode on or off */
	public static boolean deferredRender = false;
	public static boolean deferredNormalRender = false;
	
	/* The global image - Used to temporarily allow the image to be found */
	public static Image globalTexture;
	
	/* The current texture (If any) */
	public Image texture;
	
	/* The constructor */
	public Renderer(int renderMode, int vertexValuesCount) {
		//Assign the values
		this.renderMode = convert(renderMode);
		this.vertexValuesCount = vertexValuesCount;
		this.colourValuesCount = DEFAULT_COLOUR_VALUES_COUNT;
		this.textureCoordValuesCount = DEFAULT_TEXTURE_COORD_VALUES_COUNT;
		this.usage = GL15.GL_STATIC_DRAW;
		//Assign the default values
		this.verticesHandle = -2;
		this.normalsHandle = -2;
		this.coloursHandle = -2;
		this.texturesHandle = -2;
		//Check to see whether running using deferred rendering
		if (! Settings.Video.DeferredRendering) {
			//Load and assign the shaders
			if (forwardRenderShader == null) {
				forwardRenderShader = new Shader();
				forwardRenderShader.vertexShader = ShaderUtils.createRenderShader(ArrayUtils.toStringList(ShaderUtils.vertexAndorMain), Shader.VERTEX_SHADER);
				forwardRenderShader.fragmentShader = ShaderUtils.createRenderShader(ArrayUtils.toStringList(ShaderUtils.fragmentAndorMain), Shader.FRAGMENT_SHADER);
				forwardRenderShader.create();
			}
		} else {
			//Load and assign the shaders
			if (deferredShaderGeometry == null) {
				deferredShaderGeometry = new Shader();
				deferredShaderGeometry.vertexShader = ShaderUtils.createRenderShader(ArrayUtils.toStringList(ShaderUtils.vertexAndorMain), Shader.VERTEX_SHADER);
				deferredShaderGeometry.fragmentShader = ShaderUtils.createRenderShader(ArrayUtils.toStringList(ShaderUtils.fragmentAndorMain), Shader.FRAGMENT_SHADER);
				deferredShaderGeometry.create();
			}
			if (deferredShaderDefault == null) {
				deferredShaderDefault = new Shader();
				deferredShaderDefault.vertexShader = ShaderUtils.createDeferredRenderShader(ArrayUtils.toStringList(ShaderUtils.vertexAndorMain), Shader.VERTEX_SHADER, Shader.DEFAULT_SHADER);
				deferredShaderDefault.fragmentShader = ShaderUtils.createDeferredRenderShader(ArrayUtils.toStringList(ShaderUtils.fragmentAndorMain), Shader.FRAGMENT_SHADER, Shader.DEFAULT_SHADER);
				deferredShaderDefault.create();
			}
			if (deferredShaderLight == null) {
				deferredShaderLight = new Shader();
				deferredShaderLight.vertexShader = ShaderUtils.createDeferredRenderShader(ArrayUtils.toStringList(ShaderUtils.vertexAndorMain), Shader.VERTEX_SHADER, Shader.LIGHT_SHADER);
				deferredShaderLight.fragmentShader = ShaderUtils.createDeferredRenderShader(ArrayUtils.toStringList(ShaderUtils.fragmentAndorMain), Shader.FRAGMENT_SHADER, Shader.LIGHT_SHADER);
				deferredShaderLight.create();
			}
			//Setup the geometry buffer if it has not already been setup
			if (geometryBuffer == null) geometryBuffer = new GeometryBuffer();
		}
		//Add this renderer to the list
		allRenderers.add(this);
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
	public void render() {
		//Check to see whether deferred rendering is on
		if (deferredRender && ! Settings.AndroidMode && Settings.Video.DeferredRendering) {
			//Deferred render
			this.deferredRender();
		} else {
			//Forward render
			this.forwardRender();
		}
	}
	
	/* The method used to forward render */
	public void forwardRender() {
		//Calculate the matrices for rendering
		Matrix.calculateRenderMatrices();
		//The shader
		Shader shader = null;
		//Check what shader to use
		if (customShader != null)
			shader = customShader;
		else {
			//Check to see whether deferred rendering is enabled
			if (Settings.Video.DeferredRendering)
				shader = deferredShaderGeometry;
			else
				shader = forwardRenderShader;
		}
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
		shader.setUniformMatrix("andor_modelMatrix", Matrix.modelMatrix);
		
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
			shader.setUniformf("andor_hasTexture", 1.0f);
		} else {
			shader.setUniformf("andor_hasTexture", 0.0f);
		}
		
		if (! deferredRender && this.texture != null) {
			this.texture.bind();
			shader.setUniformi("andor_texture", 0);
		} else if (! deferredRender && globalTexture  != null) {
			globalTexture.bind();
			shader.setUniformi("andor_texture", 0);
		} else if (deferredRender && ! deferredNormalRender) {
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, geometryBuffer.textures[GeometryBuffer.TYPE_POSITION]);
			GL13.glActiveTexture(GL13.GL_TEXTURE1);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, geometryBuffer.textures[GeometryBuffer.TYPE_DIFFUSE]);
			GL13.glActiveTexture(GL13.GL_TEXTURE2);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, geometryBuffer.textures[GeometryBuffer.TYPE_NORMAL]);
			GL13.glActiveTexture(GL13.GL_TEXTURE3);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, geometryBuffer.depthTexture);
			shader.setUniformi("andor_positionTexture", 0);
			shader.setUniformi("andor_diffuseTexture", 1);
			shader.setUniformi("andor_normalTexture", 2);
			shader.setUniformi("andor_depthTexture", 3);
		}
		
		if (this.drawOrder != null) {
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.drawOrderHandle);
			GL11.glDrawElements(this.renderMode, this.drawOrder.length, GL11.GL_UNSIGNED_SHORT, 0);
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		} else {
			GL11.glDrawArrays(this.renderMode, 0, this.vertices.length / this.vertexValuesCount);
		}
		
		if (deferredRender && ! deferredNormalRender) {
			GL13.glActiveTexture(GL13.GL_TEXTURE3);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
			GL13.glActiveTexture(GL13.GL_TEXTURE2);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
			GL13.glActiveTexture(GL13.GL_TEXTURE1);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
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
		shader.stopUsing();
	}
	
	/* The method used to apply deferred rendering */
	public void deferredRender() {
		//Make sure deferred rendering is enabled
		if (! Settings.AndroidMode && Settings.Video.DeferredRendering) {
			if (deferredNormalRender)
				this.deferredNormalRender();
			else {
				//Assign the custom shader
				if (customShader == null) {
					customShader = deferredShaderLight;
					//Render
					this.forwardRender();
					//Reset the custom shader
					customShader = null;
				} else {
					//Custom shader already set, just render
					this.forwardRender();
				}
			}
		}
	}
	
	/* The method used to render a normal image while deferred rendering */
	public void deferredNormalRender() {
		//Save the current deferredRender state
		boolean temp = deferredRender;
		//Disable deferred rendering
		deferredRender = false;
		//Assign the custom shader
		if (customShader == null) {
			customShader = deferredShaderDefault;
			//Render
			this.forwardRender();
			//Reset the custom shader
			customShader = null;
		} else {
			//Custom shader already set, just render
			this.forwardRender();
		}
		//Assign the deferredRender state again
		deferredRender = temp;
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
	
	/* The method used to set the vertices */
	public void setValues(float[] vertices) {
		this.vertices = vertices;
	}
	
	/* The method used to set the vertices and the colours */
	public void setValues(float[] vertices, float[] colours) {
		this.vertices = vertices;
		this.colours = colours;
	}
	
	/* The method used to set the vertices, colours and the texture coordinates */
	public void setValues(float[] vertices, float[] colours, float[] textureCoords) {
		this.vertices = vertices;
		this.colours = colours;
		this.textureCoords = textureCoords;
	}
	
	/* The method used to set the vertices and the draw orders */
	public void setValues(float[] vertices, short[] drawOrder) {
		this.vertices = vertices;
		this.drawOrder = drawOrder;
	}
	
	/* The method used to set the vertices, colours and the draw order */
	public void setValues(float[] vertices, float[] colours, short[] drawOrder) {
		this.vertices = vertices;
		this.colours = colours;
		this.drawOrder = drawOrder;
	}
	
	/* The method used to set the vertices, colours, texture coordinates and the draw order */
	public void setValues(float[] vertices, float[] colours, float[] textureCoords, short[] drawOrder) {
		this.vertices = vertices;
		this.colours = colours;
		this.textureCoords = textureCoords;
		this.drawOrder = drawOrder;
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
	
	/* The static method used to release all of the renderer's that have been created */
	public static void releaseAll() {
		//Go through each renderer
		for (int a = 0; a < allRenderers.size(); a++)
			//Delete the current renderer
			allRenderers.get(a).release();
	}
	
}