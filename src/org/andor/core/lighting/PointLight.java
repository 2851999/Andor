/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.lighting;

import org.andor.core.Colour;
import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.Vector3D;
import org.andor.utils.shader.ShaderCode;

public class PointLight extends BaseLight {
	
	/* The shader code for this kind of light */
	public static ShaderCode shaderCode;
	
	/* The default shader */
	public static Shader defaultShader;
	
	/* The parameters for point lighting */
	public Vector3D attenuation;
	public float range;
	
	/* The constructor */
	public PointLight(Colour colour, float intensity, Vector3D attenuation) {
		super(colour, intensity, defaultShader);
		//Assign the variables
		this.attenuation = attenuation;
		this.range = 20f;
	}
	
	/* The constructor */
	public PointLight(Colour colour, float intensity, Vector3D attenuation, ShadowData shadowData) {
		super(colour, intensity, defaultShader, shadowData);
		//Assign the variables
		this.attenuation = attenuation;
		this.range = 20f;
	}
	
	/* The constructor */
	public PointLight(Colour colour, float intensity, Vector3D attenuation, Shader shader) {
		super(colour, intensity, shader);
		//Assign the variables
		this.attenuation = attenuation;
		this.range = 20f;
	}
	
	/* The constructor */
	public PointLight(Colour colour, float intensity, Vector3D attenuation, Shader shader, ShadowData shadowData) {
		super(colour, intensity, shader, shadowData);
		//Assign the variables
		this.attenuation = attenuation;
		this.range = 20f;
	}
	
	protected void applyPointUniforms(Shader shader, String prefix) {
		//Assign the base uniforms
		this.assignUniforms(shader, prefix + "pointLight");
		//Assign the light uniforms
		shader.setUniformf(prefix + "pointLight.position", this.getPosition());
		shader.setUniformf(prefix + "pointLight.range", this.range);
		shader.setUniformf(prefix + "pointLight.attenuation.constant", attenuation.x);
		shader.setUniformf(prefix + "pointLight.attenuation.linear", attenuation.y);
		shader.setUniformf(prefix + "pointLight.attenuation.exponent", attenuation.z);
	}
	
	/* The method used to start using this light */
	public void applyUniforms(Shader shader) {
		this.applyPointUniforms(shader, "andor_");
	}
	
	/* The static method used to setup the shaders necessary */
	public static void setupShaders() {
		//Check to see whether the shader needs to be setup
		if (shaderCode == null) {
			//Create the shader code
			shaderCode = new ShaderCode(Settings.Resources.Shaders.FORWARD_POINT_LIGHT);
			shaderCode.load();
		}
		if (defaultShader == null)
			defaultShader = shaderCode.createDefault();
	}
	
}