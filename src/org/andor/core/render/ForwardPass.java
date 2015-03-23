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
import org.andor.utils.ShaderCode;
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
			currentShader.setUniformf("lighting", 1.0f);
			currentShader.setUniformf("ambientLight", Renderer.ambientLight);
			currentShader.setUniformf("specularIntensity", Renderer.specularIntensity);
			currentShader.setUniformf("specularPower", Renderer.specularExponent);
			//if (Renderer.camera != null)
				currentShader.setUniformf("eyePos", Renderer.camera.getPosition());
			//Apply the light
			Renderer.light.applyUniforms(currentShader);
		} else if (Renderer.useAmbient) {
			currentShader.setUniformf("lighting", 1.0f);
			currentShader.setUniformf("directionalLight.base.intensity", 0);
			currentShader.setUniformf("pointLight.base.intensity", 0);
			currentShader.setUniformf("spotLight.pointLight.base.intensity", 0);
		} else {
			//Prevent lighting
			currentShader.setUniformf("lighting", 0.0f);
			currentShader.setUniformf("directionalLight.base.intensity", 0);
			currentShader.setUniformf("pointLight.base.intensity", 0);
			currentShader.setUniformf("spotLight.pointLight.base.intensity", 0);
		}
		
		//The attributes within the shader
		int vertexAttribute = currentShader.getAttributeLocation(RenderUtils.ATTRIBUTE_VERTEX);
		int colourAttribute = currentShader.getAttributeLocation(RenderUtils.ATTRIBUTE_COLOUR);
		int normalAttribute = currentShader.getAttributeLocation(RenderUtils.ATTRIBUTE_NORMAL);
		int textureCoordsAttribute = currentShader.getAttributeLocation(RenderUtils.ATTRIBUTE_TEXTURE_COORD);
		
		//Give the shader the matrices
		currentShader.setUniformMatrix(RenderUtils.UNIFORM_MODEL_VIEW_PROJECTION_MATRIX, Matrix.modelViewProjectionMatrix);
		currentShader.setUniformMatrix(RenderUtils.UNIFORM_NORMAL_MATRIX, Matrix.normalMatrix);
		currentShader.setUniformMatrix(RenderUtils.UNIFORM_MODEL_MATRIX, Matrix.modelMatrix);
		
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
		
		//Check the image
		if (renderer.texture != null)
			//Bind the texture
			currentShader.setUniformi(RenderUtils.UNIFORM_TEXTURE, this.bindTexture(renderer.texture.getPointer()));
		
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