/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.lighting;

import org.andor.core.Colour;
import org.andor.core.Shader;
import org.andor.core.Vector3D;

public class PointLight extends BaseLight {
	
	/* The parameters for point lighting */
	public Vector3D attenuation;
	public float range;
	
	/* The constructor */
	public PointLight(Colour colour, float intensity, Vector3D attenuation) {
		super(colour, intensity);
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
		this.applyPointUniforms(shader, "");
	}
	
}