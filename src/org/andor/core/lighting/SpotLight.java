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

public class SpotLight extends PointLight {
	
	/* The shader code for this kind of light */
	public static ShaderCode shaderCode;
	
	/* The default shader */
	public static Shader defaultShader;
	
	/* The parameters for spot lighting */
	public Vector3D direction;
	public float cutoff;
	
	/* The constructor */
	public SpotLight(Colour colour, float intensity, Vector3D attenuation, Vector3D direction, float cutoff) {
		super(colour, intensity, attenuation, defaultShader);
		//Assign the variables
		this.direction = direction;
		this.cutoff = cutoff;
	}
	
	/* The method used to start using this light */
	public void applyUniforms(Shader shader) {
		this.applyPointUniforms(shader, "andor_spotLight.");
		shader.setUniformf("andor_spotLight.direction", this.direction);
		shader.setUniformf("andor_spotLight.cutoff", this.cutoff);
	}
	
	/* The static method used to setup the shaders necessary */
	public static void setupShaders() {
		//Check to see whether the shader needs to be setup
		if (shaderCode == null) {
			//Create the shader code
			shaderCode = new ShaderCode(Settings.Resources.Shaders.FORWARD_SPOT_LIGHT);
			shaderCode.load();
		}
		if (defaultShader == null)
			defaultShader = shaderCode.createDefault();
	}
	
}