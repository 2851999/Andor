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

import org.andor.core.Image;
import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.model.Material;
import org.andor.utils.BufferUtils;
import org.andor.utils.GLUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

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
	
	/* The geometry buffer */
	public static GeometryBuffer geometryBuffer;
	
	/* The boolean that turns deferred rendering mode on or off */
	public static boolean deferredRender = false;
	public static boolean deferredNormalRender = false;
	
	/* The current texture (If any) */
	public Image texture;
	
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
		//Unbind any bound buffers
		GLUtils.bindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	/* The method used to render the geometry to the geometry buffer */
	public void render() {
		//Check to see whether deferred rendering is on
		if (deferredRender && ! Settings.AndroidMode && Settings.Video.DeferredRendering)
			//Deferred render
			this.deferredRender();
		else
			//Forward render
			this.forwardRender();
	}
	
	/* The method used to forward render */
	public void forwardRender() {
		if (Settings.Video.DeferredRendering && ! deferredNormalRender)
			//Find the forward pass and use it
			RenderPasses.getPass(GeometryPass.PASS_NAME).render(this);
		else
			RenderPasses.getPass(ForwardPass.PASS_NAME).render(this);
	}
	
	/* The method used to apply deferred rendering */
	public void deferredRender() {
		//Make sure deferred rendering is enabled
		if (! Settings.AndroidMode && Settings.Video.DeferredRendering) {
			if (deferredNormalRender)
				this.deferredNormalRender();
			else
				//Find the geometry pass and use it
				RenderPasses.getPass(FinalPass.PASS_NAME).render(this);
		}
	}
	
	/* The method used to render a normal image while deferred rendering */
	public void deferredNormalRender() {
		this.forwardRender();
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
		this.texture = texture;
	}
	
	/* The method used to assign the material */
	public void setMaterial(Material material) {
		this.material = material;
	}
	
	/* The static method used to convert the render mode */
	private static int convert(int renderMode) {
		//Check the render mode
		if (renderMode == QUADS)
			return GL11.GL_QUADS;
		else if (renderMode == TRIANGLES)
			return GL11.GL_TRIANGLES;
		else if (renderMode == POINTS)
			return GL11.GL_POINTS;
		else
			return renderMode;
	}
	
	/* The static method used to release all of the renderer's that have been created */
	public static void releaseAll() {
		//Go through each renderer
		for (int a = 0; a < allRenderers.size(); a++)
			//Delete the current renderer
			allRenderers.get(a).release();
	}
	
}