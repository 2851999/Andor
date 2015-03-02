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

	/* The constructor */
	public Material(String name) {
		//Assign the variables
		this.name = name;
		this.ambientColour = null;
		this.diffuseColour = null;
		this.specularColour = null;
		this.shininess = 0;
		this.alphaColourValue = 0;
	}
	
}