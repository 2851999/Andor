/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.render;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.List;

import org.andor.core.Camera3D;
import org.andor.core.Colour;
import org.andor.core.Image;
import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.lighting.BaseLight;
import org.andor.core.model.Material;
import org.andor.utils.BufferUtils;
import org.andor.utils.GLUtils;
import org.andor.utils.ObjectBuilderUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class Renderer {
	
	/* The different shapes that can be rendered */
	public static final int QUADS = 1;
	public static final int TRIANGLES = 2;
	public static final int POINTS = 3;
	public static final int TRIANGLE_FAN = 4;
	
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
	
	public float[] tangents;
	
	/* The data handles */
	public int verticesHandle;
	public int coloursHandle;
	public int normalsHandle;
	public int texturesHandle;
	public int drawOrderHandle;
	public int tangentsHandle;
	
	/* The data buffers */
	public FloatBuffer verticesBuffer;
	public FloatBuffer coloursBuffer;
	public FloatBuffer normalsBuffer;
	public FloatBuffer textureCoordsBuffer;
	public ShortBuffer drawOrderBuffer;
	public FloatBuffer tangentsBuffer;
	
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
	
	/* The geometry buffer */
	public static GeometryBuffer geometryBuffer;
	
	/* The boolean that turns deferred rendering mode on or off */
	public static boolean deferredRender = false;
	public static boolean deferredNormalRender = false;
	
	/* The lighting information */
	public static Camera3D camera;
	public static BaseLight light;
	public static boolean useAmbient = false;
	public static Colour ambientLight = new Colour(0.1f, 0.1f, 0.1f);
	public static float specularIntensity = 2;
	public static float specularExponent = 32;
	
	/* The current material (If any) */
	public Material material;
	
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
		this.tangentsHandle = -2;
		//Check to see whether running using deferred rendering
		if (! Settings.AndroidMode && Settings.Video.DeferredRendering) {
			//Setup the geometry buffer if it has not already been setup
			if (geometryBuffer == null)
				geometryBuffer = new GeometryBuffer();
		}
		//Assign the default material
		this.material = new Material("Default");
		//Add this renderer to the list
		allRenderers.add(this);
	}
	
	/* The method used to setup the buffers */
	public void setupBuffers() {
		//Check to see whether each respective element has been set and set it up if necessary
		if (vertices != null) {
			this.verticesBuffer = BufferUtils.createFlippedBuffer(this.vertices);
			this.verticesHandle = GLUtils.genBuffers();
			
			GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, this.verticesHandle);
			GLUtils.bufferData(GL15.GL_ARRAY_BUFFER, this.verticesBuffer, this.usage);
		}
		if (colours != null) {
			this.coloursBuffer = BufferUtils.createFlippedBuffer(this.colours);
			this.coloursHandle = GLUtils.genBuffers();
			
			GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, this.coloursHandle);
			GLUtils.bufferData(GL15.GL_ARRAY_BUFFER, this.coloursBuffer, this.usage);
		}
		if (normals != null) {
			this.normalsBuffer = BufferUtils.createFlippedBuffer(this.normals);
			this.normalsHandle = GLUtils.genBuffers();
			
			GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, this.normalsHandle);
			GLUtils.bufferData(GL15.GL_ARRAY_BUFFER, this.normalsBuffer, this.usage);
		}
		if (textureCoords != null) {
			this.textureCoordsBuffer = BufferUtils.createFlippedBuffer(this.textureCoords);
			this.texturesHandle = GLUtils.genBuffers();
			
			GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, this.texturesHandle);
			GLUtils.bufferData(GL15.GL_ARRAY_BUFFER, this.textureCoordsBuffer, this.usage);
		}
		if (drawOrder != null) {
			this.drawOrderBuffer = BufferUtils.createFlippedBuffer(this.drawOrder);
			this.drawOrderHandle = GLUtils.genBuffers();
			
			GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, this.drawOrderHandle);
			GLUtils.bufferData(GL15.GL_ARRAY_BUFFER, this.drawOrderBuffer, this.usage);
		}
		if (tangents != null) {
			this.tangentsBuffer = BufferUtils.createFlippedBuffer(this.tangents);
			this.tangentsHandle = GLUtils.genBuffers();
			
			GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, this.tangentsHandle);
			GLUtils.bufferData(GL15.GL_ARRAY_BUFFER, this.tangentsBuffer, this.usage);
		}
		//Unbind any bound buffers
		GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/* The method used to render the geometry to the geometry buffer */
	public void render() {
		getPass().render(this);
	}
	
	/* The static method used to determine what pass to use and return it */
	public static RenderPass getPass() {
		//Check to see whether deferred rendering is on
		if (deferredRender && ! Settings.AndroidMode && Settings.Video.DeferredRendering) {
			//Make sure deferred rendering is enabled
			if (! Settings.AndroidMode && Settings.Video.DeferredRendering) {
				if (deferredNormalRender)
					return RenderPasses.getPass(ForwardPass.PASS_NAME);
				else
					return RenderPasses.getPass(FinalPass.PASS_NAME);
			} else
				return null;
		} else {
			if (Settings.Video.DeferredRendering && ! deferredNormalRender)
				return RenderPasses.getPass(GeometryPass.PASS_NAME);
			else
				return RenderPasses.getPass(ForwardPass.PASS_NAME);
		}
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
			this.verticesHandle = GLUtils.genBuffers();
		//Bind the vertices buffer and give OpenGL the data
		GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, this.verticesHandle);
		GLUtils.bufferData(GL15.GL_ARRAY_BUFFER, this.verticesBuffer, this.usage);
		GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, 0);
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
			this.normalsHandle = GLUtils.genBuffers();
		//Bind the normals buffer and give OpenGL the data
		GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, this.normalsHandle);
		GLUtils.bufferData(GL15.GL_ARRAY_BUFFER, this.normalsBuffer, this.usage);
		GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, 0);
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
			this.coloursHandle = GLUtils.genBuffers();
		//Bind the colours buffer and give OpenGL the data
		GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, this.coloursHandle);
		GLUtils.bufferData(GL15.GL_ARRAY_BUFFER, this.coloursBuffer, this.usage);
		GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, 0);
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
			this.texturesHandle = GLUtils.genBuffers();
		//Bind the texture coordinates buffer and give OpenGL the data
		GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, this.texturesHandle);
		GLUtils.bufferData(GL15.GL_ARRAY_BUFFER, this.textureCoordsBuffer, this.usage);
		GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, 0);
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
			this.drawOrderHandle = GLUtils.genBuffers();
		//Bind the texture coordinates buffer and give OpenGL the data
		GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, this.drawOrderHandle);
		GLUtils.bufferData(GL15.GL_ARRAY_BUFFER, this.drawOrderBuffer, this.usage);
		GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/* The method used to update the tangents */
	public void updateTangents(float[] tangents) {
		//Set the vertices data
		this.tangents = tangents;
		//Create the vertices buffer
		this.tangentsBuffer = BufferUtils.createFlippedBuffer(this.tangents);
		//Check to see whether the handle has been setup
		if (this.tangentsHandle == -2)
			//Setup the handle
			this.tangentsHandle = GLUtils.genBuffers();
		//Bind the vertices buffer and give OpenGL the data
		GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, this.tangentsHandle);
		GLUtils.bufferData(GL15.GL_ARRAY_BUFFER, this.tangentsBuffer, this.usage);
		GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/* The method used to release this renderer */
	public void release() {
		if (this.vertices != null)
			GLUtils.deleteBuffers(this.verticesHandle);
		if (this.normals != null)
			GLUtils.deleteBuffers(this.normalsHandle);
		if (this.colours != null)
			GLUtils.deleteBuffers(this.coloursHandle);
		if (this.textureCoords != null)
			GLUtils.deleteBuffers(this.texturesHandle);
		if (this.drawOrder != null)
			GLUtils.deleteBuffers(this.drawOrderHandle);
		if (this.tangents != null)
			GLUtils.deleteBuffers(this.tangentsHandle);
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
	
	/* The method used to assign the texture */
	public void setTexture(Image texture) {
		this.material.diffuseTextureMap = texture;
	}
	
	/* The method used to assign the material */
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	/* The method used to update the colour */
	public void updateColour(Colour colour) {
		this.updateColours(ObjectBuilderUtils.createColourArray(this.vertices.length, colour));
	}
	
	/* The method used to update the colour */
	public void updateColour(Colour[] colours) {
		this.updateColours(ObjectBuilderUtils.createColourArray(this.vertices.length, colours));
	}
	
	/* The static method used to convert the render mode */
	private static int convert(int renderMode) {
		//The mode
		int mode = renderMode;
		//Check the mode
		if (renderMode == QUADS) mode = GL11.GL_QUADS;
		else if (renderMode == TRIANGLES) mode = GL11.GL_TRIANGLES;
		else if (renderMode == POINTS) mode = GL11.GL_POINTS;
		else if (renderMode == TRIANGLE_FAN) mode = GL11.GL_TRIANGLE_FAN;
		//Special case - On Android use triangle fan instead of quads
		if (Settings.AndroidMode && renderMode == QUADS)
			mode = GL11.GL_TRIANGLE_FAN;
		//Return the mode
		return mode;
	}
	
	/* The static method used to release all of the renderer's that have been created */
	public static void releaseAll() {
		//Go through each renderer
		for (int a = 0; a < allRenderers.size(); a++)
			//Delete the current renderer
			allRenderers.get(a).release();
	}
	
}