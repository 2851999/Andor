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
	
	/* The shader for this type of light */
	public Shader shader;
	
	/* The shadow data */
	public ShadowData shadowData;
	
	/* The constructor */
	public BaseLight(Colour colour, float intensity, Shader shader) {
		//Assign the variables
		this.colour = colour;
		this.intensity = intensity;
		this.shader = shader;
	}
	
	/* The constructor */
	public BaseLight(Colour colour, float intensity, Shader shader, ShadowData shadowData) {
		this(colour, intensity, shader);
		this.shadowData = shadowData;
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
		//Replace the shader in the render pass
		Renderer.getPass().customShader = this.shader;
	}
	
	/* The method used to stop using all lights */
	public void stopUsing() {
		//Assign the current light to this one
		Renderer.light = null;
		//Reset the custom shader
		Renderer.getPass().customShader = null;
	}
	
	/* The getter and setters */
	public void setColour(Colour colour) { this.colour = colour; }
	public void setIntensity(float intensity) { this.intensity = intensity; }
	public void setShadowData(ShadowData shadowData) { this.shadowData = shadowData; }
	public Colour getColour() { return this.colour; }
	public float getIntensity() { return this.intensity; }
	public ShadowData getShadowData() { return this.shadowData; }
	
}