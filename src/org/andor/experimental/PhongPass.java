/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.experimental;

import org.andor.core.Colour;
import org.andor.core.Matrix;
import org.andor.core.render.RenderPass;
import org.andor.core.render.Renderer;
import org.andor.tests.LightingTest;
import org.andor.utils.GLUtils;
import org.andor.utils.RenderUtils;
import org.andor.utils.ShaderCode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

public class PhongPass extends RenderPass {
	
	/* The name of this pass */
	public static final String PASS_NAME = "Forward";
	
	/* The constructor */
	public PhongPass() {
		//Call the super constructor
		super(PASS_NAME, new ShaderCode("/resources/andor/shaders/render/forward/phongshader"));
	}
	
	/* The method called to do this render pass */
	public void renderPass(Renderer renderer) {
		//Calculate the matrices for rendering
		Matrix.calculateRenderMatrices();
		//Use the shader
		this.useShader();
		
		currentShader.setUniformf("ambientLight", new Colour(0.1f, 0.1f, 0.1f));
		//currentShader.setUniformf("directionalLight.base.colour", Colour.WHITE.getR(), Colour.WHITE.getG(), Colour.WHITE.getB());
		//currentShader.setUniformf("directionalLight.base.intensity", 0.8f);
		//currentShader.setUniformf("directionalLight.direction", new Vector3D(0, 0, 1));
		
//		currentShader.setUniformf("pointLight.base.colour", Colour.WHITE.getR(), 0, 0);
//		currentShader.setUniformf("pointLight.base.intensity", 0.8f);
//		currentShader.setUniformf("pointLight.attenuation.constant", 0f);
//		currentShader.setUniformf("pointLight.attenuation.linear", 0f);
//		currentShader.setUniformf("pointLight.attenuation.exponent", 1f);
//		currentShader.setUniformf("pointLight.position", new Vector3D(0, 0, 2));
//		currentShader.setUniformf("pointLight.range", 20);
		
//		currentShader.setUniformf("spotLight.pointLight.base.colour", Colour.WHITE.getR(), 0, 0);
//		currentShader.setUniformf("spotLight.pointLight.base.intensity", 0.8f);
//		currentShader.setUniformf("spotLight.pointLight.attenuation.constant", 0f);
//		currentShader.setUniformf("spotLight.pointLight.attenuation.linear", 0f);
//		currentShader.setUniformf("spotLight.pointLight.attenuation.exponent", 0.4f);
//		currentShader.setUniformf("spotLight.pointLight.position", new Vector3D(0, 0, 2));
//		currentShader.setUniformf("spotLight.pointLight.range", 30f);
//		currentShader.setUniformf("spotLight.direction", new Vector3D(0, 0, 1));
//		currentShader.setUniformf("spotLight.cutoff", 0.9f);
		
		currentShader.setUniformf("specularIntensity", 2f);
		currentShader.setUniformf("specularPower", 32f);
		currentShader.setUniformf("eyePos", LightingTest.camera.getPosition());
		
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