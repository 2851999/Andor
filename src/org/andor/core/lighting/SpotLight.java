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

public class SpotLight extends PointLight {
	
	/* The parameters for spot lighting */
	public Vector3D direction;
	public float cutoff;
	
	/* The constructor */
	public SpotLight(Colour colour, float intensity, Vector3D attenuation, Vector3D direction, float cutoff) {
		super(colour, intensity, attenuation);
		//Assign the variables
		this.direction = direction;
		this.cutoff = cutoff;
	}
	
	/* The method used to start using this light */
	public void applyUniforms(Shader shader) {
		this.applyPointUniforms(shader, "spotLight.");
		shader.setUniformf("spotLight.direction", this.direction);
		shader.setUniformf("spotLight.cutoff", this.cutoff);
	}
	
}