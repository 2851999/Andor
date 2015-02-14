/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.experimental.deferredrendering2;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import org.andor.core.Image;
import org.andor.core.Matrix;
import org.andor.core.Shader;
import org.andor.utils.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

public class DeferredRenderer {
	
	public float[] vertices;
	public float[] colours;
	public float[] normals;
	public float[] textureCoords;
	public short[] drawOrder;
	
	public int verticesHandle;
	public int coloursHandle;
	public int normalsHandle;
	public int texturesHandle;
	public int drawOrderHandle;
	
	public FloatBuffer verticesBuffer;
	public FloatBuffer coloursBuffer;
	public FloatBuffer normalsBuffer;
	public FloatBuffer textureCoordsBuffer;
	public ShortBuffer drawOrderBuffer;
	
	public int usage;
	public int renderMode;
	
	public Shader shader;
	
	public static Image texture;
	
	public void setupBuffers() {
		usage = GL15.GL_STATIC_DRAW;
		renderMode = GL11.GL_TRIANGLES;
		shader = ShaderUtils.createShader("/resources/andor/experimental/DefaultPass", false);
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
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	public void render() {
		Matrix.calculateRenderMatrices();
		
		shader.use();
		
		int vertexAttribute = shader.getAttributeLocation("vertex");
		int colourAttribute = shader.getAttributeLocation("colour");
		int normalAttribute = shader.getAttributeLocation("normal");
		int textureCoordsAttribute = shader.getAttributeLocation("textureCoord");
		
		this.shader.setUniformMatrix("modelViewProjectionMatrix", Matrix.modelViewProjectionMatrix);
		this.shader.setUniformMatrix("normalMatrix", Matrix.normalMatrix);
		
		if (this.vertices != null) {
			GL20.glEnableVertexAttribArray(vertexAttribute);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.verticesHandle);
			GL20.glVertexAttribPointer(vertexAttribute, 2, GL11.GL_FLOAT, false, 0, 0);
		}
		
		if (this.colours != null) {
			GL20.glEnableVertexAttribArray(colourAttribute);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.coloursHandle);
			GL20.glVertexAttribPointer(colourAttribute, 4, GL11.GL_FLOAT, false, 0, 0);
		}
		
		if (this.normals != null) {
			GL20.glEnableVertexAttribArray(normalAttribute);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.normalsHandle);
			GL20.glVertexAttribPointer(normalAttribute, 2, GL11.GL_FLOAT, false, 0, 0);
		}
		
		if (this.textureCoords != null) {
			GL20.glEnableVertexAttribArray(textureCoordsAttribute);
			GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, this.texturesHandle);
			GL20.glVertexAttribPointer(textureCoordsAttribute, 2, GL11.GL_FLOAT, false, 0, 0);
		}
		
		if (texture != null) {
			texture.bind();
			this.shader.setUniformi("texture", 0);
		}
		
		if (this.drawOrder != null) {
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, this.drawOrderHandle);
			GL11.glDrawElements(this.renderMode, this.drawOrder.length, GL11.GL_UNSIGNED_SHORT, 0);
			GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		} else {
			GL11.glDrawArrays(this.renderMode, 0, this.vertices.length / 2);
		}
		
		if (this.textureCoords != null)
			GL20.glDisableVertexAttribArray(textureCoordsAttribute);
		if (this.normals != null)
			GL20.glDisableVertexAttribArray(normalAttribute);
		if (this.colours != null)
			GL20.glDisableVertexAttribArray(colourAttribute);
		if (this.vertices != null)
			GL20.glDisableVertexAttribArray(vertexAttribute);
		
		shader.stopUsing();
	}
	
}