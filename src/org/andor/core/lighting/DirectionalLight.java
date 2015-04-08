/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.lighting;

import org.andor.core.Colour;
import org.andor.core.Matrix;
import org.andor.core.Settings;
import org.andor.core.Shader;
import org.andor.core.Vector3D;
import org.andor.utils.shader.ShaderCode;

public class DirectionalLight extends BaseLight {
	
	/* The shader code for this kind of light */
	public static ShaderCode shaderCode;
	
	/* The default shader */
	public static Shader defaultShader;
	
	/* The parameters for directional lighting */
	public Vector3D direction;
	
	/* The constructor */
	public DirectionalLight(Colour colour, float intensity, Vector3D direction, float shadowArea) {
		super(colour, intensity, defaultShader);
		float halfArea = shadowArea / 2;
		this.shadowData = new ShadowData(Matrix.ortho(-halfArea, halfArea, -halfArea, halfArea, -halfArea, halfArea), true);
		//Assign the variables
		this.direction = direction;
	}
	
	/* The constructor */
	public DirectionalLight(Colour colour, float intensity, Vector3D direction) {
		this(colour, intensity, direction, 40);
	}
	
	/* The method used to start using this light */
	public void applyUniforms(Shader shader) {
		//Assign the base lights variables
		this.assignUniforms(shader, "andor_directionalLight");
		
		//Assign the light uniforms
		shader.setUniformf("andor_directionalLight.direction", direction);
	}
	
	/* The static method used to setup the shaders necessary */
	public static void setupShaders() {
		//Check to see whether the shader needs to be setup
		if (shaderCode == null) {
			//Create the shader code
			shaderCode = new ShaderCode(Settings.Resources.Shaders.FORWARD_DIRECTIONAL_LIGHT);
			shaderCode.load();
		}
		if (defaultShader == null)
			defaultShader = shaderCode.createDefault();
	}
	
}