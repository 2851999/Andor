/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.render;

import org.andor.core.Matrix;
import org.andor.core.Settings;
import org.andor.utils.GLUtils;
import org.andor.utils.RenderUtils;
import org.andor.utils.shader.ShaderCode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class FinalPass extends RenderPass {
	
	/* The name of this pass */
	public static final String PASS_NAME = "Final";
	
	/* The constructor */
	public FinalPass() {
		//Call the super constructor
		super(PASS_NAME,  new ShaderCode(Settings.Resources.Shaders.DEFERRED_FINAL_PASS));
	}
	
	/* The method called to do this render pass */
	public void renderPass(Renderer renderer) {
		//Calculate the matrices for rendering
		Matrix.calculateRenderMatrices();
		//Use the shader
		this.useShader();
		
		//The attributes within the shader
		int vertexAttribute = currentShader.getAttributeLocation(RenderUtils.ATTRIBUTE_VERTEX);
		int colourAttribute = currentShader.getAttributeLocation(RenderUtils.ATTRIBUTE_COLOUR);
		int normalAttribute = currentShader.getAttributeLocation(RenderUtils.ATTRIBUTE_NORMAL);
		int textureCoordsAttribute = currentShader.getAttributeLocation(RenderUtils.ATTRIBUTE_TEXTURE_COORD);
		
		//Give the shader the matrices
		currentShader.setUniformMatrix(RenderUtils.UNIFORM_MODEL_VIEW_PROJECTION_MATRIX, Matrix.modelViewProjectionMatrix);
		currentShader.setUniformMatrix(RenderUtils.UNIFORM_MODEL_MATRIX, Matrix.modelMatrix);
		currentShader.setUniformMatrix(RenderUtils.UNIFORM_NORMAL_MATRIX, Matrix.normalMatrix);
		
		if (renderer.vertices != null)
			prepareVertexArray(vertexAttribute, renderer.verticesHandle, renderer.vertexValuesCount);
		if (renderer.colours != null)
			prepareVertexArray(colourAttribute, renderer.coloursHandle, renderer.colourValuesCount);
		if (renderer.normals != null)
			prepareVertexArray(normalAttribute, renderer.normalsHandle, renderer.vertexValuesCount);
		if (renderer.textureCoords != null) {
			prepareVertexArray(textureCoordsAttribute, renderer.texturesHandle, renderer.textureCoordValuesCount);
			currentShader.setUniformf(RenderUtils.UNIFORM_HAS_TEXTURE, 1.0f);
		} else
			currentShader.setUniformf(RenderUtils.UNIFORM_HAS_TEXTURE, 0.0f);
		
		//Check whether the material exists
		if (renderer.material != null)
			//Assign the material values
			RenderUtils.assignMaterial(this, renderer.material);
		
		currentShader.setUniformf("andor_positionTexture", this.bindTexture(Renderer.geometryBuffer.textures[GeometryBuffer.TYPE_POSITION]));
		currentShader.setUniformf("andor_diffuseTexture", this.bindTexture(Renderer.geometryBuffer.textures[GeometryBuffer.TYPE_DIFFUSE]));
		currentShader.setUniformf("andor_normalTexture", this.bindTexture(Renderer.geometryBuffer.textures[GeometryBuffer.TYPE_NORMAL]));
		currentShader.setUniformf("andor_depthTexture", this.bindTexture(Renderer.geometryBuffer.depthTexture));
		
		//Check to see whether the draw order has been assigned
		if (renderer.drawOrder != null) {
			GLUtils.bindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, renderer.drawOrderHandle);
			GLUtils.drawElements(renderer.renderMode, renderer.drawOrder.length, GL11.GL_UNSIGNED_SHORT, 0);
			GLUtils.bindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		} else
			GLUtils.drawArrays(renderer.renderMode, 0, renderer.vertices.length / renderer.vertexValuesCount);
		
		//Unbind any textures
		this.unbindTextures();
		
		//Disable any arrays
		this.disableArrays();
		
		//Stop using the shader
		this.stopUsingShader();
	}
	
}