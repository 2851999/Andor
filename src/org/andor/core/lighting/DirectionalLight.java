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

public class DirectionalLight extends BaseLight {
	
	/* The parameters for directional lighting */
	public Vector3D direction;
	
	/* The constructor */
	public DirectionalLight(Colour colour, float intensity, Vector3D direction) {
		super(colour, intensity);
		//Assign the variables
		this.direction = direction;
	}
	
	/* The method used to start using this light */
	public void applyUniforms(Shader shader) {
		//Assign the base lights variables
		this.assignUniforms(shader, "directionalLight");
		
		//Assign the light uniforms
		shader.setUniformf("directionalLight.direction", direction);
	}
	
}