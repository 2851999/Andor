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

public class ForwardPass extends RenderPass {
	
	/* The name of this pass */
	public static final String PASS_NAME = "Forward";
	
	/* The constructor */
	public ForwardPass() {
		//Call the super constructor
		super(PASS_NAME, new ShaderCode(Settings.Resources.Shaders.FORWARD_DEFAULT));
	}
	
	/* The method called to do this render pass */
	public void renderPass(Renderer renderer) {
		//Calculate the matrices for rendering
		Matrix.calculateRenderMatrices();
		//Use the shader
		this.useShader();
		
		//Check to see whether lighting should be applied
		if (Renderer.light != null) {
			//Assign the lighting values
			currentShader.setUniformf("andor_ambientLight", Renderer.ambientLight);
			currentShader.setUniformf("andor_specularIntensity", Renderer.specularIntensity);
			currentShader.setUniformf("andor_specularPower", Renderer.specularExponent);
			if (Renderer.camera != null)
				currentShader.setUniformf("andor_eyePosition", Renderer.camera.getPosition());
			//Apply the light
			Renderer.light.applyUniforms(currentShader);
			//Use the data from the shadows
			Renderer.light.getShadowData().assignValues(this, currentShader);
		} else if (Renderer.useAmbient) {
			currentShader.setUniformf("andor_ambientLight", Renderer.ambientLight);
		}
		
		//The attributes within the shader
		int vertexAttribute = currentShader.getAttributeLocation(RenderUtils.ATTRIBUTE_VERTEX);
		int colourAttribute = currentShader.getAttributeLocation(RenderUtils.ATTRIBUTE_COLOUR);
		int normalAttribute = currentShader.getAttributeLocation(RenderUtils.ATTRIBUTE_NORMAL);
		int textureCoordsAttribute = currentShader.getAttributeLocation(RenderUtils.ATTRIBUTE_TEXTURE_COORD);
		int tangentAttribute = currentShader.getAttributeLocation(RenderUtils.ATTRIBUTE_TANGENT);
		
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
		if (renderer.tangents != null)
			prepareVertexArray(tangentAttribute, renderer.tangentsHandle, renderer.vertexValuesCount);
		if (renderer.textureCoords != null)
			prepareVertexArray(textureCoordsAttribute, renderer.texturesHandle, renderer.textureCoordValuesCount);
		
		if (renderer.material.normalTextureMap != null)
			currentShader.setUniformf("andor_useNormalMap", 1.0f);
		else
			currentShader.setUniformf("andor_useNormalMap", 0.0f);
		
		if (renderer.material.displacementTextureMap != null)
			currentShader.setUniformf("andor_useDisplacementMap", 1.0f);
		else
			currentShader.setUniformf("andor_useDisplacementMap", 0.0f);
		
		//Check whether the material exists
		if (renderer.material != null)
			//Assign the material values
			RenderUtils.assignMaterial(this, renderer.material);
		
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