/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.lighting;

import java.util.Arrays;

import org.andor.core.Matrix;
import org.andor.core.Matrix4D;
import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.Vector3D;
import org.andor.core.render.RenderPass;
import org.andor.utils.shader.ShaderCode;
import org.andor.utils.shader.UniformVector2D;

public class ShadowData {
	
	/* The shader code for this kind of light */
	public static ShaderCode shaderCode;
	
	/* The default shader */
	public static Shader defaultShader;
	
	/* The shader code for this kind of light */
	public static ShaderCode filterShaderCode;
	
	/* The default shader */
	public static Shader filterShader;
	
	/* The boolean value that determines whether shadows should be cast */
	public boolean castShadows;
	
	/* The bias value (For shadow acne) */
//	public float bias;
	
	/* The boolean that determines whether the faces will be flipped
	 * when generating the shadow map */
	public boolean flipFaces;
	
	/* The shadow map */
	public ShadowMap shadowMap;
	public float shadowSoftness;
	public float shadowMinimumVariance;
	public float shadowLightBleedReduction;
	
	/* The projection matrix */
	public Matrix4D projectionMatrix;
	
	/* The constructor */
	public ShadowData(Matrix4D projectionMatrix, boolean flipFaces) {
		//Assign the variables
		this.castShadows = true;
//		this.bias = 0.0f;
		this.flipFaces = flipFaces;
		this.shadowSoftness = 0.4f;
		this.shadowMinimumVariance = 0.2f;
		this.shadowLightBleedReduction = 0.000002f;
		this.projectionMatrix = projectionMatrix;
		//Create the shadow map
		this.shadowMap = new ShadowMap(1024, 1024);
	}
	
	/* The method used to use this data (Setup the matrix) */
	public void use(BaseLight light) {
		//Setup the light matrix
		Matrix.lightProjectionMatrix.values = Arrays.copyOf(this.projectionMatrix.values, 16);
		//Transform to the light's position
		//Get the rotation
		Vector3D r = light.getRotation();
		Vector3D p = light.getPosition();
		Vector3D s = light.getScale();
		Matrix.loadIdentity(Matrix.lightViewMatrix);
		Matrix.lightViewMatrix = Matrix.scale(Matrix.lightViewMatrix, s);
		
		Matrix.lightViewMatrix = Matrix.rotate(Matrix.lightViewMatrix, r.x, 1, 0, 0);
		Matrix.lightViewMatrix = Matrix.rotate(Matrix.lightViewMatrix, r.y, 0, 1, 0);
		Matrix.lightViewMatrix = Matrix.rotate(Matrix.lightViewMatrix, r.z, 0, 0, 1);
		
		Matrix.lightViewMatrix = Matrix.translate(Matrix.lightViewMatrix, p.multiplyNew(new Vector3D(1f, 1f, 1f).addNew(new Vector3D(0, 0, 0))));
	}
	
	/* The method used to assign the needed values in the shaders */
	public void assignValues(RenderPass pass, Shader shader) {
		shader.setUniformMatrix("andor_lightMatrix", Matrix.lightMatrix);
		if (this.shadowMap != null) {
			shader.setUniformi("andor_shadowMap", pass.bindTexture(this.shadowMap.getShadowMap().getPointer()));
			shader.setUniformf("andor_shadowVarianceMin", this.shadowMinimumVariance);
			shader.setUniformf("andor_shadowVarianceLightBleedReduction", this.shadowLightBleedReduction);
		}
		//shader.setUniformf("andor_shadowMapTexelSize", new Vector3D(1.0f / 1024.0f, 1.0f / 1024f, 0.0f));
		//shader.setUniformf("andor_shadowBias", this.bias / 1024f);
	}
	
	/* The getter and setters */
	public void setCastShadows(boolean castShadows) { this.castShadows = castShadows; }
	public void setFlipFaces(boolean flipFaces) { this.flipFaces = flipFaces; }
	public void setShadowSoftness(float shadowSoftness) { this.shadowSoftness = shadowSoftness; }
	public void setMinimumVariance(float shadowMinimumVariance) { this.shadowMinimumVariance = shadowMinimumVariance; }
	public void setLightBleedReduction(float shadowLightBleedReduction) { this.shadowLightBleedReduction = shadowLightBleedReduction; }
	public void setShadowMap(ShadowMap shadowMap) { this.shadowMap = shadowMap; }
	public boolean shouldCastShadows() { return this.castShadows; }
	public boolean shouldFlipFaces() { return this.flipFaces; }
	public float getShadowSoftness() { return this.shadowSoftness; }
	public float getMinimumVariance() { return this.shadowMinimumVariance; }
	public float getLightBleedReduction() { return this.shadowLightBleedReduction; }
	public ShadowMap getShadowMap() { return this.shadowMap; }
	
	/* The static method used to setup the shaders necessary */
	public static void setupShaders() {
		//Check to see whether the shaders need to be setup
		if (shaderCode == null) {
			//Create the shader code
			shaderCode = new ShaderCode(Settings.Resources.Shaders.SHADOWMAP_GENERATOR);
			shaderCode.load();
		}
		if (defaultShader == null)
			defaultShader = shaderCode.createDefault();
		
		if (filterShaderCode == null) {
			//Create the shader code
			filterShaderCode = new ShaderCode(Settings.Resources.Shaders.SHADOWMAP_FILTER);
			filterShaderCode.load();
			
			//Add the required uniforms
			filterShaderCode.getUniforms().addUniform(new UniformVector2D("blurScale"));
		}
		if (filterShader == null)
			filterShader = filterShaderCode.createDefault();
	}
	
}