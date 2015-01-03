/* *********************************************
 * ANDOR
 * 
 * USE - EDUCATIONAL PURPOSES ONLY
 *
 * COPYRIGHT @ 2014
 **********************************************/

package org.andor.core.model;

import java.nio.FloatBuffer;

import org.andor.core.Colour;
import org.andor.core.Image;
import org.andor.utils.BufferUtils;
import org.lwjgl.opengl.GL11;

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
	public double shininess;

	/* The alpha colour value */
	public double alphaColourValue;

	/* The alpha texture map */
	public Image alphaTextureMap;

	/* The constructor */
	public Material(String name) {
		//Assign the variables
		this.name = name;
		this.ambientColour = null;
		this.diffuseColour = null;
		this.specularColour = null;
		this.shininess = 0;
		this.alphaColourValue = 1.0;
		this.alphaTextureMap = null;
	}

	/* The method used to apply the material */
	public void apply() {
		//Check to see whether the alpha texture has been set
		if (this.alphaTextureMap != null)
			//Bind the alpha texture map
			this.alphaTextureMap.bind();
		//Check to see whether any of the colours should be used
		if (this.ambientColour != null) {
			//Get the float buffer for the colour
			FloatBuffer buffer = BufferUtils.createFlippedBuffer(this.ambientColour.getValuesRGBA());
			//Set the material property
			GL11.glMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT, buffer);
		}
		if (this.diffuseColour != null) {
			//Get the float buffer for the colour
			FloatBuffer buffer = BufferUtils.createFlippedBuffer(this.diffuseColour.getValuesRGBA());
			//Set the material property
			GL11.glMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_DIFFUSE, buffer);
		}
		if (this.specularColour != null) {
			//Get the float buffer for the colour
			FloatBuffer buffer = BufferUtils.createFlippedBuffer(this.specularColour.getValuesRGBA());
			//Set the material property
			GL11.glMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_SPECULAR, buffer);
		}
		//Set the shininess material property
		GL11.glMaterialf(GL11.GL_FRONT_AND_BACK, GL11.GL_SHININESS, (float) this.shininess);
	}
	
}