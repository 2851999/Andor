/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.lighting;

import org.andor.core.Colour;
import org.andor.core.Object3D;
import org.andor.core.Shader;
import org.andor.core.render.Renderer;

public abstract class BaseLight extends Object3D {
	
	/* The colour of this light */
	public Colour colour;
	
	/* The intensity of this light */
	public float intensity;
	
	/* The constructor */
	public BaseLight(Colour colour, float intensity) {
		//Assign the variables
		this.colour = colour;
		this.intensity = intensity;
	}
	
	/* The method used to assign the uniforms for this in a shader */
	public void assignUniforms(Shader shader, String lightName) {
		//Assign the colour values
		shader.setUniformf(lightName + ".base.colour", this.colour.r, this.colour.g, this.colour.b);
		shader.setUniformf(lightName + ".base.intensity", this.intensity);
	}
	
	/* The method used to start using this light */
	public abstract void applyUniforms(Shader shader);
	
	/* The method used to use this light */
	public void use() {
		//Assign the current light to this one
		Renderer.light = this;
	}
	
	/* The method used to stop using all lights */
	public void stopUsing() {
		//Assign the current light to this one
		Renderer.light = null;
	}
	
}