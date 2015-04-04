/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014-2015
 **********************************************/

package org.andor.core.model;

import org.andor.core.Colour;
import org.andor.core.Image;

public class Material {
	
	/* The name of this material */
	public String name;

	/* The ambient colour of this material */
	public Colour ambientColour;

	/* The diffuse colour of this material */
	public Colour diffuseColour;

	/* The specular colour of this material */
	public Colour specularColour;

	/* The shininess of this material */
	public float shininess;

	/* The alpha colour value */
	public float alphaColourValue;

	/* The alpha texture map */
	public Image alphaTextureMap;
	
	/* The diffuse texture map */
	public Image ambientTextureMap;
	
	/* The diffuse texture map */
	public Image diffuseTextureMap;
	
	/* The diffuse texture map */
	public Image specularTextureMap;
	
	/* The normal texture map */
	public Image normalTextureMap;
	
	/* The displace texture map */
	public Image displacementTextureMap;
	public float displacementMapScale;
	public float displacementMapBias;

	/* The constructor */
	public Material(String name) {
		//Assign the variables
		this.name = name;
		this.ambientColour = null;
		this.diffuseColour = null;
		this.specularColour = null;
		this.shininess = 0;
		this.alphaColourValue = 0;
		this.displacementMapScale = 0.01f;
		this.displacementMapBias = 0f;
	}
	
	/* The getter and setters */
	public void setAmbientColour(Colour ambientColour) { this.ambientColour = ambientColour; }
	public void setDiffuseColour(Colour diffuseColour) { this.diffuseColour = diffuseColour; }
	public void setSpecularColour(Colour specularColour) { this.specularColour = specularColour; }
	public void setShininess(float shininess) { this.shininess = shininess; }
	public void setAlphaColourValue(float alphaColourValue) { this.alphaColourValue = alphaColourValue; }
	public void setAlphaTextureMap(Image alphaTextureMap) { this.alphaTextureMap = alphaTextureMap; }
	public void setAmbientTextureMap(Image ambientTextureMap) { this.ambientTextureMap = ambientTextureMap; }
	public void setDiffuseTextureMap(Image diffuseTextureMap) { this.diffuseTextureMap = diffuseTextureMap; }
	public void setSpecularTextureMap(Image specularTextureMap) { this.specularTextureMap = specularTextureMap; }
	public void setNormalTextureMap(Image normalTextureMap) { this.normalTextureMap = normalTextureMap; }
	public void setDisplacementTextureMap(Image displacementTextureMap) { this.displacementTextureMap = displacementTextureMap; }
	public String getName() { return this.name; }
	public Colour getAmbientColour() { return this.ambientColour; }
	public Colour getDiffuseColour() { return this.diffuseColour; }
	public Colour getSpecularColour() { return this.specularColour; }
	public float getShininess() { return this.shininess; }
	public float getAlphaColourValue() { return this.alphaColourValue; }
	public Image getAlphaTextureMap() { return this.alphaTextureMap; }
	public Image getAmbientTextureMap() { return this.ambientTextureMap; }
	public Image getDiffuseTextureMap() { return this.diffuseTextureMap; }
	public Image getSpecularTextureMap() { return this.specularTextureMap; }
	public Image getNormalTextureMap() { return this.normalTextureMap; }
	public Image getDisplacementTextureMap() { return this.displacementTextureMap; }
	
}